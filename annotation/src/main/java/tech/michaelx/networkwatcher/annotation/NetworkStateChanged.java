package tech.michaelx.networkwatcher.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @since 2019-10-16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NetworkStateChanged {
    // app刚启动后的第一个监听器是否需要提醒网络
    boolean notifyOnAppStart() default true;
}
