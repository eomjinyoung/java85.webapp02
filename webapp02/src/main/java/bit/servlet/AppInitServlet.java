package bit.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppInitServlet extends GenericServlet{

	private static final long serialVersionUID = 1L;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);		
		// 1) Spring IoC Container 를 준비한다.
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				config.getInitParameter("contextConfigLocation"));
		
		// 2) ServletContext 창고를 얻는다.
		ServletContext servletContext = config.getServletContext();
	
		// 3) Spring IoC Container를 ServletContext 창고에 보관한다.
		servletContext.setAttribute("applicationContext", applicationContext);
	}
	
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
	}

}
