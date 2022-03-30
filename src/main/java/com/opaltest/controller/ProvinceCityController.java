package com.opaltest.controller;

import com.opaltest.domain.City;
import com.opaltest.domain.Province;
import com.opaltest.service.ProvinceCityService;
import com.opaltest.vo.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ProvinceCityController {

    @Resource
    private ProvinceCityService service;

/*
    @RequestMapping("/queryProvince.do")
    @ResponseBody
    public List<Province> queryProvinces(){
        return service.queryAllProvince();
    }
*/

    /**
     * 查询所以的省份
     * @return
     */
    @RequestMapping("/queryProvince.do")
    @ResponseBody
    public CommonResult queryProvinces(){
        List<Province> list = service.queryAllProvince();

        CommonResult cr = new CommonResult(1, "查询没有结果", "");
        if(list != null && list.size()>0) {
            cr = new CommonResult(0, "查询成功", list);
        }
        return cr;
    }

    @RequestMapping("/city/addCity.do")
    @ResponseBody
    public CommonResult addCity(City city) {
        int rows = service.addCity(city);
        CommonResult cr = new CommonResult();
        if(rows >0 ) {
            cr.setCode(0);
            cr.setMsg("添加城市成功！"+city.getName());
            cr.setData(city);
        }else {
            cr.setCode(1);
            cr.setMsg("添加成功失败！");
            cr.setData("");
        }
        return cr;
    }

    @RequestMapping("/city/queryCity.do")
    @ResponseBody
    public CommonResult queryCity(Integer pid) {
        List<City> cityList = service.queryCityByProvinceId(pid);
        CommonResult cr = new CommonResult();
        if(cityList != null && cityList.size() >0) {
            cr.setCode(0);
            cr.setMsg("查询出"+cityList.size()+"个成功");
            cr.setData(cityList);
        }else {
            cr.setCode(1);
            cr.setMsg("查询无结果！");
            cr.setData("");
        }
        return cr;
    }

}
