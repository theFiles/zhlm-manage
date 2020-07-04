<template>
  <div class="page">


    <table-search @search="search" ref="tableSearch">
      <form-item label="商品ID" name="erp_id"></form-item>
      <form-item label="商品名称" name="title"></form-item>
      <template slot="more">
        <form-item label="库存数量" name="stock_count_min"></form-item>
        <form-item label="评分分值" name="evaluate_score_min" name2="evaluate_score_max" type="num-range"></form-item>
        <form-item label="评价次数" name="evaluate_count_min" name2="evaluate_count_max" type="num-range"></form-item>
        <!--  -->
        <form-item label="一级分类" name="one_classify_id" type="select" url="oneClassify_combolist.do" select-text="title"
                   select-value="id"></form-item>
        <form-item label="二级分类" name="two_classify_id" type="select" url="twoClassify_combolist.do"></form-item>
        <form-item label="是否上架" name="is_sale" type="select" list-type="yesOrNo"></form-item>
        <form-item label="是否在打分榜" name="group_dfb" type="select" list-type="yesOrNo"></form-item>
        <form-item label="是否在秒杀" name="group_ms" type="select" list-type="yesOrNo"></form-item>
      </template>
    </table-search>

    <div class="list-action-group">
      <el-button type="primary" size="small" icon="el-icon-plus" @click="add">新建</el-button>
      <el-button type="danger" size="small" icon="el-icon-delete" :disabled="!hasSelectItem" @click="deleteData">删除
      </el-button>
    </div>

    <!-- 列表 -->
    <data-table :url="$options.url"
                :selectable="true"
                ref="dataTable"
                @selectChange="selectListChange">

      <el-table-column label="商品ID" prop="erp_id" width="140"></el-table-column>
      <el-table-column label="商品名称" prop="title" width="220" fixed></el-table-column>

      <el-table-column label="打分榜封面图片" width="120">
        <template slot-scope="scope">
          <img v-if="scope.row.dfb_cover" :src="'http://image.desksnack.com/' + scope.row.dfb_cover"
               style="width:75px;height:37.5px">
        </template>
      </el-table-column>
      <el-table-column label="秒杀封面图片" width="110">
        <template slot-scope="scope">
          <img v-if="scope.row.ms_cover" :src="'http://image.desksnack.com/' + scope.row.ms_cover"
               style="width:60px;height:60px">
        </template>
      </el-table-column>
      <el-table-column label="原价" prop="original_price" width="80" sortable="custom">
        <template slot-scope="scope">
          ￥{{ scope.row.original_price.toFixed(2) }}
        </template>
      </el-table-column>
      <el-table-column label="优惠价" prop="preferential_price" width="90" sortable>
        <template slot-scope="scope">
          ￥{{ scope.row.preferential_price.toFixed(2) }}
        </template>
      </el-table-column>
      <el-table-column label="所在栏目" prop="group_dfb" width="120">
        <template slot-scope="scope">
          <template v-if="scope.row.group_dfb === 1">打分榜</template>
          <template v-if="scope.row.group_ms === 1">秒杀</template>
        </template>
      </el-table-column>
      <el-table-column label="库存数量" prop="stock_count" width="110" sortable></el-table-column>
      <el-table-column label="上架状态" prop="is_sale" width="80">
        <template slot-scope="scope">{{ scope.row.is_sale === 1 ? '上架' : '下架' }}</template>
      </el-table-column>
      <el-table-column label="评价分值" prop="evaluate_score" width="110" sortable></el-table-column>
      <el-table-column label="评价次数" prop="evaluate_count" width="110" sortable></el-table-column>
      <el-table-column label="真实评分值" prop="real_evaluate_score" width="120" sortable></el-table-column>
      <el-table-column label="真实评价次数" prop="real_evaluate_count" width="140" sortable></el-table-column>
      <el-table-column label="创建时间" prop="create_time" width="160" sortable></el-table-column>
      <el-table-column label="操作" fixed="right" width="180">
        <template slot-scope="scope">


          <el-button v-if="$options.action.indexOf('view') !== -1" type="text" @click="view(scope.row)">查看</el-button>
          <el-button v-if="$options.action.indexOf('modify') !== -1" type="text" @click="edit(scope.row)">编辑</el-button>


          <el-dropdown trigger="click">
            <span class="el-dropdown-link">
              更多<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-button type="text" @click="deleteSingleData(scope.row)">删除</el-button>
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
      url: 'squirrel/goods_page.do',
      deleteUrl: 'squirrel/goods_delete.do',
      deleteBatchUrl: 'squirrel/orders_deleteBatch.do',
      exportUrl: 'squirrel/orders_export.do',

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