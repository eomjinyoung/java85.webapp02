package bit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.springframework.context.ApplicationContext;

import bit.dao.ManagerDao;
import bit.vo.Manager;

@WebServlet("/manager/list.do")
public class ManagerListServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  ManagerDao managerDao;
  
  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config); 
    
    // 모든 서블릿이 공유하는 ServletContext 창고를 알아낸다.
    ServletContext servletContext = config.getServletContext();
    
    // 창고에 보관된 Spring IoC 컨테이너를 꺼낸다.
    ApplicationContext applicationContext = 
        (ApplicationContext)servletContext.getAttribute("applicationContext");
    
    // Spring IoC 컨테이너에서 ManagerDao 구현체를 꺼낸다.
    managerDao = applicationContext.getBean(ManagerDao.class);
  }
  
  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    int pageNo = 1;
    int length = 5;
    
    if (request.getParameter("pageNo") != null) {
      pageNo = Integer.parseInt(request.getParameter("pageNo"));
    }
    
    if (request.getParameter("length") != null) {
      length = Integer.parseInt(request.getParameter("length"));
    }
    
    HashMap<String,Object> map = new HashMap<>();
    map.put("startIndex", (pageNo - 1) * length);
    map.put("length", length);
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<html>");
    out.println("<head>");
    out.println("  <title>매니저 목록조회</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>매니저 목록조회</h1>");
    out.println("<p><a href='form.html'>새 매니저</a></p>");
    
    try {
      List<Manager> list = managerDao.selectList(map);
      for (Manager b : list) {
        out.printf("%d, <a href='detail.do?no=%1$d'>%s</a>, %s, %s, %s<br>\n", 
            b.getMno(), b.getMnm(), b.getTel(), b.getEmail(), b.getPosi());
      }
      
    } catch (Exception e) {
      out.println("매니저 목록 조회 오류입니다!");
      e.printStackTrace();
    }
    
    out.println("</body>");
    out.println("</html>");
  }

}










