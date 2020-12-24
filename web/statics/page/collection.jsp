<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 86186
  Date: 2020/12/1
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>

    <base href="<%=basePath%>">
    <title>Title</title>
    <script src="statics/JavaScript/vue.js"></script>
    <script src="statics/JavaScript/axios.min.js"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="statics/PageStyle/collection_style.css">
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
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
            <li><a href="envelope/findReEnvelope" class="arricon">&#xe628; 我的信箱</a></li>
            <li><a href="/statics/page/collection.jsp" class="arricon">&#xe612; 我的收藏</a></li>
            <li><a href="/statics/page/history.jsp" class="arricon">&#xe709; 历史记录</a></li>
        </ul>
    </div>
    <div class="rightPart">
        <div id="div">
            <div style="margin-bottom: 20px;">
                <el-button
                        type="primary"
                        icon="el-icon-plus"
                        size="small"
                        @click="NewGroupCollectName()"
                >
                </el-button>
            </div>
            <div class="collect">
            <template>
                <el-tabs v-model="GroupCollectNameValue" @tab-click="GetCollect(GroupCollectNameValue)" style="height: 747px;" type="border-card" closable @tab-remove="removeTip">
                    <el-tab-pane :label="it.title" :key="it.name" :name="it.name" v-for="(it,index) in GroupCollectName" >
                        <el-card class="box-card" v-for="(task,index) in collect">
                            <div slot="header" class="clearfix">
                                <span>{{task.title}}</span><br>
                                <span>{{'答题人:'+task.solver}}</span>
                                <el-button style="float: right; padding: 3px 0" type="text">查看</el-button>
                            </div>
                            <div  class="text item">
                                {{'点赞数:' + task.taskData.like }}<br>
                                {{'浏览数:' + task.taskData.browse }}
                            </div>
                        </el-card>
                    </el-tab-pane>
                </el-tabs>
            </template>
            </div>
        </div>
    </div>
</div>

<script>
    let app = new Vue({
        el:"#div",
        data:{
            collect:[],
            NewName:"",
            GroupCollectName:[
                <%
                    List<String> list = (List<String>) request.getAttribute("GroupCollectName");
                    int i=1;
                    System.out.println(list);
                    for(String groupName:list){

                %>
                {
                    title: '<%=groupName%>',
                    name: '<%=i++%>',
                },
                <%
                    }
                %>
            ],
            tabIndex:<%=i-1%>,
            GroupCollectNameValue:'1',
        },
        method(){
            this.GetCollect(this.GroupCollectNameValue);
        },
        created:function(){
            this.GetCollect(this.GroupCollectNameValue);
        },
        methods:{
            addCollect(groupName){
                let that=this;
                axios.get("http://localhost:8080/addCollectGroup?groupName="+groupName).then
                (function (response){
                })
            },
            GetCollect(GroupCollectNameValue){
                // alert(GroupCollectNameValue)
                let groupName=this.GroupCollectName[parseInt(GroupCollectNameValue)-1].title;
                let that = this;
                axios.get("http://localhost:8080/getCollectByGroupName?groupName="+groupName).then
                (function (response) {
                    // alert(response)
                    for (let i = 0; i < response.data.length; i++) {
                        Vue.set(that.collect,i,{
                            title:response.data[i].answerKeyId,
                            solver:response.data[i].userId,
                            taskData:{
                                like:response.data[i].like,
                                browse:response.data[i].browse,
                            }
                        })
                    }

                }, function (err) {
                })
            },
            removeTip(GroupCollectNameValue){
                let Name;
                this.GroupCollectName.forEach((tab,index) => {
                    if(tab.name === GroupCollectNameValue){
                        Name = tab.title;
                    }
                })
                this.$confirm('将永久删除'+Name+'收藏夹包括里面的收藏哦！, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.removeTab(GroupCollectNameValue)
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            removeTab(targetName){
                let tabs = this.GroupCollectName;
                let activeName = this.GroupCollectNameValue;
                if (activeName === targetName) {
                    tabs.forEach((tab, index) => {
                        if (tab.name === targetName) {
                            let nextTab = tabs[index + 1] || tabs[index - 1];
                            if (nextTab) {
                                activeName = nextTab.name;
                            }
                        }
                    });
                }
                let Name;
                this.GroupCollectName.forEach((tab,index) => {
                    if(tab.name === targetName){
                        Name = tab.title;
                    }
                })
                let that=this;
                axios.get("http://localhost:8080/deleteCollectGroup?groupName="+Name).then
                (function (response){
                })
                this.GroupCollectNameValue = activeName;
                this.GroupCollectName = tabs.filter(tab => tab.name !== targetName);
            },
            addTab(targetName){
                let newTabName = ++this.tabIndex + '';
                this.GroupCollectName.push({
                    title: this.NewName,
                    name: newTabName,
                    content: '空空如也'
                });
                this.GroupCollectNameValue = newTabName;
            },
            NewGroupCollectName(){
                this.$prompt('请输入新收藏夹名字', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                }).then(({ value }) => {
                    this.NewName=value
                    this.addTab(this.GroupCollectNameValue)
                    this.addCollect(value)
                    this.$message({
                        type: 'success',
                        message: '新建' + value + '成功'
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
