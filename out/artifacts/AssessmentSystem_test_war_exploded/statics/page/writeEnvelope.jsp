<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人空间</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/PageStyle/writeEnvelop_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap-theme.min.css">
    <script src="../../statics/JavaScript/vue.js"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
</head>
<body>
<div class="header">
    <div class="logo"></div>
    <ul class="nav">
        <li><a href="/index.jsp">首页</a></li>
        <li><a href="../../statics/page/inputTests.jsp" :class="{disable:(${assessmentSession.username==null})}" >发布题目</a></li>
        <li><a href="../../statics/page/schoolTest.jsp" >组内任务</a></li>
    </ul>
    <div class="alreadyLogin">
        <div class="userImg"></div>
        <span class="selfSpace"><a href="/statics/page/selfSpace.jsp">个人中心</a></span>
        <div class="setting arricon">&#xe654;</div>
    </div>
</div>

<div class="self">

    <div class="leftPart">
        <div class="selfContent">个人中心</div>
        <ul>
            <li><a href="/statics/page/selfSpace.jsp" class="arricon">&#xe713; 个人信息</a></li>
            <li><a href="/statics/page/receiveEnvelop.jsp" class="arricon">&#xe628; 我的信箱</a></li>
            <li><a href="/getGroupCollectName" class="arricon">&#xe612; 我的收藏</a></li>
            <li><a href="/statics/page/history.jsp" class="arricon">&#xe709; 历史记录</a></li>
        </ul>
    </div>

    <div class="rightPart">
        <div class="envelopNav">
            <ul class="nav nav-pills">
                <li role="presentation" ><a href="${pageContext.request.contextPath}/envelope/findReEnvelope">我收到的信</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/envelope/findSeEnvelope">我发送的信</a></li>
                <li role="presentation" class="active"><a href="javascript:void(0)">写信</a></li>
            </ul>
        </div>
        <div class="write">
            <form action="${pageContext.request.contextPath}/envelope/writeEnvelope" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="example">收件人教员码</label>
                <input name="addresseeSno" type="text" class="form-control" id="example" placeholder="教员码">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">邮件内容</label>
                <textarea name="content" class="form-control" id="exampleInputPassword1" placeholder="邮件内容"></textarea>
            </div>
            <div class="form-group">
                <label for="exampleInputFile">上传附件</label>
                <input type="file" id="exampleInputFile" name="uploadFile">
                <p class="help-block">请在这里上传您的文件</p>
            </div>
            <div class="checkbox">
                <label>
                    <input type="checkbox" checked="checked" v-model="check" value="1"> Check me out
                </label>
            </div>
            <button type="submit" class="btn btn-default" >发送</button>
        </form>
        </div>
    </div>
</div>
<script>
    new Vue({
        el:'.self',
        data:{
            check:"",
        },
        methods:{
            submit(){
                if(this.check){
                    this.$message({
                        message: '已发送！',
                        type: 'success'
                    });
                }
            }
        }
    })
</script>
</body>
</html>