let bkPicture = new Vue({
    el: ".bkPicture",
    data: {
        leftPage: [1310, 1310, 1310],
        visibilityPage: ["hidden", "hidden", "hidden"],
        setInter: null,
        sno: "",
        password: "",
        password1: "",
        password2: "",
        confirmPass: "两次密码不一致",
        confirmPassColor: "red",
        difficulty: "密码必须多于6位数，少于16位",
        difficultyColor: "#999999",
        snoTip: "请输入学号",
        snoTipColor: "red",
        disable: "disable",
        loginDisable: "disable",
        registerDisable: "disable",
        username: "",
        email: "",
        teacherCode: "",
        teacherCodeTip: "",
        visibilityTeacherCode: "hidden",
        select: "",
        select1: "<option>请选择省份</option>",
        select2: "<option>请选择学校</option>",
        province:"请选择省份",
        schoolName:"请选择学校",
        errorMsg1:"",
        // pointer1:"none",
        // Newsno:"",
    },
    methods: {
        loginUser : function(){
            let that = this;
            axios.get("../login/login?sno=" + that.sno +"&password=" +that.password).then
            (function (response) {
                //处理服务器响应数据：{flag:false,errorMsg:"邮箱未激活!"}
                if (response.data.flag){
                    //注册成功，跳转页面
                    location.href = "../index.jsp";
                }else {
                    //注册失败，显示errorMsg信息
                    that.$message.error(response.data.errorMsg);
                }
            })
        },
        Student: function () {
            // this.next(1);
            let that = this;
            axios.get("../login/removeSchoolSession").then
            (function (response) {
                that.next(1);
            })
        },
        TeacherCodeSubmit: function () {
            let that = this;
            axios.get("../login/findSchoolCode?teacherCode=" + that.teacherCode).then
            (function (response) {
                console.log(response.data);
                if (response.data != "OK") {
                    that.teacherCodeTip = "错误的教员码";
                    alert("错误的教员码");
                } else {
                    that.visibilityTeacherCode = "hidden";
                    that.select = "disable";
                    that.next(1);
                }
            }, function (err) {
            })
        },
        snoJudge: function () {
            let that = this;
            axios.get("../login/findSno?sno=" + that.sno).then
            (function (response) {
                if (response.data != "OK") {
                    console.log(response.data)
                    that.snoTip = "学号已被注册";
                    that.snoTipColor = "red";
                    that.NextPage();
                }
            }, function (err) {
            })
        },
        findProvince: function () {
            let that = this;
            axios.get("../login/findProvince").then
            (function (response) {
                let lis = "<option>请选择省份</option>";     //异步循环赋值

                if (response.data.province) {    //如果存在则为学校的老师,省和学校固定
                    //[{schoolId:1,schoolNmae:江西农业大学,province:江西省,schoolCode:10086}]
                    let li = "<option >" + response.data.province + "</option>";
                    lis += li;

                    //设置select2_html的html
                    that.select2 = "<option>请选择学校</option>" +
                        "<option >" + response.data.schoolName + "</option>"
                } else {
                    //[province:江西省},{},..]
                    for (var i = 0; i < response.data.length; i++) {

                        let li = "<option >" + response.data[i] + "</option>";
                        lis += li;
                    }
                }
                that.select1 = lis;
            }, function (err) {
            })
        },
        provinceChange: function () {
            let that = this;
            axios.get("../login/findSName?province="+that.province).then
            (function (response) {
                let lis = "<option>请选择学校</option>";
                if (response.data.schoolName){
                    let li = "<option >" + response.data.schoolName + "</option>"
                    lis += li;
                }else {
                    for (var i = 0; i < response.data.length; i++) {
                        let li = "<option >" + response.data[i] + "</option>";
                        lis += li;
                    }
                }
                that.select2 = lis;
            }, function (err) {
            })
        },
        regist: function(){
            let that = this;
            axios.get("../login/regist?province=" + that.province + "&schoolName=" + that.schoolName +
            "&sno=" + that.sno + "&username=" + that.username + "&password=" + that.password1 + "&email=" + that.email).then
            (function (response) {
                //处理服务器响应数据：{flag:false,errorMsg:"注册失败!"}
                if (response.data.flag){
                    //注册成功，跳转页面
                    location.href = "/login/toIndex";
                }else {
                    //注册失败，显示errorMsg信息
                    that.$message.error(response.data.errorMsg);
                }
            }, function (err) {
            })
        },
        // Submit: function () {
        //     let that = this;
        //     axios.post("http://localhost:8080/JavaWeb_Vue/userServlet", {
        //         sno: that.sno,
        //         password: that.password1,
        //     }).then
        //     (function (response) {
        //         console.log(that.sno);
        //         console.log(that.password1);
        //         console.log(response.data);
        //     }, function (err) {
        //     })
        // },
        Login: function () {
            if (this.sno != "" && this.password != "" && this.snoTipColor != "red") {
                this.loginDisable = "";
            } else {
                this.loginDisable = "disable";
            }
        },
        NextPage: function () {
            if (this.snoTipColor != "red" && this.difficultyColor != "red" && this.confirmPassColor != "red") {
                this.disable = "";
            } else {
                this.disable = "disable";
            }
        },
        register: function () {
            if (this.username != "" && this.email != "") {
                this.registerDisable = "";
            } else {
                this.registerDisable = "disable";
            }
        },
        Confirm: function () {
            if (this.password1 === this.password2) {
                this.confirmPass = "两次密码一致";
                this.confirmPassColor = "#999999";
            } else {
                this.confirmPass = "两次密码不一致";
                this.confirmPassColor = "red";
            }
        },
        passwordJudge: function () {
            let reg1 = /^[0-9]{6,16}$|^[a-zA-Z]{6,16}$/;
            let reg2 = /^[A-Za-z0-9]{6,16}$/;
            let reg3 = /^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$/;
            if (reg1.test(this.password1)) {
                this.difficulty = "密码难度:太简单";
                this.difficultyColor = "red";
            } else {
                if (reg2.test(this.password1)) {
                    this.difficulty = "密码难度:中";
                    this.difficultyColor = "#fbc531";
                } else {
                    if (reg3.test(this.password1)) {
                        this.difficulty = "密码难度:高";
                        this.difficultyColor = "chartreuse";
                    } else {
                        this.difficulty = "密码不符合规范:密码必须多于6位数，少于16位"
                        this.difficultyColor = "red";
                    }
                }
            }
        },
        judge: function () {
            let patt = /^\d{10}$/;
            if (!patt.test(this.sno)) {
                this.snoTip = "学号不符合规则:学号为10位数字";
                this.snoTipColor = "red";
            } else {
                this.snoTip = "学号验证通过";
                this.snoTipColor = "#999999";
                this.snoJudge();
            }
        },
        judgeLogin: function () {
            let patt = /^\d{10}$/;
            if (!patt.test(this.sno)) {
                this.snoTip = "学号不符合规则:学号为10位数字";
                this.snoTipColor = "red";
            } else {
                this.snoTip = "学号验证通过";
                this.snoTipColor = "#999999";
            }
        },
        Return: function (i) {
            this.setInter = setInterval(() => {
                this.visibilityImg = "hidden";
                if (this.leftPage[i] === 1310) {

                    this.visibilityPage[i] = "hidden";
                    Vue.set(this.visibilityPage, i, this.visibilityPage[i]);
                    clearInterval(this.setInter)
                } else {
                    this.leftPage[i] += 10;
                    Vue.set(this.leftPage, i, this.leftPage[i]);
                }
            })
        },
        next: function (i) {
            this.setInter = setInterval(() => {
                this.visibilityPage[i] = "visible";
                Vue.set(this.visibilityPage, i, this.visibilityPage[i]);
                if (this.leftPage[i] === 620 + i * 10) {
                    clearInterval(this.setInter);
                } else {
                    this.leftPage[i] -= 10;
                    // console.log(this.leftPage[i]);
                    Vue.set(this.leftPage, i, this.leftPage[i]);
                }
            })
        },
        ReturnLogin: function (i) {
            this.setInter = setInterval(() => {
                this.visibilityImg = "hidden";
                if (this.leftPage[i] === 1310) {

                    this.visibilityPage[i] = "hidden";
                    Vue.set(this.visibilityPage, i, this.visibilityPage[i]);
                    clearInterval(this.setInter)
                    if (i != 0) {
                        this.ReturnLogin(i - 1);
                    }
                } else {
                    this.leftPage[i] += 10;
                    Vue.set(this.leftPage, i, this.leftPage[i]);
                }
            })
        },
        Teacher: function () {
            if (this.visibilityTeacherCode === "hidden") {
                this.visibilityTeacherCode = "visible";
            } else {
                this.visibilityTeacherCode = "hidden";
            }
        }

    }
})