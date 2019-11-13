<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>구매 목록조회</title>

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

<form name="detailForm" action="/listPurchase.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="15">전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">거래번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="200">전화번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="200">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="200">정보수정</td>
		<td class="ct_line02"></td>
	</tr>
	<tr>
		<td colspan="15" bgcolor="808285" height="1"></td>
	</tr>




	<c:set var="i" value="0"/>
		<c:forEach var="purchase" items="${list}">		
			<c:set var="i" value="${i}"/>
	
		<tr class="ct_list_pop">
		
		<td align="center">
			<a href="/getPurchase.do?tranNo=${purchase.tranNo}">${purchase.tranNo}</a>
		</td>
		<td></td>
		
		
		<td align="center">
			<a href="/getProduct.do?prodNo=${purchase.purchaseProd.prodNo}">${purchase.purchaseProd.prodName}</a>
		</td>
		<td></td>
		
		
		<td align="center">
			<a href="/getUser.do?userId=${purchase.buyer.userId}">${purchase.buyer.userId}</a>
		</td>
		<td></td>
		
		
		<td align="center">${user.userName}</td>
		<td></td>
		
		<td align="center">${user.phone}</td>
		<td></td>
		
		
		<td align="center">
			<c:if test = "${!empty purchase.tranCode.trim() && purchase.tranCode.trim()=='1'}"> 
			현재 구매완료 상태입니다
			</c:if>
			<c:if test = "${!empty purchase.tranCode.trim() && purchase.tranCode.trim()=='2'}"> 
			현재 배송중 입니다
			</c:if>
			<c:if test = "${!empty purchase.tranCode.trim() && purchase.tranCode.trim()=='3'}"> 
			현재 배송완료 상태입니다
			</c:if>	
		</td>
		<td></td>
		
		
		<td align="center">
			<c:if test = "${!empty purchase.tranCode.trim() && purchase.tranCode.trim()=='2'}"> 
			<a href="/updateTranCode.do?tranNo=${purchase.tranNo}&tranCode=3">상품 도착 </a>
			</c:if>
		</td>
		<td></td>
	</tr>
	</c:forEach>
	
	
	<tr>
		<td colspan="15" bgcolor="D6D7D6" height="1"></td>
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

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>