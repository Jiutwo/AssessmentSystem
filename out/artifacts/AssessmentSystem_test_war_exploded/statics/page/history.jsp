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
    <link rel="stylesheet" href="/statics/PageStyle/history_style.css">
    <link rel="stylesheet" href="/statics/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/statics/bootstrap/css/bootstrap-theme.min.css">
</head>
<body>
<div class="header">
    <div class="logo"></div>
    <ul class="nav">
        <li><a href="/index.jsp">首页</a></li>
        <li><a href="#">关于考试</a></li>
        <li><a href="#">专题训练</a></li>
        <li><a href="#">关于我们</a></li>
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
            <li><a href="/statics/page/receiveEnvelop.jsp" class="arricon">&#xe628; 我的信箱</a></li>
            <li><a href="/statics/page/collection.jsp" class="arricon">&#xe612; 我的收藏</a></li>
            <li><a href="/statics/page/history.jsp" class="arricon">&#xe709; 历史记录</a></li>
        </ul>
    </div>

    <div class="rightPart">
        <div class=" record">
            <div class="envelopContainer">历史记录</div>
            <el-collapse v-model="activeNames">
                <el-collapse-item   :name="index" id="drop" v-for="(it,index) in envelopContainer">
                    <template slot="title">
                        <div class="s1">
                            题目名称：<span class="testName">{{it.testName}}</span> 完成时间：<span class="finishTime">{{it.finishTime}}</span>
                        </div>
                    </template>
                    <div class="down">
                        总题数：<span class="testNum a1">{{it.taskData.testNum}}</span>
                        类别：<span class="spacial a1">{{it.taskData.spacial}}</span>
                        发布作者：<span class="author a1">{{it.taskData.author}}</span>
                    </div>
                    <el-tooltip placement="top">
                        <div slot="content">点击查看题目详情</div>
                        <el-button type="primary" icon="el-icon-edit" circle></el-button>
                    </el-tooltip>
                    <el-tooltip placement="top">
                        <div slot="content">点击给作者写信</div>
                        <el-button type="info" icon="el-icon-message" circle></el-button>
                    </el-tooltip>
                    <el-tooltip placement="top">
                        <div slot="content">点击删除此条记录</div>
                        <el-button @click="DeleteHistory(index)" type="danger" icon="el-icon-delete" circle></el-button>
                    </el-tooltip>
                </el-collapse-item>
            </el-collapse>
        </div>
    </div>
</div>
<script>
    let app = new Vue({
        el: '.self',
        data:{
            activeNames: '1',
            envelopContainer:[{
                testName:"单纯形法",
                finishTime:"2020/12/8 23",
                taskData: {
                    testNum:100,
                    spacial:"算法",
                    author:"徐正元",
                }
            },{
                testName:"矩阵连乘",
                finishTime:"2020/12/8 23",
                taskData: {
                    testNum:100,
                    spacial:"算法",
                    author:"徐正元",
                }
            },{
                testName:"背包问题",
                finishTime:"2020/12/8 23",
                taskData: {
                    testNum:100,
                    spacial:"算法",
                    author:"徐正元",
                }
            }],
        },
        methods: {
            DeleteHistory(index){
                this.envelopContainer.splice(index,1);
            },
            handleChange(val) {
                console.log(val);
            }
        }
    })
</script>
<script src="/statics/bootstrap/js/jquery-3.3.1.min.js"></script>
<script src="/statics/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>