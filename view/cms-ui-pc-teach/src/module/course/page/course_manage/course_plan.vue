<template>
  <div>
    <el-button type="primary" @click="teachplayFormVisible = true">添加课程计划</el-button>
    <el-tree
      :data="teachPlanList"
      :props="defaultProps"
      node-key="id"
      default-expand-all
      :expand-on-click-node="false"
      :render-content="renderContent">
    </el-tree>

    <el-dialog title="添加课程计划" :visible.sync="teachplayFormVisible">

      <el-form ref="teachPlanForm" :model="teachPlanActive" label-width="140px" style="width:600px;"
               :rules="teachPlanRules">
        <el-form-item label="上级结点">
          <el-select v-model="teachPlanActive.parentid" placeholder="不填表示根结点">
            <el-option
              v-for="item in teachPlanList"
              :key="item.id"
              :label="item.pname"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="章节/课时名称" prop="pname">
          <el-input v-model="teachPlanActive.pname" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="课程类型">
          <el-radio-group v-model="teachPlanActive.ptype">
            <el-radio class="radio" label='1'>视频</el-radio>
            <el-radio class="radio" label='2'>文档</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="学习时长（分钟）  请输入数字">
          <el-input type="number" v-model="teachPlanActive.timelength" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="排序字段">
          <el-input v-model="teachPlanActive.orderby" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="章节/课时介绍" prop="description">
          <el-input type="textarea" v-model="teachPlanActive.description"></el-input>
        </el-form-item>

        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="teachPlanActive.status">
            <el-radio class="radio" label="0">未发布</el-radio>
            <el-radio class="radio" label='1'>已发布</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="addTeachplan">提交</el-button>
          <el-button type="primary" v-on:click="resetForm">重置</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>
    <el-dialog title="选择媒资文件" :visible.sync="mediaFormVisible">
      <media-list v-bind:ischoose="true" @choosemedia="choosemedia"></media-list>
    </el-dialog>
  </div>
</template>
<script>
  let id = 1000;
  import * as courseApi from '../../api/course';
  import mediaList from '@/module/media/page/media_list.vue';

  export default {
    components: {
      mediaList
    },
    data() {
      return {
        mediaFormVisible: false,
        teachplayFormVisible: false,//控制添加窗口是否显示
        teachPlanList: [],
        defaultProps: {
          children: 'children',
          label: 'pname'
        },
        teachPlanRules: {
          pname: [
            {required: true, message: '请输入课程计划名称', trigger: 'blur'}
          ],
          status: [
            {required: true, message: '请选择状态', trigger: 'blur'}
          ]
        },
        teachPlanActive: {},
        teachPlanId: ''
      }
    },
    methods: {
      //选择视频，打开窗口
      choosevideo(data) {
        //得到当前的课程计划
        this.teachPlanId = data.id
//        alert(this.teachPlanId)
        this.mediaFormVisible = true;//打开窗口
      },
      //保存选择的视频
      choosemedia(mediaId, fileOriginalName, mediaUrl) {
        //保存视频到课程计划表中
        let teachPlanMedia = {}
        teachPlanMedia.mediaId = mediaId;
        teachPlanMedia.mediaFileOriginalName = fileOriginalName;
        teachPlanMedia.mediaUrl = mediaUrl;
        teachPlanMedia.courseId = this.courseid;
        //课程计划
        teachPlanMedia.teachPlanId = this.teachPlanId

        courseApi.savemedia(teachPlanMedia).then(res => {
          if (res.success) {
            this.$message.success("选择视频成功")
            //查询课程计划
            this.findTeachplan()
          } else {
            this.$message.error(res.message)
          }
        })
      },
      //提交课程计划
      addTeachplan() {
        //校验表单
        this.$refs.teachPlanForm.validate((valid) => {
          if (valid) {
            //调用api方法
            //将课程id设置到teachPlanActive
            this.teachPlanActive.courseid = this.courseid
            courseApi.addTeachplan(this.teachPlanActive).then(res => {
              if (res.success) {
                this.$message.success("添加成功")
                //刷新树
                this.findTeachplan()
              } else {
                this.$message.error(res.message)
              }

            })
          }
        })
      },
      //重置表单
      resetForm() {
        this.teachPlanActive = {}
      },

      append(data) {
        const newChild = {id: id++, label: 'testtest', children: []};
        if (!data.children) {
          this.$set(data, 'children', []);
        }
        data.children.push(newChild);

      },
      edit(data) {
        //alert(data.id);
      },
      remove(node, data) {
        const parent = node.parent;
        const children = parent.data.children || parent.data;
        const index = children.findIndex(d => d.id === data.id);
        children.splice(index, 1);

      },
      renderContent(h, {node, data, store}) {
        return (
          <span
            style="flex: 1; display: flex; align-items: center; justify-content: space-between; font-size: 14px; padding-right: 8px;">
            <span>
              <span>{node.label}</span>
            </span>
            <span>
              <el-button style="font-size: 12px;" type="text"
                         on-click={() => this.choosevideo(data)}>{data.mediaFileOriginalName}&nbsp;&nbsp;&nbsp;&nbsp; 选择视频</el-button>
              <el-button style="font-size: 12px;" type="text" on-click={() => this.edit(data)}>修改</el-button>
              <el-button style="font-size: 12px;" type="text" on-click={() => this.remove(node, data)}>删除</el-button>
            </span>
          </span>);
      },
      findTeachplan() {
        this.teachPlanList = [];
        //查询课程计划
        courseApi.findTeachplanList(this.courseid).then(res => {
          if (res.success && res.teachPlanNode.children) {
            this.teachPlanList = res.teachPlanNode.children;
          } else this.$message({
            showClose: true,
            message: '查询失败! ' + res.message,
            type: 'error'
          });
        });
      }
    },
    mounted() {
      //课程id
      this.courseid = this.$route.params.courseid;
      //查询课程计划
      this.findTeachplan();
    }
  }
</script>
<style>

</style>
