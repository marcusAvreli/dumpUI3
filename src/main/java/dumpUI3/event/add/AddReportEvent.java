package dumpUI3.event.add;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dumpUI3.context.Context;
import dumpUI3.event.Event;
import dumpUI3.model.KnowledgeBaseException;
import dumpUI3.model.entity.Report;

public class AddReportEvent implements Event {

	  /***************************************************************************/
	  /*                        Private attributes                               */
	  /***************************************************************************/
	private static final Logger logger = LoggerFactory.getLogger(AddReportEvent.class);
	  /**
	   * 
	   */
	  private ArrayList<Report> reports;
	  
	  /***************************************************************************/
	  /*                            Constructors                                 */
	  /***************************************************************************/
	  
	  /**
	   * 
	   * @param affiliations 
	   */
	  public AddReportEvent(ArrayList<Report> reports) {
	    this.reports = reports;
	  }
	  
	  /**
	   * 
	   * @param affiliation 
	   */
	  public AddReportEvent(Report affiliation) {
	    this.reports = new ArrayList<Report>();
	    this.reports.add(affiliation);
	  }
	  
	  /***************************************************************************/
	  /*                           Public Methods                                */
	  /***************************************************************************/
	  
	  /**
	   * 
	   * @throws KnowledgeBaseException 
	   */
	  public void fireEvent() throws KnowledgeBaseException {
	    logger.info("FireReport event");
	    Context.getInstance().getKbObserver().fireReportAdded(reports);
	  }
	  
	  /***************************************************************************/
	  /*                           Private Methods                               */
	  /***************************************************************************/
	}
