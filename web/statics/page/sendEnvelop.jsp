<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script src="statics/JavaScript/vue.js"></script>
    <script src="/statics/JavaScript/axios.min.js"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <link rel="stylesheet" href="statics/PageStyle/sendEnvelop_style.css">
    <link rel="stylesheet" href="statics/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="statics/bootstrap/css/bootstrap-theme.min.css">
</head>
<body>
<div class="header">
    <div class="logo"></div>
    <ul class="nav">
        <li><a href="/index.jsp">首页</a></li>
        <li><a href="../../statics/page/inputTests.jsp" :class="{disable:(${assessmentSession.username==null})}" @click="GoInputTests">发布题目</a></li>
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
                <li role="presentation" ><a href="envelope/findReEnvelope">我收到的信</a></li>
                <li role="presentation" class="active"><a href="envelope/findSeEnvelope">我发送的信</a></li>
                <li role="presentation"><a href="/statics/page/writeEnvelope.jsp">写信</a></li>
            </ul>
        </div>
        <div class="desEnvelop">
            <!-- Button trigger modal -->
            <div class="envelop" v-for="(it,index) in envelopData" :class="color[index%4]">
               <span class="temple">
                   收件人：<span class="author">{{it.author}}</span>
                   收件时间：<span class="receiveTime">{{it.receiveTime}}</span>
               </span>
                <div class="tet">
                    <button type="button" class="des" data-toggle="modal" data-target="#myModal" @click="ChangeEnvelopId(index)">
                        查看
                    </button>
                    <template class="app1">
                        <el-popconfirm
                                title="确定删除这个信件吗？"
                                @confirm="DeleteEnvelop(index)"
                        >
                            <el-button slot="reference" class="delete">删除</el-button>
                        </el-popconfirm>
                    </template>
                </div>
            </div>
            <!-- Modal -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                        </div>
                        <div class="modal-body" v-text="envelopData[envelopID].value" v-if="envelopData != 0">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    new Vue({
        el:".self",
        data:{
            color:["color1","color2","color3","color4"],
            envelopID:0,
            envelopData:[
                <c:forEach items="${pb}" var="envelope" varStatus="id">
                {
                    author:"${envelope.addresserSno}",
                    receiveTime:"${envelope.date}",
                    eid:"${envelope.eid}",
                    value:"${envelope.content}",
                },
                </c:forEach>
            ],
            <c:if test="${pb != null}">
            envelopCount:${pb.size()},
            </c:if>
            <c:if test="${pb == null}">
            envelopCount:0,
            </c:if>
        },
        methods:{
            DeleteEnvelop(i){

                let that=this;
                axios.get("../envelope/delEnvelope?eid=" + this.envelopData[i].eid).then
                (function (response) {
                    that.envelopData.splice(i,1);
                    that.envelopCount--;
                    if(response.data){
                        that.$message({
                            message: '已删除！',
                            type: 'success'
                        });

                    }else {
                        that.$message.error('错了,删除失败!');
                    }
                })
            },
            ChangeEnvelopId(i){
                this.envelopID=i;
                // let that=this;
                // axios.get("../envelope/findContent?eid=" + this.envelopData[i].eid).then
                // (function (response) {
                //     that.envelopData.value=response.data.envelope;
                // })
            }
        }
    })
</script>
<script src="statics/bootstrap/js/jquery-3.3.1.min.js"></script>
<script src="statics/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>