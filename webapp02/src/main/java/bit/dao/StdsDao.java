package bit.dao;

import java.util.List;
import java.util.Map;

import example.vo.Stds;

public interface StdsDao {
  List<Stds> selectList(Map<String, Object> paramMap) throws Exception;

  Stds selectOne(int no) throws Exception;

  Stds selectOneByPassword(Map<String, Object> paramMap) throws Exception;

  int insert(Stds stds) throws Exception;

  int update(Stds stds) throws Exception;

  int delete(int no) throws Exception;

}
