/* 역할: 게시물 데이터를 보관 
 * 뭔가 데이터를 보관하는 역할자를 "값 객체(VO; Value Object)"라 부른다.
 * => 다른 말로 "(비즈니스) 도메인 객체(Domain Object)"라 부른다.
 * => 또 다른 말로 "데이터 전송 용으로 사용하는 객체(DTO; Data Transfer Object)"라 부른다.
 * => 용어 정리: VO == DTO == 도메인 객체 
 */
package bit.vo;

import java.io.Serializable;
import java.sql.Date;

public class Manager extends Member2 implements Serializable {
	
	protected String posi;

	public String getPosi() {
		return posi;
	}

	public void setPosi(String posi) {
		this.posi = posi;
	}
	

}
