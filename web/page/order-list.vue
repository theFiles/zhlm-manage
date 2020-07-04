<template>
    <div class="page">

        <table-search ref="tableSearch" @search="search" @reset="reset">
            <form-item label="订单号" name="order_num"></form-item>
            <form-item label="子订单号" name="order_item_num"></form-item>
            <template slot="more">
                <form-item label="订单类型" name="type"></form-item>
                <form-item label="订单状态" name="state" type="list" url="squirrel/orders_getOrderType.do"></form-item>
                <form-item label="联系电话" name="link_tel"></form-item>
                <form-item label="创建时间" type="date-range" name="create_time_begin" name2="create_time_end"></form-item>
                <form-item label="修改时间" type="date-range-split" name="modify_time_begin" name2="modify_time_end"></form-item>
            </template>
        </table-search>

        <div class="list-action-group">
            <el-button size="small" icon="el-icon-download" @click="exportUrl">导出</el-button>
        </div>

        <!-- 列表 -->
        <data-table :url="$options.url"
                    :selectable="true"
                    ref="dataTable"
                    @selectChange="selectListChange">

            <el-table-column label="订单号" prop="order_num" width="240">
                <template slot-scope="scope">
                    <el-button type="text" size="mini" @click="view(scope.row)">{{ scope.row.order_num }}</el-button>
                </template>
            </el-table-column>
            <el-table-column label="子订单号" prop="order_item_num" width="240"></el-table-column>
            <el-table-column label="商品" width="320" prop="goods">
                <div slot-scope="scope" style="line-height: 30px">
                    <p v-for="content in scope.row.goods.split(',')">{{ content }}</p>
                </div>
            </el-table-column>
            <el-table-column label="订单类型" width="145" prop="type_name"></el-table-column>
            <el-table-column label="订单状态" prop="state_name" width="110"></el-table-column>
            <el-table-column label="总金额" prop="total_amount" width="90" sortable></el-table-column>
            <el-table-column label="红包金额" prop="redpacket_amount" width="120" sortable></el-table-column>
            <el-table-column label="邮费" prop="postage" width="110" sortable></el-table-column>
            <el-table-column label="实收款" prop="pay_amount" width="100" sortable></el-table-column>
            <el-table-column label="创建时间" prop="create_time" width="180" sortable></el-table-column>
            <el-table-column label="收货人" prop="receiver" width="110" sortable></el-table-column>
            <el-table-column label="联系电话" prop="link_tel" width="120" sortable></el-table-column>

        </data-table>

    </div>
</template>

<script>
  define(["vue", "listMixins"], function(Vue, listMixins) {
    return Vue.component("tablePage", {
      url: 'squirrel/orders_page.do',
      deleteUrl: 'squirrel/orders_delete.do',
      deleteBatchUrl: 'squirrel/orders_deleteBatch.do',
      exportUrl: 'squirrel/orders_export.do',

      template: template,
      data: function() {
        return {
        };
      },
      mixins: [listMixins],
      mounted: function() {
      },
    });
  });
</script>