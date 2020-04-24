<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="信息描述">
        <el-tag type="info">excel模版说明</el-tag>
        <el-tag>
          <i class="el-icon-download" />
          <a :href="'/excel/%E8%AF%BE%E7%A8%9D%BF.xls'">点击下载模版</a>
        </el-tag>
      </el-form-item>

      <el-form-item label="选择Excel">
        <el-upload
          ref="upload"
          :auto-upload="false"
          :on-success="fileUploadSuccess"
          :on-error="fileUploadError"
          :disabled="importBtnDisabled"
          :limit="1"
          :action="BASE_API+'/edu/subject/uploadSubject'"
          name="file"
          accept="application/vnd.ms-excel"
        >
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <el-button
            :loading="loading"
            style="margin-left: 10px;"
            size="small"
            type="success"
            @click="submitUpload"
          >{{ fileUploadBtnText }}</el-button>
        </el-upload>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
    data() {
        return {
            importBtnDisabled: false,    // 上传按钮是否可用
            BASE_API: process.env.BASE_API,
            loading: false,
            fileUploadBtnText: "上传到服务器"   // 按钮上文字
        }
    },
    methods: {
        // 文件上传成功
        fileUploadSuccess(response) {
             if (response.success===true) {
                 this.fileUploadBtnText = "导入文件成功"
                 this.loading= false
                 this.$message({
                    type: "success",
                    message: "导入文件成功!"
                 });
             }
             else {
                this.fileUploadBtnText = "导入文件失败"
                 this.loading= false
                 this.$message({
                    type: "success",
                    message: "导入文件失败!"
                 });
             }
        },
        // 文件上传错误
        fileUploadError() {

        },
        // 3.点击上传按钮执行
        submitUpload() {
            // 提交数据信息
            this.$refs.upload.submit();
            // 设置点击按钮不可用
            this.importBtnDisabled= true
            this.loading= true
            this.fileUploadBtnText = "文件正在上传"
        }
    }
};
</script>