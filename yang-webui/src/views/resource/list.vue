<template>
  <div class="app-container">
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
  </div>
</template>

<script>
  import {list} from '@/api/resource'

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
        }
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        this.listLoading = true
        list(this.listQuery).then(response => {
          this.list = response.records
          this.total = response.total
          this.listLoading = false
        })
      }
    }
  }
</script>

<style scoped>

</style>
