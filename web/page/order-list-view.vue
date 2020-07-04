<template>
    <div class="page">
        {{ id }}
        <desc-list :item="item" ref="descList">
            <div class="card">
                <div class="title">订单信息</div>
                <desc-row>
                    <desc-col label="订单类型	" name="receiver"></desc-col>
                    <!--<desc-col label="订单号" name="order_num"></desc-col>-->
                    <!--<desc-col label="子订单号	" name="order_item_num"></desc-col>-->
                    <!--<desc-col label="微信流水号" name="erp_id"></desc-col>-->
                    <!--<desc-col label="订单状态	" name="state_name"></desc-col>-->
                    <!--<desc-col label="发起人" name="initiator_id"></desc-col>-->
                    <!--<desc-col label="用户昵称	" name="initiator_id"></desc-col>-->
                    <!--<desc-col label="总金额" name="total_amount"></desc-col>-->
                    <!--<desc-col label="红包金额	" name="redpacket_amount"></desc-col>-->
                    <!--<desc-col label="邮费" name="postage"></desc-col>-->
                    <!--<desc-col label="折扣" name="discount"></desc-col>-->
                    <!--<desc-col label="实际付款金额	" name="total_pay_count"></desc-col>-->
                    <!--<desc-col label="总付款人数" name="total_pay_count"></desc-col>-->
                    <!--<desc-col label="剩余付款人数" name="surplus_pay_count"></desc-col>-->
                    <!--<desc-col label="剩余付款金额" name="surplus_pay_amount"></desc-col>-->
                    <!--<desc-col label="创建时间	" name="create_time"></desc-col>-->
                    <!--<desc-col label="截止时间	" name="blocking_time"></desc-col>-->
                    <!--<desc-col label="成团时间	" name="cluster_time"></desc-col>-->
                    <!--<desc-col label="付款时间	" name="pay_time"></desc-col>-->
                    <!--<desc-col label="发货时间	" name="send_time"></desc-col>-->
                    <!--<desc-col label="收货人" name="receiver"></desc-col>-->
                    <!--<desc-col label="联系电话" name="link_tel"></desc-col>-->
                    <!--<desc-col label="收货地址" name="address"></desc-col>-->
                    <!--<desc-col label="标签" name="label"></desc-col>-->
                    <!--<desc-col label="快递公司" name="express_company"></desc-col>-->
                    <!--<desc-col label="快递单号" name="express_num"></desc-col>-->
                </desc-row>
            </div>
            <div class="divider-horizontal"></div>
            <div class="card">
                <div class="title">凑单用户</div>
                <data-table no-pager
                            no-border
                            :selectable="false"
                            lazy
                            url="orderItems_getOrderUsers.do"
                            :params="{ order_id: $route.params.id, order_num: item.order_num, order_type: item.order_type }">

                    <el-table-column label="openid" prop="open_id"></el-table-column>
                    <el-table-column label="昵称" prop="nick_name"></el-table-column>
                    <el-table-column label="订单号" prop="order_item_num"></el-table-column>
                    <el-table-column label="订单金额" prop="pay_amount"></el-table-column>
                    <el-table-column label="状态" prop="state"></el-table-column>

                </data-table>
            </div>

            <div class="divider-horizontal"></div>
            <div class="card">
                <div class="title">购买商品</div>
                <data-table no-pager
                            no-border
                            :selectable="false"
                            lazy
                            url="orderItems_page.do"
                            :params="{ order_id: $route.params.id }">
                    <el-table-column label="订单项ID" prop="id"></el-table-column>
                    <el-table-column label="商品名称" prop="goods_title" width="200"></el-table-column>
                    <el-table-column label="商品图片" prop="goods_image">
                        <template slot-scope="scope">
                            <img :src='scope.row.goods_image' style="height:58px" />
                        </template>
                    </el-table-column>
                    <el-table-column label="商品价格" prop="goods_price"></el-table-column>
                    <el-table-column label="数量" prop="counts"></el-table-column>
                    <el-table-column label="评分" prop="score"></el-table-column>
                    <el-table-column label="退款类型" prop="refund_type"></el-table-column>
                    <el-table-column label="收货状态" prop="receive_state"></el-table-column>
                    <el-table-column label="退款原因" prop="refund_reason"></el-table-column>
                    <el-table-column label="退款金额" prop="refund_amount"></el-table-column>
                    <el-table-column label="退款说明" prop="refund_explain"></el-table-column>
                    <el-table-column label="联系电话" prop="link_tel"></el-table-column>
                    <el-table-column label="状态" prop="state_name"></el-table-column>


                </data-table>
            </div>

        </desc-list>
    </div>
</template>

<script>
  define(["vue", "api", "viewMixins"], function(Vue, api, viewMixins) {
    return Vue.component("viewPage", {
      url: 'order_detail.do',

      template: template,
      data: function() {
        return {
          item: {},
        };
      },
      props: {
        name: String
      },
      mixins: [viewMixins],
      mounted: function() {
      },
      methods: {
      }
    });
  });
</script>