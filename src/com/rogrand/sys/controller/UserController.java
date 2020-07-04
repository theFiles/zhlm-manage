package com.rogrand.sys.controller;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.sys.domain.User;
import com.rogrand.sys.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller("sysUserController")
@RequestMapping({"/sys/user/*"})
public class UserController
  extends BaseController
{
  @Autowired
  @Qualifier("sysUserService")
  private UserService userService;

  @ActionAnnotation(name = "用户分页查询", group = "查询")
  public void pageList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    PageParam param = BeanUtil.wrapPageBean(request);
    param.put("su_admin", getLoginUser(request).getSu_admin());
    PageResult pageResult = this.userService.pageList(param);
    responseJson(response, ResponseCode.SUCCESS, pageResult);
  }

  @ActionAnnotation(name = "用户详细查询", group = "查询")
  public void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, Object> map = new HashMap<>();
    User user = this.userService.query(request.getParameter("su_id"));
    map.put("user", user);
    responseJson(response, ResponseCode.SUCCESS, map);
  }

  @ActionAnnotation(name = "用户添加保存", group = "添加", log = true)
  public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
    User user = (User)BeanUtil.wrapBean(User.class, request.getParameter("user"));
    if (StringUtils.isEmpty(user.getSu_id())) {
      this.userService.create(user);
    } else {
      this.userService.update(user);
    }
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "用户删除", group = "删除", log = true)
  public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    this.userService.delete(request.getParameter("su_id"));
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "密码修改", group = "修改", log = true)
  public void editPass(HttpServletRequest request, HttpServletResponse response) throws Exception {
    User user = (User)BeanUtil.wrapBean(User.class, request.getParameter("user"));
    String result = this.userService.updatePassword(user);
    if (result.equals("1")) {
      User loginUser = getLoginUser(request);
      if (loginUser.getSu_id().equals(user.getSu_id())) {
        loginUser.setSu_password(user.getSu_password());
      }
    }

    responseJson(response, ResponseCode.SUCCESS, null);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\controller\UserController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */