package dumpUI3.model;



import dumpUI3.model.KnowledgeBaseException;



/**
 * This class implements all the necessary methods to manage the knowledge base.
 * 
 * @author mjcobo
 */
public class KnowledgeBaseManager {

  /***************************************************************************/
  /*                        Private attributes                               */
  /***************************************************************************/
  
  
  String basePath = "http://localhost:8080/reportService";
  /***************************************************************************/
  /*                            Constructors                                 */
  /***************************************************************************/
 
  /***************************************************************************/
  /*                           Public Methods                                */
  /***************************************************************************/
  //---------------------------------------------------------------------------
  // Data base creation and management.
  //---------------------------------------------------------------------------
  /**
   * 
   * @param filePath
   * @return
   * @throws KnowledgeBaseException
   */
  public void createKnowledgeBase(String filePath) throws KnowledgeBaseException {

    
     
        
  }
  /***************************************************************************/
  /*                           Private Methods                               */
  /***************************************************************************/

public String getBasePath() {
	return basePath;
}
}
