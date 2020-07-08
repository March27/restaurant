package com.qzz.sys.util;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ServletContextListen
 *
 */
@WebListener
public class ServletContextListen implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ServletContextListen() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
         //项目一启动就调用
    	ServletContext servletContext = event.getServletContext();
    	String filePath = servletContext.getRealPath("upload/food");
		System.out.println("filepath:"+filePath);
		
		File file = new File(filePath);
		if(!file.exists()) {
			file.mkdirs();
		}
    }
	
}
