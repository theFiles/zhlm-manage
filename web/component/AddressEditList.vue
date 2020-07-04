<template>
  <div class="form-item" :class="{ 'is-required': required, error: errorInfo }">
    <label v-if="label">{{ label }}</label>
    <div v-else class="empty-label"></div>

    <div class="control-wrapper" style="width: 50%">
      <div class="value-edit-list">
        <div class="value-edit-item" v-for="(item, index) in list">
          <el-cascader
                  style="width: 200px"
                  size="small"
                  :options="addressCascaderOption"
                  v-model="item.address">
          </el-cascader>
          <a style="margin-left: 10px"><i class="el-icon-delete" @click="remove(index)"></i></a>
        </div>
        <el-button size="mini" @click="add">新增</el-button>

        <div class="el-form-item__error" v-if="errorInfo">
          {{ errorInfo }}
        </div>

      </div>
    </div>
  </div>
</template>

<script>"use strict";define(["vue","validateUtils","element-china-area-data"],function(t,e,n){return t.component("ValueEditList",{template:template,data:function(){return{dataType:"Array",newVal:[],test:[],list:[],errorInfo:""}},watch:{list:{handler:function(){this.$emit("input",this.list)},deep:!0},newVal:function(){this.list=this.newVal}},computed:{addressCascaderOption:function(){return n.regionDataPlus}},props:{label:String,name:String,required:Boolean},mounted:function(){},methods:{add:function(){this.list.push({address:[]})},remove:function(t){this.list.splice(t,1)}}})});</script>

<style>


</style>