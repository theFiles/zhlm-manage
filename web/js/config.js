// 配置文件
var config = {};

config.title = '中和联盟后台管理系统';
//config.host = "http://192.168.0.106:8082/template-manager/";
config.host = "/tuozhanbao-manage/";
config.passwordRegex = /^[\w]{6,15}$/;


define("config", [], function () {
  return config;
});