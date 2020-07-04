<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-12-27 <br/>
描述：商家新增&修改页面
-->

<template>
    <div class="page" v-loading="loading">

        <ape-form ref="form" v-model="form">
            <form-item label="用户" name="user_id_display" ></form-item>
            <!--<form-item label="姓名" name="name" ></form-item>
            <form-item label="手机号码" name="phone" ></form-item>
            <form-item label="头像" name="image" ></form-item>-->
            <form-item label="sn" name="sn" ></form-item>
            <form-item label="厂商" name="manufacturer" ></form-item>
            <form-item label="创建时间" name="create_time" type="date" ></form-item>
            <form-item label="型号" name="model" ></form-item>
            <form-item label="状态" name="state" ></form-item>
            <form-item label="类型：0订单、1奖励、2手动添加" name="type" ></form-item>
            <!--<form-item label="分拨记录" name="record" ></form-item>
            <form-item label="订单id" name="order_id" ></form-item>-->
        </ape-form>


        <div class="form-item-control">
            <el-button size="small" type="primary" @click="submitForm" :loading="loading">提交</el-button>
        </div>

    </div>
</template>

<script>
    define(["vue", "api", "baseFormMixins"], function(Vue, api, baseFormMixins) {
        return Vue.component("modify", {

            url: '/tuozhanbao/merchant/save.do',
            detailUrl: '/tuozhanbao/merchant/detail.do',
            saveUrl: '/tuozhanbao/merchant/save.do',

            idKey: 'id',
            resultKey: 'merchant',

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