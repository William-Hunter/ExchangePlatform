<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>查看交易记录</title>
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

    <h3>查看购买请求</h3>
    <br> <br>

    <div class="col-md-8 col-sm-8 col-center-block">
        <s:iterator value="#session.deallist" var="record">
            <div class="row btn-default ">
                <p>
                    <a
                            href="${pageContext.request.contextPath}/CheckUser?checkUser.email=<s:property value="#record.sender"/>"><s:property
                            value="#record.sender"/></a> 买了 <a
                        href="${pageContext.request.contextPath}/CheckUser?checkUser.email=<s:property value="#record.receiver"/>"><s:property
                        value="#record.receiver"/></a> 的 <a
                        href="${pageContext.request.contextPath}/Item/detail?item.ids=<s:property
                                                                value="#record.ids"/>"><s:property
                        value="#record.itemName"/></a>
                </p>
            </div>
            <br>
        </s:iterator>


    </div>
</div>
</body>
</html>