package bit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.springframework.context.ApplicationContext;

import bit.dao.BitCoursDao;
import bit.vo.BitCours;

 
@WebServlet("/bit_cours/detail.do")
public class BitCoursDetailServlet extends GenericServlet {

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
    int courseNo = Integer.parseInt(request.getParameter("cono"));
 
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
   
    
   out.println("<html>");
   out.println("<head>");
   out.println("<title>교육과정 목록조회</title>");
   out.println("</head>");
   out.println(" <body>");
   out.println("<h1>교육과정 목록조회</h1>");
 
 
   
   try {
    BitCours bitcours = bitCoursDao.selectOne(courseNo);  

       if (bitcours == null) {
         out.println("해당 번호의 교육과정이 없습니다.");
         
   } else {
     out.printf("<form action='update.do' method='post'>\n");
     out.printf("<input type='hidden' name='cono' value='%d'>\n", bitcours.getCourseNo());
     out.printf("번호: %d<br>", bitcours.getCourseNo());
     out.printf("제목: <input type='text' name='title' value='%s'><br>\n",
         bitcours.getTitle());
     out.printf("내용:<textarea cols='60' rows='10' name='contents'>%s</textarea><br>\n",
         bitcours.getContents());
     out.printf("시작일: <input type='text' name='startDate' value='%s'> <br>\n", bitcours.getStartDate());
     out.printf("종료일: <input type='text' name='endDate' value='%s'> <br>\n", bitcours.getEndDate());
     out.printf("모집인원: <input type='text' name='quantity' value='%d'> <br>\n", bitcours.getQuantity());
     out.printf("상태: %s<br>\n", bitcours.getStatement());
     out.printf("강의실: %s<br>\n", bitcours.getRoomName());
     out.printf("매니저 번호: %d<br>\n", bitcours.getManagerNo());
     out.printf("<button>변경</button>\n");
     out.printf("<p><a href='delete.do?cono=%d'>삭제</a></p>\n", bitcours.getCourseNo());
     out.printf("</form>");
   }
     
       
   } catch (Exception e) {
      out.println("데이터 목록 조회 오류 입니다!");
      e.printStackTrace();
   }
   out.println(" </body>");
   out.println("</html>");
    
    
    
  }
}
