<template>
  <div class="editTable" v-loading="loading">

    <el-table
            ref="dataTable"
            :data="innerShowDataList"
            tooltip-effect="dark"
            row-key="_index"
            style="width: 100%">

      <slot></slot>

      <el-table-column label="操作" width="120" fixed="right" v-if="editable">
        <template slot-scope="scope">

          <template v-if="scope.row._editing && !scope.row._addFlag">
            <el-button type="text" @click="modifyAction(scope.row)">保存</el-button>
            <el-button type="text" @click="cancelEdit(scope.row)">取消</el-button>
          </template>

          <template v-else>
            <el-button v-if="scope.row._addFlag" type="text" @click="addAction(scope.row)">新增</el-button>
            <el-button v-else type="text" @click="changeEditStatus(scope.row, true)">修改</el-button>
            <el-popover
                    class="edit-delete"
                    v-if="scope.row._addFlag || !scope.row._editing"
                    :ref="'popover' + scope.row._index"
                    trigger="click"
                    placement="top"
                    width="160">
              <p class="popover-message"><i class="el-icon-info"></i>是否要删除此行？</p>
              <div style="text-align: right; margin: 0">
                <el-button type="text" size="mini" @click="dismiss(scope.row)">取消</el-button>
                <el-button type="primary" size="mini" @click="removeRow(scope.row)">确定</el-button>
              </div>
              <el-button slot="reference" type="text" @click="removeAction(scope.row)">删除</el-button>
            </el-popover>
          </template>

        </template>
      </el-table-column>

    </el-table>

    <el-button plain size="small" @click="addRow" style="width: 100%;margin-top: 16px;margin-bottom: 8px;border-style: dashed;" icon="el-icon-plus" v-if="editable">
      添加
    </el-button>

  </div>
</template>

<script>"use strict";define(["vue","config","api","lodash"],function(t,e,i,a){return t.component("dataTable",{template:template,data:function(){return{loading:!1,editing:!1,test:!1,innerDataList:[]}},props:{url:String,params:Object,header:Array,lazy:Boolean,dataList:Array,itemId:{type:String,default:"id"},editable:{type:Boolean,default:!0}},mounted:function(){},watch:{innerDataList:function(){this.$emit("update:dataList",this.innerDataList.filter(function(t){return!t._addFlag}))}},computed:{innerShowDataList:function(){return this.innerDataList.filter(function(t){return 2!==t.status})}},methods:{setDataList:function(t){var i=this;t.forEach(function(t,e){i.processData(t,e,!0)}),this.innerDataList=t},processData:function(t,e){var i=2<arguments.length&&void 0!==arguments[2]&&arguments[2];Object.defineProperty(t,"_index",{enumerable:!1,writable:!1,value:e}),this.$set(t,"_addFlag",!1),this.$set(t,"_editing",!1),this.$set(t,"_params",{}),Object.defineProperty(t,"_child",{enumerable:!1,writable:!0,value:[]}),Object.defineProperty(t,"_clone",{enumerable:!1,writable:!0,value:null}),t._fromRemote=i},update:function(t){this.searchParams=t||{},this.fetch()},reset:function(){this.currentPage=1,this.$refs.dataTable.clearSelection(),this.fetch()},addRow:function(){var t={};this.processData(t,this.innerDataList.length),t._addFlag=!0,t._editing=!0,this.innerDataList.push(t)},changeEditStatus:function(t,e){t._child.forEach(function(t){t.editable=e}),t._editing!==e&&!0===e&&(t._clone=a.clone(t)),t._editing=e},addAction:function(t){var e=!0,i=!0,a=!1,n=void 0;try{for(var r,s=t._child[Symbol.iterator]();!(i=(r=s.next()).done);i=!0){var o=r.value;o.validate&&(o.validate()||(e=!1))}}catch(t){a=!0,n=t}finally{try{!i&&s.return&&s.return()}finally{if(a)throw n}}e&&(t._addFlag=!1,t.status=0,this.changeEditStatus(t,!1),this.$emit("update:dataList",this.innerDataList.filter(function(t){return!t._addFlag})))},modifyAction:function(t){var e=!0,i=!0,a=!1,n=void 0;try{for(var r,s=t._child[Symbol.iterator]();!(i=(r=s.next()).done);i=!0){var o=r.value;o.validate&&(o.validate()||(e=!1))}}catch(t){a=!0,n=t}finally{try{!i&&s.return&&s.return()}finally{if(a)throw n}}e&&(t._fromRemote&&(t.status=1),this.changeEditStatus(t,!1),this.$emit("update:dataList",this.innerDataList.filter(function(t){return!t._addFlag})))},removeRow:function(e){this.$refs["popover"+e._index].showPopper=!1;var t=a.findIndex(this.innerDataList,function(t){return t._index==e._index}),i=this.innerDataList[t];-1<t&&(i._fromRemote?i.status=2:this.innerDataList.splice(t,1))},removeAction:function(t){},cancelEdit:function(t){if(t._clone)for(var e in t)e.startsWith("_")||(t[e]=t._clone[e]);this.changeEditStatus(t,!1)},getClearData:function(t){var e={};for(var i in t)i.startsWith("_")||(e[i]=t[i]);return e},dismiss:function(t){console.log(this.$refs["popover"+t._index]),this.$refs["popover"+t._index].showPopper=!1}}})});</script>
