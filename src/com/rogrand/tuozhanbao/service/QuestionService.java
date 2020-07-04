package com.rogrand.tuozhanbao.service;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;
import com.rogrand.tuozhanbao.domain.Question;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service("QuestionService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class QuestionService
  extends BaseService
{
  public Question query(String id) throws Exception {
    PageParam param = new PageParam();
    param.put("id", id);
    return (Question)this.sqlDao.query("ape_question.pageList", param);
  }







  public PageResult pageList(PageParam param) throws Exception {
    param.setCountSql("ape_question.pageCount");
    param.setRecordSql("ape_question.pageList");
    PageResult pageResult = this.pageService.pageQuery(param);
    return pageResult;
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void create(Question question) throws Exception {
    this.sqlDao.create("ape_question.create", question);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void update(Question question) throws Exception {
    this.sqlDao.update("ape_question.update", question);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String[] ids) throws Exception {
    this.sqlDao.delete("ape_question.delete", ids);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String id) throws Exception {
    String[] ids = { id };
    this.sqlDao.delete("ape_question.delete", ids);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\service\QuestionService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */