<template>
  <div class="page" style="display: flex">

    <div class="role_left">

      <table class="tree-table" v-loading="loading">
        <thead>
        <tr>
          <th>部门</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="org in showOrgList" v-show="org._show" class="org-row" @click="selectOrg(org)" :class="{ active: currentOrg && currentOrg.so_code === org.so_code }">
          <td>
            <span :style="{ paddingLeft: 45 * org._level + 'px' }"></span>
            <span v-if="org.child_orgs.length > 0" class="table-row-expand-icon" :class="org._expand ? 'table-row-expanded' : 'table-row-collapsed'" @click.stop  ="toggleMenuExpand(org)"></span>
            <span style="padding-left: 29px;" v-else></span>
            {{ org.so_name }}
          </td>
        </tr>
        </tbody>
      </table>
    </div>

    <div class="role_right">
      <div class="list-action-group">
        <el-button type="primary" size="small" icon="el-icon-plus" @click="add">新建</el-button>
      </div>

      <data-table url="sys/user/pageList.do"
                  :selectable="false"
                  ref="dataTable"
                  default-sort-key="su_id"
                  default-sort-order="desc">
        <el-table-column label="用户账号" prop="su_code" sortable></el-table-column>
        <el-table-column label="用户名称" prop="su_name" sortable></el-table-column>
        <el-table-column label="所在机构" prop="so_name" sortable></el-table-column>
        <el-table-column label="状态" prop="su_status" sortable>
          <template slot-scope="scope">
            {{ scope.row.su_status == 1 ? '启用中' : '禁用中' }}
          </template>
        </el-table-column>
        <el-table-column label="性别" prop="su_sex" sortable></el-table-column>
        <el-table-column label="用户上次登录IP" prop="su_last_ip" sortable width="195"></el-table-column>
        <el-table-column label="用户上次登录时间" prop="su_last_time" sortable width="195"></el-table-column>
        <el-table-column label="用户上次登录次数" prop="su_login_count" sortable></el-table-column>
        <el-table-column label="身份" prop="erp_id" sortable></el-table-column>
        <el-table-column label="操作" fixed="right" width="195">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="modify(scope.row)" icon="el-icon-edit">修改</el-button>

            <el-dropdown trigger="hover">
              <el-button type="primary" size="mini">
                更多<i class="el-icon-arrow-down el-icon--right"></i>
              </el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="openChangePswDialog(scope.row)">密码修改</el-dropdown-item>
                <el-dropdown-item @click.native="deleteUser(scope.row)">删除</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>

            <!--<el-button type="primary" size="mini" @click="openChangePswDialog(scope.row)" icon="fas fa-unlock-alt">密码修改</el-button>-->
            <!--<el-button type="danger" size="mini" @click="deleteUser(scope.row)" icon="el-icon-delete">删除</el-button>-->
          </template>
        </el-table-column>
      </data-table>
    </div>

    <!-- #3 编辑/添加 -->
    <el-dialog :title="(form.su_id ? '修改' : '添加') + '用户'" :visible.sync="dialogFormVisible" :close-on-click-modal="false" ref="dialog">
      <div v-loading="detailLoading">
        <ape-form v-model="form" ref="form">
          <form-item label="用户账号" required name="su_code"></form-item>
          <form-item label="用户名称" required name="su_name"></form-item>
          <form-item label="所属机构" required name="so_id" type="cascader" :list="orgList" ref="cascader"></form-item>
          <form-item label="性别" name="su_sex" type="radio" :list="[{ text: '男' }, { text: '女' }]"></form-item>
          <form-item label="密码" type="custom">
            <template slot="custom">
              <p style="line-height: 32px">默认: 888888</p>
            </template>
          </form-item>
          <form-item label="联系方式" name="su_contact"></form-item>
          <form-item label="邮箱" name="su_email"></form-item>
          <form-item label="备注" name="su_content" type="textarea"></form-item>
          <form-item label="角色分配" name="sr_id" type="checkboxGroup" :list="roleList"></form-item>
          <form-item label="是否启用" name="su_status" type="checkbox" checkLabel="启用"></form-item>
        </ape-form>

      </div>

      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogFormVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="save()" :loading="saveLoading">确 定</el-button>
      </div>
    </el-dialog>

    <!-- #4 密码修改 -->
    <el-dialog title="修改密码" :visible.sync="dialogPswVisible" :close-on-click-modal="false">
      <div>
        <ape-form v-model="pswForm" ref="pswForm">
          <form-item label="用户账号" type="custom">
            <template slot="custom">
              <p style="line-height: 32px" v-if="currentUser">{{ currentUser.su_code }}</p>
            </template>
          </form-item>
          <form-item label="用户名称" type="custom">
            <template slot="custom">
              <p style="line-height: 32px" v-if="currentUser">{{ currentUser.su_name }}</p>
            </template>
          </form-item>
          <form-item label="新密码" name="su_password_new" input-type="password" valid="password"></form-item>
          <form-item label="确认密码" name="su_password_check" input-type="password" :valid="validConfirmPsw"></form-item>
        </ape-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogPswVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="changePassword()" :loading="pswLoading">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>"use strict";define(["vue","api","md5"],function(s,i,e){return s.component("sys_page",{template:template,data:function(){return{loading:!1,detailLoading:!1,saveLoading:!1,form:{su_sex:"男",sr_id:[],su_status:!0},pswForm:{su_password_new:"",su_password_check:""},dialogFormVisible:!1,menuList:[],orgList:[],showOrgList:[],userListParams:{so_code:""},currentOrg:null,roleList:[],currentRole:{},roleMenuList:[],dialogRoleMenuList:[],expandMenuIdList:[],dialogPswVisible:!1,pswLoading:!1,currentUser:null}},computed:{parentList:function(){return this.menuList.map(function(s){return{text:s.sm_name,value:s.sm_id}})}},mounted:function(){this.fetch(),this.fetchRole()},activated:function(){this.fetchRole()},methods:{fetch:function(){var t=this;this.loading=!0,i.post("sys/org/tree.do").then(function(s){t.loading=!1,t.orgList=s.result,t.showOrgList=[],t.generateParent(t.orgList)}).catch(function(s){t.$alert(s,"获取机构失败"),t.loading=!1})},fetchRole:function(){var t=this;i.post("sys/role/list.do").then(function(s){t.roleList=s.result.map(function(s){return{text:s.sr_name,value:s.sr_id}}),console.log(t.roleList)}).catch(function(s){t.$alert(s,"获取角色列表失败"),t.loading=!1})},generateParent:function(s){var t=this,e=1<arguments.length&&void 0!==arguments[1]?arguments[1]:null,i=2<arguments.length&&void 0!==arguments[2]?arguments[2]:0,n=!0,r=!1,o=void 0;try{for(var a,u=function(){var s=a.value;s.value=s.so_id,s.label=s.so_name,s.children=0<s.child_orgs.length?s.child_orgs:null,t.$set(s,"_expand",-1!==t.expandMenuIdList.indexOf(s.so_id)),t.$set(s,"_level",i),s._parent=e,Object.defineProperty(s,"_show",{get:function(){return!s._parent||s._parent._expand&&s._parent._show}}),t.showOrgList.push(s),0<s.child_orgs.length&&t.generateParent(s.child_orgs,s,i+1)},d=s[Symbol.iterator]();!(n=(a=d.next()).done);n=!0)u()}catch(s){r=!0,o=s}finally{try{!n&&d.return&&d.return()}finally{if(r)throw o}}},toggleMenuExpand:function(s){if(console.log(s),s._expand=!s._expand,s._expand)this.expandMenuIdList.push(s.so_id);else{var t=this.expandMenuIdList.indexOf(s.so_id);-1<t&&this.expandMenuIdList.slice(t,1)}},selectOrg:function(s){this.currentOrg&&s.so_code===this.currentOrg.so_code?this.currentOrg=null:this.currentOrg=s,this.userListParams={so_code:this.currentOrg?this.currentOrg.so_code:""},this.$refs.dataTable.update(this.userListParams)},add:function(){var s=this;this.form={su_sex:"男",sr_id:[],su_status:!0},this.dialogFormVisible=!0,this.$nextTick(function(){s.$refs.form.updateFormData(),s.$refs.dialog.$el.scrollTo(0,0)})},modify:function(s){var d=this;this.detailLoading=!0,this.dialogFormVisible=!0,this.$nextTick(function(){d.$refs.dialog.$el.scrollTo(0,0)}),i.post("sys/user/detail.do",{su_id:s.su_id}).then(function(s){var t=s.result.user;t.su_status=1===Number(t.su_status),t.sr_id=t.sr_id.split(",");var e=null,i=!0,n=!1,r=void 0;try{for(var o,a=d.showOrgList[Symbol.iterator]();!(i=(o=a.next()).done);i=!0){var u=o.value;if(u.so_id===t.so_id){e=u;break}}}catch(s){n=!0,r=s}finally{try{!i&&a.return&&a.return()}finally{if(n)throw r}}d.form=s.result.user,d.detailLoading=!1,d.$nextTick(function(){if(console.log(e),e){var s=[];d.getOrgIdList(e,s),console.log(s),d.$refs.cascader.setCascaderValue(s.reverse())}d.$refs.form.updateFormData()})}).catch(function(s){console.error(s)})},deleteUser:function(s){var t=this,e=s.su_id;this.$confirm("此操作将永久删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){i.post("sys/user/delete.do",{su_id:e}).then(function(s){t.$message({type:"success",message:"删除成功!"}),t.$refs.dataTable.update()}).catch(function(s){t.$alert(""+s,"删除失败")})})},getOrgIdList:function(s,t){s._parent?(t.push(s._parent.so_id),this.getOrgIdList(s._parent,t)):t.push(s.so_id)},save:function(){var t=this;if(this.$refs.form.validate()){this.saveLoading=!0;var s=Object.assign({},this.form);s.sr_id=s.sr_id.join(","),s.su_status=s.su_status?1:0,i.post("sys/user/save.do",{user:s}).then(function(s){t.dialogFormVisible=!1,t.$refs.dataTable.update(),t.$message({type:"success",message:"保存成功"}),t.saveLoading=!1}).catch(function(s){t.$alert(s,"保存失败"),t.saveLoading=!1})}else this.$refs.dialog.$el.scrollTo(0,0)},openChangePswDialog:function(s){var t=this;this.currentUser=s,this.dialogPswVisible=!0,this.pswForm={su_password_new:"",su_password_check:""},this.$nextTick(function(){t.$refs.pswForm.updateFormData()})},changePassword:function(){var t=this;if(this.$refs.pswForm.validate()){var s={su_id:this.currentUser.su_id,su_password:e(this.pswForm.su_password_new)};this.pswLoading=!0,i.post("sys/user/editPass.do",{user:s}).then(function(s){t.dialogPswVisible=!1,t.$message({type:"success",message:"修改成功"}),t.pswLoading=!1}).catch(function(s){t.$alert(s,"修改失败"),t.pswLoading=!1})}},validConfirmPsw:function(s){return{valid:this.pswForm.su_password_new===s,info:"密码不一致"}}}})});</script>

<style>
  .role_left {
    min-width: 300px;
  }
  .role_right {
    flex: 1 1 auto;
    margin-left: 16px;
    overflow-x: auto;
  }
  .clickable {
    cursor: pointer;
  }
  .org-row {
    cursor: pointer;
  }
  .org-row.active {
    font-weight: bold;
  }
</style>