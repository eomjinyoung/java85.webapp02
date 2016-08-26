/* 역할: 게시물 데이터를 보관 
 * 뭔가 데이터를 보관하는 역할자를 "값 객체(VO; Value Object)"라 부른다.
 * => 다른 말로 "(비즈니스) 도메인 객체(Domain Object)"라 부른다.
 * => 또 다른 말로 "데이터 전송 용으로 사용하는 객체(DTO; Data Transfer Object)"라 부른다.
 * => 용어 정리: VO == DTO == 도메인 객체 
 */
package bit.vo;
 
import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected int MNO;
  protected String MNM;
  protected String TEL;
  protected String email;
  protected transient String PWD; // 보안상 암호는 직렬화 대상에서 제외하는 것이 좋다.
  
  public int getMNO() {
    return MNO;
  }
  public void setMNO(int mNO) {
    MNO = mNO;
  }
  public String getMNM() {
    return MNM;
  }
  public void setMNM(String mNM) {
    MNM = mNM;
  }
  public String getTEL() {
    return TEL;
  }
  public void setTEL(String tel) {
    this.TEL = tel;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPWD() {
    return PWD;
  }
  public void setPWD(String pwd) {
    this.PWD = pwd;
  }
  public static long getSerialversionuid() {
    return serialVersionUID;
  }
 
  
  
}







