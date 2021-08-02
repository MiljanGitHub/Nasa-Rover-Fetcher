package com.fevo.assignment.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fevo.assignment.cashing.Cash;
import com.fevo.assignment.cashing.impl.DbFetcher;
import com.fevo.assignment.cashing.impl.DbPersister;
import com.fevo.assignment.model.Response;
import com.fevo.assignment.model.RoverType;
import com.fevo.assignment.service.RoverService;

public class FetchServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
	private static final ConcurrentHashMap<String, Response> inMemoryRepo = new ConcurrentHashMap<String, Response>();
	
	private static final int N_DAYS = 10;
	
	//possible cash extensions [some parameters to use depending on which cash is being activated]
	
	//private static final String pathToFileRepo = "";
	
	//private static final String dbConnectionUrlRepo = "";
	
	@SuppressWarnings("unused")
	private Cash c;
	
	@PostConstruct
	public void init() {
		
		/*
		 * In general, regarding cashing requirement from the assignment. I decide to go with Strategy design pattern.
		 * The idea is to let us dynamically change implementation of algorithm to cash data, without using subclasses and inheritance.
		 * We can change behavior (algorithm) of our Cash during runtime (i.e favor composition over inheritance).
		 * Each Cash HAS-A persister and fetcher capabilities. With interfaces Persistable and Fetchable, we are delegating implementation
		 * to a particular class. For instance, DbFetcher/DbPersister are responsible for database cashing, FileFetcher/FilePersister for file system.
		 * The key here is that they all are interchangeable through a simple setter. Concurrency is next thing to think about though. 
		 
		 * However, for this particular example I decided to go with in-memory cash. That is, in order to try to tackle the above mentioned problem with concurrency,
		 * I make use of JDK's ConcurrentHashMap (since Tomcat creates new thread with each request and we can get into race condition).  
		 * I guess the actual database would take care of that, if we were using Spring Boot with any DB provider.
		 */
		
		c = new Cash(new DbPersister(), new DbFetcher());
		// or c = new Cash(new FilePersister(), new FileFetcher());
		 
	}

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
		@SuppressWarnings("unused")
		PrintWriter writer = resp.getWriter();
        
        //Some error checking. Probably there should be more protections, but this is just an example.
        
        RoverType roverType = RoverType.valueOfLabel(req.getParameter("Curiosity").toLowerCase());
        if (roverType == null) {
        	//return appropriate JSON response
        	return;
        }
        

        String dateKey = req.getParameter("earth_date");
        LocalDate date = LocalDate.parse(dateKey, formatter);
        long dateToMills = date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
        long now = System.currentTimeMillis();
        long nDaysAgo = now - TimeUnit.DAYS.toMillis(N_DAYS); 
        
       
        if (dateToMills <= now && dateToMills >= nDaysAgo) {

        	inMemoryRepo.computeIfAbsent(dateKey, key -> RoverService.getService().fetchResponse(roverType, req.getParameterMap()).orElseGet(null));

        	@SuppressWarnings("unused")
			Response r = inMemoryRepo.get(dateKey);
        	         
        	//use writer to return appropriate JSON response
        	
        	return;
        	
        } else {
        	//use writer to return appropriate JSON response
        	return;
        }

        
     }
	
	
	
}
