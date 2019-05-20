<template>
  <div class="app-container">
    <el-upload :drag="true" class="upload-demo" action="" :http-request="upload" :on-change="onFileChange">
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip" slot="tip">只能上传jpg/png文件, 且不超过500kb</div>
    </el-upload>
    <span>{{progress}}</span>
  </div>
</template>

<script>
  import {blockUpload} from '@/api/upload'
  import {getMd5, getMD52} from '@/utils/md5'

  export default {
    name: "resourceEdit",
    data() {
      return {
        currentChunk: 0,
        time: new Date().getTime(),
        progress: 0
      }
    },
    created() {
      this.fetchParam()
    },
    methods: {
      fetchParam() {
        // console.log(this.$route.params.id)
      },
      onFileChange(file, fileList) {
        console.log(file.raw);
        let md5 = getMD52(file.raw);
        console.log("md5: " + md5);
      },
      upload(resource) {
        // uploadFile(resource).then(response => {
        //   console.log(response)
        // })
        console.log(resource.file.raw);
        let md5 = getMd5(resource.file.raw);
        let formData = new FormData();
        let blockSize = 1024 * 1000;
        let blockNum = Math.ceil(resource.file.size / blockSize);
        let nextSize = Math.min((this.currentChunk + 1) * blockSize, resource.file.size);
        let fileData = resource.file.slice(this.currentChunk * blockSize, nextSize);
        console.log(fileData);
        formData.append('file', fileData);
        formData.append("name", resource.file.name);
        formData.append("totalSize", resource.file.size);
        formData.append("currentSize", fileData.size);
        formData.append("md5", 1);
        formData.append("type", resource.file.type);
        formData.append("chunks", blockNum);
        formData.append("chunk", this.currentChunk);

        blockUpload(formData).then(response => {

          this.progress = ((this.currentChunk + 1) * 100) / blockNum;
          console.log(response);
          if (resource.file.size <= nextSize) {
            this.$message({
              message: "上传成功",
              type: "success"
            });
            this.currentChunk = 0;
            return;
          }
          ++this.currentChunk;
          this.upload(resource);
        })
      }
    }
  }
</script>

<style scoped>

</style>
