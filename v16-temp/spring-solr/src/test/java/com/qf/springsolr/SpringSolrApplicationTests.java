package com.qf.springsolr;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSolrApplicationTests {

   @Autowired
   private  SolrClient solrClient;
    @Test
    public void adupload() throws IOException, SolrServerException {
        SolrInputDocument document=new SolrInputDocument();
        document.setField("id","102");
        document.setField("product_name","mate30");
        document.setField("product_price","88881111");
        document.setField("product_sale_point","能拍月亮的手机");
        document.setField("product_images","666");
        solrClient.add(document);
        solrClient.commit();
    }
    @Test
    public void  selectTest() throws IOException, SolrServerException {
        SolrQuery query=new SolrQuery();
        query.setQuery("product_name:mate30");
        QueryResponse response=solrClient.query(query);

        SolrDocumentList result=response.getResults();
        for (SolrDocument entries : result) {
            System.err.println(entries.getFieldValue("product_name"));
        }
    }
    @Test
    public void delTest() throws IOException, SolrServerException {
        solrClient.deleteByQuery("product_name:mate30");
        solrClient.commit();
    }
}
