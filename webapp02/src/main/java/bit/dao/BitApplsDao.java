package bit.dao;

import java.util.List;
import java.util.Map;

import bit.vo.BitAppls;


public interface BitApplsDao {
  
  int insert(BitAppls bitAppls) throws Exception;
  List<BitAppls> selectList(Map<String,Object> paramMap) throws Exception;
  BitAppls selectOne(int no) throws Exception;
  int update(BitAppls bitAppls) throws Exception;
  int delete(int no) throws Exception;
  
  
  

}
