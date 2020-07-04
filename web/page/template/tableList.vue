<template>
  <div class="page">

    <!-- #1 搜索 -->
    <table-search @search="search" ref="tableSearch">
      <form-item label="商品ID" name="erp_id"></form-item>
      <form-item label="商品名称" name="title"></form-item>
      <template slot="more">
        <form-item label="库存数量" name="stock_count_min"></form-item>
      </template>
    </table-search>

    <!-- #2 操作按钮 -->
    <div class="list-action-group">
      <el-button type="primary" size="small" icon="el-icon-plus" @click="add">新建</el-button>
      <el-button type="danger" size="small" icon="el-icon-delete" :disabled="!hasSelectItem" @click="deleteData">删除
      </el-button>
    </div>

    <!-- #3 列表 -->
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
      <el-table-column label="创建时间" prop="create_time" width="160" sortable></el-table-column>
      <el-table-column label="操作" fixed="right" width="180">
        <template slot-scope="scope">

          <el-button type="primary" size="mini" @click="view(scope.row)">查看</el-button>
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