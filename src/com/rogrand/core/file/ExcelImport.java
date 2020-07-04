package com.rogrand.core.file;

import com.alibaba.fastjson.JSON;
import com.rogrand.core.exception.ServiceException;
import org.apache.commons.fileupload.DefaultFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExcelImport
{
  private Workbook wb;

  public ExcelImport(HttpServletRequest request) throws Exception {
    DefaultFileItemFactory defaultFileItemFactory = new DefaultFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload((FileItemFactory)defaultFileItemFactory);
    List<FileItem> items = upload.parseRequest(request);

    FileItem fileItem = items.get(0);
    String fileName = fileItem.getName();
    if (StringUtils.endsWith(fileName, "xls")) {
      this.wb = (Workbook)new HSSFWorkbook(fileItem.getInputStream());
    } else if (StringUtils.endsWith(fileName, "xlsx")) {
      this.wb = (Workbook)new XSSFWorkbook(fileItem.getInputStream());
    } else {
      throw new ServiceException("2000", "文件类型错误");
    }

    fileItem.delete();
  }










  public List convertListData(int firstRowIndex, String[] fileds, Class tClass) throws IOException {
    Sheet sheet = this.wb.getSheetAt(0);

    int lastRowIndex = sheet.getLastRowNum();
    System.out.println("firstRowIndex: " + firstRowIndex);
    System.out.println("lastRowIndex: " + lastRowIndex);

    List<Map<String, String>> list = new ArrayList<>();
    for (int rIndex = firstRowIndex - 1; rIndex <= lastRowIndex; rIndex++) {
      System.out.println("rIndex: " + rIndex);
      Row row = sheet.getRow(rIndex);
      if (row != null) {
        DecimalFormat df = new DecimalFormat("#");
        Map<String, String> map = new HashMap<>();
        int firstCellIndex = row.getFirstCellNum();
        int lastCellIndex = row.getLastCellNum();
        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {
          Cell cell = row.getCell(cIndex);
          String cellValue = "";

          int cellType = cell.getCellType();

          if (0 == cellType) {
            double cellValue1 = cell.getNumericCellValue();
            cellValue = df.format(cellValue1);
          }
          if (1 == cellType) {
            cellValue = cell.getStringCellValue();
          }

          map.put(fileds[cIndex], cellValue);
        }

        list.add(map);
      }
    }

    if (tClass != null) {
      List list1 = JSON.parseArray(JSON.toJSONString(list), (Type[])tClass.getClasses());
      return list1;
    }
    return list;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\file\ExcelImport.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */