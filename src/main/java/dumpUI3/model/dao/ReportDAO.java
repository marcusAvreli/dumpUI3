package dumpUI3.model.dao;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import dumpUI3.event.add.AddReportEvent;
import dumpUI3.events.EventsReceiver;
import dumpUI3.model.KnowledgeBaseException;
import dumpUI3.model.KnowledgeBaseManager;
import dumpUI3.model.entity.Report;
import dumpUI3.model.dao.SimpleHttpAsyncClientDemo;
import dumpUI3.model.dao.SimpleHttpAsyncClientDemo.AsyncHandler;



public class ReportDAO {
	
	/***************************************************************************/
/* Private attributes */
/***************************************************************************/
private static final Logger logger = LoggerFactory.getLogger(ReportDAO.class);
/**
 * The knowlege base manager
 */
private KnowledgeBaseManager kbm;
private Statement statDeleteReport;
private String deleteReport;
private Statement statUpdateReport;
private String updateReport;
private Statement stateSelectReportById;
private String selectReportById;
private Statement stateCheckReportByName;
private String checkReportByName;
private Statement statSelectReports;
private String selectReports;
private Statement statAddReport;
private String addReport;

public ReportDAO(KnowledgeBaseManager kbm) throws KnowledgeBaseException {

	this.kbm = kbm;

}
/*
 public boolean addReport(Report affiliation, boolean notifyObservers)
          throws KnowledgeBaseException{

    return addReport(affiliation.getId(),
                          affiliation.getName(),
                          notifyObservers);
  }*/
 

 public Integer addReport(String fullAffiliation, boolean notifyObservers)
          throws KnowledgeBaseException{

    Integer id=-1;

   
    	String basePath = this.kbm.getBasePath();
    	String endpointName="/addReport";
    	String requestPath=basePath+endpointName;
    	logger.info("fullAffiliation:"+fullAffiliation);
    	logger.info("basePath:"+basePath);
    	logger.info("endpointName:"+endpointName);
    	logger.info("requestPath:"+requestPath);
    	Report report = new Report();
    	report.setName(fullAffiliation);
       
    	AsyncHandler handler = new AsyncHandler();  

    	 Map<String, Object> map = new HashMap<String, Object>();  
    	    map.put("code", "js");  
    	    map.put("day", "0");  
    	    map.put("city", "Shanghai");  
    	    map.put("dfc", "1");  
    	    map.put("charset", "utf-8"); 
    	    map.put("report", report); 
    	    id=3;
    	    try {
    			SimpleHttpAsyncClientDemo.send(requestPath, map, "utf-8", handler);
    		} catch (KeyManagementException | NoSuchAlgorithmException | IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}  
    // Notify to the observer
    if (notifyObservers) {
    
     EventsReceiver.getInstance().addEvent(new AddReportEvent(new Report()));
    }

   
 
    return id;
 }
 
}
