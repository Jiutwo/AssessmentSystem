<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="../PageStyle/login_style.css">
</head>
<body>
    <div class = "bkPicture">
<!--登录页-->
        <div class="loginPage">
            <form action="../login/login" method="post">
            <div class="loginContent">登录</div>
                <div name="errorMsg1" style="text-align: center">${errorMsg1}</div>
                <span class="user-icon">&#xe713;</span>
            <input type="text" class="username" name="sno" v-model="sno" @keyup="Login1()">
            <div class="usernameTip" v-text="snoTip" :style="{color: snoTipColor}"></div>
                <span class="pwd-icon">&#xe657;</span>
            <input type="password" class="password " name="password" v-model="password" @keyup="Login1">
            <div class="passwordTip"></div>
            <button type="submit" class="loginBtn" :class="loginDisable">登录</button>
            </form>
            <div class="registerSkip"><a href="javascript:;" @click="next(0)">没有账号？点击注册</a></div>
        </div>
<!--选择身份界面-->
        <div class="registerPage1" :style="{left:leftPage[0]+'px',visibility:visibilityPage[0]}">
            <div class="header">请选择您的身份！</div>
            <div class="student">
                <div class="textContent">我是学生</div>
                <div class="imgContent" @click="Student"></div>
            </div>
            <div class="teacher">
                <div class="textContent">我是考核方</div>
                <div class="imgContent" @click="Teacher"></div>
            </div>
            <div class="registerSkip"><a href="javascript:;" @click="Return(0)">返回登录</a></div>
        </div>
        <div class="invite" :style="{visibility: visibilityTeacherCode}">
                <div>请输入教员码</div>
                <input type="text" name="inviteCode" v-model="teacherCode">
<!--                <div style="color: #e86060" v-text="teacherCodeTip"></div>-->
                <button class="cancel" @click="Teacher">取消</button>
                <button type="submit" class="confirm" @click="TeacherCodeSubmit">确认</button>
        </div>

        <!--注册用户名密码-->
        <div class="registerPage2" :style="{left:leftPage[1]+'px',visibility:visibilityPage[1]}">
            <div class="get">
                <form action="../../index.jsp" method="post">
                    <div class="loginContent">注册</div>
                    <div class="part">
                        <input type="text" class="re_input user" name="sno " v-model="sno" @keyup="judge();NextPage()" @blur="judge();NextPage()">
                        <div class="usrTip" v-text="snoTip" :style="{color: snoTipColor}"></div>
                        <input type="password" class="re_input pwd" name="password1" v-model="password1" @keyup="passwordJudge();NextPage();Confirm()">
                        <div class="pwdTip" v-text="difficulty" :style="{color: difficultyColor}"></div>
                        <input type="password" class="re_input insure_pwd" name="password2" v-model="password2" @keyup="Confirm();NextPage()">
                        <div class="pwdTip" v-text="confirmPass" :style="{color: confirmPassColor}"></div>
                    </div>
                    <span class="span1">学号</span>
                    <span class="span2">密码</span>
                    <span class="span3">确认<br></br>密码</span>
                    <div class="turn-left arricon" @click="Return(1)"><a href="javascript:;">&#xe8cf;</a></div>
                    <div class="turn-right arricon" @click="next(2);findProvince()" :class="disable"><a href="javascript:;">&#xe8d4;</a></div>
                </form>                                                 <%--;Submit()--%>
            </div>
            <div class="registerSkip"><a href="javascript:;" @click="ReturnLogin(1)">返回登录</a></div>
        </div>
<!--完善信息页-->
        <div class="registerPage3" :style="{left:leftPage[2]+'px',visibility:visibilityPage[2]}">
            <div class="get">
                <div v-html="errorMsg" style="text-align: center"></div>
                <form action="javascript:;" method="post">

                    <!--                    学号与密码-->
                    <input name="sno" type="text" v-model="sno" style="visibility: hidden">
                    <input name="password" type="password" v-model="password1" style="visibility: hidden">

                    <div class="loginContent">注册</div>
                    <div class="last">
                        <select class="sel sel_pro" name="province"  v-model="province" v-html="select1" @change="provinceChange()">
                        </select>
                        <select class="sel sel_sch" name="schoolName" v-model="schoolName" v-html="select2" >
                        </select>
                        <input type="text" class="schoolId re_input" name="username" v-model="username" @keyup="register">
                        <input type="email" class="email re_input" name="email" v-model="email" @keyup="register">
                        <!--                    <div class="testEmail"></div>-->
                    </div>
                    <span class="span4">姓名</span>
                    <span class="span5">邮箱</span>
                    <div class="turn-left arricon" @click="Return(2)"><a href="javascript:;">&#xe8cf;</a></div>
                    <button type="submit" class="loginBtn" :class="registerDisable" @click="regist()" >注册</button>
                </form>
            </div>
            <div class="registerSkip"><a href="javascript:;" @click="ReturnLogin(2)">返回登录</a></div>
        </div>
    </div>
<script src="../JavaScript/axios.min.js"></script>
<script src="../JavaScript/vue.js"></script>
<script src="../JavaScript/LoginJS.js"></script>
</body>
</html>