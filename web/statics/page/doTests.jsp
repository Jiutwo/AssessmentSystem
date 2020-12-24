<%@ page import="java.util.List" %>
<%@ page import="cn.jiutwo.pojo.*" %><%--
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
        <div class="test-title">单纯形法</div>
        <hr>
        <div class="basicMessage">
            总题数：<span class="testNum">{{tests.testName}}</span>
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
                        <input type="radio" class="oneSel" :name="index" v-model="it.userAnswer" value="A">A、 {{it.optiona}}
                        <br>
                        <input type="radio" class="oneSel" :name="index" v-model="it.userAnswer" value="B">B、 {{it.optionb}}
                        <br>
                        <input type="radio" class="oneSel" :name="index" v-model="it.userAnswer" value="C">C、 {{it.optionc}}
                        <br>
                        <input type="radio" class="oneSel" :name="index" v-model="it.userAnswer" value="D">D、 {{it.optiond}}
                        <br>
                    </div>
                    <div class="answer" v-for="(it,index) in MultipleTopic1">
                        <div class="testKind">多选题</div>
                        <p class="testDetails" >
                            <span class="tno">{{index+1}}、</span>
                            {{it.content}}
                        </p>
                        <input type="checkbox" class="multiSel" name="multiSel" v-model="it.userAnswer" value="A">A、 {{it.optiona}}
                        <br>
                        <input type="checkbox" class="multiSel" name="multiSel" v-model="it.userAnswer" value="B">B、 {{it.optionb}}
                        <br>
                        <input type="checkbox" class="multiSel" name="multiSel" v-model="it.userAnswer" value="C">C、 {{it.optionc}}
                        <br>
                        <input type="checkbox" class="multiSel" name="multiSel" v-model="it.userAnswer" value="D">D、 {{it.optiond}}
                        <br>
                    </div>
                    <div class="answer" v-for="(it,index) in JudgeTopic">
                        <div class="testKind">判断题</div>
                        <p class="testDetails" >
                            <span class="tno">{{index+1}}、</span>
                            {{it.content}}
                        </p>
                        <input type="radio" class="judgeSel" name="judgeSel" v-model="it.userAnswer" value="1">对
                        <br>
                        <input type="radio" class="judgeSel" name="judgeSel" v-model="it.userAnswer" value="0">错
                        <br>
                    </div>
                    <div class="answer" v-for="(it,index) in BlankTopic0">
                        <div class="testKind">填空题</div>
                        <p class="testDetails" >
                            <span class="tno">{{index+1}}、</span>
                            {{it.content}}
                        </p>
                        <div class="putForm"><span class="file arricon">&#xe607;上传附件</span><span class="photo arricon">&#xe70a;上传图片</span></div>
                        <textarea class="description" name="description" v-model="it.userAnswer"></textarea>
                    </div>
                    <div class="answer" v-for="(it,index) in BlankTopic1">
                        <div class="testKind">简答题</div>
                        <p class="testDetails" >
                            <span class="tno">{{index+1}}、</span>
                            {{it.content}}
                        </p>
                        <div class="putForm"><span class="file arricon">&#xe607;上传附件</span><span class="photo arricon">&#xe70a;上传图片</span></div>
                        <textarea class="description" name="description"  v-model="it.userAnswer"></textarea>
                    </div>
                    <div class="answer" v-for="(it,index) in BlankTopic1">
                        <div class="testKind">代码题</div>
                        <p class="testDetails" >
                            <span class="tno">{{index+1}}、</span>
                            {{it.content}}
                        </p>
                        <div class="putForm"><span class="file arricon">&#xe607;上传附件</span><span class="photo arricon">&#xe70a;上传图片</span></div>
                        <textarea class="description" name="description"  v-model="it.userAnswer"></textarea>
                    </div>
                    <button type="submit" class="submit" @click="submit">提交</button>
                    <button class="quit">退出</button>
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
                    List<MultipleTopic> list1 = (List<MultipleTopic>) request.getAttribute("MultipleTopic0");
                    for(MultipleTopic MultipleTopic:list1){

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
                    userAnswer:"",
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
                    userAnswer:[],
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
                    userAnswer:"",
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
                    userAnswer:"",
                },
                <%
                    }
                %>
            ],
            BlankTopic1:[
                <%
                    List<BlankTopic> list5 = (List<BlankTopic>) request.getAttribute("BlankTopic1");
                    for(BlankTopic BlankTopic:list5){
                %>
                {
                    type:1,
                    content:"<%=BlankTopic.getContent()%>",
                    answer:"<%=BlankTopic.getAnswer()%>", // 答案
                    parse:"<%=BlankTopic.getParse()%>",
                    userAnswer:"",
                },
                <%
                    }
                %>
            ],
            userAnswer:[]
        },
        methods:{
            submit(){
                for (let i = 0; i < this.MultipleTopic0.length; i++) {
                    this.userAnswer.push(this.MultipleTopic0[i].userAnswer)
                }
                for (let i = 0; i < this.MultipleTopic1.length; i++) {
                    let MultipleTopic1Answer="";
                    for (let j = 0; j < this.MultipleTopic1[i].userAnswer.length; j++) {
                        MultipleTopic1Answer+=this.MultipleTopic1[i].userAnswer[j];
                    }
                    this.userAnswer.push(MultipleTopic1Answer)
                }
                for (let i = 0; i < this.JudgeTopic.length; i++) {
                    this.userAnswer.push(this.JudgeTopic[i].userAnswer)
                }
                for (let i = 0; i < this.BlankTopic0.length; i++) {
                    this.userAnswer.push(this.BlankTopic0[i].userAnswer)
                }
                for (let i = 0; i < this.BlankTopic1.length; i++) {
                    this.userAnswer.push(this.BlankTopic1[i].userAnswer)
                }
                <%--axios.get("/addRecord?userAnswer=" + this.userAnswer,{--%>
                <%--    params:{--%>
                <%--        taskId: "<%=taskTrans.getTid()%>",--%>
                <%--        // userAnswer:this.userAnswer--%>
                <%--    }--%>
                <%--})--%>
                location.href="/addRecord?userAnswer=" + this.userAnswer + "&taskId=<%=taskTrans.getTid()%>"
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