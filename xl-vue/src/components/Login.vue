<template>
<body id="poster">
    <div class="top">
    <span style="font-size:23px;padding-right:5px;">住院管理系统</span>
    <span>Hospital Inpatient Management System</span>
    </div>
    <div class="login">
    <div class="tabs">
   <span @click="cur=0" :class="{active:cur==0}">员工</span>
   <span @click="cur=1" :class="{active:cur==1}">患者</span>
    </div>
 
  <el-form class="login-container" label-position="left"  :rules="rules" ref="loginForm" :model="loginForm"
           label-width="0px" v-show="cur==0">
    <h3 class="login_title">员工系统登录</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="loginForm.username" 
                auto-complete="off" placeholder="用户名" ></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="loginForm.password" 
                auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item prop="check">
     <JcRange :successFun="successCode" :errorFun="errorCode"></JcRange>
     <p id="p" style="display:none;">123</p>
    </el-form-item>  
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="submitForm('loginForm','S')">登录</el-button>
    </el-form-item>
  </el-form>
 
 
      <el-form class="login-container" label-position="left" :rules="rules" ref="loginForm" :model="loginForm"
           label-width="0px" v-show="cur==1">
    <h3 class="login_title">患者系统登录</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="loginForm.username" 
                auto-complete="off" placeholder="用户名" ></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="loginForm.password" 
                auto-complete="off" placeholder="密码"  ></el-input>
    </el-form-item>
    <el-form-item prop="check">
    <JcRange :successFun="successCode" :errorFun="errorCode"></JcRange>
    </el-form-item> 
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 40%;background: #505458;border: none;margin-left:30px;" v-on:click="submitForm('loginForm','P')">登录</el-button>
       <router-link to="register"><el-button type="primary" style="width: 40%;background: #505458;border: none">注册</el-button></router-link>
    </el-form-item>
  </el-form>
 
</div>
 
</body>


</template>

<script>
import {reqPwdLogin} from '@/api'
import JcRange from './JcRange'

export default {
    name: 'Login',
    components: {JcRange},
    data () {
      return {
        loginForm: {
          username: '',
          password: ''
        },
        msg:false,
        responseResult: [],
        cur:0 ,//默认选中第一个tab
         rules: {
          username: [
            { required: true, message: '请输入用户名称', trigger: 'blur' },
            { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入用户密码', trigger: 'blur' },
            { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
          ],
        }
      }
    },
    methods: {
      successCode(success){
        this.msg = true;
        this.$message.success("用时"+success.date+"秒,"+success.msg)
      },
      errorCode(){
        this.msg = false;
      },
      submitForm(formName,type) {
        var _this = this
        this.$refs[formName].validate((valid) => {
          if (valid && _this.msg) {
            this.login(type);
          } else {
           this.$alert('校验不通过，请重新输入！')
            this.$router.go(0);
            return false;
          }
        });
      },
      async login(u_type){
        var _this = this
        const data = {
            u_username: this.loginForm.username,
            u_password: this.loginForm.password,
            u_type:u_type
        };
        const result = await reqPwdLogin(data)
        if(result.data.code === 200) {
           _this.$store.commit('login', _this.loginForm)
              var path = this.$route.query.redirect
              this.$router.replace({path: path === '/' || path === undefined ? '/admin/dashboard' : path})
        }else {
              this.$alert(result.data.message, '提示', {
                confirmButtonText: '确定',
                callback: action => {
                  window.location.reload()
                  }
              })
              
            }
      },
     
    }
  }

</script>

<style scoped>
.top {
  width: 100%;
  height: 55px;
  background-color: #fff;
  opacity: 0.5;
  line-height: 55px; 
  padding-left: 25px;
}

.login{
    margin:65px auto;
    width: 422px;
}
.login .tabs{
    display: inline-block;
    background-color: #fff;
    border-radius: 5px 5px 0 0 ;
    width: 125px;
    height:25px;
    margin-left: 1px;
    text-align: center;
    cursor: pointer;
    margin-bottom: -1px;
    box-shadow: 0 0 5px #cac6c6;
}
.tabs span {
    display: inline-block;  
    width:50px;
    height:24px;
    
}
.active {
    color:#0d0d0e;
    border-bottom: 1px solid #0d0d0e;
}
.login-container {
    border-radius:0 15px 15px;
    background-clip: padding-box;
    margin: 0 auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
   #poster {
    background:url("../assets/login.jpg") no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  body{
    margin: 0px;
    padding:0px;
  }
.inputBox{
    width: 48%;
    display: inline-flex;
    position: absolute;
}
.error{
    color: red;
    margin-top: 10px;
    font-size: 12px;
    display: none;
}
.canvasBox{
    position: relative;
    display: inline-flex;
    width: 50%;
    left: 50%;
}
.canvasBox .imgBox{
width: 80%;
height: 40px;
border: 1px solid #ccc;
border-radius: 5px;
position: relative;
display: inline-flex;
}
.canvasBox .refresh{
    top: 50%;
    display: inline-flex;
    width: 22px;
    height: 22px;
    background: url("../assets/img/update.png");
    background-size: 100%;
    border: 0;
    margin-top:8px;
    margin-left: 2px;;

}

</style>
