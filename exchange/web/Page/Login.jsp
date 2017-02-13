<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path = request.getContextPath();%>  
<html>
<head>
        <title>登录</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="<%=path %>/CSS/bootstrap.css" rel="stylesheet">
        <script src="<%=path %>/JS/jquery.js"></script>
        <script src="<%=path %>/JS/bootstrap.js"></script>
</head>

<body>
        <div class="container">
                
                        <br/>
                        <br/>
                        <br/>
                
                <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-8 col-center-block">
                                <h3>登录</h3>
                                <form action="Login" method="post">
                                        <div class="form-group">
                                                <label for="Email">电子邮箱:</label>
                                                <input id="Email" name="user.email" class="form-control" type="email" placeholder="请输入有效的邮箱格式">
                                                <s:fielderror fieldName="user.email" />
                                                </div>
                                        <div class="form-group">
                                                <label for="Password">密码:</label>
                                                <input id="Password" name="user.password" class="form-control" type="password" placeholder="请输入密码">
                                                <s:fielderror fieldName="user.password" />
                                                <a href="RecoverPassword.jsp">
                                                        <p>忘记密码</p>
                                                </a>
                                        </div>
                                       
                                        <div class="col-md-12 col-center-block">
                                                <button type="submit" class="btn btn-default">登录</button>
                                                <a href="Register.jsp">没有账户，立刻注册</a>
                                        </div>
                                </form>
                        </div>
                </div>
        </div>
</body>
</html>





