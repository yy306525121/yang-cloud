<template>
  <div class="app-container">

    <div class="filter-container">
      <el-button type="primary" class="filter-item" icon="el-icon-edit" @click="handleCreate">新建</el-button>
    </div>
    <el-table v-loading="listLoading" :data="list">
      <el-table-column align="center" label="ID">
        <template slot-scope="scope">
          <span>{{scope.row.id}}</span>
        </template>
      </el-table-column>
      <el-table-column header-align="center" align="center" label="名称">
        <template slot-scope="scope">
          <span>{{scope.row.name}}</span>
        </template>
      </el-table-column>
      <el-table-column header-align="center" align="center" label="路径">
        <template slot-scope="scope">
          <span>{{scope.row.path}}</span>
        </template>
      </el-table-column>
      <el-table-column header-align="center" align="center" label="创建时间">
        <template slot-scope="scope">
          <span>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>
      <el-table-column header-align="center" align="center" label="操作">
        <template slot-scope="scope">
          <!--<router-link :to="'/resource/edit/'+scope.row.id">-->
          <router-link :to="{name: 'ResourceUpload', params: {id: scope.row.id}}">
            <el-button size="small" type="primary" icon="el-icon-edit">编辑</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="titleMap[dialogStatus]" :visible.sync="dialogVisible" width="40%">
      <el-form ref="dataForm" :rule="rules" :model="temp" label-width="80px" label-position="left"
               style="width: 400px; margin-left: 30px">
        <el-upload :drag="true" class="upload-demo" action="" :http-request="upload" :show-file-list="showFileList" style="margin: 0 auto">
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        </el-upload>
        <el-form-item label="资源名" prop="name">
          <el-input v-model="temp.name" placeholder="请输入资源名"></el-input>
        </el-form-item>
        <el-form-item label="文件类型" prop="type">
          <el-input v-model="temp.type" readonly></el-input>
        </el-form-item>
        <el-form-item label="文件大小" prop="size">
          <el-input v-model="temp.size" readonly></el-input>
        </el-form-item>
        <el-form-item label="文件路径" prop="path">
          <el-input v-model="temp.path" readonly></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">返回</el-button>
        <el-button type="primary" @click="dialogStatus === 'create'?createDate():updateDate()">确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {uploadFile} from '@/api/upload';
  import {createResource, list} from "../../api/cms";

  export default {
    name: "resourceList",
    data() {
      return {
        list: null,
        total: 0,
        listLoading: true,
        listQuery: {
          current: 1,
          size: 20
        },
        titleMap: {
          update: '编辑资源',
          create: '新建资源'
        },
        dialogStatus: "",
        dialogVisible: false,
        rules: [],
        temp: {
          id: undefined,
          name: "",
          type: "",
          size: "",
          path: ""
        },
        showFileList: false
      }
    },
    created() {
      this.getList()
    },
    methods: {
      restTemp() {
        this.temp = {
          id: undefined,
          name: '',
          type: '',
          size: '',
          path: ''
        }
      },
      getList() {
        this.listLoading = true
        list(this.listQuery).then(response => {
          this.list = response.records
          this.total = response.total
          this.listLoading = false
        })
      },
      handleCreate() {
        this.dialogStatus = 'create'
        this.dialogVisible = true
        this.restTemp()
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      createDate(){
        this.$refs['dataForm'].validate((valid) => {
          if (valid){
            createResource(this.temp).then(response => {
              this.dialogVisible = false
              if (response.status){
                this.list.unshift(response.data)
                this.$notify({
                  title: '成功',
                  message: '创建成功',
                  type: 'success',
                  duration: 2000
                })
              } else {
                this.$notify.error({
                  title: '失败',
                  message: '创建失败'
                })
              }
            })
          }
        })
      },
      updateDate(){

      },
      upload(resource){
        let formData = new FormData();
        formData.append('file', resource.file);

        uploadFile(formData).then(response => {
          console.log(response)
          if (response.status){
            this.temp.name = response.data.name
            this.temp.size = response.data.size
            this.temp.type = response.data.type
            this.temp.path = response.data.path
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
