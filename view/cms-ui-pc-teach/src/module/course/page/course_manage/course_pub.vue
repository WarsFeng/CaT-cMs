<template>
  <div>
    <template>
      <div>
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>课程预览</span>
          </div>
          <div class="text item">
            <el-button type="primary" icon="el-icon-s-promotion" @click.native="preview">预览</el-button>
            <!--            <br/><br/>-->
            <span v-if="previewUrl && previewUrl!=''">
              <a :href="previewUrl" target="_blank">
                <el-button type="success" icon="el-icon-camera" round>查看</el-button>
              </a>
            </span>
          </div>
        </el-card>
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>课程发布</span>
          </div>
          <div class="text item">
            <div v-if="course.status == '202001'">
              状态：制作中<br/>
              <el-button type="primary" @click.native="publish">新课程发布</el-button>
            </div>
            <div v-else-if="course.status == '202003'">
              状态：已下线
              <br/><br/>
              <span><a :href="'http://cms.wars.cat/course/detail/'+this.courseid+'.html'"
                       target="_blank">点我查看课程详情页面 </a> </span>
            </div>
            <div v-else-if="course.status == '202002'">
              状态：已发布<br/>
              <el-button type="primary" @click.native="publish">修改发布</el-button>
              <br/><br/>
              <span><a :href="'http://cms.wars.cat/course/detail/'+this.courseid+'.html'"
                       target="_blank">点我查看课程详情页面 </a> </span>
            </div>
          </div>
        </el-card>
      </div>
    </template>
  </div>
</template>
<script>
  import * as courseApi from '../../api/course';

  export default {

    data() {
      return {
        dotype: '',
        courseid: '',
        course: {"id": "", "name": "", "status": ""},
        previewUrl: ''
      }
    },
    methods: {
      //预览
      preview() {
        //调用课程管理服务的预览接口，得到课程预览url
        courseApi.preview(this.courseid).then((res) => {
          if (res.success) {
            this.$message.success('预览页面生成成功，请点击下方预览链接');
            if (res.previewUrl) {
              //预览url
              this.previewUrl = res.previewUrl
            }
          } else {
            this.$message.error(res.message);
          }
        });
      },
      publish() {
        //课程发布
        courseApi.release(this.courseid).then(res => {
          if (res.success) {
            this.$message.success("发布成功，请点击下边的链接查询课程详情页面")

          } else {
            this.$message.error(res.message)
          }

        })
      },
      getCourseView() {
        courseApi.findCourseView(this.courseid).then(res => {
          if (res.success) {
            //获取课程状态
            this.course.status = res.data.base.status;
          }

        })
      }

    },
    mounted() {
      //课程id
      this.courseid = this.$route.params.courseid;
      //查询课程信息
      this.getCourseView();
    }

  }
</script>
<style>

</style>
