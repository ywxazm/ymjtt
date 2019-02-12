package com.ymjtt.common.log;

import java.lang.annotation.*;

/**
 * @auther ywx
 * @date 2019/1/10 15:28
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MethodLog {
}
