<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-09-13 <br/>
描述：交易记录表新增&修改页面
-->

<template>
    <div class="page" v-loading="loading">

        <ape-form ref="form" v-model="form">
            <form-item label="app" name="app" ></form-item>
            <form-item label="商户订单号" name="order_no" required></form-item>
            <form-item label="用户id" name="user_id" ></form-item>
            <form-item label="商户id" name="merchant_id" ></form-item>
            <form-item label="sn码" name="sn" ></form-item>
            <form-item label="厂商" name="manufacturer" ></form-item>
            <form-item label="交易金额" name="trade_amount" type="price" ></form-item>
            <form-item label="到账金额" name="arrival_amount" type="price" ></form-item>
            <form-item label="刷卡银行卡" name="bank_card" ></form-item>
            <form-item label="交易时间" name="trade_time" type="date" required></form-item>
            <form-item label="费率" name="rate" type="price" ></form-item>
            <form-item label="商户手续费" name="poundage" type="price" ></form-item>
            <form-item label="付款方式" name="pay_way" ></form-item>
            <form-item label="交易状态" name="trade_state" ></form-item>
            <form-item label="渠道id" name="channel_id" ></form-item>
            <form-item label="渠道商户编号" name="channel_mer_no" ></form-item>
            <form-item label="渠道订单号" name="channel_order_no" ></form-item>
            <form-item label="失败原因" name="failreason" ></form-item>
            <form-item label="经度" name="lon" ></form-item>
            <form-item label="纬度" name="lat" ></form-item>
            <form-item label="交易地址" name="trade_address" ></form-item>
            <form-item label="备注" name="remarks" ></form-item>
        </ape-form>


        <div class="form-item-control">
            <el-button size="small" type="primary" @click="submitForm" :loading="loading">提交</el-button>
        </div>

    </div>
</template>

<script>
    define(["vue", "api", "baseFormMixins"], function(Vue, api, baseFormMixins) {
        return Vue.component("modify", {

            url: '/tuozhanbao/tradeRecords/save.do',
            detailUrl: '/tuozhanbao/tradeRecords/detail.do',
            saveUrl: '/tuozhanbao/tradeRecords/save.do',

            idKey: 'id',
            resultKey: 'tradeRecords',

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