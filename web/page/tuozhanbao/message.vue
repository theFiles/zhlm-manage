<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-09-22 <br/>
描述：消息表主页面
-->

<template>
    <div class="page">

        <!-- 搜索栏 -->
        <table-search @search="search" @reset="reset" ref="tableSearch" v-model="searchForm">
            <form-item label="消息类型" name="type" type="select" url="/tuozhanbao/enum/getMessageType.do" select-text="text" select-value="value"></form-item>
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

            <el-table-column label="用户id" prop="user_id"></el-table-column>
            <el-table-column label="消息类型" prop="type">
                <template slot-scope="scope">
                    {{ messageType[scope.row.type] }}
                </template>
            </el-table-column>
            <el-table-column label="标题" prop="title"></el-table-column>
            <el-table-column label="消息内容" prop="context"></el-table-column>
            <!--<el-table-column label="推送url路径" prop="link"></el-table-column>-->
            <el-table-column label="是否已读" prop="is_read" width="80">
                <template slot-scope="scope">{{ scope.row.is_read == 1 ? '是' : '否' }}</template>
            </el-table-column>
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

        <!-- 添加修改弹窗 1-新页面 2-弹窗 -->
        <el-dialog v-if="!openNewPage"
                   :title="(form.id ? '修改' : '添加')"
                   :visible.sync="dialogFormVisible"
                   :close-on-click-modal="false"
                   ref="dialog">

            <ape-form ref="form" v-model="form">
                <form-item label="用户id" name="user_id" ></form-item>
                <form-item label="消息类型" name="type" type="select" url="/tuozhanbao/enum/getMessageType.do" select-text="text" select-value="value" ></form-item>
                <form-item label="标题" name="title" ></form-item>
                <form-item label="消息内容" name="context" ></form-item>
                <!--<form-item label="推送url路径" name="link" ></form-item>-->
                <form-item label="是否已读" name="is_read" type="select" list-type="yesOrNo" ></form-item>
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
            url: '/tuozhanbao/message/pageList.do',
            detailUrl: '/tuozhanbao/message/detail.do',
            saveUrl: '/tuozhanbao/message/save.do',
            deleteUrl: '/tuozhanbao/message/delete.do',
            deleteBatchUrl: '/tuozhanbao/message/deleteBatch.do',
            exportUrl: '/tuozhanbao/message/list.do',

            idKey: 'id',
            resultKey: 'message',

            action: 'view, modify, delete',

            template: template,
            data: function () {
                return {
                    messageType: {
                        1: '系统通知',
                        2: '用户通知',
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