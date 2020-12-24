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
    <link rel="stylesheet" href="/statics/PageStyle/discussion_style.css">
</head>
<body>
<div class="decorate"><div><a href="/index.jsp">< 退出题解</a></div></div></div>
<div class="rad">
    <template>
        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="题目" name="first"></el-tab-pane>
            <el-tab-pane label="评论" name="second"></el-tab-pane>
            <el-tab-pane label="题解" name="third"></el-tab-pane>
        </el-tabs>
    </template>
    <form>
    <div class="dis">
        <h2>快来写下你的评论吧！<i class="el-icon-edit-outline"></i></h2>
        <el-input
                type="textarea"
                :rows="8"
                class="discussion"
                placeholder="请输入内容"
                v-model="textarea">
        </el-input>
        <el-button type="primary" @click="submitComment">发送</el-button>
    </div>
        <template>
            <div>
                <el-divider>精选评论</el-divider>
            </div>
        </template>
        <div class="discussionArea">
            <div class="discuss" v-for="(it,index) in discuss">
                    <div class="usr">
                            <template>
                            <div class="demo-type">
                                <div>
                                    <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                                </div>
                            </div>
                            </template>
                        <div class="username">{{it.username}}</div>
                    </div>
                    <div class="discussion">
                        <p style="white-space: pre"></p>
                    </div>
                <div class="fnc">
<%--                    <span class="arricon ip">&#xe60c;<span class="likeComments">{{it.likeComments}}</span></span>--%>
<%--                    <el-button class="el-icon-chat-line-round" type="text" @click="drawer = true"></el-button>--%>
<%--                    <span class="discussionNum">{{it.discussionNum}}</span>--%>
                    <el-drawer
                            title="回复"
                            :visible.sync="drawer"
                            :direction="direction"
                            :before-close="handleClose">
                        <el-input
                                type="textarea"
                                :rows="8"
                                class="discussion1"
                                placeholder="请输入内容"
                                v-model="textarea">
                        </el-input>
                    </el-drawer>
                </div>
            </div>
        </div>

    </form>

</div>
<script>
    var app = new Vue({
        el:'.rad',
        data:{
            discuss:[],
            activeName: 'second',
            textarea:'',
            drawer: false,
            direction: 'btt',
        },
        methods: {
            submitComment(){
                axios.get("/addComment?commentContent=" + this.textarea + "&answerKeyId=<%=request.getParameter("answerKeyId")%>" ).then
                (function (response) {
                })
            },
            GetDiscuss(){
                let that = this;
                axios.get("/queryAllCommentByAnswerKeyId?answerKeyId=<%=request.getParameter("answerKeyId")%>").then
                    (function (response) {
                        for(let i=0;i<response.data.length;i++){
                            that.discuss.push({
                                username:response.data.userId,
                                code:response.data.commentContent,
                            })
                        }
                    })
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