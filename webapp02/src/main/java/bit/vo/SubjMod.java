package bit.vo;

import java.io.Serializable;

public class SubjMod implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected int smno;
	protected	String titl;
	protected String conts;
	public int getSmno() {
		return smno;
	}
	public void setSmno(int smno) {
		this.smno = smno;
	}
	public String getTitl() {
		return titl;
	}
	public void setTitl(String titl) {
		this.titl = titl;
	}
	public String getConts() {
		return conts;
	}
	public void setConts(String conts) {
		this.conts = conts;
	}
	
	
	
}
