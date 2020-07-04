<#--
版权：小月科技 <br/>
作者：dailing <br/>
生成日期：2018-09-22 <br/>
描述：购买POS机订单主页面
-->

<template>
    <div class="page">

        <!-- 搜索栏 -->
        <table-search @search="search" @reset="reset" ref="tableSearch" v-model="searchForm">
            <form-item label="用户手机号" name="user_id_display"></form-item>
            <form-item label="订单号" name="order_no"></form-item>
            <form-item label="订单状态" name="state" type="select" url="/tuozhanbao/enum/getOrderState.do" select-text="text" select-value="value"></form-item>
            <form-item label="商品名称" name="goods_name"></form-item>
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

            <el-table-column label="用户手机号" prop="user_id_display"></el-table-column>-
            <el-table-column label="订单号" prop="order_no"></el-table-column>
            <el-table-column label="订单金额" prop="order_amount" width="80">
                <template slot-scope="scope">
                    ￥{{ scope.row.order_amount.toFixed(2) }}
                </template>
            </el-table-column>
            <el-table-column label="订单状态" prop="state">
                <template slot-scope="scope">
                    {{ state[scope.row.state] }}
                </template>
            </el-table-column>
            <el-table-column label="创建时间" prop="create_time" width="170"></el-table-column>
            <el-table-column label="付款时间" prop="pay_time" width="170"></el-table-column>
            <el-table-column label="付款方式" prop="pay_way">
                <template slot-scope="scope">
                    {{ payWay[scope.row.pay_way] }}
                </template>
            </el-table-column>
            <!--<el-table-column label="商品id" prop="goods_id"></el-table-column>-->
            <el-table-column label="商品名称" prop="goods_name"></el-table-column>
            <el-table-column label="商品价格" prop="goods_price" width="80" sortable="custom">
                <template slot-scope="scope">
                    ￥{{ scope.row.goods_price.toFixed(2) }}
                </template>
            </el-table-column>
            <el-table-column label="购买数量" prop="total_count" width="80"></el-table-column>
            <el-table-column label="配送数量" prop="send_count" width="80"></el-table-column>
            <el-table-column label="剩余数量" prop="surplus_count" width="80"></el-table-column>
            <el-table-column label="退货数量" prop="return_count" width="80"></el-table-column>
            <el-table-column label="退款审核是否通过" prop="return_result" width="80">
                <template slot-scope="scope">{{ scope.row.return_result == 1 ? '是' : '否' }}</template>
            </el-table-column>
            <el-table-column label="退款金额" prop="return_money" width="80">
                <template slot-scope="scope">
                    ￥{{ scope.row.return_money.toFixed(2) }}
                </template>
            </el-table-column>
            <el-table-column label="退款路径" prop="return_way">
                <template slot-scope="scope">
                    {{ returnWay[scope.row.return_way] }}
                </template>
            </el-table-column>
            <!--<el-table-column label="审核管理员id" prop="manager_id" width="80"></el-table-column>-->
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
            url: '/tuozhanbao/orders/pageList.do',
            detailUrl: '/tuozhanbao/orders/detail.do',
            saveUrl: '/tuozhanbao/orders/save.do',
            deleteUrl: '/tuozhanbao/orders/delete.do',
            deleteBatchUrl: '/tuozhanbao/orders/deleteBatch.do',
            exportUrl: '/tuozhanbao/orders/list.do',

            idKey: 'id',
            resultKey: 'orders',

            action: 'view, modify, delete',

            template: template,
            data: function () {
                return {
                    state: {
                        1: '待付款',
                        2: '待发货',
                        3: '待收货',
                        4: '已完成',
                        5: '已失效',
                        6: '已取消',
                        7: '申请退款',
                        8: '已退款',
                        9: '拒绝退款',
                    },
                    payWay: {
                        1: '微信支付',
                        2: '支付宝支付',
                    },
                    returnWay: {
                        1: '微信支付',
                        2: '支付宝支付',
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