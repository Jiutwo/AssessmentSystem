<%@ page import="cn.jiutwo.pojo.*" %><%--
  Created by IntelliJ IDEA.
  User: 86186
  Date: 2020/12/1
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cn.jiutwo.pojo.AnswerKey" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>题解讨论</title>
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
    <link rel="stylesheet" href="/statics/PageStyle/otherAnswer_style.css">
</head>
<body>
<div class="decorate"><div><a href="/index.html">< 退出题解</a></div></div></div>
<div class="rad">
    <template>
        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="题目" name="first"></el-tab-pane>
            <el-tab-pane label="评论" name="second"></el-tab-pane>
            <el-tab-pane label="题解" name="third"></el-tab-pane>
        </el-tabs>
    </template>
    <div class="people" v-for="(it,index) in  AnswerKey" @click="GoDiscussion(index)">
        <div >
            <div class="ms">
                <template>
                    <div class="demo-type">
                        <div>
                            <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                        </div>
                    </div>
                </template>
                <div class="username">{{it.username}}</div>
            </div>
            <div class="tet">
                {{it.content}}
            </div>
            <div class="sie">
                <span class="arricon ip">&#xe60c;<span class="likeComments">{{it.likeComments}}</span></span>
                <el-button type="text" @click="drawer = true;drawerId=index" class="el-icon-view"></el-button>
            </div>
        </div>
    </div>
    <el-drawer
            title="我是标题"
            :visible.sync="drawer"
            :with-header="false">
        <div class="chouTi">
            <div class="people1">
                <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" class="userImg"></el-avatar>
                <div class="username">{{AnswerKey[drawerId].username}}</div>
            </div>
            <div class="ico">
                <span class="arricon ip">&#xe60c;<span class="likeComments">{{AnswerKey[drawerId].likeComments}}</span></span>
            </div>
            <hr>
            <div class="discussion">
                <p style="white-space: pre">{{AnswerKey[drawerId].content}}</p>
            </div>
            <div class="feedBack">
                    <h2>快来写下你的评论吧！<i class="el-icon-edit-outline"></i></h2>
                    <el-input
                            type="textarea"
                            :rows="8"
                            class="feed"
                            placeholder="请输入内容"
                            v-model="textarea">
                    </el-input>
            </div>
        </div>
    </el-drawer>
</div>
<script>
    var app = new Vue({
        el:'.rad',
        data:{
            drawerId:0,
            AnswerKey:[
                <%
                    List<AnswerKey> list = (List<AnswerKey>) request.getAttribute("allAnswerKey");
                    for(AnswerKey allAnswerKey:list){
                %>
                {
                    codeId:"<%=allAnswerKey.getCodeId()%>",
                    answerKeyId:"<%=allAnswerKey.getAid()%>",
                    username:"<%=allAnswerKey.getUserId()%>",
                    content:'<%=allAnswerKey.getContent()%>',
                    likeComments:"<%=allAnswerKey.getLike()%>",
                },
                <%
                    }
                %>
            ],
            activeName: 'third',
            textarea:'',
            drawer: false,
            direction: 'btt',
        },
        <%--content:function (){--%>
        <%--    let that = this;--%>
        <%--    axios.get("/getAllAnswerKey?topicId=" + ${}).then--%>
        <%--        (function (response) {--%>
        <%--            for(let i=0;i<response.data.length;i++){--%>
        <%--                that.discuss.push({--%>
        <%--                    username:response.data.userId,--%>
        <%--                    code:response.data.commentContent,--%>
        <%--                    likeComments:response.data.like,--%>
        <%--                })--%>
        <%--            }--%>
        <%--        })--%>
        <%--},--%>
        methods: {
            GoDiscussion(i){
                location.href="/statics/page/discussion.jsp?answerKeyId=" + this.answerKeyId+ "topicId=" +this.AnswerKey.codeId;
            },
            handleClick(tab, event) {
                console.log(tab, event);
            },
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {});
            }
        }
    });
</script>
</body>
</html>