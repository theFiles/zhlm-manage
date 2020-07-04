<template>
  <div class="page">


    <table-search @search="search" ref="tableSearch">
      <form-item label="配置KEY" name="configkey"></form-item>
      <form-item label="配置值" name="configvalue"></form-item>
      <template slot="more">
        <form-item label="配置描述" name="configdesc"></form-item>
        <form-item label="创建时间" name="configaddtime_begin" name2="configaddtime_end" type="date-range-split"></form-item>
      </template>
    </table-search>

    <div class="list-action-group">
      <el-button type="primary" size="small" icon="el-icon-plus" @click="add">新建</el-button>
    </div>

    <!-- 列表 -->
    <data-table :url="$options.url"
                :selectable="false"
                ref="dataTable"
                default-sort-key="configaddtime"
                default-sort-order="desc">

      <el-table-column label="配置KEY" prop="configkey" ></el-table-column>
      <el-table-column label="配置值" prop="configvalue"></el-table-column>
      <el-table-column label="配置描述" prop="configdesc"></el-table-column>
      <el-table-column label="配置类型" prop="configtype">
        <template slot-scope="scope">
          {{ scope.row.configtype === 0 ? "用户配置" : "系统配置" }}
        </template>
      </el-table-column>
      <el-table-column label="配置时间" prop="configaddtime"></el-table-column>

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

    <el-dialog v-if="!openNewPage"
               :title="(form.id ? '修改' : '添加')"
               :visible.sync="dialogFormVisible"
               :close-on-click-modal="false"
               ref="dialog">

      <ape-form ref="form" v-model="form">
        <form-item label="配置KEY" name="configkey" required></form-item>
        <form-item label="配置值" name="configvalue" required></form-item>
        <form-item label="配置描述" name="configdesc" required></form-item>
        <form-item label="配置类型" name="configtype" type="select" :list="[{ text: '用户配置', value: 0 }, { text: '系统配置', value: 1 }]" required></form-item>
      </ape-form>

      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogFormVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="save()" :loading="saveLoading">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>"use strict";define(["vue","listMixins"],function(s,e){return s.component("sys_page",{openNewPage:!1,idKey:"configid",url:"sys/sysconfig/list.do",detailUrl:"sys/sysconfig/detail.do",saveUrl:"sys/sysconfig/save.do",deleteUrl:"sys/sysconfig/delete.do",template:template,data:function(){return{form:{configtype:0}}},computed:{saveParams:function(){return{sysconfig:this.form}}},mixins:[e],mounted:function(){},methods:{detailLoadSuccessCallback:function(s){var e=this;this.form=s.result.sysconfig,this.$nextTick(function(){e.$refs.form.updateFormData()})}}})});</script>