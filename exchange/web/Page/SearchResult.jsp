<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<%String path = request.getContextPath();%>
<html>
<head>
<title>搜索结果</title>
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

	<div class="row-fluid container">

		<!--具体内容开始-->
		<br> <br> <br> <br> <br>
		<form action="SearchItem" method="post">
			<div class="input-group col-md-7 container">
				<input type="text" name="key" class="form-control"
					placeholder="输入你喜欢的东西"> <span class="input-group-btn">
					<button class="btn btn-default" type="submit">搜索</button>
				</span>
			</div>

		</form>
		<s:iterator value="#session.resultlist" id="item">
			<div class="col-md-4">
				<div class="col-md-12 item-back">
					<a href="ItemInfo?item.itemId=<s:property value="#item.itemId"/>">
						<img
						src="<%=path %>/Picture/Item/<s:property value="#item.owner"/>/<s:property value="#item.pictureLink"/>"
						alt="itemPicture">
					</a>
				</div>
			</div>
		</s:iterator>



		<!--具体内容结束-->

	</div>

</body>
</html>