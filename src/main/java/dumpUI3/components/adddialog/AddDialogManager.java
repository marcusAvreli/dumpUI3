package dumpUI3.components.adddialog;

import java.util.ArrayList;
import javax.swing.JFrame;
import dumpUI3.model.entity.Report;


/**
 *
 * @author mjcobo
 */
public class AddDialogManager {

  /***************************************************************************/
  /*                        Private attributes                               */
  /***************************************************************************/

  /**
   *
   */
  private AddAffiliationDialog addAffiliationDialog;
  

  /***************************************************************************/
  /*                            Constructors                                 */
  /***************************************************************************/

  private AddDialogManager() {
    
  }

  /***************************************************************************/
  /*                           Public Methods                                */
  /***************************************************************************/

  /**
   * 
   * @return
   */
  public static AddDialogManager getInstance() {

    return AddDialogManagerHolder.INSTANCE;
  }

  /**
   *
   */
  private static class AddDialogManagerHolder {

    private static final AddDialogManager INSTANCE = new AddDialogManager();
  }

  /**
   *
   * @param frame
   */
  public void init(JFrame frame) {

    this.addAffiliationDialog = new AddAffiliationDialog(frame);
  
  }

  /**
   * 
   */
  public void showAddAffiliationDialog() {

    this.addAffiliationDialog.refresh();
    this.addAffiliationDialog.setVisible(true);
  }

 
  
  /***************************************************************************/
  /*                           Private Methods                               */
  /***************************************************************************/
}