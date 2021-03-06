<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>重置密码</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<s:include value="link.jsp"/>
</head>

<body>
	<div class="container">

		<br /> <br /> <br />

		<div class="row">
			<div class="col-lg-6 col-md-6 col-sm-8 col-center-block">
				<h3>重置密码</h3>
				<form action="RecoverPassword" method="post">
					<div class="form-group">
						<label for="Name">昵称:</label> 
						<input id="Name" name="user.name"
							class="form-control" type="text" placeholder="请输入你的昵称">
						<s:fielderror fieldName="user.name" />
					</div>
					<div class="form-group">
						<label for="Email">注册时使用的电子邮箱:</label> 
						<input id="Email"
							name="user.email" class="form-control" type="email"
							placeholder="请输入注册时使用的电子邮箱">
						<s:fielderror fieldName="user.email" />
					</div>
					<div class="form-group">
						<label for="Password">新密码:</label> 
						<input id="Password"
							name="newpassword" class="form-control" type="password"
							placeholder="请输入密码">
						<s:fielderror fieldName="newpassword" />
					</div>
					
					<div class="col-md-12 col-center-block">
						<button type="submit" class="btn btn-default">重置密码</button>
						<a href="Login.jsp">取消重置，返回登录</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>









