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

import bit.dao.MemberDao;
import bit.vo.Member;


@WebServlet("/member/list.do")
public class MemberListServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config); //원래 GenericServlet 만든 메서드를 그대로 실행한다.
 // 모든 서블릿이 공유하는 ServletContext 창고를 알아낸다.
    ServletContext servletContext = config.getServletContext();
    
    // 창고에 보관된 Spring IoC 컨테이너르 ㄹ꺼낸다.
    ApplicationContext applicationContext = 
        (ApplicationContext)servletContext.getAttribute("applicationContext");
    
    // Spring IoC 컨테이너에서 BoardDao 구현체를 꺼낸다.
    memberDao = applicationContext.getBean(MemberDao.class);
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
    out.println("<title>회원 목록조회</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원 목록조회</h1>");
    out.println("<p><a href='teacher.html'>강사 등록</a></p>");
    out.println("<p><a href='member.html'>회원 등록</a></p>");
    out.println("<p><a href='/bitcampinfo/member/teacherlist.do'>강사 목록</a></p>");
    
    //out.println("<p><a href='form.html'>회원등록</a></p>");
    try {
        List<Member> list = memberDao.selectList(map);
        for (Member b : list) {
          out.printf("%d, %s, %s, %s<br>\n", 
              b.getMNO(), b.getMNM(), b.getTEL(), b.getEmail());
        }
    } catch (Exception e) {
      out.println("데이터 목록 조회 오류입니다.!");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
    
  }
}


