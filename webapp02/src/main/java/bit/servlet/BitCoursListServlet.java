package example.servlet;

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

import example.dao.BitCoursDao;
import example.vo.BitCours;

@WebServlet("/bit_cours/list.do")
public class BitCoursListServlet extends GenericServlet {

  private static final long serialVersionUID = 1L;
  
  
  BitCoursDao bitCoursDao;
  
  @Override
  public void init(ServletConfig config) throws ServletException {  
    super.init(config);  
    
    ServletContext servletContext = config.getServletContext();
    ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
                                                                  
    
    bitCoursDao = applicationContext.getBean(BitCoursDao.class);
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
    
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
   
    
    
   out.println("<html>");
   out.println("<head>");
   out.println("<title>교육과정 목록조회</title>");
   out.println("</head>");
   out.println(" <body>");
   out.println("<h1>교육과정 목록조회</h1>");
   out.println("<a href='form.html'>새 교육과정</a></p>");
 
   HashMap<String,Object> map = new HashMap<>();
   map.put("startIndex", (pageNo - 1) * length);
   map.put("length", length);
   
   try {
     List<BitCours> list = bitCoursDao.selectList(map);  
     for (BitCours b : list) {
       out.printf("%d, <a href='detail.do?cono=%1$d'>%s</a>, %s, %s, %s, %d, %d, %s %d<br>\n",
           b.getCourseNo(), b.getTitle(), b.getContents(), b.getStartDate(), b.getEndDate(), b.getQuantity(),
           b.getStatement(), b.getRoomName(), b.getManagerNo());
     }
     
   } catch (Exception e) {
      out.println("데이터 목록 조회 오류 입니다!");
      e.printStackTrace();
   }
   out.println(" </body>");
   out.println("</html>");
    
    
    
  }

}
