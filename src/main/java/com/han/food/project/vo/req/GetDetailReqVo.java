package com.han.food.project.vo.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class GetDetailReqVo {

    @NotBlank
    private String barcode;

}
