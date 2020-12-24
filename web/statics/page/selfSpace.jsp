<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 86186
  Date: 2020/12/1
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="/statics/JavaScript/vue.js"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <link rel="stylesheet" href="/statics/PageStyle/selfSpace_style.css">
</head>
<body>
    <div class="header">
        <div class="logo"></div>
        <ul class="nav">
            <li><a href="/index.jsp">首页</a></li>
            <li><a href="../../statics/page/inputTests.jsp" :class="{disable:(${assessmentSession.username==null})}" @click="GoInputTests">发布题目</a></li>
            <li><a href="../../statics/page/schoolTest.jsp" >组内任务</a></li>
        </ul>
        <div class="hd_search">
            <input type="search" name="result" id="search">
        </div>
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
                <li><a href="envelope/findReEnvelope" class="arricon">&#xe628; 我的信箱</a></li>
                <li><a href="/getGroupCollectName" class="arricon">&#xe612; 我的收藏</a></li>
                <li><a href="/statics/page/history.jsp" class="arricon">&#xe709; 历史记录</a></li>
            </ul>
        </div>

        <div class="rightPart">
            <div>
            <div class="userImg"></div>
                <div class="adjust">
                    <div>
                    <div class="username">user1234</div>
                    <template>
                    <div class="btn-change btn1" @click="open1">修改</div>
    <!--                <el-button type="text" @click="open">点击打开 Message Box</el-button>-->
                    </template>
                    </div>
                    <div class="schoolClass">学校：<span class="school">江西农业大学</span></div>
                    <template>
                        <div class="btn-change btn2" @click="open3">修改</div>
                        <!--                <el-button type="text" @click="open">点击打开 Message Box</el-button>-->
                    </template>
                </div>
            </div>
            <div class="circleArea1">
                <div class="class">
                    加入的组：
                    <div class="newGroup" v-for="(it,index) in group">
                        <span class="group" >{{it}}</span>
                        <template>
                            <el-popconfirm
                                    confirm-button-text='好的'
                                    cancel-button-text='不用了'
                                    icon="el-icon-info"
                                    icon-color="red"
                                    title="你确定退出这个组吗？"
                            >
                                <el-button @click="delGroup(index)" type="danger" icon="el-icon-delete" slot="reference" circle></el-button>
                            </el-popconfirm>
                        </template>
                    </div>
                    <el-tooltip placement="top">
                        <div slot="content">点击添加新的组！</div>
                    <template>
                        <el-button type="text" icon="el-icon-circle-plus" class="ty" @click="open2"></el-button>
                    </template>
                    </el-tooltip>
                </div>
            </div>
            <div class="circleArea2">
                <div class="rak">
                    <i class="el-icon-data-line"></i>
                    <div class="scoreDiv">答题积分：<span class="score">10000</span></div>
                    <div class="rankDiv">我的排名：<span class="rank">34</span></div>
                </div>
            </div>
        </div>
    </div>

    <script>
        var app = new Vue({
            el: '.self',
            data:{
                group:[],
                user:[{
                    username:"无",
                    school:"无",
                    profession:"无",
                }],
                count:0,
            },
            methods: {
                delGroup(i){
                    this.group.splice(i,1);
                    axios.get("").then
                    (function (response) {
                    })
                },
                open1() {
                    this.$prompt('请输入新用户名', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        // inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
                        // inputErrorMessage: '用户名不能为空'
                    }).then(({ value }) => {
                        this.$message({
                            type: 'success',
                            message: '你的新用户名：'+value
                        });
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '取消输入'
                        });
                    });
                },
                open2(){
                    this.$prompt('请输入邀请码', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        // inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
                        // inputErrorMessage: '用户名不能为空'
                    }).then(({ value }) => {
                        let that=this;
                        axios.get("").then
                        (function (response) {
                            that.group.push();
                        })
                        this.$message({
                            type: 'success',
                            message: '成功加入组：' +value
                        });
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '取消输入'
                        });
                    });
                },
                open3(){
                    this.$prompt('请输入学校id', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        // inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
                        // inputErrorMessage: '用户名不能为空'
                    }).then(({ value }) => {
                        this.$message({
                            type: 'success',
                            message: '成功修改学校为：' +value
                        });
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '取消输入'
                        });
                    });
                }
            }
        })
    </script>

</body>
</html>