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

@WebServlet("/bit_cours/delete.do")
public class BitcoursDeleteServlet extends GenericServlet {

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
    out.println("<title>교육과정 목록삭제</title>");
    //html 페이지를 완전히 출력한 후 지정된 시간이 경과하면 특정 URL을 자동으로 요청하게 만든다.
    
    out.println("<meta http-equiv='Refresh' content='1;url=list.do'>");
    out.println("</head>");
    out.println(" <body>");
    out.println("<h1>교육과정 목록삭제</h1>");



    try {
      int count = bitCoursDao.delete(courseNo);  
      if (count == 0) {
        out.println("해당 번호의 게시물이 없습니다.");

      } else {
        out.println("삭제 성공입니다!");
      }

    } catch (Exception e) {
      out.println("데이터 처리 오류 입니다!");
      e.printStackTrace();
    }
    out.println(" </body>");
    out.println("</html>");



  }
}
