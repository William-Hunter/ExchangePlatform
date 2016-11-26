<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path = request.getContextPath();%>  
<html>
<head>
        <title>修改密码</title>
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
                                <h3>修改密码</h3>
                                <form action="ChangePassword" method="post">
                                        <div class="form-group">
                                                <label for="OldPassword">旧密码:</label>
                                                <input id="OldPassword" name="user.password" class="form-control" type="password" placeholder="请输入旧密码">
                                                <s:fielderror fieldName="user.password"/>
                                                </div>
                                        <div class="form-group">
                                                <label for="NewPassword">新密码:</label>
                                                <input id="NewPassword" name="newpassword" class="form-control" type="password" placeholder="请输入新密码">
                                                <s:fielderror fieldName="newpassword"/>
                                                </div>
                                       
                                        <div class="col-md-12 col-center-block">
                                                <button type="submit" class="btn btn-default">重置密码</button>
                                                <a href="UserFace.jsp">取消修改，返回我的空间</a>
                                        </div>
                                </form>
                        </div>
                </div>
        </div>
</body>
</html>









