package com.rogrand.core.domain;

import com.rogrand.core.util.StringUtil;


public class TreeConfig
  extends SerializeCloneable
{
  private static final long serialVersionUID = 1454417934156069567L;
  private String rootID;
  private String rootText;
  private String state;
  private String fieldID;
  private String fieldText;
  private String fieldParentID;
  private String fieldChild;
  private String[] fieldAttributes;
  private String fieldChecked;

  public TreeConfig() {}

  public TreeConfig(String rootID, String rootText, String state, String fieldID, String fieldText, String fieldParentID, String fieldChild, String[] fieldAttributes, String fieldChecked) {
    setRootID(rootID);
    setRootText(rootText);
    setState(state);
    setFieldID(fieldID);
    setFieldText(fieldText);
    setFieldParentID(fieldParentID);
    setFieldChild(fieldChild);
    setFieldAttributes(fieldAttributes);
    setFieldChild(fieldChecked);
  }



  public TreeConfig(String rootID, String rootText, String state, String fieldID, String fieldText, String fieldParentID, String fieldChild, String fieldAttributes, String fieldChecked) {
    setRootID(rootID);
    setRootText(rootText);
    setState(state);
    setFieldID(fieldID);
    setFieldText(fieldText);
    setFieldParentID(fieldParentID);
    setFieldChild(fieldChild);
    setFieldAttributes(fieldAttributes);
    setFieldChild(fieldChecked);
  }


  public TreeConfig(String rootID, String rootText, String state, String fieldID, String fieldText, String fieldParentID) {
    setRootID(rootID);
    setRootText(rootText);
    setState(state);
    setFieldID(fieldID);
    setFieldText(fieldText);
    setFieldParentID(fieldParentID);
  }



  public TreeConfig(String rootID, String rootText, String state, String fieldID, String fieldText, String fieldParentID, String fieldChild) {
    setRootID(rootID);
    setRootText(rootText);
    setState(state);
    setFieldID(fieldID);
    setFieldText(fieldText);
    setFieldParentID(fieldParentID);
    setFieldChild(fieldChild);
  }



  public TreeConfig(String rootID, String rootText, String state, String fieldID, String fieldText, String fieldParentID, String fieldChild, String fieldAttributes) {
    setRootID(rootID);
    setRootText(rootText);
    setState(state);
    setFieldID(fieldID);
    setFieldText(fieldText);
    setFieldParentID(fieldParentID);
    setFieldChild(fieldChild);
    setFieldAttributes(fieldAttributes);
  }



  public TreeConfig(String rootID, String rootText, String state, String fieldID, String fieldText, String fieldParentID, String fieldChild, String[] fieldAttributes) {
    setRootID(rootID);
    setRootText(rootText);
    setState(state);
    setFieldID(fieldID);
    setFieldText(fieldText);
    setFieldParentID(fieldParentID);
    setFieldChild(fieldChild);
    setFieldAttributes(fieldAttributes);
  }

  public String getFieldID() {
    return this.fieldID;
  }

  public String getFieldParentID() {
    return this.fieldParentID;
  }

  public String getFieldText() {
    return this.fieldText;
  }

  public String getFieldChecked() {
    return this.fieldChecked;
  }

  public String[] getFieldAttributes() {
    return this.fieldAttributes;
  }

  public String getState() {
    return this.state;
  }

  public String getRootID() {
    return this.rootID;
  }

  public String getRootText() {
    return this.rootText;
  }

  public String getFieldChild() {
    return this.fieldChild;
  }

  public void setFieldID(String fieldID) {
    this.fieldID = fieldID;
  }

  public void setFieldParentID(String fieldParentID) {
    this.fieldParentID = fieldParentID;
  }

  public void setFieldText(String fieldText) {
    this.fieldText = fieldText;
  }

  public void setFieldChecked(String fieldChecked) {
    this.fieldChecked = fieldChecked;
  }

  public void setFieldAttributes(String[] fieldAttributes) {
    this.fieldAttributes = fieldAttributes;
  }

  public void setFieldAttributes(String fieldAttributes) {
    if (!StringUtil.isEmpty(fieldAttributes))
      this.fieldAttributes = fieldAttributes.split(",");
  }

  public void setFieldChild(String fieldChild) {
    this.fieldChild = fieldChild;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setRootID(String rootID) {
    this.rootID = rootID;
  }

  public void setRootText(String rootText) {
    this.rootText = rootText;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\domain\TreeConfig.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */