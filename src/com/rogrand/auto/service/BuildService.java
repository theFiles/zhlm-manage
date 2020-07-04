package com.rogrand.auto.service;

import com.alibaba.fastjson.JSON;
import com.rogrand.auto.domain.Column;
import com.rogrand.auto.domain.Database;
import com.rogrand.auto.domain.EditTable;
import com.rogrand.auto.domain.Table;
import com.rogrand.core.service.BaseService;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


@Service("buildService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class BuildService
  extends BaseService
{
  private String tempDir = "/WEB-INF/template";
  private String outDir = "/autoBuild";

  public Database getStructure() throws Exception {
    BasicDataSource dataSource = (BasicDataSource)this.sqlDao.getDataSource();
    if (dataSource.getDriverClassName().contains("mysql")) {
      return getMysqlStructure(dataSource);
    }

    throw new RuntimeException("不支持" + dataSource.getDriverClassName() + "数据库");
  }


  private Database getMysqlStructure(BasicDataSource dataSource) throws Exception {
    Database database = new Database();
    database.setDriverClassName(dataSource.getDriverClassName());
    database.setUrl(dataSource.getUrl());
    database.setUsername(dataSource.getUsername());
    database.setPassword(dataSource.getPassword());
    List<Table> tableList = this.sqlDao.list("mysql.getTables");
    Properties comments = getComments();
    for (Table table : tableList) {
      List<Column> columnList = this.sqlDao.list("mysql.getColumns", table.getName());
      table.setName(table.getName().toLowerCase());
      setSaveComments(comments, table);
      for (Column column : columnList) {
        column.setName(column.getName().toLowerCase());
        setSaveComments(comments, table, column);
      }
      table.setColumnList(columnList);
    }
    database.setTableList(tableList);
    return database;
  }

  private void setSaveComments(Properties comments, Table table) {
    if (comments.containsKey(table.getName() + ".annotation")) {
      table.setAnnotation(comments.getProperty(table.getName() + ".annotation"));
    }
    if (comments.containsKey(table.getName() + ".subjectmodulename")) {
      table.setSubjectModuleName(comments.getProperty(table.getName() + ".subjectmodulename"));
    }
    if (comments.containsKey(table.getName() + ".buildcurd") && comments.getProperty(table.getName() + ".buildcurd").equals("true")) {
      table.setBuildCURD(Boolean.valueOf(true));
    }
    if (comments.containsKey(table.getName() + ".pageType")) {
      table.setPageType(comments.getProperty(table.getName() + ".pageType"));
    }
    if (comments.containsKey(table.getName() + ".multiple")) {
      table.setMultiple(comments.getProperty(table.getName() + ".multiple"));
    }
    if (comments.containsKey(table.getName() + ".paging")) {
      table.setPaging(comments.getProperty(table.getName() + ".paging"));
    }
    if (comments.containsKey(table.getName() + ".relevanceTables")) {
      String relevanceTables = comments.getProperty(table.getName() + ".relevanceTables");
      table.setRelevanceTables((List) JSON.parseObject(relevanceTables, List.class));
    }
    if (comments.containsKey(table.getName() + ".editTable")) {
      String editTable = comments.getProperty(table.getName() + ".editTable");
      table.setEditTable((EditTable) JSON.parseObject(editTable, EditTable.class));
    }
  }

  private void setSaveComments(Properties comments, Table table, Column column) {
    if (comments.containsKey(table.getName() + ".column." + column.getName() + ".annotation")) {
      column.setAnnotation(comments.getProperty(table.getName() + ".column." + column.getName() + ".annotation"));
    }
    if (comments.containsKey(table.getName() + ".column." + column.getName() + ".type")) {
      column.setType(comments.getProperty(table.getName() + ".column." + column.getName() + ".type"));
    }
    if (comments.containsKey(table.getName() + ".column." + column.getName() + ".params")) {
      String params = comments.getProperty(table.getName() + ".column." + column.getName() + ".params");
      column.setParams((Map) JSON.parseObject(params, HashMap.class));
    }
    if (comments.containsKey(table.getName() + ".column." + column.getName() + ".search")) {
      column.setSearch(comments.getProperty(table.getName() + ".column." + column.getName() + ".search"));
    }
  }

  private Properties getComments() throws SQLException, IOException {
    Properties properties = new Properties();
    File file = new File(this.servletContext.getRealPath(this.tempDir + "/comments.properties"));
    InputStream inputStream = null;
    if (file.exists()) {
      try {
        inputStream = new FileInputStream(file);
        properties.load(inputStream);
      }
      catch (IOException e) {
        this.logger.error(e.getMessage());
      } finally {

        if (inputStream != null) {
          try {
            inputStream.close();
          }
          catch (IOException e) {
            this.logger.error(e.getMessage());
          }
        }
      }
    }










































    return properties;
  }

  public static void main(String[] args) {}
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\auto\service\BuildService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */