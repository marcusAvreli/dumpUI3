package dumpUI3.commands.task;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class ShowManagerTask {

	  /***************************************************************************/
	  /*                        Private attributes                               */
	  /***************************************************************************/

	  /**
	   * The component where the panel will be added
	   */
	  private JComponent receiver;

	  /**
	   * The panel to add.
	   */
	  private JPanel panel;

	  /***************************************************************************/
	  /*                            Constructors                                 */
	  /***************************************************************************/

	  /**
	   * Contructs a new {@link ShowManagerTask}.
	   * 
	   * @param receiver The component where the panel will be added
	   * @param panel The panel to add
	   */
	  public ShowManagerTask(JComponent receiver, JPanel panel) {
	    this.receiver = receiver;
	    this.panel = panel;
	  }

	  /***************************************************************************/
	  /*                           Public Methods                                */
	  /***************************************************************************/

	  /**
	   * 
	   */
	  public void execute() {

	    this.receiver.removeAll();
	    this.receiver.add(this.panel);
	    this.receiver.validate();
	    this.receiver.repaint();
	  }

	  /***************************************************************************/
	  /*                           Private Methods                               */
	  /***************************************************************************/
}
