<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,bean.Item"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>我的物品</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<s:include value="link.jsp"/>
<body>

<s:include value="nav.jsp"/>
	<div class="row-fluid container">

		<br>
		<br>
		<br>
		<br>
		<br>

		<div class="col-md-3">
			<div class="col-md-12 item-back">
				<a href="${pageContext.request.contextPath}/EditItemInit">
					<img src="${pageContext.request.contextPath}/Picture/Item/more.jpg" alt="addmore"></a>

			</div>
		</div>

		<s:iterator value="#request.itemlist" id="item">

			<div class="col-md-3">
				<div class="row col-md-12 item-back">
					<a
						href="ItemInfo?item.itemId=<s:property value="#item.itemId" />
					">
					<img src="${pageContext.request.contextPath}/${sessionScope.img_api}?category=Item&name=<s:property value="#item.pictureLink" />" alt="itemPicture">
				</a>
				<div class="row">
					<a href="EditItemInit?item.ids=<s:property value="#item.ids"/>" class="col-md-6 edit">
						编辑
					</a>
					<a href="RemoveItem?item.ids=<s:property value="#item.ids"/>
					" class="col-md-6">删除
				</a>
			</div>
		</div>
	</div>
</s:iterator>

<!--循环结束-->
</div>

</body>
</html>