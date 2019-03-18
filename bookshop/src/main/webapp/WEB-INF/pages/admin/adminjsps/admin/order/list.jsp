<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>订单列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/jsps/pager/pager.css'/>" />

<link rel="stylesheet" type="text/css"
	href="<c:url value='/adminjsps/admin/css/order/list.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/css.css'/>" />

</head>

<body>
	<p class="pLink">
		<a
			href="<c:url value='/admin/order/listByStatus/1/1'/>">未付款</a>
		| <a
			href="<c:url value='/admin/order/listByStatus/2/1'/>">已付款</a>
		| <a
			href="<c:url value='/admin/order/listByStatus/3/1'/>">已发货</a>
		| <a
			href="<c:url value='/admin/order/listByStatus/4/1'/>">交易成功</a>
		| <a
			href="<c:url value='/admin/order/listByStatus/5/1'/>">已取消</a>
	</p>
	<p style="font-weight: 900; color: red">${msg }</p>
	<div class="divMain">
		<div class="title">
			<div style="margin-top: 7px;">
				<span style="margin-left: 150px; margin-right: 280px;">商品信息</span> <span
					style="margin-left: 40px; margin-right: 100px;">金额</span> <span
					style="margin-left: 50px; margin-right: 53px;">订单状态</span> <span
					style="margin-left: 100px;">操作</span>
			</div>
		</div>
		<br />
		<table align="center" border="0" width="100%" cellpadding="0"
			cellspacing="0">
			<c:forEach items="${pages.list }" var="order">

				<tr class="tt">
					<td width="320px">订单号：<a
						href="<c:url value='/admin/order/detail/${order.oid }'/>">${order.oid }</a></td>
					<td width="200px">下单时间：${order.ordertime }</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr style="padding-top: 10px; padding-bottom: 10px;">
					<td colspan="2"><c:forEach items="${order.orderItemList }"
							var="orderItem">
							<a class="link2"
								href="<c:url value='/admin/book/detail/${orderItem.bid }'/>">
								<img border="0" width="70"
								src="<c:url value='/${orderItem.image_b }'/>" />
							</a>
						</c:forEach></td>
					<td width="115px"><span class="price_t">&yen;${order.total }</span>
					</td>
					<td width="142px"><c:choose>
							<c:when test="${order.status eq 1 }">(等待付款)</c:when>
							<c:when test="${order.status eq 2 }">(准备发货)</c:when>
							<c:when test="${order.status eq 3 }">(等待确认)</c:when>
							<c:when test="${order.status eq 4 }">(交易成功)</c:when>
							<c:when test="${order.status eq 5 }">(已取消)</c:when>
						</c:choose></td>
					<td><a
						href="<c:url value='/admin/order/detail/${order.oid }/show'/>">查看</a><br />
						<c:if test="${order.status eq 2 }">
							已付款 <a
								href="<c:url value='/admin/order/detail/${order.oid }/deliver'/>"><button>发货</button></a>
							<br />
						</c:if></td>
				</tr>
			</c:forEach>


		</table>
		<br />
		<%@include file="/jsps/pager/adminorderpager.jsp"%>
	</div>
</body>
</html>
