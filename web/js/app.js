"use strict";function _defineProperty(e,t,i){return t in e?Object.defineProperty(e,t,{value:i,enumerable:!0,configurable:!0,writable:!0}):e[t]=i,e}define("api",["axios","config","qs"],function(e,r,t){var i={};e.defaults.withCredentials=!0;var a=e.create({timeout:15e3,withCredentials:!0});return a.interceptors.response.use(function(e){var t=e.data?e.data.code:"";return"1002"===t&&vueApp.$router.replace("/login"),"1003"===t&&vueApp.$router.replace("/403"),"0000"!==t?Promise.reject(e.data.message):e.data},function(e){return Promise.reject(e)}),i.get=function(e,t){return a.get(r.host+e,{params:t})},i.post=function(e,t){var i=new URLSearchParams;for(var n in t){var o=t[n];i.append(n,o===Object(o)?JSON.stringify(o):o)}return a.post(r.host+e,i)},i.put=function(e,t){return a.post(r.host+e,t)},i.getExportUrl=function(e,t){return r.host+e+"?"+encodeURI(JSON.stringify(t))},i}),define("store",["vue","vuex"],function(e,t){e.use(t);var i="user";return{setUser:function(e){localStorage.setItem(i,JSON.stringify(e))},logout:function(){localStorage.removeItem("token"),localStorage.removeItem(i)},setCookie:function(e,t,i){var n=new Date;n.setDate(n.getDate()+i),document.cookie=e+"="+escape(t)+(null==i?"":";expires="+n.toGMTString())},getCookie:function(e){var t=void 0,i=void 0;return 0<document.cookie.length&&-1!==(t=document.cookie.indexOf(e+"="))?(t=t+e.length+1,-1===(i=document.cookie.indexOf(";",t))&&(i=document.cookie.length),unescape(document.cookie.substring(t,i))):""},clearCookie:function(){document.cookie="token=;expires=Thu, 01 Jan 1970 00:00:01 GMT;",document.cookie="username=;expires=Thu, 01 Jan 1970 00:00:01 GMT;"}}}),define("routerUtils",["api","store","reqVue!../views/app"],function(e,r,t){return{fetchMenu:function(n){var o=this;return e.post("sys/main.do").then(function(e){r.setUser(e.result.loginUser),r.setCookie("cdn",e.result.sysConfig.cdn);var t=e.result.menu.filter(function(e){return"0"===e.sm_parentid||!e.sm_parentid}).map(function(e){return{name:e.sm_name,path:e.sm_action,icon:e.sm_icon,children:e.child_menus.map(function(e){return{name:e.sm_name,path:e.sm_action.replace(".do","")}})}}),i=o.generateRoute(t);return n&&n(i,t),Promise.resolve(i)}).catch(function(e){return Promise.reject(e)})},generateRoute:function(e){var t=function(t){return function(e){require([t],e)}},i=[{name:"首页",path:"/",component:t("reqVue!../page/index")},{name:"首页",path:"/index",component:t("reqVue!../page/index")}],n=!0,o=!1,r=void 0;try{for(var a,s=e[Symbol.iterator]();!(n=(a=s.next()).done);n=!0){var u=a.value;if(!u.component&&"/"!==u.path&&0<u.children.length)u.component=t("reqVue!../views/firstLevel");else{var c=[],l=u.path.match(/\/(.*?)\/(.*?)$/);if(l){var d=l?l[2]:"menu_main",f=l[1];u.component=t("reqVue!../page/"+f+"/"+d),console.log(u),i.push(u),c.push({name:"添加",noShowMenu:!0,path:u.path+"/add/",component:t("reqVue!../page/"+f+"/"+d+"_modify"),meta:{parent:[u.name],first:!0}}),c.push({name:"修改",noShowMenu:!0,path:u.path+"/modify",component:t("reqVue!../page/"+f+"/"+d+"_modify"),meta:{parent:[u.name],first:!0}}),c.push({name:"查看",noShowMenu:!0,path:u.path+"/view",component:t("reqVue!../page/"+f+"/"+d+"_view"),meta:{parent:[u.name],first:!0}}),i=i.concat(c)}}if("/index"===u.path&&(u.component=t("reqVue!../page/index")),u.children){var p=[],h=!0,m=!1,v=void 0;try{for(var g,y=u.children[Symbol.iterator]();!(h=(g=y.next()).done);h=!0){var $=g.value,b=$.path.match(/\/(.*?)\/(.*?)$/),T=b?b[2]:"menu_main",x=b[1];$.component=t("reqVue!../page/"+x+"/"+T),$.meta={parent:[u.name]},p.push($),$.isNotTable||(p.push({name:"添加",noShowMenu:!0,path:$.path+"/add/",component:t("reqVue!../page/"+x+"/"+T+"_modify"),meta:{parent:[u.name,$.name]}}),p.push({name:"修改",noShowMenu:!0,path:$.path+"/modify",component:t("reqVue!../page/"+x+"/"+T+"_modify"),meta:{parent:[u.name,$.name]}}),p.push({name:"查看",noShowMenu:!0,path:$.path+"/view",component:t("reqVue!../page/"+x+"/"+T+"_view"),meta:{parent:[u.name,$.name]}}))}}catch(e){m=!0,v=e}finally{try{!h&&y.return&&y.return()}finally{if(m)throw v}}i=i.concat(p)}}}catch(e){o=!0,r=e}finally{try{!n&&s.return&&s.return()}finally{if(o)throw r}}return i.push({path:"/403",component:t("reqVue!../views/403")}),i.push({path:"*",component:t("reqVue!../views/404")}),i}}}),define("validateUtils",["config"],function(t){return{validateEmail:function(e){return/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/.test(e.toLowerCase())},validatePassword:function(e){return t.passwordRegex.test(e)},validateNumber:function(e){return!isNaN(parseFloat(e))&&isFinite(e)},validatePositiveNumber:function(e){return!isNaN(parseFloat(e))&&isFinite(e)&&0<=Number(e)},validatePositiveIntegerWithZero:function(e){if(e instanceof String){var t=Math.floor(Number(e));return String(t)===e&&0<=t}return e%1==0&&0<=e},validatePositiveInteger:function(e){if(e instanceof String){var t=Math.floor(Number(e));return String(t)===e&&0<t}return e%1==0&&0<e}}}),define("advancedFormMixins",["api"],function(u){return{data:function(){return{loading:!1,form:{}}},methods:{isValid:function(){var e=!0,t=!0,i=!1,n=void 0;try{for(var o,r=this.$children[Symbol.iterator]();!(t=(o=r.next()).done);t=!0){var a=o.value;"card"===a.$options._componentTag&&(a.validate()||(e=!1))}}catch(e){i=!0,n=e}finally{try{!t&&r.return&&r.return()}finally{if(i)throw n}}return e},fetch:function(){var f=this;this.id&&(this.loading=!0,u.post(this.$options.detailUrl,{id:this.id}).then(function(e){if(f.fetchSuccessBefore(e),f.form=e.result[f.$options.resultKey],console.log(f.form),f.$options.editTableKey&&(f.editTableDataList=e.result[f.$options.editTableKey]||[]),f.$options.extraEditTableKey){var t=!0,i=!1,n=void 0;try{for(var o,r=f.$options.extraEditTableKey[Symbol.iterator]();!(t=(o=r.next()).done);t=!0){var a=o.value;f.$set(f.extraEditTable,a,e.result[a]||[])}}catch(e){i=!0,n=e}finally{try{!t&&r.return&&r.return()}finally{if(i)throw n}}}f.$nextTick(function(){var e=!0,t=!1,i=void 0;try{for(var n,o=f.$children[Symbol.iterator]();!(e=(n=o.next()).done);e=!0){var r=n.value;"card"===r.$options._componentTag&&r.updateFormData()}}catch(e){t=!0,i=e}finally{try{!e&&o.return&&o.return()}finally{if(t)throw i}}if(f.$refs.editTable&&f.$refs.editTable.setDataList(f.editTableDataList),f.$options.extraEditTableKey){var a=!0,s=!1,u=void 0;try{for(var c,l=f.$options.extraEditTableKey[Symbol.iterator]();!(a=(c=l.next()).done);a=!0){var d=c.value;f.$refs[d+"EditTable"].setDataList(f.extraEditTable[d])}}catch(e){s=!0,u=e}finally{try{!a&&l.return&&l.return()}finally{if(s)throw u}}}}),f.fetchSuccessAfter(e),f.loading=!1}).catch(function(e){f.loading=!1}))},submitForm:function(){var t=this;if(console.log(this.form),this.isValid()){this.loading=!0;var e=_defineProperty({},this.$options.resultKey,this.form);if(this.$options.editTableKey&&this.$refs.editTable&&(e[this.$options.editTableKey]=this.editTableDataList),this.$options.extraEditTableKey){var i=!0,n=!1,o=void 0;try{for(var r,a=this.$options.extraEditTableKey[Symbol.iterator]();!(i=(r=a.next()).done);i=!0){var s=r.value;e[s]=this.extraEditTable[s]}}catch(e){n=!0,o=e}finally{try{!i&&a.return&&a.return()}finally{if(n)throw o}}}u.post(this.$options.url,e).then(function(e){t.loading=!1,t.successHandle()}).catch(function(e){t.loading=!1,t.$alert("提交失败",e)})}}}}}),define("baseFormMixins",["api"],function(u){return{idKey:"id",beforeRouteEnter:function(e,t,i){i()},data:function(){return{loading:!1,form:{},id:0,editTableDataList:[],extraEditTable:{}}},created:function(){if(this.$options.extraEditTableKey){var e=!0,t=!1,i=void 0;try{for(var n,o=this.$options.extraEditTableKey[Symbol.iterator]();!(e=(n=o.next()).done);e=!0){var r=n.value;this.$set(this.extraEditTable,r,[])}}catch(e){t=!0,i=e}finally{try{!e&&o.return&&o.return()}finally{if(t)throw i}}}},mounted:function(){this.id=localStorage.getItem("id"),this.fetch()},destroyed:function(){localStorage.removeItem("id")},methods:{fetch:function(){var s=this;this.id&&(this.loading=!0,u.post(this.$options.detailUrl,{id:this.id}).then(function(e){if(s.fetchSuccessBefore(e),s.form=e.result[s.$options.resultKey],s.$options.editTableKey&&(s.editTableDataList=e.result[s.$options.editTableKey]||[]),s.$options.extraEditTableKey){var t=!0,i=!1,n=void 0;try{for(var o,r=s.$options.extraEditTableKey[Symbol.iterator]();!(t=(o=r.next()).done);t=!0){var a=o.value;s.$set(s.extraEditTable,a,e.result[a]||[])}}catch(e){i=!0,n=e}finally{try{!t&&r.return&&r.return()}finally{if(i)throw n}}}s.$nextTick(function(){if(s.$refs.form.updateFormData(),s.$refs.editTable&&s.$refs.editTable.setDataList(s.editTableDataList),s.$options.extraEditTableKey){var e=!0,t=!1,i=void 0;try{for(var n,o=s.$options.extraEditTableKey[Symbol.iterator]();!(e=(n=o.next()).done);e=!0){var r=n.value;s.$refs[r+"EditTable"].setDataList(s.extraEditTable[r])}}catch(e){t=!0,i=e}finally{try{!e&&o.return&&o.return()}finally{if(t)throw i}}}}),s.fetchSuccessAfter(e),s.loading=!1}).catch(function(e){s.loading=!1}))},submitForm:function(){var t=this;if(this.$refs.form.validate()){this.loading=!0;var e=_defineProperty({},this.$options.resultKey,this.form);if(this.$options.editTableKey&&this.$refs.editTable&&(e[this.$options.editTableKey]=this.editTableDataList),this.$options.extraEditTableKey){var i=!0,n=!1,o=void 0;try{for(var r,a=this.$options.extraEditTableKey[Symbol.iterator]();!(i=(r=a.next()).done);i=!0){var s=r.value;e[s]=this.extraEditTable[s]}}catch(e){n=!0,o=e}finally{try{!i&&a.return&&a.return()}finally{if(n)throw o}}}this.preProcessPostData(e),u.post(this.$options.url,e).then(function(e){t.loading=!1,t.successHandle()}).catch(function(e){t.loading=!1,t.$alert("提交失败",e)})}},successHandle:function(){var e=this,t=this.$route.path.replace(/^(.*)\/.*$/,"$1"),i=this.$route.path.match(/^.*\/(.*)$/)[1];sessionStorage.setItem(t,i),this.$message({message:"add"===i?"添加成功":"修改成功",type:"success"}),setTimeout(function(){e.$router.back()},200)},fetchSuccessBefore:function(e){},fetchSuccessAfter:function(e){},preProcessPostData:function(e){}}}}),define("listMixins",["api","store"],function(o,e){return{openNewPage:!0,idKey:"id",data:function(){return{searchForm:{},selectList:[],hasSelectItem:!1,dialogFormVisible:!1,importDialogVisible:!1,importUploading:!1,fileList:[],form:{},formClone:{},saveLoading:!1,deleteLoading:!1,uploading:!1}},computed:{openNewPage:function(){return!!this.$options.openNewPage},saveParams:function(){return _defineProperty({},this.$options.resultKey,this.form)},cdn:function(){return e.getCookie("cdn")}},mounted:function(){this.formClone=Object.assign({},this.form)},activated:function(){var e=sessionStorage.getItem(this.$route.path);console.log(e),"add"===e?(1===this.$refs.dataTable.currentPage?this.$refs.dataTable.fetch():this.$refs.dataTable.currentPage=1,sessionStorage.removeItem(this.$route.path)):"modify"===e&&(this.$refs.dataTable.fetch(),sessionStorage.removeItem(this.$route.path))},deactivated:function(){console.log("deactivated")},methods:{selectListChange:function(e){this.selectList=e,this.hasSelectItem=0<e.length},search:function(e){this.$refs.dataTable.changeSearchParams(e)},deleteData:function(){var t=this,e=this.selectList.map(function(e){return e[t.$options.idKey]});this.$confirm("此操作将永久删除所选择的数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.deleteLoading=!0,o.post(t.$options.deleteBatchUrl,{ids:e}).then(function(e){t.$message({type:"success",message:"删除成功!"}),t.$refs.dataTable.update(),t.deleteLoading=!1}).catch(function(e){t.$alert(""+e,"删除失败"),t.deleteLoading=!1})})},exportUrl:function(){var e=this.$refs.dataTable.sortKey,t=this.$refs.dataTable.sortOrder;Object.assign(this.$refs.tableSearch.form).sortOrder="order by "+e+" "+t,window.location.href=o.getExportUrl(this.$options.exportUrl)},add:function(){var e=this;this.openNewPage?(localStorage.removeItem("id"),this.$router.push({path:this.$route.path+"/add"})):(this.dialogFormVisible=!0,this.form=Object.assign({},this.formClone),this.$nextTick(function(){e.$refs.form.updateFormData()}))},view:function(e){var t=e.id;localStorage.setItem("id",t),this.$router.push({path:this.$route.path+"/view/",params:{id:t}})},edit:function(e){var t=this,i=e[this.$options.idKey];this.openNewPage?(localStorage.setItem("id",i),this.$router.push({path:this.$route.path+"/modify",params:{id:i}})):(this.detailLoading=!0,this.dialogFormVisible=!0,this.$nextTick(function(){t.$refs.dialog.$el.scrollTo(0,0)}),o.post(this.$options.detailUrl,_defineProperty({},this.$options.idKey,i)).then(function(e){t.detailLoadSuccessCallback(e)}).catch(function(e){}))},save:function(){var t=this;this.$refs.form.validate()&&(this.saveLoading=!0,o.post(this.$options.saveUrl,this.saveParams).then(function(e){t.saveLoading=!1,t.dialogFormVisible=!1,t.$refs.dataTable.fetch()}).catch(function(e){t.saveLoading=!1,t.$alert(e,"保存失败")}))},deleteSingleData:function(e){var t=this,i=e[this.$options.idKey];this.$confirm("此操作将永久删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){o.post(t.$options.deleteUrl,_defineProperty({},t.$options.idKey,i)).then(function(e){t.$message({type:"success",message:"删除成功!"}),t.$refs.dataTable.update()}).catch(function(e){t.$alert(""+e,"删除失败")})})},reset:function(){this.$refs.dataTable.reset()},detailLoadSuccessCallback:function(e){var t=this;this.form=e.result[this.$options.resultKey],this.$nextTick(function(){t.$refs.form.updateFormData()})},importExcel:function(){this.fileList=[],this.importDialogVisible=!0},fileChange:function(e,t){this.fileList=t},uploadImportFile:function(e){var t=this,i=this.fileList[0];if(console.log(i),i){var n=new FormData;n.append("files",i.raw),this.importUploading=!0,o.put(this.$options.importUrl,n).then(function(e){t.importDialogVisible=!1,t.$message({type:"success",message:"导入成功!"}),t.reset(),t.importUploading=!1}).catch(function(e){t.$alert(""+e,"导入失败"),t.importUploading=!1})}else this.$message.error("请选择要上传的文件")}}}}),define("pageMixins",[],function(){return{}}),define("viewMixins",["api"],function(e){return{beforeRouteEnter:function(e,t,i){console.log(e),i()},data:function(){return{item:{},id:""}},watch:{$route:function(){this.fetch()}},mounted:function(){this.fetch(),this.id=localStorage.getItem("id")},methods:{fetch:function(){var a=this;this.$nextTick(function(){var e=!0,t=!1,i=void 0;try{for(var n,o=a.$refs.descList.$children[Symbol.iterator]();!(e=(n=o.next()).done);e=!0){var r=n.value;r.lazy&&r.fetch()}}catch(e){t=!0,i=e}finally{try{!e&&o.return&&o.return()}finally{if(t)throw i}}})}}}}),requirejs.config({urlArgs:"version=fx5r7v37",baseUrl:"./lib",paths:{promise:"es6-promise.min",vue:"vue",reqVue:"require-vuejs","vue-router":"vue-router.min",vuex:"vuex.min",ELEMENT:"element-ui",axios:"axios.min",lodash:"lodash.min",qs:"qs.min",md5:"md5.min",sortablejs:"sortable.min","element-china-area-data":"element-china-area-data",g2:"g2.min",config:"../js/config",api:"api",routerUtils:"../router/index"},shim:{promise:{exports:"Q"},vue:{exports:"Vue"},"vue-router":{exports:"VueRouter"},ELEMENT:{deps:["vue"],exports:"ELEMENT"},api:{deps:["config"]}},deps:["promise"]}),require(["vue","vue-router","store","ELEMENT","config","api","routerUtils"],function(n,o,r,e,t,a,i){n.use(e),n.use(o);var s=function(t){return function(e){require([t],e)}};function u(){var e=document.getElementsByClassName("appLoading")[0];return e.parentNode.removeChild(e)}i.fetchMenu(function(e,t){var i=new o({scrollBehavior:function(e,t,i){return i||{x:0,y:0}},routes:[{path:"/auto/build",component:s("reqVue!../views/build")},{path:"/login",component:s("reqVue!../views/login")},{path:"/",component:s("reqVue!../views/app"),children:e}]});i.beforeEach(function(e,t,i){i()}),window.vueApp=new n({router:i,el:"#app",api:a,store:r}),vueApp.menuList=t,u()}).catch(function(e){var t=new o({scrollBehavior:function(e,t,i){return i||{x:0,y:0}},routes:[{path:"/auto/build",component:s("reqVue!../views/build")},{path:"/login",component:s("reqVue!../views/login")}]});window.vueApp=new n({router:t,el:"#app",api:a,store:r}),vueApp.$router.replace("/login"),u()})}),require(["vue","reqVue!../component/DataTable","reqVue!../component/Form","reqVue!../component/FormItem","reqVue!../component/TableSearch","reqVue!../component/EditTable","reqVue!../component/Card","reqVue!../component/DescList","reqVue!../component/DescRow","reqVue!../component/DescCol","reqVue!../component/Upload","reqVue!../component/ValueEditList","reqVue!../component/AddressEditList"],function(e,t,i,n,o,r,a,s,u,c,l,d,f){e.component("dataTable",t),e.component("apeForm",i),e.component("formItem",n),e.component("tableSearch",o),e.component("editTable",r),e.component("card",a),e.component("descList",s),e.component("descRow",u),e.component("descCol",c),e.component("upload",l),e.component("valueEditList",d),e.component("addressEditList",f)});