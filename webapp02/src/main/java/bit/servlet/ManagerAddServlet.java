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

import bit.dao.ManagerDao;
import bit.vo.Manager;

@WebServlet("/manager/add.do")
public class ManagerAddServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

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
		request.setCharacterEncoding("UTF-8");

		Manager manager = new Manager();
		manager.setMnm(request.getParameter("mnm"));
		manager.setTel(request.getParameter("tel"));
		manager.setEmail(request.getParameter("email"));
		manager.setPwd(request.getParameter("pwd"));

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println(" <head>");
		out.println("  <title>매니저 등록</title>");
		out.println("<meta http-equiv='Refresh' content='1;url=list.do'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>매니저 등록 결과</h1>");

		try {
			managerDao.insert(manager);
			out.println("등록 성공입니다!");

		} catch (Exception e) {
			out.println("데이터 처리 오류입니다.");
			e.printStackTrace();
		}

		out.println("</body>");
		out.println("</html>");
	}

}