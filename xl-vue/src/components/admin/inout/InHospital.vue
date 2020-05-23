<template>
  <div>
 <el-dialog
      title="修改入院登记信息"
      :visible.sync="dialogFormVisible"
      width="800px">
      <el-form v-model="selectedReg" style="text-align: left;" ref="dataForm">
        <el-form-item label="RegId:">
        {{selectedReg.reg_id}}
        </el-form-item>
         <el-row>
                <el-col :span="8">
                    <el-form-item label="用户账号" >              
                        {{selectedReg.patient_id}}
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="姓名">
                       {{selectedReg.reg_name}}
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="身份证" >
                        {{selectedReg.reg_identity}}
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label="性别">              
                       <template>
                            <el-radio v-model="selectedReg.reg_sex" label="男">男</el-radio>
                            <el-radio v-model="selectedReg.reg_sex" label="女">女</el-radio>
                        </template> 
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="年龄">
                        <el-input disabled v-model="selectedReg.reg_age"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="出生日期">
                        <el-input disabled v-model="selectedReg.reg_birth"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label="联系电话">
                        <el-input disabled v-model="selectedReg.reg_phone"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="16">
                    <el-form-item label="工作地址">
                        <el-input disabled v-model="selectedReg.reg_workplace"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label="医保卡号" prop="carte_vital">
                        <el-input disabled v-model="selectedReg.carte_vital"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="收治科室">
                       <template>
              <el-select disabled v-model="selectedReg.dep_name" placeholder="请选择">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
          </template>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="入院时间">
                        <el-input disabled v-model="selectedReg.create_time"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="10">
                    <el-form-item label="入院诊断">
                        <el-input
                        type="textarea"
                        disabled
                        autosize
                        placeholder="请输入内容"
                        v-model="selectedReg.condition">
                        </el-input>
                    </el-form-item>
                </el-col>
                 <el-col :span="8">
                  <el-form-item label="预缴金">
                      <el-input disabled v-model="selectedReg.pre_amount" ></el-input>
                  </el-form-item>
                 </el-col>              
            </el-row>
            <el-divider></el-divider>
            <el-row>
              <el-col :span="6">
                <el-form-item label="入住病区">
                 <template>
              <el-select v-model="selectedReg.ward_name" placeholder="请选择" @change="selectWard()">
                <el-option
                  v-for="item in wards"
                  :key="item.ward_name"
                  :label="item.ward_name"
                  :value="item.ward_name">
                </el-option>
              </el-select>
          </template>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="床位类型">
                 <template>
              <el-select v-model="selectedReg.bed_type" placeholder="请选择" @change="selectbed()">
                <el-option
                  v-for="item in wardType"
                  :key="item.bed_type"
                  :label="item.bed_type"
                  :value="item.bed_type">
                </el-option>
              </el-select>
          </template>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="入住床位">
                 <template>
              <el-select v-model="selectedReg.bed_name" placeholder="请选择" >
                <el-option
                  v-for="item in beds"
                  :key="item.bed_name"
                  :label="item.bed_name"
                  :value="item.bed_name">
                </el-option>
              </el-select>
          </template>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="主管医生">
                 <template>
              <el-select v-model="selectedReg.doc_name" placeholder="请选择" >
                <el-option
                  v-for="item in docs"
                  :key="item.staff_name"
                  :label="item.staff_name+' '+'5/'+item.patient_num"
                  :value="item.staff_name">
                </el-option>
              </el-select>
          </template>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
                <el-col :span="6">
                    <el-form-item label="操作员">
                        <el-input disabled v-model="selectedReg.operator_uname"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="18">
                    <el-button type="primary" style="left:-50%;margin-left:50%" @click="onSubmit(selectedReg)">提交</el-button>
                </el-col>
            </el-row>
            
    </el-form>           
    </el-dialog>
    <el-row style="display:inline-block;margin-top:10px; ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>出入院管理</el-breadcrumb-item>
        <el-breadcrumb-item>入院管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-button style="text-align: left;display: inline-block;
    float:right;margin-right:23px;"  type="warning" @click="register">入院登记</el-button>
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
        :data="regs"
        stripe
        style="width: 100%"
        :max-height="tableHeight">

         <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-row >
            <el-form-item label="id:" >
            <span>{{ props.row.reg_id }}</span>
            </el-form-item>
            <el-form-item label="姓名:">
            <span>{{ props.row.reg_name }}</span>
            </el-form-item>
            <el-form-item label="身份证号:">
            <span>{{ props.row.reg_identity }}</span>
            </el-form-item>
            <el-form-item label="联系电话:">
            <span>{{ props.row.reg_phone }}</span>
            </el-form-item>
            <el-form-item label="医保卡号:">
            <span>{{ props.row.carte_vital }}</span>
            </el-form-item>            
          </el-row>
           <el-row>
            <el-form-item label="性别:">
            <span>{{ props.row.reg_sex }}</span>
            </el-form-item>
            <el-form-item label="收治科室:">
            <span>{{ props.row.dep_name }}</span>
            </el-form-item>
            <el-form-item label="预缴金额:">
            <span>{{ props.row.pre_amount }}</span>
            </el-form-item>
            <el-form-item label="病情:">
            <span>{{ props.row.condition }}</span>
            </el-form-item>
            <el-form-item label="状态:">
            <span>{{ props.row.out_flag==='1'?'已出院':'未出院' }}</span>
            </el-form-item>
                       
          </el-row>
           <el-row>
            <el-form-item label="入住病区:">
            <span>{{ props.row.ward_name }}</span>
            </el-form-item>
            <el-form-item label="床位类型:">
            <span>{{ props.row.bed_type }}</span>
            </el-form-item>
            <el-form-item label="入住床位:">
            <span>{{ props.row.bed_name }}</span>
            </el-form-item>
            <el-form-item label="主管医生:">
            <span>{{ props.row.doc_name }}</span>
            </el-form-item>
            <el-form-item label="入院时间:">
            <span>{{ props.row.create_time }}</span>
            </el-form-item>
                       
          </el-row>
        </el-form>
      </template>
    </el-table-column>

        <el-table-column
          prop="reg_id"
          label="id"
          sortable
          >
        </el-table-column>
        <el-table-column
          prop="reg_name"
          label="患者姓名"
          fit>
        </el-table-column>
        <el-table-column
          prop="dep_name"
          label="收治科室"
          fit>
        </el-table-column>
        <el-table-column
          label="状态"
          :filters="[{ text: '已出院', value: '1' }, { text: '未出院', value: '0' }]"
          filter-placement="bottom-end"
          fit>
          <template slot-scope="scope">
            <!-- staff_flag   -->
            <el-switch
              v-model="scope.row.out_flag ==='0'?true:false" 
              disabled
              >
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          fit>
          <template slot-scope="scope">
            <el-button
              type="text"
              size="small"
              @click="editReg(scope.row)">
              编辑
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
  
  import {reqRegList,reqBedList,reqWardsList,reqBedType,reqDocs,reqUpdateReg} from '@/api'
  import {defaultData} from '@/util/data.js'
  export default {
    name: 'InHospital',
    components: {},
    data () {
      return {
        dialogFormVisible: false,
        bedDistribution:false,
        wardType:[],
        regs: [],
        data:[],
        selectedReg: [],              
        limit: 5,
        total: null,
        page: 1,
        pageshow:true,
        searchData:'',
        options: defaultData.options,
        beds:[],
        wards:[],
        docs:[],
      }
    },
    created () {
      this.listReg()
      this.listWards()
    },
    
    computed: {
      tableHeight () {
        return window.innerHeight - 150
      },
     
    },
    methods: {
      async selectWard(){
       const ward_name = this.selectedReg.ward_name
       const result = await reqBedType(ward_name)
       if(result && result.status === 200){
         this.wardType = result.data
       }
      },
        register(){
            this.$router.replace({path:'/admin/inout/registration'})
        },
        async listWards () {
        var _this = this
        const result = await reqWardsList();
         if (result && result.status === 200) {
              _this.wards = result.data
              
            }
      },
     async selectbed () {
        const ward_name = this.selectedReg.ward_name
        const bed_type = this.selectedReg.bed_type
        const ward = {
          ward_name,
          bed_type
        }
        const result = await reqBedList(ward)
        if(result && result.status === 200){
          this.beds = result.data
        }
      },
      async listReg () {
          var _this = this
          const result = await reqRegList();
          
          if (result && result.status === 200) {
              _this.data = result.data
              this.getList();
            }
        },
        // 处理数据
        getList() {
          let regs;
                //  es6过滤得到满足搜索条件的展示数据list
                if(this.searchData === ''){
                     regs = this.data
                }else{
                     regs = this.data.filter((item, index) =>
                   item.reg_name.includes(this.searchData)             
                )
                }
                 //过滤分页
                this.regs = regs.filter((item, index) =>
                    index < this.page * this.limit && index >= this.limit * (this.page - 1)
                )
                //分页的总数据
                this.total = regs.length
               
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
     
      
       
     
       async editReg (reg) {
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
       

    } 
  }
</script>

<style >
  .demo-table-expand label {
    
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 19%;
  }
</style>
