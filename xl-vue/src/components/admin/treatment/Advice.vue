<template>
  <div >
    <el-dialog
      title="查看医嘱信息"
      :visible.sync="dialogFormVisible">
      <el-form :model="selectedAdvice" label-position="center"
               label-width="0px">
        <el-form-item label="医嘱期效" label-width="120px" prop="advice_type">
          {{selectedAdvice.advice_type}}
             
        </el-form-item>
        <el-form-item label="入院id" label-width="120px" prop="reg_id">
         {{selectedAdvice.reg_id}}
        </el-form-item>
        <el-form-item label="患者姓名" label-width="120px" prop="reg_name">
          {{selectedAdvice.reg_name}}
        </el-form-item>
        <el-form-item label="诊断代码" label-width="120px" prop="diagnostic_code">
          <template>
            <el-select disabled  v-model="selectedAdvice.diagnostic_code" placeholder="请选择">
              <el-option
                  v-for="item in adviceCode"
                  :key="item.value"
                  :label="item.label+'('+item.value+')'"
                  :value="item.value">
                </el-option>
          </el-select>
          </template>
        </el-form-item>      
        <el-form-item label="详细医嘱" label-width="120px" prop="advice_detail">
          <template>
            <el-input
                        type="textarea"
                        autosize
                        disabled
                        placeholder="请输入内容"
                        v-model="selectedAdvice.advice_detail">
                        </el-input>
          </template>
        </el-form-item>
        <el-form-item label="开始时间" label-width="120px" prop="create_time">
          <template>
            <el-date-picker
              v-model="selectedAdvice.create_time"
              value-format="yyyy-MM-dd"
              disabled
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </template>
        </el-form-item>
        <el-form-item label="结束时间" label-width="120px" prop="end_time">
          <template>
            <el-date-picker
              v-model="selectedAdvice.end_time"
              value-format="yyyy-MM-dd"
              disabled
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </template>
        </el-form-item>
         <el-form-item label="开嘱医生" label-width="120px" prop="staff_id">
        {{selectedAdvice.staff_id}}
        </el-form-item>
      </el-form>
    </el-dialog>
<el-row style="display:inline-block;margin-top:10px; ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>医嘱护理管理</el-breadcrumb-item>
        <el-breadcrumb-item>医嘱管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <CreateAdvice @onSubmit="listAdvices()" ></CreateAdvice>
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
        :data="advices"
        stripe
        style="width: 100%"
        :max-height="tableHeight">

         <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="医嘱内容:" >
            <span>{{ props.row.advice_detail }}</span>
            </el-form-item>
            <el-form-item label="开嘱医生:">
            <span>{{ props.row.staff_id }}</span>
            </el-form-item>
            
                       
          </el-row>
        </el-form>
      </template>
    </el-table-column>
         <el-table-column
          prop="advice_type"
          label="期效"
          :filters="[{ text: '长期', value: '长期' }, { text: '短期', value: '短期' }]"
          filter-placement="bottom-end"
          :filter-method="filterTag"
          fit>
          <template slot-scope="scope">
        <el-tag
          :type="scope.row.advice_type === '长期' ? 'primary' : 'success'"
          disable-transitions>{{scope.row.advice_type}}</el-tag>
      </template>
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
          prop="diagnostic_code"
          label="诊断代码"
          fit>
        </el-table-column>
        <el-table-column
          prop="create_time"
          label="开始时间" 
          fit>
          <template slot-scope="scope">
            <span>{{scope.row.create_time | dateformat }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="end_time"
          label="结束时间" 
          fit>
          <template slot-scope="scope">
            <span>{{scope.row.end_time | dateformat }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          fit>
          <template slot-scope="scope">
           
            <el-button
              type="text"
              size="small"
              @click="detailAdvice(scope.row)">
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
import { reqAdviceList } from "@/api";
import {defaultData} from '@/util/data.js'
import CreateAdvice from './CreateAdvice';
export default {
      name: 'Advice',
      components: {CreateAdvice},
      data () {
          return {
            adviceCode:defaultData.advice,
            advices: [],
            data:[],
            dialogFormVisible: false,
            selectedAdvice: [],     
            searchData:'',
            limit: 5,
            total: null,
            page: 1,
            pageshow:true,
            
          }
      },
       filters: {
        dateformat (value) {
          return value.substring(0, 10);
        }
      },
      created() {
        this.listAdvices()
      },
      
      computed: {
        tableHeight () {
          return window.innerHeight - 150
        },
        
      },
      methods:
      {
        filterTag(value, row) {
        return row.advice_type === value;
      },
        async listAdvices () {
        var _this = this
        const result = await reqAdviceList();
         if (result && result.status === 200) {
              _this.data = result.data
              this.getList();
            }
      },
        
        // 处理数据
        getList() {
          let advices;
                //  es6过滤得到满足搜索条件的展示数据list
                if(this.searchData === ''){
                     advices = this.data
                }else{
                     advices = this.data.filter((item, index) =>
                   item.reg_name.includes(this.searchData)             
                )
                }
                 //过滤分页
                this.advices = advices.filter((item, index) =>
                    index < this.page * this.limit && index >= this.limit * (this.page - 1)
                )
                //分页的总数据
                this.total = advices.length
               
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
         detailAdvice (advices) {
          this.dialogFormVisible = true
          this.selectedAdvice = advices
        },
       
       
        
      },  
      
    
}
</script>

<style scoped >
.demo-table-expand {
    font-size: 0;
  }
.demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 51%;
  }
</style>
