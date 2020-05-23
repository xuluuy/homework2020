<template>

  <el-menu
    :default-active="onRoutes"
    class="el-menu-admin el-menu-vertical-demo"
    router
    background-color="#2e4c64"
    mode="vertical"  
    text-color="#bfcbd9"
    active-text-color="#ffd04b"
    :collapse="isCollapse"
    unique-opened>
    <!-- 折叠按钮 -->
        <div class="collapse-btn" @click="collapseChage">
            <i v-if="!isCollapse" class="el-icon-s-unfold"></i>
            <i v-else class="el-icon-s-fold"></i>
        </div>
    <!--<div style="height: 80px;"></div>-->
    <template v-for="(item,i) in adminMenus" >
     
      <!--index 没有用但是必需字段-->
      <el-submenu  :key="i" :index="i + ''" style="text-align: left">
   
        <template slot="title">
        <i :class="item.iconCls" class="icon_color"></i>
        <span slot="title" style="font-size: 14px;">         
          {{item.nameZh}}
        </span>
        </template>
        <el-menu-item v-for="child in item.children" :key="child.path" :index="child.path">
          <i :class="child.icon" class="icon_color"></i>
           <span slot="title" style="font-size: 12px;">         
          {{ child.nameZh }}
        </span>
         
        </el-menu-item>
      </el-submenu>   
      
    </template>
  </el-menu>

</template>

<script>

    export default {
      name: 'AdminMenu',
      data () {
        return {
          isCollapse: false,      
        }
      },
      computed: {
        adminMenus () {
          return this.$store.state.adminMenus
        },
         onRoutes() {
            return this.$route.path;
        }
      },
      methods:{
    // 侧边栏折叠
        collapseChage() {
            this.isCollapse = !this.isCollapse;
           
        },
      },
     
    }
</script>

<style scoped>
  .el-menu-admin {
    border-radius: 5px;
    height: 100%;
    text-align: center;
    margin-left: 3px;
  }
   .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
  .collapse-btn {    
    cursor: pointer;
    display: inline-block;
    color:#bfcbd9;
    font-size: 25px;  
}
.icon_color {
  color: #bfcbd9;
  font-size: 16px;
  margin: 2px;
}
 
</style>
