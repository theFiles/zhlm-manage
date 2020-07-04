<template>
  <form class="ape-form">
    <slot></slot>
  </form>
</template>

<script>"use strict";define(["vue"],function(t){return t.component("app",{template:template,data:function(){return{form:{}}},props:{value:Object},watch:{value:function(t){this.form=t},form:function(t){this.$emit("input",t)}},mounted:function(){this.updateFormData()},methods:{validate:function(){var e=!0;return this.$children.forEach(function(t){t.validate&&!t.validate()&&(e=!1)}),e},updateFormData:function(){var a=this,t=!0,e=!1,n=void 0;try{for(var r,o=function(){var e=r.value;e.name&&("upload-image"===e.type&&e.$refs.upload.setImageList(a.value[e.name]||""),"editor"===e.type&&e.editor.txt.html(a.value[e.name]),e.newVal=a.value[e.name],"checkboxGroup"!==e.type&&"Array"!==e.dataType||(e.newVal=a.value[e.name]||[]),e.errorInfo="",a.$set(a.form,e.name,e.newVal),e.$on("input",function(t){a.form[e.name]=t}),e.updateForm&&e.updateForm(a.form),e.name2&&(a.$set(a.form,e.name2,e.newVal2),e.$on("input2",function(t){a.form[e.name2]=t})))},i=this.$children[Symbol.iterator]();!(t=(r=i.next()).done);t=!0)o()}catch(t){e=!0,n=t}finally{try{!t&&i.return&&i.return()}finally{if(e)throw n}}}}})});</script>
