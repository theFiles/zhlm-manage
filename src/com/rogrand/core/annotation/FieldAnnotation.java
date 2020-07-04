package com.rogrand.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FieldAnnotation {
  String comment();
  
  boolean hasNextHead() default false;
  
  int colspan() default 1;
  
  boolean empty() default true;
  
  int len() default 0;
  
  int scale() default 0;
  
  boolean exp() default true;
  
  int expIndex() default 0;
  
  String expFormat() default "";
  
  boolean imp() default true;
  
  boolean pk() default false;
  
  Class<?> relation() default Object.class;
  
  String fk() default "";
  
  String show() default "";
  
  String sheet() default "";
  
  String defaultValue() default "";
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\annotation\FieldAnnotation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */