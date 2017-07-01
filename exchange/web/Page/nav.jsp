<%--
  Created by IntelliJ IDEA.
  User: william
  Date: 2017/7/1
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false"
                    aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/Page/UserFace.jsp"> <img
                    src="${pageContext.request.contextPath}\Picture\Logo.jpg" alt="logo fail"></a>
        </div>
        <div id="navbar-collapse" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">

                <li><a href="${pageContext.request.contextPath}/Page/MyItem.jsp">
                    <p>我的物品</p>
                </a></li>

                <li><a href="${pageContext.request.contextPath}/Deal/Record">
                    <p>购买记录</p>
                </a></li>
                <li><a href="${pageContext.request.contextPath}/Page/ChangePassword.jsp">
                    <p>修改密码</p>
                </a></li>
                <li><a href="${pageContext.request.contextPath}/Logout">
                    <p>退出登录</p>
                </a></li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>
