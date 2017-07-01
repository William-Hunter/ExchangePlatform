<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="bean.User" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>我的空间</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <s:include value="link.jsp"/>

</head>

<body>
<s:include value="nav.jsp"/>
<div class="row-fluid container">

    <br> <br> <br> <br> <br> <br> <br>
    <br>
    <p>
        现在时间：
        <s:bean id="now" name="java.util.Date"/>
        <s:date name="#attr.now" format="yyyy/MM/dd hh:mm:ss"/>
    </p>
    <br> <br> <br> <br> <br> <br> <br>
    <br>


    <form action="Item/Search" method="post">
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


