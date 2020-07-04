<template>
  <div class="form-item" :class="{ 'is-required': required, error: errorInfo }">
    <label v-if="label">{{ label }}</label>
    <div v-else class="empty-label"></div>

    <div class="control-wrapper" style="width: 50%">
      <div class="value-edit-list">
        <div class="value-edit-item" v-for="(item, index) in list">
          <!--<label>购买月数：</label>-->
          <el-input size="small" placeholder="请输入sn码" v-model="item.monthCount"></el-input>
          <!--<label style="margin-left: 16px">价格：</label>-->
          <!--<el-input size="small" placeholder="请输入" v-model="item.price"></el-input>-->
          <a><i class="el-icon-delete" @click="remove(index)"></i></a>
        </div>
        <el-button size="mini" @click="add">新增</el-button>

        <div class="el-form-item__error" v-if="errorInfo">
          {{ errorInfo }}
        </div>

      </div>
    </div>
  </div>
</template>

<script>
  define(["vue", "validateUtils"], function (Vue, validateUtils) {
    return Vue.component("ValueEditList", {
      template: template,
      data: function () {
        return {
          dataType: 'Array',
          newVal: [],

          list: [
              {id:0,monthCount:''}
          ],
          errorInfo: '',
        };
      },
      watch: {
        list () {
          this.$emit('input', this.list)
          if (this.errorInfo) {
            this.validate()
          }
        },
        newVal () {
          this.list = this.newVal;
        }
      },
      props: {
        label: String,
        name: String,
        required: Boolean,
      },
      mounted () {

      },
      methods: {
        add () {

          this.list.push({
            monthCount: '',
            // price: '',
          })
        },

        remove (index) {
          this.list.splice(index, 1);
        },

        validate () {
          console.log('xx')
          let isValid = true;
          this.errorInfo = '';

          // #1：校验必填项是否必填
          if (this.required && this.list.length === 0) {
            this.errorInfo = '请填写';
            return false;
          }
          for (const item of this.list) {
            if (!item.monthCount) {
              this.errorInfo = '请填写';
              isValid = false;
              break;
            }
            // if (!validateUtils.validatePositiveIntegerWithZero(item.monthCount)) {
            //   this.errorInfo = '月份数只能为有效数字';
            //   isValid = false;
            //   break;
            // }
            // if (!validateUtils.validatePositiveNumber(item.price)) {
            //   this.errorInfo = '价格不能小于 0';
            //   isValid = false;
            //   break;
            // }
          }

          return isValid;
        }

      }
    });
  });
</script>

<style>

  .value-edit-item {
    display: flex;
    align-items: center;

  }
  .value-edit-item {
    margin-bottom: 12px;
  }

  .value-edit-item label {
    color: rgba(0,0,0,.85);
    margin-right: 2px;
  }

  .value-edit-item > .el-input {
    margin-right: 10px;
    width: 200px;
  }

  .value-edit-item a {
    cursor: pointer;
  }

</style>