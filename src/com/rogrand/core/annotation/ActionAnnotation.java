package com.rogrand.core.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ActionAnnotation {
  String name() default "";

  String group() default "";

  boolean log() default false;

  Type check() default Type.LOGIN_GROUP;

  public enum Type {
    NO, LOGIN, LOGIN_GROUP;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\annotation\ActionAnnotation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */