<template>
    <div class="page">

        {{ $route.params }}
        <ape-form ref="form" v-model="form">
            <form-item label="商品名称" name="title" required></form-item>
            <form-item label="分类" name="one_classify_id" type="select" url="oneClassify_combolist.do" select-text="title" select-value="id"></form-item>
            <form-item name="two_classify_id" type="select" url="twoClassify_combolist.do" last-select-name="one_classify_id"></form-item>

            <form-item label="epr商品id" name="erp_id" required></form-item>
            <form-item label="原价" name="original_price" type="price" required></form-item>
            <form-item label="优惠价" name="preferential_price" type="price"></form-item>
            <form-item label="包装单位" name="unit"></form-item>
            <form-item label="口味" name="taste"></form-item>
            <form-item label="原料" name="material"></form-item>
            <form-item label="克重" name="grammage" type="positive-number"></form-item>
            <form-item label="是否在打分榜" name="group_dfb" type="select" list-type="yesOrNo"></form-item>
            <form-item label="打分榜封面图片" name="dfb_cover_img"></form-item>
            <form-item label="是否在秒杀" name="group_ms" type="select" list-type="yesOrNo"></form-item>


            <form-item label="秒杀封面图片" name="ms_cover_img" type="upload-image"></form-item>


            <form-item label="商品轮播图" name="slideshow" type="upload-image" :limit="5"></form-item>
            <form-item label="卖点黑板报" name="goods_detail" type="editor"></form-item>
            <form-item label="运费模版" name="freight_template_id" type="select" url="squirrel/freightTemplate_comboboxdata.do" select-text="name" select-value="id" required></form-item>
            <form-item label="库存数量" name="stock_count"></form-item>
            <form-item label="评价分值" name="evaluate_score"></form-item>
            <form-item label="评价次数" name="evaluate_count"></form-item>


            <form-item label="是否上架" name="is_sale" type="select" list-type="yesOrNo" required></form-item>


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