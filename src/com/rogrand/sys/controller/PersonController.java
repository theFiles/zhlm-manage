package com.rogrand.sys.controller;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.security.MD5;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.sys.domain.Role;
import com.rogrand.sys.domain.User;
import com.rogrand.sys.service.UserService;
import org.apache.commons.lang.StringUtils;
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


@Controller("sysPersonController")
@RequestMapping({"/sys/person_*"})
public class PersonController
  extends BaseController
{
  @Autowired
  @Qualifier("sysUserService")
  private UserService userService;

  @ActionAnnotation(name = "个人信息查看", check = ActionAnnotation.Type.LOGIN)
  public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, Object> map = new HashMap<>();
    User user = this.userService.query(getLoginUser(request).getSu_id());
    map.put("user", user);
    if (user.getSu_admin().equals("0")) {
      List<Role> roleList = this.userService.listRole(user);
      map.put("roleList", roleList);
    }
    return getView(request, map);
  }

  @ActionAnnotation(name = "个人信息修改", check = ActionAnnotation.Type.LOGIN)
  public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return view(request, response);
  }

  @ActionAnnotation(name = "个人信息修改", check = ActionAnnotation.Type.LOGIN, log = true)
  public ModelAndView editSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
    User user = (User)BeanUtil.wrapBean(User.class, request.getParameter("user"));
    return this.userService.updatePersonInfo(user) ? responseText(response, "1") : responseText(response, "0");
  }

  @ActionAnnotation(name = "个人密码修改", check = ActionAnnotation.Type.LOGIN)
  public ModelAndView editPass(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return getView(request);
  }

  @ActionAnnotation(name = "个人密码修改", check = ActionAnnotation.Type.LOGIN, log = true)
  public ModelAndView editPassSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String originalPwd = request.getParameter("su_password0");
    String newPwd = request.getParameter("su_password");

    if (StringUtils.isEmpty(originalPwd) || StringUtils.isEmpty(newPwd)) {
      return responseText(response, "原始密码或新密码不能为空!");
    }

    User user = this.userService.query(getLoginUser(request).getSu_id());
    if (user != null) {
      if (user.getSu_password().equals(MD5.getEncrypt(originalPwd))) {
        user.setSu_password(MD5.getEncrypt(newPwd));
        return responseText(response, this.userService.updatePassword(user));
      }
      return responseText(response, "输入的原始密码不正确!");
    }

    return responseText(response, "用户信息不存在!");
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\controller\PersonController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */