<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-09-22 <br/>
描述：机具新增&修改页面
-->

<template>
    <div class="page" v-loading="loading">

        <ape-form ref="form" v-model="form">
            <form-item label="名称" name="name" ></form-item>
            <form-item label="机具型号" name="model_number" ></form-item>
            <form-item label="厂家" name="manufacturer" type="select" url="/tuozhanbao/enum/getManufacturer.do" select-text="text" select-value="value" ></form-item>
            <form-item label="价格" name="price" type="price" ></form-item>
            <form-item label="返现金额" name="return_money" type="price" ></form-item>
            <form-item label="起步台数" name="min_count" ></form-item>
            <form-item label="图片" name="image" type="upload-image" :limit="10" ></form-item>
            <form-item label="机具介绍" name="content" type="editor" ></form-item>
            <form-item label="创建时间" name="create_time" type="date" ></form-item>
            <form-item label="修改时间" name="update_time" type="date" ></form-item>
        </ape-form>


        <div class="form-item-control">
            <el-button size="small" type="primary" @click="submitForm" :loading="loading">提交</el-button>
        </div>

    </div>
</template>

<script>
    define(["vue", "api", "baseFormMixins"], function(Vue, api, baseFormMixins) {
        return Vue.component("modify", {

            url: '/tuozhanbao/machines/save.do',
            detailUrl: '/tuozhanbao/machines/detail.do',
            saveUrl: '/tuozhanbao/machines/save.do',

            idKey: 'id',
            resultKey: 'machines',

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