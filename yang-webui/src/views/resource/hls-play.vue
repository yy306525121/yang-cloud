<template>
  <div class="app-container">
    <div class="player">
      <video-player class="video-player vjs-custom-skin" ref="videoPlayer" :playsinline="true" :options="playerOptions">

      </video-player>
    </div>
  </div>
</template>

<script>
  import {videoPlayer} from 'vue-video-player'
  import 'videojs-flash'

  export default {
    name: "play",
    data() {
      return {
        playerOptions: {
          autoplay: false,
          controls: true,
          techOrder: ['flash', 'html5'],
          sourceOrder: true,
          flash: {hls: {withCredentials: false}},
          html5: {hls: {withCredentials: false}},
          sources: [
            //   {
            //   type: 'rtmp/mp4',
            //   src: 'rtmp://184.72.239.149/vod/&mp4:BigBuckBunny_115k.mov'
            // },
            // {
            //   withCredentials: false,
            //   type: 'application/x-mpegURL',
            //   src: 'http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8'
            // }
            {
              withCredentials: false,
              type: 'video/mp4',
              src: 'http://pn9hjbdbs.bkt.clouddn.com/11%E5%8F%AF%E9%9D%A0%E6%B6%88%E6%81%AF%E6%9C%8D%E5%8A%A1%E7%9A%84%E8%AE%BE%E8%AE%A1%E4%B8%8E%E5%AE%9E%E7%8E%B0--%E6%B6%88%E6%81%AF%E7%AE%A1%E7%90%86%E5%AD%90%E7%B3%BB%E7%BB%9F.mp4'
            }
          ],
          poster: "http://pm1lr64p5.bkt.clouddn.com/2.jpg",

        }
      }
    },
    components: {
      videoPlayer
    },
    methods: {

    },
    computed: {
      player() {
        return this.$refs.videoPlayer.player
      }
    }
  }
</script>

<style scoped>

</style>
