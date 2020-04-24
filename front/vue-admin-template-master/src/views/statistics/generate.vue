<template>
    <div class="app-container">
        <!--表单-->
        <el-form :inline="true" class="demo-form-inline">

        <el-form-item label="日期">
            <el-date-picker
            v-model="day"
            type="date"
            placeholder="选择要统计的日期"
            value-format="yyyy-MM-dd" />
        </el-form-item>

        <el-button
            :disabled="btnDisabled"
            type="primary"
            @click="generateData()">生成</el-button>
        </el-form>
  </div>

</template>
<script>
import statistics from "@/api/statistics";
export default {
    data() {
        return {
            btnDisabled: false,
            day: '',    // 选择生成的是哪一天
        }
    },
    created() {

    },
    methods: {
        // 生成注册登陆数据
        generateData() {
            statistics
                .generateData(this.day)
                .then(response => {
                    this.$message({
                        type: "success",
                        message: "生成数据成功!"
                    }); 
                    this.btnDisabled=true
                })
        },
    }
}
</script>