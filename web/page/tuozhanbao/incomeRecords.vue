<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-09-22 <br/>
描述：收益记录主页面
-->

<template>
    <div class="page">

        <!-- 搜索栏 -->
        <table-search @search="search" @reset="reset" ref="tableSearch" v-model="searchForm">
            <form-item label="用户手机号" name="user_id_display"></form-item>
            <form-item label="商户手机号" name="merchant_id_display"></form-item>
            <form-item label="订单号" name="order_no"></form-item>
            <form-item label="收益类型" name="type" type="select" url="/tuozhanbao/enum/getIncomeType.do" select-text="text" select-value="value"></form-item>
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

            <el-table-column label="用户手机号" prop="user_id_display"></el-table-column>
            <el-table-column label="商户手机号" prop="merchant_id_display"></el-table-column>
            <el-table-column label="订单号" prop="order_no"></el-table-column>
            <el-table-column label="收益类型" prop="type">
                <template slot-scope="scope">
                    {{ incomeType[scope.row.type] }}
                </template>
            </el-table-column>
            <el-table-column label="收益金额" prop="amount" width="80" sortable="custom">
                <template slot-scope="scope">
                    ￥{{ scope.row.amount.toFixed(2) }}
                </template>
            </el-table-column>
            <el-table-column label="原始金额" prop="pristine_amount" width="80" sortable="custom">
                <template slot-scope="scope">
                    ￥{{ scope.row.pristine_amount.toFixed(2) }}
                </template>
            </el-table-column>
            <el-table-column label="剩余金额" prop="surplus_amount" width="80" sortable="custom">
                <template slot-scope="scope">
                    ￥{{ scope.row.surplus_amount.toFixed(2) }}
                </template>
            </el-table-column>
            <!--<el-table-column label="状态" prop="state"></el-table-column>-->
            <el-table-column label="备注" prop="descp"></el-table-column>
            <el-table-column label="创建时间" prop="create_time" width="170" sortable="custom"></el-table-column>
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
                <form-item label="用户ID" name="user_id" ></form-item>
                <form-item label="商户id" name="merchant_id" ></form-item>
                <form-item label="订单号" name="order_no" ></form-item>
                <form-item label="收益类型" name="type" type="select" url="/tuozhanbao/enum/getIncomeType.do" select-text="text" select-value="value" ></form-item>
                <form-item label="收益金额" name="amount" type="price" ></form-item>
                <form-item label="原始金额" name="pristine_amount" type="price" ></form-item>
                <form-item label="剩余金额" name="surplus_amount" type="price" ></form-item>
                <!--<form-item label="状态" name="state" ></form-item>-->
                <form-item label="备注" name="descp" ></form-item>
                <form-item label="创建时间" name="create_time" type="date" ></form-item>
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
            url: '/tuozhanbao/incomeRecords/pageList.do',
            detailUrl: '/tuozhanbao/incomeRecords/detail.do',
            saveUrl: '/tuozhanbao/incomeRecords/save.do',
            deleteUrl: '/tuozhanbao/incomeRecords/delete.do',
            deleteBatchUrl: '/tuozhanbao/incomeRecords/deleteBatch.do',
            exportUrl: '/tuozhanbao/incomeRecords/list.do',

            idKey: 'id',
            resultKey: 'incomeRecords',

            action: 'view, modify, delete',

            template: template,
            data: function () {
                return {
                    incomeType: {
                        1: '激活pos机',
                        2: '刷卡',
                        3: '提现',
                    },
                };
            },
            mixins: [listMixins],
            mounted: function () {
            },
            methods: {
            }
        });
    });
</script>