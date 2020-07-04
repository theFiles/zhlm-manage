package com.rogrand.sys.service;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.exception.ServiceException;
import com.rogrand.core.service.BaseService;
import com.rogrand.sys.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("sysRoleService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class RoleService
  extends BaseService
{
  public Role query(String sr_id) throws Exception {
    return query(new Role(sr_id));
  }








  public Role query(Long sr_id) throws Exception {
    return query(new Role(sr_id));
  }









  public Role query(Role role) throws Exception {
    return (Role)this.sqlDao.query("sys_role.query", role);
  }








  public List<Role> list(Role role) throws Exception {
    return this.sqlDao.list("sys_role.query", role);
  }








  public PageResult pageList(PageParam role) throws Exception {
    role.setCountSql("sys_role.pageCount");
    role.setRecordSql("sys_role.pageList");
    return this.pageService.pageQuery(role);
  }








  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void create(Role role, Map<String, String[]> menuAction) throws Exception {
    role.setMode("sr_code");
    Role check = query(role);
    if (check != null) throw new ServiceException("2000", "角色编码已存在");
    this.sqlDao.create("sys_role.create", role);

    RoleMenu roleMenu = new RoleMenu();
    RoleAction roleAction = new RoleAction();
    for (Map.Entry<String, String[]> entry : menuAction.entrySet()) {
      roleMenu.setSr_id(role.getSr_id());
      roleMenu.setSm_id(entry.getKey());
      this.sqlDao.create("sys_role_menu.create", roleMenu);
      String[] sa_ids = entry.getValue();
      if (sa_ids != null && sa_ids.length > 0) {
        for (String sa_id : sa_ids) {
          roleAction.setSr_id(role.getSr_id());
          roleAction.setSm_id(roleMenu.getSm_id());
          roleAction.setSa_id(sa_id);
          this.sqlDao.create("sys_role_action.create", roleAction);
        }
      }
    }
  }








  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String update(Role role, Map<String, String[]> menuAction) throws Exception {
    role.setMode("sr_code");
    Role check = query(role);
    if (check != null && !check.getSr_id().equals(role.getSr_id())) return "2";
    this.sqlDao.update("sys_role.update", role);
    this.sqlDao.delete("sys_role_action.delete", new RoleAction("sr_id", role.getSr_id()));
    this.sqlDao.delete("sys_role_menu.delete", new RoleMenu("sr_id", role.getSr_id()));
    RoleMenu roleMenu = new RoleMenu();
    RoleAction roleAction = new RoleAction();
    for (Map.Entry<String, String[]> entry : menuAction.entrySet()) {
      roleMenu.setSr_id(role.getSr_id());
      roleMenu.setSm_id(entry.getKey());
      this.sqlDao.create("sys_role_menu.create", roleMenu);
      String[] sa_ids = entry.getValue();
      if (sa_ids != null && sa_ids.length > 0) {
        for (String sa_id : sa_ids) {
          roleAction.setSr_id(role.getSr_id());
          roleAction.setSm_id(roleMenu.getSm_id());
          roleAction.setSa_id(sa_id);
          this.sqlDao.create("sys_role_action.create", roleAction);
        }
      }
    }
    return "1";
  }








  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(Role role) throws Exception {
    this.sqlDao.delete("sys_user_role.delete", new UserRole("sr_id", role.getSr_id()));
    this.sqlDao.delete("sys_role_action.delete", new RoleAction("sr_id", role.getSr_id()));
    this.sqlDao.delete("sys_role_menu.delete", new RoleMenu("sr_id", role.getSr_id()));
    this.sqlDao.delete("sys_role.delete", role);
    return "1";
  }









  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(String sr_id) throws Exception {
    return delete(new Role(sr_id));
  }


  public List<Map<String, Object>> tree(Role role) throws Exception {
    List<Menu> menuList = this.sqlDao.list("sys_role_menu.queryMenu", role);
    List<Action> actionList = this.sqlDao.list("sys_role_action.queryAction", role);


    List<Map<String, Object>> appArray = new ArrayList<>();
    for (int i = 0; i < menuList.size(); i++) {
      Menu menu = menuList.get(i);
      if ("0".equals(menu.getSm_parentid())) {
        Map<String, Object> node = new HashMap<>();
        node.put("id", menu.getSm_id());
        node.put("sap_id", menu.getSap_id());
        node.put("sm_id", menu.getSm_id());
        node.put("sm_name", menu.getSm_name());
        node.put("state", "open");
        List<Map<String, Object>> menuArray = createTree(menuList, actionList, menu.getSm_id());
        if (menuArray.size() > 0) {
          node.put("children", menuArray);
          int check1 = 0;
          int check2 = 0;
          for (Map<String, Object> map : menuArray) {
            if (map.get("check").equals("1")) check1++;
            if (map.get("check").equals("2")) check2++;
          }
          if (check2 > 0)
          { node.put("check", "2");
             }

          else if (check1 == 0) { node.put("check", "0"); }
          else if (check1 == menuArray.size()) { node.put("check", "1"); }
          else { node.put("check", "2"); }


        }
        else {

          node.put("check", menu.getStatus().equals("1") ? "1" : "0");
          boolean search = false;
          List<Map<String, Object>> actionArray = new ArrayList<>();
          for (Action action : actionList) {
            if (action.getSm_id().equals(menu.getSm_id())) {
              search = true;
              Map<String, Object> item = new HashMap<>();
              item.put("sa_id", action.getSa_id());
              item.put("sa_group", action.getSa_group());

              item.put("check", action.getStatus().equals("1") ? "1" : "0");
              actionArray.add(item); continue;
            }
            if (search) {
              break;
            }
          }
          node.put("actionList", actionArray);
        }

        appArray.add(node);
      }
    }

    return appArray;
  }

  private List<Map<String, Object>> createTree(List<Menu> menuList, List<Action> actionList, String sm_parentid) {
    List<Map<String, Object>> menuArray = new ArrayList<>();
    boolean find = false;
    for (Menu menu : menuList) {
      if (menu.getSm_parentid().equals(sm_parentid)) {
        find = true;
        Map<String, Object> node = new HashMap<>();
        node.put("id", menu.getSm_id());
        node.put("sap_id", menu.getSap_id());
        node.put("sm_id", menu.getSm_id());
        node.put("sm_name", menu.getSm_name());
        node.put("sm_parentid", menu.getSm_parentid());
        if (menu.getSm_child().equals("1")) {
          List<Map<String, Object>> children = createTree(menuList, actionList, menu.getSm_id());
          if (children.size() > 0) {
            node.put("children", children);

            int check1 = 0;
            int check2 = 0;
            for (Map<String, Object> map : children) {
              if (map.get("check").equals("1")) check1++;
              if (map.get("check").equals("2")) check2++;
            }
            if (check2 > 0)
            { node.put("check", "2");
               }

            else if (check1 == 0) { node.put("check", "0"); }
            else if (check1 == children.size()) { node.put("check", "1"); }
            else { node.put("check", "2"); }

          } else {

            node.put("check", "check0");
          }
        } else {

          node.put("check", menu.getStatus().equals("1") ? "1" : "0");
          boolean search = false;
          List<Map<String, Object>> actionArray = new ArrayList<>();
          for (Action action : actionList) {
            if (action.getSm_id().equals(menu.getSm_id())) {
              search = true;
              Map<String, Object> item = new HashMap<>();
              item.put("sa_id", action.getSa_id());
              item.put("sa_group", action.getSa_group());

              item.put("check", action.getStatus().equals("1") ? "1" : "0");
              actionArray.add(item); continue;
            }
            if (search) {
              break;
            }
          }
          node.put("actionList", actionArray);
        }
        menuArray.add(node); continue;
      }
      if (find) {
        break;
      }
    }
    return menuArray;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\service\RoleService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */