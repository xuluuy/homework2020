<template>
      <el-form :model="ruleForm" label-position="left" v-loading="loading" ref="ruleForm"
     :rules="rules"  label-width="100px" class="login-container">
           <h3 class="login_title">用户注册</h3>
           <el-form-item label="用户名" prop="username">
           <el-input type="text" v-model="ruleForm.username"
                auto-complete="off"></el-input>
           </el-form-item>
           <el-form-item label="密码" prop="password">
           <el-input type="password" v-model="ruleForm.password"
                auto-complete="off"></el-input>
           </el-form-item>
           <el-form-item label="姓名" >
           <el-input type="text" v-model="ruleForm.name"
                auto-complete="off"></el-input>
           </el-form-item>
           <el-form-item label="电话" prop="phone">
           <el-input type="text" v-model="ruleForm.phone"
                auto-complete="off"></el-input>
           </el-form-item>
            <el-form-item label="email" >
           <el-input type="text" v-model="ruleForm.email"
                auto-complete="off"></el-input>
           </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">立即注册</el-button>
    <el-button @click="resetForm('ruleForm')">重置</el-button>
  </el-form-item>
</el-form>
  
</template>
<script>
import {reqRegister} from '@/api'
//定义一个全局的变量
  var validPhone=(rule, value,callback)=>{
     const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
      if (!value){
          callback(new Error('请输入电话号码'))
      }else  if (!reg.test(value)){
        callback(new Error('请输入正确的11位手机号码'))
      }else {
          callback()
      }
  }
 export default {
    data() {
      return {
        loading: false,
        ruleForm: {
          username: '',
          password: '',
          name: '',
          phone: '',
          email: ''        
        },
        rules: {
          username: [
            { required: true, message: '请输入用户名称', trigger: 'blur' },
            { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入用户密码', trigger: 'blur' },
            { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
          ],
         phone:[
            { required: true, trigger: 'blur',validator: validPhone },
         ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.register();
          } else {
           this.$alert('校验不通过，请重新输入！')
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      //注册
      async register () {
         var _this = this
         const user = {
           u_username: this.ruleForm.username,
            u_password: this.ruleForm.password,
            u_name:this.ruleForm.name,
            u_phone:this.ruleForm.phone,
            u_email:this.ruleForm.email,
            u_type:'P'
         }
          const result = await reqRegister(user)
          if (result.data.code === 200) {
              this.$alert('注册成功', '提示', {
                confirmButtonText: '确定'
              })
              _this.$router.replace('/login')
            } else {
              this.$alert(result.data.message, '提示', {
                confirmButtonText: '确定'
              })
            }
      }
       /* register () {
        var _this = this
        this.$axios
          .post('/register', {
            u_username: this.ruleForm.username,
            u_password: this.ruleForm.password,
            u_name:this.ruleForm.name,
            u_phone:this.ruleForm.phone,
            u_email:this.ruleForm.email,
            u_type:'P'
          })
          .then(resp => {
            if (resp.data.code === 200) {
              this.$alert('注册成功', '提示', {
                confirmButtonText: '确定'
              })
              _this.$router.replace('/login')
            } else {
              this.$alert(resp.data.message, '提示', {
                confirmButtonText: '确定'
              })
            }
          })
          .catch(failResponse => {})
      }*/
    }
  } 
  
</script>
<style scoped>
 .login-container:before{
    background:url("../assets/login.jpg") no-repeat;
    background-size: cover;
    width:100%;
    height: 100%;
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    z-index: -1;/*-1 可以当背景*/
    -webkit-filter: blur(5px);
    filter: blur(5px);
}
 
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 30px auto;
    width: 550px;
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
 
</style>