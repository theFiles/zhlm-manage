<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-12-27 <br/>
描述：商家主页面
-->

<template>
    <div class="page">

        <!-- 搜索栏 -->
        <table-search @search="search" @reset="reset" ref="tableSearch" v-model="searchForm">
            <form-item label="用户" name="user_id_display"></form-item>
            <form-item label="sn" name="sn"></form-item>
            <form-item label="状态" name="state"></form-item>
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

            <el-table-column label="用户" prop="user_id_display"></el-table-column>
            <!--<el-table-column label="姓名" prop="name"></el-table-column>
            <el-table-column label="手机号码" prop="phone"></el-table-column>-->
            <el-table-column label="头像" prop="image"></el-table-column>
            <el-table-column label="sn" prop="sn"></el-table-column>
            <el-table-column label="厂商" prop="manufacturer"></el-table-column>
            <el-table-column label="创建时间" prop="create_time" width="170" sortable="custom"></el-table-column>
            <el-table-column label="型号" prop="model"></el-table-column>
            <el-table-column label="状态" prop="state"></el-table-column>
            <el-table-column label="类型：0订单、1奖励、2手动添加" prop="type"></el-table-column>
            <el-table-column label="分拨记录" prop="record"></el-table-column>
            <el-table-column label="订单id" prop="order_id_display"></el-table-column>
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
            url: '/tuozhanbao/merchant/pageList.do',
            detailUrl: '/tuozhanbao/merchant/detail.do',
            saveUrl: '/tuozhanbao/merchant/save.do',
            deleteUrl: '/tuozhanbao/merchant/delete.do',
            deleteBatchUrl: '/tuozhanbao/merchant/deleteBatch.do',
            exportUrl: '/tuozhanbao/merchant/list.do',

            idKey: 'id',
            resultKey: 'merchant',

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