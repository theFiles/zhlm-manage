package com.rogrand.sys.controller;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.sys.domain.Role;
import com.rogrand.sys.domain.User;
import com.rogrand.sys.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@Controller("sysRoleController")
@RequestMapping({"/sys/role/*.do"})
public class RoleController
  extends BaseController
{
  @Autowired
  @Qualifier("sysRoleService")
  private RoleService roleService;

  @ActionAnnotation(name = "角色列表", group = "查询")
  public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<Role> roleList = this.roleService.list(new Role("order", "sr_id"));
    responseJson(response, ResponseCode.SUCCESS, roleList);
  }

  @ActionAnnotation(name = "菜单分配情况", group = "查询")
  public void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String sr_id = request.getParameter("sr_id");
    Role role = new Role("sr_id", sr_id);
    User user = getLoginUser(request);
    role.setSap_id(user.getSap_id());
    List<Map<String, Object>> list = this.roleService.tree(role);
    responseJson(response, ResponseCode.SUCCESS, list);
  }

  @ActionAnnotation(name = "角色添加保存", group = "添加", log = true)
  public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Role role = (Role)BeanUtil.wrapBean(Role.class, request.getParameter("role"));
    JavaType javaType = TypeFactory.mapType(Map.class, String.class, String[].class);
    Map<String, String[]> menuAction = (Map<String, String[]>)BeanUtil.wrapBean(javaType, request.getParameter("menuAction"));

    if (StringUtils.isEmpty(role.getSr_id()) || "0".equals(role.getSr_id())) {
      this.roleService.create(role, menuAction);
    } else {
      this.roleService.update(role, menuAction);
    }

    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "角色删除", group = "删除", log = true)
  public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    this.roleService.delete(request.getParameter("sr_id"));
    responseJson(response, ResponseCode.SUCCESS, null);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\controller\RoleController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */