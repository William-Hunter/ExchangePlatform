<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" import="bean.User"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>用户详情</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<s:include value="link.jsp"/>
</head>

<body>
<s:include value="nav.jsp"/>

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="row container col-center-block">

		<h3>用户详情</h3>
		<br>
		<br>
		<br>
		<br>

		<div class="col-md-8 col-sm-8 col-center-block text-center">

			<div class="row">
				<p>昵称：<s:property value="#request.checkuser.name"/></p>
			</div>
			<br>
			<div class="row">
				<p>加入时间：<s:property value="#request.checkuser.joinTime"/></p>
			</div>
			<br>
			<div class="row">
				<p>email：<s:property value="#request.checkuser.email"/></p>
			</div>
			<div class="row">

                        </div>

		</div>

	</div>

</body>
</html>