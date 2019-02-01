<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.title" placeholder="请输入与内容" style="width: 200px;" class="filter-item"></el-input>
      <el-button class="filter-item" type="primary" icon="el-icon-search">搜索</el-button>
    </div>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="编号" width="180"></el-table-column>
      <el-table-column prop="icon" label="图标" width="100"></el-table-column>
      <el-table-column prop="title" label="系统标题" width="180"></el-table-column>
      <el-table-column prop="name" label="系统名称" width="180"></el-table-column>
      <el-table-column prop="basePath" label="根目录"></el-table-column>
      <el-table-column label="状态" width="100">
        <template slot-scope="scope">
          <el-tag type="success">有效</el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="180">
        <template slot-scope="scope">
          <el-button size="small">编辑</el-button>
          <el-button size="small" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit"></pagination>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [{
          "id": 1,
          "icon": 'a',
          "name": "yang-auth",
          "code": "auth",
          "title": '权限系统',
          "basePath": "http://www.baidu.com",
          "description": null,
          "status": "删除"
        },
          {
            "id": 2,
            "icon": 'a',
            "name": "权限系统",
            "code": "auth",
            "title": '权限系统',
            "basePath": "http://www.baidu.com",
            "description": null,
            "status": "正常"
          }, {
            "id": 3,
            "icon": 'a',
            "name": "权限系统",
            "code": "auth",
            "title": '权限系统',
            "basePath": "http://www.baidu.com",
            "description": null,
            "status": "正常"
          }
        ],
        listQuery: {
          title: '',
          page: 1,
          limit: 20,
        },
        total: 0
      }
    },
    methods: {
      getList(){
        this.listLoading = true
      }
    }
  }
</script>

<style scoped>

</style>
