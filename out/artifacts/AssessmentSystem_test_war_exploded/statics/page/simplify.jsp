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
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <link rel="stylesheet" href="../PageStyle/inputTests_style.css">
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../bootstrap/css/bootstrap-theme.min.css">
</head>
<body>
<div class="decorate"></div>
<div class="rad">
    <template>
        <div class="mo">
            <el-radio v-model="radio1" label="1" border>手动添加</el-radio>
            <el-radio @change="GoAutoInput" v-model="radio1" label="2" border>自动添加</el-radio>
            <ul class="nav nav-tabs">
                <li role="presentation"><a href="inputTests.jsp">单选题</a></li>
                <li role="presentation"><a href="multiply.jsp">多选题</a></li>
                <li role="presentation"><a href="judge.jsp">判断题</a></li>
                <li role="presentation" class="active"><a href="simplify.jsp">简答题</a></li>
            </ul>
        </div>
    </template>
    <form>
        <div class="mes">题干<span class="bur">*</span></div>
        <div class="itq">
            <div class="question">
                <el-input
                        type="textarea"
                        :rows="2"
                        placeholder="请输入题干内容"
                        v-model="textarea">
                </el-input>
            </div>
        </div>
        <div class="mes">答案<span class="bur">*</span></div>
        <div class="lim">
            <el-input
                    type="textarea"
                    :rows="2"
                    placeholder="请输入题目答案"
                    v-model="key">
            </el-input>
        </div>
        <div class="mes">解析<span class="bur">*</span></div>
        <div class="lim">
            <el-input
                    type="textarea"
                    :rows="2"
                    placeholder="请输入题目解析"
                    v-model="analytical">
            </el-input>
        </div>
    </form>
    <div class="buttons">
        <el-button type="primary" plain class="mButton">上一题</el-button>
        <template>
            <el-button type="success" @click="open1" class="mButton">完成提交</el-button>
        </template>
        <el-button type="primary" plain class="mButton">下一题</el-button>
        <template>
            <el-button type="danger" icon="el-icon-delete" @click="open2" circle ></el-button>
        </template>
    </div>
</div>
<script>
    var app = new Vue({
        el: '.rad',
        data : {
            analytical:"",
            key:"",
            radio1: '1',
            input:'',
            textarea: '',
            radio:3
        },
        methods: {
            GoAutoInput(){
                location.href="autoInput.jsp";
            },
            open1() {
                this.$confirm('你确定要提交上传吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
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
<script src="../bootstrap/js/jquery-3.3.1.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</body>
</html>