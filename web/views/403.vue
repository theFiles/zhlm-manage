<template>
    <div class="exception">
        <div class="image-block">
            <div class="image" style="background-image: url(https://gw.alipayobjects.com/zos/rmsportal/wZcnGqRDyhPOEYFcZDnb.svg)"></div>
        </div>
        <div class="content">
            <h1>403</h1>
            <div class="desc">抱歉，你无权访问该页面</div>
            <div class="action">
                <el-button size="small" type="primary" @click="$router.replace({ name: '首页' })">返回首页</el-button>
            </div>
        </div>
    </div>
</template>
<script>"use strict";define(["vue"],function(e){return e.component("page403",{template:template})});</script>