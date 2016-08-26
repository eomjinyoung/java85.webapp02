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

import example.dao.StdsDao;
import example.vo.Stds;

@WebServlet("/stds/detail.do")
public class StdsDetailServlet extends GenericServlet {
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
    int no = Integer.parseInt(request.getParameter("sno"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>학생 상세조회</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>학생 상세조회</h1>");

    try {
      Stds stds = stdsDao.selectOne(no);
      if (stds == null) {
        out.println("해당 번호의 게시물이 없습니다.");
      } else {
        out.println("<form action='update.do' method='post'>\n");
        out.printf("<input type='hidden' name='SNO' value='%d'>\n", stds.getSNO());
        out.printf("학생번호 : %d<br>\n", stds.getSNO());
        out.printf("이름 : <input type='text' name='MNM' value='%s'><br>\n", stds.getMNM());
        out.printf("전화 : <input type='text' name='TEL' value='%s'><br>\n", stds.getTEL());
        out.printf("이메일 : <input type='text' name='EMAIL' value='%s'><br>\n", stds.getEMAIL());
        out.printf("최종학력 : <input type='text' name='SLVL' value='%s'><br>\n", stds.getSLVL());
        out.printf("학교명 : <textarea cols='60' rows='10' name='SCHL'>%s</textarea><br>\n", stds.getSCHL());
        out.printf("졸업연도 : %s<br>\n", stds.getED_DT());
        out.printf("재직여부 : <input type='text' name='WORK' value='%s'><br>\n", stds.getWORK());
        out.printf("회사명 : <input type='text' name='COMP' value='%s'><br>\n", stds.getCOMP());
        out.printf("<p><a href='delete.do?sno=%d'>삭제</a><p>\n", stds.getSNO());
        out.println("<button>변경</button>");
      }
    } catch (Exception e) {
      out.println("데이터 목록 조회 오류입니다!");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");

  }

}
