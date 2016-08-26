package bit.dao;

import java.util.List;
import java.util.Map;

import bit.vo.Mods;


public interface ModsDao {
  List<Mods> selectList(Map<String,Object> paramMap) throws Exception;
  Mods selectOne(int no) throws Exception;
  int insert(Mods mods) throws Exception;
  int update(Mods mods) throws Exception;
  int delete(int cno, int sno) throws Exception;

}
