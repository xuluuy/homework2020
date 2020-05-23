import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
//const Vue = require("vue");
//const Vuex = require("vuex")

export default new Vuex.Store({
    state: {
      user: {
        username: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).username
      },
      adminMenus: []
    },
    mutations: {
      initAdminMenu (state, menus) {
        state.adminMenus = menus
      },
      login (state, user) {
        state.user = user
        window.localStorage.setItem('user', JSON.stringify(user))
      },
      logout (state) {
        state.user = []
        state.adminMenus = []
        window.localStorage.removeItem('user')
      }
    }
  })