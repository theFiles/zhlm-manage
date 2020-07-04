package com.rogrand.common.service;

import com.alibaba.fastjson.JSON;
import com.rogrand.common.domain.Cities;
import com.rogrand.common.domain.Provinces;
import com.rogrand.core.domain.PageParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("CommonService")
public class CommonService
{
  @Autowired
  @Qualifier("ProvincesService")
  private ProvincesService provincesService;
  @Autowired
  @Qualifier("CitiesService")
  private CitiesService citiesService;
  @Autowired
  @Qualifier("AreasService")
  private AreasService areasService;

  public String getProvinceComoboBoxDate() throws Exception {
    List<Provinces> list = new ArrayList<>();
    Provinces provinces = new Provinces();
    provinces.setProvince("全国");
    list.add(provinces);

    List<Provinces> provincesList = this.provincesService.list(null);
    list.addAll(provincesList);

    return null;
  }





  public String getProvinceTreeDate(String nodeid) throws Exception {
    List<Map<String, Object>> treeList = new ArrayList<>();

    if (StringUtils.isEmpty(nodeid)) {
      List<Provinces> provincesList = this.provincesService.list(null);
      for (int i = 0; i < provincesList.size(); i++) {
        Provinces provinces = provincesList.get(i);
        Map<String, Object> provinceNode = new HashMap<>();
        provinceNode.put("id", provinces.getProvinceid());
        provinceNode.put("text", provinces.getProvince());
        provinceNode.put("state", "closed");
        treeList.add(provinceNode);
      }
    } else {

      PageParam pageParam = new PageParam();
      pageParam.put("provinceid", nodeid);
      List<Cities> citiesList = this.citiesService.list(pageParam);
      List<Map<String, Object>> provinceChildren = new ArrayList<>();
      for (int j = 0; j < citiesList.size(); j++) {
        Cities cities = citiesList.get(j);
        Map<String, Object> cityNode = new HashMap<>();
        cityNode.put("id", cities.getCityid());
        cityNode.put("text", cities.getCity());
        cityNode.put("state", "open");
        treeList.add(cityNode);
      }
    }

    return JSON.toJSONString(treeList);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\common\service\CommonService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */