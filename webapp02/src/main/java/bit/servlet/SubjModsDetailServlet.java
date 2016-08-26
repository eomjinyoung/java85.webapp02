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
import subj_mods.vo.SubjMod;


@WebServlet("/subj_mods/detail.do")
public class SubjModsDetailServlet extends GenericServlet{
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
    out.println("<title>게시물 상세 조회</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시물 상세 조회</h1>");
    try {
    	SubjMod subjMod = subjModsDao.selectOne(no);
    	
    	if (subjMod == null) {
    		out.println("해당 번호의 게시물이 없습니다.");
    	} else {
    		out.printf("<form action='update.do' method='post'>\n");
    		out.printf("<input type='hidden' name='smno' value='%d'>\n", subjMod.getSmno());
    		out.printf("번호 : %d<br>\n", subjMod.getSmno());
    		out.printf("제목 : <input type='text' name='titl' value='%s'><br>\n", 
    				subjMod.getTitl());
    		out.printf("내용 : <textarea cols='60' rows='10' name='conts'>%s</textarea><br>\n", 
    				subjMod.getConts());   		
    		out.printf("<button type='submit'>변경</button>");
    		out.printf("<p><a href='delete.do?no=%d'>삭제</a></p>\n",subjMod.getSmno());
    		
    		out.printf("</form>\n");
    	}
    	
    	
    } catch (Exception e) {
    	out.println("데이터 목록조회 오류");
    	e.printStackTrace();
    }
    out.println("<a href='list.do'>목록</a>");
    
    out.println("</body>");
    out.println("</html>");
    
	}

	
}
