<template>
    <div id="loginContainer">
        <div class="top">
            <img class="logo" src="images/logo.svg">
            <h1>{{ title }}</h1>
        </div>
        <div class="main">
            <div class="login">
                <el-alert
                        v-if="loginErrorInfo"
                        style="margin-bottom: 24px"
                        :title="loginErrorInfo"
                        type="error"
                        :closable="false"
                        show-icon>
                </el-alert>

                <ape-form ref="form">
                    <form-item class="login-form-item" label="用户名" placeholder="用户名" size="medium" prefix-icon="fas fa-user" no-label required v-model="form.user.su_code"></form-item>
                    <form-item class="login-form-item" label="密码" placeholder="密码" size="medium" prefix-icon="fas fa-unlock" no-label required input-type="password" v-model="form.user.su_password"></form-item>
                    <div class="verify-form-item">
                        <form-item placeholder="验证码" label="验证码" size="medium" no-label required v-model="form.randomCode" @keyup.enter.native="login" style="margin-bottom: 0"></form-item>
                        <img :src="verifyCodeSrc" title="点击刷新" @click="updateVerifyCodeSrc">
                    </div>
                </ape-form>
                <el-button type="primary" @click="login" :loading="loading" style="margin-top: 40px">登 录</el-button>
            </div>
        </div>
    </div>
</template>

<script>"use strict";define(["vue","config","store","api","md5","routerUtils","reqVue!./app"],function(e,o,r,t,n,i,s){return e.component("login",{verifyCodeUrl:"sys/loginRandom.do",template:template,data:function(){return{form:{user:{su_code:"admin",su_password:"admin888"},randomCode:""},loginErrorInfo:"",verifyCodeSrc:"",loading:!1}},mounted:function(){this.form.user.su_code="admin",this.form.user.su_password="admin888",this.updateVerifyCodeSrc()},computed:{title:function(){return o.title}},methods:{login:function(){var o=this;if(this.$refs.form.validate()){this.loading=!0,this.loginErrorInfo="";var e={user:{su_code:this.form.user.su_code,su_password:n(this.form.user.su_password)},randomCode:this.form.randomCode};t.post("sys/login.do",e).then(function(e){return o.fetchMenu()}).catch(function(e){e.code?o.loginErrorInfo=e.message||"未知错误":o.loginErrorInfo="登录失败",o.updateVerifyCodeSrc(),o.loading=!1})}},updateVerifyCodeSrc:function(){var e=o.host+this.$options.verifyCodeUrl+"?serial="+(new Date).getTime();this.verifyCodeSrc=e},fetchMenu:function(){var r=this;return i.fetchMenu(function(e,o){vueApp.menuList=o,r.$router.addRoutes([{path:"/",component:s,children:e}]),r.$router.replace({name:"首页"})})}}})});</script>