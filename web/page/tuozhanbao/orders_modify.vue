<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-09-22 <br/>
描述：购买POS机订单新增&修改页面
-->

<template>
    <div class="page" v-loading="loading">

        <ape-form ref="form" v-model="form">
            <form-item label="用户id" name="user_id" ></form-item>
            <form-item label="订单号" name="order_no" ></form-item>
            <form-item label="订单金额" name="order_amount" type="price" ></form-item>
            <form-item label="订单状态" name="state" type="select" url="/tuozhanbao/enum/getOrderState.do" select-text="text" select-value="value" ></form-item>
            <form-item label="创建时间" name="create_time" type="date" ></form-item>
            <form-item label="付款时间" name="pay_time" type="date" ></form-item>
            <form-item label="付款方式" name="pay_way" type="select" url="/tuozhanbao/enum/getPayType.do" select-text="text" select-value="value" ></form-item>
            <form-item label="商品id" name="goods_id" ></form-item>
            <form-item label="商品名称" name="goods_name" ></form-item>
            <form-item label="商品价格" name="goods_price" type="price" ></form-item>
            <form-item label="商品图片" name="goods_image" type="upload-image" :limit="10" ></form-item>
            <form-item label="购买数量" name="total_count" ></form-item>
            <form-item label="配送数量" name="send_count" ></form-item>
            <form-item label="剩余数量" name="surplus_count" ></form-item>
            <form-item label="退货数量" name="return_count" ></form-item>
            <form-item label="退款说明" name="return_explain" type="textarea" ></form-item>
            <form-item label="退款审核是否通过" name="return_result" type="select" list-type="yesOrNo" ></form-item>
            <form-item label="退款金额" name="return_money" type="price" ></form-item>
            <form-item label="退款路径" name="return_way" type="select" url="/tuozhanbao/enum/getReturnType.do" select-text="text" select-value="value" ></form-item>
            <form-item label="审核管理员id" name="manager_id" ></form-item>
        </ape-form>


        <div class="form-item-control">
            <el-button size="small" type="primary" @click="submitForm" :loading="loading">提交</el-button>
        </div>

    </div>
</template>

<script>
    define(["vue", "api", "baseFormMixins"], function(Vue, api, baseFormMixins) {
        return Vue.component("modify", {

            url: '/tuozhanbao/orders/save.do',
            detailUrl: '/tuozhanbao/orders/detail.do',
            saveUrl: '/tuozhanbao/orders/save.do',

            idKey: 'id',
            resultKey: 'orders',

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