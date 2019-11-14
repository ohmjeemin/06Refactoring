package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {

	
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	//C
	public PurchaseDaoImpl() {
		System.out.println(this.getClass());
	}

	
	@Override
	public void insertPurchase(Purchase purchase) throws Exception {
		sqlSession.insert("PurchaseMapper.addPurchase", purchase);

	}

	@Override
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
			map.put("search", search);
			map.put("buyerId", buyerId);
		
			System.out.println("getPurchaseList() >>>>>>>>>>>>>" + map);
		
			List<Purchase> list = sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
		
			System.out.println("getPurchaseList() >>>>>>>>>>>>>" + list);
			
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setBuyer((User)sqlSession.selectOne("UserMapper.getUser", list.get(i).getBuyer().getUserId()));
				list.get(i).setPurchaseProd((Product)sqlSession.selectOne("ProductMapper.getProduct", list.get(i).getPurchaseProd().getProdNo()));
			}
			
			map.put("totalCount", sqlSession.selectOne("PurchaseMapper.getTotalCount", map));
			map.put("list", list);

		return map;
	}
	

	@Override
	public Purchase findPurchase(int tranNo) throws Exception {
        System.out.println(">>>>>>>>>>>>>findPurchase() call....");
		return 	sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
	}

	@Override
	public Purchase findPurchase2(int prodNo) throws Exception {
		return 	sqlSession.selectOne("PurchaseMapper.getPurchase2", prodNo);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updateTranCode", purchase);
	}

	@Override
	public void updateTranCode2(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updateTranCodebyProd", purchase);

	}

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
			String buyerId ="user01";
			map.put("search", search);
			map.put("buyerId", buyerId);
			System.out.println("getPurchaseList() ÀÇ map: >>>>>>>>>>>>>" + map);
		
			List<Purchase> list = sqlSession.selectList("PurchaseMapper.getSaleList", map);
		
			System.out.println("getPurchaseList() ÀÇ list:>>>>>>>>>>>>>" + list);
			
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setBuyer((User)sqlSession.selectOne("UserMapper.getUser", list.get(i).getBuyer().getUserId()));
				list.get(i).setPurchaseProd((Product)sqlSession.selectOne("ProductMapper.getProduct", list.get(i).getPurchaseProd().getProdNo()));
			}
			
			map.put("totalCount", sqlSession.selectOne("PurchaseMapper.getTotalCount2", map));
			map.put("list", list);

		return map;
	}

	@Override
	public String makeCurrentPageSql(String sql, Search search) {
		// TODO Auto-generated method stub
		return null;
	}

}
