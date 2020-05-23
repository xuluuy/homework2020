<template>
  <div>
    <el-dialog
      title="修改用户信息"
      :visible.sync="dialogFormVisible">
      <el-form v-model="selectedUser" style="text-align: left" ref="dataForm">
        <el-form-item label="用户名" label-width="120px" prop="u_username">
          <label>{{selectedUser.u_username}}</label>
        </el-form-item>
        <el-form-item label="真实姓名" label-width="120px" prop="u_name">
          <el-input v-model="selectedUser.u_name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号" label-width="120px" prop="u_phone">
          <el-input v-model="selectedUser.u_phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" label-width="120px" prop="u_email">
          <el-input v-model="selectedUser.u_email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="120px" prop="u_password">
          <el-button type="warning" @click="resetPassword(selectedUser.u_username)">重置密码</el-button>
          <span style="font-size:9px;">*重置为123456</span>
        </el-form-item>
        <el-form-item label="角色分配" label-width="120px" prop="roles">
          <el-checkbox-group v-model="selectedRolesIds">
              <el-checkbox v-for="(role,index) in roles" :key="index" :label="role.r_id">{{role.r_name_zh}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit(selectedUser)">确 定</el-button>
      </div>
    </el-dialog>
    <el-row style="display:inline-block;margin-top:10px; ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
        <el-breadcrumb-item>用户信息</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <bulk-registration @onSubmit="listUsers()" ></bulk-registration>
     <el-row style="margin-right:5px;float:right;margin-bottom:5px; ">
            <el-col :span="18" style="margin-right:3px;">
                <el-input v-model="searchData" placeholder="输入用户姓名查询" @keyup.enter.native="search">></el-input>
            </el-col>
            <el-col :span="2">
                <el-button type="primary" @click="search">查询</el-button>
            </el-col>
        </el-row>
    <el-card style="width: 98%;height:auto;">
      <el-table
        :data="users"
        stripe
        style="width: 98%"
        :max-height="tableHeight">
        <el-table-column
          prop="u_id"
          label="id"
          sortable
          width="100">
        </el-table-column>
        <el-table-column
          prop="u_username"
          label="用户名"
          fit>
        </el-table-column>
        <el-table-column
          prop="u_name"
          label="真实姓名"
          fit>
        </el-table-column>
        <el-table-column
          prop="u_phone"
          label="手机号"
          width="120"
          >
        </el-table-column>
        <el-table-column
          prop="u_email"
          label="邮箱"
          show-overflow-tooltip
          fit>
        </el-table-column>
        <el-table-column
          label="状态"
          width="100">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.u_enabled==='1'?true:false"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @change="(value) => commitStatusChange(value, scope.row)">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column
      prop="u_type"
      label="类型"
      width="100"
      :filters="[{ text: '患者', value: 'P' }, { text: '员工', value: 'S' }]"
      :filter-method="filterTag"
      filter-placement="bottom-end">
      <template slot-scope="scope">
        <el-tag
          :type="scope.row.u_type === 'P' ? 'primary' : 'success'"
          disable-transitions>{{scope.row.u_type==='P'?'患者':'员工'}}</el-tag>
      </template>
    </el-table-column>
    <el-table-column
          prop="create_time"
          label="创建时间"
          fit
          width="100px">
        </el-table-column>
        <el-table-column
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              @click="editUser(scope.row)"
              type="text"
              size="small">
              编辑
            </el-button>
            <el-button
            @click="deleteUser(scope.row)"
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
  import BulkRegistration from './BulkRegistration'
  import {reqUserList,reqRoleList,reqRidByUid,reqUpdateUser,reqResetPassword,reqDeleteUser,reqUpdateUstatus} from '@/api'
    export default {
      name: 'UserProfile',
      components: {BulkRegistration},
      data () {
          return {
            users: [],
            data:[],
            roles: [],
            dialogFormVisible: false,
            selectedUser: [],
            selectedRolesIds: [],
            searchData:'',
            limit: 5,
            total: null,
            page: 1,
            pageshow:true,
          }
      },
      created() {
        this.listUsers()
        this.listRoles()
      },
      
      computed: {
        tableHeight () {
          return window.innerHeight - 150
        }
      },
      methods:
      {
        filterTag(value, row) {
        return row.u_type === value;
      },
      async listUsers () {
        var _this = this
        const result = await reqUserList();
         if (result && result.status === 200) {
              _this.data = result.data
              //_this.users = resp.data
              this.getList();
            }
      },
        /* listUsers () {
          var _this = this
          this.$axios.get('/admin/user').then(resp => {
            if (resp && resp.status === 200) {
              _this.data = resp.data
              //_this.users = resp.data
              this.getList();
            }
          })
        }, */
        // 处理数据
        getList() {
          let users;
                //  es6过滤得到满足搜索条件的展示数据list
                if(this.searchData === ''){
                     users = this.data
                }else{
                     users = this.data.filter((item, index) =>
                   item.u_name.includes(this.searchData)             
                )
                }
                 //过滤分页
                this.users = users.filter((item, index) =>
                    index < this.page * this.limit && index >= this.limit * (this.page - 1)
                )
                //分页的总数据
                this.total = users.length
               
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
        
        async listRoles () {
          var _this = this
          const result = await reqRoleList();
          if (result && result.status === 200) {
              _this.roles = result.data
            }
        },
        /* listRoles () {
          var _this = this
          this.$axios.get('/admin/role').then(resp => {
            if (resp && resp.status === 200) {
              _this.roles = resp.data
            }
          })
        }, */
       async commitStatusChange (value, user) {
          const enabled = value===true?'1':'0'
          if (user.u_username !== 'admin') {
            const result = await reqUpdateUstatus({
              u_enabled: enabled,
              u_username: user.u_username
            });
            if (result && result.status === 200) {
                if (value) {
                  this.$message('用户 [' + user.u_username + '] 已启用')
                } else {
                  this.$message('用户 [' + user.u_username + '] 已禁用')
                }
                
            }
          } else {
            user.u_enabled = true
            this.$alert('不能禁用管理员账户')
          }
          this.listUsers()
        },
        async onSubmit (user){
          let _this = this
          // 根据视图绑定的角色 id 向后端传送角色信息
          let roles = []
          for (let i = 0; i < _this.selectedRolesIds.length; i++) {
            for (let j = 0; j < _this.roles.length; j++) {
              if (_this.selectedRolesIds[i] === _this.roles[j].r_id) {
                roles.push(_this.roles[j])
              }
            }
          }
          const updateuser = {
             u_id:user.u_id,          
            u_name: user.u_name,
            u_phone: user.u_phone,
            u_email: user.u_email,
            roles: roles
          }
          const result = await reqUpdateUser(updateuser);
           if (result && result.status === 200) {
              this.$alert('用户信息修改成功')
              this.dialogFormVisible = false
              // 修改角色后重新请求用户信息，实现视图更新
              this.listUsers()
            }
        },
       /*  onSubmit (user) {
          let _this = this
          // 根据视图绑定的角色 id 向后端传送角色信息
          let roles = []
          for (let i = 0; i < _this.selectedRolesIds.length; i++) {
            for (let j = 0; j < _this.roles.length; j++) {
              if (_this.selectedRolesIds[i] === _this.roles[j].r_id) {
                roles.push(_this.roles[j])
              }
            }
          }
          this.$axios.post('/admin/updateUser', {
            u_id:user.u_id,          
            u_name: user.u_name,
            u_phone: user.u_phone,
            u_email: user.u_email,
            roles: roles
          }).then(resp => {
            if (resp && resp.status === 200) {
              this.$alert('用户信息修改成功')
              this.dialogFormVisible = false
              // 修改角色后重新请求用户信息，实现视图更新
              this.listUsers()
            }
          })
        }, */
        async editUser (user) {
          this.dialogFormVisible = true
          this.selectedUser = user
         // this.selectedUser.u_password = ''
          let roleIds = []
          const result = await reqRidByUid(user.u_id);
           if (result && result.status === 200) {
              
          for (let i = 0; i < result.data.length; i++) {
            roleIds.push(result.data[i].r_id)
          }
            }     
          this.selectedRolesIds = roleIds
        },
       /*  editUser (user) {
          this.dialogFormVisible = true
          this.selectedUser = user
          this.selectedUser.u_password = ''
          let roleIds = []
          this.$axios.post('/admin/getU_R',{
            u_id:user.u_id
          }).then(resp => {
            if (resp && resp.status === 200) {
              
          for (let i = 0; i < resp.data.length; i++) {
            roleIds.push(resp.data[i].r_id)
          }
            }
          })      
          this.selectedRolesIds = roleIds
        }, */
      async resetPassword (u_username) {
        const result = await reqResetPassword(u_username)
        if(result && result.status === 200){
           this.$alert('密码已重置为 123456')
        }
        },
        deleteUser(user){
         const u_id = user.u_id
          this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
           const result = await reqDeleteUser(u_id)
         if(result && result.status ===200){
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
              this.listUsers()
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
