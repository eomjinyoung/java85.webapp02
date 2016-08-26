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

import subj_mods.dao.SubjModsDao;


@WebServlet("/subj_mods/delete.do")
public class SubjModsDeleteServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	
	SubjModsDao subjModsDao;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config); // 원래  GenericServlet이 하던일 하게 하라				
		// spring IoC container 에 보관된 BoardDao 구현체를 꺼낸다
		ServletContext servletContext = config.getServletContext();
		ApplicationContext applicationContext = 
				(ApplicationContext)servletContext.getAttribute("applicationContext");
		subjModsDao = applicationContext.getBean(SubjModsDao.class);
	}
	
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
		
    out.println("<html>");
    out.println("<head>");
    out.println("<title>게시물 목록 조회</title>");
    out.println("<meta http-equiv='refresh' content='1;url=list.do'>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시물 삭제 </h1>");
    
    try {    	
    	int count = subjModsDao.delete(no);    	
    	if (count == 0) {
    		out.println("해당 번호의 게시물이 없습니다.");
    	} else {
    		out.println("삭제 성공<br>");    		
    	}
    	
    } catch (Exception e) {
    	out.println("데이터 처리 오류");
    	e.printStackTrace();
    }
   
    out.println("</body>");
    out.println("</html>");    
	}
	
}
