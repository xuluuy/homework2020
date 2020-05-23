<template>
  <div>
    <el-dialog
      title="修改员工信息"
      :visible.sync="dialogFormVisible">
      <el-form v-model="selectedStaff" style="text-align: left" ref="dataForm">
        <el-form-item label="id" label-width="120px" prop="staff_id">
          {{selectedStaff.staff_id}}
        </el-form-item>
        <el-form-item label="姓名" label-width="120px" prop="staff_name">
          {{selectedStaff.staff_name}}
        </el-form-item>
         <el-form-item label="性别" prop="staff_sex"label-width="120px">
           <template>
              <el-radio v-model="selectedStaff.staff_sex" label="男">男</el-radio>
              <el-radio v-model="selectedStaff.staff_sex" label="女">女</el-radio>
           </template>
         </el-form-item>
         <el-form-item label="科室" prop="dep_name" label-width="120px">
           <template>
              <el-select v-model="selectedStaff.dep_name" placeholder="请选择">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
          </template>
         </el-form-item>
         <el-form-item label="岗位" prop="position" label-width="120px">
           <el-input v-model="selectedStaff.position" style="width:150px;"></el-input>
         </el-form-item>
         <el-form-item label="职称" prop="professional_title" label-width="120px">
           <template>
              <el-select v-model="selectedStaff.professional_title" placeholder="请选择">
                <el-option
                  v-for="item in professional"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
          </template>
         </el-form-item>
         <el-form-item label="状态" prop="staff_flag" label-width="120px">
           <template >
              <el-radio v-model="selectedStaff.staff_flag" label="0">空闲</el-radio>
              <el-radio v-model="selectedStaff.staff_flag" label="1">占用</el-radio>
            </template>
         </el-form-item>
        </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit(selectedStaff)">确 定</el-button>
      </div> 
    </el-dialog>
    <el-row style="display:inline-block;margin-top:10px; ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
        <el-breadcrumb-item>员工管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
   
    <el-row style="margin-right:22px;float:right;margin-bottom:5px; ">
            <el-col :span="18" style="margin-right:3px;">
                <el-input v-model="searchData" placeholder="输入员工名查询" @keyup.enter.native="search">></el-input>
            </el-col>
            <el-col :span="2">
                <el-button type="primary" @click="search">查询</el-button>
            </el-col>
        </el-row>
    <el-card  style="width: 98%;height:auto;">
      <el-table
        :data="staff"
        stripe
        style="width: 100%"
        :max-height="tableHeight">

         <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-row >
            <el-form-item label="id:" >
            <span>{{ props.row.staff_id }}</span>
            </el-form-item>
            <el-form-item label="姓名:">
            <span>{{ props.row.staff_name }}</span>
            </el-form-item>
            <el-form-item label="科室:">
            <span>{{ props.row.dep_name }}</span>
            </el-form-item>
            <el-form-item label="所属角色:">
            <span>{{ props.row.role_name }}</span>
            </el-form-item>
            <el-form-item label="手机:">
            <span>{{ props.row.staff_phone }}</span>
            </el-form-item>            
          </el-row>
           <el-row>
            <el-form-item label="性别:">
            <span>{{ props.row.staff_sex }}</span>
            </el-form-item>
            <el-form-item label="岗位:">
            <span>{{ props.row.position }}</span>
            </el-form-item>
            <el-form-item label="职称:">
            <span>{{ props.row.professional_title }}</span>
            </el-form-item>
            <el-form-item label="状态:">
            <span>{{ props.row.staff_flag==='1'?'占用':'空闲' }}</span>
            </el-form-item>
                       
          </el-row>
        </el-form>
      </template>
    </el-table-column>

        <el-table-column
          prop="staff_id"
          label="id"
          sortable
          >
        </el-table-column>
        <el-table-column
          prop="staff_name"
          label="员工姓名"
          fit>
        </el-table-column>
        <el-table-column
          prop="role_name"
          label="员工角色"
          fit
          :filters="[{ text: '系统管理员', value: '系统管理员' }, { text: '药品管理员', value: '药品管理员' },
          { text: '医生', value: '医生' }, { text: '护士', value: '护士' }, { text: '财务', value: '财务' }]"
          :filter-method="filterTag"
          filter-placement="bottom-end">
          <template slot-scope="scope">
        <el-tag
          :type="scope.row.role_name | getColorType"
          >{{scope.row.role_name}}</el-tag>
        </template>
        </el-table-column>
        <el-table-column
          label="状态 (空闲/占用)"
          :filters="[{ text: '占用', value: '1' }, { text: '空闲', value: '0' }]"
          :filter-method="filterStatus"
          filter-placement="bottom-end"
          fit>
          <template slot-scope="scope">
            <!-- staff_flag 0:空闲 1:占用  -->
            <el-switch
              v-model="scope.row.staff_flag ==='0'?true:false" 
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
              @click="editStaff(scope.row)">
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
  import {reqStaffList,reqUpdateStaff} from '@/api'
  import {defaultData} from '@/util/data.js'
  export default {
    name: 'Staff',
    components: {},
    filters:{
      //tag类型
      getColorType(role_name) {
        const colorMap = {
          '系统管理员': 'danger',
          '财务': '',
          '药品管理员': 'info',
          '护士': 'warning',
          '医生': 'success',
        };
        return colorMap[role_name]
      }
    },
    data () {
      return {
        dialogFormVisible: false,
        staff: [],
        data:[],
        selectedStaff: [],              
        limit: 5,
        total: null,
        page: 1,
        pageshow:true,
        searchData:'',
        options: defaultData.options,
        professional:defaultData.professional,
      }
    },
    created () {
      this.listStaff()
    },
    
    computed: {
      tableHeight () {
        return window.innerHeight - 150
      },
     
    },
    methods: {
      filterStatus(value,row) {
        return row.staff_flag === value;
      },
      filterTag(value, row) {
             return row.role_name === value;
             },
      async listStaff () {
          var _this = this
          const result = await reqStaffList();
          if (result && result.status === 200) {
              _this.data = result.data
              this.getList();
            }
        },
        // 处理数据
        getList() {
          let staff;
                //  es6过滤得到满足搜索条件的展示数据list
                if(this.searchData === ''){
                     staff = this.data
                }else{
                     staff = this.data.filter((item, index) =>
                   item.staff_name.includes(this.searchData)             
                )
                }
                 //过滤分页
                this.staff = staff.filter((item, index) =>
                    index < this.page * this.limit && index >= this.limit * (this.page - 1)
                )
                //分页的总数据
                this.total = staff.length
               
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
     
      
       
     
      async editStaff (staff) {
      this.dialogFormVisible = true
      this.selectedStaff = staff
      },
     async onSubmit (staff) {
        const updatestaff = {
          staff_id : staff.staff_id,
          dep_name: staff.dep_name,
          staff_sex: staff.staff_sex,
          position: staff.position,
          professional_title: staff.professional_title, 
          staff_flag:staff.staff_flag
        }
        const result = await reqUpdateStaff(updatestaff);
        if (result && result.status === 200) {
            this.$alert('员工信息修改成功！')
            this.dialogFormVisible = false
            this.listStaff()
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
