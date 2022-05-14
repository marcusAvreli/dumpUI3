package dumpUI3.components.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dumpUI3.App;
import dumpUI3.components.adddialog.AddDialogManager;
import dumpUI3.components.globalslavepanel.WordGlobalSlavePanel;
import dumpUI3.components.itemslist.WordsListPanel;
import dumpUI3.model.entity.Report;

/**
*
* @author mjcobo
*/
public class WordManager extends GenericItemManagerPanel<Report> {

 /***************************************************************************/
 /*                        Private attributes                               */
 /***************************************************************************/

 /***************************************************************************/
 /*                            Constructors                                 */
 /***************************************************************************/
	private static final Logger logger = LoggerFactory.getLogger(WordManager.class);
 /**
  * 
  */
 public WordManager() {
   super(new WordsListPanel(),
         new WordGlobalSlavePanel());

   setMasterPanelTitle("Words list");
   setSlavePanelTitle("Word detail");
 }

 /***************************************************************************/
 /*                           Public Methods                                */
 /***************************************************************************/

 /**
  *
  */
 @Override
 public void addAction() {
	 logger.info("add operation called");
	 AddDialogManager.getInstance().showAddAffiliationDialog();
 }

 /**
  *
  */
 @Override
 public void moveToAction(List<Report> items) {
   //JoinEntitiesDialogManager.getInstance().showWordsJoinDialog(items);
 }

 /**
  *
  */
 @Override
 public void removeAction(List<Report> items) {
  // (new PerformKnowledgeBaseEditTask(new DeleteWordEdit(items), this)).execute();
 }

 /***************************************************************************/
 /*                           Private Methods                               */
 /***************************************************************************/
}