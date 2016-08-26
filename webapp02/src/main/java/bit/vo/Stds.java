package bit.vo;

import java.io.Serializable;
import java.sql.Date;

public class Stds implements Serializable {
  private static final long serialVersionUID = 1L;

  protected int SNO;            // 학생번호
  protected String SLVL;        // 최종학력
  protected String SCHL;        // 학교명
  protected Date ED_DT;         // 졸업연도
  protected char WORK;          // 재직여부
  protected String COMP;        // 회사명
  protected String POSI;        // 직급
  protected String CTEL;        // 회사전화
  protected String CFAX;        // 회사팩스
  protected char POSTNO;        // 우편번호
  protected String BAS_ADDR;    // 기본주소
  protected String DET_ADDR;    // 상세주소
  
  protected String MNM;         // 회원이름
  protected String TEL;         // 회원전화
  protected String EMAIL;       // 회원이메일
  protected String PWD;         // 회원비밀번호
  

  public String getMNM() {
    return MNM;
  }

  public void setMNM(String mNM) {
    MNM = mNM;
  }

  public String getTEL() {
    return TEL;
  }

  public void setTEL(String tEL) {
    TEL = tEL;
  }

  public String getEMAIL() {
    return EMAIL;
  }

  public void setEMAIL(String eMAIL) {
    EMAIL = eMAIL;
  }

  public String getPWD() {
    return PWD;
  }

  public void setPWD(String pWD) {
    PWD = pWD;
  }

  public int getSNO() {
    return SNO;
  }

  public void setSNO(int sNO) {
    SNO = sNO;
  }

  public String getSLVL() {
    return SLVL;
  }

  public void setSLVL(String sLVL) {
    SLVL = sLVL;
  }

  public String getSCHL() {
    return SCHL;
  }

  public void setSCHL(String sCHL) {
    SCHL = sCHL;
  }

  public Date getED_DT() {
    return ED_DT;
  }

  public void setED_DT(Date eD_DT) {
    ED_DT = eD_DT;
  }

  public char getWORK() {
    return WORK;
  }

  public void setWORK(char wORK) {
    WORK = wORK;
  }

  public String getCOMP() {
    return COMP;
  }

  public void setCOMP(String cOMP) {
    COMP = cOMP;
  }

  public String getPOSI() {
    return POSI;
  }

  public void setPOSI(String pOSI) {
    POSI = pOSI;
  }

  public String getCTEL() {
    return CTEL;
  }

  public void setCTEL(String cTEL) {
    CTEL = cTEL;
  }

  public String getCFAX() {
    return CFAX;
  }

  public void setCFAX(String cFAX) {
    CFAX = cFAX;
  }

  public char getPOSTNO() {
    return POSTNO;
  }

  public void setPOSTNO(char pOSTNO) {
    POSTNO = pOSTNO;
  }

  public String getBAS_ADDR() {
    return BAS_ADDR;
  }

  public void setBAS_ADDR(String bAS_ADDR) {
    BAS_ADDR = bAS_ADDR;
  }

  public String getDET_ADDR() {
    return DET_ADDR;
  }

  public void setDET_ADDR(String dET_ADDR) {
    DET_ADDR = dET_ADDR;
  }

}
