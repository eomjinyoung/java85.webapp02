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

import subj_mods.dao.SubjModsDao;
import subj_mods.vo.SubjMod;


@WebServlet("/subj_mods/list.do")
public class SubjModsListServlet extends GenericServlet{
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
		
		int pageNo = 1;
		int length = 16;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		
		if (request.getParameter("length") != null) {
			length = Integer.parseInt(request.getParameter("length"));
		}
		
		
		HashMap<String,Object> map = new HashMap<>();
    map.put("startIndex", (pageNo - 1) * length);
    map.put("length", length);
    
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
		
    out.println("<html>");
    out.println("<head>");
    out.println("<title>게시물 목록 조회</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시물 목록 조회</h1>");
    out.println("<a href='form.html'>입력</a><br>");
    out.println("<table>");
    out.println(" <thead>");
    out.println("<tr>");
    out.println("<th scope='col'>글번호</th>");
    out.println("<th scope='col'>제목</th>");
    out.println("<th scope='col'>내용</th>");
    out.println("</tr>");
    out.println("</thead>");
    out.println("<tbody>");
   
    try {
    	List<SubjMod> list = subjModsDao.selectList(map);
    	for (SubjMod b : list) {
    	out.println("<tr style='text-align:center'>");
    		out.printf("<td>%d</td>",b.getSmno());
    		out.printf("<td><a href='detail.do?no=%d'>%s</a></td>",b.getSmno(), b.getTitl());
    		out.printf("<td>%s</td>",b.getConts());
    	out.println(" </tr>");
    	}
    	
    	out.println("</tbody>");
    	out.println("</table>");
    } catch (Exception e) {
    	out.println("데이터 목록조회 오류");
    	e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
    
	}

	
}
