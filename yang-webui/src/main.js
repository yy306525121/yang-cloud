import Vue from 'vue'
import VueVideoPlayer from 'vue-video-player'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n
import '@/styles/index.scss' // global css
import App from './App'
import store from './store'
import router from './router'
// 视频播放
import 'video.js/dist/video-js.css'
import 'videojs-contrib-hls'

import '@/icons' // icon
import '@/permission' // permission control

Vue.use(ElementUI, { locale })
// 视频播放
Vue.use(VueVideoPlayer)

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
