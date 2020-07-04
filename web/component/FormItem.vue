<template>
  <div class="form-item" :class="{ 'is-required': required, error: errorInfo }">

    <template v-if="!noLabel">
      <label v-if="label">{{ label }}</label>
      <div v-else class="empty-label"></div>
    </template>

    <div class="control-wrapper" v-if="editable && !disableEdit" :class="{ wide: type === 'editor' }">

      <!-- 下拉框 -->
      <el-select v-if="type === 'select'"
                 size="small"
                 clearable
                 :multiple="multiple"
                 :filterable="filterable"
                 placeholder="请选择"
                 v-model="newVal"
                 :disabled="disabled"
                 @visible-change="selectVisibleChange"
                 :loading="selectDataLoading">
        <el-option
                v-for="item in selectList"
                :key="item[selectValue]"
                :label="item[selectText]"
                :value="item[selectValue]">
        </el-option>
      </el-select>

      <!-- 联动选择器 -->
      <el-cascader v-else-if="type === 'cascader'" :options="list" size="small" :show-all-levels="false"
                   :disabled="disabled"
                   :clearable="true" change-on-select @change="cascaderChange" v-model="cascaderData"></el-cascader>

      <!-- 范围值 -->
      <template v-else-if="type === 'num-range'">
        <div class="num-range">
          <el-input size="small" placeholder="请输入" v-model.number="newVal" :disabled="disabled"></el-input>
          <span class="separator">至</span>
          <el-input size="small" placeholder="请输入" v-model.number="newVal2" :disabled="disabled"></el-input>
        </div>
      </template>

      <!-- 日期选择 -->
      <el-date-picker v-else-if="type === 'date'" size="small" v-model="newVal" type="date" placeholder="选择日期" clearable
                      :disabled="disabled"
                      :value-format="dateValueFormat"></el-date-picker>

      <!-- 日期范围 -->
      <el-date-picker
              v-else-if="type === 'date-range'"
              size="small"
              :disabled="disabled"
              :picker-options="dateRangePickerOptions"
              v-model="dateRange"
              @change="dateRangeChange"
              type="daterange"
              range-separator="至"
              :value-format="dateValueFormat"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
      </el-date-picker>

      <!-- 日期范围 - 分开 -->
      <div v-else-if="type === 'date-range-split'" class="date-range-split">
        <el-date-picker size="small" v-model="newVal" type="date" placeholder="开始日期" clearable
                        :value-format="dateValueFormat" :disabled="disabled"></el-date-picker>
        <span class="separator">至</span>
        <el-date-picker size="small" v-model="newVal2" type="date" placeholder="结束日期" clearable
                        :value-format="dateValueFormat" :disabled="disabled"></el-date-picker>
      </div>

      <!-- textarea -->
      <el-input v-else-if="type === 'textarea'" type="textarea" :autosize="{ minRows: 2, maxRows: 4}"
                placeholder="请输入内容" v-model="newVal" :disabled="disabled"></el-input>

      <!-- price -->
      <el-input v-else-if="type === 'price'" size="small" placeholder="请输入" v-model="newVal"
                @blur="inputBlur" :disabled="disabled"></el-input>

      <!-- upload -->
      <template v-else-if="type === 'upload-image'">
        <upload ref="upload" v-model="newVal" :limit="limit" :disabled="disabled"></upload>
      </template>

      <!-- 富文本 -->
      <div v-else-if="type === 'editor'" ref="editor"></div>

      <!-- 带建议的输入框 -->
      <el-autocomplete
              v-else-if="type === 'autocomplete'"
              class="inline-input"
              v-model="newVal"
              placeholder="请输入内容"
              :disabled="disabled"
      ></el-autocomplete>

      <!-- CheckBox -->
      <el-checkbox v-else-if="type === 'checkbox'" v-model="newVal" :disabled="disabled">{{ checkLabel }}</el-checkbox>

      <!-- CheckBox Group -->
      <el-checkbox-group v-else-if="type === 'checkboxGroup'" v-model="newVal">
        <el-checkbox v-for="option in list" :label="option[selectValue]" :disabled="disabled">{{ option[selectText] }}</el-checkbox>
      </el-checkbox-group>

      <!-- 单选 -->
      <el-radio-group v-else-if="type === 'radio'" v-model="newVal">
        <el-radio v-for="option in list" :label="option[selectValue] || option[selectText]" :disabled="disabled">{{ option[selectText] }}
        </el-radio>
      </el-radio-group>

      <el-cascader
              v-else-if="type === 'address'"
              style="width: 100%"
              size="small"
              :clearable="true"
              :options="addressCascaderOption"
              v-model="newVal"
              :disabled="disabled">
      </el-cascader>

      <!-- 自定义 -->
      <slot name="custom" v-else-if="type === 'custom'"></slot>

      <el-input v-else-if="type === 'number'" :size="size" :placeholder="computedPlaceholder" v-model="newVal" :prefix-icon="prefixIcon"
                :disabled="disabled"
                :suffix-icon="suffixIcon">
      </el-input>

      <!-- 输入框 -->
      <el-input v-else :size="size" :placeholder="computedPlaceholder" v-model="newVal"
                :prefix-icon="prefixIcon"
                :suffix-icon="suffixIcon"
                :disabled="disabled"
                :type="inputType">
      </el-input>

      <div class="el-form-item__error" v-if="errorInfo">
        {{ errorInfo }}
      </div>


      <slot></slot>

    </div>

    <!-- 不可编辑的状态 -->
    <div v-else>
      <slot name="static" v-if="$slots.static"></slot>
      <template v-else>{{ unEditableDisplayValue }}</template>
    </div>

  </div>
