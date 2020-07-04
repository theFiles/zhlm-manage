<template>
  <div class="page">

    <!-- #1 Top -->
    <div class="list-action-group">
      <el-button type="primary" size="small" icon="el-icon-plus" @click="add">新建</el-button>
    </div>

    <!-- #2 表格 -->
    <table class="tree-table" v-loading="loading">
      <thead>
      <tr>
        <th>机构名称</th>
        <th>机构编码</th>
        <th>联系方式</th>
        <th>邮箱地址</th>
        <th>邮编</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>

      <tr v-for="org in showOrgList" v-show="org._show">
        <td>
          <span :style="{ paddingLeft: 45 * org._level + 'px' }"></span>
          <span v-if="org.child_orgs.length > 0" class="table-row-expand-icon" :class="org._expand ? 'table-row-expanded' : 'table-row-collapsed'" @click="toggleMenuExpand(org)"></span>
          <span style="padding-left: 29px;" v-else></span>
          {{ org.so_name }}
        </td>
        <td>{{ org.so_code }}</td>
        <td>{{ org.so_contact }}</td>
        <td>{{ org.so_email }}</td>
        <td>{{ org.so_post }}</td>
        <td>
          <el-button type="text" @click="modifyMenu(org)">编辑</el-button>
          <el-button type="text" @click="deleteMenu(org)" :disabled="org.child_orgs.length > 0">删除</el-button>
        </td>
      </tr>


      </tbody>
    </table>

    <!-- #3 编辑/添加 -->
    <el-dialog :title="(form.so_id ? '修改' : '添加') + '机构'" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
      <div v-loading="detailLoading">
        <ape-form v-model="form" ref="form">
          <form-item label="机构编码" name="so_code" required></form-item>
          <form-item label="机构名称" name="so_name" required></form-item>
          <form-item label="上级机构" name="so_parentid" type="cascader" :list="orgList" ref="cascader"></form-item>
          <form-item label="联系方式" name="so_contact"></form-item>
          <form-item label="邮箱地址" name="so_email"></form-item>
          <form-item label="邮政编码" name="so_post"></form-item>
        </ape-form>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogFormVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="save()" :loading="saveLoading">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>"use strict";define(["vue","api"],function(t,i){return t.component("sys_page",{template:template,data:function(){return{loading:!1,detailLoading:!1,saveLoading:!1,form:{},dialogFormVisible:!1,orgList:[],showOrgList:[],expandMenuIdList:[]}},computed:{parentList:function(){this.orgList.map(function(){})}},mounted:function(){this.fetch()},methods:{fetch:function(){var e=this;this.loading=!0,i.post("sys/org/tree.do").then(function(t){e.loading=!1,e.orgList=t.result,e.showOrgList=[],e.generateParent(e.orgList)}).catch(function(t){e.$alert(t,"获取机构失败"),e.loading=!1})},generateParent:function(t){var e=this,n=1<arguments.length&&void 0!==arguments[1]?arguments[1]:null,i=2<arguments.length&&void 0!==arguments[2]?arguments[2]:0,s=!0,o=!1,a=void 0;try{for(var r,d=function(){var t=r.value;t.value=t.so_id,t.label=t.so_name,t.children=0<t.child_orgs.length?t.child_orgs:null,e.$set(t,"_expand",-1!==e.expandMenuIdList.indexOf(t.so_id)),e.$set(t,"_level",i),t._parent=n,Object.defineProperty(t,"_show",{get:function(){return!t._parent||t._parent._expand&&t._parent._show}}),e.showOrgList.push(t),0<t.child_orgs.length&&e.generateParent(t.child_orgs,t,i+1)},c=t[Symbol.iterator]();!(s=(r=c.next()).done);s=!0)d()}catch(t){o=!0,a=t}finally{try{!s&&c.return&&c.return()}finally{if(o)throw a}}},toggleMenuExpand:function(t){if(console.log(t),t._expand=!t._expand,t._expand)this.expandMenuIdList.push(t.so_id);else{var e=this.expandMenuIdList.indexOf(t.so_id);-1<e&&this.expandMenuIdList.slice(e,1)}},add:function(){var t=this;this.form={sm_icon:"",sm_name:"",sm_parentid:"",sm_action:"",sm_content:""},this.dialogFormVisible=!0,this.$nextTick(function(){t.$refs.form.updateFormData()}),this.$refs.cascader.setCascaderValue([])},modifyMenu:function(e){var n=this;this.detailLoading=!0,this.form=Object.assign({},e),this.dialogFormVisible=!0,this.$nextTick(function(){var t=[];n.getOrgIdList(e,t),n.$refs.cascader.setCascaderValue(t.reverse())}),i.post("sys/org/detail.do",{so_id:e.so_id}).then(function(t){n.form=t.result.org,n.$refs.form.updateFormData(),n.detailLoading=!1}).catch(function(t){n.$alert(t,"获取菜单详情失败"),n.detailLoading=!1,n.dialogFormVisible=!1})},getOrgIdList:function(t,e){console.log(t.so_name,t.so_id,t._parent),t._parent&&(e.push(t._parent.so_id),this.getOrgIdList(t._parent,e))},deleteMenu:function(t){var e=this;this.$confirm("此操作将永久删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){i.post("sys/org/delete.do",{so_id:t.so_id}).then(function(t){e.$message({type:"success",message:"删除成功!"}),e.fetch()}).catch(function(t){e.$alert(""+t,"删除失败")})})},save:function(){var e=this;this.$refs.form.validate()&&(this.saveLoading=!0,i.post("sys/org/save.do",{org:this.form}).then(function(t){e.dialogFormVisible=!1,e.fetch(),e.$message({type:"success",message:"保存成功"}),e.saveLoading=!1}).catch(function(t){e.$alert(t,"保存失败"),e.saveLoading=!1}))}}})});</script>

<style scoped>

</style>