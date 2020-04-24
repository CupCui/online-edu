<template>
  <div class="app-container">
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="teacherCondition.name" placeholder="讲师名" />
      </el-form-item>

      <el-form-item>
        <el-select v-model="teacherCondition.level" clearable placeholder="讲师头衔">
          <el-option :value="1" label="高级讲师" />
          <el-option :value="2" label="首席讲师" />
        </el-select>
      </el-form-item>

      <el-form-item label="添加时间">
        <el-date-picker
          v-model="teacherCondition.beginTime"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="teacherCondition.endTime"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="queryTeacherPageByCondition()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table
      v-loading="listLoading"
      :data="teacherList"
      element-loading-text="数据加载中"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">{{ (pageNum - 1) * pageSize + scope.$index + 1 }}</template>
      </el-table-column>

      <el-table-column prop="name" label="名称" width="80" />

      <el-table-column label="头衔" width="80">
        <template slot-scope="scope">{{ scope.row.level===1?'高级讲师':'首席讲师' }}</template>
      </el-table-column>

      <el-table-column prop="intro" label="资历" />

      <el-table-column prop="gmtCreate" label="添加时间" width="160" />

      <el-table-column prop="sort" label="排序" width="60" />

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/teacher/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="deleteTeacherById(scope.row.id)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      :current-page="pageNum"
      :page-size="pageSize"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="queryTeacherPageByCondition"
    />
  </div>
</template>

<script>
import teacher from "@/api/teacher";

export default {
  // 初始化数据
  data() {
    return {
      pageNum: 1,
      pageSize: 4,
      total: 0,
      listLoading: false,
      teacherList: null,
      teacherCondition: {}
    };
  },

  // 定义方法
  methods: {
    queryTeacherPageByCondition(pageNumUI = 1) {
      this.pageNum = pageNumUI;
      teacher
        .queryTeacherPageByCondition(
          this.pageNum,
          this.pageSize,
          this.teacherCondition
        )
        .then(response => {
          this.teacherList = response.data.teacherList;
          this.total = response.data.totalPage;
        });
    },

    // 2.删除讲师功能
    deleteTeacherById(teacherId) {
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          teacher.deleteTeacherById(teacherId).then(response => {
            // 删除成功
            this.$message({
              type: "success",
              message: "删除成功!"
            });
            // 刷新页面
            this.queryTeacherPageByCondition();
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    }
  },
  // 页面加载时执行的方法
  created() {
    this.queryTeacherPageByCondition();
  }
};
</script>