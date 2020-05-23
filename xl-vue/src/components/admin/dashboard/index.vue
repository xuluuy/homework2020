<template>
  <div class="page">
   <template  v-for="(item,i) in menu" >
    <div class="box" v-for="child in item.children" :key="child.path" @click="change(child.path)">  
{{child.nameZh}}</div>
   </template>  
  </div>
</template>

<script>
export default {
  name: 'AdminIndex',
      data () {
        return{
         
          menu:[],
        }
      },
      created(){
       this.menu=this.$store.state.adminMenus
      
      },
      methods:{
        change(value){
         this.$router.replace({path:value})
        }
      }
    
}
</script>

<style >
.page{
background: #fff;
padding: 50px 20px;
display: flex;
flex-flow:row wrap;
}
.box{
  border:1px solid #CCE1F3;
  border-radius: 5px;
  width: 200px;
  height: 100px;
  margin:20px;
  text-align: center;
  line-height: 100px;
   color: #505458;
   cursor: pointer;
}
.box:hover{
   box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
</style>
