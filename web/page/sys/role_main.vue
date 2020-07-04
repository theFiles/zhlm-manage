<template>
  <div class="page" style="display: flex">

    <div class="role_left">
      <div class="list-action-group">
        <el-button type="primary" size="small" icon="el-icon-plus" @click="add">新建</el-button>
      </div>

      <table class="tree-table" v-loading="loading">
        <thead>
        <tr>
          <th>角色编码</th>
          <th>角色名称</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="role in roleList" class="role-row clickable" @click="openRoleDetail(role)" :class="{ active: role.sr_id === currentRole.sr_id }">
          <td>
            {{ role.sr_code }}
          </td>
          <td>{{ role.sr_name }}</td>
          <td>
            <el-button type="text" @click.stop="modifyRole(role)">修改</el-button>
            <el-button type="text" @click.stop="deleteRole(role)">删除</el-button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>

    <div class="role_right">
      <table class="tree-table" v-loading="detailLoading">
        <thead>
        <tr>
          <th>菜单名称</th>
          <th>菜单功能</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="menu in roleMenuList" v-show="menu._show" class="role-menu-row">
          <td>
            <span :style="{ paddingLeft: 45 * menu._level + 'px' }"></span>
            <span v-if="menu.children && menu.children.length > 0" class="table-row-expand-icon" :class="menu._expand ? 'table-row-expanded' : 'table-row-collapsed'" @click="toggleMenuExpand(menu)"></span>
            <span style="padding-left: 29px;" v-else></span>
            <el-checkbox disabled :indeterminate="menu._indeterminate" v-model="menu._parentCheck" v-if="menu._level === 0 && menu.children">{{ menu.sm_name }}</el-checkbox>
            <el-checkbox disabled :indeterminate="menu._indeterminate" v-model="menu._check" v-else>{{ menu.sm_name }}</el-checkbox>
          </td>
          <td class="role__checkbox_group">
            <el-checkbox disabled v-model="action._check" v-for="action in menu.actionList">{{ action.sa_group }}</el-checkbox>
          </td>
        </tr>
        </tbody>
      </table>
    </div>

    <!-- #3 编辑/添加 -->
    <el-dialog :title="(form.sr_id ? '修改' : '添加') + '角色'" :visible.sync="dialogFormVisible" :close-on-click-modal="false" ref="dialog">
      <div v-loading="detailLoading">
        <ape-form v-model="form" ref="form" class="role_form">
          <form-item label="角色编码" required name="sr_code"></form-item>
          <form-item label="角色名称" required name="sr_name"></form-item>
        </ape-form>

        <table class="tree-table">
          <thead>
          <tr>
            <th>菜单名称</th>
            <th>菜单功能</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="menu in dialogRoleMenuList" v-show="menu._show">
            <td>
              <span :style="{ paddingLeft: 45 * menu._level + 'px' }"></span>
              <span v-if="menu.children && menu.children.length > 0" class="table-row-expand-icon" :class="menu._expand ? 'table-row-expanded' : 'table-row-collapsed'" @click="toggleMenuExpand(menu)"></span>
              <span style="padding-left: 29px;" v-else></span>
              <el-checkbox :indeterminate="menu._indeterminate" v-model="menu._parentCheck" v-if="menu._level === 0 && menu.children">{{ menu.sm_name }}</el-checkbox>
              <el-checkbox :indeterminate="menu._indeterminate" v-model="menu._check" v-else>{{ menu.sm_name }}</el-checkbox>
            </td>
            <td class="role__checkbox_group">
              <el-checkbox v-model="action._check" v-for="action in menu.actionList">{{ action.sa_group }}</el-checkbox>
            </td>
          </tr>
          </tbody>
        </table>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogFormVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="save()" :loading="saveLoading">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>"use strict";define(["vue","api"],function(t,d){return t.component("sys_page",{template:template,data:function(){return{loading:!1,detailLoading:!1,saveLoading:!1,form:{sr_code:"",sr_name:""},dialogFormVisible:!1,menuList:[],roleList:[],currentRole:{},roleMenuList:[],dialogRoleMenuList:[],expandMenuIdList:[]}},computed:{parentList:function(){return this.menuList.map(function(t){return{text:t.sm_name,value:t.sm_id}})}},mounted:function(){this.fetch()},methods:{fetch:function(){var e=this;this.loading=!0,d.post("sys/role/list.do").then(function(t){e.loading=!1,e.roleList=t.result}).catch(function(t){e.$alert(t,"获取菜单失败"),e.loading=!1})},toggleMenuExpand:function(t){if(t._expand=!t._expand,t._expand)this.expandMenuIdList.push(t.sm_id);else{var e=this.expandMenuIdList.indexOf(t.sm_id);-1<e&&this.expandMenuIdList.slice(e,1)}},add:function(){var e=this;this.form={sr_code:"",sr_name:""},this.detailLoading=!0,this.dialogFormVisible=!0,this.$nextTick(function(){e.$refs.form.updateFormData(),e.$refs.dialog.$el.scrollTo(0,0)}),d.post("sys/role/detail.do",{sr_id:0}).then(function(t){e.detailLoading=!1,e.dialogRoleMenuList=[],e.generateParent(e.dialogRoleMenuList,t.result)}).catch(function(t){console.log(t),e.$alert(t,"获取菜单详情失败"),e.detailLoading=!1,e.dialogFormVisible=!1})},openRoleDetail:function(t){var e=this;this.detailLoading=!0,this.currentRole=t,d.post("sys/role/detail.do",{sr_id:t.sr_id}).then(function(t){e.detailLoading=!1,e.roleMenuList=[],e.generateParent(e.roleMenuList,t.result)}).catch(function(t){console.log(t),e.$alert(t,"获取角色详情失败"),e.detailLoading=!1})},modifyRole:function(e){var n=this;this.detailLoading=!0,this.dialogFormVisible=!0,this.$nextTick(function(){n.$refs.dialog.$el.scrollTo(0,0)}),d.post("sys/role/detail.do",{sr_id:e.sr_id}).then(function(t){n.detailLoading=!1,n.dialogRoleMenuList=[],n.generateParent(n.dialogRoleMenuList,t.result),n.form=Object.assign({},e),n.$nextTick(function(){n.$refs.form.updateFormData()})}).catch(function(t){console.log(t),n.$alert(t,"获取菜单详情失败"),n.detailLoading=!1,n.dialogFormVisible=!1})},deleteRole:function(t){var e=this,n=t.sr_id;this.$confirm("此操作将永久删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){d.post("sys/role/delete.do",{sr_id:n}).then(function(t){e.$message({type:"success",message:"删除成功!"}),e.fetch()}).catch(function(t){e.$alert(""+t,"删除失败")})})},generateParent:function(l,t){var s=this,c=2<arguments.length&&void 0!==arguments[2]?arguments[2]:null,d=3<arguments.length&&void 0!==arguments[3]?arguments[3]:0,e=!0,n=!1,i=void 0;try{for(var u,o=function(){var a=u.value;if(s.$set(a,"_expand",!0),s.$set(a,"_level",d),a._parent=c,s.$set(a,"_check",!!Number(a.check)),a.actionList){var t=!0,e=!1,n=void 0;try{for(var i,o=a.actionList[Symbol.iterator]();!(t=(i=o.next()).done);t=!0){var r=i.value;s.$set(r,"_check",!!Number(r.check))}}catch(t){e=!0,n=t}finally{try{!t&&o.return&&o.return()}finally{if(e)throw n}}}Object.defineProperty(a,"_show",{get:function(){return!a._parent||a._parent._expand&&a._parent._show}}),(a.children&&0<a.children.length||a.actionList&&0<a.actionList.length)&&(Object.defineProperty(a,"_check",{get:function(){if(a.actionList&&0<a.actionList.length)return a.actionList.filter(function(t){return t._check}).length===a.actionList.length},set:function(t){if(a.actionList){var e=!0,n=!1,i=void 0;try{for(var o,r=a.actionList[Symbol.iterator]();!(e=(o=r.next()).done);e=!0){o.value._check=t}}catch(t){n=!0,i=t}finally{try{!e&&r.return&&r.return()}finally{if(n)throw i}}}}}),Object.defineProperty(a,"_indeterminate",{get:function(){if(a.actionList&&0<a.actionList.length){var t=a.actionList.filter(function(t){return t._check}).length;return 0<t&&t!==a.actionList.length}}})),Object.defineProperty(a,"_parentCheck",{get:function(){if(a.children&&0<a.children.length)return a.children.filter(function(t){return t._check}).length===a.children.length},set:function(t){if(a.children){var e=!0,n=!1,i=void 0;try{for(var o,r=a.children[Symbol.iterator]();!(e=(o=r.next()).done);e=!0){o.value._check=t}}catch(t){n=!0,i=t}finally{try{!e&&r.return&&r.return()}finally{if(n)throw i}}}}}),l.push(a),a.children&&0<a.children.length&&s.generateParent(l,a.children,a,d+1)},r=t[Symbol.iterator]();!(e=(u=r.next()).done);e=!0)o()}catch(t){n=!0,i=t}finally{try{!e&&r.return&&r.return()}finally{if(n)throw i}}},save:function(){var e=this;if(this.$refs.form.validate()){var t={},n=this.saveLoading=!0,i=!1,o=void 0;try{for(var r,a=this.dialogRoleMenuList[Symbol.iterator]();!(n=(r=a.next()).done);n=!0){var l=r.value;if(console.log(l),l.actionList){var s=l.actionList.filter(function(t){return t._check}),c=s.length;0===l.actionList.length&&l._check?t[l.id]=[]:0<c&&(t[l.id]=s.map(function(t){return t.sa_id}))}else l._check&&(t[l.id]=[])}}catch(t){i=!0,o=t}finally{try{!n&&a.return&&a.return()}finally{if(i)throw o}}d.post("sys/role/save.do",{role:this.form,menuAction:t}).then(function(t){e.dialogFormVisible=!1,e.fetch(),e.$message({type:"success",message:"保存成功"}),e.saveLoading=!1}).catch(function(t){e.$alert(t,"保存失败"),e.saveLoading=!1})}else this.$refs.dialog.$el.scrollTo(0,0)}}})});</script>

<style>
  .role_right {
    flex: 1 1 auto;
    margin-left: 16px;
    padding-top: 50px;
  }
  .clickable {
    cursor: pointer;
  }
  .role-row.active {
    font-weight: bold;
  }
  .role_form {
    display: flex;
  }
  .role-menu-row .el-checkbox__input.is-disabled+span.el-checkbox__label {
    color: inherit;
  }
  .role-menu-row .el-checkbox__input.is-disabled.is-checked .el-checkbox__inner::after {
    border-color: #000000;
  }
  .role_form .form-item .control-wrapper {
    flex: 1 1 auto;
  }
  .role_form .form-item:last-child {
    margin-left: 16px;
  }

  .role__checkbox_group .el-checkbox+.el-checkbox {
    margin: 0;
  }
  .role__checkbox_group .el-checkbox {
    margin-right: 30px !important;
  }
</style>