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

import example.dao.StdsDao;
import example.vo.Stds;

@WebServlet("/stds/list.do")
public class StdsListServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  StdsDao stdsDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config); // 원래 GenericServlet 만든 메서드를 그대로 실행한다.
    ServletContext servletContext = config.getServletContext();
    ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
    stdsDao = applicationContext.getBean(StdsDao.class);
  }

  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    int pageNo = 1;
    int length = 50;

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
    out.println("<title>학생 목록조회</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>학생 목록조회</h1>");
    out.println("<p><a href='form.html'>새 글</a><p>");

    try {
      List<Stds> list = stdsDao.selectList(map);
      for (Stds b : list) {
        out.printf("%d, <a href='detail.do?sno=%1$d'>%s, %s, %s, %s</a>, %s, %s, %c<br>\n", b.getSNO(), b.getMNM(),
            b.getTEL(), b.getEMAIL(), b.getSLVL(), b.getSCHL(), b.getED_DT(), b.getWORK());
      }
    } catch (Exception e) {
      out.println("데이터 목록 조회 오류입니다!");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");

  }

}
