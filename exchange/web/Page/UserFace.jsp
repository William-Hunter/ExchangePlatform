<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="bean.User"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path = request.getContextPath();%>
<html>
<head>
<title>我的空间</title>
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
                                src="E:\workspace\ExchangePlatform\Picture\Logo.jpg" alt="logo fail"></a>
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

		<br> <br> <br> <br> <br> <br> <br>
		<br>
		<p>
			现在时间：
			<s:bean id="now" name="java.util.Date" />
			<s:date name="#attr.now" format="yyyy/MM/dd hh:mm:ss" />
		</p>
		<br> <br> <br> <br> <br> <br> <br>
		<br>


		<form action="SearchItem" method="post">
			<div class="input-group col-md-7 container">
				<input type="text" name="key" class="form-control"
					placeholder="输入你喜欢的东西"> <span class="input-group-btn">
					<button class="btn btn-default" type="submit">搜索</button>
				</span>
			</div>

		</form>


		<!--具体内容结束-->

	</div>

</body>
</html>


