<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.title" placeholder="标题" style="width: 200px;" class="filter-item"
                @keyup.enter.native="handleFilter"></el-input>
      <el-input v-model="listQuery.name" placeholder="名称" style="width: 200px;" class="filter-item"
                @keyup.enter.native="handleFilter"></el-input>
      <el-button type="primary" class="filter-item" icon="el-icon-search" @click="handleFilter">搜索</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit"
                 @click="handleCreate">新建
      </el-button>
      <el-button class="filter-item" :loading="downloadLoading" @click="handleDownload" type="primary" icon="el-icon-download">导出</el-button>
    </div>
    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="ID" width="80">
        <template slot-scope="scope">
          <span>{{scope.row.id}}</span>
        </template>
      </el-table-column>
      <el-table-column width="200" align="center" label="标题">
        <template slot-scope="scope">
          <span>{{scope.row.title}}</span>
        </template>
      </el-table-column>
      <el-table-column width="150" align="center" label="名称">
        <template slot-scope="scope">
          <span>{{scope.row.name}}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="描述">
        <template slot-scope="scope">
          <span>{{scope.row.description}}</span>
        </template>
      </el-table-column>
      <el-table-column width="110" class="status-col" align="center" label="状态">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{scope.row.status}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column width="240" class="status-col" align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)"
                     :disabled="scope.row.status | hasDelete">编辑
          </el-button>
          <el-button type="danger" size="mini" :disabled="scope.row.status | hasDelete" @click="handleDelete">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-width="80px" label-position="left"
               style="width: 400px; margin-left: 50px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="temp.title" placeholder="请输入标题" class="filter-item"></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="temp.name" placeholder="请输入名称" class="filter-item"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="temp.description" placeholder="请输入名称" class="filter-item"></el-input>
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
  import {createSystem, deleteSystem, fetchList, updateSystem} from "@/api/system";
  import Pagination from '@/components/Pagination'

  export default {
    name: 'SystemList',
    components: {Pagination},
    filters: {
      statusFilter(status) {
        const statusMap = {
          '正常': 'success',
          draft: 'info',
          '已删除': 'danger'
        }
        return statusMap[status]
      },
      hasDelete(status) {
        const deletedMap = {
          '已删除': true,
          '正常': false
        }

        return deletedMap[status]
      }
    },

    data() {
      return {
        list: null,
        total: 0,
        listLoading: true,
        listQuery: {
          current: 1,
          size: 20
        },
        temp: {
          id: undefined,
          title: '',
          name: '',
          code: '',
          description: '',
          icon: '',
          basePath: ''
        },
        dialogStatus: '',
        dialogFormVisible: false,
        textMap: {
          update: '编辑',
          create: '创建'
        },
        rules: {
          title: [{required: true, message: '请输入标题', trigger: 'change'}]
        },
        downloadLoading: false
      }
    },


    created() {
      this.getList()
    },

    methods: {
      getList() {
        this.listLoading = true
        fetchList(this.listQuery).then(response => {
          this.list = response.records
          this.total = response.total
          this.listLoading = false
        })
      },
      handleFilter() {
        this.listQuery.page = 1
        this.getList()
      },

      handleSizeChange(val) {
        this.listQuery.size = val
        this.getList()
      },

      handleCurrentChange(val) {
        this.listQuery.current = val
        this.getList()
      },

      resetTemp() {
        this.temp = {
          id: undefined,
          title: '',
          name: '',
          code: '',
          description: '',
          icon: '',
          basePath: ''
        }
      },
      handleCreate() {
        this.resetTemp()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      createDate() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            createSystem(this.temp).then(response => {
              this.dialogFormVisible = false
              if (response.status) {
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
                  message: '创建失败',
                })
              }

            })
          }
        })
      },
      handleUpdate(row) {
        // copy obj
        this.temp = Object.assign({}, row)
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
      },
      updateDate() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.temp)
            updateSystem(tempData).then(() => {
              for (const v of this.list) {
                if (v.id === this.temp.id) {
                  const index = this.list.indexOf(v)
                  // 从index位置删除1条数据， 并添加this.temp数据
                  this.list.splice(index, 1, this.temp)
                  break
                }
              }
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '更新成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      handleDelete(row) {
        this.$confirm('此操作将永久删除记录,是否继续？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const tempData = Object.assign({}, row);
          deleteSystem(tempData.id).then(response => {
            if (response.status) {
              row.status = '已删除'
              this.$message({
                type: 'success',
                message: '删除成功'
              })
            } else {
              this.$notify.error({
                title: '失败',
                message: '删除失败',
              })
            }
          })

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },

      handleDownload() {
        this.downloadLoading = true
        import('@/utils/Export2Excel').then(excel => {
          const tHeader = ['标题', '名称', '描述', '状态']
          const filterVal = ['title', 'name', 'description', 'status']
          const data = this.formatJson(filterVal, this.list)
          excel.export_json_to_excel({
            header: tHeader,
            data,
            filename: '系统列表'
          })
          this.downloadLoading = false
        })
      },

      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => {
          if (j === 'timestamp') {
            return parseTime(v[j])
          } else {
            return v[j]
          }
        }))
      }
    }
  }
</script>

<style scoped>
  .edit-input {
    padding-right: 100px;
  }

  .cancel-btn {
    position: absolute;
    right: 15px;
    top: 10px;
  }
</style>
