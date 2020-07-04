<template>
    <div class="build-page" v-loading="loading">

        <el-tree
                class="left"
                :data="treeData"
                show-checkbox
                highlight-current
                @check-change="checkChange"
                node-key="name"
                @node-click="nodeClick"
                :default-expanded-keys="['ape']"
                :props="defaultProps">
        </el-tree>

        <div class="right">

            <template v-if="currentData">

                <div class="action-list">
                    <el-button size="small" type="primary" icon="fas fa-save" @click="save" :loading="saveLoading">
                        保存配置
                    </el-button>
                    <el-button size="small" type="primary" icon="fas fa-wrench" @click="build" :loading="buildLoading">
                        生成代码
                    </el-button>
                </div>

                <h3>生成参数</h3>
                <table class="params-table el-table tree-table el-table--border ">
                    <tr>
                        <td>表名称</td>
                        <td>{{ currentData.name }}</td>
                        <td>domain类名</td>
                        <td>{{ "com.rogrand"+ subPackage + ".domain." + currentData.classDomain }}</td>
                    </tr>
                    <tr>
                        <td>controller类名</td>
                        <td>{{ "com.rogrand"+ subPackage + ".controller." + currentData.classDomain + "Controller" }}
                        </td>
                        <td>service类名</td>
                        <td>{{ "com.rogrand"+ subPackage + ".service." + currentData.classDomain + "Service" }}</td>
                    </tr>
                    <tr>
                        <td>url映射</td>
                        <td>
                            <template v-if="currentData.subjectModuleName">/</template>
                            {{ currentData.subjectModuleName }}{{ currentData.mappings }}
                        </td>
                        <td>模块名</td>
                        <td>{{ currentData.moduleName }}</td>
                    </tr>
                    <tr>
                        <td>表名注释</td>
                        <td>
                            <el-input size="small" v-model="currentData.annotation"></el-input>
                        </td>
                        <td>子模块名</td>
                        <td>
                            <el-input size="small" v-model="currentData.subjectModuleName"></el-input>
                        </td>
                    </tr>
                    <tr>
                        <td>生成CURD</td>
                        <td>
                            <el-checkbox v-model="currentData.buildCURD">是</el-checkbox>
                        </td>
                        <td>新增/修改页展示类型</td>
                        <td>
                            <el-radio v-model="currentData.pageType" label="1">新页面</el-radio>
                            <el-radio v-model="currentData.pageType" label="2">弹窗</el-radio>
                        </td>
                    </tr>
                    <tr>
                        <td>列表多选</td>
                        <td>
                            <el-checkbox v-model="currentData.multiple" true-label="1" false-label="0">多选</el-checkbox>
                        </td>
                        <td>是否分页</td>
                        <td>
                            <el-checkbox v-model="currentData.paging" true-label="1" false-label="0">分页</el-checkbox>
                        </td>
                    </tr>
                </table>

                <h3 style="margin-top: 40px">关联表</h3>
                <table class="el-table tree-table el-table--border">
                    <thead>
                    <tr>
                        <th>关联表</th>
                        <th>关联表字段</th>
                        <th>展示字段</th>
                        <th>主表字段</th>
                        <th>别名</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="associationTable in currentData.relevanceTables">
                        <td>
                            <el-select size="small" v-model="associationTable.associationTable"
                                       @change="selectChange(associationTable)">
                                <el-option v-for="table in treeData[0].children" :label="table.name"
                                           :value="table.name"></el-option>
                            </el-select>
                        </td>
                        <td>
                            <el-select size="small" v-model="associationTable.associationTableField">
                                <template v-if="associationTable.associationTable">
                                    <el-option v-for="column in getColumnList(associationTable.associationTable)"
                                               :label="column.name" :value="column.name"></el-option>
                                </template>
                            </el-select>
                        </td>
                        <td>
                            <el-select size="small" v-model="associationTable.displayField">
                                <template v-if="associationTable.associationTable">
                                    <el-option v-for="column in getColumnList(associationTable.associationTable)"
                                               :label="column.name" :value="column.name"></el-option>
                                </template>
                            </el-select>
                        </td>
                        <td>
                            <el-select size="small" v-model="associationTable.tableField">
                                <el-option v-for="column in currentData.columnList" :label="column.name"
                                           :value="column.name"></el-option>
                            </el-select>
                        </td>
                        <td>
                            <el-select size="small" v-model="associationTable.alias">
                                <el-option v-for="n in 10" :label="'t' + n" :value="'t' + n"></el-option>
                            </el-select>
                        </td>
                        <td>
                            <el-button type="danger" size="mini" @click="removeRow(associationTable)">删除</el-button>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <el-button plain size="small" @click="addRow"
                           style="width: 100%;margin-top: 16px;margin-bottom: 8px;border-style: dashed;"
                           icon="el-icon-plus">添加
                </el-button>

                <h3 style="margin-top: 40px">字段列表</h3>
                <div style="overflow-x: auto">
                    <table class="el-table tree-table el-table--border" style="min-width: 1600px">
                        <thead>
                        <tr>
                            <th>字段名</th>
                            <th width="250px">字段注释</th>
                            <th>类型</th>
                            <th width="300px">参数</th>
                            <th>固定列</th>
                            <th>是否搜索列</th>
                            <th>是否主键</th>
                            <th>允许为空</th>
                            <th>Jdbc类型</th>
                            <th>Ibatis类型</th>
                            <th>Java类型</th>
                            <th>长度</th>
                            <th>精度</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="column in currentData.columnList">
                            <td>{{ column.name }}</td>
                            <td>
                                <el-input size="small" v-model="column.annotation"></el-input>
                            </td>
                            <td>
                                <el-select v-model="column.type" size="small" style="width: 150px">
                                    <el-option v-for="type in typeList"
                                               :key="type.key"
                                               :label="type.label"
                                               :value="type.key">
                                    </el-option>
                                </el-select>
                            </td>
                            <td>
                                <template v-if="column.type === 'select'">
                                    <el-input size="small" placeholder="请求地址" v-model="column._url"></el-input>
                                    <el-input size="small" placeholder="text" v-model="column._text"></el-input>
                                    <el-input size="small" placeholder="value" v-model="column._value"></el-input>
                                </template>
                                <template v-if="column.type === 'image'">
                                    <el-input size="small" placeholder="图片上传最大张数"
                                              v-model.number="column._count"></el-input>
                                </template>
                                <template v-if="column.type === 'editTable'">
                                    <div class="editParamsRow" v-for="params in column.editParamsList">
                                        <el-input size="small" placeholder="字段" v-model="params.key"></el-input>
                                        <el-select v-model="params.type" size="small" style="width: 150px"
                                                   placeholder="类型">
                                            <el-option v-for="type in typeList" :key="type.key" :label="type.label"
                                                       :value="type.key" v-if="type.key !== 'editTable'"></el-option>
                                        </el-select>
                                        <el-button class="btn_del" type="text" icon="el-icon-delete"
                                                   @click="removeEditParamsRow(column, params)"></el-button>
                                        <template v-if="params.type === 'select'">
                                            <el-input size="small" placeholder="请求地址" v-model="params.url"></el-input>
                                            <el-input size="small" placeholder="text" v-model="params.text"></el-input>
                                            <el-input size="small" placeholder="value"
                                                      v-model="params.value"></el-input>
                                            <div class="line"></div>
                                        </template>
                                        <template v-if="params.type === 'image'">
                                            <el-input size="small" placeholder="图片上传最大张数"
                                                      v-model.number="params._count"></el-input>
                                            <div class="line"></div>
                                        </template>
                                    </div>
                                    <el-button type="text" size="small" @click="addEditParams(column)">添加</el-button>
                                </template>
                            </td>
                            <td>
                                <el-checkbox v-model="column.fix" true-label="1" false-label="0"></el-checkbox>
                            </td>
                            <td>
                                <el-checkbox v-model="column.search" true-label="1" false-label="0"></el-checkbox>
                            </td>
                            <td>{{ column.pk === 'Y' ? '是' : '否' }}</td>
                            <td>{{ column.nullAble === 'YES' ? '是' : '否' }}</td>
                            <td>{{ column.jdbcType }}</td>
                            <td>{{ column.ibatisType }}</td>
                            <td>{{ column.javaType }}</td>
                            <td>{{ column.length }}</td>
                            <td>{{ column.scale }}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <h3 style="margin-top: 40px">可编辑表格</h3>
                <div style="margin-bottom: 20px">
                    <el-select size="small" v-model="editTable.table" @change="selectChange(editTable)">
                        <el-option v-for="table in treeData[0].children" :label="table.name"
                                   :value="table.name"></el-option>
                    </el-select>
                    <el-select size="small" v-model="editTable.column">
                        <template v-if="editTable.table">
                            <el-option v-for="column in getColumnList(editTable.table)" :label="column.name"
                                       :value="column.name"></el-option>
                        </template>
                    </el-select>
                </div>

                <div style="overflow-x: auto">
                    <table class="el-table tree-table el-table--border" style="min-width: 1600px">
                        <thead>
                        <tr>
                            <th>字段名</th>
                            <th width="250px">字段注释</th>
                            <th>类型</th>
                            <th width="300px">参数</th>
                            <th>是否参与编辑</th>
                            <th>是否主键</th>
                            <th>允许为空</th>
                            <th>Jdbc类型</th>
                            <th>Ibatis类型</th>
                            <th>Java类型</th>
                            <th>长度</th>
                            <th>精度</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="column in editTable.columnList">
                            <td>{{ column.name }}</td>
                            <td>
                                <el-input size="small" v-model="column.annotation"></el-input>
                            </td>
                            <td>
                                <el-select v-model="column.type" size="small" style="width: 150px">
                                    <el-option v-for="type in typeList" :key="type.key" :label="type.label"
                                               :value="type.key"></el-option>
                                </el-select>
                            </td>
                            <td>
                                <template v-if="column.type === 'select'">
                                    <el-input size="small" placeholder="请求地址" v-model="column.url"></el-input>
                                    <el-input size="small" placeholder="text" v-model="column.text"></el-input>
                                    <el-input size="small" placeholder="value" v-model="column.value"></el-input>
                                </template>
                                <template v-if="column.type === 'image'">
                                    <el-input size="small" placeholder="图片上传最大张数"
                                              v-model.number="column.count"></el-input>
                                </template>
                            </td>
                            <td>
                                <el-checkbox v-model="column.editable" true-label="1" false-label="0"></el-checkbox>
                            </td>
                            <td>{{ column.pk === 'Y' ? '是' : '否' }}</td>
                            <td>{{ column.nullAble === 'YES' ? '是' : '否' }}</td>
                            <td>{{ column.jdbcType }}</td>
                            <td>{{ column.ibatisType }}</td>
                            <td>{{ column.javaType }}</td>
                            <td>{{ column.length }}</td>
                            <td>{{ column.scale }}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </template>

        </div>

    </div>
</template>
<script>"use strict";
define(["vue", "api"], function (t, g) {
    return t.component("build", {
        template: template, aliasList: ["t1"], data: function () {
            return {
                loading: !1,
                treeData: [{id: -1, name: "ape", children: []}],
                defaultProps: {children: "children", label: "name"},
                currentData: null,
                lastSelectNodeEl: null,
                saveLoading: !1,
                buildLoading: !1,
                selectList: [],
                typeList: [{label: "默认", key: "default"}, {label: "下拉框", key: "select"}, {
                    label: "是否下拉框",
                    key: "selectIf"
                }, {label: "图片上传", key: "image"}, {label: "textarea", key: "textarea"}, {label: "富文本", key: "editor"}]
            }
        }, computed: {
            tableList: function () {
                return this.treeData[0].children
            }, subPackage: function () {
                if (!this.currentData) return "";
                var t = "";
                return this.currentData.moduleName && (t += "." + this.currentData.moduleName), this.currentData.subjectModuleName && (t += "." + this.currentData.subjectModuleName), t
            }, editTable: function () {
                return this.currentData ? this.currentData.editTable : {table: "", column: "", columnList: []}
            }
        }, mounted: function () {
            this.fetch()
        }, methods: {
            fetch: function (t, e) {
                var T = this;
                this.loading = !0, g.post("/auto/tableList.do").then(function (t) {
                    var e = t.result.tableList, a = !0, r = !1, n = void 0;
                    try {
                        for (var i, l = e[Symbol.iterator](); !(a = (i = l.next()).done); a = !0) {
                            var o = i.value;
                            o.multiple = o.multiple || "0", o.pageType = o.pageType || "0", o.paging = o.paging || "1", o.relevanceTables = o.relevanceTables || [], o.editTable = o.editTable || {
                                table: "",
                                column: "",
                                columnList: []
                            };
                            var s = !0, u = !1, c = void 0;
                            try {
                                for (var d, m = o.editTable.columnList[Symbol.iterator](); !(s = (d = m.next()).done); s = !0) {
                                    var f = d.value, h = f.params || {};
                                    f.url = h.url || "", f.text = h.text || "", f.value = h.value || "", f.count = h.count || ""
                                }
                            } catch (t) {
                                u = !0, c = t
                            } finally {
                                try {
                                    !s && m.return && m.return()
                                } finally {
                                    if (u) throw c
                                }
                            }
                            var y = !0, v = !1, p = void 0;
                            try {
                                for (var b, g = o.columnList[Symbol.iterator](); !(y = (b = g.next()).done); y = !0) {
                                    var L = b.value, x = L.params || {};
                                    L.type = L.type || "", L._url = x.url || "", L._text = x.text || "", L._value = x.value || "", L._count = x.count || "", L.editParamsList = x.editParamsList || [], L.required = L.required || "0", L.search = L.search || "0", L.fix = L.fix || "0"
                                }
                            } catch (t) {
                                v = !0, p = t
                            } finally {
                                try {
                                    !y && g.return && g.return()
                                } finally {
                                    if (v) throw p
                                }
                            }
                        }
                    } catch (t) {
                        r = !0, n = t
                    } finally {
                        try {
                            !a && l.return && l.return()
                        } finally {
                            if (r) throw n
                        }
                    }
                    T.treeData[0].children = e, T.loading = !1
                }).catch(function (t) {
                    T.loading = !1, T.$alert(t, "获取失败")
                })
            }, nodeClick: function (t, e, a) {
                if (!(0 < e.childNodes.length || -1 === t.id)) {
                    this.lastSelectNodeEl && this.lastSelectNodeEl.removeAttribute("active");
                    var r = a.$el;
                    r.setAttribute("active", ""), this.lastSelectNodeEl = r, this.currentData = t
                }
            }, checkChange: function (t, e) {
                if (e) this.selectList.push(t); else {
                    var a = this.selectList.indexOf(t);
                    -1 < a && this.selectList.splice(a, 1)
                }
            }, save: function () {
                for (var t, e, a = this, r = this.tableList, n = {}, i = 0; i < r.length; i++) {
                    var l = r[i], o = l.name;
                    n[o + ".annotation"] = l.hasOwnProperty("annotation") ? l.annotation : "", n[o + ".subjectmodulename"] = l.hasOwnProperty("subjectModuleName") ? l.subjectModuleName : "", n[o + ".buildcurd"] = l.hasOwnProperty("buildCURD") ? l.buildCURD : "", n[o + ".pageType"] = l.hasOwnProperty("pageType") ? l.pageType : "", n[o + ".multiple"] = l.hasOwnProperty("multiple") ? l.multiple : "", n[o + ".relevanceTables"] = JSON.stringify(l.relevanceTables), n[o + ".paging"] = l.hasOwnProperty("paging") ? l.paging : "";
                    var s = l.editTable.columnList, u = !0, c = !1, d = void 0;
                    try {
                        for (var m, f = s[Symbol.iterator](); !(u = (m = f.next()).done); u = !0) {
                            var h = m.value;
                            "select" === h.type ? (h.params = {}, h.params.url = h.url, h.params.text = h.text, h.params.value = h.value) : "image" === h.type && (h.params.count = h.count)
                        }
                    } catch (t) {
                        c = !0, d = t
                    } finally {
                        try {
                            !u && f.return && f.return()
                        } finally {
                            if (c) throw d
                        }
                    }
                    n[o + ".editTable"] = JSON.stringify(l.editTable), t = l.columnList;
                    for (var y = 0; y < t.length; y++) {
                        n[o + ".column." + (e = t[y]).name] = e.hasOwnProperty("annotation") ? e.annotation : "", n[o + ".column." + e.name + ".type"] = e.hasOwnProperty("type") ? e.type : "", n[o + ".column." + e.name + ".annotation"] = e.hasOwnProperty("annotation") ? e.annotation : "";
                        var v = {};
                        "select" === e.type ? (v.url = e._url, v.text = e._text, v.value = e._value) : "image" === e.type && (v.count = e._count), n[o + ".column." + e.name + ".params"] = JSON.stringify(v), n[o + ".column." + e.name + ".search"] = e.hasOwnProperty("search") ? e.search : "", n[o + ".column." + e.name + ".fix"] = e.hasOwnProperty("fix") ? e.fix : "", n[o + ".column." + e.name + ".required"] = e.hasOwnProperty("required") ? e.required : "", n[o + ".column." + e.name + ".editParamsList"] = JSON.stringify(e.editParamsList)
                    }
                }
                console.log(n), this.saveLoading = !0, g.post("auto/save.do", {param: n}).then(function (t) {
                    a.saveLoading = !1, a.$message({message: "配置保存成功", type: "success"})
                }).catch(function (t) {
                    a.$alert(t, "保存失败"), a.saveLoading = !1
                })
            }, build: function () {
                var e = this;
                if (0 !== this.selectList.length) {
                    var t = !0, a = !1, r = void 0;
                    try {
                        for (var n, i = this.selectList[Symbol.iterator](); !(t = (n = i.next()).done); t = !0) {
                            var l = n.value, o = !0, s = !1, u = void 0;
                            try {
                                for (var c, d = l.editTable.columnList[Symbol.iterator](); !(o = (c = d.next()).done); o = !0) {
                                    var m = c.value;
                                    "select" === m.type ? m.params = {
                                        url: m.url,
                                        text: m.text,
                                        value: m.value
                                    } : "image" === m.type && (m.params = {count: m.count})
                                }
                            } catch (t) {
                                s = !0, u = t
                            } finally {
                                try {
                                    !o && d.return && d.return()
                                } finally {
                                    if (s) throw u
                                }
                            }
                            var f = !0, h = !1, y = void 0;
                            try {
                                for (var v, p = l.columnList[Symbol.iterator](); !(f = (v = p.next()).done); f = !0) {
                                    var b = v.value;
                                    "select" === b.type ? b.params = {
                                        url: b._url,
                                        text: b._text,
                                        value: b._value
                                    } : "image" === b.type && (b.params = {count: b._count})
                                }
                            } catch (t) {
                                h = !0, y = t
                            } finally {
                                try {
                                    !f && p.return && p.return()
                                } finally {
                                    if (h) throw y
                                }
                            }
                        }
                    } catch (t) {
                        a = !0, r = t
                    } finally {
                        try {
                            !t && i.return && i.return()
                        } finally {
                            if (a) throw r
                        }
                    }
                    this.buildLoading = !0, g.post("auto/create.do", {tableList: this.selectList}).then(function (t) {
                        e.buildLoading = !1, e.$alert("代码生成成功。生成目录/web/autoBuild", "消息提醒")
                    }).catch(function (t) {
                        e.$alert(t, "生成代码请求发送失败"), e.buildLoading = !1
                    })
                } else this.$alert("请勾选树节点中的表生成代码", "提示")
            }, addRow: function () {
                var e = {associationTable: "", associationTableField: "", displayField: "", tableField: "", alias: ""};
                Object.defineProperty(e, "_columnList", {
                    get: function () {
                        var t = this.tableList.filter(function (t) {
                            return t.name === e.associationTable
                        })[0];
                        return t ? t.columnList : []
                    }
                }), this.currentData.relevanceTables.push(e)
            }, removeRow: function (t) {
                var e = this.currentData.relevanceTables.indexOf(t);
                -1 < e && this.currentData.relevanceTables.splice(e, 1)
            }, getColumnList: function (e) {
                var t = this.tableList.filter(function (t) {
                    return t.name === e
                })[0];
                if (t) {
                    var a = t.columnList;
                    return JSON.parse(JSON.stringify(a))
                }
                return []
            }, selectChange: function (e) {
                if (e.associationTableField && (e.associationTableField = ""), e.column && (e.column = ""), e.table) {
                    var t = this.tableList.filter(function (t) {
                        return t.name === e.table
                    })[0];
                    if (t) {
                        var a = t.columnList, r = !0, n = !1, i = void 0;
                        try {
                            for (var l, o = a[Symbol.iterator](); !(r = (l = o.next()).done); r = !0) {
                                var s = l.value;
                                this.$set(s, "editable", "1")
                            }
                        } catch (t) {
                            n = !0, i = t
                        } finally {
                            try {
                                !r && o.return && o.return()
                            } finally {
                                if (n) throw i
                            }
                        }
                        e.columnList = JSON.parse(JSON.stringify(a))
                    } else e.columnList = []
                }
            }, addEditParams: function (t) {
                t.editParamsList.push({key: "", type: "default", url: "", text: "", value: "", count: ""})
            }, removeEditParamsRow: function (t, e) {
                var a = t.editParamsList.indexOf(e);
                -1 < a && t.editParamsList.splice(a, 1)
            }, processData: function (t) {
            }
        }
    })
});</script>