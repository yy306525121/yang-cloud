<template>
  <div :class="{'hidden':hidden}" class="pagination-container">
    <el-pagination
      :background="background"
      layout="prev, pager, next"
      :total="50">
    </el-pagination>

  </div>
</template>

<script>
  import {scrollTo} from '@/utils/scrollTo'

  export default {
    name: 'Pagination',
    props: {
      total: {
        // 总条目数
        required: true,
        type: Number
      },
      current: {
        // 当前页码
        type: Number,
        default: 1
      },
      size: {
        // 每页条数
        type: Number,
        default: 20
      },
      pageSizes: {
        //每页显示个数选择器的选项设置
        type: Array,
        default() {
          return [10, 20, 30, 50]
        }
      },
      layout: {
        //组件布局
        type: String,
        default: 'total, sizes, prev, pager, next, jumper'
      },
      background: {
        //是否为分页按钮添加背景色
        type: Boolean,
        default: true
      },
      autoScroll: {
        type: Boolean,
        default: true
      },
      hidden: {
        type: Boolean,
        default: false
      }
    },
    computed: {
      currentPage: {
        get() {
          return this.current
        },
        set(val) {
          this.$emit('update:current', val)
        }
      },
      pageSize: {
        get() {
          return this.size
        },
        set(val) {
          this.$emit('update:size', val)
        }
      }
    },
    methods: {
      handleSizeChange(val) {
        this.$emit('pagination', {page: this.current, limit: val})
        if (this.autoScroll) {
          scrollTo(0, 800)
        }
      },
      handleCurrentChange(val) {
        this.$emit('pagination', {page: val, limit: this.size})
        if (this.autoScroll) {
          scrollTo(0, 800)
        }
      }
    }
  }
</script>

<style scoped>
  .pagination-container {
    background: #fff;
    padding: 32px 16px;
  }
  .pagination-container.hidden {
    display: none;
  }
</style>
