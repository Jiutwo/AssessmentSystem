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
    <title>教员中心</title>
    <script src="../JavaScript/vue.js"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <link rel="stylesheet" href="../PageStyle/manageTests_style.css">
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../bootstrap/css/bootstrap-theme.min.css">
</head>
<body>
    <div class="decorate"></div>
    <ol class="breadcrumb">
        <li><a href="../index.html">首页</a></li>
        <li class="active">教员中心</li>
        <li><a href="">作业报告</a></li>
        <li><a href="correct.html">批改作业</a></li>
    </ol>
    <div class="bodySize">
        <div class="alreadyInput">
            <el-container style="height: 500px; border: 1px solid #eee">
                <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                    <el-menu :default-openeds="['1']">
                        <el-submenu index="1">
                            <template slot="title"><i class="el-icon-info"></i><span class="underWay">进行中</span></template>
                            <el-menu-item index="1-1">
                                <span class="testName">单纯形法</span>
                            </el-menu-item>
                        </el-submenu>
                        <el-submenu index="2">
                            <template slot="title"><i class="el-icon-success"></i><span>已结束</span></template>
                            <el-menu-item index="2-1"><span class="testName">单纯形法</span></el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-aside>

                <el-container>
                    <el-header style="text-align: right; font-size: 12px">
                        <span>作业详情</span>
                    </el-header>
                    <div>
                    <div class="testDetail">
                        <div class="udrWyImg"><div class="sh"><i class="el-icon-edit-outline"></i></div><div class="condition">已完成</div></div>
                        <span class="testName"><h3>单纯形法</h3></span>
                        <br>
                        <span class="spacial"><h4>算法</h4></span>
                        <div class="up">
                            开始时间：<span class="startTime">2020-11-29 16:43</span>
                            <hr>
                            截止时间：<span class="endTime">2020-11-30 17:33</span>
                            <div class="hasFinished">已截至</div>
                        </div>
                        <div class="down">
                            <hr>
                            总题数：<span class="testNum">100</span>
                        </div>
                    </div>
                    <div class="finishCondition">
                        <div class="arricon groupIcon"><div class="gr">&#xe60d;</div><div class="fc">完成情况</div></div>
                        <div class="groupId"><span class="group"><h3>55463</h3></span></div>
                        <div class="vr">
                            完成人数：<span class="finishMan">10</span>/<span class="totalMan">20</span>
                            <hr>
                            未批改人数：<span class="withoutCorrect">2</span>
                        </div>
                    </div>
                    </div>
                </el-container>
            </el-container>
        </div>
        <div class="inputTests">
            <div class="inputContent">
                <form>
                    <template>
                        <div>
                            <el-input
                                    name="testName"
                                    placeholder="请输入题目名"
                                    v-model="input"
                                    clearable>
                            </el-input>
                            <el-divider></el-divider>
                            <el-input
                                    name="spacial"
                                    placeholder="请输入题目类型"
                                    v-model="input"
                                    clearable>
                            </el-input>
                            <el-divider></el-divider>
                            <template>
                            <div class="block">
                                <span class="demonstration">任务时间</span>
                                <el-date-picker
                                        v-model="value2"
                                        type="datetimerange"
                                        :picker-options="pickerOptions"
                                        range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期"
                                        align="right">
                                </el-date-picker>
                            </div>
                            </template>
                            <el-divider></el-divider>
                            <template>
                                <el-radio-group v-model="radio" class="answer">
                                    <el-radio :label="3">公开发布</el-radio>
                                    <el-radio :label="6">发布到组</el-radio>
                                </el-radio-group>
                            </template>
                        </div>
                    </template>
                </form>
            </div>
            <div class="skipTo">
                <div class="jumbotron">
                    <div class="separate">
                        <h2>填写了题目必要信息？</h2>
                        <p>点击按钮后进入添加题目，即可完成任务发布！</p>
                        <p> <el-button type="primary">添加题目</el-button></p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        var app = new Vue({
            el: '.bodySize',
            data() {
                const item = {
                    date: '2016-05-02',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                };
                return {
                    radio:6,
                    tableData: Array(20).fill(item),
                    pickerOptions: {
                        shortcuts: [{
                            text: '最近一周',
                            onClick(picker) {
                                const end = new Date();
                                const start = new Date();
                                start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                                picker.$emit('pick', [start, end]);
                            }
                        }, {
                            text: '最近一个月',
                            onClick(picker) {
                                const end = new Date();
                                const start = new Date();
                                start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                                picker.$emit('pick', [start, end]);
                            }
                        }, {
                            text: '最近三个月',
                            onClick(picker) {
                                const end = new Date();
                                const start = new Date();
                                start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                                picker.$emit('pick', [start, end]);
                            }
                        }]
                    },
                    value1: [new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
                    value2: ''
                }
            }
        });
    </script>
    <script src="../bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    </body>
    </html>