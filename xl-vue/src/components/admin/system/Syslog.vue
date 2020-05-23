<template>
  <div>
    <el-row style="display:inline-block;margin-top:10px; ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>系统管理</el-breadcrumb-item>
        <el-breadcrumb-item>日志信息</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-header style="margin-top:10px;" >
       <el-form :inline="true" :model="formInline" class="demo-form-inline" size="mini">
         <el-form-item label="访问时间">
            <el-date-picker class="dateStyle" v-model="formInline.beginTime" value-format="yyyy-MM-dd HH:mm:ss" @change="dataFormat" type="datetime" placeholder="选择日期时间"></el-date-picker>
            至&nbsp;<el-date-picker class="dateStyle" v-model="formInline.endTime" value-format="yyyy-MM-dd HH:mm:ss" @change="dataFormat" type="datetime" placeholder="选择日期时间"></el-date-picker>
          </el-form-item>
          <el-form-item>
           <el-button type="primary" class="btn" @click="onSubmit"><i class="el-icon-search"></i>查询</el-button>
         </el-form-item>
         <el-form-item>
           <el-button type="danger" class="btn" @click="resetting"><i class="el-icon-refresh"></i>重置</el-button>
         </el-form-item>
       </el-form>
     </el-header>
    <el-card style="width: 98%;">
      <el-table v-loading="loading" :data="tableData"  stripe style="width: 98%"
        :max-height="tableHeight">
        <el-table-column align="center" label="访问时间" prop="ACCESSDATE" fit></el-table-column>
        <el-table-column align="center" label="请求方式" prop="REQUESTTYPE" fit></el-table-column>
        <el-table-column align="center" label="请求接口" prop="ACCESSINTERFACE" fit></el-table-column>
        <el-table-column header-align="center" align="left" label="接口参数" width="90">
          <template slot-scope="scope" v-if="scope.row.INTERFACEPARAMS">
            <el-popover v-if="scope.row.INTERFACEPARAMS.length>10" placement="left-end" width="200" trigger="hover"
              :content="scope.row.INTERFACEPARAMS">
              <span slot="reference" style="text-overflow: ellipsis;white-space:nowrap;overflow: hidden;">{{scope.row.INTERFACEPARAMS}}</span>
            </el-popover>
            <span v-else>{{scope.row.INTERFACEPARAMS}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="访问IP" prop="ACCESSIP" width="130"></el-table-column>
      </el-table>
      <el-footer>
      <div style="padding: 15px 0;text-align: right;">
        <el-pagination  @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="formInline.pageNum"
          :page-sizes="pageSizes" :page-size="formInline.pageSize" layout="total, sizes, prev, pager, next, jumper"
          :total="pageTotal">
        </el-pagination>
      </div>
    </el-footer>
    </el-card>
    

  </div>
</template>

<script>
import {formatDate} from '@/util'
import {reqsysLogList} from '@/api'
  export default {
    data() {
      return {
        tableData: [],
        loading: false,
        formInline: {
          pageNum: 1,
          pageSize: 10,
          beginTime: new Date(new Date().getTime()-3600*1000*24*7),
          endTime: new Date()
        },
        pageSizes: [5, 10, 15, 20],
        pageTotal: 0
      }
    },
    created() {
      this.formInline.beginTime=formatDate(this.formInline.beginTime,true);
      this.formInline.endTime=formatDate(this.formInline.endTime,true);
      this.getData();
    },
    computed: {
        tableHeight () {
          return window.innerHeight - 150
        }
      },
    methods: {
     async getData() {
        this.loading = true
        const data = this.formInline
        const result  = await reqsysLogList(data)
         if(result && result.status === 200){
            this.loading = false;
            this.tableData = result.data.data.rows;
            this.pageTotal = result.data.data.total;
          }
      },
     onSubmit() {//查询
        this.getData();
      },
      resetting() {//重置
        this.formInline.beginTime=formatDate(new Date(new Date().getTime()-3600*1000*24*7),true);
        this.formInline.endTime=formatDate(new Date(),true);
        this.getData();
      },
       dataFormat(val){
        console.log(val);
        
      },
      handleSizeChange(val) {
        //console.log(`每页 ${val} 条`);
        this.formInline.pageSize = val;
        this.formInline.pageNum = 1;
        this.getData();
      },
      handleCurrentChange(val) {
        //console.log(`当前页: ${val}`);
        this.formInline.pageNum = val;
        this.getData();
      }
    }
  }
</script>

<style scoped>

</style>


