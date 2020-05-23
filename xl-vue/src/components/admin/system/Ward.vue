<template>
  <div>
    <el-dialog
      title="修改病房信息"
      :visible.sync="dialogFormVisible">
      <el-form v-model="selectedWard" style="text-align: left" ref="dataForm">
        <el-form-item label="病房名称" label-width="120px" prop="ward_name">
          <el-input v-model="selectedWard.ward_name" style="width:222px;"></el-input>
        </el-form-item>
        <el-form-item label="所属科室" label-width="120px" prop="dep_name">
         <template>
              <el-select v-model="selectedWard.dep_name" placeholder="请选择">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
          </template>
        </el-form-item>
        <el-form-item label="病房类型" label-width="120px" prop="bed_type">
          <template>
              <el-select v-model="selectedWard.bed_type" placeholder="请选择">
                <el-option
                  v-for="item in wardType"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
          </template>
        </el-form-item>
        <el-form-item label="总床位" label-width="120px" prop="total_bed">
          <template>
            <el-input-number v-model="selectedWard.total_bed" controls-position="right"  :min="1" :max="100"></el-input-number>
          </template>
        </el-form-item>      
        <el-form-item label="剩余床位" label-width="120px" prop="remain_bed">
          <template>
            <el-input-number v-model="selectedWard.remain_bed" controls-position="right" :min="0" :max="100"></el-input-number>
          </template>
        </el-form-item>
        <el-form-item label="床位价格" label-width="120px" prop="bed_price">
          <template>
            <el-input-number v-model="selectedWard.bed_price" :precision="2" :step="1" ></el-input-number>元
          </template>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit(selectedWard)">确 定</el-button>
      </div>
    </el-dialog>
    <el-row style="display:inline-block;margin-top:10px; ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>系统管理</el-breadcrumb-item>
        <el-breadcrumb-item>病房管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
     <CreateWard @onSubmit="listWards()" ></CreateWard> 
     <el-row style="margin-right:5px;float:right;margin-bottom:5px; ">
            <el-col :span="18" style="margin-right:3px;">
                <el-input v-model="searchData" placeholder="输入病房名称查询" @keyup.enter.native="search"></el-input>
            </el-col>
            <el-col :span="2">
                <el-button type="primary" @click="search">查询</el-button>
            </el-col>
        </el-row>
    <el-card style="width: 98%;height:auto;">
      <el-table
        :data="wards"
        stripe
        style="width: 98%"
        :max-height="tableHeight">
        <el-table-column
          prop="ward_id"
          label="id"
          sortable
          width="100">
        </el-table-column>
        <el-table-column
          prop="ward_name"
          label="病房名称"
          fit>
        </el-table-column>
        <el-table-column
          prop="dep_name"
          label="所属科室"
          fit>
        </el-table-column>
        <el-table-column
          prop="bed_type"
          label="病房类型"
          width="160"
          >
          <template slot-scope="scope">
        <el-tag
          :type="scope.row.bed_type | getColorType"
          >{{scope.row.bed_type}}</el-tag>
        </template>
        </el-table-column>
        <el-table-column
          prop="total_bed,remain_bed"
          label="床位数量(总/余)" 
          fit>
          <template slot-scope="scope">
            <span>{{scope.row.total_bed + '/' + scope.row.remain_bed}}</span>
          </template>
        </el-table-column>
         <el-table-column
          prop="bed_price"
          label="床位价位/元" 
          fit>
          <template slot-scope="scope">
            <span>{{scope.row.bed_price | rounding}}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              @click="editWard(scope.row)"
              type="text"
              size="small">
              编辑
            </el-button>
            <el-button
            @click="deleteWard(scope.row)"
              type="text"
              size="small">
              移除
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
  import CreateWard from './CreateWard'
  import {reqWardList,reqUpdateWard,reqDeleteWard} from '@/api'
  import {defaultData} from '@/util/data.js'
    export default {
      name: 'Ward',
      components: {CreateWard},
      data () {
          return {
            wards: [],
            data:[],
            dialogFormVisible: false,
            selectedWard: [],     
            searchData:'',
            limit: 5,
            total: null,
            page: 1,
            pageshow:true,
            options: defaultData.options,
            wardType: defaultData.wardType,
          }
      },
      filters: {
      rounding (value) {
        return value.toFixed(2)
        },
        //tag类型
      getColorType(role_name) {
        const colorMap = {
          '重症监护室(ICU_1人)': 'danger',
          '初级(4人)': 'info',
          '高等(2人)': 'warning',
          'VIP(1人)': 'success',
        };
        return colorMap[role_name]
      }
      },
      created() {
        this.listWards()
      },
      
      computed: {
        tableHeight () {
          return window.innerHeight - 150
        },
        
      },
      methods:
      {
      async listWards () {
        var _this = this
        const result = await reqWardList();
         if (result && result.status === 200) {
              _this.data = result.data
              this.getList();
            }
      },
        
        // 处理数据
        getList() {
          let wards;
                //  es6过滤得到满足搜索条件的展示数据list
                if(this.searchData === ''){
                     wards = this.data
                }else{
                     wards = this.data.filter((item, index) =>
                   item.ward_name.includes(this.searchData)             
                )
                }
                 //过滤分页
                this.wards = wards.filter((item, index) =>
                    index < this.page * this.limit && index >= this.limit * (this.page - 1)
                )
                //分页的总数据
                this.total = wards.length
               
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
        
       
        async onSubmit (ward){
          let _this = this
          const updateward = {
             ward_id:ward.ward_id,          
            ward_name: ward.ward_name,
            dep_name: ward.dep_name,
            bed_type: ward.bed_type,
            total_bed: ward.total_bed,
            remain_bed: ward.remain_bed,
            bed_price: ward.bed_price,
           
          }
          const result = await reqUpdateWard(updateward);
           if (result && result.status === 200) {
              this.$alert('病房信息修改成功')
              this.dialogFormVisible = false
              this.listWards()
            }
        },
      
         editWard (ward) {
          this.dialogFormVisible = true
          this.selectedWard = ward
        },
      
        deleteWard(ward){
         const ward_id = ward.ward_id
          this.$confirm('此操作将永久删除该病房, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          
           const result = await reqDeleteWard(ward_id)
         if(result && result.status ===200){
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
              this.listWards()
         }
      
          
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      }
        
      }, 
      
    }
</script>

<style scoped>

</style>
