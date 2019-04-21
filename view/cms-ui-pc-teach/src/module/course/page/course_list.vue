<template>
  <section>
    <el-row>
      <el-col
        :xl="{span: 2, offset: 0}"
        :lg="{span: 3, offset: 1}"
        :md="{span: 5, offset: 1}"
        :sm="{span: 7, offset: 1}"
        :xs="{span: 11, offset: 1}"
      >
        <el-card :body-style="{ padding: '2px' }">
          <img src="/static/images/add.jpg" class="image">
          <div style="padding: 10px;">
            <span>课程名称</span>
            <div class="bottom clearfix">
              <time class="time"></time>
              <router-link class="mui-tab-item" :to="{path:'/course/add/base'}">
                <el-button type="text" class="button">新增课程</el-button>
              </router-link>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col
        :xl="{span: 2, offset: 0}"
        :lg="{span: 3, offset: 1}"
        :md="{span: 5, offset: 1}"
        :sm="{span: 7, offset: 1}"
        :xs="{span: 11, offset: 1}"
        v-for="(course, index) in courses" :key="course.id">
        <el-card :body-style="{ padding: '2px' }">
          <!--          <img :src="course.pic!=null?imgUrl+course.pic:'/static/images/nonepic.jpg'" class="image" height="280px">-->
          <img src="/static/images/nonepic.jpg" class="image">
          <div style="padding: 10px;">
            <span>{{course.name}}</span>
            <div class="bottom clearfix">
              <time class="time"></time>
              <el-button type="text" class="button" @click="handleManage(course.id)">管理课程</el-button>
            </div>
          </div>
        </el-card>
      </el-col>

      <!--分页-->
      <el-col :span="24" class="toolbar">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page=page
          :page-sizes="[sizeConst, sizeConst*2, sizeConst*3, sizeConst*4,sizeConst*5]"
          :page-size=size
          layout="total, sizes, prev, pager, next, jumper"
          :total=total
          :pager-count="11"
          style="text-align: center"
          background
        >
        </el-pagination>
      </el-col>
    </el-row>
  </section>
</template>

<script>
  import * as courseApi from '../api/course';

  let sysConfig = require('@/../config/sysConfig')
  export default {
    data() {
      return {
        page: 1,
        size: 20,
        sizeConst: 20,
        total: 0,
        courses: [],
        sels: [],//列表选中列
        imgUrl: sysConfig.imgUrl
      }
    },
    methods: {
      // Size change
      handleSizeChange(size) {
        this.size = size;
        this.getCourseList();
      },
      //分页方法
      handleCurrentChange(val) {
        this.page = val;
        this.getCourseList();
      },
      //获取课程列表
      getCourseList() {
        courseApi.findCourseList(this.page, this.size, {}).then((res) => {
          if (res.success) {
            this.total = res.queryResult.total;
            this.courses = res.queryResult.list;
          }
        });
      },
      handleManage: function (id) {
        this.$router.push({path: '/course/manager/' + id})
      }

    },
    created() {

    },
    mounted() {
      //查询我的课程
      this.getCourseList();
    }
  }
</script>

<style scoped>
  .el-col-8 {
    width: 20%
  }

  .el-col-offset-2 {
    margin-left: 2%
  }

  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
</style>
