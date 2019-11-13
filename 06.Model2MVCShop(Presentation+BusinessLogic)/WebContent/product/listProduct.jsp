<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>상품 관리</title>

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

		<form name="detailForm"
			action="/listProduct.do?menu=${param.menu}" method="post">

			<table width="100%" height="37" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
						width="15" height="37"></td>
					<td background="/images/ct_ttl_img02.gif" width="100%"
						style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">
									
									${!empty param.menu && param.menu=='manage' ? '판매 상품 관리':'상품 조회'}
								
								</td>
						<!-- <td width="93%" class="ct_ttl01">상품 검색</td> -->
							</tr>
						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
						width="12" height="37"></td>
				</tr>
			</table>

			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td align="right">
						<select name="searchCondition" class="ct_input_g" style="width:80px">
							<option value="0" ${!empty search.searchCondition && search.searchCondition ==0 ? 'selected' : '' }>상품번호</option>
							<option value="1" ${!empty search.searchCondition && search.searchCondition ==1 ? 'selected' : '' }>상품명</option>
							<option value="2" ${!empty search.searchCondition && search.searchCondition ==2 ? 'selected' : '' }>가격</option>
						</select>
						<input 	type="text" name="searchKeyword" value="${search.searchKeyword}"  class="ct_input_g" 
							style="width:200px; height:20px" 
							
							<%--Javascript 엔터키 --%>
							onkeydown="javascript:if(event.keyCode==13){fncGetList(1);}"/>
						</td>
							<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetList( '1');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

			<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
				<tr>
		<td colspan="11" >전체  ${resultPage.totalCount} 건수,	현재 ${resultPage.currentPage}  페이지
		</td>
	</tr>
				<tr>
					<td class="ct_list_b" width="50">No${product.proTranCode.trim()}</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">상품명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="100">가격</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="100">등록일</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="100">현재상태</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>
				
				<c:set var="i" value="0"/>
					<c:forEach var="product" items="${list}">
						<c:set var="i" value="${i+1}"/>
							<tr class="ct_list_pop">
							<td align="center">${ i }</td>
							<td></td>
							
							
							<td align="center">
								
						<c:if test="${menu.equals('manage')}">
									
							<c:if test = "${product.proTranCode.trim().equals('0')}">
									<a href="/updateProductView.do?prodNo=${product.prodNo}&menu=${menu}">
									${product.prodName}</a></c:if>
							
							<c:if test = "${product.proTranCode.trim().equals('1')}">
								${product.prodName}</c:if>
							<c:if test = "${product.proTranCode.trim().equals('2')}">
								${product.prodName}</c:if>
							<c:if test = "${product.proTranCode.trim().equals('3')}">
								${product.prodName}</c:if>
								
						</c:if>		
											
							<c:if test = "${menu.equals('search')}">							
								<c:if test = "${product.proTranCode.trim().equals('0')}">
									<a href="/getProduct.do?prodNo=${product.prodNo}&menu=${menu}">
									${product.prodName}</a>
								</c:if>
							
							<c:if test = "${product.proTranCode.trim().equals('1')}">
								${product.prodName}</c:if>
							<c:if test = "${product.proTranCode.trim().equals('2')}">
								${product.prodName}</c:if>
							<c:if test = "${product.proTranCode.trim().equals('3')}">
								${product.prodName}</c:if>
								
								
							</c:if>
					
							</td>
							<td></td>
							<td align="center">${product.price}</td>
							<td></td>
							<td align="center">${product.regDate}</td>
							<td></td>
				
							<td align="center">
							
								<c:if test = "${product.proTranCode.trim().equals('0')}">
									판매중</c:if>
								<c:if test="${product.proTranCode.trim().equals('1')}">재고없음 </c:if>
								<c:if test="${product.proTranCode.trim().equals('2')}">재고없음 </c:if>
								<c:if test="${product.proTranCode.trim().equals('3')}">재고없음 </c:if>
								
						
							<!-- ${product.proTranCode} -->
							</td>
						</tr>
						<tr>
							<td colspan="11" bgcolor="D6D7D6" height="1"></td>
						</tr>
				</c:forEach>
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