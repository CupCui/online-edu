import chapter from '@/api/chapter/chapter_back'
import section from '@/api/chapter/section_back'
export default {
  data() {
    return {
      saveBtnDisabled: false, // 保存按钮是否禁用
      chapterSectionList: {},
      chapterFormVisible: false,
      saveChapterBtnDisabled: false,
      chapterUI: {
        courseId: '',
        title: '',
        sort: 0
      },
      sectionFormVisible: false,
      saveSectionBtnDisabled: false,
      fileList: [],
      sectionUI: {
        title: '',
        sort: 0,
        isFree: true,
        videoSourceId: '',
        videoOriginalName: '',
        chapterId: '', // 添加小节的章节id
        courseId: '',
      },
      

    }
  },

  created() {
    this.chapterUI.courseId = this.$route.params.courseId,
    this.getChapterAndSection()
  },

  methods: {
    //---------小节管理----------
    // 添加小节弹窗
    saveSectionDialog(chapterId) {
      this.sectionFormVisible = true;
      //清空原有的值
      this.sectionUI = {};
      //小节属于那个课程
      this.sectionUI.courseId = this.$route.params.courseId;
      //小节属于那个章节
      this.sectionUI.chapterId = chapterId;
      //清空文件列表
      this.fileList = []
    },
    // 修改小节弹窗
    updateSectionDialog(sectionId) {
      this.sectionFormVisible=true
      section
        .getSectionById(sectionId)
        .then(response => {
          this.sectionUI = response.data.section
          this.fileList = [{ 'name': this.sectionUI.videoOriginalName }]
        })
    },
    // 添加或修改小节
    saveOrUpdateSection() {
      if (this.sectionUI.id) {
        this.updateSection()
      } else {
        this.saveSection()
      }
    },
    // 添加小节
    saveSection() {
      section
        .addSection(this.sectionUI)
        .then(response => {
            this.$message({
              type: "success",
              message: "添加小节成功!"
            });
            this.sectionFormVisible=false
            this.getChapterAndSection()
        })
    },
    // 修改小节
    updateSection() {
      section
        .updateSection(this.sectionUI)
        .then(response => {
          this.$message({
            type: "success",
            message: "修改小节成功!"
          });
          this.sectionFormVisible=false
          this.getChapterAndSection()
        })
    },
    // 删除小节
    deleteSection(sectionId) {
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          section
          .deleteSection(sectionId)
          .then(response => {
            this.$message({
              type: "success",
              message: "删除小节成功!"
            });
            this.getChapterAndSection()
          })
        });      
    },
    //---------视频管理--------
    uploadVideoSuccess(response, file) {
      this.sectionUI.videoSourceId = response.data.videoId
      this.sectionUI.videoOriginalName = file.getName
    },
    beforeVideoDelete(file) {
      return this.$confirm(`确定要删除${file.name}吗？`)
    },
    deleteVideo() {
      section
        .deleteAliyunVideo(this.sectionUI.videoSourceId)
        .then(response => {
          this.$message({
            type: "success",
            message: "删除视频成功!"
          });
          this.sectionUI.videoSourceId = '',
          this.sectionUI.videoOriginalName = '',
          this.fileList = []
        })
    },
    uploadExceed() {},
    
    //---------章节管理--------
    // 1.章节和小节的列表查询
    getChapterAndSection() {
      chapter
        .getChapterAndSection(this.chapterUI.courseId)
        .then(response => {
          this.chapterSectionList = response.data.chapterSectionList
        })
    },
    // 添加章节弹框
    saveChapterDialog() {
      this.chapterFormVisible = true,
      this.chapterUI.title = '',
      this.chapterUI.sort = ''
    },
    // 添加章节
    saveChapter() {
      chapter
        .saveChapter(this.chapterUI)
        .then(response => {
          console.log(response)
          this.chapterFormVisible = false
          this.$message({
            type: "success",
            message: "添加章节成功!"
          });
          this.getChapterAndSection()
        })
    },
    // 修改章节弹框
    updateChapterDialog(chapterId) {
      this.chapterFormVisible = true
      // 回显
      chapter
        .getChapter(chapterId)
        .then(response => {
          this.chapterUI = response.data.chapter
        })
    },
    // 更新章节
    updateChapter() {
      chapter
        .updateChapter(this.chapterUI)
        .then(response => {
          this.chapterFormVisible = false
          this.$message({
            type: "success",
            message: "更新章节成功!"
          });
          this.getChapterAndSection()
        })
    },
    // 更新或添加章节
    saveOrUpdateChapter() {
      if (this.chapterUI.id) {
        this.updateChapter()
      } else {
        this.saveChapter()
      }
    },
    deleteChapter(courseId) {
      chapter
        .deleteChapter(courseId)
        .then(response => {
          this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          })
            .then(() => {
              this.$message({
                type: "success",
                message: "删除章节成功!"
              });
              this.getChapterAndSection()
            });
        })
  },
  previous() {
    console.log('previous')
    this.$router.push({ path: '/course/form/' + this.chapterUI.courseId})
  },

  next() {
    console.log('next')
    this.$router.push({ path: '/course/publish/' + this.chapterUI.courseId})
  }
}
  }