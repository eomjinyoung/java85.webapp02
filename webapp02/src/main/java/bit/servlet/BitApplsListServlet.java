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

import bit.dao.BitApplsDao;
import bit.vo.BitAppls;

@WebServlet("/bitappls/list.do")
public class BitApplsListServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;
  
  BitApplsDao bitApplsDao;
  
  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    
    ServletContext servletContext = config.getServletContext();
    
    ApplicationContext applicationContext = (ApplicationContext)servletContext.getAttribute("applicationContext");
    
    bitApplsDao = applicationContext.getBean(BitApplsDao.class);
    
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
    map.put("startIndex",  (pageNo - 1) * length);
    map.put("length", length);
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<html>");
    out.println("<head>");
    out.println("  <title>수강신청 목록 조회</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>수강신청 목록 조회</h1>");
    out.println("<p><a href='form.html'>수강신청 하기</a></p>");
    
    try {
      int count = 1;
      List<BitAppls> list = bitApplsDao.selectList(map);
      for (BitAppls a : list) {
//        out.printf("%d, <a href='detail.do?no=%1$d'>%d</a>, %d, %s, %d, %s<br>\n",
//            a.getNo(), a.getStudentNo(), a.getCourseNo(), a.getCreatedDate(), a.getStatus(), a.getReason());
        out.printf("<a href='detail.do?no=%1$d'>%d</a>, %d, %d, %s, %d, %s<br>\n",
            count++, a.getStudentNo(), a.getCourseNo(), a.getCreatedDate(), a.getStatus(), a.getReason());
        
      }
    } catch (Exception e) {
      out.println("데이터 목록 조회 오류 입니다.");
      e.printStackTrace();
    }
    
    out.println("</body>");
    out.println("</html>");
    
  }

}
