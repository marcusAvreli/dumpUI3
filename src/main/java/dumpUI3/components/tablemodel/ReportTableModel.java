package dumpUI3.components.tablemodel;

import dumpUI3.model.entity.Report;

public class ReportTableModel extends GenericDynamicTableModel<Report> {

	  /***************************************************************************/
	  /*                        Private attributes                               */
	  /***************************************************************************/

	  /***************************************************************************/
	  /*                            Constructors                                 */
	  /***************************************************************************/

	  /**
	   *
	   */
	  public ReportTableModel() {
	    super(new String[] {"ID", "Name"});
	   
	  }

	  /***************************************************************************/
	  /*                           Public Methods                                */
	  /***************************************************************************/

	  /**
	   *
	   * @param rowIndex
	   * @param columnIndex
	   * @return
	   */
	  @Override
	  public Object getValueAt(int rowIndex, int columnIndex) {

	    if ((rowIndex >= 0) && (rowIndex < getRowCount())) {

	      Report report = getItem(rowIndex);

	      switch (columnIndex) {

	        case 0:
	          return report.getId();

	        case 1:
	          return report.getName();

	      

	        default:
	          return "";
	      }

	    } else {

	      return "";

	    }
	  }

	  /***************************************************************************/
	  /*                           Private Methods                               */
	  /***************************************************************************/
	}
