package com.rogrand.sys.controller;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.sys.domain.Org;
import com.rogrand.sys.domain.User;
import com.rogrand.sys.service.OrgService;
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


@Controller("sysOrgController")
@RequestMapping({"/sys/org/*"})
public class OrgController
  extends BaseController
{
  @Autowired
  @Qualifier("sysOrgService")
  private OrgService orgService;

  @ActionAnnotation(name = "机构树", group = "查询")
  public void tree(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<Org> orgs = this.orgService.tree(new Org(), getLoginUser(request));
    responseJson(response, ResponseCode.SUCCESS, orgs);
  }

  @ActionAnnotation(name = "机构详细查询", group = "查询")
  public void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, Object> map = new HashMap<>();
    User user = getLoginUser(request);
    Org org1 = new Org(request.getParameter("so_id"));
    org1.setSap_id(user.getSap_id());
    Org org = this.orgService.query(org1);
    map.put("org", org);
    responseJson(response, ResponseCode.SUCCESS, map);
  }

  @ActionAnnotation(name = "机构保存", group = "添加", log = true)
  public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Org org = (Org)BeanUtil.wrapBean(Org.class, request.getParameter("org"));
    User user = getLoginUser(request);
    if (StringUtils.isEmpty(org.getSo_id())) {
      org.setSap_id(user.getSap_id());
      this.orgService.create(org);
    } else {
      this.orgService.update(org);
    }
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "机构删除", group = "删除", log = true)
  public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Org org = new Org(request.getParameter("so_id"));
    org.setSap_id(getLoginUser(request).getSap_id());
    this.orgService.delete(org);
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  @ActionAnnotation(name = "机构排序", group = "排序")
  public ModelAndView order(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return getView(request);
  }

  @ActionAnnotation(name = "机构排序保存", group = "排序", log = true)
  public ModelAndView orderSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return getView(request);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\controller\OrgController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */