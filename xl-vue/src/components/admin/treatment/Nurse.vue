<template>
  <div>
    <el-dialog
      title="查看护理记录"
      :visible.sync="dialogFormVisible">
        <chart ref="chart1" :options="orgOptions" :auto-resize="true"></chart>
    </el-dialog>
<el-row style="display:inline-block;margin-top:10px; ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>医嘱护理管理</el-breadcrumb-item>
        <el-breadcrumb-item>护理管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <CreateNurse @onSubmit="listNurses()" ></CreateNurse>
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
        :data="record"
        stripe
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          prop="nur_id"
          label="护理id"
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
          prop="staff_id"
          label="护理人员"
          fit>
        </el-table-column>
        <el-table-column
          prop="create_time"
          label="护理时间" 
          fit>
          <template slot-scope="scope">
            <span>{{scope.row.create_time}}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          fit>
          <template slot-scope="scope">
           
            <el-button
              type="text"
              size="small"
              @click="detailRecord(scope.row)">
              查看详情
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
import CreateNurse from './CreateNurse';
import {reqNurseList,reqGetDetails} from '@/api';
export default {
   name: 'Nurse',
      components: {CreateNurse},
      data () {
          return {
            record: [],
            data:[],
            dialogFormVisible: false,
            selectedrecord: [],     
            searchData:'',
            limit: 5,
            total: null,
            page: 1,
            pageshow:true,
            orgOptions: {},
            base:'',
            dataDetail:{},
          }
      },
       
      created() {
        this.listNurses()
        
      },
      
      computed: {
        tableHeight () {
          return window.innerHeight - 150
        },
        
      },
      methods:{
        async GetDetails(){
          var _this = this
          const reg_id = this.selectedrecord.reg_id
         const result = await reqGetDetails(reg_id)
         if(result && result.status ===200){
            _this.base = JSON.stringify(result.data)
            
         }
        },
        async listNurses () {
        var _this = this
        const result = await reqNurseList();
         if (result && result.status === 200) {
              _this.data = result.data
              this.getList();
            }
      },
        
        // 处理数据
        getList() {
          let record;
                //  es6过滤得到满足搜索条件的展示数据list
                if(this.searchData === ''){
                     record = this.data
                }else{
                     record = this.data.filter((item, index) =>
                   item.reg_name.includes(this.searchData)             
                )
                }
                 //过滤分页
                this.record = record.filter((item, index) =>
                    index < this.page * this.limit && index >= this.limit * (this.page - 1)
                )
                //分页的总数据
                this.total = record.length
               
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
     async detailRecord (record) {
      this.dialogFormVisible = true
      this.selectedrecord = record

      const reg_id = this.selectedrecord.reg_id
         const result = await reqGetDetails(reg_id)
         if(result && result.status ===200){
            let str = JSON.stringify(result.data)
             console.log(str);
             let dataDetail = JSON.parse(str); 

              //echarts数据
          this.orgOptions = {
    title: {
        text: '患者护理信息'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data: ['体温', '心率', '高压','低压', '血糖']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: dataDetail.create_time
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name: '体温',
            type: 'line',
            stack: '总量',
            data: dataDetail.p_temperature
        },
        {
            name: '心率',
            type: 'line',
            stack: '总量',
            data: dataDetail.heart_rate
        },
        {
            name: '高压',
            type: 'line',
            stack: '总量',
            data: dataDetail.blood_pressure_h
        },
        {
            name: '低压',
            type: 'line',
            stack: '总量',
            data: dataDetail.blood_pressure_l
        },
        {
            name: '血糖',
            type: 'line',
            stack: '总量',
            data: dataDetail.blood_sugar
        }
    ]

    }
        }
         },

      }
}
</script>

<style >
</style>
