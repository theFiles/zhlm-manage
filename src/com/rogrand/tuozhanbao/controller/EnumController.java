package com.rogrand.tuozhanbao.controller;

import com.rogrand.core.controller.BaseController;
import com.rogrand.core.enums.*;
import com.rogrand.tuozhanbao.enums.UserGrade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller("EnumController")
@RequestMapping({"/tuozhanbao/enum/*.do"})
public class EnumController
  extends BaseController
{
  public void getIncomeType(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<Map<String, Object>> list = new ArrayList<>();
    IncomeType[] values = IncomeType.values();
    for (int i = 0; i < values.length; i++) {
      IncomeType enumobj = values[i];
      Map<String, Object> map = new HashMap<>();
      map.put("value", enumobj.getValue());
      map.put("text", enumobj.name());
      list.add(map);
    }
    responseJson(response, ResponseCode.SUCCESS, list);
  }







  public void getManufacturer(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<Map<String, Object>> list = new ArrayList<>();
    Manufacturer[] values = Manufacturer.values();
    for (int i = 0; i < values.length; i++) {
      Manufacturer enumobj = values[i];
      Map<String, Object> map = new HashMap<>();
      map.put("value", enumobj.getValue());
      map.put("text", enumobj.name());
      list.add(map);
    }
    responseJson(response, ResponseCode.SUCCESS, list);
  }







  public void getMessageType(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<Map<String, Object>> list = new ArrayList<>();
    MessageType[] values = MessageType.values();
    for (int i = 0; i < values.length; i++) {
      MessageType enumobj = values[i];
      Map<String, Object> map = new HashMap<>();
      map.put("value", enumobj.getValue());
      map.put("text", enumobj.name());
      list.add(map);
    }
    responseJson(response, ResponseCode.SUCCESS, list);
  }







  public void getOrderState(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<Map<String, Object>> list = new ArrayList<>();
    OrderState[] values = OrderState.values();
    for (int i = 0; i < values.length; i++) {
      OrderState enumobj = values[i];
      Map<String, Object> map = new HashMap<>();
      map.put("value", enumobj.getValue());
      map.put("text", enumobj.name());
      list.add(map);
    }
    responseJson(response, ResponseCode.SUCCESS, list);
  }







  public void getPayType(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<Map<String, Object>> list = new ArrayList<>();
    PayType[] values = PayType.values();
    for (int i = 0; i < values.length; i++) {
      PayType enumobj = values[i];
      Map<String, Object> map = new HashMap<>();
      map.put("value", enumobj.getValue());
      map.put("text", enumobj.name());
      list.add(map);
    }
    responseJson(response, ResponseCode.SUCCESS, list);
  }







  public void getSendState(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<Map<String, Object>> list = new ArrayList<>();
    SendState[] values = SendState.values();
    for (int i = 0; i < values.length; i++) {
      SendState enumobj = values[i];
      Map<String, Object> map = new HashMap<>();
      map.put("value", enumobj.getValue());
      map.put("text", enumobj.name());
      list.add(map);
    }
    responseJson(response, ResponseCode.SUCCESS, list);
  }







  public void getTeachingType(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<Map<String, Object>> list = new ArrayList<>();
    TeachingType[] values = TeachingType.values();
    for (int i = 0; i < values.length; i++) {
      TeachingType enumobj = values[i];
      Map<String, Object> map = new HashMap<>();
      map.put("value", enumobj.getValue());
      map.put("text", enumobj.name());
      list.add(map);
    }
    responseJson(response, ResponseCode.SUCCESS, list);
  }







  public void getTradeState(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<Map<String, Object>> list = new ArrayList<>();
    TradeState[] values = TradeState.values();
    for (int i = 0; i < values.length; i++) {
      TradeState enumobj = values[i];
      Map<String, Object> map = new HashMap<>();
      map.put("value", enumobj.getValue());
      map.put("text", enumobj.name());
      list.add(map);
    }
    responseJson(response, ResponseCode.SUCCESS, list);
  }







  public void getConfigType(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<Map<String, Object>> list = new ArrayList<>();
    ConfigType[] values = ConfigType.values();
    for (int i = 0; i < values.length; i++) {
      ConfigType enumobj = values[i];
      Map<String, Object> map = new HashMap<>();
      map.put("value", enumobj.getValue());
      map.put("text", enumobj.name());
      list.add(map);
    }
    responseJson(response, ResponseCode.SUCCESS, list);
  }







  public void getUserGrades(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<Map<String, Object>> list = new ArrayList<>();
    UserGrade[] values = UserGrade.values();
    for (int i = 0; i < values.length; i++) {
      UserGrade enumobj = values[i];
      Map<String, Object> map = new HashMap<>();
      map.put("value", enumobj.getValue());
      map.put("text", enumobj.name());
      list.add(map);
    }
    responseJson(response, ResponseCode.SUCCESS, list);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\controller\EnumController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */