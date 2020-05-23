<template>
  <div >
      <el-dialog
      title="药品发放"
      :visible.sync="dialogFormVisible">
      <el-form v-model="createDispatch" style="text-align: left" >
        <el-form-item label="入院id" label-width="120px" prop="reg_id">
          <el-input v-model="createDispatch.reg_id" style="width:222px;" @blur="queryPname"></el-input>
        </el-form-item>
        <el-form-item label="患者姓名" label-width="120px" prop="reg_name">
          <el-input v-model="createDispatch.reg_name" style="width:222px;"></el-input>
        </el-form-item>
        <el-form-item label="药品名称" label-width="120px" >
          <template>
              <el-select v-model="createDispatch.drug_id" placeholder="请选择" @change="selected">
                <el-option
                  v-for="item in drugs"
                  :key="item.drug_id"
                  :label="item.drug_name+' / '+item.drug_quantity"
                  :value="item.drug_id">
                </el-option>
              </el-select>
          </template>
        </el-form-item>
        <el-form-item label="药品价格" label-width="120px" prop="drug_price">
         <template>
            <el-input-number disabled v-model="createDispatch.drug_price" :precision="2" :step="1" ></el-input-number> 元
          </template>
        </el-form-item>
         <el-form-item label="药品数量" label-width="120px"  prop="drug_num">
          <template>
            <el-input-number v-model="createDispatch.drug_num" controls-position="right"  ></el-input-number>
          </template>
        </el-form-item> 
        <el-form-item label="操作员" label-width="120px" prop="drug_operator">
          <span>{{createDispatch.staff_id}}</span>
        </el-form-item>
      </el-form>     
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="clear">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </div> 
    </el-dialog>
 <el-row style="display:inline-block;margin-top:10px; ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>药品管理</el-breadcrumb-item>
        <el-breadcrumb-item>药品发放</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-button style="text-align: left;display: inline-block;
    float:right;margin-right:23px;" type="warning" @click="dialogFormVisible = true"> 药品发放</el-button>
     <el-row style="margin-right:5px;float:right;margin-bottom:5px; ">
            <el-col :span="18" style="margin-right:3px;">
                <el-input v-model="searchData" placeholder="输入患者入院id查询" @keyup.enter.native="search">></el-input>
            </el-col>
            <el-col :span="2">
                <el-button type="primary" @click="search">查询</el-button>
            </el-col>
        </el-row>
        
    <el-card  style="width: 98%;height:auto;">
      <el-table
        :data="dispatch"
        stripe
        style="width: 100%"
        :max-height="tableHeight">

        <el-table-column
          prop="reg_id"
          label="入院id"
          
          >
        </el-table-column>
        <el-table-column
          prop="drug_name"
          label="药品名称"
          fit>
        </el-table-column>
        <el-table-column
          prop="drug_num"
          label="药品数量"
          fit>
        </el-table-column>
        
        <el-table-column
          prop="staff_id"
          label="操作员"
          fit>
        </el-table-column>
        <el-table-column
          prop="create_time"
          label="创建时间"
          fit>
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
import {reqDurgList,reqQuaryRegname,reqCreateDispatch,reqDispatchList} from '@/api';
export default {
     name: 'DrugDispatch',
    components: {},
    data () {
      return {
        dialogFormVisible: false,        
        dispatch: [],
        data:[],          
        limit: 5,
        total: null,
        page: 1,
        pageshow:true,
        searchData:'',
        createDispatch:{
            drug_id :'',
            drug_num :'',
            drug_price :'',
            reg_id:'',
            reg_name:'',
            staff_id :this.$store.state.user.username,
            create_time :'',
                    },
        drugs: [],
      }
    },
    created () {
      this.listDispatch()
      this.listDrugs()
    },
    
    computed: {
      tableHeight () {
        return window.innerHeight - 150
      },
     
    },
    methods: {
        async queryPname (){
            const reg_id = this.createDispatch.reg_id
            const result = await reqQuaryRegname(reg_id)
            if(result && result.status === 200){
                this.createDispatch.reg_name = result.data
            }
        },
        async listDrugs () {
        var _this = this
        const result = await reqDurgList();
         if (result && result.status === 200) {
              _this.drugs = result.data
             
            }
        },
        selected(){
            const drugId = this.createDispatch.drug_id
            let newdrug = this.drugs.filter((item, index) =>
                   item.drug_id.includes(drugId))
            this.createDispatch.drug_price = newdrug[0].drug_price      

        },
        clear(){
          this.createDispatch={
            drug_id :'',
            drug_num :'',
            drug_price :'',
            reg_id:'',
            reg_name:'',
            staff_id :this.$store.state.user.username,
            create_time :'',
                    }
          this.dialogFormVisible = false
        },
       async onSubmit(){
          const create = {
            drug_id :this.createDispatch.drug_id,
            drug_num :this.createDispatch.drug_num,
            drug_price :this.createDispatch.drug_price,
            reg_id:this.createDispatch.reg_id,
            staff_id :this.createDispatch.staff_id,           
          }
          const result = await reqCreateDispatch(create)
          if(result && result.status ===200){
              this.$message({
                    message: '药品分发成功！',
                    type: 'success'
                    })
            this.$emit('onSubmit')
          }else {
                this.$message.error('药品分发失败！');
              }
               
               this.clear();
        },
        async listDispatch () {
        var _this = this
        const result = await reqDispatchList();
         if (result && result.status === 200) {
              _this.data = result.data
              this.getList()
            }
      },
     
        // 处理数据
        getList() {
          let dispatch;
                //  es6过滤得到满足搜索条件的展示数据list
                if(this.searchData === ''){
                     dispatch = this.data
                }else{
                     dispatch = this.data.filter((item, index) =>
                   item.reg_id.includes(this.searchData)             
                )
                }
                 //过滤分页
                this.dispatch = dispatch.filter((item, index) =>
                    index < this.page * this.limit && index >= this.limit * (this.page - 1)
                )
                //分页的总数据
                this.total = dispatch.length
               
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
     
      
       
     
       /* async editReg (reg) {
      this.dialogFormVisible = true
      this.selectedReg = reg
      const dep_name = reg.dep_name
      const result = await reqDocs(dep_name)
      if(result && result.status === 200){
        this.docs = result.data
      }
      },
     async onSubmit (selectedReg) {
        const updatesreg = selectedReg
        const result = await reqUpdateReg(updatesreg);
        if (result && result.status === 200) {
            this.$alert('入院信息修改成功！')
            this.dialogFormVisible = false
            this.listReg()
          }     
      },
        */

    } 
}
</script>

<style >
</style>
