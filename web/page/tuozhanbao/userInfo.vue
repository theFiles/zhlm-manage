<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-09-22 <br/>
描述：用户主页面
-->

<template>
    <div class="page">

        <!-- 搜索栏 -->
        <table-search @search="search" @reset="reset" ref="tableSearch" v-model="searchForm">
            <form-item label="手机号" name="phone"></form-item>
            <!--<form-item label="推荐码" name="recommon_number"></form-item>-->
            <!--<form-item label="真实姓名" name="real_name"></form-item>-->
            <!--<form-item label="微信号" name="wx_no"></form-item>-->
        </table-search>

        <!-- 操作按钮 -->
        <div class="list-action-group">
            <el-button type="primary" size="small" icon="el-icon-plus" @click="add">新建</el-button>
            <el-button type="danger" size="small" icon="el-icon-delete" :disabled="!hasSelectItem" @click="deleteData" :loading="deleteLoading">删除</el-button>
        </div>

        <!-- 列表 -->
        <data-table :url="$options.url"
                    :selectable="true"
                    ref="dataTable"
                    default-sort-key="id"
                    default-sort-order="desc"
                    @selectChange="selectListChange">

            <el-table-column label="手机号" prop="phone"></el-table-column>
            <el-table-column label="等级" prop="grade"></el-table-column>
            <!-- <el-table-column label="登录密码" prop="login_pwd"></el-table-column> -->
            <el-table-column label="推荐人ID" prop="recommon_id"></el-table-column>
            <el-table-column label="编码" prop="number"></el-table-column>
            <!--<el-table-column label="推荐码" prop="recommon_number"></el-table-column>-->
            <el-table-column label="用户类型" prop="user_type"></el-table-column>
            <el-table-column label="昵称" prop="nick_name"></el-table-column>
            <el-table-column label="真实姓名" prop="real_name"></el-table-column>
            <el-table-column label="身份证号" prop="id_card"></el-table-column>
            <el-table-column label="是否实名" prop="authenticated" width="80">
                <template slot-scope="scope">{{ scope.row.authenticated == 1 ? '是' : '否' }}</template>
            </el-table-column>
            <el-table-column label="是否绑定微信" prop="if_wx_register" width="80">
                <template slot-scope="scope">{{ scope.row.if_wx_register == 1 ? '是' : '否' }}</template>
            </el-table-column>
            <el-table-column label="微信号" prop="wx_no"></el-table-column>
            <<!--el-table-column label="微信二维码地址" prop="wx_qrcode"></el-table-column>
            <el-table-column label="个推cid" prop="gt_cid"></el-table-column>-->
            <el-table-column label="账户余额" prop="account_balance" width="80" sortable="custom">
                <template slot-scope="scope">
                    ￥{{ scope.row.account_balance.toFixed(2) }}
                </template>
            </el-table-column>
            <el-table-column label="分润余额" prop="profit_balance" width="80" sortable="custom">
                <template slot-scope="scope">
                    ￥{{ scope.row.profit_balance.toFixed(2) }}
                </template>
            </el-table-column>
            <el-table-column label="结算卡号" prop="settle_card_no"></el-table-column>
            <el-table-column label="结算户名" prop="settle_name"></el-table-column>
            <el-table-column label="开户银行" prop="settle_bank"></el-table-column>
            <el-table-column label="开户省份" prop="settle_province"></el-table-column>
            <el-table-column label="开户城市" prop="settle_city"></el-table-column>
            <el-table-column label="开户网点" prop="settle_branch_bank"></el-table-column>
            <el-table-column label="预留手机号" prop="settle_phone"></el-table-column>
            <el-table-column label="头像" width="110">
                <template slot-scope="scope">
                    <img v-if="scope.row.head_image" :src="cdn + scope.row.head_image" style="width:60px;height:60px">
                </template>
            </el-table-column>
            <el-table-column label="奖励台数" prop="hortation_num" width="80" sortable="custom"></el-table-column>
            <!--<el-table-column label="隐私开关" prop="privacy"></el-table-column>
            <el-table-column label="储蓄卡正面扫描" width="110">
                <template slot-scope="scope">
                    <img v-if="scope.row.settle_card_z_scan" :src="cdn + scope.row.settle_card_z_scan" style="width:60px;height:60px">
                </template>
            </el-table-column>
            <el-table-column label="储蓄卡反面扫描" width="110">
                <template slot-scope="scope">
                    <img v-if="scope.row.settle_card_f_scan" :src="cdn + scope.row.settle_card_f_scan" style="width:60px;height:60px">
                </template>
            </el-table-column>
            <el-table-column label="身份证正面照" width="110">
                <template slot-scope="scope">
                    <img v-if="scope.row.id_card_z_image" :src="cdn + scope.row.id_card_z_image" style="width:60px;height:60px">
                </template>
            </el-table-column>
            <el-table-column label="身份证反面照" width="110">
                <template slot-scope="scope">
                    <img v-if="scope.row.id_card_f_image" :src="cdn + scope.row.id_card_f_image" style="width:60px;height:60px">
                </template>
            </el-table-column>
            <el-table-column label="手持身份证照" width="110">
                <template slot-scope="scope">
                    <img v-if="scope.row.hand_idcard_image" :src="cdn + scope.row.hand_idcard_image" style="width:60px;height:60px">
                </template>
            </el-table-column>
            <el-table-column label="信用卡正面照" width="110">
                <template slot-scope="scope">
                    <img v-if="scope.row.credit_card_z_image" :src="cdn + scope.row.credit_card_z_image" style="width:60px;height:60px">
                </template>
            </el-table-column>
            <el-table-column label="手持信用卡照" width="110">
                <template slot-scope="scope">
                    <img v-if="scope.row.hand_credit_image" :src="cdn + scope.row.hand_credit_image" style="width:60px;height:60px">
                </template>
            </el-table-column>-->
            <!--<el-table-column label="状态" prop="state"></el-table-column>
            <el-table-column label="拒绝理由" prop="reject_reason"></el-table-column>-->
            <el-table-column label="创建时间" prop="create_time" width="170" sortable="custom"></el-table-column>
            <el-table-column label="操作" fixed="right" width="200">
                <template slot-scope="scope">
                    <!--<el-button type="primary" size="mini" @click="view(scope.row)" icon="fas fa-eye">查看</el-button>-->
                    <el-button type="primary" size="mini" @click="edit(scope.row)" icon="el-icon-edit">编辑</el-button>
                    <el-dropdown trigger="hover">
                        <el-button type="primary" size="mini">
                            更多<i class="el-icon-arrow-down el-icon--right"></i>
                        </el-button>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item @click.native="deleteSingleData(scope.row)">删除</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </template>
            </el-table-column>
        </data-table>


    </div>
</template>

<script>
    define(["vue", "listMixins"], function (Vue, listMixins) {
        return Vue.component("tablePage", {
            url: '/tuozhanbao/userInfo/pageList.do',
            detailUrl: '/tuozhanbao/userInfo/detail.do',
            saveUrl: '/tuozhanbao/userInfo/save.do',
            deleteUrl: '/tuozhanbao/userInfo/delete.do',
            deleteBatchUrl: '/tuozhanbao/userInfo/deleteBatch.do',
            exportUrl: '/tuozhanbao/userInfo/list.do',

            idKey: 'id',
            resultKey: 'userInfo',

            action: 'view, modify, delete',

            template: template,
            data: function () {
                return {};
            },
            mixins: [listMixins],
            mounted: function () {
            },
            methods: {
            }
        });
    });
</script>