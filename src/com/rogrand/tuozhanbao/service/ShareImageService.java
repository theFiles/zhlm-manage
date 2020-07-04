package com.rogrand.tuozhanbao.service;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;
import com.rogrand.tuozhanbao.domain.ShareImage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service("ShareImageService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class ShareImageService
  extends BaseService
{
  public ShareImage query(String id) throws Exception {
    PageParam param = new PageParam();
    param.put("id", id);
    return (ShareImage)this.sqlDao.query("ape_share_image.pageList", param);
  }







  public PageResult pageList(PageParam param) throws Exception {
    param.setCountSql("ape_share_image.pageCount");
    param.setRecordSql("ape_share_image.pageList");
    PageResult pageResult = this.pageService.pageQuery(param);
    return pageResult;
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void create(ShareImage shareImage) throws Exception {
    this.sqlDao.create("ape_share_image.create", shareImage);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void update(ShareImage shareImage) throws Exception {
    this.sqlDao.update("ape_share_image.update", shareImage);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String[] ids) throws Exception {
    this.sqlDao.delete("ape_share_image.delete", ids);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String id) throws Exception {
    String[] ids = { id };
    this.sqlDao.delete("ape_share_image.delete", ids);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\service\ShareImageService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */