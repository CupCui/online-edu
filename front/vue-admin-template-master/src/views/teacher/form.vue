<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacherParam.name" />
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input v-model="teacherParam.sort" controls-position="right" min="0" />
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacherParam.level" clearable placeholder="请选择">
          <!--
            数据类型一定要和取出的json中的一致，否则没法回填
            因此，这里value使用动态绑定的值，保证其数据类型是number
          -->
          <el-option :value="1" label="高级讲师" />
          <el-option :value="2" label="首席讲师" />
        </el-select>
      </el-form-item>
      <el-form-item label="讲师资历">
        <el-input v-model="teacherParam.career" />
      </el-form-item>
      <el-form-item label="讲师简介">
        <el-input v-model="teacherParam.intro" :rows="10" type="textarea" />
      </el-form-item>

      <!-- 讲师头像 -->
      <el-form-item label="讲师头像">
        <!-- 头衔缩略图 -->
        <pan-thumb :image="teacherParam.avatar"/>
        <!-- 文件上传按钮 -->
        <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">更换头像</el-button>

        <!--
        v-show：是否显示上传组件
        :key：类似于id，如果一个页面多个图片上传控件，可以做区分
        :url：后台上传的url地址
        @close：关闭上传组件
        @crop-upload-success：上传成功后的回调-->
        <image-cropper
          v-show="imagecropperShow"
          :width="300"
          :height="300"
          :key="imagecropperKey"
          :url="BASE_API + '/oss/uploadFile'"
          field="file"
          @close="close"
          @crop-upload-success="cropSuccess"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import teacher from "@/api/teacher";
import ImageCropper from "@/components/ImageCropper";
import PanThumb from "@/components/PanThumb";

const defaultForm = {
  id: "",
  name: "",
  sort: 0,
  level: "",
  career: "",
  intro: "",
  avatar: ""
};

export default {
    // 声明组件
    components: {
        ImageCropper,
        PanThumb
    },
  // 页面初始化数据
  data() {
    return {
      teacherParam: defaultForm,
      // v-show：是否显示上传组件
      imagecropperShow: false,
      // :key：类似于id，如果一个页面多个图片上传控件，可以做区分
      imagecropperKey: 0,
      BASE_API: process.env.BASE_API
      
    };
  },
  // 定义方法
  methods: {
      // 1.关闭上传弹窗组件
      close() {
            this.imagecropperShow = false
            this.imagecropperKey = this.imagecropperKey + 1
      },

        // 文件上传成功后执行的方法
      cropSuccess(response) {
          // 图片回显
          this.teacherParam.avatar = response.url
          // 关闭弹窗
          this.imagecropperShow = false
          // 避免重复使用上传组件
          this.imagecropperKey = this.imagecropperKey + 1
      },

    // 1. 保存讲师
    saveTeacher() {
      teacher.saveTeacher(this.teacherParam).then(response => {
        // a.提示添加成功
        this.$message({
          type: "success",
          message: "添加讲师成功!"
        });
        // b.刷新页面
        this.$router.push({ path: "/teacher/list" });
      });
    },
    // 2.根据id查询讲师
    queryTeacherById() {
      teacher
        .queryTeacherById(this.$route.params.teacherId) //获取页面路由参数
        .then(response => {
          this.teacherParam = response.data.teacher;
        });
    },
    // 3.修改讲师
    updateTeacher() {
      teacher
        .updateTeacher(this.teacherParam) //获取页面路由参数
        .then(response => {
          // a.提示修改成功
          this.$message({
            type: "success",
            message: "修改讲师成功!"
          });
          // b.刷新页面
          this.$router.push({ path: "/teacher/list" });
        });
    },
    // 4.修改或保存讲师
    saveOrUpdate() {
      if (this.$route.params.teacherId) {
        // 如果路由中有id才执行
        this.updateTeacher();
      } else {
        this.saveTeacher();
      }
    }
  },

  created() {
    if (this.$route.params.teacherId) {
      // 如果路由中有id才执行
      this.queryTeacherById();
    }
  },
  // 4.修改或保存讲师
  watch: {
      $route(to, from) {
          console.log("router change")
          this.teacherParam = defaultForm
      }
  }
};
</script>