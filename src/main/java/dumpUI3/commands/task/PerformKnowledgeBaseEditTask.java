package dumpUI3.commands.task;

 import java.awt.Window;
import javax.swing.JComponent;
import dumpUI3.commands.edit.KnowledgeBaseEdit;




/**
 *
 * @author mjcobo
 */
public class PerformKnowledgeBaseEditTask {

  /***************************************************************************/
  /*                        Private attributes                               */
  /***************************************************************************/

  /**
   * 
   */
  private KnowledgeBaseEdit edit;
  private JComponent component;

  private boolean successful;


  /***************************************************************************/
  /*                            Constructors                                 */
  /***************************************************************************/

  /**
   * 
   * @param edit
   * @param compenent
   */
  public PerformKnowledgeBaseEditTask(KnowledgeBaseEdit edit, JComponent compenent) {

    this.edit = edit;
    this.component = compenent;
    this.successful = false;
  }

  /***************************************************************************/
  /*                           Public Methods                                */
  /***************************************************************************/

  /**
   * 
   */
  public void execute() {

   
      
   
      this.successful = this.edit.execute();
    

     

  
  }

  /**
   * 
   * @return
   */
  public boolean isSuccessful() {
    return successful;
  }

  /***************************************************************************/
  /*                           Private Methods                               */
  /***************************************************************************/
}