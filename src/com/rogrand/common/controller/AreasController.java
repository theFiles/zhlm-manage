package com.rogrand.common.controller;

import com.rogrand.common.domain.Areas;
import com.rogrand.common.service.AreasService;
import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.DataConvert;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller("AreasController")
@RequestMapping({"/common/areas_*.do"})
public class AreasController
  extends BaseController
{
  @Autowired
  @Qualifier("AreasService")
  private AreasService areasService;

  @ActionAnnotation(name = "行政区域县区信息表入口", group = "查询")
  public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return getView(request);
  }

  @ActionAnnotation(name = "行政区域县区信息表分页", group = "查询")
  public void page(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam param = new PageParam();
    BeanUtils.populate(param, request.getParameterMap());
    PageResult pageResult = this.areasService.pageList(param);
    responseJson(response, ResponseCode.SUCCESS, pageResult);
  }

  @ActionAnnotation(name = "行政区域县区信息", group = "查询")
  public void comboboxData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam param = new PageParam();
    BeanUtils.populate(param, request.getParameterMap());
    List<Areas> list = this.areasService.list(param);
    List selectList = DataConvert.toSelectData(list, new String[] { "areaid", "area" });
    responseJson(response, ResponseCode.SUCCESS, selectList);
  }

  @ActionAnnotation(name = "行政区域县区信息表详细", group = "查询")
  public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Areas areas = this.areasService.query(request.getParameter("id"));
    model.put("areas", areas);
    return getView(request, model);
  }

  @ActionAnnotation(name = "行政区域县区信息表添加", group = "添加")
  public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return getView(request);
  }

  @ActionAnnotation(name = "行政区域县区信息表添加保存", group = "添加", log = true)
  public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Areas areas = (Areas)BeanUtil.wrapBean(Areas.class, request.getParameter("areas"));
    String result = this.areasService.create(areas);
    return responseText(response, result);
  }

  @ActionAnnotation(name = "行政区域县区信息表修改", group = "修改")
  public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Areas areas = this.areasService.query(request.getParameter("id"));
    model.put("areas", areas);
    return getView(request, model);
  }

  @ActionAnnotation(name = "行政区域县区信息表修改保存", group = "修改", log = true)
  public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Areas areas = (Areas)BeanUtil.wrapBean(Areas.class, request.getParameter("areas"));
    String result = this.areasService.update(areas);
    return responseText(response, result);
  }

  @ActionAnnotation(name = "行政区域县区信息表删除", group = "删除", log = true)
  public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String result = this.areasService.delete(request.getParameter("id"));
    return responseText(response, result);
  }

  @ActionAnnotation(name = "行政区域县区信息表批量删除", group = "删除", log = true)
  public ModelAndView deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String[] ids = (String[])BeanUtil.wrapArray(String.class, request.getParameter("ids"));
    String result = this.areasService.delete(ids);
    return responseText(response, result);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\common\controller\AreasController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */