<template>
  <div class="page">

    <div class="list-action-group">
      <el-button type="primary" size="small" icon="el-icon-plus" @click="add" :disabled="enableSort || enableChildSort">新建</el-button>
      <el-button size="small" @click="changeSort" :disabled="enableChildSort">{{ enableSort ? '更新排序' : '排序' }}</el-button>
    </div>

    <table class="tree-table" v-loading="loading">
      <thead>
      <tr>
        <th>菜单名称</th>
        <th>菜单动作</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody ref="tbody">

      <template v-for="(menu, index) in menuList">

        <tr :class="{ sortable: enableSort, disableSort: enableChildSort }" class="parent-row" :index="menu.sm_id">
          <td>
            <span v-if="menu.child_menus.length > 0 && !enableSort && !enableChildSort" class="table-row-expand-icon" :class="menu._expand ? 'table-row-expanded' : 'table-row-collapsed'" @click="toggleMenuExpand(menu)"></span>
            <span style="padding-left: 29px;" v-else></span>
            {{ menu.sm_name }}
          </td>
          <td>{{ menu.sm_action }}</td>
          <td>
            <template>
              <el-button type="text" @click="modifyMenu(menu)" :disabled="enableSort || enableChildSort">编辑</el-button>
              <el-button type="text" @click="deleteMenu(menu)" :disabled="menu.child_menus.length > 0 || enableSort || enableChildSort">删除</el-button>
              <el-button type="text" @click="changeChildSort(menu)" :disabled="menu.child_menus.length === 0 || (enableChildSort && menu.sm_id !== currentSortChildMenuId)">{{ enableChildSort && menu.sm_id === currentSortChildMenuId ? '更新排序' : '排序' }}</el-button>
            </template>
          </td>
        </tr>

        <tr class="expand-row" v-for="(subMenu, index) in menu.child_menus" v-if="menu._expand" :index="subMenu.sm_id">
          <td>{{ subMenu.sm_name }} </td>
          <td>{{ subMenu.sm_action }}</td>
          <td>
            <el-button type="text" @click="modifyMenu(subMenu)" :disabled="enableChildSort">编辑</el-button>
            <el-button type="text" @click="deleteMenu(subMenu)" :disabled="enableChildSort">删除</el-button>
          </td>
        </tr>


      </template>

      </tbody>
    </table>

    <el-dialog :title="(form.sm_id ? '修改' : '添加') + '菜单'" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
      <div v-loading="detailLoading">
        <ape-form v-model="form" ref="form">
          <form-item label="菜单图标" name="sm_icon" placeholder="图标的名称, 如 book-open" :suffix-icon="form.sm_icon" >
            <el-tooltip class="item" effect="dark" content="图标名称列表" placement="top-start">
              <a style="font-size: 12px; color: #666666; display: inline-block; margin-left: 8px; position: absolute;right: -24px;top: 8px;" href="https://fontawesome.com/icons?d=gallery&m=free" target="_blank"><i class="fas fa-info-circle"></i></a>
            </el-tooltip>
            <!--<i class="fas" :class="'fa-' + form.icon" style="font-size: 36px; color: #555555; margin-top: 4px; display: block"></i>-->
          </form-item>
          <form-item label="菜单名称" name="sm_name" required></form-item>
          <form-item label="父菜单" name="sm_parentid" type="select" :list="parentList"></form-item>
          <form-item label="菜单动作" name="sm_action" required></form-item>
          <form-item label="备注" name="sm_content"></form-item>
        </ape-form>

        <edit-table ref="actionTable" :dataList.sync="actionList" item-id="sm_id">
          <el-table-column label="功能组名" name="first_count">
            <form-item slot-scope="scope" :data="scope.row" name="sa_group" required></form-item>
          </el-table-column>

          <el-table-column label="控制器名">
            <form-item slot-scope="scope" :data="scope.row" name="sa_class" required></form-item>
          </el-table-column>

        </edit-table>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogFormVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="save()" :loading="saveLoading">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>"use strict";define(["vue","api","sortablejs"],function(t,r,e){return t.component("sys_page",{template:template,data:function(){return{loading:!1,detailLoading:!1,saveLoading:!1,form:{},actionList:[],dialogFormVisible:!1,menuList:[],expandMenuIdList:[],sortable:null,childSortable:null,currentSortChildMenuId:"",enableSort:!1,enableChildSort:!1}},computed:{parentList:function(){return this.menuList.map(function(t){return{text:t.sm_name,value:t.sm_id}})}},watch:{enableSort:function(){if(this.sortable.option("disabled",!this.enableSort),this.enableSort){var t=!0,e=!1,i=void 0;try{for(var n,s=this.menuList[Symbol.iterator]();!(t=(n=s.next()).done);t=!0){n.value._expand=!1}}catch(t){e=!0,i=t}finally{try{!t&&s.return&&s.return()}finally{if(e)throw i}}}else this.saveSort()}},mounted:function(){var t=this;this.fetch(),this.$nextTick(function(){t.sortable=e.create(t.$refs.tbody,{group:"sort"}),t.childSortable=e.create(t.$refs.tbody,{filter:".parent-row"}),t.sortable.option("disabled",!t.enableSort),t.childSortable.option("disabled",!t.enableChildSort)})},methods:{fetch:function(){var a=this;this.loading=!0,r.post("sys/menu/list.do").then(function(t){var e=!(a.loading=!1),i=!1,n=void 0;try{for(var s,o=t.result[Symbol.iterator]();!(e=(s=o.next()).done);e=!0){s.value._expand=-1!==a.expandMenuIdList.indexOf(v.sm_id)}}catch(t){i=!0,n=t}finally{try{!e&&o.return&&o.return()}finally{if(i)throw n}}a.menuList=t.result}).catch(function(t){a.$alert(t,"获取菜单失败"),a.loading=!1})},toggleMenuExpand:function(t){if(t._expand=!t._expand,t._expand)this.expandMenuIdList.push(t.sm_id);else{var e=this.expandMenuIdList.indexOf(t.sm_id);-1<e&&this.expandMenuIdList.slice(e,1)}},add:function(){var t=this;this.form={sm_icon:"",sm_name:"",sm_parentid:"",sm_action:"",sm_content:""},this.actionList=[],this.dialogFormVisible=!0,this.$nextTick(function(){t.$refs.form.updateFormData(),t.$refs.actionTable.setDataList(t.actionList)})},modifyMenu:function(t){var e=this;this.detailLoading=!0,this.form=Object.assign({},t),r.post("sys/menu/detail.do",{sm_id:t.sm_id}).then(function(t){e.actionList=t.result.actionList,e.form=t.result.menu,e.$refs.actionTable.setDataList(e.actionList),e.$refs.form.updateFormData(),e.detailLoading=!1}).catch(function(t){e.$alert(t,"获取菜单详情失败"),e.detailLoading=!1,e.dialogFormVisible=!1}),this.dialogFormVisible=!0},deleteMenu:function(t){var e=this,i=t.sm_id;this.$confirm("此操作将永久删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){r.post("sys/menu/delete.do",{sm_id:i}).then(function(t){e.$message({type:"success",message:"删除成功!"}),e.fetch()}).catch(function(t){e.$alert(""+t,"删除失败")})})},save:function(){var e=this;this.form.sm_type||(this.form.sm_type=1),this.$refs.form.validate()&&(this.saveLoading=!0,r.post("sys/menu/save.do",{menu:this.form,actionList:this.actionList}).then(function(t){e.dialogFormVisible=!1,e.fetch(),e.$message({type:"success",message:"保存成功"}),e.saveLoading=!1}).catch(function(t){e.$alert(t,"保存失败"),e.saveLoading=!1}))},changeSort:function(){this.enableSort=!this.enableSort},saveSort:function(t){for(var e=this,i=document.querySelectorAll(t?".expand-row":".parent-row"),n=[],s=0;s<i.length;s++){var o=i[s].getAttribute("index");n.push({sm_id:o,sm_order:s+1})}console.log(n),this.menuList=[],this.loading=!0,r.post("sys/menu/updateOrder.do",{menus:n}).then(function(t){e.fetch()}).catch(function(t){e.loading=!1})},changeChildSort:function(t){if(this.enableChildSort=!this.enableChildSort,this.childSortable.option("disabled",!this.enableChildSort),this.enableChildSort){this.currentSortChildMenuId=t.sm_id;var e=!0,i=!1,n=void 0;try{for(var s,o=this.menuList[Symbol.iterator]();!(e=(s=o.next()).done);e=!0){var a=s.value;t.sm_id===a.sm_id?a._expand=!0:a._expand=!1}}catch(t){i=!0,n=t}finally{try{!e&&o.return&&o.return()}finally{if(i)throw n}}}else this.currentSortChildMenuId="",this.saveSort(!0)}}})});</script>

<style scoped>

</style>