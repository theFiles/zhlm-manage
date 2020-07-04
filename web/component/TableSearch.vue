<template>
  <div class="dataTable-search-container">

    <!--<slot></slot>-->

    <!--<template v-if="open">-->
    <!--<slot name="more"></slot>-->
    <!--</template>-->

    <!--<div class="form-item control" :class="{ open: open }">-->
    <!--<div class="content">-->
    <!--<el-button type="primary" size="small" @click="$emit('search', form)">查询</el-button>-->
    <!--<el-button size="small" @click="reset">重置</el-button>-->
    <!--<el-button type="text" @click="open = !open">-->
    <!--{{ open ? '收起' : '展开' }}<i :class="open ? 'el-icon-arrow-up' : 'el-icon-arrow-down'"></i>-->
    <!--</el-button>-->
    <!--</div>-->
    <!--</div>-->

  </div>
</template>

<script>"use strict";define(["vue","config","api"],function(t,e,n){return t.component("app",{render:function(t){var e=this,n=this.$slots.default?this.$slots.default.length:0,a=t("el-button",{attrs:{type:"text"},on:{click:function(t){e.open=!e.open}}},[this.open?"收起":"展开",t("i",{class:this.open?"el-icon-arrow-up":"el-icon-arrow-down"})]),o=t("div",{staticClass:"form-item control",class:{open:this.open}},[t("div",{staticClass:"content"},[t("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(t){e.$emit("search",e.form)}}},["查询"]),t("el-button",{attrs:{size:"small"},on:{click:this.reset}},["重置"]),3<n?a:[]],1)]);return 0===n?t("div"):this.open?t("div",{staticClass:"dataTable-search-container"},[this.$slots.default,o]):t("div",{staticClass:"dataTable-search-container"},[this.$slots.default.slice(0,3),o])},template:template,data:function(){return{open:!0,form:{}}},props:{label:String,value:Object},mounted:function(){this.form=this.value,this.updateFormData()},watch:{open:function(){var t=this;this.$nextTick(function(){t.updateFormData()})},value:function(t){this.form=t},form:function(t){this.$emit("input",t)}},methods:{updateFormData:function(){var n=this,t=!0,e=!1,a=void 0;try{for(var o,i=function(){var e=o.value;e.name&&("upload-image"===e.type&&e.$refs.upload.setImageList(n.value[e.name]||""),"editor"===e.type&&e.editor.txt.html(n.value[e.name]),e.newVal=n.value[e.name],"checkboxGroup"!==e.type&&"Array"!==e.dataType||(e.newVal=n.value[e.name]||[]),e.errorInfo="",n.$set(n.form,e.name,e.newVal||""),e.$on("input",function(t){n.form[e.name]=t}),e.updateForm&&e.updateForm(n.form),e.name2&&(n.$set(n.form,e.name2,e.newVal2),e.$on("input2",function(t){n.form[e.name2]=t})))},r=this.$children[Symbol.iterator]();!(t=(o=r.next()).done);t=!0)i()}catch(t){e=!0,a=t}finally{try{!t&&r.return&&r.return()}finally{if(e)throw a}}},refresh:function(){},reset:function(){for(var t in this.form){var e=!(this.form[t]=""),n=!1,a=void 0;try{for(var o,i=this.$children[Symbol.iterator]();!(e=(o=i.next()).done);e=!0){var r=o.value;r.name&&(r.newVal=""),r.name2&&(r.newVal2="")}}catch(t){n=!0,a=t}finally{try{!e&&i.return&&i.return()}finally{if(n)throw a}}}this.$emit("reset")}}})});</script>
