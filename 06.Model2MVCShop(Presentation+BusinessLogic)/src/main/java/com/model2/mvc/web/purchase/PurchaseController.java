package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpSession;

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
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

@Controller
public class PurchaseController {

	
	
	
	//F
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	
	
	
	
	//C
	public PurchaseController() {
		System.out.println(this.getClass());
	}
	
	
	
	
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	
	
	
	
	@RequestMapping("/addPurchaseView.do")
	public String addPurchaseView(@RequestParam("prodNo") int prodNo, Model model ) throws Exception {
		
		System.out.println("/addPurchaseView.do");
		
		System.out.println("    prodNo ::: "+prodNo);
		
		Product product = productService.getProduct(prodNo);
		
		System.out.println("    prod ::: "+product);
		model.addAttribute("product", product);
	
		return "forward:/purchase/addPurchaseView.jsp";	
	
	}
	
	
	
	
	
	@RequestMapping("/addPurchase.do")
	public String addPurchase(@ModelAttribute("purchase") Purchase purchase, @RequestParam("prodNo") int prodNo, Model model, HttpSession session) throws Exception {
		
		System.out.println("/addPurchase.do");
		System.out.println(purchase);
		
		User user =(User)session.getAttribute("user");
		purchase.setBuyer(user);
		
		Product product = productService.getProduct(prodNo);
		purchase.setPurchaseProd(product);
		
		
		purchaseService.addPurchase(purchase);
		model.addAttribute("purchase", purchase);
		
		return "forward:/purchase/addPurchase.jsp";

	}
	
	
	
	
	
	
	@RequestMapping("/listPurchase.do")
	public String listPurchase(@ModelAttribute("Search") Search search, Model model, HttpSession session) throws Exception {
		
		
		if(search.getCurrentPage()==0) {
	   		search.setCurrentPage(1);
	   	}
	   	search.setPageSize(pageSize);
	   	
	   	
	   	User user = (User)session.getAttribute("user");
	   	String buyerId = user.getUserId();
	   	Map<String, Object> map = purchaseService.getPurchaseList(search, buyerId);
	   	
	   	Page resultPage	= 
				new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
	   	
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
	
		
		
		return "forward:/purchase/listPurchase.jsp";
	}
	
	
	
	
	
	
	@RequestMapping("/getPurchase.do")
	public String getPurchase(@ModelAttribute("purchase") Purchase purchase, Model model) throws Exception{
		
		purchase = purchaseService.getPurchase(purchase.getTranNo());
		model.addAttribute("purchase", purchase);
		
		return "forward:/purchase/getPurchase.jsp";
	}
	
	
	
	
	

	@RequestMapping("/updatePurchaseView.do")
	public String updatePurchaseView(@RequestParam("tranNo") int tranNo, @ModelAttribute("purchase") Purchase purchase, Model model) throws Exception {
				
		purchase = purchaseService.getPurchase(tranNo);
		model.addAttribute("purchase", purchase);
		System.out.println(purchase);
		return "forward:/purchase/updatePurchaseView.jsp";
	}

	
	
	
	

	@RequestMapping("/updatePurchase.do")
	public String updatePurchase(@ModelAttribute("purchase") Purchase purchase, Model model) throws Exception {
		
		purchaseService.updatePurchase(purchase);
		model.addAttribute("purchase", purchase);
				
		return "redirect:/getPurchase.do?tranNo="+purchase.getTranNo();
		
	}

	
	
	
	
	@RequestMapping("/listSale.do")
	public String listSale(@ModelAttribute("search") Search search, Model model) throws Exception {
		
		if(search.getCurrentPage()==0) {
	   		search.setCurrentPage(1);
	   	}
	   	search.setPageSize(pageSize);
	   	
	   	
		Map<String, Object> map  = purchaseService.getSaleList(search);
		
	 	Page resultPage	= 
				new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
	   	
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
	
		return "forward:/purchase/listSale.jsp";
		
	}
	
	
	
	
	@RequestMapping("/updateTranCode.do")
	public String updateTranCode(@ModelAttribute("purchase") Purchase purchase, @RequestParam("tranCode") String tranCode, @RequestParam("tranNo") int tranNo, Model model) throws Exception {
		
		purchase.setTranNo(tranNo);
		purchase.setTranCode(tranCode);
		purchaseService.updateTranCode(purchase);
		model.addAttribute("purchase", purchase);
				
		return "redirect:/listPurchase.do?menu=search";
		
	}


	
	
	@RequestMapping("/updateTranCodeByProd.do")
	public String updateTranCodeByProd(@ModelAttribute("purchase") Purchase purchase, @RequestParam("tranCode") String tranCode, @RequestParam("prodNo") int prodNo, Model model) throws Exception {
		
		Product product = productService.getProduct(prodNo);
		
		purchase.setPurchaseProd(product);
		purchase.setTranCode(tranCode);
		purchaseService.updateTranCode2(purchase);
		model.addAttribute("purchase", purchase);
				
		return "forward:/listSale.do";
	}
	
}
