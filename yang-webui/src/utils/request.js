import axios from 'axios'
import {Message, MessageBox} from 'element-ui'
import store from '../store'
import {getAccessToken} from '@/utils/auth'


// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_API, // api 的 base_url
  timeout: 5000 // 请求超时时间
})

// request拦截器
service.interceptors.request.use(
  config => {

    if (store.getters.access_token) {
      config.headers['Authorization'] = 'Bearer ' + getAccessToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    }

    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
    /**
     * code为非20000是抛错 可结合自己业务进行修改
     */
    const res = response.data
    if (response.status !== 200) {
      return Promise.reject('error')
    } else {
      return response.data
    }
  },
  error => {
    console.log(error.response.status)
    if (error.response) {
      if (error.response.status === 401) {
        // token失效
        let refreshToken = store.getters.refresh_token
        console.log("获取到refreshToken: " + refreshToken);
        if (refreshToken != null) {
          let config = error.config
          console.log("获取到config: " + config);
          store.dispatch('DeleteAccessToken').then((res) => {
            //清除AccessToken成功
          }).catch((err) => {

          })
          if (!config.isRetryRequest) {
            return store.dispatch('flushToken').then((res) => {
              config.isRetryRequest = true
              //重新请求
              // config.headers['Authorization'] = 'Bearer ' + getAccessToken() // 让请求携带新的token
              location.reload()
              // return axios(config)
            }).catch((err) => {
              //刷新失败， 跳转到登陆页
              MessageBox.confirm(
                '你已被登出，可以取消继续留在该页面，或者重新登录',
                '确定登出',
                {
                  confirmButtonText: '重新登录',
                  cancelButtonText: '取消',
                  type: 'warning'
                }
              ).then(() => {
                store.dispatch('FedLogOut').then(() => {
                  location.reload() // 为了重新实例化vue-router对象 避免bug
                })
              })
            })
          }
        }
      } else {
        Message({
          message: error.message,
          type: 'error',
          duration: 5 * 1000
        });
        console.log(error)
      }

      return Promise.reject(error)
    }
  }
)

export default service
