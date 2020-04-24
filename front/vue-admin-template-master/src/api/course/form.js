import course from "@/api/course/course"
import Tinymce from '@/components/Tinymce'

export default {
  components: { Tinymce },
  data() {
    return {
        BASE_API: process.env.BASE_API,
        saveBtnDisabled: false,
        // 讲师信息
        teacherList: [],
        // 一级课程分类
        parentSubjectList: [],
        // 二级课程分类
        childSubjectList: [],
        courseId: '',
        courseInfo: {
            id: '',
            teacherId: '',
            parentSubjectId: '',
            childSubjectId: '',
            cover: '/static/upload.png',
            title: '',
            price: 0,
            lessonNum: 0,
            description: ''
        }
    }
  },
  methods: {
    // 1.查询所有讲师
    getAllTeacher() {
        course
            .getAllTeacher()
            .then(response => {
                this.teacherList = response.data.teacherList
            })
    },
    // 2.查询所有课程
    getAllSubject() {
        course
            .getAllSubject()
            .then(response => {
                this.parentSubjectList = response.data.allSubjects
            })
    },
    // 3.查询二级分类
    getAllChildSubject(currentParentId) {
        // 迭代parentSubjectList
        for (let index = 0; index < this.parentSubjectList.length; index++) {
            const parentSubject = this.parentSubjectList[index];
            // 判断currentParentId与parentSubject
            if (currentParentId === parentSubject.id) {
                // 取得当前parentSubject的children作为二级分类集合
                this.childSubjectList = parentSubject.children;
            }
        }
    },
    // 4.文件上传之前
    beforeUploadCover(response) {},
    // 5.文件上传之后
    afterUploadCover(response) {
        this.courseInfo.cover=response.data.url
    },
    // 6.保存课程信息
    saveCourseInfo() {
        course.saveCourseInfo(this.courseInfo)
            .then(response => {
                this.$message({
                    type: "success",
                    message: "添加成功!"
                  }); 
            })
            this.$router.push({path:'/course/chapter/' + this.courseId})
    },
    // 7.根据课程id查询课程
    getCourseById() {
        course
            .getCourseById(this.courseId)
            .then(response => {
                this.courseInfo = response.data.courseInfo
                // 拿到讲师集合
                this.getAllTeacher()

                course
                    .getAllSubject()
                    .then(response => {
                        this.parentSubjectList = response.data.allSubjects
                        for (let index = 0; index < this.parentSubjectList.length; index++) {
                            const parentSubjec = this.parentSubjectList[index];
                            // 判断哪个一级分类被选中
                            if (parentSubjec.id === this.courseInfo.parentSubjectId) {
                                // 得到该一级分类的子节点
                                this.childSubjectList = parentSubjec.children
                            }
                        }
                    })
                // // 拿到课程分类集合
                // this.getAllSubject()
                // // 拿到二级分类
                // this.getAllChildSubject(this.courseInfo.childSubjectId)
            })
    },

    // 8.修改课程基本信息
    updateCourseInfo() {
        course.updateCourseInfo(this.courseInfo)
            .then(response => {
                this.$message({
                    type: "success",
                    message: "更新课程成功!"
                  }); 
                  this.$router.push({path:'/course/chapter/' + this.courseId})
            })
    },
    // 9.添加或更新课程
    saveOrUpdateCourseInfo() {
        if (this.courseId) {
            this.updateCourseInfo()
        } else {
            this.saveCourseInfo()
        }
    }
    

  },
  created() {
      // 判断路由是否有值
      if (this.$route.params.courseId) {
        this.courseId = this.$route.params.courseId,
        this.getCourseById()
      } else {
        this.getAllTeacher(),
        this.getAllSubject()
      }
  }
}
