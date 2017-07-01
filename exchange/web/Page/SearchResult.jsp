<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<html>
<head>
<title>搜索结果</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<s:include value="link.jsp"/>
</head>

<body>

<s:include value="nav.jsp"/>
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
						<img src="${pageContext.request.contextPath}/${sessionScope.img_api}?category=Item&name=<s:property value="#item.pictureLink"/>"
						alt="itemPicture">
					</a>
				</div>
			</div>
		</s:iterator>



		<!--具体内容结束-->

	</div>

</body>
</html>