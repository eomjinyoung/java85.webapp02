package bit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bit.dao.ManagerDao;
import bit.vo.Manager;

@WebServlet("/manager/update.do")
public class ManagerUpdateServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

	ApplicationContext iocContainer;
	ManagerDao managerDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext servletContext = config.getServletContext();

		ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");

		managerDao = applicationContext.getBean(ManagerDao.class);
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Manager manager = new Manager();
		manager.setMno(Integer.parseInt(request.getParameter("no")));
		manager.setPwd(request.getParameter("password"));
		manager.setMnm(request.getParameter("mnm"));
		manager.setPosi(request.getParameter("posi"));

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println(" <head>");
		out.println("  <title>매니저 변경</title>");
		out.println("<meta http-equiv='Refresh' content='1;url=list.do'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>매니저 변경 결과</h1>");

		try {
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("no", manager.getMno());
			paramMap.put("password", manager.getPwd());

			if (managerDao.selectOneByPassword(paramMap) == null) {
				out.println("해당 매니저가 없거나 암호가 일치하지 않습니다.");
			} else {
				managerDao.update(manager);
				out.println("변경 성공입니다!");
			}

		} catch (Exception e) {
			out.println("데이터 처리 오류입니다.");
			e.printStackTrace();
		}

		out.println("</body>");
		out.println("</html>");
	}

}