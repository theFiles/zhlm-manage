package com.rogrand.sys.service;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.exception.ServiceException;
import com.rogrand.core.service.BaseService;
import com.rogrand.sys.domain.Org;
import com.rogrand.sys.domain.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("sysOrgService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class OrgService
  extends BaseService
{
  public Org query(Long so_id) throws Exception {
    return query(new Org(so_id));
  }

  public Org query(String so_id) throws Exception {
    return query(new Org(so_id));
  }

  public Org query(Org org) throws Exception {
    return (Org)this.sqlDao.query("sys_org.query", org);
  }

  public List<Org> list(Org org) throws Exception {
    return this.sqlDao.list("sys_org.query", org);
  }

  public List<Org> tree(Org org, User user) throws Exception {
    org.setSap_id(user.getSap_id());
    org.setOrder("a.so_parentid, a.so_id");
    List<Org> orgs = list(org);

    List<Org> parentOrgs = new ArrayList<>();
    for (int i = 0; i < orgs.size(); i++) {
      Org org1 = orgs.get(i);
      if ("0".equals(org1.getSo_parentid())) {
        org1.setChild_orgs(queryChildOrg(org1.getSo_id(), orgs));
        parentOrgs.add(org1);
      }
    }
    return parentOrgs;
  }

  private List<Org> queryChildOrg(String so_id, List<Org> orgs) {
    List<Org> orgList = new ArrayList<>();
    for (int i = 0; i < orgs.size(); i++) {
      Org org = orgs.get(i);
      if (so_id.equals(org.getSo_parentid())) {
        org.setChild_orgs(queryChildOrg(org.getSo_id(), orgs));
        orgList.add(org);
      }
    }
    return orgList;
  }






































  public PageResult pageList(PageParam param) throws Exception {
    param.setCountSql("sys_org.pageCount");
    param.setRecordSql("sys_org.pageList");







    return this.pageService.pageQuery(param);
  }

  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void create(Org org) throws Exception {
    if (StringUtils.isEmpty(org.getSo_parentid())) org.setSo_parentid("0");
    org.setMode("so_code");
    Org check = query(org);
    if (check != null) throw new ServiceException("2000", "机构编码重复");
    this.sqlDao.create("sys_org.create", org);
  }

  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void update(Org org) throws Exception {
    org.setMode("so_code");
    Org check = query(org);
    if (check != null && !org.getSo_id().equals(check.getSo_id())) throw new ServiceException("2000", "机构编码重复");
    this.sqlDao.update("sys_org.update", org);
  }

  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(Org org) throws Exception {
    Org check = query(org);
    if (check.getSo_used().equals("1")) return "2";
    this.sqlDao.delete("sys_org.delete", org);
    return "1";
  }

  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String so_id) throws Exception {
    delete(new Org(so_id));
  }

  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(String[] so_ids) throws Exception {
    for (String so_id : so_ids) {
      delete(so_id);
    }
    return "1";
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\service\OrgService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */