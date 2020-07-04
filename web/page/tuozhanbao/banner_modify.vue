<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-09-22 <br/>
描述：轮播图新增&修改页面
-->

<template>
    <div class="page" v-loading="loading">

        <ape-form ref="form" v-model="form">
            <!--<form-item label="位置" name="position" ></form-item>-->
            <form-item label="标题" name="title" ></form-item>
            <!--<form-item label="介绍" name="introduce" ></form-item>-->
            <form-item label="主图片" name="image" type="upload-image" :limit="1" ></form-item>
            <!--<form-item label="内容" name="content" ></form-item>-->
            <form-item label="跳转链接" name="link_url" ></form-item>
            <!--<form-item label="排序值" name="order_no" ></form-item>-->
        </ape-form>


        <div class="form-item-control">
            <el-button size="small" type="primary" @click="submitForm" :loading="loading">提交</el-button>
        </div>

    </div>
</template>

<script>
    define(["vue", "api", "baseFormMixins"], function(Vue, api, baseFormMixins) {
        return Vue.component("modify", {

            url: '/tuozhanbao/banner/save.do',
            detailUrl: '/tuozhanbao/banner/detail.do',
            saveUrl: '/tuozhanbao/banner/save.do',

            idKey: 'id',
            resultKey: 'banner',

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
            }
        });
    });
</script>