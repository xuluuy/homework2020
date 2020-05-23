<template>
  <div >
      <el-dialog
      title="住院预缴费凭据打印"
      :visible.sync="dialogFormVisible">
      <div id="printTest2" style="margin-bottom:20px;">
          <table class="tb" border= "0" width="95%" style="margin:0 auto;border-collapse: collapse;">
               <caption align="top" 
               style="font-size:18px;margin-bottom: 6px;font-weight: bold;letter-spacing: 2px;"
               >XX医院<span style="font-size:20px;">住院</span>预缴金收款凭据</caption>
              <thead>                      
                       <tr style="text-align:left">
                           <th width="10%">日期：</th>  
                           <th width="20%">{{regs.create_time.substring(0, 10)}}</th>                         
                           <th width="10%">单位：</th>
                           <th whith="20%">元</th>
                           <th width="10%">No</th>
                           <th width="26%">{{regs.pre_id}}</th>
                       </tr>
             </thead>
             <tbody>
                 <tr>
                     <td>医保卡号</td>
                     <td>{{regs.carte_vital}}</td>
                     <td>住院号</td>
                     <td>{{regs.reg_id}}</td>
                     <td>姓名</td>
                     <td>{{regs.reg_name}}</td>
                 </tr>
                 <tr>
                     <td colspan="2">预缴金额</td>
                     <td>人民币小写</td>
                     <td>￥{{regs.pre_amount}}</td>
                     <td>人民币大写</td>
                     <td>{{upmount}}</td>
                 </tr>
                 <tr>
                     <td rowspan="3">说明</td>
                     <td rowspan="3" colspan="5" style="text-align:left;padding-left:3px;">
                         1.请当面核对金额，离柜概不负责。<br>
                         2.本收据请妥善保存，出院时凭此据结算，多退少补。<br>
                         3.本凭据并非正式收据，不得作为报销凭据。
                     </td>
                 </tr>
                 <tr></tr>
                 <tr></tr>
             </tbody>

          </table>  
          <el-divider ></el-divider>
          <div style="width:100%;text-align:center;item-align:center">
              <h4>住院信息查询</h4>
              <span>请登录微信小程序并扫描下方二维码</span><br>
              <div id="qrcode"  style="display:inline-block;margin-top:30px;"></div> 
          </div> 
          
      </div>
      <el-button type="primary" @click="dialogFormVisible = false">返回</el-button>
      <el-button type="success" v-print="'#printTest2'">打印</el-button>
      
    </el-dialog>
  <el-row style="display:inline-block;margin-top:10px;margin-bottom:5px; ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>出入院管理</el-breadcrumb-item>
        <el-breadcrumb-item>入院登记</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-form :model="regs" :rules="rules" label-position="right" ref="regs"
               label-width="100px" class="form_border">
        <h3 class="login_title">入院登记</h3>
        <el-form-item label="RegId:">
        {{regs.reg_id}}
        </el-form-item>
         <el-row>
                <el-col :span="8">
                    <el-form-item label="用户账号" prop="patient_uname">              
                        <el-input v-model="regs.patient_uname" ></el-input> 
                        <bulk-registration :flag = true  ></bulk-registration>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="姓名" prop="reg_name">
                        <el-input v-model="regs.reg_name"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="身份证" prop="reg_identity">
                        <el-input v-model="regs.reg_identity"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label="性别">              
                       <template>
                            <el-radio v-model="regs.reg_sex" label="男">男</el-radio>
                            <el-radio v-model="regs.reg_sex" label="女">女</el-radio>
                        </template> 
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="年龄">
                        <el-input v-model="regs.reg_age"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="出生日期">
                        <el-input v-model="regs.reg_birth"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label="联系电话">
                        <el-input v-model="regs.reg_phone"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="16">
                    <el-form-item label="工作地址">
                        <el-input v-model="regs.reg_workplace"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label="医保卡号" prop="carte_vital">
                        <el-input v-model="regs.carte_vital"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="收治科室">
                       <template>
              <el-select v-model="regs.dep_name" placeholder="请选择">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
          </template>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="入院时间">
                        <el-input disabled v-model="regs.create_time"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="10">
                    <el-form-item label="入院诊断">
                        <el-input
                        type="textarea"
                        autosize
                        placeholder="请输入内容"
                        v-model="regs.condition">
                        </el-input>
                    </el-form-item>
                </el-col>
                 <el-col :span="8">
                  <el-form-item label="预缴金">
                      <el-input v-model="regs.pre_amount" @blur="Upcase"></el-input>
                  </el-form-item>
                 </el-col>
                <el-col :span="6">
                    <el-form-item >
                        <template >
                            <el-button
                            @click="PreMount()"
                            type="warning"
                            >
                            打印预缴凭据
                            </el-button>
                        </template>    
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="6">
                    <el-form-item label="操作员">
                        <el-input disabled v-model="regs.OPERATOR_uname"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="18">
                    <el-button type="primary" style="left:-50%;margin-left:50%" @click="submitForm('regs')">提交</el-button>
                </el-col>
            </el-row>
            
    </el-form>           
  </div>
