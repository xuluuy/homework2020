<template>
  <div style="text-align: left;display: inline-block;
    float:right;margin-right:23px;">
    <span v-if="flag" @click="dialogFormVisible = true" style="font-size:11px;cursor: pointer;">没有账号?</span>
    <el-button v-else class="add-button" type="primary" @click="dialogFormVisible = true">添加用户</el-button>
    <el-dialog
      title="添加用户"
      :visible.sync="dialogFormVisible"
      @close="clear"
      width="25%">
      <el-form :model="loginForm" :rules="rules" label-position="left" ref="loginForm"
               label-width="0px">
        <el-form-item prop="username">
          <el-input type="text" v-model="loginForm.username"
                    auto-complete="off" placeholder="账号"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="loginForm.password"
                    auto-complete="off" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input type="text" v-model="loginForm.name"
                    auto-complete="off" placeholder="真实姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input type="text" v-model="loginForm.phone"
                    auto-complete="off" placeholder="电话号码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input type="text" v-model="loginForm.email"
                    auto-complete="off" placeholder="E-Mail"></el-input>
        </el-form-item>
        <el-form-item>
          <el-select v-model="loginForm.type" placeholder="用户类型">
      <el-option label="员工" value="S"></el-option>
      <el-option label="患者" value="P"></el-option>
    </el-select>
        </el-form-item>
        <el-form-item style="width: 100%">
          <el-button type="primary" style="width: 40%;background: #505458;border: none" @click="submitForm('loginForm')">添加</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: 'BulkRegistration',
      data () {
        return {
          
          dialogFormVisible: false,
          rules: {
            username: [{required: true, message: '用户名不能为空', trigger: 'blur'}],
            password: [{required: true, message: '密码不能为空', trigger: 'blur'}]
          },
          loginForm: {
            username: '',
            password: '',
            name: '',
            phone: '',
            email: '',
            type:''
          }
        }
      },
      props:{
        flag:{
          type:Boolean
        }
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
        clear () {
          this.loginForm = {
            username: '',
            password: '',
            name: '',
            phone: '',
            email: '',
            type:''
          }
        },
        register () {
          this.$axios
            .post('/register', {
              u_username: this.loginForm.username,
              u_password: this.loginForm.password,
              u_name: this.loginForm.name,
              u_phone: this.loginForm.phone,
              u_email: this.loginForm.email,
              u_type:this.loginForm.type
            })
            .then(resp => {
              if (resp.data.code === 200) {
                this.$alert('注册成功', '提示', {
                  confirmButtonText: '确定'
                })
                
                this.clear()
                this.$emit('onSubmit')
              } else {
                this.$alert(resp.data.message, '提示', {
                  confirmButtonText: '确定'
                })
              }
              this.dialogFormVisible = false
            })
            .catch(failResponse => {})
        }
      }
    }
</script>

<style scoped>
 
</style>
