<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-09-11 <br/>
描述：日志记录新增&修改页面
-->

<template>
    <div class="page" v-loading="loading">

        <ape-form ref="form" v-model="form">
            <form-item label="日志id" name="sl_id" required></form-item>
            <form-item label="日志时间" name="sl_date" type="date" required></form-item>
            <form-item label="登录账号" name="sl_user_code" ></form-item>
            <form-item label="用户名称" name="sl_user_name" ></form-item>
            <form-item label="机构名称" name="sl_org_name" ></form-item>
            <form-item label="ip地址" name="sl_ip" ></form-item>
            <form-item label="类名称" name="sl_class" ></form-item>
            <form-item label="方法名称" name="sl_method" ></form-item>
            <form-item label="方法描述" name="sl_description" ></form-item>
            <form-item label="日志内容" name="sl_content" ></form-item>
        </ape-form>


        <div class="form-item-control">
            <el-button size="small" type="primary" @click="submitForm" :loading="loading">提交</el-button>
        </div>

    </div>
</template>

<script>
    define(["vue", "api", "baseFormMixins"], function(Vue, api, baseFormMixins) {
        return Vue.component("modify", {

            url: '/sys/log/save.do',
            detailUrl: '/sys/log/detail.do',
            saveUrl: '/sys/log/save.do',

            idKey: 'sl_id',
            resultKey: 'log',

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