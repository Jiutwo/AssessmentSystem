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
    <link rel="stylesheet" href="../PageStyle/atuoInput_style.css">
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../bootstrap/css/bootstrap-theme.min.css">
</head>
<body>
<div class="decorate"></div>
<div class="rad">
    <template>
        <div class="mo">
            <el-radio @change="GoAutoInput" v-model="radio1" label="1" border>手动添加</el-radio>
            <el-radio v-model="radio1" label="2" border>自动添加</el-radio>
        </div>
    </template>
    <form>
    <div class="sm">
        <div class="dec">
            <i class="el-icon-upload"></i><span class="font">填写每种题型对应数量后即可上传</span>
        </div>
        <div class="mr">
            <div class="pos">
                <div class="tes">论述题</div>
                <div class="sg">
                    <el-input
                            name="testKind"
                            placeholder="0"
                            v-model="input"
                            clearable>
                    </el-input>
                </div>
            </div>
            <div class="pos">
                <div class="tes">论述题</div>
                <div class="sg">
                    <el-input
                            name="testKind"
                            placeholder="0"
                            v-model="input"
                            clearable>
                    </el-input>
                </div>
            </div>
            <div class="pos">
                <div class="tes">论述题</div>
                <div class="sg">
                    <el-input
                            name="testKind"
                            placeholder="0"
                            v-model="input"
                            clearable>
                    </el-input>
                </div>
            </div>
            <div class="pos">
                <div class="tes">论述题</div>
                <div class="sg">
                    <el-input
                            name="testKind"
                            placeholder="0"
                            v-model="input"
                            clearable>
                    </el-input>
                </div>
            </div>
            <div class="pos">
                <div class="tes">论述题</div>
                <div class="sg">
                    <el-input
                            name="testKind"
                            placeholder="0"
                            v-model="input"
                            clearable>
                    </el-input>
                </div>
            </div>
        </div>
        <div class="btn">
            <el-button type="primary">完成<i class="el-icon-upload el-icon--right"></i></el-button>
        </div>
    </div>
    </form>
</div>


<script>
    var app = new Vue({
        el: '.rad',
        data () {
            return {
                radio1: '2',
                input:''
            };
        },
        methods:{
            GoAutoInput(){
                location.href="inputTests.jsp";
            },
        }
    })
</script>
<script src="../bootstrap/js/jquery-3.3.1.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</body>
</html>