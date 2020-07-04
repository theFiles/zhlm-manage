<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-09-22 <br/>
描述：轮播图主页面
-->

<template>
    <div class="page">

        <!-- 搜索栏 -->
        <table-search @search="search" @reset="reset" ref="tableSearch" v-model="searchForm">
            <form-item label="标题" name="title"></form-item>
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

            <!--<el-table-column label="位置" prop="position"></el-table-column>-->
            <el-table-column label="标题" prop="title"></el-table-column>
            <!--<el-table-column label="介绍" prop="introduce"></el-table-column>-->
            <el-table-column label="主图片" width="110">
                <template slot-scope="scope">
                    <img v-if="scope.row.image" :src="cdn + scope.row.image" style="width:60px;height:60px">
                </template>
            </el-table-column>
            <el-table-column label="跳转链接" prop="link_url"></el-table-column>
            <!--<el-table-column label="排序值" prop="order_no" width="80" sortable="custom"></el-table-column>-->
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
            url: '/tuozhanbao/banner/pageList.do',
            detailUrl: '/tuozhanbao/banner/detail.do',
            saveUrl: '/tuozhanbao/banner/save.do',
            deleteUrl: '/tuozhanbao/banner/delete.do',
            deleteBatchUrl: '/tuozhanbao/banner/deleteBatch.do',
            exportUrl: '/tuozhanbao/banner/list.do',

            idKey: 'id',
            resultKey: 'banner',

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