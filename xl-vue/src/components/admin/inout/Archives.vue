<template>
  <div >
 <el-row style="display:inline-block;margin-top:10px; ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>出入院管理</el-breadcrumb-item>
        <el-breadcrumb-item>档案管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
     <el-row style="margin-right:5px;float:right;margin-bottom:5px; ">
            <el-col :span="18" style="margin-right:3px;">
                <el-input v-model="searchData" placeholder="输入患者入院id查询">></el-input>
            </el-col>
            <el-col :span="2">
                <el-button type="primary" @click="search">查询</el-button>
            </el-col>
        </el-row>
        
    <el-card  style="width: 98%;height:auto;">
      <el-table
        :data="archs"
        stripe
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          prop="reg_name"
          label="患者姓名"
          fit>
        </el-table-column>
        <el-table-column
          prop="reg_id"
          label="入院id"
          fit>
        </el-table-column>
        <el-table-column
          prop="create_time"
          label="创建时间"
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
import {reqArchsList} from '@/api';
export default {
     name: 'Archives',
    components: {},
    data () {
      return {
        dialogFormVisible: false,        
        archs: [],
        data:[],
        selectedArch: [],              
        limit: 5,
        total: null,
        page: 1,
        pageshow:true,
        searchData:'',
      }
    },
    created () {
      this.listArchs()
    },
    
    computed: {
      tableHeight () {
        return window.innerHeight - 150
      },
     
    },
    methods: {
        async listArchs () {
        var _this = this
        const result = await reqArchsList();
         if (result && result.status === 200) {
              _this.data = result.data
              this.getList()
            }
      },
     
        // 处理数据
        getList() {
          let archs;
                //  es6过滤得到满足搜索条件的展示数据list
                if(this.searchData === ''){
                     archs = this.data
                }else{
                     archs = this.data.filter((item, index) =>
                   item.reg_id.includes(this.searchData)             
                )
                }
                 //过滤分页
                this.archs = archs.filter((item, index) =>
                    index < this.page * this.limit && index >= this.limit * (this.page - 1)
                )
                //分页的总数据
                this.total = archs.length
               
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
     detailCost(c){
            let reg_id = c.reg_id
               this.$router.push({path:'/admin/inout/archivesDetail',query: {reg_id:reg_id}})
          },
      
       
     
       

    } 
}
</script>

<style >
</style>
