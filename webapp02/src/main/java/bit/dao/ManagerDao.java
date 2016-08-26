package bit.dao;

import java.util.List;
import java.util.Map;

import bit.vo.Manager;

public interface ManagerDao {
  List<Manager> selectList(Map<String,Object> paramMap) throws Exception;
  Manager selectOne(int no) throws Exception;
  Manager selectOneByPassword(Map<String,Object> paramMap) throws Exception;
  int insert(Manager manager) throws Exception;
  int update(Manager manager) throws Exception;
  int delete(int no) throws Exception; 
}








