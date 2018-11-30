package com.mty.jpsite.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 带有此注解的方法可实现Zookeeper加锁
 */

/**用于描述方法*/
@Target(value = ElementType.METHOD)
/**注解将会由虚拟机保留,以便它可以在运行时通过反射读取*/
@Retention(RetentionPolicy.RUNTIME)
public @interface AcquireZk {
    String path() default "";

    String type() default "";
}
