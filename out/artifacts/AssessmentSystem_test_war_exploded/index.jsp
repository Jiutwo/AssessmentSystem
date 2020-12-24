<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页面</title>
    <script src="statics/JavaScript/vue.js"></script>
    <script src="statics/JavaScript/axios.min.js"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>

    <base href="<%=basePath%>">
    <link rel="stylesheet" href="statics/PageStyle/index_style.css">
</head>
<body>
<div class="header">
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
    </el-menu>
    <div class="line"></div>
    <el-menu
            :default-active="activeIndex2"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
        <div class="el-icon-loading"></div>
        <el-menu-item index="1">首页</el-menu-item>
        <el-menu-item index="3" :class="{disable:(${assessmentSession.username==null})}" @click="GoInputTests">发布题目</el-menu-item>
        <el-menu-item index="4"><a href="/statics/page/schoolTest.jsp" target="_blank" :class="{disable:(${assessmentSession.username==null}&&${userSession.username==null})}"
        >组内任务</a></el-menu-item>
        <div class="mr">
            <input class="search" type="search" name="search" value="请输入搜索内容"/>
            <el-button type="primary"
                       class="button"   icon="el-icon-search"></el-button>
        </div>
        <div class="mc" style="">
                    <span class=" el-icon-user" >
                    </span>
            <span class="getIn"><a href="/login/toLogin">登录<span class="he">|</span>注册</a></span>
        </div>
    </el-menu>
    <div class="alreadyLogin" :class="{disable:(${assessmentSession.username==null}&&${userSession.username==null})}"
    >
        <div class="userImg" @mouseenter="EnterChangeSkip" @mouseleave="LeaveChangeSkip"></div>
        <span class="selfSpace"><a href="/statics/page/selfSpace.jsp">个人中心</a></span>
    </div>
    <div class="d-userImg" :class="{disable:skipShow1 && skipShow2}" @mouseenter="SkipEnter" @mouseleave="SkipLeave"></div>
    <div class="downMenu" :class="{disable:skipShow1 && skipShow2}" @mouseenter="SkipEnter" @mouseleave="SkipLeave">
        <div class="message">
            <div class="username">{{user.username}}</div>
            <div class="school">{{user.school}}</div>
            <div class="profession">{{user.profession}}</div>
            <div class="permission arricon">&#xe614;</div>
        </div>
        <div class="skip" >
            <ul>
                <li><a href="/statics/page/selfSpace.jsp" class="arricon">&#xe713; 个人中心</a></li>
                <li><a href="statics/page/receiveEnvelop.jsp" class="arricon">&#xe628; 收件箱</a></li>
                <li><a href="/getGroupCollectName" class="arricon">&#xe612; 我的收藏</a></li>
                <li><a href="statics/page/history.jsp" class="arricon">&#xe709; 历史记录</a></li>
                <li><a class="arricon" @click="GoLogin">&#xe686; 退出</a></li>
            </ul>
        </div>
    </div>
    <div class="turnImage">
        <template>
            <el-carousel :interval="4000" type="card" height="200px">
                <el-carousel-item v-for="item in img" :key="item">
                    <img :src="item" class="Image">
                </el-carousel-item>
            </el-carousel>
        </template>
    </div>
    <div class="rankingList">
        <div class="outer">
            <h2><span class="t arricon">&#xef54;</span>排行榜</h2>
            <ul class="rank">
<%--                <div class="champion arricon">&#xe6b1;</div>--%>
                <li v-for="(it,index) in rankingList">
                    <div class="order frontPeople" :class="{frontPeople:index < 4}">
                        {{index+1}}
                    </div>
                    <span class="username">
                        {{it.username}}
                    </span>
                    <span class="score">
                        {{it.score}}
                    </span>
                </li>
            </ul>
        </div>
        <div class="mine"><div class="outer1"><div class="order">{{user.ranking}}</div><span class="username">{{user.username}}</span><span class="score">{{user.score}}</span></div></div>
    </div>
    <div class="scoreAssess">
        <div class="space"></div>
        <div class="mission"><em>公开考题</em></div>
        <hr>
        <div class="tests" v-for="(it,index) in tests">
