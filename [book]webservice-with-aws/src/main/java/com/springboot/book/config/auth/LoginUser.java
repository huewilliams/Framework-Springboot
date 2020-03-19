package com.springboot.book.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
// @Target() : 어노테이션이 생성될 수 있는 위치를 지정함
// PARAMETER : 메소드의 파라미터로 선언된 객체에서만 사용가능
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { // @interface : 이 파일을 어노테이션 클래스로 지정함.
}
