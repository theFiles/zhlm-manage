package com.rogrand.tuozhanbao.controller;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.DataConvert;
import com.rogrand.tuozhanbao.domain.Address;
import com.rogrand.tuozhanbao.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller("AddressController")
@RequestMapping({"/tuozhanbao/address/*.do"})
public class AddressController
  extends BaseController
{
  @Autowired
  @Qualifier("AddressService")
  private AddressService addressService;

  @ActionAnnotation(name = "收货地址分页查询", group = "查询")
  public void pageList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam param = BeanUtil.wrapPageParam(request);
    PageResult pageResult = this.addressService.pageList(param);
    responseJson(response, ResponseCode.SUCCESS, pageResult);
  }

  @ActionAnnotation(name = "收货地址下拉查询", group = "查询")
  public void selectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam param = BeanUtil.wrapPageParam(request);
    String fileds = (String)param.get("fileds");
    PageResult pageResult = this.addressService.pageList(param);
    List list = DataConvert.toSelectData(pageResult.getRows(), fileds);
    responseJson(response, ResponseCode.SUCCESS, list);
  }

  @ActionAnnotation(name = "收货地址详细查询", group = "查询")
  public void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, Object> model = new HashMap<>();
    String id = request.getParameter("id");
    Address address = this.addressService.query(request.getParameter("id"));
    model.put("address", address);
    responseJson(response, ResponseCode.SUCCESS, model);
  }

  @ActionAnnotation(name = "收货地址添加&修改保存", group = "保存", log = true)
  public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Address address = (Address)BeanUtil.wrapBean(Address.class, request.getParameter("address"));
    if (address.getId() == null) {
      this.addressService.create(address);
    } else {
      this.addressService.update(address);
    }
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "收货地址删除", group = "删除", log = true)
  public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    this.addressService.delete(request.getParameter("id"));
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "收货地址批量删除", group = "删除", log = true)
  public void deleteBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String[] ids = (String[])BeanUtil.wrapArray(String.class, request.getParameter("ids"));
    this.addressService.delete(ids);
    responseJson(response, ResponseCode.SUCCESS, null);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\controller\AddressController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */