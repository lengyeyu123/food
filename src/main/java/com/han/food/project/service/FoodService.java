package com.han.food.project.service;

import cn.hutool.http.HttpUtil;
import com.han.food.framework.web.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class FoodService {

    public R<?> getDetail(String barcode) {
        Map<String, Object> params = new HashMap<>();
        params.put("barcode", barcode);
        params.put("app_id", "hrlqkumrq2numnrr");
        params.put("app_secret", "SC9GbXVvNVBNUEZ1WGlKZDQ1Nld5Zz09");
        String s = HttpUtil.get("https://www.mxnzp.com/api/barcode/goods/details", params);
        System.out.println(s);
        return R.success(s);
    }
}
