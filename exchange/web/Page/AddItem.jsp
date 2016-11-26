<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>添加物品</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<%=path %>/CSS/bootstrap.css" rel="stylesheet">
<script src="<%=path %>/JS/jquery.js"></script>
<script src="<%=path %>/JS/bootstrap.js"></script>
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

	<div class="row container">
		<br> <br> <br> <br>
		<h3>添加物品</h3>
		<br> <br>
		<form action="AddItem" method="post" enctype="multipart/form-data">
			<div class="col-lg-6 col-md-6 col-sm-8 col-center-block">
				<div class="form-group">
					<label for="InputEmail">物品名</label> <input type="text"
						id="InputEmail" name="item.itemName" class="form-control"
						placeholder="请输入物品名">

				</div>
				<div class="form-group">
					<label for="InputPassword">购买时价格</label> <input type="text"
						id="InputPassword" name="item.buyPrice" class="form-control"
						placeholder="请输入购买时的价格">

				</div>
				<div class="form-group">
					<label for="InputPassword1">物品介绍</label> <input type="text"
						id="InputPassword1" name="item.description" class="form-control"
						placeholder="请输入物品介绍">

				</div>
				<div class="form-group">
					<label for="InputFile">上传图片</label> <input type="file"
						id="InputFile" name="pictureUpload">
				</div>

				<button type="submit"
					class="btn btn-default col-center-block col-md-3">添加物品</button>
			</div>
		</form>
	</div>

</body>
</html>