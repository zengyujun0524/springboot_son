package com.example.springboot_son.ann;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireToken {


}
