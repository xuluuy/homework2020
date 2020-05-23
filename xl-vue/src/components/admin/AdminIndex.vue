<template>
  <el-container id="admin-body">
    <el-header style="z-index: 1;height: 60px;margin-left: -15px">
      <Header style="position: absolute;width: 99%;"></Header>
    </el-header>
    <!--<el-row style="height: 100%;">-->
    <el-container>
      <el-aside style="height: 100%;width:auto;">
        <admin-menu></admin-menu>
      </el-aside>
      <el-main class="main_content">
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  import AdminMenu from './AdminMenu'
  import Header from './Header'

  export default {
    name: 'AdminIndex',
    components: {AdminMenu, Header},
    
    mounted () {
      // this.$router.replace('/admin/dashboard')
    }
  }
</script>

<style scoped>
  #admin-body {
  height: 95vh;
 
}
.main_content{
  
  background: #f6f6f6;
}

</style>
