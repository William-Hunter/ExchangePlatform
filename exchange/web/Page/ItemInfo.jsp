<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>物品详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <s:include value="link.jsp"/>
</head>

<body>

<s:include value="nav.jsp"/>
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
                        <s:property value="#request.item.itemName"/>
                    </p>
                </div>
                <div class="row">
                    <p>
                        拥有者： <a href="${pageContext.request.contextPath}/CheckUser?user.ids=
								<s:property value="#request.owner.ids"/>">
                        <s:property value="#request.owner.name"/>
                    </a>
                    </p>
                </div>
                <div class="row">
                    <p>
                        物品介绍：
                        <s:property value="#request.item.description"/>
                    </p>
                </div>
                <div class="row">
                    <p>
                        价格：
                        <s:property value="#request.item.buyPrice"/>
                    </p>
                </div>

            </div>
            <div class="col-md-6 col-sm-6 col-xs-6">
                <img src="${pageContext.request.contextPath}/${sessionScope.img_api}?category=Item&name=<s:property value="#request.item.pictureLink" />" alt="" class="col-md-12 col-sm-12 col-xs-12">
            </div>
        </div>
        <br>
        <div class="row">

            <form action="${pageContext.request.contextPath}/Comment/Add?" method="post">
                <input type="text" value="<s:property value="#session.user.ids"/>" id="sender" style="display: none">
                <input type="text" value="<s:property value="#request.item.owner"/>" id="receiver" style="display: none">
                <input type="text" value="<s:property value="#request.item.ids"/>" id="aim" style="display: none">
                <textarea id="context" class="form-control" rows="3" placeholder="在此处输入您的评论"></textarea>
                <br>
                <br>
                <div class="col-center-block col-md-8 ">
                    <button id="commentBTN" type="button" class="btn btn-default col-md-3 col-md-offset-1">评论
                    </button>
                </div>
            </form>
            <div class="col-md-3"></div>
            <form action="Deal?item.ids=<s:property value="#request.item.ids"/>&newOwner=<s:property value="#session.user.email"/>" method="post">
                <button type="submit" class="btn btn-default col-md-3 btn-success">购买</button>
            </form>

        </div>
        <br> <br>
        <div class="row">
            <h3>查看消息</h3>
        </div>

        <br> <br>
        <s:iterator value="#request.commentlist" var="comment">
            <div class="row">
                <div class="col-md-11 col-sm-11 col-center-block">
                    <div class="row btn-default">
                        <p>
                            <a href="/CheckUser?checkUser.email=<s:property value="#comment.sender"/>">
                                <s:property value="#comment.sender"/>
                            </a> 评论：
                            <s:property value="#comment.context"/>
                        </p>
                        <p>
                            <a href="${pageContext.request.contextPath}
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
    </div>

<script type="text/javascript">

$('#commentBTN').click(function () {
    var sender=$('#sender').val();
    var receiver=$('#receiver').val();
    var aim=$('#aim').val();
    var context=$('#context').val();
    $.ajax({
        type: 'POST',
        dataType: 'json',
        url: '${pageContext.request.contextPath}/Comment/Add',
        data: {
            sender : sender,
            receiver:receiver,
            aim:aim,
            context:context
        },
        success: function(result) {
            if(result.code==200){
                alert("评论成功");
            }else{
                alert("评论失败，系统错误");
            }
        }
    });
});

</script>
</body>
</html>