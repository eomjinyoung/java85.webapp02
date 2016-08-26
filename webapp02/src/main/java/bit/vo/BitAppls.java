package bit.vo;

import java.io.Serializable;
import java.sql.Date;

public class BitAppls implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected int no;
  protected int studentNo;
  protected int courseNo;
  protected Date createdDate;
  protected int status;
  protected String reason;
  
  
  
  
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public int getStudentNo() {
    return studentNo;
  }
  public void setStudentNo(int studentNo) {
    this.studentNo = studentNo;
  }
  public int getCourseNo() {
    return courseNo;
  }
  public void setCourseNo(int courseNo) {
    this.courseNo = courseNo;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public int getStatus() {
    return status;
  }
  public void setStatus(int status) {
    this.status = status;
  }
  public String getReason() {
    return reason;
  }
  public void setReason(String reason) {
    this.reason = reason;
  }
  
  
  
  
  

}
