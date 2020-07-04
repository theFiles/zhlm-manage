<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-12-24 <br/>
描述：库存管理新增&修改页面
-->

<template>
    <div class="page" v-loading="loading">

        <ape-form ref="form" v-model="form">
            <form-item label="用户账号" name="user_id" type="select" url="/tuozhanbao/userInfo/selectList.do" select-text="phone" select-value="id" required></form-item>
            <form-item label="型号" name="model" ></form-item>
            <form-item label="品牌" name="brand" ></form-item>
            <form-item label="sn码" name="sn" ></form-item>
            <form-item label="状态" name="state" ></form-item>
            <form-item label="类型" name="type" ></form-item>
            <form-item label="创建时间" name="create_time" type="date" ></form-item>
            <!--
            <form-item label="订单号" name="ape_orders_id" ></form-item>
            <form-item label="分拨记录" name="record" ></form-item>
            -->
        </ape-form>


        <div class="form-item-control">
            <el-button size="small" type="primary" @click="submitForm" :loading="loading">提交</el-button>
        </div>

    </div>
</template>

<script>
    define(["vue", "api", "baseFormMixins"], function(Vue, api, baseFormMixins) {
        return Vue.component("modify", {

            url: '/tuozhanbao/stock/save.do',
            detailUrl: '/tuozhanbao/stock/detail.do',
            saveUrl: '/tuozhanbao/stock/save.do',

            idKey: 'id',
            resultKey: 'stock',

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