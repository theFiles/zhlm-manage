package com.rogrand.sys.controller;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.exception.ServiceException;
import com.rogrand.core.security.ValidateCode;
import com.rogrand.core.system.SystemParameter;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.DateUtil;
import com.rogrand.core.util.StringUtil;
import com.rogrand.core.util.WebUtil;
import com.rogrand.sys.domain.Log;
import com.rogrand.sys.domain.Menu;
import com.rogrand.sys.domain.User;
import com.rogrand.sys.service.LogService;
import com.rogrand.sys.service.LoginService;
import com.rogrand.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller("manageController")
@RequestMapping({"/sys/welcome.do", "/sys/main.do", "/sys/login*.do"})
public class ManageController
  extends BaseController
{
  @Autowired
  @Qualifier("loginService")
  private LoginService loginService;
  @Autowired
  @Qualifier("sysLogService")
  private LogService logService;
  @Autowired
  @Qualifier("sysUserService")
  private UserService userService;

  private void logLogin(User loginUser, String method) throws Exception {
    Log log = new Log();
    log.setSl_date(new Date());
    log.setSl_class(getClass().getName());
    log.setSl_method(method);
    log.setSl_description("用户登录");
    log.setSl_user_code(loginUser.getSu_code());
    log.setSl_user_name(loginUser.getSu_name());
    log.setSl_org_name(loginUser.getSo_name());
    log.setSl_ip(loginUser.getSu_last_ip());
    log.setSl_content("用户登录系统");
    this.logService.create(log);
  }








  @ActionAnnotation(name = "系统入口动作", check = ActionAnnotation.Type.LOGIN)
  public void main(HttpServletRequest request, HttpServletResponse response) throws Exception {
    HttpSession session = request.getSession(true);
    User loginUser = getLoginUser(session);
    List<Menu> menuList = this.loginService.queryMenu(loginUser);
    if (menuList == null || menuList.size() == 0) {
      session.removeAttribute("loginUser");
      throw new ServiceException(ResponseCode.NO_MENU);
    }
    if (loginUser.getSu_admin().equals("0")) {
      List<String> actionList = this.loginService.queryAction(loginUser);
      session.setAttribute("loginAction", actionList);
    }

    Map<String, Object> model = new HashMap<>();
    model.put("menu", menuList);
    model.put("loginUser", loginUser);
    model.put("sysConfig", getSysConfig());

    HashMap<Class<?>, String[]> hashMap = new HashMap<Class<?>, String[]>()
      {

      };
    responseJson(response, ResponseCode.SUCCESS, model, hashMap);
  }








  @ActionAnnotation(name = "系统登录验证", check = ActionAnnotation.Type.NO)
  public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
    this.logger.info("开始登陆...");
    String checkRandomCode = SystemParameter.get("checkRandomCode");
    if (checkRandomCode != null && checkRandomCode.equals("true") &&
      !checkRandomCode(request)) {
      throw new ServiceException(ResponseCode.AUTH_CODE_ERR1);
    }

    User user = (User)BeanUtil.wrapBean(User.class, request.getParameter("user"));
    if (StringUtil.isEmpty(user.getSu_code())) throw new ServiceException("2000", "用户名不能为空");
    if (StringUtil.isEmpty(user.getSu_password())) throw new ServiceException("2000", "密码不能为空");
    User loginUser = this.loginService.checkLogin(user);
    if (loginUser == null) throw new ServiceException("2000", "用户名或密码错误");
    if (!loginUser.getSu_admin().equals("1") && StringUtil.isEmpty(loginUser.getSr_id())) {
      throw new ServiceException(ResponseCode.NO_ACCESS);
    }
    HttpSession session = request.getSession(true);
    session.setAttribute("loginUser", loginUser);
    session.removeAttribute("randomCode");


    this.logger.info("更新登陆次数");
    int count = loginUser.getSu_login_count() + 1;
    loginUser.setSu_login_count(count);
    loginUser.setSu_last_ip(WebUtil.getClientIP(request));
    loginUser.setSu_last_time(DateUtil.getNow());
    this.userService.update(loginUser);
    logLogin(loginUser, "login");
    responseJson(response, ResponseCode.SUCCESS, null);
  }








  @ActionAnnotation(name = "退出登录", check = ActionAnnotation.Type.NO)
  public void loginExit(HttpServletRequest request, HttpServletResponse response) throws Exception {
    HttpSession session = request.getSession(true);
    session.removeAttribute("loginUser");
    responseJson(response, ResponseCode.SUCCESS, null);
  }









  @ActionAnnotation(name = "登录验证码", check = ActionAnnotation.Type.NO)
  public ModelAndView loginRandom(HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("image/jpeg");
    HttpSession session = request.getSession(true);
    ValidateCode validateCode = new ValidateCode(80, 20, 4, 20);
    session.setAttribute("randomCode", validateCode.getCode());
    validateCode.write((OutputStream)response.getOutputStream());
    return null;
  }

  @ActionAnnotation(name = "欢迎页面", check = ActionAnnotation.Type.LOGIN)
  public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return getView(request);
  }







  private boolean checkRandomCode(HttpServletRequest request) {
    HttpSession session = request.getSession(true);
    if (session == null) return false;
    String requestCode = request.getParameter("randomCode");
    String sessionCode = (String)session.getAttribute("randomCode");

    return (requestCode != null && sessionCode != null && requestCode.equalsIgnoreCase(sessionCode));
  }

  private static String disposeHtml(String s) {
    if (s == null) return "";
    return freemarker.template.utility.StringUtil.HTMLEnc(s);
  }

  private static String disposeJs(String s) {
    if (s == null) return "";
    return freemarker.template.utility.StringUtil.javaScriptStringEnc(disposeHtml(s));
  }

  private static String disposeAction(String action) {
    if (action == null) return "";
    return disposeJs("/" + StringUtil.trimChar(action, '/'));
  }

  private Map<String, Object> getSysConfig() {
    Map<String, Object> map = new HashMap<>();
    String oss_type = SystemParameter.get("oss.type");
    String cdn = "";
    if ("qiniu".equals(oss_type)) {
      cdn = SystemParameter.get("qiniu.cdn");
    }
    else if ("aliyun".equals(oss_type)) {
      cdn = SystemParameter.get("aliyun.cdn");
    }

    map.put("cdn", cdn);
    return map;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\controller\ManageController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */