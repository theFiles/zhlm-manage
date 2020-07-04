<template>
  <div>
    <slot></slot>
  </div>
</template>

<script>"use strict";define(["vue"],function(t){return t.component("Card",{template:template,data:function(){return{}},watch:{},props:{data:Object},mounted:function(){var t=!0,e=!1,n=void 0;try{for(var o,r=this.$children[Symbol.iterator]();!(t=(o=r.next()).done);t=!0){var l=o.value;if("el-table-column"===l.$options._componentTag&&l.$children[0]&&"formItem"===l.$children[0].$options._componentTag){var a=l.$children[0];"select"===a.type&&console.log(a)}}}catch(t){e=!0,n=t}finally{try{!t&&r.return&&r.return()}finally{if(e)throw n}}console.log(this.$children)},methods:{}})});</script>
