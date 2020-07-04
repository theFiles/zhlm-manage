package com.rogrand.sys.controller;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.FileUtil;
import com.rogrand.sys.domain.Action;
import com.rogrand.sys.domain.Menu;
import com.rogrand.sys.domain.User;
import com.rogrand.sys.service.AppService;
import com.rogrand.sys.service.MenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller("sysMenuController")
@RequestMapping({"/sys/menu/*.do"})
public class MenuController
  extends BaseController
{
  @Autowired
  @Qualifier("sysMenuService")
  private MenuService menuService;
  @Autowired
  @Qualifier("sysAppService")
  private AppService appService;

  @ActionAnnotation(name = "菜单列表", group = "查询")
  public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<Menu> tree = this.menuService.tree(getLoginUser(request));
    responseJson(response, ResponseCode.SUCCESS, tree);
  }

  @ActionAnnotation(name = "菜单详细", group = "查询")
  public void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, Object> map = new HashMap<>();
    Menu menu = this.menuService.query(request.getParameter("sm_id"));
    map.put("menu", menu);
    if (menu.getSm_type().equals("1")) {
      Action action = new Action("sm_id", menu.getSm_id());
      action.setOrder("sa_group");
      List<Action> actionList = this.menuService.listAction(action);
      map.put("actionList", actionList);
    }
    responseJson(response, ResponseCode.SUCCESS, map);
  }

  @ActionAnnotation(name = "菜单添加保存", group = "添加", log = true)
  public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Menu menu = (Menu)BeanUtil.wrapBean(Menu.class, request.getParameter("menu"));
    List<Action> actionList = BeanUtil.wrapBeanList(Action.class, request.getParameter("actionList"));
    if (StringUtils.isEmpty(menu.getSm_id())) {
      User user = getLoginUser(request);
      menu.setSap_id(user.getSap_id());
      this.menuService.create(menu, actionList);
    } else {
      this.menuService.update(menu, actionList);
    }

    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "菜单删除", group = "删除", log = true)
  public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String sm_id = request.getParameter("sm_id");
    this.menuService.delete(sm_id);
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "菜单上移", group = "修改")
  public void orderup(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String sm_id = request.getParameter("sm_id");
    this.menuService.orderup(sm_id);
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "菜单下移", group = "修改")
  public void orderdown(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String sm_id = request.getParameter("sm_id");
    this.menuService.orderdown(sm_id);
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "菜单排序", group = "修改")
  public void updateOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<Menu> menuList = BeanUtil.wrapBeanList(Menu.class, request.getParameter("menus"));
    this.menuService.updateOrder(menuList);
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  private Map<String, String> getIcons() {
    Map<String, String> map = new HashMap<>();
    File file = new File(getServletContext().getRealPath("/css/iconMenu.css"));
    String css = FileUtil.read(file);

    String regex = "/\\*(.*?)\\*/";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(css);
    List<String> commentList = new ArrayList<>();
    while (matcher.find()) {
      if (matcher.group(1) != null) commentList.add(matcher.group(1).trim());

    }
    regex = "\\.(.*?)\\{";
    pattern = Pattern.compile(regex);
    matcher = pattern.matcher(css);
    List<String> styleList = new ArrayList<>();

    while (matcher.find()) {
      if (matcher.group(1) != null) styleList.add(matcher.group(1).trim());

    }
    for (int i = 0; i < styleList.size(); i++) {
      String key = styleList.get(i);
      String value = "";
      if (commentList.size() > i) value = commentList.get(i);
      map.put(key, value);
    }

    LinkedHashMap<String, String> sortMap = new LinkedHashMap<>();
    Object[] keys = map.keySet().toArray();
    Arrays.sort(keys);
    for (Object key : keys) {
      sortMap.put((String)key, map.get(key));
    }
    return sortMap;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\controller\MenuController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */