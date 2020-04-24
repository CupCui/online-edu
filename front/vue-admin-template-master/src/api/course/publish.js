import course from "@/api/course/course"
export default {
    data() {
        return {
            saveBtnDisabled: false,
            courseId: '',
            courseConfirm: {},
        }
    },
    methods: {
        // 1.课程发布信息确认
        getCourseConfirmInfo() {
            course
                .getCourseConfirmInfo(this.courseId)
                .then(response => {
                    console.log(response)
                    this.courseConfirm = response.data.courseConfirm
                })
        },
        // 2.发布课程
        publishCourse() {
            this.$confirm("是否确定发布课程?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
              })
                .then(() => {
                    course
                    .publishCourse(this.courseId)
                    .then(response => {
                        this.$message({
                            type: "success",
                            message: "课程发布成功!"
                        }); 
                        this.$router.push({ path: '/course/list'})
                    })
                  });
        },
        previous() {
            console.log('previous')
            this.$router.push({ path: '/course/chapter/' + this.courseId})
        },

        next() {
            console.log('next')
            this.$router.push({ path: '/course/publish/' + this.courseId})
        }
    },
    created() {
        this.courseId = this.$route.params.courseId
        this.getCourseConfirmInfo()
    }
}