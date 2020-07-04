package com.rogrand.core.system;

import com.rogrand.core.util.WebUtil;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class WebFilter
  extends CharacterEncodingFilter
{
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    super.doFilterInternal(request, response, filterChain);
  }




  protected void initFilterBean() throws ServletException {
    WebUtil.setServletContext(getServletContext());
    super.initFilterBean();
  }





  public void destroy() {
    super.destroy();
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\system\WebFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */