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

import subj_mods.dao.SubjModsDao;
import subj_mods.vo.SubjMod;


@WebServlet("/subj_mods/update.do")
public class SubjModsUpdateServlet extends GenericServlet{
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
		request.setCharacterEncoding("UTF-8");
		
		SubjMod subjMod = new SubjMod();		
		subjMod.setSmno(Integer.parseInt(request.getParameter("smno")));
		subjMod.setTitl(request.getParameter("titl"));
		subjMod.setConts(request.getParameter("conts"));
		
		
		
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
		
    out.println("<html>");
    out.println("<head>");
    out.println("<title>게시물 등록</title>");
    out.println("<meta http-equiv='refresh' content='1;url=list.do'>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시물 변경 </h1>");
    try {
   
    	int no =  subjMod.getSmno();
   	
    	if (subjModsDao.selectOne(no) == null) {
    		out.println("해당 게시물이 없습니다.");
    	} else {
    		subjModsDao.update(subjMod);
      	out.println("변경 성공");
    	}
        	 
    } catch (Exception e) {
    	out.println("데이터 처리 오류");
    	e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
	}

	
}
