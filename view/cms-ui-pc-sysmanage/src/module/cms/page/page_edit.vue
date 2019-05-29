<template>
  <el-form :model="form" :rules="rules" ref="form" label-width="100px">
    <el-form-item label="所属站点" prop="siteId">
      <el-select
        v-model="form.siteId"
        filterable remote clearable
        placeholder="请选择站点"
        :remote-method="siteRemoteMethod"
        @change="templateRemoteMethod('')"
        :loading="loading.site">
        <el-option
          v-for="item in siteList"
          :key="item.siteId"
          :label="item.siteName"
          :value="item.siteId">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="页面模板" prop="templateId">
      <el-select
        v-model="form.templateId"
        filterable remote clearable
        placeholder="请选择模板"
        :remote-method="templateRemoteMethod"
        :loading="loading.template">
        <el-option id="templateOption"
                   v-for="item in templateList"
                   :key="item.templateId"
                   :label="item.templateName"
                   :value="item.templateId">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="页面名称" prop="pageName">
      <el-input
        placeholder="请输入页面名称"
        v-model="form.pageName"
        clearable>
      </el-input>
    </el-form-item>
    <el-form-item label="别名" prop="pageAlias">
      <el-input
        placeholder="请输入页面别名"
        v-model="form.pageAlias"
        clearable>
      </el-input>
    </el-form-item>
    <el-form-item label="访问路径" prop="pageWebPath">
      <el-input
        placeholder="请输入访问路径"
        v-model="form.pageWebPath"
        clearable>
      </el-input>
    </el-form-item>
    <el-form-item label="物理路径" prop="pagePhysicalPath">
      <el-input
        placeholder="请输入物理路径"
        v-model="form.pagePhysicalPath"
        clearable>
      </el-input>
    </el-form-item>
    <el-form-item label="类型" prop="pageType">
      <el-radio-group v-model="form.pageType" size="small">
        <el-radio-button label="1">动态</el-radio-button>
        <el-radio-button label="0">静态</el-radio-button>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="数据Url" prop="dataUrl">
      <el-input
        placeholder="请输入数据Url"
        v-model="form.dataUrl"
        clearable>
      </el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="pageCreateTime">
      <el-date-picker
        v-model="form.pageCreateTime"
        type="datetime"
        placeholder="请选择创建时间">
      </el-date-picker>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="addSubmit" :loading="loading.submit">提交</el-button>
      <el-button type="" @click="() => {
        this.$router.push({
          path: '/cms/page/list',
          query: {
             page: this.$route.query.page,
             siteId: this.$route.query.siteId,
             pageAlias: this.$route.query.pageAlias,
          }});}">返回
      </el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import * as cmsApi from "../api/cms";

  export default {
    data() {
      return {
        siteList: [],
        templateList: [],
        form: {
          siteId: '',
          templateId: '',
          pageName: '',
          pageAlias: '',
          pageWebPath: '',
          pagePhysicalPath: '',
          pageType: 1,
          dataUrl: '',
          pageCreateTime: ''
        },
        rules: {
          siteId: [
            {required: true, message: "请选择站点", trigger: 'blur'}
          ],
          templateId: [
            {required: true, message: "请选择模板", trigger: 'blur'}
          ],
          pageName: [
            {required: true, message: "请输入页面名称", trigger: 'blur'}
          ],
          pageAlias: [
            {required: true, message: "请输入页面别名", trigger: 'blur'}
          ],
          pageWebPath: [
            {required: true, message: "请输入页面访问路径", trigger: 'blur'}
          ],
          pagePhysicalPath: [
            {required: true, message: "请输入页面物理路径", trigger: 'blur'}
          ]
        },
        loading: {
          site: false,
          template: false,
          submit: false
        }
      }
    },
    methods: {
      siteRemoteMethod(query) {
        this.loading.site = true;
        setTimeout(() => {
          cmsApi.sub_site_list(query).then(res => {
            this.siteList = res.queryResult.list;
            this.loading.site = false;
          });
        }, 200);
      },
      templateRemoteMethod(query) {
        if (!this.form.siteId) {
          this.templateList = [];
          this.form.templateId = '';
          return
        }
        this.loading.template = true;
        setTimeout(() => {
          cmsApi.sub_template_list(this.form.siteId, query).then(res => {
            this.templateList = res.queryResult.list;
            this.loading.template = false;
          });
        }, 200);
      },
      addSubmit() {
        // Valid
        this.$refs.form.validate((valid) => {
          if (valid) {
            // Confirm
            this.$confirm('确认要提交吗?', '提示'
              , {center: true}).then(() => {
              // Confirm yes
              this.loading.submit = true;
              // Add page
              cmsApi.page_update(this.$route.query.pageId, this.form).then(res => {
                if (res.success) { // Add page success
                  this.$message({
                    showClose: true,
                    message: '提交成功!',
                    type: 'success'
                  });
                  this.$refs['form'].resetFields();
                } else this.$message({ // Add page fail
                  showClose: true,
                  message: '提交失败! ' + res.message,
                  type: 'error'
                });
              });
              this.loading.submit = false;
              // Confirm cancel
            }).catch(() => {
              return false;
            });
          } else {
            return false;
          }
        });
      }
    },
    created() {
      this.siteRemoteMethod();
      cmsApi.page_get(this.$route.query.pageId).then(res => {
        this.form = res.cmsPage;
        this.templateRemoteMethod()
      });
      this.loading.site = false;
    }
  }
</script>

<style scoped>
</style>
