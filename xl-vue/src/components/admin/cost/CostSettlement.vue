<template>
  <div >
    <el-dialog
      title="费用结算清单"
      :visible.sync="dialogFormVisible2">
       <div id = "printTest2">
         <table  class="tb" border= "0" width="95%" style="margin:0 auto;border-collapse: collapse;">
               <caption align="top" 
               style="font-size:18px;margin-bottom: 6px;font-weight: bold;letter-spacing: 2px;"
               >患者住院费用结算清单</caption>
               <thead style="font-size:14px;">                      
                       <tr style="text-align:left">
                           <th width="20%">姓名：</th>  
                           <th width="30%">{{this.settlement.reg_name}}</th>                         
                           <th width="20%">住院号：</th>
                           <th whith="30%">{{this.settlement.reg_id}}</th>
                           
                       </tr>
                       <tr style="text-align:left">
                           <th width="20%">性别：</th>
                           <th width="30%">{{this.settlement.reg_sex}}</th>
                           <th width="20%">住院科室：</th>
                           <th width="30%">{{this.settlement.dep_name}}</th>
                       </tr>
                       <tr style="text-align:left">
                           <th colspan="4">住院从 &nbsp;{{this.settlement.create_time}}&nbsp; 至 &nbsp;{{this.settlement.end_time}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共 &nbsp;{{this.settlement.days}} &nbsp;天</th>
                          
                       </tr>
             </thead>
             <tr>
                    
                    <td colspan="2">项目</td>
                    <td colspan="2">金额(元)</td>
                </tr>
             <tbody  v-for="info in this.settlement.costDetails" :key="info.exp_id">
                <tr>
                    
                    <td colspan="2">{{info.charge_code}}</td>
                    <td colspan="2">{{info.charge_amount}}</td>
                </tr>
   
 
             </tbody>  
             <tbody class="white">
               <tr>
                 <td colspan="4" style="text-align:right;">总金额：{{this.settlement.cost_amount}} 元 </td>
               </tr>
               <tr >
                 <td colspan="4">预缴金额:{{this.settlement.pre_payment}} 元 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   现金退款:{{this.settlement.out_amount}} 元 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   应补金额:{{this.settlement.supply_amount}} 元 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 </td>
                 
               </tr>
               <tr >
                 <td colspan="2">操作员：{{this.selectCost.cost_operator}}</td>
                 <td colspan="2">{{this.selectCost.create_time}}</td>
               </tr>
             </tbody>
    </table>  
       </div>
       <el-button type="primary" @click="dialogFormVisible2 = false">返回</el-button>
      <el-button type="success" v-print="'#printTest2'">打印</el-button>
    </el-dialog>
    <el-dialog
      title="费用结算"
      :visible.sync="dialogFormVisible">
      <el-form v-model="selectCost" style="text-align: left" >
        <el-form-item label="编号:" label-width="120px" >
          {{selectCost.cost_id}}
        </el-form-item>
        <el-form-item label="住院号:" label-width="120px" >
         {{selectCost.reg_id}}
        </el-form-item>
        <el-form-item label="姓名:" label-width="120px" >
        {{selectCost.reg_name}}
        </el-form-item>
        <el-form-item label="结算时间:" label-width="120px" >
          <template>
            <el-date-picker
              v-model="selectCost.create_time"
              value-format="yyyy-MM-dd HH:mm:ss"
              type="datetime"
              placeholder="选择日期时间">
            </el-date-picker>
          </template>
        </el-form-item>
        <el-form-item label="操作员:" label-width="120px" >
          <span>{{selectCost.cost_operator}}</span>
        </el-form-item>
      </el-form>
      <el-button type="primary" @click="statement">结算清单</el-button>
      <el-button type="primary" @click="done">已结算</el-button>
    </el-dialog>
<el-row style="display:inline-block;margin-top:10px; ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>费用管理</el-breadcrumb-item>
        <el-breadcrumb-item>费用结算</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-row style="margin-right:5px;float:right;margin-bottom:5px; ">
            <el-col :span="18" style="margin-right:3px;">
                <el-input v-model="searchData" placeholder="输入患者名查询" @keyup.enter.native="search">></el-input>
            </el-col>
            <el-col :span="2">
                <el-button type="primary" @click="search">查询</el-button>
            </el-col>
        </el-row>
        
    <el-card  style="width: 98%;height:auto;">
      <el-table
        :data="cost"
        stripe
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          prop="cost_id"
          label="费用id"
          sortable
          >
        </el-table-column>
         <el-table-column
          prop="reg_id"
          label="患者id"
          sortable
          >
        </el-table-column>
        
        <el-table-column
          prop="reg_name"
          label="患者姓名"
          fit>
        </el-table-column>
       
        <el-table-column
          label="操作"
          fit>
          <template slot-scope="scope">
           
            <el-button
              type="text"
              size="small"
              @click="detailCost(scope.row)">
              费用明细
            </el-button>
             <el-button
              type="text"
              size="small"
              @click="costSettlement(scope.row)">
              费用结算
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination v-if="pageshow" style="float:right;margin: 10px 0;"
      @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page"
            :page-sizes="[1, 2,5, 10]" :page-size="limit" layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
    </el-card>
  </div>
</template>

<script>
import {reqCostList,reqQueryUname,reqQuerySettlement,reqUpdateCost} from '@/api';
export default {
    name: 'CostSettlement',
      components: {},
      data () {
          return {
            dialogFormVisible:false,
            dialogFormVisible2:false,
            cost: [],
            data:[],
            searchData:'',
            selectCost:[],
            limit: 5,
            total: null,
            page: 1,
            pageshow:true,
            settlement:[],
            
          }
      },
       created() {
        this.listCost()
        
      },
      
      computed: {
        tableHeight () {
          return window.innerHeight - 150
        },
        
      },
      methods:{
        async done(){
          const done = {
            reg_id:this.settlement.reg_id,
            cost_amount:this.settlement.cost_amount,
            cost_operator:this.selectCost.cost_operator,
            create_time:this.selectCost.create_time,
            cost_type:'已结算'
          }
          const result = await reqUpdateCost(done)
          if(result && result.status === 200){
            this.$message({
                    message: '结算成功！',
                    type: 'success'
                    })
          }else{
            this.$message.error('结算失败！');
          }
          this.dialogFormVisible = false
        },
        async statement(){
           this.dialogFormVisible2 = true;
           const reg_id = this.selectCost.reg_id
           const end_time = this.selectCost.create_time
           const result  = await reqQuerySettlement(reg_id,end_time)
           if(result && result.status === 200){
             this.settlement = result.data
           }
        },
        async costSettlement(value){
              this.dialogFormVisible = true;
              this.selectCost = value;
              const u_name = this.$store.state.user.username
              const result = await reqQueryUname(u_name)
              if(result && result.status ===200){
                this.selectCost.cost_operator = result.data.u_name
              }
              
            },
          detailCost(c){
            let reg_id = c.reg_id
               this.$router.push({path:'/admin/cost/costDetail',query: {reg_id:reg_id}})
          },
          async listCost () {
        var _this = this
        const result = await reqCostList();
         if (result && result.status === 200) {
              _this.data = result.data
              this.getList();
            }
      },
        
        // 处理数据
        getList() {
          let cost;
                //  es6过滤得到满足搜索条件的展示数据list
                if(this.searchData === ''){
                     cost = this.data
                }else{
                     cost = this.data.filter((item, index) =>
                   item.reg_name.includes(this.searchData)             
                )
                }
                 //过滤分页
                this.cost = cost.filter((item, index) =>
                    index < this.page * this.limit && index >= this.limit * (this.page - 1)
                )
                //分页的总数据
                this.total = cost.length
               
            },
             // 当每页数量改变
            handleSizeChange(val) {
                //console.log(`每页 ${val} 条`);
                this.limit = val
                this.getList()
              },
               // 当每页数量改变
              handleCurrentChange(val) {
                //onsole.log(`当前页: ${val}`);
                this.page = val
                this.getList()
            },
           
             // 搜索过滤数据
            search() {
                 this.page = 1
                this.pageshow = false
                this.getList()
                this.$nextTick(() => {
                    this.pageshow = true
                })
            },
            
      }
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
  .white td{
    border: 0px solid black;
    font-size: 14px;
    font-weight: bold;
    text-align: left;
  }
</style>
