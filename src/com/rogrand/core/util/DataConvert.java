package com.rogrand.core.util;

import com.rogrand.core.domain.TreeConfig;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataConvert
{
  public static String toDataGridData(List list) {
    Map<String, Object> map = new HashMap<>();

    if (list == null || list.size() == 0) {
      map.put("total", Integer.valueOf(0));
      map.put("rows", Boolean.valueOf(false));
    } else {

      map.put("total", Integer.valueOf(list.size()));
      map.put("rows", list);
    }
    return BeanUtil.toJsonString(map);
  }










  public static List toSelectData(List list, String fields) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    return (fields == null) ? list : toSelectData(list, fields.split(","));
  }










  public static List toSelectData(List list, String[] fields) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    if (list == null || list.size() == 0) return null;
    if (fields != null && fields.length > 0) {
      List<Map<String, String>> data = new ArrayList<>();
      for (Object obj : list) {
        Map<String, String> model = new HashMap<>();
        for (String field : fields)
        {
          model.put(field.trim(), BeanUtils.getProperty(obj, field.trim()));
        }
        data.add(model);
      }
      return data;
    }

    return list;
  }


  public static String toTreeData(List list, TreeConfig config) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    return toTreeData(list, config, true);
  }

  public static String toTreeData(List list, TreeConfig config, boolean showRoot) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    if (showRoot) {
      Map<String, Object> tree = new HashMap<>();
      tree.put("id", config.getRootID());
      tree.put("text", config.getRootText());
      if (list != null && list.size() > 0) {
        List<Map<String, Object>> childTree = StringUtil.isEmpty(config.getFieldChild()) ? createTreeData(list, config, config.getRootID(), 0) : createTreeData(list, config, config.getRootID());



        if (childTree != null && childTree.size() > 0) {
          tree.put("children", childTree);
        }
      }
      return BeanUtil.toJsonString(new Object[] { tree });
    }

    if (list != null && list.size() > 0) {
      List<Map<String, Object>> childTree = StringUtil.isEmpty(config.getFieldChild()) ? createTreeData(list, config, config.getRootID(), 0) : createTreeData(list, config, config.getRootID());


      return (childTree != null && childTree.size() > 0) ? BeanUtil.toJsonString(childTree) : "[]";
    }

    return "[]";
  }



  private static List<Map<String, Object>> createTreeData(List list, TreeConfig config, String parentID) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    List<Map<String, Object>> treeList = new ArrayList<>();

    for (Object obj : list) {

      if (parentID.equals(BeanUtils.getProperty(obj, config.getFieldParentID()))) {

        Map<String, Object> tree = new HashMap<>();
        tree.put("id", BeanUtils.getProperty(obj, config.getFieldID()));
        tree.put("text", BeanUtils.getProperty(obj, config.getFieldText()));
        if (!StringUtil.isEmpty(config.getFieldChecked()) &&
          BeanUtils.getProperty(obj, config.getFieldChecked()).equals("1")) {
          tree.put("checked", Boolean.valueOf(true));
        }

        if (config.getFieldAttributes() != null && (config.getFieldAttributes()).length > 0) {
          Map<String, Object> attributeMap = new HashMap<>();
          for (String attribute : config.getFieldAttributes()) {
            attributeMap.put(attribute.trim(), BeanUtils.getProperty(obj, attribute.trim()));
          }
          tree.put("attributes", attributeMap);
        }
        if (BeanUtils.getProperty(obj, config.getFieldChild()).equals("1")) {
          List<Map<String, Object>> childTree = createTreeData(list, config, BeanUtils.getProperty(obj, config.getFieldID()));
          if (childTree != null && childTree.size() > 0) {
            tree.put("children", childTree);
            if (!StringUtil.isEmpty(config.getState())) {
              tree.put("state", config.getState());
            }
          }
        }
        treeList.add(tree);
      }
    }



    return treeList;
  }

  private static List<Map<String, Object>> createTreeData(List list, TreeConfig config, String parentID, int start) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    List<Map<String, Object>> treeList = new ArrayList<>();

    for (int i = start; i < list.size(); i++) {
      Object obj = list.get(i);

      if (parentID.equals(BeanUtils.getProperty(obj, config.getFieldParentID()))) {

        Map<String, Object> tree = new HashMap<>();
        tree.put("id", BeanUtils.getProperty(obj, config.getFieldID()));
        tree.put("text", BeanUtils.getProperty(obj, config.getFieldText()));
        if (!StringUtil.isEmpty(config.getFieldChecked()) &&
          BeanUtils.getProperty(obj, config.getFieldChecked()).equals("1")) {
          tree.put("checked", Boolean.valueOf(true));
        }

        if (config.getFieldAttributes() != null && (config.getFieldAttributes()).length > 0) {
          Map<String, Object> attributeMap = new HashMap<>();
          for (String attribute : config.getFieldAttributes()) {
            attributeMap.put(attribute.trim(), BeanUtils.getProperty(obj, attribute.trim()));
          }
          tree.put("attributes", attributeMap);
        }
        if (i + 1 < list.size()) {
          List<Map<String, Object>> childTree = createTreeData(list, config, BeanUtils.getProperty(obj, config.getFieldID()), i + 1);
          if (childTree != null && childTree.size() > 0) {
            tree.put("children", childTree);
            if (!StringUtil.isEmpty(config.getState())) {
              tree.put("state", config.getState());
            }
          }
        }
        treeList.add(tree);
      }
    }



    return treeList;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\DataConvert.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */