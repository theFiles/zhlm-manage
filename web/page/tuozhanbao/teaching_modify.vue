<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-09-22 <br/>
描述：教学新增&修改页面
-->

<template>
    <div class="page" v-loading="loading">

        <ape-form ref="form" v-model="form">
            <!--<form-item label="分类" name="classify" ></form-item>-->
            <form-item label="类型" name="type_display" type="select" url="/tuozhanbao/enum/getTeachingType.do" select-text="text" select-value="value" ></form-item>
            <form-item label="标题" name="title" ></form-item>
            <form-item label="封面" name="image" type="upload-image" :limit="1" ></form-item>
            <form-item label="内容" name="content" type="editor" ></form-item>
            <!--<form-item label="链接地址" name="link_url" ></form-item>-->
            <!--<form-item label="创建时间" name="create_time" type="date" ></form-item>-->
            <!--<form-item label="更新时间" name="update_time" type="date" ></form-item>-->
        </ape-form>


        <div class="form-item-control">
            <el-button size="small" type="primary" @click="submitForm" :loading="loading">提交</el-button>
        </div>

    </div>
</template>

<script>
    define(["vue", "api", "baseFormMixins"], function(Vue, api, baseFormMixins) {
        return Vue.component("modify", {

            url: '/tuozhanbao/teaching/save.do',
            detailUrl: '/tuozhanbao/teaching/detail.do',
            saveUrl: '/tuozhanbao/teaching/save.do',

            idKey: 'id',
            resultKey: 'teaching',

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