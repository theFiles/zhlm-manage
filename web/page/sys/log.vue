<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-09-11 <br/>
描述：日志记录主页面
-->

<template>
    <div class="page">

        <!-- 搜索栏 -->
        <table-search @search="search" @reset="reset" ref="tableSearch">
            <form-item label="日志时间" type="date-range-split" name="sl_date_begin" name2="sl_date_end"></form-item>
            <form-item label="登录账号" name="sl_user_code"></form-item>
            <form-item label="用户名称" name="sl_user_name"></form-item>
        </table-search>

        <!-- 操作按钮 -->
        <div class="list-action-group">
            <el-button type="danger" size="small" icon="el-icon-delete" :disabled="!hasSelectItem" @click="deleteData" :loading="deleteLoading">删除</el-button>
        </div>

        <!-- 列表 -->
        <data-table :url="$options.url"
                    :selectable="true"
                    ref="dataTable"
                    default-sort-key="sl_date"
                    default-sort-order="desc"
                    @selectChange="selectListChange">

            <el-table-column label="日志时间" prop="sl_date" width="170" sortable="custom"></el-table-column>
            <el-table-column label="登录账号" prop="sl_user_code"></el-table-column>
            <el-table-column label="用户名称" prop="sl_user_name"></el-table-column>
            <el-table-column label="机构名称" prop="sl_org_name"></el-table-column>
            <el-table-column label="ip地址" prop="sl_ip"></el-table-column>
            <el-table-column label="类名称" prop="sl_class"></el-table-column>
            <el-table-column label="方法名称" prop="sl_method"></el-table-column>
            <el-table-column label="方法描述" prop="sl_description"></el-table-column>
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
            url: '/sys/log/pageList.do',
            detailUrl: '/sys/log/detail.do',
            saveUrl: '/sys/log/save.do',
            deleteUrl: '/sys/log/delete.do',
            deleteBatchUrl: '/sys/log/deleteBatch.do',
            exportUrl: '/sys/log/list.do',

            idKey: 'sl_id',
            resultKey: 'log',

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