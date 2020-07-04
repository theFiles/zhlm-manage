package com.rogrand.core.dao;

import org.springframework.dao.DataAccessException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface SqlDao {
  Map query(String paramString1, Object paramObject, String paramString2) throws DataAccessException;
  
  Map query(String paramString1, Object paramObject, String paramString2, String paramString3) throws DataAccessException;
  
  <T> T query(String paramString, Object paramObject) throws DataAccessException;
  
  <T> T query(String paramString) throws DataAccessException;
  
  <T> List<T> list(String paramString, Object paramObject) throws DataAccessException;
  
  <T> List<T> list(String paramString, Object paramObject, int paramInt1, int paramInt2) throws DataAccessException;
  
  <T> List<T> list(String paramString) throws DataAccessException;
  
  Object create(String paramString, Object paramObject) throws DataAccessException;
  
  int update(String paramString, Object paramObject) throws DataAccessException;
  
  int delete(String paramString, Object paramObject) throws DataAccessException;
  
  Connection getConnection();
  
  void releaseConnection(Connection paramConnection);
  
  DataSource getDataSource();
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\dao\SqlDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */