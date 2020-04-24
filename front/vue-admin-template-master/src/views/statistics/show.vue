<template>
  <div class="app-container">
    <!--表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-select v-model="queryObj.dataType" clearable placeholder="请选择">
          <el-option label="学员登录数统计" value="login_num"/>
          <el-option label="学员注册数统计" value="register_num"/>
          <el-option label="课程播放数统计" value="video_view_num"/>
          <el-option label="每日课程数统计" value="course_num"/>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-date-picker
          v-model="queryObj.beginTime"
          type="date"
          placeholder="选择开始日期"
          value-format="yyyy-MM-dd"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="queryObj.endTime"
          type="date"
          placeholder="选择截止日期"
          value-format="yyyy-MM-dd"
        />
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="showResult()">查询</el-button>
    </el-form>

    <div class="chart-container">
      <div id="chart" class="chart" style="height:500px;width:100%"/>
    </div>
  </div>
</template>
<script>
// <!-- 引入 echarts.js -->
import echarts from "echarts";
import statistics from "@/api/statistics";
export default {
    data() {
        return {
            queryObj: {
                dataType: '',
                beginTime: '',
                endTime: ''
            },
            xData: [],
            yData: [],
            title: '',

        }
    },
    created() {
    },
    methods: {
        showResult() {
            statistics
                .showStatistics(this.queryObj)
                .then(response => {
                    this.xData = response.data.xData
                    this.yData = response.data.yData
                    switch (this.queryObj.dataType) {
                        case "login_num":
                            this.title = "学员登录数统计"
                            break;
                        case "register_num":
                            this.title = "学员注册数统计"
                            break;
                        case "video_view_num":
                            this.title = "课程播放数统计"
                            break;
                        case "course_num":
                            this.title = "每日课程数统计"
                            break;
                        default:
                            break;
                    }
                    this.createEcharts()
                })
        },
        // 画图
        createEcharts() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('chart'));
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: this.title
                },
                tooltip: {},
                legend: {
                    data:['统计']
                },
                xAxis: {
                    data: this.xData
                },
                yAxis: {},
                series: [{
                    name: '统计',
                    type: 'bar',
                    data: this.yData
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    }
}
</script>