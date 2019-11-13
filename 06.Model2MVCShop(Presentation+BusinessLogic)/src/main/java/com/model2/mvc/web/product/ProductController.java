package com.model2.mvc.web.product;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;

@Controller
public class ProductController {

	//Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	
	
	@RequestMapping("/addProductView.do")
	public String addProductView() throws Exception {
	
		System.out.println("/addProductView.do");
		
		return "redirect:/product/addProductView.jsp";
	}
	
	@RequestMapping("/addProduct.do")
	public String addProduct(@ModelAttribute("product") Product product, Model model) throws Exception{
		
		System.out.println("/addProduct.do");
		//Business Logic
		if(product.getManuDate()!=null) {
			product.setManuDate(product.getManuDate().replaceAll("-", ""));		
		}
		productService.addProduct(product);
		model.addAttribute("product",product);
		
		return "forward:/product/addProduct.jsp";
	} 
	
	@RequestMapping("/getProduct.do")
	public String getProduct(@ModelAttribute("product") Product product , Model model ) throws Exception {
		
		System.out.println("/getProduct.do");
	
		product = productService.getProduct(product.getProdNo());
		model.addAttribute("product", product);
		
		return "forward:/product/readProduct.jsp";
	}
	
	@RequestMapping("/updateProductView.do")
	public String updateProductView(@ModelAttribute("product") Product product , Model model ) throws Exception {

		System.out.println("/updateProductView.do");
		
		product = productService.getProduct(product.getProdNo());
		model.addAttribute("product", product);
		
		return "forward:/product/updateProduct.jsp";
	}
	
	@RequestMapping("/updateProduct.do")
	public String updateProduct( @ModelAttribute("product") Product product) throws Exception{

		System.out.println("/updateProduct.do");
		
	   	productService.updateProduct(product);
	
		return "redirect:/getProduct.do?prodNo="+product.getProdNo();
	}
	
	
	
	
	@RequestMapping("/listProduct.do")
	public String listProduct(@ModelAttribute("Search") Search search, @RequestParam("menu") String menu, Model model) throws Exception{

		System.out.println("/listProduct.do");
		//Business Logic
	   	
	   	if(search.getCurrentPage()==0) {
	   		search.setCurrentPage(1);
	   	}
	   	search.setPageSize(pageSize);
	   	
	   	Map<String, Object> map = productService.getProductList(search);
	   	
	  	Page resultPage	= 
				new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
	   	
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("menu", menu);
		

		return "forward:/product/listProduct.jsp";
	}
	
	
}
