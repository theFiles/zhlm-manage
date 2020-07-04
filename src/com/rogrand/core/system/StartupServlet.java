package com.rogrand.core.system;

import com.rogrand.core.util.SpringContextHolder;
import com.rogrand.sys.domain.Sysconfig;
import com.rogrand.sys.service.SysconfigService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.List;


public class StartupServlet
  extends HttpServlet
{
  private static final long serialVersionUID = -8888709767612737448L;
  private Log logger = LogFactory.getLog(getClass());







  public void init() throws ServletException {
    try {
      ServletContext application = getServletContext();
      String systemName = SystemParameter.get("systemName");
      if (SystemParameter.get("subSystemName") != null) {
        systemName = SystemParameter.get("subSystemName");
      }

      loadSysConfigParams();

      System.out.println(application.getRealPath("/"));
      System.out.println("**************************************************");
      System.out.println("             " + systemName + "初始化完成！");
      System.out.println("**************************************************");
    }
    catch (Exception e) {
      this.logger.error("exception class:" + e.getClass().getName() + "\nexception message:" + e.getMessage());
      throw new ServletException(e);
    }
  }




  private void loadSysConfigParams() throws Exception {
    SysconfigService service = (SysconfigService)SpringContextHolder.getBean("SysconfigService");

    List<Sysconfig> list = service.list();
    if (list != null)
      for (Sysconfig config : list)
        SystemParameter.set(config.getConfigkey(), config.getConfigvalue());
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\system\StartupServlet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */