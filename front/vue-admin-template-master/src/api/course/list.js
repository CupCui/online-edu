import course from "@/api/course/course"
export default {
    data() {
        return {
            queryCourse: {
                title: '',
                status: ''
            },
            courseList: null,
            pageNum: 1,
            pageSize: 4,
            total: 0
        }
    },
    methods: {
        getCoursePageByCondition() {
            course
                .getCoursePageByCondition(this.pageNum, this.pageSize, this.queryCourse)
                .then(response => {
                    this.total = response.data.total,
                    this.courseList = response.data.courseList
                })
        },
        // 删除课程
        deleteCourseInfo(courseId) {
            this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
              })
                .then(() => {
                    course
                    .deleteCourseInfo(courseId)
                    .then(response => {
                        this.$message({
                            type: "success",
                            message: "更新课程成功!"
                          }); 
                          this.getCoursePageByCondition()   // 刷新
                    })
                  });
            
        }
    },
    created() {
        this.getCoursePageByCondition()
    }
}