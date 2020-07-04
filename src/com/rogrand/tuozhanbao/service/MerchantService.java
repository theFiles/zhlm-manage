package com.rogrand.tuozhanbao.service;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;
import com.rogrand.tuozhanbao.domain.Merchant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service("MerchantService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class MerchantService
  extends BaseService
{
  public Merchant query(String id) throws Exception {
    PageParam param = new PageParam();
    param.put("id", id);
    return (Merchant)this.sqlDao.query("ape_merchant.pageList", param);
  }







  public PageResult pageList(PageParam param) throws Exception {
    param.setCountSql("ape_merchant.pageCount");
    param.setRecordSql("ape_merchant.pageList");
    PageResult pageResult = this.pageService.pageQuery(param);
    return pageResult;
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void create(Merchant merchant) throws Exception {
    this.sqlDao.create("ape_merchant.create", merchant);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void update(Merchant merchant) throws Exception {
    this.sqlDao.update("ape_merchant.update", merchant);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String[] ids) throws Exception {
    this.sqlDao.delete("ape_merchant.delete", ids);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String id) throws Exception {
    String[] ids = { id };
    this.sqlDao.delete("ape_merchant.delete", ids);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\service\MerchantService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */