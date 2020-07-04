<template>
  <div>

    <div class="expand-table-row">
      <td><span v-if="data.child_orgs.length > 0" class="table-row-expand-icon" :class="expand ? 'table-row-expanded' : 'table-row-collapsed'" @click="toggleMenuExpand()"></span>{{ data.so_name }}</td>
      <td>{{ data.so_code }}</td>
      <td>{{ data.so_contact }}</td>
      <td>{{ data.so_email }}</td>
      <td>{{ data.so_post }}</td>
      <td>
        <el-button type="text" @click="modifyMenu(data)">编辑</el-button>
        <el-button type="text" @click="deleteMenu(data)" :disabled="data.child_orgs.length > 0">删除</el-button>
      </td>
    </div>


    <expand-row :data="subMenu" v-for="(subMenu, index) in data.child_orgs" v-if="expand"></expand-row>


  </div>
</template>

<script>"use strict";define(["vue","reqVue!./ExpandRow"],function(n,e){return n.component("ExpandRow",{template:template,components:{ExpandRow:e},data:function(){return{expand:!1}},props:{data:Object},mounted:function(){},methods:{toggleMenuExpand:function(){this.expand=!this.expand}},render:function(n){}})});</script>
