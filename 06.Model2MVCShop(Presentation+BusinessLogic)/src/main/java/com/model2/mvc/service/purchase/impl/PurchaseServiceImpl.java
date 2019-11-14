package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.domain.Purchase;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao purchaseDao; 
	
	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}
	
	//C
	public PurchaseServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDao.insertPurchase(purchase);
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		System.out.println("[Impl:getPurchase호출]");
		return purchaseDao.findPurchase(tranNo);
						}

	@Override
	public Purchase getPurchase2(int prodNo) throws Exception {
		System.out.println("[Impl:getPurchase2호출]");
		return purchaseDao.findPurchase2(prodNo);
	}

	@Override
	public Map<String,Object> getPurchaseList(Search search, String buyerId) throws Exception {
		System.out.println("[serviceImpl::getPurchaseList호출]");
		return purchaseDao.getPurchaseList(search, buyerId);
	}

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		System.out.println("[serviceImpl::getSaleList호출]");
		return purchaseDao.getSaleList(search);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		System.out.println("[serviceImpl::updatePurchase호출]");
		purchaseDao.updatePurchase(purchase);	
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		System.out.println("[serviceImpl::updateTranCode호출]");
		purchaseDao.updateTranCode(purchase);
		
	}

	@Override
	public void updateTranCode2(Purchase purchase) throws Exception {
		System.out.println("[serviceImpl::updateTranCode호출]");
		purchaseDao.updateTranCode2(purchase);
		
	}
}
