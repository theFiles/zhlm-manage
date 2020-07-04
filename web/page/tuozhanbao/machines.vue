<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-09-22 <br/>
描述：机具主页面
-->

<template>
    <div class="page">

        <!-- 搜索栏 -->
        <table-search @search="search" @reset="reset" ref="tableSearch" v-model="searchForm">
            <form-item label="商品名称" name="name"></form-item>
            <!--<form-item label="机具型号" name="model_number"></form-item>-->
            <form-item label="厂家" name="manufacturer" type="select" url="/tuozhanbao/enum/getManufacturer.do" select-text="text" select-value="value"></form-item>
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

            <el-table-column label="名称" prop="name"></el-table-column>
            <el-table-column label="机具型号" prop="model_number"></el-table-column>
            <el-table-column label="厂家" prop="manufacturer"></el-table-column>
            <el-table-column label="价格" prop="price" width="80" sortable="custom">
                <template slot-scope="scope">
                    ￥{{ scope.row.price.toFixed(2) }}
                </template>
            </el-table-column>
            <el-table-column label="返现金额" prop="return_money" width="80" sortable="custom">
                <template slot-scope="scope">
                    ￥{{ scope.row.return_money.toFixed(2) }}
                </template>
            </el-table-column>
            <el-table-column label="起步台数" prop="min_count" width="80" sortable="custom"></el-table-column>
            <el-table-column label="创建时间" prop="create_time" width="170" sortable="custom"></el-table-column>
            <el-table-column label="修改时间" prop="update_time" width="170" sortable="custom"></el-table-column>
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
            url: '/tuozhanbao/machines/pageList.do',
            detailUrl: '/tuozhanbao/machines/detail.do',
            saveUrl: '/tuozhanbao/machines/save.do',
            deleteUrl: '/tuozhanbao/machines/delete.do',
            deleteBatchUrl: '/tuozhanbao/machines/deleteBatch.do',
            exportUrl: '/tuozhanbao/machines/list.do',

            idKey: 'id',
            resultKey: 'machines',

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