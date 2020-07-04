package com.rogrand.common.controller;

import com.rogrand.common.domain.Cities;
import com.rogrand.common.service.CitiesService;
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


@Controller("CitiesController")
@RequestMapping({"/common/cities_*.do"})
public class CitiesController
  extends BaseController
{
  @Autowired
  @Qualifier("CitiesService")
  private CitiesService citiesService;

  @ActionAnnotation(name = "行政区域地州市信息表入口", group = "查询")
  public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return getView(request);
  }

  @ActionAnnotation(name = "行政区域地州市信息表分页", group = "查询")
  public void page(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam param = new PageParam();
    BeanUtils.populate(param, request.getParameterMap());
    PageResult pageResult = this.citiesService.pageList(param);
    responseJson(response, ResponseCode.SUCCESS, pageResult);
  }

  @ActionAnnotation(name = "行政区域地州市信息", group = "查询")
  public void comboboxData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam param = new PageParam();
    BeanUtils.populate(param, request.getParameterMap());
    List<Cities> list = this.citiesService.list(param);
    List selectList = DataConvert.toSelectData(list, new String[] { "cityid", "city" });
    responseJson(response, ResponseCode.SUCCESS, selectList);
  }

  @ActionAnnotation(name = "行政区域地州市信息表详细", group = "查询")
  public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Cities cities = this.citiesService.query(request.getParameter("id"));
    model.put("cities", cities);
    return getView(request, model);
  }

  @ActionAnnotation(name = "行政区域地州市信息表添加", group = "添加")
  public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return getView(request);
  }

  @ActionAnnotation(name = "行政区域地州市信息表添加保存", group = "添加", log = true)
  public ModelAndView addSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Cities cities = (Cities)BeanUtil.wrapBean(Cities.class, request.getParameter("cities"));
    String result = this.citiesService.create(cities);
    return responseText(response, result);
  }

  @ActionAnnotation(name = "行政区域地州市信息表修改", group = "修改")
  public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Cities cities = this.citiesService.query(request.getParameter("id"));
    model.put("cities", cities);
    return getView(request, model);
  }

  @ActionAnnotation(name = "行政区域地州市信息表修改保存", group = "修改", log = true)
  public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Cities cities = (Cities)BeanUtil.wrapBean(Cities.class, request.getParameter("cities"));
    String result = this.citiesService.update(cities);
    return responseText(response, result);
  }

  @ActionAnnotation(name = "行政区域地州市信息表删除", group = "删除", log = true)
  public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String result = this.citiesService.delete(request.getParameter("id"));
    return responseText(response, result);
  }

  @ActionAnnotation(name = "行政区域地州市信息表批量删除", group = "删除", log = true)
  public ModelAndView deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String[] ids = (String[])BeanUtil.wrapArray(String.class, request.getParameter("ids"));
    String result = this.citiesService.delete(ids);
    return responseText(response, result);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\common\controller\CitiesController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */