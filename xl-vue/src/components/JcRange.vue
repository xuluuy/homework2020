<template>
  <div class="jc-component__range">
    <div class="jc-range" :class="rangeStatus?'success':''" >
        <i @mousedown="rangeMove" :class="rangeStatus?successIcon:startIcon"></i>
        {{rangeStatus?successText:startText}}
    </div>
  </div>
</template>
<script>
export default {
    props: {
        // 成功之后的函数
        successFun: {
            type: Function
        },
        //成功图标
        successIcon: {
            type: String,
            default: 'el-icon-success'
        },
        //成功文字
        successText: {
            type: String,
            default: '验证成功'
        },
        //开始的图标
        startIcon: {
            type: String,
            default: 'el-icon-d-arrow-right'
        },
        //开始的文字
        startText: {
            type: String,
            default: '请拖住滑块，拖动到最右边'
        },
        //失败之后的函数
        errorFun: {
            type: Function
        },
        //或者用值来进行监听
        status: {
            type: String
        }
    },
    data(){
        return {
            disX : 0,
            rangeStatus: false,
            date:'',
            startDate:'',
            endDate:'',
            msg:''
        }
    },
  methods:{
        //计算时间
        computeTime(){
            this.date = (this.endDate-this.startDate)/1000
            if(this.date<0.01){
                this.msg='您已超过99%的用户！'
            }else if(this.date<0.02){
                this.msg='您已超过97%的用户！'
            }else if(this.date<0.05){
                this.msg='您已超过85%的用户！'
            }else{
                this.msg='您已超过65%的用户！'
            }
        },
        //滑块移动
        rangeMove(e){
            let ele = e.target;
            let startX = e.clientX;
            let eleWidth = ele.offsetWidth;
            let parentWidth =  ele.parentElement.offsetWidth;
            let MaxX = parentWidth - eleWidth;
            if(this.rangeStatus){//不运行
                return false;
            }
            document.onmousemove = (e) => {
            this.startDate =  new Date().getTime();
                let endX = e.clientX;
                this.disX = endX - startX;
                if(this.disX<=0){
                    this.disX = 0;
                }
                if(this.disX>=MaxX-eleWidth){//减去滑块的宽度,体验效果更好
                    this.disX = MaxX;
                }
                ele.style.transition = '.1s all';
                ele.style.transform = 'translateX('+this.disX+'px)';
                e.preventDefault();
            }
            document.onmouseup = ()=> {
                this.endDate = new Date().getTime();
                if(this.disX !== MaxX){
                    ele.style.transition = '.5s all';
                    ele.style.transform = 'translateX(0)';
                    //执行失败的函数
                    this.errorFun && this.errorFun();
                    

                }else{
                    this.rangeStatus = true;
                    if(this.status){
                        this.$parent[this.status] = true;
                    }
                    this.computeTime()
                    //执行成功的函数
                    this.successFun && this.successFun({date:this.date,msg:this.msg});
                }
                document.onmousemove = null;
                document.onmouseup = null;
            }
        }
    }
};
</script>
<style lang="scss" scoped>
@mixin jc-flex{
    display: flex;
    justify-content: center;
    align-items: center;
}
.jc-component__range{
    .jc-range{
        background-color: #e9e9e9;
        position: relative;
        transition: 1s all;
        user-select: none;
        color: #8b8989;
        @include jc-flex;
        height: 40px; /*no*/
        border-radius: 5px;
        &.success{
            background-color: #a3caeb;
            color: #fff;
            i{
                color: #a3caeb;
            }
        }
        i{
            position: absolute;
            left: 0;
            width: 50px;/*no*/
             border-radius: 5px;
            height: 100%;
            color: #a3caeb;
            background-color: #fff;
            border: 1px solid #d8d8d8;
            cursor: pointer;
            font-size: 24px;
            @include jc-flex;
        }
    }
}
</style>