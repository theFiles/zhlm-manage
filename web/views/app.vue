<template>
    <div class="app-layout" :class="{ collapse: isCollapse }">
        <div class="sidebar-container el-scrollbar" :class="{ collapse: isCollapse }">
            <el-scrollbar>
                <div class="logo">
                    <router-link to="/index">
                        <img src="./images/logo.svg" alt="logo">
                        <h1>后台管理系统</h1>
                    </router-link>
                </div>

                <el-menu
                        class="el-menu-vertical"
                        style="border-right: none"
                        mode="vertical"
                        :show-timeout="200"
                        background-color="#304156"
                        text-color="#bfcbd9"
                        :unique-opened="true"
                        :router="true"
                        :default-active="$route.name"
                        :collapse="isCollapse"
                        active-text-color="#409EFF">

                    <el-menu-item index="首页" :route="{ name: '首页', path: '/' }">
                        <i class="fas fa-home" style="margin-right: 5px"></i>
                        <span slot="title">首页</span>
                    </el-menu-item>

                    <template v-for="item in menuList">
                        <el-menu-item :index="item.name" v-if="!item.children || item.children.length === 0" :route="item">
                            <i class="fas" :class="item.icon"></i>
                            <span slot="title">{{ item.name }}</span>
                        </el-menu-item>
                        <el-submenu :index="item.name" v-else>
                            <template slot="title">
                                <i :class="item.icon"></i>
                                <span slot="title">{{ item.name }}</span>
                            </template>
                            <!--@click.native="$router.push({ name: subItem.name })"-->
                            <el-menu-item :index="subItem.name"
                                          :route="subItem"
                                          v-if="!subItem.noShowMenu"
                                          v-for="(subItem, subIndex) in (item.children || [])">
                                <i :class="subItem.icon"></i>
                                <span slot="title">{{ subItem.name }}</span>
                            </el-menu-item>
                        </el-submenu>

                    </template>

                </el-menu>

            </el-scrollbar>

        </div>

        <div class="main-container">
            <ul class="navbar">
                <div class="menu-action" @click="isCollapse = !isCollapse"><i :class="isCollapse ? 'icon-menu-fold' : 'icon-menu-unfold'"></i></div>
                <div class="right">
                    <el-dropdown>
                    <span class="user-dropdown">
                        <span class="avatar"><img
                                src="https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png"></span>
                        <span class="name">{{ user.su_name }}</span>
                    </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item @click.native="openChangePswDialog">修改密码</el-dropdown-item>
                            <!--<el-dropdown-item @click.native="">个人信息</el-dropdown-item>-->
                            <el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </ul>


            <div class="app-breadcrumb" v-if="breadcrumb[1] && breadcrumb[1].name">
                <span v-for="bread in breadcrumb" class="breadcrumb">
                    <span tag="span" class="breadcrumb-link" :to="bread.path">
                        <router-link v-if="bread.path" :to="bread.path">{{ bread.name }}</router-link>
                        <template v-else>{{ bread.name }}</template>
                    </span>
                    <span class="breadcrumb-separator">/</span>
                </span>
            </div>

            <div id="page-container">
                <!--<transition name="component-fade" mode="out-in">-->
                <keep-alive exclude="modify,sys_page">
                    <router-view></router-view>
                </keep-alive>
                <!--</transition>-->
            </div>

        </div>

        <!-- 修改密码 -->
        <el-dialog
                title="修改密码"
                :visible.sync="dialogVisible"
                width="40%">
            <ape-form ref="form" class="dialog-form">
                <form-item label="原始密码" v-model="form.oldPsw" input-type="password" valid="password" required></form-item>
                <form-item label="新密码" v-model="form.psw" input-type="password" valid="password" required></form-item>
                <form-item label="确认密码" v-model="form.confirmPsw" input-type="password" :valid="validConfirmPsw" required></form-item>
            </ape-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="small" @click="dialogVisible = false">取 消</el-button>
                <el-button size="small" type="primary" @click="changePassword" :loading="loading">修 改</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>"use strict";define(["vue","config","store","api","routerUtils"],function(e,g,n,o,t){return e.component("app",{template:template,beforeRouteEnter:function(e,t,n){n()},data:function(){return{isCollapse:!1,dialogVisible:!1,form:{oldPsw:"",psw:"",confirmPsw:""},loading:!1,loginUser:{},menuList:[],user:{}}},mounted:function(){this.menuList=vueApp.menuList,this.user=JSON.parse(localStorage.getItem("user"))},computed:{breadcrumb:function(){if("/index"===this.$route.path||"/"===this.$route.path)return[{name:"首页"}];for(var e=[{name:"首页",path:"/index"}],t=this.$route.meta&&this.$route.meta.parent||[],n=0;n<t.length;n++)2<=t.length&&0<n||this.$route.meta.first?e.push({name:t[n],path:{name:t[n]}}):e.push({name:t[n]});return e.push({name:this.$route.name}),e}},methods:{logout:function(){o.post("sys/loginExit.do").then(function(e){}).catch(function(e){return console.log(e)}),n.logout(),this.$router.replace("/login")},openChangePswDialog:function(){for(var e in this.dialogVisible=!0,this.form)this.form[e]=""},changePassword:function(){var e=this;this.$refs.form.validate()&&(this.loading=!0,setTimeout(function(){e.$message({message:"密码修改成功",type:"success"}),e.loading=!1,e.dialogVisible=!1},1e3))},validConfirmPsw:function(e){return{valid:this.form.psw===e,info:"密码不一致"}},fetchMenu:function(){var t=this;o.post("sys/main.do").then(function(e){n.setUser(e.result.loginUser),t.menuList=e.result.menu.filter(function(e){return"0"===e.sm_parentid||!e.sm_parentid}).map(function(e){return{name:e.sm_name,path:e.sm_name,icon:e.sm_icon,children:e.child_menus.map(function(e){return{name:e.sm_name,path:e.sm_name}})}}),t.generateRoute()}).catch(function(e){})},generateRoute:function(){var e=function(t){return function(e){require([t],e)}},t=[],n=!0,o=!1,r=void 0;try{for(var a,i=g.menu[Symbol.iterator]();!(n=(a=i.next()).done);n=!0){var u=a.value;if(u.component||"/"===u.path||(u.component=e("reqVue!./firstLevel")),"/index"===u.path&&(u.component=e("reqVue!../page/index")),u.children){var s=[],p=!0,m=!1,h=void 0;try{for(var c,l=u.children[Symbol.iterator]();!(p=(c=l.next()).done);p=!0){var f=c.value,d=f.path.replace("/","").replace("/","-");f.component=e("reqVue!../page/"+d),s.push(f),f.isNotTable||(s.push({name:"添加",noShowMenu:!0,path:f.path+"/add/",component:e("reqVue!../page/"+d+"-modify"),meta:{parent:f.name}}),s.push({name:"修改",noShowMenu:!0,path:f.path+"/modify",component:e("reqVue!../page/"+d+"-modify"),meta:{parent:f.name}}),s.push({name:"查看",noShowMenu:!0,path:f.path+"/view",component:e("reqVue!../page/"+d+"-view"),meta:{parent:f.name}}))}}catch(e){m=!0,h=e}finally{try{!p&&l.return&&l.return()}finally{if(m)throw h}}u.children=s}t.push(u)}}catch(e){o=!0,r=e}finally{try{!n&&i.return&&i.return()}finally{if(o)throw r}}t.push({path:"/",component:e("reqVue!../page/index")}),t.push({path:"/403",component:e("reqVue!./403")}),t.push({path:"/*",component:e("reqVue!./404")}),this.$router.addRoutes([{path:"/",component:App,children:t}]),this.$router.replace({name:"首页"})}}})});</script>

<style>
    .text {
        font-size: 46px;
    }
</style>