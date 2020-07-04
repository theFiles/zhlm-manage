package com.rogrand.tuozhanbao.controller;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.DataConvert;
import com.rogrand.tuozhanbao.domain.HotHeadlines;
import com.rogrand.tuozhanbao.service.HotHeadlinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller("HotHeadlinesController")
@RequestMapping({"/tuozhanbao/hotHeadlines/*.do"})
public class HotHeadlinesController
  extends BaseController
{
  @Autowired
  @Qualifier("HotHeadlinesService")
  private HotHeadlinesService hotHeadlinesService;

  @ActionAnnotation(name = "热门头条表分页查询", group = "查询")
  public void pageList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam param = BeanUtil.wrapPageParam(request);
    PageResult pageResult = this.hotHeadlinesService.pageList(param);
    responseJson(response, ResponseCode.SUCCESS, pageResult);
  }

  @ActionAnnotation(name = "热门头条表下拉查询", group = "查询")
  public void selectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam param = BeanUtil.wrapPageParam(request);
    String fileds = (String)param.get("fileds");
    PageResult pageResult = this.hotHeadlinesService.pageList(param);
    List list = DataConvert.toSelectData(pageResult.getRows(), fileds);
    responseJson(response, ResponseCode.SUCCESS, list);
  }

  @ActionAnnotation(name = "热门头条表详细查询", group = "查询")
  public void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, Object> model = new HashMap<>();
    String id = request.getParameter("id");
    HotHeadlines hotHeadlines = this.hotHeadlinesService.query(request.getParameter("id"));
    model.put("hotHeadlines", hotHeadlines);
    responseJson(response, ResponseCode.SUCCESS, model);
  }

  @ActionAnnotation(name = "热门头条表添加&修改保存", group = "保存", log = true)
  public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
    HotHeadlines hotHeadlines = (HotHeadlines)BeanUtil.wrapBean(HotHeadlines.class, request.getParameter("hotHeadlines"));
    if (hotHeadlines.getId() == null) {
      this.hotHeadlinesService.create(hotHeadlines);
    } else {
      this.hotHeadlinesService.update(hotHeadlines);
    }
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "热门头条表删除", group = "删除", log = true)
  public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    this.hotHeadlinesService.delete(request.getParameter("id"));
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "热门头条表批量删除", group = "删除", log = true)
  public void deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String[] ids = (String[])BeanUtil.wrapArray(String.class, request.getParameter("ids"));
    this.hotHeadlinesService.delete(ids);
    responseJson(response, ResponseCode.SUCCESS, null);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\controller\HotHeadlinesController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */