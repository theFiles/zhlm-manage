package com.rogrand.core.controller;

import com.rogrand.core.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.*;


public class MultiViewResolver
  extends WebApplicationObjectSupport
  implements ViewResolver, Ordered
{
  private Map[] viewResolvers;
  private int order = 0;

  public void setOrder(int order) {
    this.order = order;
  }

  public int getOrder() {
    return this.order;
  }



  public View resolveViewName(String s, Locale locale) throws Exception {
    if (s.startsWith("forward:") || s.startsWith("redirect:")) {
      return null;
    }

    int index = s.lastIndexOf('.');
    if (index == -1) {
      String fileList = "";
      for (Map resolver : this.viewResolvers) {
        String prefix = (String)resolver.get("prefix");
        String suffix = (String)resolver.get("suffix");
        String filename = "";
        if (StringUtil.isEmpty(prefix)) {
          filename = "/" + trimChar(s, '/') + suffix;
        } else {

          filename = "/" + trimChar(prefix, '/') + "/" + trimChar(s, '/') + suffix;
        }
        fileList = fileList + "\n" + filename;
        File viewFile = new File(getServletContext().getRealPath(filename));
        if (viewFile.exists() && viewFile.isFile()) {
          return ((ViewResolver)resolver.get("viewResolver")).resolveViewName(s, locale);
        }
      }
      throw new FileNotFoundException("没有找到模板文件" + fileList + ((this.viewResolvers.length > 1) ? "\n其中之一" : ""));
    }

    String viewSuffix = s.substring(index);
    for (Map resolver : this.viewResolvers) {
      String suffix = (String)resolver.get("suffix");
      if (suffix.equals(viewSuffix)) {
        return ((ViewResolver)resolver.get("viewResolver")).resolveViewName(s.substring(0, index), locale);
      }
    }
    throw new FileNotFoundException("没有找到模板文件" + s);
  }




  protected void initApplicationContext(ApplicationContext context) {
    super.initApplicationContext(context);
    Map beans = BeanFactoryUtils.beansOfTypeIncludingAncestors((ListableBeanFactory)getWebApplicationContext(), UrlBasedViewResolver.class, true, false);
    if (!beans.isEmpty()) {
      List<UrlBasedViewResolver> resolverList = new ArrayList<>(beans.values());
      this.viewResolvers = (Map[])new HashMap[resolverList.size()];
      Collections.sort(resolverList, (Comparator<? super UrlBasedViewResolver>)new OrderComparator());
      for (int i = 0; i < resolverList.size(); i++) {
        UrlBasedViewResolver item = resolverList.get(i);
        try {
          Map<String, Object> map = new HashMap<>();
          Method method = BeanUtils.findMethod(item.getClass(), "getPrefix", null);
          method.setAccessible(true);
          map.put("prefix", method.invoke(item, new Object[0]));
          method = BeanUtils.findMethod(item.getClass(), "getSuffix", null);
          method.setAccessible(true);
          map.put("suffix", method.invoke(item, new Object[0]));
          map.put("viewResolver", item);
          this.viewResolvers[i] = map;
        } catch (Exception e) {
          this.logger.error(e.getMessage());
        }
      }
    }
  }

  private static String trimChar(String s, char c) {
    return StringUtils.trimTrailingCharacter(StringUtils.trimLeadingCharacter(s, c), c);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\controller\MultiViewResolver.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */