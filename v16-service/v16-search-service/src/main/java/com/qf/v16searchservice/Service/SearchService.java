package com.qf.v16searchservice.Service;/*
@author:g
@Date:2019/8/9
    */

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.api.ISearchService;
import com.qf.v16.entity.TProduct;
import com.qf.v16.mapper.TProductMapper;
import com.qf.v16.mapper.TProductTypeMapper;
import com.qf.v16.pojo.ResultBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchService implements ISearchService {

    @Autowired
    private TProductMapper productMapper;
    @Autowired
    private SolrClient solrClient;
    @Override
    public ResultBean synAllDate() {
        List<TProduct> list=productMapper.list();
        for (TProduct product : list) {
            SolrInputDocument document=new SolrInputDocument();
            document.setField("id",product.getId());
            document.setField("product_name",product.getName());
            document.setField("product_price",product.getPrice());
            document.setField("product_sale_point",product.getSalePoint());



            try {
                solrClient.add(document);
            } catch (SolrServerException |IOException e) {
                e.printStackTrace();
                return new ResultBean("500","同步索引块失败");
            }
        }
        try {
            solrClient.commit();
        } catch (SolrServerException | IOException e) {

            e.printStackTrace();
            return new ResultBean("500","提交索引块失败");
        }
        return  new ResultBean("200","提交索引块成功");
    }

    @Override
    public ResultBean queryByKeywords(String keywords) {
        SolrQuery query=new SolrQuery();
        if(!StringUtils.isAnyEmpty(keywords)){
            query.setQuery("product_name:"+keywords);
        }else {
            query.setQuery("product_name:mate30");
        }
         query.setHighlight(true);
        query.addHighlightField("product_name");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(query.addHighlightField("product_name"));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");
        



        try {
            QueryResponse response= solrClient.query(query);
            SolrDocumentList result=response.getResults();
            List<TProduct> target=new ArrayList<>(result.size());
            
            //设置高亮
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

            for (SolrDocument document : result) {
                TProduct product=new TProduct();
                product.setId(Long.parseLong(document.getFieldValue("id").toString()));
             //   product.setName(document.getFieldValue("product_name").toString());
                product.setSalePoint(document.getFieldValue("product_sale_point").toString());
                product.setPrice(Long.parseLong(document.getFieldValue("product_price").toString()));


                //设置高亮信息
                Map<String, List<String>> idHighlight = highlighting.get(document.getFieldValue("id").toString());

                List<String> productNameHightLiight=idHighlight.get("product_name");

                if(productNameHightLiight != null){
                    product.setName(productNameHightLiight.get(0));
                }else {
                    product.setName(document.getFieldValue("product_name").toString());
                }

                target.add(product);
            }
            return new ResultBean("200",target);
        } catch (SolrServerException |IOException e) {
            e.printStackTrace();
            return new ResultBean("500","查询失败");
        }



        }

    @Override
    public ResultBean updateById(Long id) {
        TProduct product = productMapper.selectByPrimaryKey(id);

        SolrInputDocument document=new SolrInputDocument();
        document.setField("id",product.getId());
        document.setField("product_name",product.getName());
        document.setField("product_price",product.getPrice());
        document.setField("product_sale_point",product.getSalePoint());

        try {
            solrClient.add(document);
        } catch (SolrServerException |IOException e) {
            e.printStackTrace();
            return new ResultBean("500","同步索引块失败");
        }
        try {
            solrClient.commit();
        } catch (SolrServerException | IOException e) {

            e.printStackTrace();
            return new ResultBean("500","提交索引块失败");
        }
        return  new ResultBean("200","提交索引块成功");

    }
}
