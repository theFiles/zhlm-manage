package com.rogrand.core.service;

import com.rogrand.core.dao.SqlDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;


public class BaseService
  implements ServletContextAware
{
  protected ServletContext servletContext;
  @Autowired
  @Qualifier("sqlDao")
  protected SqlDao sqlDao;
  @Autowired
  @Qualifier("pageService")
  protected AbstractPageService pageService;
  protected Log logger = LogFactory.getLog(getClass());

  public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  public WebApplicationContext getWebApplicationContext() {
    return WebApplicationContextUtils.getWebApplicationContext(this.servletContext);
  }

  public SqlDao getSqlDao() {
    return this.sqlDao;
  }

  public void setSqlDao(SqlDao sqlDao) {
    this.sqlDao = sqlDao;
  }

  public AbstractPageService getPageService() {
    return this.pageService;
  }

  public void setPageService(AbstractPageService pageService) {
    this.pageService = pageService;
  }


  public <T> T getBean(String id) {
    return (T)getWebApplicationContext().getBean(id);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\service\BaseService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */