<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.username" placeholder="用户名" class="filter-item" style="width: 200px;" @keyup.enter.native="handleFilter"></el-input>
      <el-button type="primary" class="filter-item" icon="el-icon-search" @click="handleFilter">搜索</el-button>
      <el-button type="primary" class="filter-item" icon="el-icon-edit" @click="handleCreate">新建</el-button>
    </div>
    <el-table
      v-loading="listLoading" element-loading-text="拼命加载中" :data="list" fit highlight-current-row style="width: 100%;">
      <el-table-column label="ID" align="center" width="80">
        <template slot-scope="scope">
          <span>{{scope.row.id}}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户名" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.username}}</span>
        </template>
      </el-table-column>
      <el-table-column label="昵称" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.nickname}}</span>
        </template>
      </el-table-column>
      <el-table-column header-align="center" align="center" label="邮箱">
        <template slot-scope="scope">
          <span>{{scope.row.email}}</span>
        </template>
      </el-table-column>
      <el-table-column header-align="center" align="center" label="是否已激活">
        <template slot-scope="scope">
          <el-tag type="scope.row.activated | activatedFilter">{{scope.row.activated}}</el-tag>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogVisible" width="30%">
      <el-form ref="dataForm" :model="temp" label-width="80px">
        <el-form-item label=""></el-form-item>
      </el-form>
      <span>这是一段信息</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {fetchList} from '@/api/user'

  export default {
    name: "userList",
    data() {
      return {
        list: null,
        total: 0,
        listLoading: true,
        listQuery: {
          username: "",
          current: 1,
          size: 20
        },
        dialogVisible: false,
        textMap: {
          update: "编辑用户",
          create: "新建用户"
        }
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList: function () {
        this.listLoading = true
        fetchList(this.listQuery).then(data => {
          this.list = data.records
          this.total = data.total
          this.listLoading = false
        })
      },
      handleFilter: function(){
        this.listQuery.page = 1
        this.getList()
      },
      handleCreate: function(){
        this.dialogStatus = 'create'
        this.dialogVisible = true
      }
    },
    filters: {
      activatedFilter(activated){
        const activatedMap = {
          true: 'success',
          false: 'danger'
        }
      }
    }
  }
</script>

<style scoped>

</style>
