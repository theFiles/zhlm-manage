<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-09-22 <br/>
描述：系统配置表主页面
-->

<template>
    <div class="page">

        <!-- 搜索栏 -->
        <table-search @search="search" @reset="reset" ref="tableSearch" v-model="searchForm">
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

            <el-table-column label="id" prop="configid" width="80" sortable="custom"></el-table-column>
            <el-table-column label="配置名称" prop="configkey"></el-table-column>
            <el-table-column label="配置值" prop="configvalue"></el-table-column>
            <el-table-column label="创建时间" prop="configaddtime" width="170" sortable="custom"></el-table-column>
            <el-table-column label="配置说明" prop="configdesc"></el-table-column>
            <el-table-column label="配置类型" prop="configtype" width="80" sortable="custom"></el-table-column>
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

        <!-- 添加修改弹窗 1-新页面 2-弹窗 -->
        <el-dialog v-if="!openNewPage"
                   :title="(form.id ? '修改' : '添加')"
                   :visible.sync="dialogFormVisible"
                   :close-on-click-modal="false"
                   ref="dialog">

            <ape-form ref="form" v-model="form">
                <form-item label="id" name="configid" required></form-item>
                <form-item label="配置名称" name="configkey" required></form-item>
                <form-item label="配置值" name="configvalue" ></form-item>
                <form-item label="创建时间" name="configaddtime" type="date" ></form-item>
                <form-item label="配置说明" name="configdesc" ></form-item>
                <form-item label="配置类型" name="configtype" type="select" url="/tuozhanbao/enum/getConfigType.do" select-text="text" select-value="value" ></form-item>
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
            url: '/tuozhanbao/config/pageList.do',
            detailUrl: '/tuozhanbao/config/detail.do',
            saveUrl: '/tuozhanbao/config/save.do',
            deleteUrl: '/tuozhanbao/config/delete.do',
            deleteBatchUrl: '/tuozhanbao/config/deleteBatch.do',
            exportUrl: '/tuozhanbao/config/list.do',

            idKey: 'id',
            resultKey: 'config',

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