package com.rogrand.tuozhanbao.controller;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.DataConvert;
import com.rogrand.tuozhanbao.domain.Activity;
import com.rogrand.tuozhanbao.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller("ActivityController")
@RequestMapping({"/tuozhanbao/activity/*.do"})
public class ActivityController
  extends BaseController
{
  @Autowired
  @Qualifier("ActivityService")
  private ActivityService activityService;

  @ActionAnnotation(name = "活动分页查询", group = "查询")
  public void pageList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam param = BeanUtil.wrapPageParam(request);
    PageResult pageResult = this.activityService.pageList(param);
    responseJson(response, ResponseCode.SUCCESS, pageResult);
  }

  @ActionAnnotation(name = "活动下拉查询", group = "查询")
  public void selectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam param = BeanUtil.wrapPageParam(request);
    String fileds = (String)param.get("fileds");
    PageResult pageResult = this.activityService.pageList(param);
    List list = DataConvert.toSelectData(pageResult.getRows(), fileds);
    responseJson(response, ResponseCode.SUCCESS, list);
  }

  @ActionAnnotation(name = "活动详细查询", group = "查询")
  public void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, Object> model = new HashMap<>();
    String id = request.getParameter("id");
    Activity activity = this.activityService.query(request.getParameter("id"));
    model.put("activity", activity);
    responseJson(response, ResponseCode.SUCCESS, model);
  }

  @ActionAnnotation(name = "活动添加&修改保存", group = "保存", log = true)
  public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Activity activity = (Activity)BeanUtil.wrapBean(Activity.class, request.getParameter("activity"));
    if (activity.getId() == null) {
      this.activityService.create(activity);
    } else {
      this.activityService.update(activity);
    }
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "活动删除", group = "删除", log = true)
  public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    this.activityService.delete(request.getParameter("id"));
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "活动批量删除", group = "删除", log = true)
  public void deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String[] ids = (String[])BeanUtil.wrapArray(String.class, request.getParameter("ids"));
    this.activityService.delete(ids);
    responseJson(response, ResponseCode.SUCCESS, null);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\controller\ActivityController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */