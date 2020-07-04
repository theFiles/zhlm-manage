package com.rogrand.core.service;

import org.bson.types.ObjectId;

import java.io.IOException;

public interface MongoDbService {
  ObjectId uploadFile(String paramString) throws IOException;
  
  void delete(String paramString);
  
  ObjectId uploadRemoteFile(String paramString1, String paramString2) throws IOException;
  
  String getFileName(String paramString);
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\service\MongoDbService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */