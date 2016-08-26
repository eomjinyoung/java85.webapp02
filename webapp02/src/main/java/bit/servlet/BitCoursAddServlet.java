package example.servlet;

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

import example.dao.BitCoursDao;
import example.vo.BitCours;

@WebServlet("/bit_cours/add.do")
public class BitCoursAddServlet extends GenericServlet {

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
    request.setCharacterEncoding("UTF-8");
    BitCours bitCours = new BitCours();

    bitCours.setTitle(request.getParameter("title"));
    bitCours.setContents(request.getParameter("contents"));
    bitCours.setStartDate(request.getParameter("startDate"));
    bitCours.setEndDate(request.getParameter("endDate"));
    bitCours.setQuantity(Integer.parseInt(request.getParameter("quantity")));

    

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();


    out.println("<html>");
    out.println("<head>");
    out.println("<title>교육과정 등록하기</title>");
    //html 페이지를 완전히 출력한 후 지정된 시간이 경과하면 특정 URL을 자동으로 요청하게 만든다.

    out.println("<meta http-equiv='Refresh' content='1;url=list.do'>");
    out.println("</head>");
    out.println(" <body>");
    out.println("<h1>교육과정 목록조회</h1>");



    try {
      bitCoursDao.insert(bitCours);      
      out.println("등록 성공입니다!");
    } catch (Exception e) {
      out.println("데이터 처리 오류 입니다!");
      e.printStackTrace();
    }
    out.println(" </body>");
    out.println("</html>");



  }
}
