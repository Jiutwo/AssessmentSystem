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
    <title>个人中心</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>

    <base href="<%=basePath%>">
    <link rel="stylesheet" href="/statics/PageStyle/schoolTest_style.css">
    <script src="/statics/JavaScript/vue.js"></script>
    <script src="/statics/JavaScript/axios.min.js"></script>
</head>
<body>
    <div class="decorate"><div><a href="/index.jsp">< 返回首页</a></div></div></div>
    <div class="testAbout">
        <div class="school_test">学校任务</div>
        <div class="testKind1">
            <div class="condition">未完成</div>
            <div class="condition">已完成</div>
        </div>
        <div class="testKind2">
            <div class="condition">未批改</div>
            <div class="condition">已批改</div>
        </div>
    </div>
    <div class="tests">
        <div class="test" v-for="(it,index) in tests">
            <div class="imgCon"></div>
            <div class="msgCon">
                <h1 class="testName">{{it.testName}}</h1>
                <br>
                <div class="class1">开始时间：</div><div class="startTime class2">{{it.startTime}}</div>
                <div class="class1">截止时间：</div><div class="endTime class2">{{it.endTime}}</div>
                 <div class="class1"> 总题数：</div><div class="testNum class2">{{it.testNum}}</div>
                 <div class="class1">类别：</div><div class="spacial class2">{{it.spacial}}</div>
                <div class="class1">发布作者：</div><div class="author class2">{{it.author}}</div>
                <button class="enter" @click="GoDoTests(index)">{{tests[index].completed}}</button>
                <!--                <div class="collection arricon"><a href="#">&#xe612;</a> </div>-->
            </div>
        </div>
    </div>
    <script>
        let index = new Vue({
            el:".tests",
            data:{
                identity:0,
                work:"",
                type:0,
                skipShow1:true,
                skipShow2:true,
                tests:[{
                    completed:"",
                    testName:"算法",
                    startTime:"2020-12-12",
                    endTime:"2020-12-13",
                    testNum:"100",
                    spacial:"算法",
                    author:"徐正元",
                    taskId:""
                }],
            },
            created:function(){
                let size=0;
                let that=this;
                axios.get("/getMyAllTaskByUserId?userId=${assessmentSession.uid}${userSession.uid}&type="+this.type).then  // 学校考核任务
                    (function (response) {
                        size=response.data.length;
                        // alert(size)
                        for(let i=0;i<response.data.length;i++){
                            Vue.set(that.tests,i,{
                                testName:response.data[i].title,
                                startTime:response.data[i].createtime,
                                endTime:response.data[i].endtime,
                                testNum:response.data[i].allCount,
                                spacial:"",
                                author:response.data[i].founder,
                                tid:response.data[i].tid,
                                completed:response.data[i].completed,
                            })
                        }
                    })
                if(${assessmentSession.username==null}){
                    this.work="立即答题"
                    this.identity=0;
                }else{
                    this.work="开始批改";
                    this.identity=1;
                }
            },
            methods:{
                GoDoTests(i){
                    // alert(this.tests[i].tid)
                    if(this.identity === 0){
                        // alert(this.tests[i].tid)
                        location.href="/queryTopicByTakeId?taskId="+this.tests[i].tid
                    }else{

                    }
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
                }
            }
        })
    </script>
</body>
</html>