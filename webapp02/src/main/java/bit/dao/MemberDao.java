package bit.dao;

import java.util.List;
import java.util.Map;

import bit.vo.Member;
  

public interface MemberDao {
  
  List<Member> selectList(Map<String,Object> paramMap) throws Exception;
  List<Member> teacherList(Map<String,Object> paramMap) throws Exception;
  Member selectOne(int no) throws Exception;
  Member selectOneByPassword(Map<String,Object> paramMap) throws Exception;
  int insert(Member member) throws Exception;
  int teacherinsert(Member member) throws Exception;
  int update(Member member) throws Exception;
  int delete(int no) throws Exception;
  int deleteteacher(int no) throws Exception;
}








