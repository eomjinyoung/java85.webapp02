package bit.dao;

import java.util.List;
import java.util.Map;

import bit.vo.SubjMod;

public interface SubjModsDao {
	List<SubjMod> selectList(Map<String, Object> paramMap) throws Exception;
	SubjMod selectOne(int no) throws Exception;    
  int insert(SubjMod subjMod) throws Exception;
  int update(SubjMod subjMod) throws Exception;
  int delete(int no) throws Exception;
}
