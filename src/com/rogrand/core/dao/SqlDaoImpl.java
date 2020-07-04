package com.rogrand.core.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.mapping.parameter.ParameterMap;
import com.ibatis.sqlmap.engine.mapping.sql.Sql;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


@Repository("sqlDao")
public class SqlDaoImpl
  extends SqlMapClientTemplate
  implements SqlDao
{
  private Log logger = LogFactory.getLog(getClass());
  private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


  @Override
  @Autowired
  public void setSqlMapClient(@Qualifier("sqlMapClient") SqlMapClient sqlMapClient) {
    super.setSqlMapClient(sqlMapClient);
  }


  @Override
  @Autowired
  public void setDataSource(@Qualifier("dataSource") DataSource dataSource) {
    super.setDataSource(dataSource);
  }












  public Map query(String sqlID, Object object, String keyProperty) throws DataAccessException {
    printSql(sqlID, object);
    return queryForMap(sqlID, object, keyProperty);
  }













  public Map query(String sqlID, Object object, String keyProperty, String valueProperty) throws DataAccessException {
    printSql(sqlID, object);
    return queryForMap(sqlID, object, keyProperty, valueProperty);
  }









  public <T> T query(String sqlID, Object object) throws DataAccessException {
    printSql(sqlID, object);
    return (T)queryForObject(sqlID, object);
  }









  public <T> List<T> list(String sqlID, Object object) throws DataAccessException {
    printSql(sqlID, object);
    return queryForList(sqlID, object);
  }











  public <T> List<T> list(String sqlID, Object object, int skipResults, int maxResults) throws DataAccessException {
    printSql(sqlID, object);
    return queryForList(sqlID, object, skipResults, maxResults);
  }









  public <T> T query(String sqlID) throws DataAccessException {
    return query(sqlID, null);
  }









  public <T> List<T> list(String sqlID) throws DataAccessException {
    return list(sqlID, null);
  }









  public Object create(String sqlID, Object object) throws DataAccessException {
    printSql(sqlID, object);
    return insert(sqlID, object);
  }











  public int update(String sqlID, Object object) throws DataAccessException {
    printSql(sqlID, object);
    return super.update(sqlID, object);
  }










  public int delete(String sqlID, Object object) throws DataAccessException {
    printSql(sqlID, object);
    return super.delete(sqlID, object);
  }









  private void printSql(String sqlID, Object parameter) {
    if (this.logger.isInfoEnabled()) {
      SqlMapClientImpl sqlMapClient = (SqlMapClientImpl)getSqlMapClient();

      MappedStatement statement = sqlMapClient.getMappedStatement(sqlID);
      StatementScope scope = new StatementScope(new SessionScope());
      statement.initRequest(scope);
      Sql sql = statement.getSql();
      String strSql = sql.getSql(scope, parameter);
      strSql = strSql.replaceAll("\\s+", " ");
      ParameterMap parameterMap = sql.getParameterMap(scope, parameter);
      Object[] objects = parameterMap.getParameterObjectValues(scope, parameter);
      this.logger.info(sqlID + ":\n " + strSql);
      String param = "";
      String value = "";
      if (objects != null) {
        for (int i = 0; i < objects.length; i++) {
          if (objects[i] == null) {
            value = "null";
          } else if (objects[i] instanceof java.util.Date) {
            value = dateFormat.format(objects[i]);
          } else {
            value = objects[i].toString();
          }
          param = param + "参数" + (i + 1) + ":" + value + "\n";
        }
      }
      this.logger.info(param);
    }
  }

  public Connection getConnection() {
    return DataSourceUtils.getConnection(getDataSource());
  }

  public void releaseConnection(Connection connection) {
    DataSourceUtils.releaseConnection(connection, getDataSource());
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\dao\SqlDaoImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */