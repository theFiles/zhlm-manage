package com.rogrand.core.util;

import org.dom4j.Document;
import org.dom4j.io.DOMReader;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;


public class XMLUtil
{
  public static Document parseDom4j(org.w3c.dom.Document doc) throws Exception {
    if (doc == null) return null;
    DOMReader xmlReader = new DOMReader();
    return xmlReader.read(doc);
  }







  public static org.w3c.dom.Document parseW3c(Document doc) throws Exception {
    if (doc == null) return null;
    StringReader reader = new StringReader(doc.asXML());
    InputSource source = new InputSource(reader);
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    return documentBuilder.parse(source);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\XMLUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */