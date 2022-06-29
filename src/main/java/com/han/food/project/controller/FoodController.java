package com.han.food.project.controller;

import com.han.food.framework.web.domain.R;
import com.han.food.project.service.FoodService;
import com.han.food.project.vo.req.GetDetailReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/food")
@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;

    /**
     * 06917878036526
     *
     * @param reqVo
     * @return
     */
    @PostMapping("/getDetail")
    public R<?> getDetail(@RequestBody @Validated GetDetailReqVo reqVo) {
        return foodService.getDetail(reqVo.getBarcode());
    }

}