<%--            <div class="imgCon"></div>--%>
            <div class="imgCon arricon">&#xe609;</div>
            <div class="msgCon">
                <h1 class="testName">{{it.testName}}</h1>
                <br>
                开始时间：<span class="startTime">{{it.startTime}}</span>
                截止时间：<span class="endTime">{{it.endTime}}</span>
                总题数：<span class="testNum">{{it.testNum}}</span>
                类别：<span class="spacial">{{it.spacial}}</span>
                发布作者：<span class="author">{{it.author}}</span>
                <!--                <div class="collection arricon"><a href="#">&#xe612;</a> </div>-->
            </div>
            <div class="desCon">
                <button class="cat" @click="GoDoTests(index)">查看详情</button>
            </div>
        </div>
    </div>

</div>
<script>
    let index = new Vue({
        el:".header",
        data:{
            activeIndex: '1',
            activeIndex2: '1',
            img:['statics/image/studyCode.png','statics/image/doIt.png','statics/image/discussion.png','statics/image/time.png','statics/image/happyStudy.png'],
            skipShow1:true,
            skipShow2:true,
            rankingList:[],
            user:{
                username:"无",
                school:"无",
                profession:"无",
                ranking:0,
                score:0,
            },
            tests:[{
                testName:"",
                startTime:"",
                endTime:"",
                testNum:"",
                spacial:"",
                author:"",
            }],
        },
        created:function(){
            this.user.username="${userSession.username}" || "${assessmentSession.username}";
            // alert(this.user.username)
            <%--this.user.school="${userSession.schoolName}"|| "${assessmentSession.schoolName}";--%>
            let that=this;
            axios.get("/getPublicTask").then  // 公开考核任务
            (function (response) {
                // alert(response.data)
                for(let i=0;i<response.data.length;i++){
                    Vue.set(that.tests,i,{
                        tid:response.data[i].tid,
                        testName:response.data[i].title,
                        startTime:response.data[i].createtime,
                        endTime:response.data[i].endtime,
                        testNum:response.data[i].allCount,
                        spacial:"",
                        author:response.data[i].founder,
                    })
                }
            })
            axios.get("/getRankList").then  // 排行榜信息、
                (function (response) {
                    for(let i=0;i<response.data.length;i++){
                        Vue.set(that.rankingList,i,{
                            username:response.data[i].userName,
                            score:response.data[i].integral,
                        })
                    }
                })
            // axios.get("/getMyRank").then  // 个人排行榜信息、
            //     (function (response) {
            //
            //         that.user.score=response.data.integral;
            //     })
            <%--axios.get("/getMyRank").then  // 排行榜信息、--%>
            <%--    (function (response) {--%>
            <%--        that.user.username="${userSession.username}"--%>
            <%--        that.user--%>
            <%--    })--%>
        },
        methods:{
            GoLogin(){
              location.href="/login/remvoeSession"
            },
            GoDoTests(i){
                // alert(this.tests[i].tid)
                if (${assessmentSession.username==null}&&${userSession.username==null}){
                    alert("请先登录");

                }
                if (!(${assessmentSession.username==null}&&${userSession.username==null})) {
                    // alert(this.tests[i].tid)
                    location.href="/queryTopicByTakeId?taskId="+this.tests[i].tid;
                }


            },
            GoInputTests(){
                location.href="statics/page/inputTests.jsp"
            },
            EnterChangeSkip:function (){
                this.skipShow1=false;
            },
            LeaveChangeSkip:function (){
                this.skipShow1=true;
            },
            SkipEnter:function (){
                this.skipShow2=false;
            },
            SkipLeave:function (){
                this.skipShow2=true;
            },
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    })
</script>
</body>
</html>
