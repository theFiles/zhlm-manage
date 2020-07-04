<template>
  <div class="ape-card form">
    <div class="ape-card-title">{{ title }}</div>
    <div class="ape-card-body">
      <slot></slot>
    </div>
  </div>
</template>

<script>"use strict";define(["vue"],function(t){return t.component("Card",{template:template,data:function(){return{errorCount:0,form:{}}},watch:{value:function(t){this.form=t},form:function(t){this.$emit("input",t)}},props:{value:Object,title:String},mounted:function(){this.form=this.value,this.updateFormData()},methods:{validate:function(){var e=0;return this.$children.forEach(function(t){t.validate&&!t.validate()&&e++}),0===(this.errorCount=e)},updateFormData:function(){var o=this;console.log(this.form),console.log(this.value);var t=!0,e=!1,n=void 0;try{for(var a,r=function(){var e=a.value;if(e.name){if("upload-image"===e.type&&e.$refs.upload.setImageList(o.value[e.name]||""),"editor"===e.type&&e.editor.txt.html(o.value[e.name]),e.newVal=o.value[e.name],e.errorInfo="",o.$set(o.form,e.name,void 0===e.newVal?"":e.newVal),"select"===e.type){var n=e.name+"_display";n in o.form||o.$set(o.form,n,""),e.$on("display",function(t){console.log(t),o.form[n]=t})}e.$on("input",function(t){o.form[e.name]=t}),e.updateForm&&e.updateForm(o.form),e.name2&&(o.$set(o.form,e.name2,e.newVal2),e.$on("input2",function(t){o.form[e.name2]=t}))}},i=this.$children[Symbol.iterator]();!(t=(a=i.next()).done);t=!0)r()}catch(t){e=!0,n=t}finally{try{!t&&i.return&&i.return()}finally{if(e)throw n}}}}})});</script>
