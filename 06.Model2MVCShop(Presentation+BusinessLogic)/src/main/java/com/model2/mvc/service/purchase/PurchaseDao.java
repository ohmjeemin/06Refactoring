package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {

	///M
	public void insertPurchase(Purchase purchase) throws Exception;
	
	public Map<String,Object> getPurchaseList(Search search, String buyerId) throws Exception;
	
	public Purchase findPurchase(int tranNo) throws Exception; 

	public Purchase findPurchase2(int prodNo) throws Exception;
	
	public void updatePurchase(Purchase purchase) throws Exception;
	
	public void updateTranCode(Purchase purchase) throws Exception;
	
	public void updateTranCode2(Purchase purchase) throws Exception;
	
	public Map<String,Object> getSaleList(Search search) throws Exception;
	
	public String makeCurrentPageSql(String sql , Search search);
	
	
}
