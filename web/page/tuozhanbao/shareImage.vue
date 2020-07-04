<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-11-09 <br/>
描述：分享图片主页面
-->

<template>
    <div class="page">

        <!-- 搜索栏 -->
        <table-search @search="search" @reset="reset" ref="tableSearch">
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

            <el-table-column label="主图片" width="110">
                <template slot-scope="scope">
                    <img v-if="scope.row.image" :src="cdn + scope.row.image" style="width:60px;height:60px">
                </template>
            </el-table-column>
            <el-table-column label="排序值" prop="order_no" width="80" sortable="custom"></el-table-column>
            <el-table-column label="备注" prop="comment"></el-table-column>
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
                <form-item label="主图片" name="image" type="upload-image" :limit="1" required></form-item>
                <form-item label="排序值" name="order_no" ></form-item>
                <form-item label="备注" name="comment" ></form-item>
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
            url: '/tuozhanbao/shareImage/pageList.do',
            detailUrl: '/tuozhanbao/shareImage/detail.do',
            saveUrl: '/tuozhanbao/shareImage/save.do',
            deleteUrl: '/tuozhanbao/shareImage/delete.do',
            deleteBatchUrl: '/tuozhanbao/shareImage/deleteBatch.do',
            exportUrl: '/tuozhanbao/shareImage/list.do',

            idKey: 'id',
            resultKey: 'shareImage',

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