<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-09-22 <br/>
描述：交易记录表主页面
-->

<template>
    <div class="page">

        <!-- 搜索栏 -->
        <table-search @search="search" @reset="reset" ref="tableSearch" v-model="searchForm">
            <form-item label="sn码" name="sn"></form-item>
            <form-item label="付款方式" name="pay_way" type="select" url="/tuozhanbao/enum/getPayType.do" select-text="text" select-value="value"></form-item>
            <form-item label="交易状态" name="trade_state" type="select" url="/tuozhanbao/enum/getTradeState.do" select-text="text" select-value="value"></form-item>
        </table-search>

        <!-- 操作按钮 -->
        <div class="list-action-group">
            <!--<el-button type="primary" size="small" icon="el-icon-plus" @click="add">新建</el-button>-->
            <el-button type="danger" size="small" icon="el-icon-delete" :disabled="!hasSelectItem" @click="deleteData" :loading="deleteLoading">删除</el-button>
        </div>

        <!-- 列表 -->
        <data-table :url="$options.url"
                    :selectable="true"
                    ref="dataTable"
                    default-sort-key="id"
                    default-sort-order="desc"
                    @selectChange="selectListChange">

            <!--<el-table-column label="app" prop="app"></el-table-column>-->
            <el-table-column label="商户订单号" prop="order_no"></el-table-column>
            <el-table-column label="用户id" prop="user_id"></el-table-column>
            <el-table-column label="商户id" prop="merchant_id"></el-table-column>
            <el-table-column label="sn码" prop="sn"></el-table-column>
            <el-table-column label="厂商" prop="manufacturer"></el-table-column>
            <el-table-column label="交易金额" prop="trade_amount" width="80" sortable="custom">
                <template slot-scope="scope">
                    ￥{{ scope.row.trade_amount.toFixed(2) }}
                </template>
            </el-table-column>
            <el-table-column label="到账金额" prop="arrival_amount" width="80" sortable="custom">
                <template slot-scope="scope">
                    ￥{{ scope.row.arrival_amount.toFixed(2) }}
                </template>
            </el-table-column>
            <el-table-column label="刷卡银行卡" prop="bank_card"></el-table-column>
            <el-table-column label="交易时间" prop="trade_time" width="170" sortable="custom"></el-table-column>
            <el-table-column label="费率" prop="rate" width="80" sortable="custom">
                <template slot-scope="scope">
                    ￥{{ scope.row.rate.toFixed(2) }}
                </template>
            </el-table-column>
            <el-table-column label="商户手续费" prop="poundage" width="80" sortable="custom">
                <template slot-scope="scope">
                    ￥{{ scope.row.poundage.toFixed(2) }}
                </template>
            </el-table-column>
            <el-table-column label="付款方式" prop="pay_way"></el-table-column>
            <el-table-column label="交易状态" prop="trade_state"></el-table-column>
            <el-table-column label="渠道id" prop="channel_id" width="80" sortable="custom"></el-table-column>
            <el-table-column label="渠道商户编号" prop="channel_mer_no"></el-table-column>
            <el-table-column label="渠道订单号" prop="channel_order_no"></el-table-column>
            <el-table-column label="经度" prop="lon" width="80" sortable="custom"></el-table-column>
            <el-table-column label="纬度" prop="lat" width="80" sortable="custom"></el-table-column>
            <el-table-column label="交易地址" prop="trade_address"></el-table-column>
            <el-table-column label="备注" prop="remarks"></el-table-column>
            <el-table-column label="操作" fixed="right" width="120">
                <template slot-scope="scope">
                    <!--<el-button type="primary" size="mini" @click="view(scope.row)" icon="fas fa-eye">查看</el-button>-->
                    <!--<el-button type="primary" size="mini" @click="edit(scope.row)" icon="el-icon-edit">编辑</el-button>-->
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

        <!-- 添加修改弹窗 1-新页面 2-弹窗 -->
        <el-dialog v-if="!openNewPage"
                   :title="(form.id ? '修改' : '添加')"
                   :visible.sync="dialogFormVisible"
                   :close-on-click-modal="false"
                   ref="dialog">

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
                <form-item label="付款方式" name="pay_way" type="select" url="/tuozhanbao/enum/getPayType.do" select-text="text" select-value="value" ></form-item>
                <form-item label="交易状态" name="trade_state" type="select" url="/tuozhanbao/enum/getTradeState.do" select-text="text" select-value="value" ></form-item>
                <form-item label="渠道id" name="channel_id" ></form-item>
                <form-item label="渠道商户编号" name="channel_mer_no" ></form-item>
                <form-item label="渠道订单号" name="channel_order_no" ></form-item>
                <form-item label="失败原因" name="failreason" type="textarea" ></form-item>
                <form-item label="经度" name="lon" ></form-item>
                <form-item label="纬度" name="lat" ></form-item>
                <form-item label="交易地址" name="trade_address" ></form-item>
                <form-item label="备注" name="remarks" ></form-item>
            </ape-form>


            <div slot="footer" class="dialog-footer">
                <el-button size="small" @click="dialogFormVisible = false">取 消</el-button>
                <el-button size="small" type="primary" @click="save()" :loading="saveLoading">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
    define(["vue", "listMixins"], function (Vue, listMixins) {
        return Vue.component("tablePage", {
            openNewPage:false,// 新建、修改、查看是否跳转新页面，否则为弹窗模式展示
            url: '/tuozhanbao/tradeRecords/pageList.do',
            detailUrl: '/tuozhanbao/tradeRecords/detail.do',
            saveUrl: '/tuozhanbao/tradeRecords/save.do',
            deleteUrl: '/tuozhanbao/tradeRecords/delete.do',
            deleteBatchUrl: '/tuozhanbao/tradeRecords/deleteBatch.do',
            exportUrl: '/tuozhanbao/tradeRecords/list.do',

            idKey: 'id',
            resultKey: 'tradeRecords',

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