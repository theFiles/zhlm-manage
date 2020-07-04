<template>
  <div>
    <ul class="upload">
      <li v-for="(upload, index) in uploadTempList">


        <div class="upload__image">
          <div class="upload__action">
            <a @click="previewImage(upload)" v-if="upload.percent === 100 || upload.src.startsWith('http')"><i class="far fa-eye"></i></a>
            <a @click="remove(index)" v-if="upload.percent === 100 || upload.src.startsWith('http')"><i class="far fa-trash-alt"></i></a>
          </div>
          <img class="clickView" :src="upload.src">
        </div>
        <p class="status" v-if="upload.percent <= 100">{{ upload.percent }}%</p>

      </li>
      <li class="upload__input-box" v-if="uploadTempList.length < limit || !limit">
        <input ref="upload" @change="fileChange" type="file" accept="image/gif,image/jpeg,image/jpg,image/png" :disabled="disabled" />
        <p class="el-icon-plus"></p>
      </li>
    </ul>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>

<script>"use strict";define(["vue","config","store"],function(e,a,t){return e.component("Upload",{template:template,data:function(){return{uploadTempList:[],imageList:[],dialogVisible:!1,dialogImageUrl:"",nameList:[]}},props:{disabled:Boolean,value:[String,Array],limit:Number},computed:{cdn:function(){return t.getCookie("cdn")}},mounted:function(){},methods:{fileChange:function(e){var i=this,t=new FileReader,n=e.target.files[0];n&&(t.onload=function(e){var t={src:e.target.result,file:n,percent:0,name:""};i.uploadTempList.push(t),i.uploadFile(t)},t.readAsDataURL(n),this.resetInput(this.$refs.upload))},uploadFile:function(i){var t=this,e=new FormData;e.append("file_path",i.file,i.file.name),this.postUploadFile(e,function(e){e&&e.result?(i.percent=101,i.src=e.result.absolutePath,i.name=e.result.name,t.$emit("input",t.getImageList())):t.remove(t.uploadTempList.indexOf(i))},function(e){var t=~~(e.loaded/e.total*100);i.percent=t},function(e){t.remove(t.uploadTempList.indexOf(i))})},postUploadFile:function(e,i,t){var n=new XMLHttpRequest;n.upload.onprogress=t,n.open("POST",a.host+"file/upload2.do",!0),n.onreadystatechange=function(e){if(4===n.readyState&&200===n.status){var t=n.responseText;"outofsize"==t?this.$alert("文件过大，无法上传！","系统提示"):"errorDimension"==t?this.$alert("图片尺寸必须是128*128","系统提示"):"error"==t?this.$alert("图片上传失败，请重新上传！","系统提示"):i(JSON.parse(t))}},n.send(e)},remove:function(e){this.uploadTempList.splice(e,1),this.$emit("input",this.getImageList())},resetInput:function(e){e.type="text",e.type="file"},setImageList:function(e){var t=this;if(e){var i=e.split(",");this.uploadTempList=i.map(function(e){return{src:t.cdn+e,percent:101,name:e}})}},getImageList:function(){return this.uploadTempList.map(function(e){return e.name}).join(",")},previewImage:function(e){this.dialogImageUrl=e.src,this.dialogVisible=!0}}})});</script>
