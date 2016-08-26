package bit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bit.dao.ModsDao;
import bit.vo.Mods;



@WebServlet("/mods/list.do")
public class ModsListServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  ApplicationContext iocContainer;
  ModsDao modsDao;

  @Override
  public void init() throws ServletException {
    super.init();
    iocContainer = new ClassPathXmlApplicationContext(
        "conf/application-context.xml");

    modsDao = iocContainer.getBean(ModsDao.class);
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

    HashMap<String, Object> map = new HashMap<>();
    map.put("startIndex", (pageNo - 1) * length);
    map.put("length", length);

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>과정 학습 모듈</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>과정 학습 모듈</h1>");
    
    
    try {
      List<Mods> list = modsDao.selectList(map);
      for (Mods s : list) {
        out.printf("%d,%d,%d<br>\n", s.getCono(), s.getSmno(), s.getTno());
      }

    } catch (Exception e) {
      out.println("데이터 목록 조회 오류입니다.");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");

  }

}
