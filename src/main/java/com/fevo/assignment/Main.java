package com.fevo.assignment;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import com.fevo.assignment.servlet.FetchServlet;


public class Main {
	
	public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        // Add an empty context
        Context ctx = tomcat.addContext("", null);

        // Get an instance of the servlet and add a servlet mapping
        String fetchServletName = FetchServlet.class.getSimpleName();
        Tomcat.addServlet(ctx, fetchServletName, new FetchServlet());
        ctx.addServletMapping("/fetchData", fetchServletName);
        
        /*
         * Some other servlets and mappings
         */

        // Start the Tomcat instance
        tomcat.start();
        tomcat.getServer().await();
	}
}
