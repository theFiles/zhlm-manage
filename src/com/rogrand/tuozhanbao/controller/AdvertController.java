package com.rogrand.tuozhanbao.controller;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.DataConvert;
import com.rogrand.tuozhanbao.domain.Advert;
import com.rogrand.tuozhanbao.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller("AdvertController")
@RequestMapping({"/tuozhanbao/advert/*.do"})
public class AdvertController
  extends BaseController
{
  @Autowired
  @Qualifier("AdvertService")
  private AdvertService advertService;

  @ActionAnnotation(name = "广告分页查询", group = "查询")
  public void pageList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam param = BeanUtil.wrapPageParam(request);
    PageResult pageResult = this.advertService.pageList(param);
    responseJson(response, ResponseCode.SUCCESS, pageResult);
  }

  @ActionAnnotation(name = "广告下拉查询", group = "查询")
  public void selectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam param = BeanUtil.wrapPageParam(request);
    String fileds = (String)param.get("fileds");
    PageResult pageResult = this.advertService.pageList(param);
    List list = DataConvert.toSelectData(pageResult.getRows(), fileds);
    responseJson(response, ResponseCode.SUCCESS, list);
  }

  @ActionAnnotation(name = "广告详细查询", group = "查询")
  public void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, Object> model = new HashMap<>();
    String id = request.getParameter("id");
    Advert advert = this.advertService.query(request.getParameter("id"));
    model.put("advert", advert);
    responseJson(response, ResponseCode.SUCCESS, model);
  }

  @ActionAnnotation(name = "广告添加&修改保存", group = "保存", log = true)
  public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Advert advert = (Advert)BeanUtil.wrapBean(Advert.class, request.getParameter("advert"));
    if (advert.getId() == null) {
      this.advertService.create(advert);
    } else {
      this.advertService.update(advert);
    }
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "广告删除", group = "删除", log = true)
  public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    this.advertService.delete(request.getParameter("id"));
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "广告批量删除", group = "删除", log = true)
  public void deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String[] ids = (String[])BeanUtil.wrapArray(String.class, request.getParameter("ids"));
    this.advertService.delete(ids);
    responseJson(response, ResponseCode.SUCCESS, null);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\controller\AdvertController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */