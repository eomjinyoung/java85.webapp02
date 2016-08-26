package bit.vo;

public class Mods {
  
 protected int cono;
 protected int smno;
 protected int tno;
 
public int getCono() {
  return cono;
}
public void setCono(int cono) {
  this.cono = cono;
}
public int getSmno() {
  return smno;
}
public void setSmno(int smno) {
  this.smno = smno;
}
public int getTno() {
  return tno;
}
public void setTno(int tno) {
  this.tno = tno;
}

@Override
public String toString() {
  return "Mods [cono=" + cono + ", smno=" + smno + ", tno=" + tno + "]";
}
  

  

}
