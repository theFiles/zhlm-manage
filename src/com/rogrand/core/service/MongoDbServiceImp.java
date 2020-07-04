package com.rogrand.core.service;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.apache.cxf.common.util.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


@Service("MongoDbService")
public class MongoDbServiceImp
  implements MongoDbService
{
  @Autowired
  @Qualifier("mongoTemplate")
  private MongoTemplate mongoTemplate;

  public ObjectId uploadFile(String filePath) throws IOException {
    ObjectId objectId = null;
    try {
      GridFS gridFS = new GridFS(this.mongoTemplate.getDb());
      File file = new File(filePath);
      GridFSInputFile mongofile = gridFS.createFile(file);
      mongofile.save();
      objectId = (ObjectId)mongofile.getId();
      if (objectId == null) {
        throw new RuntimeException("ObjectId为null");
      }
      file.delete();
    } catch (IOException e) {
      throw new RuntimeException("mongoUploadErr", e);
    }
    return objectId;
  }










  public void delete(String objectId) {
    (new GridFS(this.mongoTemplate.getDb())).remove(new ObjectId(objectId));
  }









  public ObjectId uploadRemoteFile(String remoteFilePath, String localFilePath) throws IOException {
    ObjectId objectId = null;
    HttpURLConnection connection = null;
    DataInputStream in = null;
    DataOutputStream out = null;

    try {
      URL url = new URL(remoteFilePath);
      connection = (HttpURLConnection)url.openConnection();
      in = new DataInputStream(connection.getInputStream());
      out = new DataOutputStream(new FileOutputStream(localFilePath));
      byte[] buffer = new byte[4096];
      int count = 0;
      while ((count = in.read(buffer)) > 0) {
        out.write(buffer, 0, count);
      }

      objectId = uploadFile(localFilePath);
    } catch (IOException e) {
      throw new RuntimeException("mongoUploadErr", e);
    } finally {
      if (out != null) {
        out.close();
      }
      if (in != null) {
        in.close();
      }
      if (connection != null) {
        connection.disconnect();
      }
    }
    return objectId;
  }


  public String getFileName(String objId) throws IllegalArgumentException {
    if (StringUtils.isEmpty(objId)) {
      throw new IllegalArgumentException("the paramter objectId is empty");
    }
    ObjectId objectId = new ObjectId(objId);
    GridFS gridFS = new GridFS(this.mongoTemplate.getDb());
    GridFSDBFile gfsdbFile = gridFS.findOne(objectId);
    String fileName = gfsdbFile.getFilename();
    if (StringUtils.isEmpty(fileName)) {
      throw new RuntimeException("FileName is null,check objeciIt is or right");
    }
    return gfsdbFile.getFilename();
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\service\MongoDbServiceImp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */