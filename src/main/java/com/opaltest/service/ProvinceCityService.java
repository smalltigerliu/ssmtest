package com.opaltest.service;

import com.opaltest.domain.City;
import com.opaltest.domain.Province;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProvinceCityService {

    List<Province> queryAllProvince();

    int addCity(City city);

    List<City> queryCityByProvinceId(Integer provinceId);
}
