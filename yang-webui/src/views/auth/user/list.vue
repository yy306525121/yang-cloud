<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading" element-loading-text="拼命加载中" :data="list" fit highlight-current-row="true" style="width: 100%;">
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
        loadingList: true,
        listQuery: {
          current: 1,
          size: 20
        }
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList: function () {
        this.loadingList = true
        fetchList(this.listQuery).then(data => {
          this.list = data.records
          this.total = data.total
          this.loadingList = false
        })
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
