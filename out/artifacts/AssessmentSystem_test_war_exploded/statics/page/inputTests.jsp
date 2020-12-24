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
    <title>添加题目</title>
    <script src="../JavaScript/vue.js"></script>
    <script src="../JavaScript/axios.min.js"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <link rel="stylesheet" href="../PageStyle/inputTests_style.css">
<%--    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">--%>
<%--    <link rel="stylesheet" href="../bootstrap/css/bootstrap-theme.min.css">--%>
</head>
<body>
<div class="decorate"><div><a href="/index.jsp">< 返回首页</a></div></div></div>
    <div class="rad">
        <template>
                <el-radio v-model="radio1" label="1" border>手动添加</el-radio>
                <el-radio @change="GoAutoInput" v-model="radio1" label="2" border>自动添加</el-radio>
                    <el-tabs type="border-card">
                        <el-tab-pane label="单选题">
                            <form>
                                <div class="mes">题干<span class="bur">*</span></div>
                                <div class="itq">
                                    <div class="question">{{MultipleTopic0Id+1}}、
                                        <el-input
                                                type="textarea"
                                                :rows="2"
                                                placeholder="请输入题干内容"
                                                v-model="MultipleTopic0[MultipleTopic0Id].content">
                                        </el-input>
                                    </div>
                                </div>
                                <div class="mes">选项<span class="bur">*</span></div>
                                <div class="itt">
                                    <div >
                                        <div class="hole">
                                            <div class="alp ">A、</div>
                                            <div class="shorter ">
                                                <el-input
                                                        name="testName"
                                                        placeholder=""
                                                        v-model="MultipleTopic0[MultipleTopic0Id].optiona"
                                                        clearable>
                                                </el-input>
                                            </div>
                                        </div>
                                        <div class="hole">
                                            <div class="alp">B、</div>
                                            <div class="shorter">
                                                <el-input
                                                        name="spacial"
                                                        placeholder=""
                                                        v-model="MultipleTopic0[MultipleTopic0Id].optionb"
                                                        clearable>
                                                </el-input>
                                            </div>
                                        </div>
                                        <div class="hole">
                                            <div class="alp">C、</div>
                                            <div class="shorter">
                                                <el-input
                                                        name="startTime"
                                                        placeholder=""
                                                        v-model="MultipleTopic0[MultipleTopic0Id].optionc"
                                                        clearable>
                                                </el-input>
                                            </div>
                                        </div>
                                        <div class="hole">
                                            <div class="alp">D、</div>
                                            <div class="shorter">
                                                <el-input
                                                        name="endTime"
                                                        placeholder=""
                                                        v-model="MultipleTopic0[MultipleTopic0Id].optiond"
                                                        clearable>
                                                </el-input>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="mes">答案<span class="bur">*</span></div>
                                <div class="out">
                                    <template>
                                        <el-radio-group v-model="MultipleTopic0[MultipleTopic0Id].answer" class="answer">
                                            <el-radio label="A">A</el-radio>
                                            <el-radio label="B">B</el-radio>
                                            <el-radio label="C">C</el-radio>
                                            <el-radio label="D">D</el-radio>
                                        </el-radio-group>
                                    </template>
                                </div>
                                <div class="mes">解析<span class="bur">*</span></div>
                                <div class="lim">
                                    <el-input
                                            type="textarea"
                                            :rows="2"
                                            placeholder="请输入题目解析"
                                            v-model="MultipleTopic0[MultipleTopic0Id].parse">
                                    </el-input>
                                </div>
                            </form>
                            <div class="buttons">
                                <el-button type="primary" plain class="mButton" @click="returnMultipleTopic0">上一题</el-button>
                                <template>
                                    <el-button type="success" @click="open1" class="mButton" @click="upload">完成提交</el-button>
                                </template>
                                <el-button type="primary" plain class="mButton" @click="nextMultipleTopic0">下一题</el-button>
                                <template>
                                    <el-button type="danger" icon="el-icon-delete" @click="open2" circle ></el-button>
                                </template>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="多选题">
                            <form>
                                <div class="mes">题干<span class="bur">*</span></div>
                                <div class="itq">
                                    <div class="question">{{MultipleTopic1Id+1}}
                                        <el-input
                                                type="textarea"
                                                :rows="2"
                                                placeholder="请输入题干内容"
                                                v-model="MultipleTopic1[MultipleTopic1Id].content">
                                        </el-input>
                                    </div>
                                </div>
                                <div class="mes">选项<span class="bur">*</span></div>
                                <div class="itt">
                                    <div >
                                        <div class="hole">
                                            <div class="alp ">A、</div>
                                            <div class="shorter ">
                                                <el-input
                                                        name="testName"
                                                        placeholder=""
                                                        v-model="MultipleTopic1[MultipleTopic1Id].optiona"
                                                        clearable>
                                                </el-input>
                                            </div>
                                        </div>
                                        <div class="hole">
                                            <div class="alp">B、</div>
                                            <div class="shorter">
                                                <el-input
                                                        name="spacial"
                                                        placeholder=""
                                                        v-model="MultipleTopic1[MultipleTopic1Id].optionb"
                                                        clearable>
                                                </el-input>
                                            </div>
                                        </div>
                                        <div class="hole">
                                            <div class="alp">C、</div>
                                            <div class="shorter">
                                                <el-input
                                                        name="startTime"
                                                        placeholder=""
                                                        v-model="MultipleTopic1[MultipleTopic1Id].optionc"
                                                        clearable>
                                                </el-input>
                                            </div>
                                        </div>
                                        <div class="hole">
                                            <div class="alp">D、</div>
                                            <div class="shorter">
                                                <el-input
                                                        name="endTime"
                                                        placeholder=""
                                                        v-model="MultipleTopic1[MultipleTopic1Id].optiond"
                                                        clearable>
                                                </el-input>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="mes">答案<span class="bur">*</span></div>
                                <div class="out">
                                    <template>
                                        <el-checkbox-group v-model="MultipleTopic1[MultipleTopic1Id].answer" class="mulAnswer">
                                            <el-checkbox label="A"></el-checkbox>
                                            <el-checkbox label="B"></el-checkbox>
                                            <el-checkbox label="C"></el-checkbox>
                                            <el-checkbox label="D"></el-checkbox>
                                        </el-checkbox-group>
                                    </template>
                                </div>
                                <div class="mes">解析<span class="bur">*</span></div>
                                <div class="lim">
                                    <el-input
                                            type="textarea"
                                            :rows="2"
                                            placeholder="请输入题目解析"
                                            v-model="MultipleTopic1[MultipleTopic1Id].parse">
                                    </el-input>
                                </div>
                            </form>
                            <div class="buttons">
                                <el-button type="primary" plain class="mButton" @click="returnMultipleTopic1">上一题</el-button>
                                <template>
                                    <el-button type="success" @click="open1" class="mButton" @click="upload">完成提交</el-button>
                                </template>
                                <el-button type="primary" plain class="mButton" @click="nextMultipleTopic1">下一题</el-button>
                                <template>
                                    <el-button type="danger" icon="el-icon-delete" @click="open2" circle ></el-button>
                                </template>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="判断题">
                            <form>
                                <div class="mes">题干<span class="bur">*</span></div>
                                <div class="itq">
                                    <div class="question">{{JudgeTopicId+1}}
                                        <el-input
                                                type="textarea"
                                                :rows="2"
                                                placeholder="请输入题干内容"
                                                v-model="JudgeTopic[JudgeTopicId].content">
                                        </el-input>
                                    </div>
                                </div>
                                <div class="mes">答案<span class="bur">*</span></div>
                                <div class="out">
                                    <template>
                                        <el-radio-group v-model="JudgeTopic[JudgeTopicId].answer" class="answer">
                                            <el-radio :label="3">对</el-radio>
                                            <el-radio :label="6">错</el-radio>
                                        </el-radio-group>
                                    </template>
                                </div>
                                <div class="mes">解析<span class="bur">*</span></div>
                                <div class="lim">
                                    <el-input
                                            type="textarea"
                                            :rows="2"
                                            placeholder="请输入题目解析"
                                            v-model="JudgeTopic[JudgeTopicId].parse">
                                    </el-input>
                                </div>
                            </form>
                            <div class="buttons">
                                <el-button type="primary" plain class="mButton" @click="returnJudgeTopic">上一题</el-button>
                                <template>
                                    <el-button type="success" @click="open1" class="mButton" @click="upload">完成提交</el-button>
                                </template>
                                <el-button type="primary" plain class="mButton" @click="nextJudgeTopic">下一题</el-button>
                                <template>
                                    <el-button type="danger" icon="el-icon-delete" @click="open2" circle ></el-button>
                                </template>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="填空题">
                            <form>
                                <div class="mes">题干<span class="bur">*</span></div>
                                <div class="itq">
                                    <div class="question">{{BlankTopic0Id+1}}
                                        <el-input
                                                type="textarea"
                                                :rows="2"
                                                placeholder="请输入题干内容"
                                                v-model="BlankTopic0[BlankTopic0Id].content">
                                        </el-input>
                                    </div>
                                </div>
                                <div class="mes">答案<span class="bur">*</span></div>
                                <div class="lim">
                                    <el-input
                                            type="textarea"
                                            :rows="2"
                                            placeholder="请输入题目答案"
                                            v-model="BlankTopic0[BlankTopic0Id].answer">
                                    </el-input>
                                </div>
                                <div class="mes">解析<span class="bur">*</span></div>
                                <div class="lim">
                                    <el-input
                                            type="textarea"
                                            :rows="2"
                                            placeholder="请输入题目解析"
                                            v-model="BlankTopic0[BlankTopic0Id].parse">
                                    </el-input>
                                </div>
                            </form>
                            <div class="buttons">
                                <el-button type="primary" plain class="mButton" @click="returnBlankTopic0">上一题</el-button>
                                <template>
                                    <el-button type="success" @click="open1" class="mButton"  @click="upload">完成提交</el-button>
                                </template>
                                <el-button type="primary" plain class="mButton" @click="nextBlankTopic0">下一题</el-button>
                                <template>
                                    <el-button type="danger" icon="el-icon-delete" @click="open2" circle ></el-button>
                                </template>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="代码题">
                            <form>
                                <div class="mes">题干<span class="bur">*</span></div>
                                <div class="itq">
                                    <div class="question">{{BlankTopic1Id+1}}
                                        <el-input
                                                type="textarea"
                                                :rows="2"
                                                placeholder="请输入题干内容"
                                                v-model="BlankTopic1[BlankTopic1Id].content">
                                        </el-input>
                                    </div>
                                </div>
                                <div class="mes">答案<span class="bur">*</span></div>
                                <div class="lim">
                                    <el-input
                                            type="textarea"
                                            :rows="2"
                                            placeholder="请输入题目答案"
                                            v-model="BlankTopic1[BlankTopic1Id].answer">
                                    </el-input>
                                </div>
                                <div class="mes">解析<span class="bur">*</span></div>
                                <div class="lim">
                                    <el-input
                                            type="textarea"
                                            :rows="2"
                                            placeholder="请输入题目解析"
                                            v-model="BlankTopic1[BlankTopic1Id].parse">
                                    </el-input>
                                </div>
                            </form>
                            <div class="buttons">
                                <el-button type="primary" plain class="mButton" @click="returnBlankTopic1">上一题</el-button>
                                <template>
                                    <el-button type="success" @click="open1" class="mButton" @click="upload">完成提交</el-button>
                                </template>
                                <el-button type="primary" plain class="mButton" @click="nextBlankTopic1">下一题</el-button>
                                <template>
                                    <el-button type="danger" icon="el-icon-delete" @click="open2" circle ></el-button>
                                </template>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="简答题">
                            <form>
                                <div class="mes">题干<span class="bur">*</span></div>
                                <div class="itq">
                                    <div class="question">{{BlankTopic1Id+1}}
                                        <el-input
                                                type="textarea"
                                                :rows="2"
                                                placeholder="请输入题干内容"
                                                v-model="BlankTopic1[BlankTopic1Id].content">
                                        </el-input>
                                    </div>
                                </div>
                                <div class="mes">答案<span class="bur">*</span></div>
                                <div class="lim">
                                    <el-input
                                            type="textarea"
                                            :rows="2"
                                            placeholder="请输入题目答案"
                                            v-model="BlankTopic1[BlankTopic1Id].answer">
                                    </el-input>
                                </div>
                                <div class="mes">解析<span class="bur">*</span></div>
                                <div class="lim">
                                    <el-input
                                            type="textarea"
                                            :rows="2"
                                            placeholder="请输入题目解析"
                                            v-model="BlankTopic1[BlankTopic1Id].parse">
                                    </el-input>
                                </div>
                            </form>
                            <div class="buttons">
                                <el-button type="primary" plain class="mButton" @click="returnBlankTopic1">上一题</el-button>
                                <template>
                                    <el-button type="success" @click="open1" class="mButton" @click="upload">完成提交</el-button>
                                </template>
                                <el-button type="primary" plain class="mButton" @click="nextBlankTopic1">下一题</el-button>
                                <template>
                                    <el-button type="danger" icon="el-icon-delete" @click="open2" circle ></el-button>
                                </template>
                            </div>
                        </el-tab-pane>
                    </el-tabs>
        </template>

    </div>
    <script>
        let app = new Vue({
            el: '.rad',
            data : {
                MultipleTopic0Id:0,
                MultipleTopic1Id:0,
                JudgeTopicId:0,
                BlankTopic0Id:0,
                BlankTopic1Id:0,
                MultipleTopic0:[{
                    type:0,
                    content:"",
                    optiona:"",
                    optionb:"",
                    optionc:"",
                    optiond:"",
                    answer:"",
                    parse:"",
                }],
                MultipleTopic1:[{
                    type:1,
                    content:"",
                    optiona:'',
                    optionb:'',
                    optionc:'',
                    optiond:'',
                    answer:[],
                    parse:"",
                }],
                JudgeTopic:[{
                    content:"",
                    answer:"", // 答案
                    parse:"",
                }],
                BlankTopic0:[{
                    type:0,
                    content:"",
                    answer:"", // 答案
                    parse:"",
                }],
                BlankTopic1:[{
                    type:1,
                    content:"",
                    answer:"", // 答案
                    parse:"",
                }],
                radio1: '1',
                checkList: ['选中且禁用','复选框 A']
            },
            methods: {
                nextMultipleTopic0(){
                    if(this.MultipleTopic0Id === this.MultipleTopic0.length-1){
                        this.MultipleTopic0.push({
                            type:0,
                            content:"",
                            optiona:"",
                            optionb:"",
                            optionc:"",
                            optiond:"",
                            answer:"",
                            parse:"",
                        })
                    }
                    this.MultipleTopic0Id++;
                },
                nextMultipleTopic1(){
                    if(this.MultipleTopic1Id === this.MultipleTopic1.length-1){
                        this.MultipleTopic1.push({
                            type:1,
                            content:"",
                            optiona:"",
                            optionb:"",
                            optionc:"",
                            optiond:"",
                            answer:[],
                            parse:"",
                        })
                    }
                    this.MultipleTopic1Id++;
                },
                nextJudgeTopic(){
                    if(this.JudgeTopicId === this.JudgeTopic.length-1){
                        this.JudgeTopic.push({
                            content:"",
                            answer:"", // 答案
                            parse:"",
                        })
                    }
                    this.JudgeTopicId++;
                },
                nextBlankTopic0(){
                    if(this.BlankTopic0Id === this.BlankTopic0.length-1){
                        this.BlankTopic0.push({
                            content:"",
                            answer:"", // 答案
                            parse:"",
                        })
                    }
                    this.BlankTopic0Id++;
                },
                nextBlankTopic1(){
                    if(this.BlankTopic1Id === this.BlankTopic1.length-1){
                        this.BlankTopic1.push({
                            content:"",
                            answer:"", // 答案
                            parse:"",
                        })
                    }
                    this.BlankTopic1Id++;
                },
                returnMultipleTopic0(){
                    // alert(this.MultipleTopic0Id)
                    if(this.MultipleTopic0Id>0){
                        this.MultipleTopic0Id--;
                    }
                },
                returnMultipleTopic1(){
                    if(this.MultipleTopic1Id>0){
                        this.MultipleTopic1Id--;
                    }
                },
                returnJudgeTopic(){
                    if(this.JudgeTopicId>0){
                        this.JudgeTopicId--;
                    }
                },
                returnBlankTopic0(){
                    if(this.BlankTopic0Id>0){
                        this.BlankTopic0Id--;
                    }
                },
                returnBlankTopic1(){
                    if(this.BlankTopic1Id>0){
                        this.BlankTopic1Id--;
                    }
                },
                upload(){
                    for (let i = 0; i < this.MultipleTopic0.length; i++) {
                        axios.get("${pageContext.request.contextPath}/addSinTopic",
                            {
                                params:{
                                    content:this.MultipleTopic0[i].content,
                                    optiona:this.MultipleTopic0[i].optiona,
                                    optionb:this.MultipleTopic0[i].optionb,
                                    optionc:this.MultipleTopic0[i].optionc,
                                    optiond:this.MultipleTopic0[i].optiond,
                                    answer:this.MultipleTopic0[i].answer,
                                    parse:this.MultipleTopic0[i].parse,
                                }
                            }
                        ).then
                        (function (response) {
                        })
                    }
                    for (let i = 0; i < this.MultipleTopic1.length; i++) {
                        axios.get("${pageContext.request.contextPath}/addMulTopic?answer="+this.MultipleTopic1[i].answer,
                            {
                                params:{
                                    content:this.MultipleTopic1[i].content,
                                    optiona:this.MultipleTopic1[i].optiona,
                                    optionb:this.MultipleTopic1[i].optionb,
                                    optionc:this.MultipleTopic1[i].optionc,
                                    optiond:this.MultipleTopic1[i].optiond,
                                    // answer:this.MultipleTopic1[i].answer,
                                    parse:this.MultipleTopic1[i].parse,
                                }
                            }
                        ).then
                        (function (response) {
                        })
                    }
                    for (let i = 0; i < this.JudgeTopic.length; i++) {
                        axios.get("${pageContext.request.contextPath}/addJudgeTopic",
                            {
                                params:{
                                    content:this.JudgeTopic[i].content,
                                    answer:this.JudgeTopic[i].answer,
                                    parse:this.JudgeTopic[i].parse,
                                }
                            }
                        ).then
                        (function (response) {
                        })
                    }
                    for (let i = 0; i < this.BlankTopic0.length; i++) {
                        axios.get("${pageContext.request.contextPath}/addBlankTopic",
                            {
                                params:{
                                    content:this.BlankTopic0[i].content,
                                    answer:this.BlankTopic0[i].answer,
                                    parse:this.BlankTopic0[i].parse,
                                }
                            }
                        ).then
                        (function (response) {
                        })
                    }
                    for (let i = 0; i < this.BlankTopic1.length; i++) {
                        axios.get("${pageContext.request.contextPath}/addEssayTopic",
                            {
                                params:{
                                    content:this.BlankTopic1[i].content,
                                    answer:this.BlankTopic1[i].answer,
                                    parse:this.BlankTopic1[i].parse,
                                }
                            }
                        ).then
                        (function (response) {
                        })
                    }
                },
                GoAutoInput(){
                    location.href="autoInput.jsp";
                },
                open1() {
                    this.$confirm('你确定要提交上传吗?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.upload();
                        this.$message({
                            type: 'success',
                            message: '提交成功!'
                        });
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消提交'
                        });
                    });
                },
                open2() {
                    this.$confirm('你确定要删除该题目吗?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
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
                }
            }
        })
    </script>
<%--    <script src="../bootstrap/js/jquery-3.3.1.min.js"></script>--%>
<%--    <script src="../bootstrap/js/bootstrap.min.js"></script>--%>
</body>
</html>