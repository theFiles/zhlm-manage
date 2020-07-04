package com.rogrand.tuozhanbao.controller;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.DataConvert;
import com.rogrand.tuozhanbao.domain.IncomeRecords;
import com.rogrand.tuozhanbao.service.IncomeRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller("IncomeRecordsController")
@RequestMapping({"/tuozhanbao/incomeRecords/*.do"})
public class IncomeRecordsController
  extends BaseController
{
  @Autowired
  @Qualifier("IncomeRecordsService")
  private IncomeRecordsService incomeRecordsService;

  @ActionAnnotation(name = "收益记录分页查询", group = "查询")
  public void pageList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam param = BeanUtil.wrapPageParam(request);
    PageResult pageResult = this.incomeRecordsService.pageList(param);
    responseJson(response, ResponseCode.SUCCESS, pageResult);
  }

  @ActionAnnotation(name = "收益记录下拉查询", group = "查询")
  public void selectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam param = BeanUtil.wrapPageParam(request);
    String fileds = (String)param.get("fileds");
    PageResult pageResult = this.incomeRecordsService.pageList(param);
    List list = DataConvert.toSelectData(pageResult.getRows(), fileds);
    responseJson(response, ResponseCode.SUCCESS, list);
  }

  @ActionAnnotation(name = "收益记录详细查询", group = "查询")
  public void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, Object> model = new HashMap<>();
    String id = request.getParameter("id");
    IncomeRecords incomeRecords = this.incomeRecordsService.query(request.getParameter("id"));
    model.put("incomeRecords", incomeRecords);
    responseJson(response, ResponseCode.SUCCESS, model);
  }

  @ActionAnnotation(name = "收益记录添加&修改保存", group = "保存", log = true)
  public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
    IncomeRecords incomeRecords = (IncomeRecords)BeanUtil.wrapBean(IncomeRecords.class, request.getParameter("incomeRecords"));
    if (incomeRecords.getId() == null) {
      this.incomeRecordsService.create(incomeRecords);
    } else {
      this.incomeRecordsService.update(incomeRecords);
    }
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "收益记录删除", group = "删除", log = true)
  public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    this.incomeRecordsService.delete(request.getParameter("id"));
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "收益记录批量删除", group = "删除", log = true)
  public void deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String[] ids = (String[])BeanUtil.wrapArray(String.class, request.getParameter("ids"));
    this.incomeRecordsService.delete(ids);
    responseJson(response, ResponseCode.SUCCESS, null);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\controller\IncomeRecordsController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */