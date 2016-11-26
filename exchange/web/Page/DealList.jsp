﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>查看交易记录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<%=path%>/CSS/bootstrap.css" rel="stylesheet">
<script src="<%=path%>/JS/jquery.js"></script>
<script src="<%=path%>/JS/bootstrap.js"></script>
</head>

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
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="row container col-center-block">

		<h3>查看购买请求</h3>
		<br> <br>
		
		<div class="col-md-8 col-sm-8 col-center-block">
		<s:iterator value="#session.deallist" var="record">
				<div class="row btn-default ">
					<p>
						<a
							href="<%=path%>/CheckUser?checkUser.email=<s:property value="#record.sender"/>"><s:property
								value="#record.sender" /></a> 买了 <a
							href="<%=path%>/CheckUser?checkUser.email=<s:property value="#record.receiver"/>"><s:property
								value="#record.receiver" /></a> 的 <a
							href="<%=path%>/ItemInfo?item.itemId=<s:property
                                                                value="#record.itemId"/>"><s:property
								value="#record.itemName" /></a>
					</p>
				</div>
				<br>
		</s:iterator>


	       </div>
	</div>
</body>
</html>