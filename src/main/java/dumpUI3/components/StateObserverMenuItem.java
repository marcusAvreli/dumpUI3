package dumpUI3.components;

import javax.swing.JMenuItem;

import dumpUI3.StateObserver;
import dumpUI3.context.Context;


public class StateObserverMenuItem extends JMenuItem
        implements StateObserver {

  /***************************************************************************/
  /*                        Private attributes                               */
  /***************************************************************************/

  /***************************************************************************/
  /*                            Constructors                                 */
  /***************************************************************************/

  /**
	 * 
	 */
	private static final long serialVersionUID = -5978235019707717123L;


/**
   * 
   */
  public StateObserverMenuItem() {
    super();

    init();
  }

  /***************************************************************************/
  /*                           Public Methods                                */
  /***************************************************************************/


  public void stateChanged(boolean loaded) {
    setEnabled(loaded);
  }


  private void init() {

    
    Context.getInstance().addStateObserver(this);
  }
}