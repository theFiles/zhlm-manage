package com.rogrand.sys.service;

import com.rogrand.core.exception.ServiceException;
import com.rogrand.core.service.BaseService;
import com.rogrand.sys.domain.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("sysMenuService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class MenuService
  extends BaseService
{
  @Autowired
  @Qualifier("loginService")
  private LoginService loginService;

  public Menu query(String sm_id) throws Exception {
    return query(new Menu(sm_id));
  }

  public Menu query(Menu param) throws Exception {
    return (Menu)this.sqlDao.query("sys_menu.query", param);
  }

  public List<Menu> list(Menu param) throws Exception {
    return this.sqlDao.list("sys_menu.query", param);
  }








  public List<Action> listAction(Action action) throws Exception {
    return this.sqlDao.list("sys_action.query", action);
  }








  public List<Menu> tree(User user) throws Exception {
    user.setSu_admin("1");
    List<Menu> menuList = this.loginService.queryMenu(user);
    return menuList;
  }








  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void create(Menu menu, List<Action> actionList) throws Exception {
    Long order = (Long)this.sqlDao.query("sys_menu.selectOrder", menu);
    if (order == null) {
      order = Long.valueOf(Long.parseLong("1"));
    } else {
      Long long_1 = order, long_2 = order = Long.valueOf(order.longValue() + 1L);
    }
    menu.setSm_parentid(StringUtils.isEmpty(menu.getSm_parentid()) ? "0" : menu.getSm_parentid());
    menu.setSm_order(order);
    menu.setSm_admin("0");
    this.sqlDao.create("sys_menu.create", menu);
    if (menu.getSm_type().equals("1") && actionList != null) {
      for (Action action : actionList) {
        action.setSm_id(menu.getSm_id());
        this.sqlDao.create("sys_action.create", action);
      }
    }
    this.sqlDao.delete("sys_role_action.delete", new RoleAction("sm_id", menu.getSm_parentid()));
    this.sqlDao.delete("sys_action.delete", new Action("sm_id", menu.getSm_parentid()));
  }


  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String update(Menu menu, List<Action> actionList) throws Exception {
    Menu result = query(menu.getSm_id());
    this.sqlDao.update("sys_menu.update", menu);
    if (!result.getSap_id().equals(menu.getSap_id()) && result.getSm_child().equals("1")) {
      update(result, menu.getSap_id());
    }

    if (menu.getSm_type().equals("0")) {
      this.sqlDao.delete("sys_role_action.delete", new RoleAction("sm_id", menu.getSm_id()));
      this.sqlDao.delete("sys_action.delete", new Action("sm_id", menu.getSm_id()));
    } else if (menu.getSm_type().equals("1") &&
      actionList != null) {
      for (Action action : actionList) {
        if (action.getStatus().equals("0")) {
          action.setSm_id(menu.getSm_id());
          this.sqlDao.create("sys_action.create", action); continue;
        }  if (action.getStatus().equals("1")) {
          action.setSm_id(menu.getSm_id());
          this.sqlDao.update("sys_action.update", action); continue;
        }  if (action.getStatus().equals("2")) {
          this.sqlDao.delete("sys_role_action.delete", new RoleAction("sa_id", action.getSa_id()));
          this.sqlDao.delete("sys_action.delete", new Action("sa_id", action.getSa_id()));
        }
      }
    }

    this.sqlDao.delete("sys_role_action.delete", new RoleAction("sm_id", menu.getSm_parentid()));
    this.sqlDao.delete("sys_action.delete", new Action("sm_id", menu.getSm_parentid()));
    return "1";
  }


  private void update(Menu menu, String sap_id) throws Exception {
    Menu param = new Menu("mode", "order");
    param.setSap_id(menu.getSap_id());
    param.setSm_parentid(menu.getSm_id());
    List<Menu> menuList = list(param);
    if (menuList != null && menuList.size() > 0) {
      Menu update = new Menu("sap_id", sap_id);
      for (Menu item : menuList) {
        update.setSm_id(item.getSm_id());
        this.sqlDao.update("sys_menu.update", update);
        if (item.getSm_child().equals("1")) {
          update(item, sap_id);
        }
      }
    }
  }









  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(Menu menu) throws Exception {
    Menu result = (Menu)this.sqlDao.query("sys_menu.query", menu);
    if (result.getSm_child().equals("1")) return "2";
    if (result.getSm_admin().equals("1")) return "3";

    this.sqlDao.delete("sys_role_action.delete", new RoleAction("sm_id", menu.getSm_id()));
    this.sqlDao.delete("sys_role_menu.delete", new RoleMenu("sm_id", menu.getSm_id()));
    this.sqlDao.delete("sys_action.delete", new Action("sm_id", menu.getSm_id()));
    this.sqlDao.delete("sys_menu.delete", menu);
    return "1";
  }








  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String sm_id) throws Exception {
    delete(new Menu(sm_id));
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void updateOrder(List<Menu> menuList) throws Exception {
    for (Menu menu : menuList) {
      this.sqlDao.update("sys_menu.update", menu);
    }
  }






  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void orderup(String menuId) throws Exception {
    Menu menu = query(new Menu(menuId));
    Long order = menu.getSm_order();
    if (order.longValue() == 0L) {
      throw new ServiceException("2000", "已经在最上面了");
    }

    List<Menu> list = this.sqlDao.list("sys_menu.selectMenuList", menu);
    for (int i = 0; i < list.size(); i++) {
      if (((Menu)list.get(i)).getSm_order().longValue() == order.longValue() - 1L) {
        Menu menu1 = new Menu(((Menu)list.get(i)).getSm_id());
        menu1.setSm_order(order);
        this.sqlDao.update("sys_menu.update", menu);
      }
    }

    Menu menu2 = new Menu(menuId);
    menu2.setSm_order(Long.valueOf(order.longValue() - 1L));
    this.sqlDao.update("sys_menu.update", menu);
  }

  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void orderdown(String menuId) throws Exception {
    Menu menu = query(new Menu(menuId));
    Long order = menu.getSm_order();

    List<Menu> list = this.sqlDao.list("sys_menu.selectMenuList", menu);
    if (order.longValue() == list.size()) {
      throw new ServiceException("2000", "已经在最下面了");
    }

    for (int i = 0; i < list.size(); i++) {
      if (((Menu)list.get(i)).getSm_order().longValue() == order.longValue() + 1L) {
        Menu menu1 = new Menu(((Menu)list.get(i)).getSm_id());
        menu1.setSm_order(order);
        this.sqlDao.update("sys_menu.update", menu);
      }
    }

    Menu menu2 = new Menu(menuId);
    menu2.setSm_order(Long.valueOf(order.longValue() + 1L));
    this.sqlDao.update("sys_menu.update", menu);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\service\MenuService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */