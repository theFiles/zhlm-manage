package com.rogrand.sys.controller;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.sys.domain.Sysconfig;
import com.rogrand.sys.service.SysconfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller("SysconfigController")
@RequestMapping({"/sys/sysconfig/*.do"})
public class SysconfigController
  extends BaseController
{
  @Autowired
  @Qualifier("SysconfigService")
  private SysconfigService sysconfigService;

  @ActionAnnotation(name = "系统配置分页", group = "查询")
  public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam sysconfig = BeanUtil.wrapPageBean(request);
    PageResult pageResult = this.sysconfigService.list(sysconfig);
    responseJson(response, ResponseCode.SUCCESS, pageResult);
  }

  @ActionAnnotation(name = "系统配置详细", group = "查询")
  public void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Sysconfig sysconfig = this.sysconfigService.query(request.getParameter("configid"));
    model.put("sysconfig", sysconfig);
    responseJson(response, ResponseCode.SUCCESS, model);
  }

  @ActionAnnotation(name = "系统配置添加保存", group = "添加", log = true)
  public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Sysconfig sysconfig = (Sysconfig)BeanUtil.wrapBean(Sysconfig.class, request.getParameter("sysconfig"));
    if (sysconfig.getConfigid() == null) {
      sysconfig.setConfigaddtime(new Date());
      this.sysconfigService.create(sysconfig);
    } else {
      this.sysconfigService.update(sysconfig);
    }
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "系统配置删除", group = "删除", log = true)
  public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    this.sysconfigService.delete(request.getParameter("configid"));
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "系统配置批量删除", group = "删除", log = true)
  public ModelAndView deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String[] configids = (String[])BeanUtil.wrapArray(String.class, request.getParameter("configids"));
    String result = this.sysconfigService.delete(configids);
    return responseText(response, result);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\controller\SysconfigController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */