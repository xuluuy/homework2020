<template>
  <div class="page">
<el-row style="display:inline-block;margin-top:10px; ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>出入院管理</el-breadcrumb-item>
        <el-breadcrumb-item>档案明细</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
       <el-card  style="width: 98%;height:auto;">
             <h3 class="login_title">档案明细</h3>
             <el-row>
                <el-col :span="12">
                     <span class="label">入院编号:</span> 
                    {{this.reg_id}}
                    </el-form-item>
                </el-col>
               
             </el-row>
            <el-divider content-position="left" ><span style=" font-weight: bold;">入院信息</span> </el-divider>
            <el-row style="margin:0 0 10px 10px;">
                <el-col :span="8">
                     <span class="label">姓名:</span>              
                     {{this.archives.regs.reg_name}}
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                     <span class="label">身份证:</span>              
                     {{this.archives.regs.reg_identity}}
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                     <span class="label">预缴费:</span>              
                     {{this.archives.regs.pre_amount}}元
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row style="margin:0 0 10px 10px;">
                <el-col :span="8">
                     <span class="label">收治科室:</span>              
                     {{this.archives.regs.dep_name}}
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                     <span class="label">床位号:</span>              
                     {{this.archives.regs.bed_name}}
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                     <span class="label">主治医生:</span>              
                     {{this.archives.regs.doc_name}}
                    </el-form-item>
                </el-col>
            </el-row>
            <el-divider content-position="left" ><span style=" font-weight: bold;">医嘱信息</span> </el-divider>
            <table class="white" border= "0" width="95%" style="margin:0 auto;border-collapse: collapse;">
                <tbody v-for="advice in this.archives.advice" :key="advice.advice_id">
                   <tr>
                       <td>{{advice.advice_type}}</td>
                       <td>{{advice.advice_detail}}</td>
                   </tr>
                </tbody>
            </table>
             <el-divider content-position="left" ><span style=" font-weight: bold;">药品信息</span> </el-divider>
              <table class="white" border= "0" width="95%" style="margin:0 auto;border-collapse: collapse;">
                <tbody v-for="drug in this.archives.drugs" :key="drug.drug_id">
                   <tr>
                       <td>{{drug.drug_name}}</td>
                       <td>{{drug.drug_num}}份</td>
                       <td>{{drug.drug_price}}元</td>
                   </tr>
                </tbody>
            </table>
       </el-card>
  
  </div>
</template>

<script>
import {reqQueryArchivesDetail} from '@/api'
export default {
    name: 'ArchivesDetail',
      components: {},
      data () {
          return {
            archives:[],  
            reg_id:'',
          }
      },
      created(){
        this.reg_id = this.$route.query.reg_id
        this.getReg_id()
      },
      methods:{
           getReg_id(){        
              if (typeof this.reg_id === 'undefined') {
                this.$axios
                .post('/cost/query/regId', {
                   u_username:this.$store.state.user.username
                })
                .then(successResponse => {
                    this.reg_id = successResponse.data.reg_id
                })
              }

                
                setTimeout(() => {
                    this.listArchivesDetail()
                }, 1000);
              
          }, 
         async listArchivesDetail(){
              const result = await reqQueryArchivesDetail(this.reg_id)
              if(result && result.status ===200){
                  this.archives = result.data
              }
          }
      },
}
</script>

<style >
.login_title {
    margin: 0px auto 10px auto;
    text-align: center;
    color: #505458;
    
  }
  .label{
      color: #505458;
  }
.white td{
    border: 0px solid black;
    font-size: 14px;
    text-align: left;
  }
</style>
