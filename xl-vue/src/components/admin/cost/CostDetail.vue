<template>
  <div class="page">
<el-row style="display:inline-block;margin-top:10px; ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>费用管理</el-breadcrumb-item>
        <el-breadcrumb-item>费用明细</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-card  style="width: 98%;height:auto;">
        <div id="printTest2">
      <table  class="tb" border= "0" width="95%" style="margin:0 auto;border-collapse: collapse;">
               <caption align="top" 
               style="font-size:18px;margin-bottom: 6px;font-weight: bold;letter-spacing: 2px;"
               >患者住院费用明细清单</caption>
               <thead style="font-size:14px;">                      
                       <tr style="text-align:left">
                           <th width="10%">姓名：</th>  
                           <th width="23%">{{reg_name}}</th>                         
                           <th width="10%">住院号：</th>
                           <th whith="23%">{{reg_id}}</th>
                           <th width="10%">科室：</th>
                           <th width="23%">{{dep_name}}</th>
                       </tr>
                       <tr style="text-align:left">
                           <th >打印时间：</th>
                           <th colspan="5">{{create_time}}</th>
                       </tr>
             </thead>
             <tr>
                    <td colspan="2">编码</td>
                    <td colspan="2">项目</td>
                    <td colspan="2">金额(元)</td>
                </tr>
             <tbody  v-for="info in costDetail" :key="info.exp_id">
                <tr>
                    <td colspan="2">{{info.exp_id}}</td>
                    <td colspan="2">{{info.charge_code}}</td>
                    <td colspan="2">{{info.charge_amount}}</td>
                </tr>
   
 
             </tbody>   
    </table>  
    </div>
    <el-button type="success" v-print="'#printTest2'">打印</el-button>     
    </el-card>
  </div>
</template>

<script>
import {reqQueryId,reqQueryPatientInfo,reqQueryCostInfo} from '@/api'
import {formatDate} from '@/util'
export default {
     name: 'CostDetail',
      components: {},
      data () {
          return {
              costDetail:[],
              reg_id:'',
              reg_name:'',
              dep_name:'',
              create_time:formatDate(new Date(),true),

          }
      },
       created() {    
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
                    
                    this.listCostDetail()
                }, 1000);
              
          }, 
          async getPatientInfo(){
               const res = await reqQueryPatientInfo(this.reg_id)
            if(res && res.status === 200){
                this.reg_name = res.data.reg_name
                this.dep_name = res.data.dep_name
                this.reg_id = res.data.reg_id
            }
          },
        async getCostDetail(){
               const info = {
                create_time:this.create_time,
                reg_id:this.reg_id
            }
           const result = await reqQueryCostInfo(info);
             if(result && result.status ===200){
                this.costDetail = result.data
            }
          },
           listCostDetail(){
              
              
            //根据reg_id获得患者信息
           this.getPatientInfo()
            //根据reg_id获取费用明细
            //alert(this.create_time)
           this.getCostDetail()
           },

       },
}
</script>

<style >
 .tb td{
      text-align:center;
      border:1px solid black;
      height: 30px;
      line-height: 30px;
      font-size: 14px;
  }
</style>
