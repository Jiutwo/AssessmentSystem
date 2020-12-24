<%--
  Created by IntelliJ IDEA.
  User: 86186
  Date: 2020/12/13
  Time: 8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.jiutwo.pojo.*" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>题目内容</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>

    <base href="<%=basePath%>">
    <link rel="stylesheet" href="statics/PageStyle/doTests_style.css">
    <script src="/statics/JavaScript/vue.js"></script>
    <script src="/statics/JavaScript/axios.min.js"></script>
</head>
<body>
<div class="decorate"><div><a href="/index.jsp">< 退出题目</a></div></div></div>
<section id="section">
    <div class="test-title">{{tests.testName}}</div>
    <hr>
    <div class="basicMessage">
        总题数：<span class="testNum">{{tests.testNum}}</span>
        发布作者：<span class="author">{{tests.author}}</span>
        <br>
        截止时间：<span class="endTime">{{tests.endTime}}</span>
        共<span class="testCount">{{tests.testNum}}</span>道<span class="testKind">单选题</span>
    </div>
    <hr>
    <div class="answerArea">
        <form action="javascript:;" method="post">
            <div class="answers">
                <div class="answer" v-for="(it,index) in MultipleTopic0">
                    <div class="testKind">单选题</div>
                    <p class="testDetails" >
                        <span class="tno">{{index+1}}、</span>
                        {{it.content}}
                    </p>
                    <input type="radio" class="oneSel" :name="index" v-model="it.userAnswer" :value="it.optiona" style="pointer-events: none">A、 {{it.optiona}}
                    <br>
                    <input type="radio" class="oneSel" :name="index" v-model="it.userAnswer" :value="it.optionb" style="pointer-events: none">B、 {{it.optionb}}
                    <br>
                    <input type="radio" class="oneSel" :name="index" v-model="it.userAnswer" :value="it.optionc" style="pointer-events: none">C、 {{it.optionc}}
                    <br>
                    <input type="radio" class="oneSel" :name="index" v-model="it.userAnswer" :value="it.optiond" style="pointer-events: none">D、 {{it.optiond}}
                    <br>
                    <span>你的答案：{{it.userAnswer}}</span>
                    <br>
                    <span style="color: forestgreen">正确答案：{{it.answer}}</span>
                    <br>
                    <span>得分：{{it.result}}</span>
                </div>
                <div class="answer" v-for="(it,index) in MultipleTopic1">
                    <div class="testKind">多选题</div>
                    <p class="testDetails" >
                        <span class="tno">{{index+1}}、</span>
                        {{it.content}}
                    </p>
                    <input type="checkbox" class="multiSel" name="multiSel" v-model="it.userAnswer" :value="it.optiona" style="pointer-events: none">A、 {{it.optiona}}
                    <br>
                    <input type="checkbox" class="multiSel" name="multiSel" v-model="it.userAnswer" :value="it.optionb" style="pointer-events: none">B、 {{it.optionb}}
                    <br>
                    <input type="checkbox" class="multiSel" name="multiSel" v-model="it.userAnswer" :value="it.optionc" style="pointer-events: none">C、 {{it.optionc}}
                    <br>
                    <input type="checkbox" class="multiSel" name="multiSel" v-model="it.userAnswer" :value="it.optiond" style="pointer-events: none">D、 {{it.optiond}}
                    <br>
                    <span>你的答案：{{it.userAnswer}}</span>
                    <br>
                    <span style="color: forestgreen">正确答案：{{it.answer}}</span>
                    <br>
                    <span>得分：{{it.result}}</span>
                </div>
                <div class="answer" v-for="(it,index) in JudgeTopic">
                    <div class="testKind">判断题</div>
                    <p class="testDetails" >
                        <span class="tno">{{index+1}}、</span>
                        {{it.content}}
                    </p>
                    <input type="radio" class="judgeSel" name="judgeSel" v-model="it.userAnswer" value="1" style="pointer-events: none">对
                    <br>
                    <input type="radio" class="judgeSel" name="judgeSel" v-model="it.userAnswer" value="0" style="pointer-events: none">错
                    <br>
                    <span>你的答案：{{it.userAnswer}}</span>
                    <br>
                    <span style="color: forestgreen">正确答案：{{it.answer}}</span>
                    <br>
                    <span>得分：{{it.result}}</span>
                </div>
                <div class="answer" v-for="(it,index) in BlankTopic0">
                    <div class="testKind">填空题</div>
                    <p class="testDetails" >
                        <span class="tno">{{index+1}}、</span>
                        {{it.content}}
                    </p>
                    <div class="putForm"><span class="file arricon">&#xe607;上传附件</span><span class="photo arricon">&#xe70a;上传图片</span></div>
                    <textarea class="description" name="description" v-model="it.userAnswer" style="pointer-events: none"></textarea>
                    <br>
                    <span>你的答案：{{it.userAnswer}}</span>
                    <br>
                    <span style="color: forestgreen">正确答案：{{it.answer}}</span>
                    <br>
                    <span>得分：暂未评分</span>
                </div>
                <div class="answer" v-for="(it,index) in BlankTopic1">
                    <div class="testKind">简答题</div>
                    <p class="testDetails" >
                        <span class="tno">{{index+1}}、</span>
                        {{it.content}}
                    </p>
                    <div class="putForm"><span class="file arricon">&#xe607;上传附件</span><span class="photo arricon">&#xe70a;上传图片</span></div>
                    <textarea class="description" name="description"  v-model="it.userAnswer" style="pointer-events: none"></textarea>
                    <br>
                    <span>你的答案：{{it.userAnswer}}</span>
                    <br>
                    <span style="color: forestgreen">正确答案：{{it.answer}}</span>
                    <br>
                    <span>得分：暂未评分</span>
                    <button class="submit" @click="GoOtherAnswer(index)">查看其他人题解</button>
                </div>
            </div>
        </form>
    </div>
