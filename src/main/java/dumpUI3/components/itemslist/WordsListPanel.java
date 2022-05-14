package dumpUI3.components.itemslist;


import dumpUI3.components.manager.GenericDynamicItemsListPanel;
import dumpUI3.components.tablemodel.ReportTableModel;
import dumpUI3.context.Context;
import dumpUI3.model.entity.Report;

import java.util.List;

import dumpUI3.EntityObserver;

/**
 *
 * @author mjcobo
 */
public class WordsListPanel 
extends GenericDynamicItemsListPanel<Report> 
implements EntityObserver<Report> {

  /***************************************************************************/
  /*                        Private attributes                               */
  /***************************************************************************/
  
  /***************************************************************************/
  /*                            Constructors                                 */
  /***************************************************************************/  

  /**
   * 
   * @param tableModel 
   */
  public WordsListPanel() {
    super(new ReportTableModel());
    
    Context.getInstance().getKbObserver().addReportObserver(this);
  }
  
  /***************************************************************************/
  /*                           Public Methods                                */
  /***************************************************************************/

  /**
   * 
   * @param items
   * @throws KnowledgeBaseException 
   */
  public void entityAdded(List<Report> items) {
    addItems(items);
  }

  /**
   * 
   * @param items
   * @throws KnowledgeBaseException 
   */
  public void entityRemoved(List<Report> items)  {
    removeItems(items);
  }

  /**
   * 
   * @param items
   * @throws KnowledgeBaseException 
   */
  public void entityUpdated(List<Report> items)  {
    updateItems(items);
  }

  /**
   * 
   * @throws KnowledgeBaseException 
   */
  public void entityRefresh()  {
   // refreshItems(Context.getInstance().getFactoryDAO().getWordDAO().getWords());
  }
  
  /***************************************************************************/
  /*                           Private Methods                               */
  /***************************************************************************/
}