/*
ajax请求函数模块
返回值: promise对象(异步返回的数据是: response.data)
 */
//import axios from 'axios'
const axios = require("axios")
export default function ajax (url, data={}, type='GET') {

    return new Promise(function (resolve, reject) {
      // 执行异步ajax请求
      let promise
      if (type === 'GET') {
        // 发送get请求
        promise = axios.get(url,data)
      } else {
        // 发送post请求
        promise = axios.post(url, data)
      }
      promise.then(function (response) {
        // 成功了调用resolve()
        resolve(response)
      }).catch(function (error) {
        //失败了调用reject() 
        reject(error)
        //MessageChannel.error('请求出错了'+error.message)
      })
    })
  }
  
  /*
  const response = await ajax()
  const result = response.data
  
  const resule = await ajax()
   */