</template>

<script>"use strict";function _defineProperty(e,t,i){return t in e?Object.defineProperty(e,t,{value:i,enumerable:!0,configurable:!0,writable:!0}):e[t]=i,e}define(["vue","config","api","validateUtils","lodash","element-china-area-data"],function(e,t,n,o,i,a){return e.component("formItem",{template:template,data:function(){return{newVal:"",newVal2:"",dateRange:[],selectHasLoad:!1,selectDataLoading:!1,fetchSelectList:[],editor:null,dialogImageUrl:"",dialogVisible:!1,uploadFileList:[],tempFileList:[],errorInfo:"",editable:!0,dateRangePickerOptions:{shortcuts:[{text:"最近一周",onClick:function(e){var t=new Date,i=new Date;i.setTime(i.getTime()-6048e5),e.$emit("pick",[i,t])}},{text:"最近一个月",onClick:function(e){var t=new Date,i=new Date;i.setTime(i.getTime()-2592e6),e.$emit("pick",[i,t])}},{text:"最近三个月",onClick:function(e){var t=new Date,i=new Date;i.setTime(i.getTime()-7776e6),e.$emit("pick",[i,t])}}]},cascaderData:[],form:{}}},props:{disabled:Boolean,label:String,value:[String,Number,Date],type:{type:String,default:"input"},inputType:{type:String,default:"text"},required:Boolean,noLabel:Boolean,placeholder:String,prefixIcon:String,suffixIcon:String,size:{type:String,default:"small"},precision:{type:Number,default:-1},range:{type:String,default:""},list:Array,listType:String,url:String,name:String,name2:String,selectText:{type:String,default:"text"},selectValue:{type:String,default:"value"},lastSelectName:String,lastSelectKey:String,multiple:Boolean,filterable:Boolean,selectChild:String,tips:String,uploadUrl:{type:String,default:"xxxxxx"},limit:{type:Number,default:1},limitSize:{type:Number,default:2},valid:[String,Function],data:Object,dateValueFormat:{type:String,default:"yyyy-MM-dd"},checkLabel:String,lazy:Boolean,params:Object,disableEdit:Boolean,defaultValue:[String,Number,Object]},watch:{value:function(e){this.newVal=e},newVal:function(){var e=Number(this.newVal);"number"!==this.type||isNaN(e)?this.$emit("input",this.newVal):this.$emit("input",e),"select"===this.type&&this.change(),this.errorInfo&&this.validate()},newVal2:function(){this.$emit("input2",this.newVal2)},uploadFileList:function(){},params:{handler:function(e,t){i.isEqual(e,t)||this.selectHasLoad&&(this.selectHasLoad=!1,this.fetchSelectList=[])},deep:!0}},computed:{selectList:function(){return"yesOrNo"===this.listType?[{text:"是",value:"1"},{text:"否",value:"0"}]:this.url?this.fetchSelectList:this.list},computedUploadUrl:function(){return t.host+"/file/upload2.do"},computedPlaceholder:function(){return this.placeholder||"请输入"+(this.label||"")},unEditableDisplayValue:function(){var t=this;if("select"===this.type){var e=this.selectList.filter(function(e){return e[t.selectValue]===t.newVal})[0];return e?e[this.selectText]:""}return this.newVal},addressCascaderOption:function(){return a.regionData}},mounted:function(){var i=this;if(this.$nextTick(function(){if(i.data){var e,t=i.data[i.name]?i.data[i.name]:"select"===i.type&&i.multiple?[]:"";if(i.$set(i.data,i.name,t),i.newVal=t,i.data._child.push(i),i.$on("input",function(e){i.data[i.name]=e}),i.$watch(function(){return i.data[i.name]},function(e){i.newVal=e}),i.editable=i.data._editing,i.defaultValue&&"select"===i.type&&!i.data._fromRemote)i.newVal=i.defaultValue.value,i.fetchSelectList=[(e={},_defineProperty(e,i.selectText,i.defaultValue.text),_defineProperty(e,i.selectValue,i.defaultValue.value),e)];i.data._fromRemote&&i.updateForm(i.data)}i.lastSelectName&&i.$watch(function(){return i.$parent.form[i.lastSelectName]},function(e){i.selectHasLoad=!1})}),"editor"===this.type){var e=new window.wangEditor(this.$refs.editor);e.customConfig.menus=["head","bold","fontSize","italic","underline","foreColor","backColor","link","list","justify","quote","image"],e.customConfig.uploadImgServer=this.computedUploadUrl,e.customConfig.zIndex=1e3,e.customConfig.uploadImgMaxSize=1024*this.limitSize*1024,e.customConfig.uploadFileName="file_path",e.customConfig.uploadImgHooks={customInsert:function(e,t,i){e(t.result.absolutePath)}},e.customConfig.customAlert=function(e){i.$alert(e,"上传图片失败")},e.customConfig.onchange=function(e){i.newVal=e},e.create(),this.editor=e}},methods:{updateForm:function(e){var t;"select"!==this.type||this.selectHasLoad||(this.fetchSelectList=[(t={},_defineProperty(t,this.selectText,e[this.name+"_display"]),_defineProperty(t,this.selectValue,e[this.name]),t)])},change:function(){var t=this;if(!this.url||this.selectHasLoad){var e=(this.selectChild||"").split(",").filter(function(e){return!!e}).map(function(e){return e.trim()}),i=this.data?this.data._child:this.$parent.$children,a=!0,n=!1,l=void 0;try{for(var s,r=i[Symbol.iterator]();!(a=(s=r.next()).done);a=!0){var o=s.value;-1!==e.indexOf(o.name)&&(o.newVal="")}}catch(e){n=!0,l=e}finally{try{!a&&r.return&&r.return()}finally{if(n)throw l}}var u=this.selectList.find(function(e){return e[t.selectValue]===t.newVal});this.$emit("change",this.newVal,u),this.$emit("display",u?u[this.selectText]:"")}},update:function(){var t=this,e=0<arguments.length&&void 0!==arguments[0]?arguments[0]:{};this.selectDataLoading=!0,n.get(this.url,Object.assign(this.params,e)).then(function(e){t.fetchSelectList=e.result,t.selectHasLoad=!0,t.change(),t.$nextTick(function(){t.selectDataLoading=!1})}).catch(function(e){console.log(e),t.selectDataLoading=!1})},inputBlur:function(){"price"===this.type&&o.validatePositiveNumber(this.newVal)&&(this.newVal=Number(this.newVal).toFixed(2))},dateRangeChange:function(){this.dateRange?(this.$emit("input",this.dateRange[0]),this.$emit("input2",this.dateRange[1])):(this.$emit("input",null),this.$emit("input2",null))},selectVisibleChange:function(e){var t=this;if(this.url&&!this.selectHasLoad&&!this.selectDataLoading){this.selectDataLoading=!0;var i={};for(var a in this.params)i[a]=void 0===this.params[a]?"":this.params[a];n.get(this.url,i).then(function(e){t.fetchSelectList=e.result,t.selectDataLoading=!1,t.selectHasLoad=!0}).catch(function(e){console.log(e),t.selectDataLoading=!1})}},handleRemove:function(e,t){this.tempFileList.splice(this.tempFileList.indexOf(e),1)},handlePictureCardPreview:function(e){this.dialogImageUrl=e.url,this.dialogVisible=!0},handleUploadError:function(e,t,i){this.tempFileList.splice(this.tempFileList.indexOf(t),1),this.$alert("错误原因："+e,"上传失败")},beforeUpload:function(e){var t="image"===e.type.split("/")[0],i=1<=this.limitSize?e.size/1024/1024<this.limitSize:e.size/1024<1e3*this.limitSize;if(t||this.$message.error("上传文件的格式不正确!"),!i){1<=this.limitSize?this.limitSize:this.limitSize;this.$message.error("上传图片大小不能超过 "+this.limitSize+" MB!")}return t&&this.tempFileList.push(e),t&&i},handleUploadSuccess:function(e,t,i){console.log(e)},validate:function(){var e=!0,t="";if(this.required){if("select"===this.type&&this.multiple&&0===this.newVal.length)return this.errorInfo="请选择"+(this.label||""),!1;if(null===this.newVal||void 0===this.newVal||""===this.newVal){var i="select"===this.type||"date"===this.type||"date-range"===this.type?"选择":"输入";return this.errorInfo="请"+i+(this.label||""),!1}}switch(this.valid){case"email":e=o.validateEmail(this.newVal);break;case"password":e=o.validatePassword(this.newVal);break;case"number":e=o.validateNumber(this.newVal);break;case"positive-number":e=o.validatePositiveNumber(this.newVal);break;case"positive-integer":e=o.validatePositiveInteger(this.newVal);break;case"positive-integer-0":e=o.validatePositiveIntegerWithZero(this.newVal)}if("price"===this.type&&(e=!this.newVal||o.validatePositiveNumber(this.newVal)),"number"===this.type)if(e=o.validateNumber(this.newVal),o.validateNumber(this.newVal)){if(0<=this.precision){var a=(this.newVal+"").split(".");(a[1]?a[1].length:0)>this.precision&&(e=!1,t=0===this.precision?"请输入整数":"数字精度不能大于"+this.precision)}if(this.range){var n=this.range.split(","),l=Number(n[0])||Number.MIN_VALUE,s=Number(n[1])||Number.MAX_VALUE;e=Number(this.newVal)>=l&&Number(this.newVal)<=s}}else e=!1;if(this.errorInfo=t||(e?"":"格式错误"),"function"==typeof this.valid){var r=this.valid(this.newVal);this.errorInfo=r.valid?"":r.info,e=r.valid}return e},cascaderChange:function(e){this.newVal=e[e.length-1],console.log(e)},setCascaderValue:function(e){this.cascaderData=e,console.log(e)}}})});</script>
