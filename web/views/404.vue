<template>
    <div class="exception">
        <div class="image-block">
            <div class="image" style="background-image: url(https://gw.alipayobjects.com/zos/rmsportal/KpnpchXsobRgLElEozzI.svg)"></div>
        </div>
        <div class="content">
            <h1>404</h1>
            <div class="desc">抱歉，你访问的页面不存在</div>
            <div class="action">
                <el-button size="small" type="primary" @click="$router.replace({ name: '首页' })">返回首页</el-button>
            </div>
        </div>
    </div>
</template>
<script>"use strict";define(["vue"],function(e){return e.component("page404",{template:template})});</script>