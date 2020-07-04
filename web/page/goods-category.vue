<template>
    <div class="advanced-form-page">

        <card title="运费模板基础信息" class="form">
            <form-item label="模板名称" name="name" required></form-item>
            <form-item label="计价方式" name="charge_type" type="select" :list="[{text: '按件数', value: 1}, {text: '按重量', value: 2}]"></form-item>
            <form-item label="运费方式" name="ship_type" type="select" :list="[{text: '快递', value: '快递'}, {text: 'EMS', value: 'EMS'}, {text: '平邮', value: '平邮'}]"></form-item>
        </card>

        <card title="运费">
            <edit-table url="freightItem_list.do" :dataList.sync="freightList">
                <el-table-column label="首件数量" name="first_count">
                    <form-item slot-scope="scope" :data="scope.row" name="first_count" valid="positive-integer" required></form-item>
                </el-table-column>

                <el-table-column label="首件运费">
                    <form-item slot-scope="scope" :data="scope.row" name="first_freight" type="price" required></form-item>
                </el-table-column>

                <el-table-column label="次件数量">
                    <form-item slot-scope="scope" :data="scope.row" name="next_count" valid="positive-integer" required></form-item>
                </el-table-column>

                <el-table-column label="此件运费">
                    <form-item slot-scope="scope" :data="scope.row" name="next_freight" type="price" required></form-item>
                </el-table-column>

                <el-table-column label="指定省份">
                    <form-item slot-scope="scope" :data="scope.row" name="city" type="select" multiple url="common/getProvinceComoboBoxDate.do" select-text="province" select-value="province" required>
                        <template slot="static">{{ scope.row.city | filterCity }}</template>
                    </form-item>
                </el-table-column>
            </edit-table>
        </card>

        <div class="toolbar">
            <span></span>
            <el-button type="primary" size="small" @click="submitForm" :loading="loading">提  交</el-button>
        </div>

    </div>
</template>

<script>
  define(["vue", "api", "baseFormMixins", "advancedFormMixins"], function(Vue, api, baseFormMixins, advancedFormMixins) {
    return Vue.component("modify", {
      template: template,
      data: function() {
        return {
          freightList: [],
        };
      },
      mixins: [baseFormMixins, advancedFormMixins],
      filters: {
        filterCity: function (city) {
          return (city || []).join(',')
        }
      },
      methods: {
        submitForm () {
          if (this.isValid()) {
            this.loading = true;
            setTimeout(() => {
              this.loading = false;
            }, 1000)
          }
        },
      }
    });
  });
</script>