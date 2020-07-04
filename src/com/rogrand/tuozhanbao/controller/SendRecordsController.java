package com.rogrand.tuozhanbao.controller;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.DataConvert;
import com.rogrand.job.QuartzJob;
import com.rogrand.tuozhanbao.domain.Orders;
import com.rogrand.tuozhanbao.domain.SendRecords;
import com.rogrand.tuozhanbao.domain.Stock;
import com.rogrand.tuozhanbao.service.OrdersService;
import com.rogrand.tuozhanbao.service.SendRecordsService;
import com.rogrand.tuozhanbao.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller("SendRecordsController")
@RequestMapping({"/tuozhanbao/sendRecords/*.do"})
public class SendRecordsController
  extends BaseController
{
  @Autowired
  @Qualifier("SendRecordsService")
  private SendRecordsService sendRecordsService;
  @Autowired
  @Qualifier("StockService")
  private StockService stockService;
  @Autowired
  @Qualifier("OrdersService")
  private OrdersService ordersService;
  @Autowired
  private QuartzJob quartzJob;

  @ActionAnnotation(name = "配送记录分页查询", group = "查询")
  public void pageList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam param = BeanUtil.wrapPageParam(request);
    PageResult pageResult = this.sendRecordsService.pageList(param);
    responseJson(response, ResponseCode.SUCCESS, pageResult);
  }

  @ActionAnnotation(name = "配送记录下拉查询", group = "查询")
  public void selectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam param = BeanUtil.wrapPageParam(request);
    String fileds = (String)param.get("fileds");
    PageResult pageResult = this.sendRecordsService.pageList(param);
    List list = DataConvert.toSelectData(pageResult.getRows(), fileds);
    responseJson(response, ResponseCode.SUCCESS, list);
  }

  @ActionAnnotation(name = "配送记录详细查询", group = "查询")
  public void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, Object> model = new HashMap<>();
    String id = request.getParameter("id");
    SendRecords sendRecords = this.sendRecordsService.query(request.getParameter("id"));
    model.put("sendRecords", sendRecords);
    responseJson(response, ResponseCode.SUCCESS, model);
  }

  @ActionAnnotation(name = "配送记录添加&修改保存", group = "保存", log = true)
  public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
    SendRecords sendRecords = (SendRecords)BeanUtil.wrapBean(SendRecords.class, request.getParameter("sendRecords"));
    if (sendRecords.getId() == null) {
      this.sendRecordsService.create(sendRecords);
    } else {
      this.sendRecordsService.update(sendRecords);
    }
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "配送记录删除", group = "删除", log = true)
  public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    this.sendRecordsService.delete(request.getParameter("id"));
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "配送记录批量删除", group = "删除", log = true)
  public void deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String[] ids = (String[])BeanUtil.wrapArray(String.class, request.getParameter("ids"));
    this.sendRecordsService.delete(ids);
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "确认发货", group = "保存", log = true)
  public void send(HttpServletRequest request, HttpServletResponse response) throws Exception {
    SendRecords sendRecords = (SendRecords)BeanUtil.wrapBean(SendRecords.class, request.getParameter("sendRecords"));

    SendRecords sendRecords1 = this.sendRecordsService.query(sendRecords.getId().toString());

    Long orderId = sendRecords1.getOrder_id();
    Orders orders = this.ordersService.query(orderId.toString());

    for (int i = 0; i < sendRecords.getSnCode().size(); i++) {

      String snCode = (String)((Map)sendRecords.getSnCode().get(i)).get("monthCount");
      Stock stock = new Stock();
      stock.setApe_orders_id(orderId);
      stock.setBrand(sendRecords.getBrand());
      stock.setCreate_time(new Date());
      stock.setModel(sendRecords.getModel());
      stock.setSn(snCode);
      stock.setState("0");
      stock.setType("0");
      stock.setUser_id(orders.getUser_id());
      this.stockService.create(stock);
    }

    this.sendRecordsService.send(sendRecords);
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  public void test(HttpServletRequest request, HttpServletResponse response) throws Exception {
    this.quartzJob.computeIncome();
    responseJson(response, ResponseCode.SUCCESS, null);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\controller\SendRecordsController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */