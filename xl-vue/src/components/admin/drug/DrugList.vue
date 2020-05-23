<template>
  <div>
    <el-dialog
      title="修改病房信息"
      :visible.sync="dialogFormVisible">
      <el-form v-model="selectedDurg" style="text-align: left" ref="dataForm">
        <el-form-item label="药品名称" label-width="120px" prop="drug_name">
          <el-input v-model="selectedDurg.drug_name" style="width:222px;"></el-input>
        </el-form-item>
        <el-form-item label="药品价格" label-width="120px" prop="drug_price">
         <template>
            <el-input-number v-model="selectedDurg.drug_price" :precision="2" :step="1" ></el-input-number> 元
          </template>
        </el-form-item>
        <el-form-item label="供应商" label-width="120px" prop="drug_supply">
         <el-input v-model="selectedDurg.drug_supply" style="width:222px;"></el-input>
        </el-form-item>
        <el-form-item label="药品数量" label-width="120px" prop="drug_quantity">
          <template>
            <el-input-number v-model="selectedDurg.drug_quantity" controls-position="right"  ></el-input-number>
          </template>
        </el-form-item>      
        <el-form-item label="有效日期" label-width="120px" prop="drug_exp">
          <template>
            <el-date-picker
              v-model="selectedDurg.drug_exp"
              value-format="yyyy-MM-dd"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </template>
        </el-form-item>
        <el-form-item label="操作员" label-width="120px" prop="drug_operator">
          <span>{{selectedDurg.drug_operator}}</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit(selectedDurg)">确 定</el-button>
      </div>
    </el-dialog>
    <el-row style="display:inline-block;margin-top:10px; ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>药品管理</el-breadcrumb-item>
        <el-breadcrumb-item>药品清单</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
      <CreateDurg @onSubmit="listDrugs()" ></CreateDurg>  
     <el-row style="margin-right:5px;float:right;margin-bottom:5px; ">
            <el-col :span="18" style="margin-right:3px;">
                <el-input v-model="searchData" placeholder="输入药品名称查询" @keyup.enter.native="search"></el-input>
            </el-col>
            <el-col :span="2">
                <el-button type="primary" @click="search">查询</el-button>
            </el-col>
        </el-row>
    <el-card style="width: 98%;height:auto;">
      <el-table
        :data="drugs"
        stripe
        style="width: 98%"
        :max-height="tableHeight">
        <el-table-column
          prop="drug_id"
          label="id"
          sortable
          width="100">
        </el-table-column>
        <el-table-column
          prop="drug_name"
          label="药品名称"
          show-overflow-tooltip
          content="drug_name"
          fit>
        </el-table-column>
        <el-table-column
          prop="drug_price"
          label="药品单价/元"
          fit>
           <template slot-scope="scope">
            <span>{{scope.row.drug_price | rounding}}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="drug_supply"
          label="药品供应商"
          show-overflow-tooltip
          fit
          >  
        </el-table-column>
        <el-table-column
          prop="drug_quantity"
          label="药品总量" 
          fit>
          <template slot-scope="scope">
             <span v-if="scope.row.drug_quantity < 120" style="color:red">{{scope.row.drug_quantity}}</span>
            <span v-else>{{scope.row.drug_quantity}}</span>
          </template>
        </el-table-column>
         <el-table-column
          prop="drug_exp"
          label="有效期" 
          fit>
          <template slot-scope="scope">
            <span>{{scope.row.drug_exp | dateformat }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="drug_operator"
          label="操作员" 
          fit>
        </el-table-column>
        <el-table-column
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              @click="editDurg(scope.row)"
              type="text"
              size="small">
              编辑
            </el-button>
            <el-button
            @click="deleteDurg(scope.row)"
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
  import CreateDurg from './CreateDrug'
  import {reqDurgList,reqUpdateDurg,reqDeleteDurg} from '@/api'
  import {formatDate} from '@/util'
    export default {
      name: 'Durg',
      components: {CreateDurg},
      data () {
          return {
            drugs: [],
            data:[],
            dialogFormVisible: false,
            selectedDurg: [],     
            searchData:'',
            limit: 5,
            total: null,
            page: 1,
            pageshow:true,
            
          }
      },
      filters: {
      rounding (value) {
        return value.toFixed(2)
        },
        dateformat (value) {
          return value.substring(0, 10);
        }
      },
      created() {
        this.listDrugs()
      },
      
      computed: {
        tableHeight () {
          return window.innerHeight - 150
        },
        
      },
      methods:
      {
       async listDrugs () {
        var _this = this
        const result = await reqDurgList();
         if (result && result.status === 200) {
              _this.data = result.data
              this.getList();
            }
      },
        
        // 处理数据
        getList() {
          let drugs;
                //  es6过滤得到满足搜索条件的展示数据list
                if(this.searchData === ''){
                     drugs = this.data
                }else{
                     drugs = this.data.filter((item, index) =>
                   item.drug_name.includes(this.searchData)             
                )
                }
                 //过滤分页
                this.drugs = drugs.filter((item, index) =>
                    index < this.page * this.limit && index >= this.limit * (this.page - 1)
                )
                //分页的总数据
                this.total = drugs.length
               
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
         editDurg (durg) {
          this.dialogFormVisible = true
          this.selectedDurg = durg
        },
       
        async onSubmit (durg){
          let _this = this
          const updatedurg = {
             drug_id:durg.drug_id,          
            drug_name: durg.drug_name,
            drug_price: durg.drug_price,
            drug_supply: durg.drug_supply,
            drug_quantity: durg.drug_quantity,
            drug_exp: durg.drug_exp.substring(0, 10),
            drug_operator: durg.drug_operator,
           
          }
          const result = await reqUpdateDurg(updatedurg);
           if (result && result.status === 200) {
              this.$alert('药品信息修改成功')
              this.dialogFormVisible = false
              this.listDrugs()
            }
        },
      
        
      
        deleteDurg(durg){
         const drug_id = durg.drug_id
          this.$confirm('此操作将永久删除该药品, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          
           const result = await reqDeleteDurg(drug_id)
         if(result && result.status ===200){
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
              this.listDrugs()
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
