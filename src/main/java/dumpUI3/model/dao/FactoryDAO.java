package dumpUI3.model.dao;

import dumpUI3.model.KnowledgeBaseException;
import dumpUI3.model.KnowledgeBaseManager;

/**
*
* @author mjcobo
*/
public class FactoryDAO {

/***************************************************************************/
/*                        Private attributes                               */
/***************************************************************************/

private KnowledgeBaseManager kbm;

private ReportDAO affiliationDAO;
private ReportDAO reportDAO;


/***************************************************************************/
/*                            Constructors                                 */
/***************************************************************************/

public FactoryDAO(KnowledgeBaseManager kbm) throws KnowledgeBaseException {

  this.kbm = kbm;

  affiliationDAO = new ReportDAO(kbm);
  reportDAO = new ReportDAO(kbm);
 
}

/***************************************************************************/
/*                           Public Methods                                */
/***************************************************************************/

/**
 * @return the kbm
 */
public KnowledgeBaseManager getKbm() {
  return kbm;
}

public ReportDAO getReportDAO() {
	    return reportDAO;
	  }

/***************************************************************************/
/*                           Private Methods                               */
/***************************************************************************/
}

