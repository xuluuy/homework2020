import Vue from 'vue'
import Router from 'vue-router'
// 导入刚才编写的组件
//import AdminIndex from '@/components/admin/AdminIndex'
//import DashboardAdmin from '@/components/admin/dashboard/index.vue'
//import Login from '@/components/Login'

//const Vue = require("vue");
//const Router = require("vue-router")
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    // 下面都是固定的写法
    {
      path:'/',
      redirect:'/login'
    },
      {
        path: '/login',
        name: 'Login',
        component: resolve => {
          require(['@/components/Login.vue'], resolve)},//懒加载路由
      },
      {
        path: '/register',
        name: 'Register',
        component:   resolve => {
          require(['@/components/Register.vue'], resolve)},
      },
    
    ]
})
export const createRouter = routes => new Router({
  mode: 'history',
  routes: [
    // 下面都是固定的写法
   {
     path:'/',
     redirect:'/login'
   },
      {
        path: '/login',
        name: 'Login',
        component: resolve => {
          require(['@/components/Login.vue'], resolve)},
      },
      {
        path: '/register',
        name: 'Register',
        component: resolve => {
          require(['@/components/Register.vue'], resolve)},
      },
     
    ]
})
