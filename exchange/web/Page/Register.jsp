<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<%String path = request.getContextPath();%>
<html>
<head>
	<title>注册</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="<%=path %>/CSS/bootstrap.css" rel="stylesheet">
	<script src="<%=path %>/JS/jquery.js"></script>
	<script src="<%=path %>/JS/bootstrap.js"></script>
</head>

<body>

	<div class="container">

		<br /> <br /> <br />

		<div class="row">
			<div class="col-lg-6 col-md-6 col-sm-8 col-center-block">

				<h3>注册</h3>
				<form action="Register" method="post">
					<div class="form-group">
						<label for="Email">电子邮箱:</label> <input id="Email"
							name="user.email" class="form-control" type="email"
							placeholder="请输入有效的邮箱格式">
						<s:fielderror fieldName="user.email" />
					</div>

					<div class="form-group">
						<label for="Password">密码:</label> <input id="Password"
							name="user.password" class="form-control" type="password"
							placeholder="数字或字母">
						<s:fielderror fieldName="user.password" />
					</div>

					<div class="form-group">
						<label for="Repassword">密码确认:</label> <input id="Repassword"
							name="repassword" class="form-control" type="password"
							placeholder="数字或字母">
						<s:fielderror fieldName="repassword" />
					</div>

					<div class="form-group">
						<label for="Name">昵称:</label> <input id="Name" name="user.name"
							class="form-control" type="text" placeholder="可以为中英文长度不超过12个字符">
						<s:fielderror fieldName="user.name" />
					</div>

					<div class="col-md-12 col-center-block">
						<button type="submit" class="btn btn-default">提交注册信息</button>
						<a href="Login.jsp">已有账户，立刻登录</a>
					</div>

				</form>
			</div>
		</div>





		<!-- - -->

	</div>


</body>
</html>




