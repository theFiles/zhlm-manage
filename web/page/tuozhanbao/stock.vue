<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-12-24 <br/>
描述：库存管理主页面
-->

<template>
    <div class="page">

        <!-- 搜索栏 -->
        <table-search @search="search" @reset="reset" ref="tableSearch"  v-model="searchForm">

            <form-item label="用户账号" name="user_phone"></form-item>
            <form-item label="型号" name="model"></form-item>
            <form-item label="品牌" name="brand"></form-item>
            <form-item label="sn码" name="sn"></form-item>
            <form-item label="状态" name="state"  type="select" :list='[{"text":"未激活","value":"0"},{"text":"已激活","value":"1"}]' ></form-item>
            <!--
            <form-item label="分拨记录" name="record"></form-item>
            -->
        </table-search>

        <!-- 操作按钮 -->
        <div class="list-action-group">
            <!--
            <el-button type="primary" size="small" icon="el-icon-plus" @click="add">新建</el-button>
            <el-button type="danger" size="small" icon="el-icon-delete" :disabled="!hasSelectItem" @click="deleteData" :loading="deleteLoading">删除</el-button>
            -->
        </div>

        <!-- 列表 -->
        <data-table :url="$options.url"
                    :selectable="true"
                    ref="dataTable"
                    default-sort-key="id"
                    default-sort-order="desc"
                    @selectChange="selectListChange">

            <el-table-column label="用户账号" prop="user_id_display"></el-table-column>
            <el-table-column label="型号" prop="model"></el-table-column>
            <el-table-column label="品牌" prop="brand"></el-table-column>
            <el-table-column label="sn码" prop="sn"></el-table-column>
            <el-table-column label="状态" prop="state">
                <template slot-scope="scope">
                    {{ stateMap[scope.row.state] }}
                </template>
            </el-table-column>
            <el-table-column label="创建时间" prop="create_time" width="170" sortable="custom"></el-table-column>
            <!--
            <el-table-column label="类型" prop="type"></el-table-column>
            <el-table-column label="分拨记录" prop="record"></el-table-column>
            <el-table-column label="订单号" prop="ape_orders_id" width="80" sortable="custom"></el-table-column>
            -->
            <el-table-column label="操作" fixed="right" width="200">
                <template slot-scope="scope">
                    <!--
                    <el-button type="primary" size="mini" @click="view(scope.row)" icon="fas fa-eye">查看</el-button>
                    <el-button type="primary" size="mini" @click="edit(scope.row)" icon="el-icon-edit">编辑</el-button>
                    -->
                    <template v-if="scope.row.state == '0'">
                        <el-button type="primary" size="mini" @click.native="deleteSingleData(scope.row)" >解绑</el-button>
                    </template>
                    <!--
                    <el-dropdown trigger="hover">
                        <el-button type="primary" size="mini">
                            更多<i class="el-icon-arrow-down el-icon--right"></i>
                        </el-button>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item @click.native="deleteSingleData(scope.row)">解绑</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                     -->
                </template>
            </el-table-column>
        </data-table>


    </div>
</template>

<script>
    define(["vue", "listMixins"], function (Vue, listMixins) {
        return Vue.component("tablePage", {
            url: '/tuozhanbao/stock/pageList.do',
            detailUrl: '/tuozhanbao/stock/detail.do',
            saveUrl: '/tuozhanbao/stock/save.do',
            deleteUrl: '/tuozhanbao/stock/delete.do',
            deleteBatchUrl: '/tuozhanbao/stock/deleteBatch.do',
            exportUrl: '/tuozhanbao/stock/list.do',

            idKey: 'id',
            resultKey: 'stock',

            action: 'view, modify, delete',

            template: template,
            data: function () {
                return {
                    stateMap: {
                        0: '未激活',
                        1: '已激活'
                    }
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