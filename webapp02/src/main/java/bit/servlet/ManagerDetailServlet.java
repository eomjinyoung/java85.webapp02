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
import org.springframework.context.support.ClassPathXmlApplicationContext;

import example.dao.ManagerDao;
import example.vo.Manager;


@WebServlet("/manager/detail.do")
public class ManagerDetailServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

	ApplicationContext iocContainer;
	ManagerDao managerDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
ServletContext servletContext = config.getServletContext();
		
		ApplicationContext applicationContext = 
				(ApplicationContext)servletContext.getAttribute("applicationContext");
		
		managerDao = applicationContext.getBean(ManagerDao.class);

	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println(" <head>");
		out.println("  <title>매니저 상세조회</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>매니저 상세조회</h1>");


		try {
			Manager manager = managerDao.selectOne(no);

			if (manager == null) {
				out.println("해당 번호의 매니저가 없습니다.");
			} else {
				out.printf("<form action='update.do' method='post'>\n");
				out.printf("<input type='hidden' name='no' value='%d'>\n", manager.getMno());
				out.printf("번호: %d<br>\n", manager.getMno());
				out.printf("이름: <input type='text' name='title' value='%s'\n><br>", 
						manager.getMnm());
				out.printf("전화번호: <input type='text' name='tel' value='%s'\n><br>\n", 
						manager.getTel());
				out.printf("암호: <input type='password' name='password'><br>\n");
				out.printf("이메일: %s<br>\n", manager.getEmail());
				out.printf("매니저 번호: %s<br>\n", manager.getMgrno());
				out.printf("직급: %s<br>\n", manager.getPosi());
				out.printf("<button>변경</button>\n"); 
				out.printf("<p><a href='delete.do?no=%d'>삭제</a></p>\n", manager.getMno());
				out.printf("</form>\n");
			}

    } catch (Exception e) {
      out.println("데이터 목록조회 오류입니다.");
      e.printStackTrace();
    }
    
    out.println("</body>");
    out.println("</html>");
  }
  
}