<template>
<div>
  <el-dialog
      title="修改用户密码"
      :modal-append-to-body='false'
      :visible.sync="dialogFormVisible"
      @close="resetForm('ruleForm2')">
      <el-form :model="ruleForm2" style="text-align: left" 
      status-icon :rules="rules2" ref="ruleForm2" label-width="100px" >
  <el-form-item label="密码" prop="u_password">
    <el-input type="password" v-model="ruleForm2.u_password" ></el-input>
  </el-form-item>
  <el-form-item label="新密码" prop="pass">
    <el-input type="password" v-model="ruleForm2.pass" auto-complete="off"></el-input>
  </el-form-item>
  <el-form-item label="确认新密码" prop="checkPass">
    <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off"></el-input>
  </el-form-item>
  
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm2')">提交</el-button>
    <el-button @click="resetForm('ruleForm2')">重置</el-button>
  </el-form-item>
</el-form>
      
    </el-dialog>
    <el-card class="admin-header">
    
      <img src="../../assets/logo.jpg" alt="" width="35px" style="float: left;margin-top:-11px;" >
    
    <span style="font-size: 25px;font-weight: bold;position:absolute;left: 77px">住院管理系统</span>
    <span style="position:absolute;right: 115px;cursor:default">
      {{date |format}}
      <i class="el-icon-user-solid" style="font-size:20px;margin-right:5px;"></i>{{username}}</span>
    <el-dropdown style="position:absolute;right: 65px;font-size:30px;" @command="handleCommand">
       <i class="el-icon-setting el-icon-switch-button" ></i>
        <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="resetPassword">修改密码</el-dropdown-item>
        <el-dropdown-item  command="handleFullScreen" >{{fullscreen?`取消全屏`:`全屏`}}</el-dropdown-item>
        
      </el-dropdown-menu>
    </el-dropdown>   
    <el-tooltip effect="dark" content="退出登录" placement="bottom">
      <i class="el-icon-switch-button" v-on:click="logout" style="font-size: 30px;float: right;"></i>
    </el-tooltip>
  </el-card>
</div>
  
</template>

<script>
  import {createRouter} from '../../router'
  import {reqLogout,reqResetPass} from '@/api'
  import {formatDate} from '@/util'
  export default {
    name: 'Header',
    data(){
       var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm2.checkPass !== '') {
            this.$refs.ruleForm2.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm2.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
        var checkPassword = (rule, value, callback) =>{
          // if (value === '') {
          //callback(new Error('请再次输入密码'));
       // } else {
         this.$axios.post('/checkUser',{
           u_username:this.username,
           u_password:value
         }).then(resp => {
            if (resp.data.code === 200) {
               callback()      
              }else{
                callback(new Error(resp.data.message));
              }                      
          })
                    
       // }
      }
        
      return {
        date:new Date(),
        username: this.$store.state.user.username,
        fullscreen: false,
        dialogFormVisible: false,
         ruleForm2: {
          pass: '',
          checkPass: '',
          u_password: ''
        },
        rules2: {
          u_password:[
            {validator:checkPassword,trigger:'blur'}
          ],
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
         
        }
  
      }
      
    },
    filters:{
      format(value){
        return formatDate(value,true)
      }
    },
     mounted() {
    //创建定时器更新最新的时间
    var _this = this;
    this.timeId = setInterval(function() {
      _this.date =new Date()
    }, 1000);
  },
  beforeDestroy: function() {
    //实例销毁前青出于定时器
    if (this.timeId) {
      clearInterval(this.timeId);
    }
  },
    methods: {

     submitForm(formName) {
       const newpass = this.ruleForm2.checkPass
        this.$refs[formName].validate(async(valid) => {
          if (valid) {
            const user = {
              u_username : this.username,
              u_password : newpass
            }
            const result = await reqResetPass(user)
            if(result && result.status === 200){
              this.$alert("密码修改成功！")
            }
          } else {
            this.$alert('输入不正确，请重新输入！');
            return false;
          }
        });
         this.dialogFormVisible = false
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
    

      async logout() {
         var _this = this
         const result = await  reqLogout()
         if (result.data.code === 200) {
            _this.$store.commit('logout')
            _this.$router.replace('/login')
            // 清空路由，防止路由重复加载
            const newRouter = createRouter()
            _this.$router.matcher = newRouter.matcher
          }
      },
      /* logout () {
        var _this = this
        this.$axios.get('/logout').then(resp => {
          if (resp.data.code === 200) {
            _this.$store.commit('logout')
            _this.$router.replace('/login')
            // 清空路由，防止路由重复加载
            const newRouter = createRouter()
            _this.$router.matcher = newRouter.matcher
          }
        }).catch(failResponse => {})
      }, */
       // 下拉菜单选择事件 
        handleCommand(command) {
          //全屏
            if (command == 'handleFullScreen') {
               let element = document.documentElement;
            if (this.fullscreen) {
                if (document.exitFullscreen) {
                    document.exitFullscreen();
                } else if (document.webkitCancelFullScreen) {
                    document.webkitCancelFullScreen();
                } else if (document.mozCancelFullScreen) {
                    document.mozCancelFullScreen();
                } else if (document.msExitFullscreen) {
                    document.msExitFullscreen();
                }
            } else {
                if (element.requestFullscreen) {
                    element.requestFullscreen();
                } else if (element.webkitRequestFullScreen) {
                    element.webkitRequestFullScreen();
                } else if (element.mozRequestFullScreen) {
                    element.mozRequestFullScreen();
                } else if (element.msRequestFullscreen) {
                    // IE11
                    element.msRequestFullscreen();
                }
            }
            this.fullscreen = !this.fullscreen;
            }
            if(command == 'resetPassword'){
              this.resetPassword()
            }
        },
        resetPassword(){
           this.dialogFormVisible = true
          
        }
      
    }
  }
</script>

<style scoped>
  .admin-header {
    height: 55px;
    opacity: 0.85;
    line-height: 12px;
    min-width: 900px;
    background-color: #fcfcfc;
  }
  .el-icon-switch-button {
    cursor: pointer;
    outline:0;
    
  }
  
</style>
