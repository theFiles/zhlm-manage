<template>
  <div class="desc-list">
    <slot></slot>
  </div>
</template>

<script>"use strict";define(["vue"],function(t){return t.component("app",{template:template,data:function(){return{}},props:{item:Object},mounted:function(){var o=this;this.$nextTick(function(){var t=!0,e=!1,n=void 0;try{for(var r,i=o.$children[Symbol.iterator]();!(t=(r=i.next()).done);t=!0){r.value.item=o.item}}catch(t){e=!0,n=t}finally{try{!t&&i.return&&i.return()}finally{if(e)throw n}}})}})});</script>
