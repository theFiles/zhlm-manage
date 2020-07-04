<template>
  <div class="dataTable" v-loading="loading">
    <el-table
            ref="dataTable"
            :data="dataList"
            tooltip-effect="dark"
            style="width: 100%"
            @sort-change="sortChange"
            :border="!noBorder"
            :row-key="getRowKey"
            @selection-change="handleSelectionChange">

      <el-table-column
              v-if="selectable"
              :reserve-selection="true"
              fixed
              type="selection"
              width="35">
      </el-table-column>

      <slot></slot>

    </el-table>

    <el-pagination
            v-if="!noPager"
            background
            layout="total, prev, pager, next, sizes, jumper"
            :current-page.sync="currentPage"
            @size-change="handleSizeChange"
            :page-size="pageSize"
            :total="total">
    </el-pagination>

  </div>
</template>

<script>"use strict";define(["vue","config","api"],function(t,e,i){return t.component("dataTable",{template:template,data:function(){return{loading:!1,currentPage:1,total:0,pageSize:10,sortKey:"id",sortOrder:"desc",dataList:[],multipleSelection:[],searchParams:{}}},props:{url:String,selectable:{type:Boolean,default:!0},noPager:Boolean,noBorder:Boolean,lazy:Boolean,rowKey:{type:String,default:"rows"},totalKey:{type:String,default:"total"},defaultSortKey:{type:String,default:"id"},defaultSortOrder:{type:String,default:"desc"},params:Object},mounted:function(){var o=this;this.sortKey=this.defaultSortKey,this.sortOrder=this.defaultSortOrder,console.log(this.defaultSortOrder),this.$nextTick(function(){var t=!0,e=!1,r=void 0;try{for(var a,i=o.$refs.dataTable.$children[Symbol.iterator]();!(t=(a=i.next()).done);t=!0){var n=a.value;n.sortable&&(n.sortable="custom")}}catch(t){e=!0,r=t}finally{try{!t&&i.return&&i.return()}finally{if(e)throw r}}}),this.lazy||this.fetch()},watch:{currentPage:function(){this.fetch()},pageSize:function(){this.fetch()},multipleSelection:function(){this.$emit("selectChange",this.multipleSelection)}},methods:{fetch:function(){var e=this;this.loading=!0;var t=this.url,r={rows:this.pageSize,page:this.currentPage,sort:this.sortKey,order:this.sortOrder};this.noPager&&(delete r.rows,delete r.page);var a=Object.assign(r,this.searchParams,this.params);i.get(t,a).then(function(t){t=t.result,1===a.page&&(e.total=t.total||0),e.dataList=t.rows,e.loading=!1,console.log(t)}).catch(function(t){e.loading=!1})},update:function(t){this.searchParams=t||{},this.fetch()},changeSearchParams:function(t){this.searchParams=t||{},1===this.currentPage?this.fetch():this.currentPage=1},reset:function(){1===this.currentPage?this.fetch():this.currentPage=1,this.$refs.dataTable.clearSelection()},handleSelectionChange:function(t){this.multipleSelection=t},handleSizeChange:function(t){this.pageSize=t},sortChange:function(t,e){if(!t.column)return this.sortKey=this.defaultSortKey,this.sortOrder=this.defaultSortOrder,void this.fetch();this.sortKey=t.column.property,this.sortOrder="ascending"===t.order?"asc":"desc",this.fetch()},getRowKey:function(t){return t.id},deleteData:function(){}}})});</script>
