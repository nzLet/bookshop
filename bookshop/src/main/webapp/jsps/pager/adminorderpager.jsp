<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function _go() {
		var pc = $("#pageCode").val();//获取文本框中的当前页码
		if(!/^[1-9]\d*$/.test(pc)) {//对当前页码进行整数校验
			alert('请输入正确的页码！');
			return;
		}
		if(pc > ${pages.pages}) {//判断当前页码是否大于最大页
			alert('请输入正确的页码！');
			return;
		}
		location = "/bookshop/admin/order/list/" + pc;
	}
</script>
</head>
<body>
<div class="divBody">
	<div class="divContent">
		<a href="/bookshop/admin/order/list/${pages.prePage }" class="aBtn bold">上一页</a>


		<c:forEach items="${pages.navigatepageNums}" var="pn">
			<a href="/bookshop/admin/order/list/${pn}" class="aBtn">${pn }</a>
		</c:forEach>
		<c:if test="${pages.pageNum+2 < pages.pages }">
			<span class="spanApostrophe">...</span>
		</c:if>
		<a href="/bookshop/admin/order/list/${pages.nextPage }" class="aBtn bold">下一页</a>

		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

		<span>共${pages.pages }页</span> <span>到</span> <input type="text"
			class="inputPageCode" id="pageCode" value="${pages.pageNum }" /> <span>页</span>
		<a href="javascript:_go();" class="aSubmit">确定</a>
	</div>
</div>
</body>
</html>