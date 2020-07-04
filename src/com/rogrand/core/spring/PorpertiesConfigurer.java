package com.rogrand.core.spring;

import com.rogrand.core.system.SystemParameter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;


public class PorpertiesConfigurer
  extends PropertyPlaceholderConfigurer
{
  protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
    super.processProperties(beanFactoryToProcess, props);
    for (Object key : props.keySet()) {
      String keyStr = key.toString();
      String value = props.getProperty(keyStr);
      SystemParameter.set(keyStr, value);
    }
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\spring\PorpertiesConfigurer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */