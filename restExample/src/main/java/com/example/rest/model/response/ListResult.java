package com.example.rest.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
// 결과가 여러건인 api 를 담는 모델
public class ListResult<T> extends CommonResult {
    private List<T> list;
}
