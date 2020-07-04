package com.rogrand.sys.service;

import com.rogrand.core.service.BaseService;
import com.rogrand.core.system.SystemParameter;
import com.rogrand.sys.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("loginService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class LoginService
  extends BaseService
{
  public User checkLogin(User user) throws Exception {
    this.logger.info("查询用户...");
    User loginUser = (User)this.sqlDao.query("sys_login.queryUser", user);
    if (loginUser == null) return null;
    if (!loginUser.getSu_password().equals(user.getSu_password())) return null;
    App app = (App)this.sqlDao.query("sys_login.querySystem", new App("sap_code", SystemParameter.get("systemCode")));
    loginUser.setSap_id(app.getSap_id());
    if (!loginUser.getSu_admin().equals("1")) {
      this.logger.info("查询用户角色...");
      List<Role> roleList = this.sqlDao.list("sys_login.queryRole", loginUser);
      if (roleList != null && roleList.size() > 0) {
        String sr_id = "", sr_code = "", sr_name = "";
        for (Role role : roleList) {
          sr_id = sr_id + "," + role.getSr_id();
          sr_code = sr_code + "," + role.getSr_code();
          sr_name = sr_name + "," + role.getSr_name();
        }
        loginUser.setSr_id(sr_id.substring(1));
        loginUser.setSr_code(sr_code.substring(1));
        loginUser.setSr_name(sr_name.substring(1));
      }
    }
    return loginUser;
  }








  public List<Menu> queryMenu(User user) throws Exception {
    List<Menu> menuList = this.sqlDao.list("sys_login.queryMenu", user);
    List<Menu> parentMenus = new ArrayList<>();
    for (int i = 0; i < menuList.size(); i++) {
      Menu menu = menuList.get(i);
      if ("0".equals(menu.getSm_parentid())) {
        menu.setChild_menus(queryChildMenu(menu.getSm_id(), menuList));
        parentMenus.add(menu);
      }
    }

    return parentMenus;
  }

  private List<Menu> queryChildMenu(String sm_id, List<Menu> menus) {
    List<Menu> menuList = new ArrayList<>();
    for (int i = 0; i < menus.size(); i++) {
      Menu menu = menus.get(i);
      if (sm_id.equals(menu.getSm_parentid())) {
        menu.setChild_menus(queryChildMenu(menu.getSm_id(), menus));
        menuList.add(menu);
      }
    }
    return menuList;
  }


  public List<String> queryAction(User user) throws Exception {
    List<Action> actionList = this.sqlDao.list("sys_login.queryAction", user);
    List<String> list = new ArrayList<>();

    for (Action action : actionList) {
      if (action.getSa_class() != null && action.getSa_group() != null) {
        list.add(action.getSa_class() + "." + action.getSa_group());
      }
    }

    return list;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\service\LoginService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */