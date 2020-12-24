let ul = new Vue({
    el:"#section",
    data:{
        arr:["img/img1.jpg","img/img2.jpg","img/img3.jpg","img/img4.jpg","img/img5.jpg","img/img6.jpg","img/img1.jpg"],
        setInter:null,
        left:0,
        width:0,
        autoSetInter:null,
    },
    mounted(){
        this.init()
        this.autoSetInter = setInterval(()=>{
            this.next();
        },2000);
    },
    methods:{
        init(){
            this.width=this.arr.length*520;
        },
        previous:function (){
            if(this.left === 0) {
                this.left = (-this.width+520);
            }
            clearInterval(this.setInter);
            this.setInter = setInterval(() => {
                this.left = this.left + 10;
                if (this.left % 520 === 0) {
                    clearInterval(this.setInter);
                }
            }, 10)
        },
        next:function (){
            clearInterval(this.autoSetInter);
            clearInterval(this.setInter);
            this.setInter = setInterval(() =>{
                this.left=this.left-10;
                if(this.left%520 === 0){
                    if(this.left === (-this.width+520)){
                        this.left = 0;
                    }
                    clearInterval(this.setInter);
                    this.autoSetInter = setInterval(()=>{
                        this.next();
                    },1500)
                }
            },10)
        }
    }
})