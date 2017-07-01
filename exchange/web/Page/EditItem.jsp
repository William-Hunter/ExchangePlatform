<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>添加物品</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <s:include value="link.jsp"/>
</head>

<body>
<s:include value="nav.jsp"/>

<div class="row container">
    <br> <br> <br> <br>
    <h3>编辑物品</h3>
    <br> <br>
    <form action="SubmitItem" method="post">
        <div class="col-lg-6 col-md-6 col-sm-8 col-center-block">
            <input type="hidden" name="item.ids"
                   <c:if test="${not empty item}">value="<s:property value="#request.item.ids"/>"</c:if>>

            <div class="form-group">
                <label for="itemName">物品名</label>
                <input id="itemName" type="text" name="item.itemName" class="form-control"
                       <c:if test="${not empty item}">value="<s:property value="#request.item.itemName"/>"</c:if>
                       placeholder="请输入物品名">
            </div>
            <div class="form-group">
                <label for="buyPrice">购买时价格</label>
                <input id="buyPrice" type="text" name="item.buyPrice" class="form-control"
                       <c:if test="${not empty item}">value="<s:property value="#request.item.buyPrice"/>"</c:if>
                       placeholder="请输入购买时的价格">
            </div>
            <div class="form-group">
                <label for="description">物品介绍</label>
                <input id="description" type="text" name="item.description" class="form-control"
                       <c:if test="${not empty item}">value="<s:property value="#request.item.description"/>"</c:if>
                       placeholder="请输入物品介绍">
            </div>
            <div class="form-group">
                <c:if test="${not empty item}">
                    <img src="${pageContext.request.contextPath}/${sessionScope.img_api}?category=Item&name=<s:property value="#item.pictureLink" />" alt="itemPicture">
                </c:if>
                <label for="exampleInputFile">上传图片</label>
                <input type="file" name="item.pictureLink" id="exampleInputFile">
            </div>
            <button type="submit" class="btn btn-default col-center-block col-md-3">提交</button>
        </div>
    </form>
</div>

</body>
</html>