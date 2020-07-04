<template>
  <div class="page">

    <ape-form ref="form" v-model="form">
      <form-item label="配置KEY" name="title" required></form-item>
      <form-item label="配置值" name="one_classify_id" type="select" url="oneClassify_combolist.do" select-text="title" select-value="id"></form-item>
      <form-item label="配置描述" name="erp_id" required></form-item>
      <form-item label="配置类型" name="is_sale" type="select" :list="[{ text: '用户配置' }, { text: '系统配置' }]" required></form-item>

      {{ form }}

      <div class="form-item-control">
        <el-button size="small" @click="submitForm" :loading="loading">提交</el-button>
      </div>
    </ape-form>

  </div>
</template>

<script>
  define(["vue", "api", "baseFormMixins"], function(Vue, api, baseFormMixins) {
    return Vue.component("modify", {
      url: 'squirrel/goods_addSave.do',

      template: template,
      data: function() {
        return {
        };
      },
      mixins: [baseFormMixins],
      mounted: function() {
        console.log(this.$route)
      },
      methods: {
        submitForm() {
          this.loading = true;

          api.post(this.$options.url, { goods: this.form }).then(result => {
            this.loading = false;
            // 成功后回调处理，必须调用，不然返回上一级无法刷新
            this.successHandle();
          }).catch(error => {
            this.loading = false;
            this.$alert('提交失败', error);
          });

          setTimeout(() => {
            this.loading = false;
            this.successHandle();
          }, 200);
        },
      }
    });
  });
</script>