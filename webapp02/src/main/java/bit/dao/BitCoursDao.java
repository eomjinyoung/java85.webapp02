package example.dao;

import java.util.List;
import java.util.Map;

import example.vo.BitCours;

public interface BitCoursDao {
  List<BitCours> selectList(Map<String,Object> paramMap) throws Exception;
  BitCours selectOne(int courseNo) throws Exception;
  int insert(BitCours bitcours) throws Exception;
  int update(BitCours bitcours) throws Exception;
  int delete(int courseNo) throws Exception;
}
