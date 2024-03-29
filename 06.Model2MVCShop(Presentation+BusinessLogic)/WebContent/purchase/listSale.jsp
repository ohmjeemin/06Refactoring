<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>판매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function fncGetList(currentPage) {
	document.getElementById("currentPage").value = currentPage;
	document.detailForm.submit();
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listSale.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">판매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>


	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
		<tr>
			
			<c:if test="${!empty search.searchCondition}">
				<td align="right">
				<select name="searchCondition" class="ct_input_g" style="width:80px">
					<c:if test="${search.searchCondition==0}">

						<option value="0" selected>거래번호</option>
						<option value="1">상품명</option>
					</c:if>
					<c:if test="${search.searchCondition==1}">
						<option value="0">거래번호</option>
						<option value="1" selected>상품명</option>
					</c:if>
				</select>
						
				<input 	type="text" name="searchKeyword" 
								class="ct_input_g" style="width:200px; height:19px" >
			</td>
			</c:if>
		
		
		<c:if test="${empty search.searchCondition}">
			<td align="right">
					<select name="searchCondition" class="ct_input_g" style="width:80px">
						<option value="0">거래번호</option>
						<option value="1">상품명</option>
					</select>
					
					<input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" >
				</td>
			</c:if>
			
			
			<td align="right" width="70">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="17" height="23">
							<img src="/images/ct_btnbg01.gif" width="17" height="23">
						</td>
						<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
							<a href="javascript:fncGetList('1');">검색</a>
						</td>
						<td width="14" height="23">
							<img src="/images/ct_btnbg03.gif" width="14" height="23">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
		<tr>
			<td colspan="11" >전체  ${resultPage.totalCount} 건수, 현재  ${resultPage.currentPage} 페이지</td>
		</tr>
		<tr>
			<td class="ct_list_b" width="100">No</td>
			<td class="ct_line02"></td>
			<td class="ct_list_b" width="150">TranNo</td>
			<td class="ct_line02"></td>
			<td class="ct_list_b" width="150">상품명</td>
			<td class="ct_line02"></td>
			<td class="ct_list_b" width="150">가격</td>	
			<td class="ct_line02"></td>
			<td class="ct_list_b" width="150">배송상태</td>
			<td class="ct_line02"></td>	
		</tr>
		<tr>
			<td colspan="11" bgcolor="808285" height="1"></td>
		</tr>

		<c:set var="i" value="0"/>
		<c:forEach var="purchase" items="${list}">		
			<c:set var="i" value="${i+1}"/>
	
		<tr class="ct_list_pop">
			<td align="center">${i}</td>
			<td></td>
	
			<td align="left">
				<a href="/getPurchase.do?tranNo=${purchase.tranNo}"> ${purchase.tranNo} </a> 
			</td>
			<td></td>
		
		
			<td align="left">
				<a href="/getProduct.do?prodNo=${purchase.purchaseProd.prodNo}">${purchase.purchaseProd.prodName}</a> 
			</td>
			<td></td>
			
			<td align="left">${purchase.purchaseProd.price}</td>
			<td></td>
			
			<td align="left">
			
	
								<c:if test = "${purchase.tranCode.trim()=='0'}"> 
									판매중
								</c:if>
								<c:if test = "${purchase.tranCode.trim()=='1'}"> 
									구매완료 <a href="/updateTranCodeByProd.do?prodNo=${purchase.purchaseProd.prodNo}&tranCode=2">배송하기</a>
								</c:if>
								<c:if test = "${purchase.tranCode.trim()=='2'}"> 
									배송중
								</c:if>
								<c:if test = "${purchase.tranCode.trim()=='3'}"> 
									배송완료
								</c:if>
							
							
			</td>
			<td></td>
			
			</tr>
		
		</c:forEach>
			<tr>
			<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>
		 
	</table>
	
<!-- PageNavigation Start... -->
<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
		   
				<%-- page관련 태그들 모듈화해서 jsp파일로 만들었어--%>
				<jsp:include page="../common/pageNavigator.jsp"/>	
    	</td>
	</tr>
</table>
<!-- PageNavigation End... -->
</form>
</div>

</body>
</html>