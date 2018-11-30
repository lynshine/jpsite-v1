package com.mty.jpsite.annotation;

import com.mty.jpsite.configuration.swagger.SwaggerConfiguration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 开启swagger文档自动生成功能
 * 可以在JpsiteApplication类上添加
 * 和resource/META-INF/resource/spring.factories 指定EnableAutoConfiguration效果一样，二者选一即可
 */

/**注解将会由虚拟机保留,以便它可以在运行时通过反射读取*/
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
/**说明了Annotation所修饰的对象范围，TYPE:用于描述类、接口(包括注解类型) 或enum声明*/
@Target(value = {java.lang.annotation.ElementType.TYPE})
/**注解表明这个注解应该被 javadoc工具记录*/
@Documented
/**这个注解帮助我们将多个配置文件导入到单个主配置中，以避免将所有配置写在一个配置中*/
@Import(SwaggerConfiguration.class)
/**组合注解,包含EnableSwagger2注解的所有功能*/
@EnableSwagger2
public @interface EnableMySwagger {
}
