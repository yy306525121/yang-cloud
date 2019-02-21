<template>
  <div class="app-container">
    <el-upload :drag="true" class="upload-demo" action="" :http-request="upload">
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip" slot="tip">只能上传jpg/png文件, 且不超过500kb</div>
    </el-upload>
  </div>
</template>

<script>
  import {uploadFile} from '@/api/upload'

  export default {
    name: "resourceEdit",
    created() {
      this.fetchParam()
    },
    methods: {
      fetchParam() {
        // console.log(this.$route.params.id)
      },
      upload(resource){
        // uploadFile(resource).then(response => {
        //   console.log(response)
        // })
        let formData = new FormData();
        formData.append('file', resource.file);

        uploadFile(formData).then(response => {
          console.log(response)
        })
      }
    }
  }
</script>

<style scoped>

</style>
