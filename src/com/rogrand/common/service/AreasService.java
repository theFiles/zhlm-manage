package com.rogrand.common.service;

import com.rogrand.common.domain.Areas;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("AreasService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class AreasService
  extends BaseService
{
  public Areas query(String id) throws Exception {
    PageParam param = new PageParam();
    param.put("id", id);
    return (Areas)this.sqlDao.query("common_areas.pageList", param);
  }







  public List<Areas> list(PageParam param) throws Exception {
    return this.sqlDao.list("common_areas.pageList", param);
  }







  public PageResult pageList(PageParam param) throws Exception {
    param.setCountSql("common_areas.pageCount");
    param.setRecordSql("common_areas.pageList");
    return this.pageService.pageQuery(param);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String create(Areas areas) throws Exception {
    this.sqlDao.create("common_areas.create", areas);
    return "1";
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String update(Areas areas) throws Exception {
    this.sqlDao.update("common_areas.update", areas);
    return "1";
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(Areas areas) throws Exception {
    this.sqlDao.delete("common_areas.delete", areas);
    return "1";
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(String id) throws Exception {
    return delete(new Areas(id));
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(String[] ids) throws Exception {
    for (String id : ids) {
      delete(id);
    }
    return "1";
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\common\service\AreasService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */