<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-09-22 <br/>
描述：用户新增&修改页面
-->

<template>
    <div class="page" v-loading="loading">

        <ape-form ref="form" v-model="form">
            <form-item label="登录密码" name="login_pwd" ></form-item>
            <form-item label="等级" name="grade" type="select" url="/tuozhanbao/enum/getUserGrades.do" select-text="text" select-value="value" ></form-item>
            <form-item label="昵称" name="nick_name" ></form-item>
            <form-item label="奖励台数" name="hortation_num" ></form-item>

            <!--<form-item label="手机号" name="phone" required></form-item>
            <form-item label="推荐人ID" name="recommon_id" ></form-item>
            <form-item label="编码" name="number" ></form-item>
            <form-item label="推荐码" name="recommon_number" ></form-item>
            <form-item label="用户类型" name="user_type" ></form-item>

            <form-item label="真实姓名" name="real_name" ></form-item>
            <form-item label="身份证号" name="id_card" ></form-item>
            <form-item label="是否实名" name="authenticated" type="select" list-type="yesOrNo" ></form-item>
            <form-item label="是否绑定微信" name="if_wx_register" type="select" list-type="yesOrNo" ></form-item>
            <form-item label="微信号" name="wx_no" ></form-item>
            <form-item label="微信二维码地址" name="wx_qrcode" ></form-item>
            <form-item label="个推cid" name="gt_cid" ></form-item>
            <form-item label="账户余额" name="account_balance" type="price" ></form-item>
            <form-item label="分润余额" name="profit_balance" type="price" ></form-item>
            <form-item label="结算卡号" name="settle_card_no" ></form-item>
            <form-item label="结算户名" name="settle_name" ></form-item>
            <form-item label="开户银行" name="settle_bank" ></form-item>
            <form-item label="开户省份" name="settle_province" ></form-item>
            <form-item label="开户城市" name="settle_city" ></form-item>
            <form-item label="开户网点" name="settle_branch_bank" ></form-item>
            <form-item label="预留手机号" name="settle_phone" ></form-item>
            <form-item label="头像" name="head_image" type="upload-image" :limit="1" ></form-item>
            <form-item label="隐私开关" name="privacy" ></form-item>
            <form-item label="储蓄卡正面扫描" name="settle_card_z_scan" type="upload-image" :limit="1" ></form-item>
            <form-item label="储蓄卡反面扫描" name="settle_card_f_scan" type="upload-image" :limit="1" ></form-item>
            <form-item label="身份证正面照" name="id_card_z_image" type="upload-image" :limit="1" ></form-item>
            <form-item label="身份证反面照" name="id_card_f_image" type="upload-image" :limit="1" ></form-item>
            <form-item label="手持身份证照" name="hand_idcard_image" type="upload-image" :limit="1" ></form-item>
            <form-item label="信用卡正面照" name="credit_card_z_image" type="upload-image" :limit="1" ></form-item>
            <form-item label="手持信用卡照" name="hand_credit_image" type="upload-image" :limit="1" ></form-item>
            <form-item label="状态" name="state" ></form-item>
            <form-item label="权限" name="authority" ></form-item>
            <form-item label="拒绝理由" name="reject_reason" ></form-item>
            <form-item label="创建时间" name="create_time" type="date" ></form-item>-->
        </ape-form>


        <div class="form-item-control">
            <el-button size="small" type="primary" @click="submitForm" :loading="loading">提交</el-button>
        </div>

    </div>
</template>

<script>
    define(["vue", "api", "baseFormMixins"], function(Vue, api, baseFormMixins) {
        return Vue.component("modify", {

            url: '/tuozhanbao/userInfo/save.do',
            detailUrl: '/tuozhanbao/userInfo/detail.do',
            saveUrl: '/tuozhanbao/userInfo/save.do',

            idKey: 'id',
            resultKey: 'userInfo',

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