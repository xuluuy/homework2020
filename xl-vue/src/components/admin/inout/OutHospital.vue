<template>
  <div>
       <el-dialog
      title="出院办理"
      :visible.sync="dialogFormVisible">
      <el-form v-model="selectedOut" style="text-align: left" >
        <el-form-item label="编号:" label-width="120px" >
          {{selectedOut.out_id}}
        </el-form-item>
        <el-form-item label="住院号:" label-width="120px" >
         {{selectedOut.reg_id}}
        </el-form-item>
        <el-form-item label="姓名:" label-width="120px" >
        {{selectedOut.reg_name}}
        </el-form-item>
        <el-form-item label="费用结算:" label-width="120px" >
        {{selectedOut.is_cost}}
        </el-form-item>
        <el-form-item label="出院时间:" label-width="120px" >
          <template>
            <el-date-picker
              v-model="selectedOut.create_time"
              value-format="yyyy-MM-dd HH:mm:ss"
              type="datetime"
              placeholder="选择日期时间">
            </el-date-picker>
          </template>
        </el-form-item>
        <el-form-item label="操作员:" label-width="120px" >
          <span>{{selectedOut.out_operator}}</span>
        </el-form-item>
      </el-form>
      <el-button type="primary" @click="outHospital">出院</el-button>
    </el-dialog>
<el-row style="display:inline-block;margin-top:10px; ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>出入院管理</el-breadcrumb-item>
        <el-breadcrumb-item>出院管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-row  style="margin-right:5px;float:right;margin-bottom:5px; ">
            <el-col :span="18" style="margin-right:3px;">
                <el-input v-model="searchData" placeholder="输入患者名查询" @keyup.enter.native="search">></el-input>
            </el-col>
            <el-col :span="2">
                <el-button type="primary" @click="search">查询</el-button>
            </el-col>
        </el-row>
        <el-card  style="width: 98%;height:auto;">
      <el-table
        :data="out"
        stripe
        style="width: 100%"
        :max-height="tableHeight">
      
       <el-table-column
          prop="out_id"
          label="出院编号"
          sortable
          >
        </el-table-column>
        <el-table-column
          prop="reg_id"
          label="入院编号"
          sortable
          >
        </el-table-column>
        <el-table-column
          prop="reg_name"
          label="患者姓名"
          sortable
          >
        </el-table-column>
        <el-table-column
          prop="is_cost"
          label="是否结算"
          fit
          :filters="[{ text: '已结算', value: '已结算' }, { text: '未结算', value: '未结算' }]"
          :filter-method="filterTag"
          filter-placement="bottom-end">
          <template slot-scope="scope">
       <el-tag
          :type="scope.row.is_cost === '未结算' ? 'info' : 'success'"
          disable-transitions>{{scope.row.is_cost}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          prop="is_out"
          label="是否出院"
          fit
          :filters="[{ text: '已出院', value: '已出院' }, { text: '未出院', value: '未出院' }]"
          :filter-method="filterTag1"
          filter-placement="bottom-end">
          <template slot-scope="scope">
       <el-tag
          :type="scope.row.is_out === '未出院' ? 'primary' : 'error'"
          disable-transitions>{{scope.row.is_out}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              @click="editOut(scope.row)"
              type="text"
              size="small">
              办理出院
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
import {reqOutList,reqUpdateOut} from '@/api'
export default {
    name: 'OutHospital',
    components: {},
    data () {
      return {
        out:[],
        selectedOut:[],
        data:[],
        limit: 5,
        total: null,
        page: 1,
        pageshow:true,
        searchData:'',
        dialogFormVisible:false,
      }
    },
    created () {
      this.listOut()
    },
    computed: {
      tableHeight () {
        return window.innerHeight - 150
      },
     
    },
    methods: {
        async outHospital(){
            const info = this.selectedOut
            if(info.is_cost ==='未结算'){
                this.$message.error('请先结算住院费用！');
            }else{
                const result = await reqUpdateOut(info)
                if(result && result.status ===200){
                    this.$message({
                    message: '成功出院！',
                    type: 'success'
                    })
                }else{this.$message.error('请重新办理出院！');}
                this.listOut()
                this.dialogFormVisible = false
            }
            
        },
        editOut(value){
            this.dialogFormVisible = true
            this.selectedOut = value
            this.selectedOut.out_operator = this.$store.state.user.username
        },
        filterTag(value, row) {
        return row.is_cost === value;
      },
      filterTag1(value, row) {
        return row.is_out === value;
      },
         async listOut () {
          var _this = this
          const result = await reqOutList();
          
          if (result && result.status === 200) {
              _this.data = result.data
              this.getList();
            }
        },
         // 处理数据
        getList() {
          let out;
                //  es6过滤得到满足搜索条件的展示数据list
                if(this.searchData === ''){
                     out = this.data
                }else{
                     out = this.data.filter((item, index) =>
                   item.reg_name.includes(this.searchData)             
                )
                }
                 //过滤分页
                this.out = out.filter((item, index) =>
                    index < this.page * this.limit && index >= this.limit * (this.page - 1)
                )
                //分页的总数据
                this.total = out.length
               
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
    },
}
</script>

<style >
</style>
