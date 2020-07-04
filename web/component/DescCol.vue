<template>
  <el-col :span="span">
    <div class="term">{{ label }}</div>
    <div class="detail">
      <slot v-if="!!this.$slots.default"></slot>
      <template v-else>
        {{ item[name] }}
      </template>
    </div>
  </el-col>
</template>

<script>"use strict";define(["vue"],function(t){return t.component("app",{template:template,data:function(){return{item:{}}},props:{label:String,name:String,span:{type:Number,default:8}},watch:{},computed:{},mounted:function(){},methods:{}})});</script>
