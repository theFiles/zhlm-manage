package com.rogrand.core.service;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;


@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class PageService
  extends AbstractPageService
  implements ServletContextAware
{
  @Autowired
  @Qualifier("mysqlPageService")
  AbstractPageService mysqlPageService;
  protected ServletContext servletContext;
  private String driverClassName;

  public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  public void setDriverClassName(String driverClassName) {
    this.driverClassName = driverClassName;
  }






  private AbstractPageService getService() {
    WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(this.servletContext);
    if (this.driverClassName.contains("mysql")) {
      return (AbstractPageService)webApplicationContext.getBean("mysqlPageService");
    }
    return null;
  }


  public PageResult pageQuery(PageParam pageParam) throws Exception {
    return this.mysqlPageService.pageQuery(pageParam);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\service\PageService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */