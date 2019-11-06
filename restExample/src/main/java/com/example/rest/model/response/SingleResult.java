package com.example.rest.model.response;

import lombok.Getter;
import lombok.Setter;

// 결과가 단일건인 api 를 담는 모델
@Getter
@Setter
public class SingleResult<T> extends CommonResult {
    private T data;
}
