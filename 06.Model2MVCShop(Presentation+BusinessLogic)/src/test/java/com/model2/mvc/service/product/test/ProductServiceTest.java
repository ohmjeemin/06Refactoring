package com.model2.mvc.service.product.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })

public class ProductServiceTest {	
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Test
	public void testAddProduct() throws Exception{
	
		Product product = new Product();
		product.setProdNo(20000);
		product.setProdName("주디필통");
		product.setProdDetail("분홍색");
		product.setPrice(8000);
		product.setManuDate("20190303");
		
		productService.addProduct(product);
		product = productService.getProduct(20000);
	
		Assert.assertEquals("주디필통", product.getProdName());
		Assert.assertEquals("분홍색", product.getProdDetail());
		
	}
	
}
