import subject from "@/api/subject/subject"
export default {
    data() {
        return {
            subjectList: [], //学科分类列表数据
            defaultProps: {
                children: 'children',
                label: 'title'
            }, // 该树默认数据
            filterText: '',  // 过滤节点字段
            dialogFormVisible: false,   // 默认不弹窗
            subjectUI: {    // 页面输入的课程分类信息
                title: '',
                parentId: '',
            }
        }
    },
    methods: {
        // 添加一级或二级分类
        saveParentOrChildSubject() {
            if (this.subjectUI.parentId) {
                this.saveChildSubject();
            } else {
                this.saveParentSubject();
            }
        },
        // 3.添加一级分类弹窗
        openParentDialog() {
            this.dialogFormVisible=true // 弹框
            this.subjectUI = {}   // 清空弹框内容
        },
        // 3.添加二级分类弹窗
        openChildDialog(data) { // data传递parentId
            this.subjectUI.parentId = data.id
            this.dialogFormVisible=true // 弹框
            this.subjectUI.title = ''   // 清空弹框内容
        },
        // 1.添加一级分类课程分类
        saveParentSubject() {
            subject
                .saveParentSubject(this.subjectUI)
                .then(resposne => {
                    this.$message({
                        type: "success",
                        message: "添加成功!"
                      }); 
                    this.dialogFormVisible=false // 取消弹框
                    this.queryAllSubject()    // 刷新页面
                })
        },
        // 2.添加二级分类课程分类
        saveChildSubject() {
            subject
                .saveChildSubject(this.subjectUI)
                .then(resposne => {
                    this.$message({
                        type: "success",
                        message: "添加成功!"
                      }); 
                    this.dialogFormVisible=false // 取消弹框
                    this.queryAllSubject()    // 刷新页面
                })
        },
        remove(node, data) {
            this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
              })
                .then(() => {
                    subject
                    .deleteSubjectById(data.id)
                    .then(response => {
                        this.$message({
                            type: "success",
                            message: "删除成功!"
                          }); 
                        this.$refs.subjectTree.remove(node) // 不需要查询数据库，直接把元素从页面删除
                    })
                })

        },
        queryAllSubject() {
            subject
                .queryAllSubject()
                .then(response => {
                    this.subjectList = response.data.allSubjects
                })
        },
        // 过滤方法
        filterNode(value, data) {
            if (!value) return true;
            return data.title.indexOf(value) !== -1;
          }
    },
    watch: {
        filterText(val) {
          this.$refs.subjectTree.filter(val);
        }
      },
    created() {
        this.queryAllSubject()
    }
}