</section>

<script>
    <%
        TaskTrans taskTrans = (TaskTrans) request.getAttribute("TaskTrans");
    %>
    new Vue({
        el:"section",
        data:{
            tests:{
                testName:"<%=taskTrans.getTitle()%>",
                startTime:"<%=taskTrans.getStatus()%>",
                endTime:"<%=taskTrans.getEndtime()%>",
                testNum:"<%=taskTrans.getAllCount()%>>",
                author:"<%=taskTrans.getFounder()%>",
            },
            MultipleTopic0:[
                <%
                    int i=0;
                    String[] userAnswer = (String[]) request.getAttribute("myRecord");
                    RecordTrans record = (RecordTrans) request.getAttribute("recordTrans");
                    List<MultipleTopic> list1 = (List<MultipleTopic>) request.getAttribute("MultipleTopic0");
                    for(cn.jiutwo.pojo.MultipleTopic MultipleTopic:list1){

                %>
                {
                    type:0,
                    content:"<%=MultipleTopic.getContent()%>",
                    optiona:'<%=MultipleTopic.getOptiona()%>',
                    optionb:'<%=MultipleTopic.getOptionb()%>',
                    optionc:'<%=MultipleTopic.getOptionc()%>',
                    optiond:'<%=MultipleTopic.getOptiond()%>',
                    answer:"<%=MultipleTopic.getAnswer()%>",
                    parse:"<%=MultipleTopic.getParse()%>",
                    userAnswer:"<%=userAnswer[i]%>",
                    result:"<%=record.getAllScore()[i++]%>"
                },
                <%
                    }
                %>
            ],
            MultipleTopic1:[
                <%
                    List<MultipleTopic> list2 = (List<MultipleTopic>) request.getAttribute("MultipleTopic1");
                    for(MultipleTopic MultipleTopic:list2){
                %>
                {
                    type:1,
                    content:"<%=MultipleTopic.getContent()%>",
                    optiona:'<%=MultipleTopic.getOptiona()%>',
                    optionb:'<%=MultipleTopic.getOptionb()%>',
                    optionc:'<%=MultipleTopic.getOptionc()%>',
                    optiond:'<%=MultipleTopic.getOptiond()%>',
                    answer:"<%=MultipleTopic.getAnswer()%>",
                    parse:"<%=MultipleTopic.getParse()%>",
                    userAnswer:"<%=userAnswer[i]%>",
                    result:"<%=record.getAllScore()[i++]%>"
                },
                <%
                    }
                %>
            ],
            JudgeTopic:[
                <%
                    List<JudgeTopic> list3 = (List<JudgeTopic>) request.getAttribute("JudgeTopic");
                    for(JudgeTopic JudgeTopic:list3){
                %>
                {
                    content:"<%=JudgeTopic.getContent()%>",
                    answer:"<%=JudgeTopic.getAnswer()%>", // 答案
                    parse:"<%=JudgeTopic.getParse()%>",
                    userAnswer:"<%=userAnswer[i]%>",
                    result:"<%=record.getAllScore()[i++]%>"
                },
                <%
                    }
                %>
            ],
            BlankTopic0:[
                <%
                    List<BlankTopic> list4 = (List<BlankTopic>) request.getAttribute("BlankTopic0");
                    for(BlankTopic BlankTopic:list4){
                %>
                {
                    type:0,
                    content:"<%=BlankTopic.getContent()%>",
                    answer:"<%=BlankTopic.getAnswer()%>", // 答案
                    parse:"<%=BlankTopic.getParse()%>",
                    userAnswer:"<%=userAnswer[i]%>",
                    result:"<%=record.getAllScore()[i++]%>"
                },
                <%
                    }
                %>
            ],
            BlankTopic1:[
                <%
                    List<BlankTopic> list5 = (List<BlankTopic>) request.getAttribute("BlankTopic1");
                    int n=0;
                    for(BlankTopic BlankTopic:list5){
                %>
                {
                    type:1,
                    content:"<%=BlankTopic.getContent()%>",
                    answer:"<%=BlankTopic.getAnswer()%>", // 答案
                    parse:"<%=BlankTopic.getParse()%>",
                    userAnswer:"<%=userAnswer[i]%>",
                    result:"<%=record.getAllScore()[i++]%>",
                    topicId:"<%=record.getTopicid()[n++]%>"
                },
                <%
                    }
                %>
            ],
            userAnswer:[]
        },
        methods:{
            GoOtherAnswer(i){
                location.href="/getAllAnswerKey?topicId=" + this.BlankTopic1[i].topicId[i];
            },
            random(){
                let count=10; //随机数的数量
                let arr=new Array; //定义一个arr数组
                //给arr数组赋值
                for (let i=0;i<count;i++){
                    arr[i]=i+1;
                }
                arr.sort(function(){ return 0.5 - Math.random(); });
                for (let i=0;i<count;i++){
                }// TODO 随机打乱题目
            },

        }
    })
</script>
</body>
</html>
