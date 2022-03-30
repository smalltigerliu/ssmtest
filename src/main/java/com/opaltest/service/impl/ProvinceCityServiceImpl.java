package com.opaltest.service.impl;

import com.opaltest.dao.CityDao;
import com.opaltest.dao.ProvinceDao;
import com.opaltest.domain.City;
import com.opaltest.domain.Province;
import com.opaltest.service.ProvinceCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProvinceCityServiceImpl implements ProvinceCityService {

    @Resource
    private ProvinceDao pdao;
    @Autowired
    private CityDao cdao;

    @Override
    @Transactional
    public List<Province> queryAllProvince() {

        return pdao.selectProvinceList();
    }

    @Override
    public int addCity(City city) {
        int rows = cdao.insertCity(city);
        return rows;
    }

    @Override
    public List<City> queryCityByProvinceId(Integer provinceId) {

        return cdao.selectCityByProvinceId(provinceId);
    }
}
