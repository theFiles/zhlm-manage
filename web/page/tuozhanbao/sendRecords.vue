<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-09-22 <br/>
描述：配送记录主页面
-->

<template>
    <div class="page">

        <!-- 搜索栏 -->
        <table-search @search="search" @reset="reset" ref="tableSearch" v-model="searchForm">
            <form-item label="配送单号" name="send_no"></form-item>
            <form-item label="状态" name="state" type="select" url="/tuozhanbao/enum/getSendState.do" select-text="text" select-value="value"></form-item>
            <form-item label="快递单号" name="express_no"></form-item>
            <form-item label="快递公司" name="express_company"></form-item>
            <form-item label="联系电话" name="phone"></form-item>
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

            <el-table-column label="订单号" prop="order_id_display"></el-table-column>
            <el-table-column label="订单状态" prop="order_state_display"></el-table-column>
            <el-table-column label="配送单号" prop="send_no"></el-table-column>
            <el-table-column label="配送数量" prop="count" width="80"></el-table-column>
            <el-table-column label="配送状态" prop="state">
                <template slot-scope="scope">
                    {{ state[scope.row.state] }}
                </template>
            </el-table-column>
            <el-table-column label="创建时间" prop="create_time" width="170" sortable="custom"></el-table-column>
            <el-table-column label="发货时间" prop="send_time" width="170" sortable="custom"></el-table-column>
            <el-table-column label="快递单号" prop="express_no"></el-table-column>
            <el-table-column label="快递公司" prop="express_company"></el-table-column>
            <el-table-column label="联系人" prop="name"></el-table-column>
            <el-table-column label="联系电话" prop="phone"></el-table-column>
            <el-table-column label="省份" prop="province"></el-table-column>
            <el-table-column label="城市" prop="city"></el-table-column>
            <el-table-column label="区" prop="area"></el-table-column>
            <el-table-column label="详细地址" prop="address"></el-table-column>
            <el-table-column label="操作" fixed="right" width="200">
                <template slot-scope="scope">
                    <!--<el-button type="primary" size="mini" @click="view(scope.row)" icon="fas fa-eye">查看</el-button>-->
                    <el-button :disabled="scope.row.state != '1'" type="primary" size="mini" @click="edit(scope.row)" icon="el-icon-edit">发货</el-button>
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
                   :title="(form.id ? '发货' : '添加')"
                   :visible.sync="dialogFormVisible"
                   :close-on-click-modal="false"
                   ref="dialog">

            <ape-form ref="form" v-model="form">
                <!--<form-item label="订单id" name="order_id" required></form-item>-->
                <!--<form-item label="配送单号" name="send_no" ></form-item>
                <form-item label="配送数量" name="count" ></form-item>-->
                <!--<form-item label="状态" name="state" type="select" url="/tuozhanbao/enum/getSendState.do" select-text="text" select-value="value" ></form-item>-->
                <!--<form-item label="创建时间" name="create_time" type="date" ></form-item>
                <form-item label="发货时间" name="send_time" type="date" ></form-item>-->

                <form-item label="快递单号" name="express_no" ></form-item>
                <form-item label="快递公司" name="express_company" ></form-item>
                <form-item label="品牌" name="brand" ></form-item>
                <form-item label="型号" name="model" ></form-item>
                <value-edit-list label="SN码" name="snCode"></value-edit-list>

                <!--<form-item label="联系人" name="name" ></form-item>
                <form-item label="联系电话" name="phone" ></form-item>
                <form-item label="省份" name="province" ></form-item>
                <form-item label="城市" name="city" ></form-item>
                <form-item label="区" name="area" ></form-item>
                <form-item label="详细地址" name="address" ></form-item>-->
            </ape-form>


            <div slot="footer" class="dialog-footer">
                <el-button size="small" @click="dialogFormVisible = false">取 消</el-button>
                <!--<el-button size="small" type="primary" @click="save()" :loading="saveLoading">确 定</el-button>-->
                <el-button size="small" type="primary" @click="send()" :loading="saveLoading">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
    define(["vue", "listMixins", "api"], function (Vue, listMixins, api) {
        return Vue.component("tablePage", {
            openNewPage:false,// 新建、修改、查看是否跳转新页面，否则为弹窗模式展示
            url: '/tuozhanbao/sendRecords/pageList.do',
            detailUrl: '/tuozhanbao/sendRecords/detail.do',
            saveUrl: '/tuozhanbao/sendRecords/save.do',
            deleteUrl: '/tuozhanbao/sendRecords/delete.do',
            deleteBatchUrl: '/tuozhanbao/sendRecords/deleteBatch.do',
            exportUrl: '/tuozhanbao/sendRecords/list.do',

            idKey: 'id',
            resultKey: 'sendRecords',

            action: 'view, modify, delete',

            template: template,
            data: function () {
                return {
                    state: {
                        1: '已提交',
                        2: '已发货',
                        3: '已完成',
                    },
                    list:[0],



                };
            },
            mixins: [listMixins],
            mounted: function () {
            },
            onLoad(){
                var a=document.getElementsByClassName('formInput');
                console.log('输出：'+a);
            },
            methods: {
                send: function () {
                    var e = this;

                    var sendRecords = {
                        id: this.form.id,
                        express_no: this.form.express_no,
                        express_company: this.form.express_company,
                        brand:this.form.brand,
                        model:this.form.model,
                        snCode:this.form.snCode
                    }
                    console.log(sendRecords);
                    e.saveLoading = true;
                    api.post('/tuozhanbao/sendRecords/send.do', {"sendRecords": JSON.stringify(sendRecords)}).then(function (s) {
                        e.saveLoading = false;
                        e.dialogFormVisible = false;
                        e.$refs.dataTable.fetch()
                    }).catch(function (error) {
                        let message='出错了';
                        e.saveLoading = false;
                        e.$message({ message: message, type: 'error'})
                    });
                }
            }
        });;
    });
</script>