// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import Print from 'vue-print-nb'
import 'element-ui/lib/theme-chalk/index.css';　　
import 'echarts/theme/macarons.js'
import ECharts from 'vue-echarts'
import 'echarts/lib/chart/line'


import store from '@/store'
import '@/assets/icon/iconfont.css';


// 设置反向代理，前端请求默认发送到 http://localhost:8080/api
var axios = require('axios')　
axios.defaults.baseURL = 'http://localhost:8080/api'　 
// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
Vue.prototype.$axios = axios
Vue.config.productionTip = false

Vue.component('chart', ECharts)


//允许跨域
axios.defaults.withCredentials = true

Vue.use(ElementUI);
Vue.use(Print); 

router.beforeEach((to, from, next) => {
  if (store.state.user.username && to.path.startsWith('/admin')) {
    axios.get('/authentication').then(resp => {
      initAdminMenu(router, store)
    })
  }
  //如果需要认证
  if (to.meta.requireAuth) {
    if (store.state.user.username) {
      axios.get('/authentication').then(resp => {
        if (resp) next()
      })
    } else {
      next({
        path: 'login',
        query: {redirect: to.fullPath}
      })
    }
  } else {
    next()
  }
}
)
//菜单
const initAdminMenu = (router, store) => {
  if (store.state.adminMenus.length > 0) {
    return
  }
  axios.get('/menu').then(resp => {
    if (resp && resp.status === 200) {
      var fmtRoutes = formatRoutes(resp.data)
      router.addRoutes(fmtRoutes)
      store.commit('initAdminMenu', fmtRoutes)
    }
  })
}

//把从后台传来的菜单转换成vue能识别的
const formatRoutes = (routes) => {
  let fmtRoutes = []
  routes.forEach(route => {
    if (route.children) {
      route.children = formatRoutes(route.children)
    }

    let fmtRoute = {
      path: route.m_path,
      component: resolve => {
        require(['./components/admin/' + route.m_component + '.vue'], resolve)
      },
      name: route.m_name,
      nameZh: route.m_name_zh,
      iconCls: route.m_icon_cls,
      children: route.children
    }
    fmtRoutes.push(fmtRoute)
  })
  return fmtRoutes
}

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
