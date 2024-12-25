<template>
    <el-container>
      <el-main>
        <!-- 搜索表单 -->
        <el-row :gutter="20" class="mb-20">
          <el-col :span="4">
            <el-input v-model="search.name" placeholder="Id..."></el-input>
          </el-col>
          <el-col :span="4">
            <el-input v-model="search.category" placeholder="Name..."></el-input>
          </el-col>
          <el-col :span="4">
            <el-input v-model="search.status" placeholder="Position..."></el-input>
          </el-col>
          <el-col :span="4">
            <el-input v-model="search.name" placeholder="Role..."></el-input>
          </el-col>
          <el-col :span="4">
            <el-input v-model="search.category" placeholder="Organization..."></el-input>
          </el-col>
          <el-col :span="4" class="text-right">
            <el-button type="primary" @click="searchData" class="custom-small-button">Search</el-button>
            <el-button type="success" @click="addData" class="custom-small-button">Add</el-button>
          </el-col>
        </el-row>
        <!-- <el-row :gutter="20" class="mb-20">

        </el-row> -->
        <!-- 数据表格 -->
        <el-table :data="tableData" width="100%">
          <el-table-column prop="id" label="Id"></el-table-column>
          <el-table-column prop="name" label="Name"></el-table-column>
          <el-table-column prop="position" label="Position"></el-table-column>
          <el-table-column prop="role" label="Role"></el-table-column>
          <el-table-column prop="organization" label="Organization"></el-table-column>
          <el-table-column label="Action">
            <template #default="scope">
              <el-button size="small" @click="editData(scope.row)" class="custom-small-button">Edit</el-button>
              <el-button size="small" type="danger" @click="deleteData(scope.row)" class="custom-small-button">Delete</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页组件 -->
        <el-pagination
          background
          layout="prev, pager, next"
          :total="total"
          :page-size="pageSize"
          @current-change="handlePageChange"
          class="mt-20">
        </el-pagination>
      </el-main>
    </el-container>
  </template>
  
  <script>
  export default {
    data() {
      return {
        search: {
          name: '',
          category: '',
          status: ''
        },
        tableData: [],
        total: 0,
        pageSize: 10,
        currentPage: 1
      };
    },
    methods: {
      fetchData() {
        // 调用接口获取数据，暂时使用假数据
        this.tableData = [
          { name: 'data1', category: 'data1', status: 'data1' },
          { name: 'data1', category: 'data1', status: 'data1' },
          { name: 'data1', category: 'data1', status: 'data1' },
          { name: 'data1', category: 'data1', status: 'data1' },
          { name: 'data1', category: 'data1', status: 'data1' },
          { name: 'data1', category: 'data1', status: 'data1' },
          { name: 'data1', category: 'data1', status: 'data1' },
          { name: 'data1', category: 'data1', status: 'data1' },
          { name: 'data1', category: 'data1', status: 'data1' },
          { name: 'data1', category: 'data1', status: 'data1' },
          // ...更多假数据
        ];
        this.total = this.tableData.length;
      },
      searchData() {
        // 根据搜索条件调用接口获取数据
        console.log('搜索数据', this.search);
        this.fetchData();
      },
      addData() {
        // 添加数据的逻辑
        console.log('添加数据');
      },
      editData(row) {
        // 编辑数据的逻辑
        console.log('编辑数据', row);
      },
      deleteData(row) {
        // 删除数据的逻辑
        console.log('删除数据', row);
      },
      handlePageChange(page) {
        this.currentPage = page;
        this.fetchData();
      }
    },
    mounted() {
      this.fetchData();
    }
  };
  </script>
  
  <style scoped>
  .mb-20 {
    margin-bottom: 20px;
  }
  .mt-20 {
    margin-top: 20px;
  }
  .text-right {
    text-align: right;
  }
  .custom-small-button {
    padding: 5px 10px; /* 调整按钮内边距 */
    font-size: 12px; /* 调整字体大小 */
  }
  
  /* PC端样式 */
  @media (min-width: 768px) {
    .el-container {
      padding: 15px; /* 调整为较小的内边距 */
    }
  }
  
  /* 移动端样式 */
  @media (max-width: 767px) {
    .el-container {
      padding: 10px;
    }
    .el-col {
      width: 100%;
      margin-bottom: 10px;
    }
    .text-right {
      text-align: center;
    }
  }
  </style>
  