</template>

<script>
import {reqCreateRegId,reqQueryUserByUname,reqPreId,reqCreateReg,reqCreatePre} from '@/api'
import BulkRegistration from '@/components/admin/user/BulkRegistration';
import {defaultData} from '@/util/data.js'
import {formatDate} from '@/util'
import  QRCode from 'qrcodejs2'
export default {
    name: 'Registration',
    components: {BulkRegistration},
    data(){
        return {
        dialogFormVisible: false,
        unit: new Array("仟", "佰", "拾", "", "仟", "佰", "拾", "", "角", "分"),
        upmount:'',
        regs:{    //登记表为insert 预缴金表为insert
            reg_id:'',
            patient_uanme:'',
            patient_id:'', //要插入数据u_id
            reg_name:'',
            reg_identity:'',
            reg_sex:'',
            reg_age:'',
            reg_birth:'',
            reg_phone:'',
            reg_workplace:'',
            carte_vital:'',
            dep_name:'',
            create_time: formatDate(new Date(),true),
            condition:'',
            pre_amount:'',
            OPERATOR_uname:this.$store.state.user.username,
            pre_id:'', //预缴费id
        },
        
        rules:{
            reg_identity: [
              { required: true, message: '身份证号不能为空', trigger: 'blur' },
               { validator: this.validID, trigger: 'blur' }
           ],
           reg_name:[{ required: true, message: '姓名不能为空', trigger: 'blur' }],
           patient_uname: [
              { required: true, message: '患者账号不能为空', trigger: 'blur' },
               { validator: this.queryPatient, trigger: 'blur' }
           ],
           carte_vital:[{ required: true, message: '医保卡号不能为空', trigger: 'blur' }],
        },
        options: defaultData.options,    
        }
    },
    created() {
     this.createId()
     this.preId()
    },
    methods:{
        qrcode(){
            let _this = this
            let code = _this.regs.reg_id
            let qrcode = new QRCode('qrcode',{
                width:124,
                height:124,
                //text:code, // text: `http://localhost:8080/testUrl/myGrades?uid=${this.userInfo.uid}`,
                text: `${this.regs.reg_id}`,
            })
        },
        submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.addReg();
          } else {
           this.$alert('校验不通过，请重新输入！')
            return false;
          }
        });
      },
     async addReg(){
          const registrations = {
            reg_id :this.regs.reg_id,
            patient_id :this.regs.patient_id,
            reg_name :this.regs.reg_name,
            reg_sex :this.regs.reg_sex,
            reg_age :this.regs.reg_age,
            reg_birth :this.regs.reg_birth,
            reg_identity :this.regs.reg_identity,
            reg_workplace :this.regs.reg_workplace,
            reg_phone :this.regs.reg_phone,
            carte_vital :this.regs.carte_vital,
            pre_amount :this.regs.pre_amount,
            dep_name :this.regs.dep_name,
            operator_uname :this.regs.OPERATOR_uname,
            condition :this.regs.condition,
            out_flag :'0',
            create_time :this.regs.create_time
          } 
          
          const pre_payment = {
            pre_id :this.regs.pre_id,
            reg_id :this.regs.reg_id,
            pre_amount:this.regs.pre_amount,
            operator_uname :this.regs.OPERATOR_uname,
            create_time :this.regs.create_time
          }
          const resultReg = await reqCreateReg(registrations)
          const resultPre = await reqCreatePre(pre_payment)
          if(resultReg && resultReg.status === 200 && resultPre && resultPre.status ===200){
              this.$alert('登记成功', '提示', {
                  confirmButtonText: '确定'
                })
              this.$router.replace({path:'/admin/inout/inhospital'});  
          }
      },
        //预缴费打印
      PreMount(){
          this.dialogFormVisible = true
          //document.getElementById("qrcode").innerHTML = ''
          this.$nextTick(function () {
              this.qrcode()
          })
      },  
      async createId(){
          var _this = this
          const result = await reqCreateRegId()
          if(result && result.status === 200){
                _this.regs.reg_id = result.data
          }
      },
      async preId(){
          var _this = this
          const result = await reqPreId()
          if(result && result.status === 200){
                _this.regs.pre_id = result.data
          }
      },
      async queryPatient(rule,value,callback){
           var _this = this
           const u_username = value
           const result = await reqQueryUserByUname(u_username)
           if(result && result.status === 200){
               _this.regs.reg_name = result.data.u_name
               _this.regs.reg_phone = result.data.u_phone
               _this.regs.patient_id = result.data.u_id
               callback()
           }
      },
      
      // 身份证验证
      async validID(rule,value,callback)
      {
        // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X 
        let reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (reg.test(value)) {
          await this.go(value.length);
          callback()
        } else {
          callback(new Error('身份证号码不正确'))
        }
      },
      
      // 实现自动生成生日，性别，年龄
      go(val) {
        let iden = this.regs.reg_identity;
        let sex = null;
        let birth = null;
        let myDate = new Date();
        let month = myDate.getMonth() + 1;
        let day = myDate.getDate();
        let age = 0;
 
        if(val===18){
          age = myDate.getFullYear() - iden.substring(6, 10) - 1;
          sex = iden.substring(16,17);
          birth = iden.substring(6,10)+"-"+iden.substring(10,12)+"-"+iden.substring(12,14);
          if (iden.substring(10, 12) < month || iden.substring(10, 12) == month && iden.substring(12, 14) <= day) age++;
 
        }
        if(val===15){
          age = myDate.getFullYear() - iden.substring(6, 8) - 1901;
          sex = iden.substring(13,14);
          birth = "19"+iden.substring(6,8)+"-"+iden.substring(8,10)+"-"+iden.substring(10,12);
          if (iden.substring(8, 10) < month || iden.substring(8, 10) == month && iden.substring(10, 12) <= day) age++;
        }
 
        if(sex%2 === 0)
          sex = '女';
        else
          sex = '男';
        this.regs.reg_sex = sex;
        this.regs.reg_age = age;
        this.regs.reg_birth = birth;
        
      },
      //预缴金大写
      Upcase(){
          let m = this.regs.pre_amount
           m *= 100;
           m += "";
            var length = m.length;
 
            var result = "";
            for (var i = 0; i < length; i++) {
                    if (i == 2) {
                        result = "元" + result;
                    } else if (i == 6) {
                        result = "万" + result;
                    }
                    if (m.charAt(length - i - 1) == 0) {
                        if (i != 0 && i != 1) {
                            if (result.charAt(0) != '零' && result.charAt(0) != '元' && result.charAt(0) != '万') {
                                result = "零" + result;
                            }
                        }
                        continue;
                    }
                    result = this.toDx(m.charAt(length - i - 1)) + this.unit[this.unit.length - i - 1] + result;
                }
                result += result.charAt(result.length - 1) == '元' ? "整" : "";
                this.upmount = result;
      },
      toDx(n) {   //阿拉伯数字转换函数
                switch (n) {
                    case "0":
                        return "零";
                    case "1":
                        return "壹";
                    case "2":
                        return "贰";
                    case "3":
                        return "叁";
                    case "4":
                        return "肆";
                    case "5":
                        return "伍";
                    case "6":
                        return "陆";
                    case "7":
                        return "柒";
                    case "8":
                        return "捌";
                    case "9":
                        return "玖";
                }
            },

    }
}
</script>

<style scoped>
.login_title {
    margin: 0px auto 10px auto;
    text-align: center;
    color: #505458;
    
  }
  .form_border {
    background: rgb(255, 255, 255);
    border-radius: 5px;
    box-shadow: 0 0 15px #cac6c6;
    padding: 10px 20px 10px 0;
    color: #505458;
  }
  .form_border .el-form-item {
      margin-bottom: 25px;
  }
  .tb td{
      text-align:center;
      border:1px solid black;
      height: 30px;
      line-height: 30px;
     
  }
</style>
