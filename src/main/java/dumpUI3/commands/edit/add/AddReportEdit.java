package dumpUI3.commands.edit.add;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dumpUI3.App;
import dumpUI3.commands.edit.KnowledgeBaseEdit;

import dumpUI3.events.EventsReceiver;
import dumpUI3.model.KnowledgeBaseException;
import dumpUI3.model.entity.Report;

import dumpUI3.context.Context;

/**
 *
 * @author mjcobo
 */
public class AddReportEdit extends KnowledgeBaseEdit {
	private static final Logger logger = LoggerFactory.getLogger(AddReportEdit.class);
  /***************************************************************************/
  /*                        Private attributes                               */
  /***************************************************************************/

  /**
   * The ID of the Report
   */
  private Integer affiliationID;

  /**
   * This attribute contains the complete affiliation.
   */
  private String fullAffilliation;

  /**
   * The elements added
   */
  private ArrayList<Report> affiliationsAdded;

  /***************************************************************************/
  /*                            Constructors                                 */
  /***************************************************************************/

  /**
   * 
   * @param fullAffilliation
   */
  public AddReportEdit(String fullAffilliation) {
    super();

    this.fullAffilliation = fullAffilliation;
    this.affiliationsAdded = new ArrayList<Report>();
  }

  /***************************************************************************/
  /*                           Public Methods                                */
  /***************************************************************************/

  /**
   * 
   * @throws KnowledgeBaseException
   */
  @Override
  public boolean execute()  {

    boolean successful = false;
    logger.info("executing add operation");
    boolean notifyObservers =true;
    try {

      if (this.fullAffilliation == null) {

        successful = false;
        this.errorMessage = "The full affiliation can not be null.";
      
      } 
        
    	 int id = Context.getInstance().getFactoryDAO().getReportDAO().addReport(fullAffilliation, notifyObservers);
    	  if(id==-1)
      { // Check the integrity

        successful = false;
        this.errorMessage = "An Report yet exists with this full affiliation.";

      }

       
      

    } catch (KnowledgeBaseException e) {

     

      successful = false;
      this.errorMessage = "An exception happened.";

      
    }

    return successful;
  
  }

  /**
   *
   * @throws CannotUndoException
   */
  
  

  /***************************************************************************/
  /*                           Private Methods                               */
  /***************************************************************************/
}
