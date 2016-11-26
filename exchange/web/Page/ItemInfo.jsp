<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>物品详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<%=path%>
	/CSS/bootstrap.css" rel="stylesheet">
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

	<div class="row container col-center-block">
		<br> <br> <br> <br>
		<h3>物品详情</h3>
		<br> <br>

		<div class="col-lg-7 col-md-8 col-sm-8 col-center-block">
			<div class="row">
				<div class="col-md-6 col-sm-6 col-xs-6">
					<div class="row">
						<p>
							物品名：
							<s:property value="#session.item.itemName" />
						</p>
					</div>
					<div class="row">
						<p>
							拥有者： <a
								href="<%=path%>
								/CheckUser?checkUser.email=
								<s:property value="#session.item.owner"/>
								">
								<s:property value="#session.item.owner" />
							</a>
						</p>
					</div>
					<div class="row">
						<p>
							物品介绍：
							<s:property value="#session.item.description" />
						</p>
					</div>
					<div class="row">
						<p>
							价格：
							<s:property value="#session.item.buyPrice" />
						</p>
					</div>

				</div>
				<div class="col-md-6 col-sm-6 col-xs-6">
					<img
						src="<%=path%>
					/Picture/Item/
					<s:property value="#session.item.owner" />
					/
					<s:property value="#session.item.pictureLink" />
					"
						alt="" class="col-md-12 col-sm-12 col-xs-12">
				</div>
			</div>
			<br>
			<div class="row">

				<form
					action="AddComment?comment.sender=<s:property value="#session.user.email"/>
				&comment.receiver=
				<s:property value="#session.item.owner"/>
				&comment.aim=
				<s:property value="#session.item.itemId"/>
				"
					method="post">
					<textarea name="comment.context" class="form-control" rows="3"
						placeholder="在此处输入您的评论"></textarea>
					<br>
					<br>

					<div class="col-center-block col-md-8 ">
						<button type="submit"
							class="btn btn-default col-md-3 col-md-offset-1">评论</button>
				</form>
				<div class="col-md-3"></div>
				<form
					action="Deal?item.itemId=<s:property value="#session.item.itemId"/>
				&newOwner=
				<s:property value="#session.user.email"/>
				"
					method="post">
					<button type="submit" class="btn btn-default col-md-3 btn-success">购买</button>
				</form>

			</div>
			<br> <br>
			<div class="row">
				<h3>查看消息</h3>
			</div>

			<br> <br>
			<s:iterator value="#session.commentlist" var="comment">
				<div class="row">
					<div class="col-md-11 col-sm-11 col-center-block">
						<div class="row btn-default">
							<p>
								<a
									href="/CheckUser?checkUser.email=<s:property value="#comment.sender"/>
							">
									<s:property value="#comment.sender" />
								</a> 评论：
								<s:property value="#comment.context" />
							</p>
							<p>
								<a
									href="<%=path %>
							/DeleteComment?comment.commentId=
							<s:property value="#comment.commentId"/>
							">删除
								</a>
							</p>
						</div>
						<br>
					</div>
				</div>
			</s:iterator>



		</div>
</body>
</html>