/* 역할: 게시물 데이터를 보관 
 * 뭔가 데이터를 보관하는 역할자를 "값 객체(VO; Value Object)"라 부른다.
 * => 다른 말로 "(비즈니스) 도메인 객체(Domain Object)"라 부른다.
 * => 또 다른 말로 "데이터 전송 용으로 사용하는 객체(DTO; Data Transfer Object)"라 부른다.
 * => 용어 정리: VO == DTO == 도메인 객체 
 */
package bit.vo;

import java.io.Serializable;

public class BitCours implements Serializable {
  private static final long serialVersionUID = 1L;
 
  protected int courseNo;
  protected String title;
  protected String contents;
  protected String startDate;
  protected String endDate;
  protected int quantity;
  protected int statement;
  protected String roomName;
  protected int ManagerNo;
  
  public int getCourseNo() {
    return courseNo;
  }
  public void setCourseNo(int courseNo) {
    this.courseNo = courseNo;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContents() {
    return contents;
  }
  public void setContents(String contents) {
    this.contents = contents;
  }
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  public String getEndDate() {
    return endDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  public int getStatement() {
    return statement;
  }
  public void setStatement(int statement) {
    this.statement = statement;
  }
  public String getRoomName() {
    return roomName;
  }
  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }
  public int getManagerNo() {
    return ManagerNo;
  }
  public void setManagerNo(int managerNo) {
    ManagerNo = managerNo;
  }
  
  
   
  
}







