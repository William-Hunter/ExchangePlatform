<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,bean.Item"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
	<title>我的物品</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="<%=path%>/CSS/bootstrap.css" rel="stylesheet"></head>
	<script src="<%=path%>/JS/jquery.js"></script>
	<script src="<%=path%>/JS/bootstrap.js"></script>
<body>

	<nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
                <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed"
                                data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false"
                                aria-controls="navbar">
                                <span class="sr-only">Toggle navigation</span> <span
                                        class="icon-bar"></span> <span class="icon-bar"></span> <span
                                        class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="<%=path%>/Page/UserFace.jsp"> <img
                                src="<%=path%>/Picture/Logo.jpg" alt="logo fail"></a>
                </div>
                <div id="navbar-collapse" class="collapse navbar-collapse">
                        <ul class="nav navbar-nav navbar-right">

                                <li><a href="<%=path%>/MyItem">
                                                <p>我的物品</p>
                                </a></li>

                                <li><a href="<%=path%>/BuyRecord">
                                                <p>购买记录</p>
                                </a></li>
                                <li><a href="<%=path%>/Page/ChangePassword.jsp">
                                                <p>修改密码</p>
                                </a></li>
                                <li><a href="<%=path%>/Logout">
                                                <p>退出登录</p>
                                </a></li>
                        </ul>
                </div>
                <!--/.nav-collapse -->
        </div>
        </nav>

	<div class="row-fluid container">

		<br>
		<br>
		<br>
		<br>
		<br>

		<div class="col-md-3">
			<div class="col-md-12 item-back">
				<a href="<%=path%>
					/Page/AddItem.jsp">
					<img
					src="<%=path%>/Picture/Item/more.jpg" alt="addmore"></a>

			</div>
		</div>

		<s:iterator value="#session.itemlist" id="item">

			<div class="col-md-3">
				<div class="row col-md-12 item-back">
					<a
						href="ItemInfo?item.itemId=<s:property value="#item.itemId" />
					">
					<img
						src="<%=path%>
					/Picture/Item/
					<s:property value="#item.owner" />
					/
					<s:property value="#item.pictureLink" />
					"
						alt="itemPicture">
				</a>
				<div class="row">
					<a href="EditItem?item.ids=<s:property value="#item.itemId"/>" class="col-md-6 edit">
						编辑
					</a>
					<a href="RemoveItem?item.ids=<s:property value="#item.itemId"/>
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