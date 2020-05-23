<template>
  <div>
    <el-dialog
      title="修改角色信息"
      :visible.sync="dialogFormVisible">
      <el-form v-model="selectedRole" style="text-align: left" ref="dataForm">
        <el-form-item label="角色名" label-width="120px" prop="r_name">
          <el-input v-model="selectedRole.r_name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" label-width="120px" prop="r_name_zh">
          <el-input v-model="selectedRole.r_name_zh" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="菜单配置" label-width="120px" prop="menus">
          <el-tree
            :data="menus"
            :props="props"
            show-checkbox
            
            node-key="m_id"
            ref="tree">
          </el-tree>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit(selectedRole)">确 定</el-button>
      </div>
    </el-dialog>
    <el-row style="display:inline-block;margin-top:10px; ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
        <el-breadcrumb-item>角色配置</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <role-create @onSubmit="listRoles()"></role-create>
    <el-row style="margin-right:5px;float:right;margin-bottom:5px; ">
            <el-col :span="18" style="margin-right:3px;">
                <el-input v-model="searchData" placeholder="输入角色名查询" @keyup.enter.native="search"></el-input>
            </el-col>
            <el-col :span="2">
                <el-button type="primary" @click="search">查询</el-button>
            </el-col>
        </el-row>
    <el-card  style="width: 98%;height:auto;">
      <el-table
        :data="roles"
        stripe
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          prop="r_id"
          label="id"
          >
        </el-table-column>
        <el-table-column
          prop="r_name"
          label="角色名"
          fit>
        </el-table-column>
        <el-table-column
          prop="r_name_zh"
          label="角色描述"
          fit>
        </el-table-column>
        <el-table-column
          label="状态"
          fit>
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.r_enabled ==='1'?true:false"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @change="(value) => commitStatusChange(value, scope.row)">
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
              @click="editRole(scope.row)">
              编辑
            </el-button>
            <el-button
              type="text"
              size="small"
              @click="deleteRole(scope.row)">
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
  import RoleCreate from './RoleCreate'
  import {reqRoleList,reqMenuList,reqUpdateRole,reqSelectMids,reqChangeRole,reqDeleteRole} from '@/api'
  export default {
    name: 'UserRole',
    components: {RoleCreate},
    data () {
      return {
        dialogFormVisible: false,
        roles: [],
        data:[],
        menus: [],
        selectedRole: [],
        selectedMenusIds: [],
        props: {
          id: 'm_id',
          label: 'm_name_zh',
          children: 'children'
        },
        limit: 5,
        total: null,
        page: 1,
        pageshow:true,
        searchData:'',
      }
    },
    created () {
      this.listRoles()
      this.listMenus()
    },
    computed: {
      tableHeight () {
        return window.innerHeight - 150
      }
    },
    methods: {
      async listRoles () {
          var _this = this
          const result = await reqRoleList();
          if (result && result.status === 200) {
              _this.data = result.data
              this.getList();
            }
        },
        // 处理数据
        getList() {
          let roles;
                //  es6过滤得到满足搜索条件的展示数据list
                if(this.searchData === ''){
                     roles = this.data
                }else{
                     roles = this.data.filter((item, index) =>
                   item.r_name.includes(this.searchData)             
                )
                }
                 //过滤分页
                this.roles = roles.filter((item, index) =>
                    index < this.page * this.limit && index >= this.limit * (this.page - 1)
                )
                //分页的总数据
                this.total = roles.length
               
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
     
      async listMenus () {
        var _this = this
        const result = await reqMenuList();
        if(result && result.status === 200){
          _this.menus = result.data
        }
      },
      async commitStatusChange (value, role) {
        const enabled = value===true?'1':'0'
        if (role.r_id !=='1') {   
          const result = await reqChangeRole({
              r_enabled: enabled,
              r_id: role.r_id
            })
            if (result && result.status === 200) {
                if (value) {
                  this.$message('角色 [' + role.r_name_zh + '] 已启用')
                } else {
                  this.$message('角色 [' + role.r_name_zh + '] 已禁用')
                }
              }
           
        } else {
          role.r_enabled = true
          this.$alert('无法禁用系统管理员！')
        }
         this.listRoles()
      },
     
      async editRole (role) {
      this.dialogFormVisible = true
      this.selectedRole = role
      let menuid = []
      const r_id = role.r_id;
      /** 将树的父级与子级关联移除 */  
       // this.$refs.tree.checkStrictly = true 
      const result = await reqSelectMids(r_id);
      if(result && result.status === 200){
         this.selectedMenusIds = result.data           
      }
      menuid = this.selectedMenusIds
       if (this.$refs.tree) {
         this.$refs.tree.setCheckedNodes([])
          let a= this.$refs.tree
              //默认选中的树的数据
        setTimeout(function () {
                  menuid.forEach((value)=>{
        //1. 勾选节点的 key 或者 data 2. boolean 类型，节点是否选中 3. boolean 类型，是否设置子节点 ，默认为 false）如果全部选中setCheckedKeys
                      a.setChecked(value,true,false)
                  })
        },100,a);
       }
                    
       /* for (let i = 0; i < role.menus.length; i++) {
        menuIds.push(role.menus[i].m_id)
        for (let j = 0; j < role.menus[i].children.length; j++) {
          menuIds.push(role.menus[i].children[j].m_id)
        }
      }   */
      /** 将树的父级与子级关联绑定 */  
      //this.$refs.tree.checkStrictly = false 
      /* // 判断树是否已经加载，第一次打开对话框前树不存在，会报错。所以需要设置 default-checked
      if (this.$refs.tree) {
        this.$refs.tree.setChecked(menuIds)
      } */
      },
      async onSubmit (role) {
        let _this = this
        let checkedKeys = this.$refs.tree.getCheckedKeys();
        let hafCheckedKeys = this.$refs.tree.getHalfCheckedKeys();
        let menu_ids = checkedKeys.concat(hafCheckedKeys)
        this.$axios.post('/admin/role/menus?r_id=' + role.r_id, {
          menusIds: menu_ids
        }).then(resp => {
          if (resp && resp.status === 200) {
            console.log(resp.data.data)
            this.listRoles()
          }
        })
        const updaterole = {
          r_id: role.r_id,
          r_name: role.r_name,
          r_name_zh: role.r_name_zh,                   
        }
        const result = await reqUpdateRole(updaterole);
        if (result && result.status === 200) {
            this.$alert('角色信息修改成功！')
            this.dialogFormVisible = false
            this.listRoles()
          }     
      },
       async deleteRole(role){
         const r_id = role.r_id
         const result = await reqDeleteRole(r_id)
         if(result && result.status ===200){
           this.$alert('用户信息删除成功')
              this.listRoles()
         }
      },

    }
  }
</script>

<style scoped>
  
</style>
