package com.rogrand.sys.service;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.exception.ServiceException;
import com.rogrand.core.security.MD5;
import com.rogrand.core.service.BaseService;
import com.rogrand.core.util.DateUtil;
import com.rogrand.core.util.StringUtil;
import com.rogrand.sys.domain.Role;
import com.rogrand.sys.domain.User;
import com.rogrand.sys.domain.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("sysUserService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class UserService
  extends BaseService
{
  public User query(String su_id) throws Exception {
    return query(new User(su_id));
  }

  public User query(Long su_id) throws Exception {
    return query(new User(su_id));
  }

  public User query(User user) throws Exception {
    return (User)this.sqlDao.query("sys_user.query", user);
  }

  public PageResult pageList(PageParam param) throws Exception {
    param.setCountSql("sys_user.pageCount");
    param.setRecordSql("sys_user.pageList");
    PageResult pageResult = this.pageService.pageQuery(param);
    return pageResult;
  }

  public List<Role> listRole(User user) throws Exception {
    return this.sqlDao.list("sys_user_role.queryRole", user);
  }









  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String create(User user) throws Exception {
    user.setMode("su_code");
    User check = (User)this.sqlDao.query("sys_user.query", user);
    if (check != null)
      return "2";
    user.setSu_admin("0");
    user.setSu_random(StringUtil.getRandomChar(10));
    user.setSu_password(MD5.getEncrypt("888888"));
    user.setSu_add_time(DateUtil.getNow());
    user.setSu_login_count(0);
    this.sqlDao.create("sys_user.create", user);
    String sr_id = user.getSr_id();
    if (sr_id != null) {
      UserRole userRole = new UserRole();
      userRole.setSu_id(user.getSu_id());
      String[] ids = sr_id.split(",");
      for (String id : ids) {
        userRole.setSr_id(id);
        this.sqlDao.create("sys_user_role.create", userRole);
      }
    }
    return "1";
  }









  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String update(User user) throws Exception {
    user.setMode("su_code");
    User check = (User)this.sqlDao.query("sys_user.query", user);
    if (check != null && !check.getSu_id().equals(user.getSu_id()))
      return "2";
    this.sqlDao.update("sys_user.update", user);
    this.sqlDao.delete("sys_user_role.delete", new UserRole("su_id", user.getSu_id()));
    String sr_id = user.getSr_id();
    if (sr_id != null) {
      UserRole userRole = new UserRole();
      userRole.setSu_id(user.getSu_id());
      String[] ids = sr_id.split(",");
      for (String id : ids) {
        userRole.setSr_id(id);
        this.sqlDao.create("sys_user_role.create", userRole);
      }
    }
    return "1";
  }









  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(String su_id) throws Exception {
    return delete(new User(su_id));
  }

  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(User user) throws Exception {
    User check = (User)this.sqlDao.query("sys_user.query", user);
    if (check.getSu_admin().equals("1")) throw new ServiceException("2000", "系统管理员不能删除");

    this.sqlDao.delete("sys_user_role.delete", new UserRole("su_id", user.getSu_id()));
    this.sqlDao.delete("sys_user.delete", user);
    return "1";
  }

  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String updatePassword(User user) throws Exception {
    user.putFields("su_id,su_password");
    this.sqlDao.update("sys_user.update", user);
    return "1";
  }








  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public boolean updatePersonInfo(User user) throws Exception {
    return (this.sqlDao.update("sys_user.update", user) == 1);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\service\UserService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */