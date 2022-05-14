package dumpUI3;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dumpUI3.model.KnowledgeBaseException;
import dumpUI3.model.entity.Report;

public class Observer {
	private static final Logger logger = LoggerFactory.getLogger(Observer.class);

	private ArrayList<EntityObserver<Report>> reportObservers = new ArrayList<EntityObserver<Report>>();

	public void addReportObserver(EntityObserver<Report> observer) {

		this.reportObservers.add(observer);
	}

	public void removeReportObserver(EntityObserver<Report> observer) {

		this.reportObservers.remove(observer);
	}

	public void fireReportAdded(List<Report> items) {

		int i;

		for (i = 0; i < this.reportObservers.size(); i++) {
			logger.info("fire imported dcoument added_1");
			this.reportObservers.get(i).entityAdded(items);
		}
	}

	public void fireReportRemoved(ArrayList<Report> items) {

		int i;

		for (i = 0; i < this.reportObservers.size(); i++) {

			this.reportObservers.get(i).entityRemoved(items);
		}
	}

	public void fireReportUpdated(ArrayList<Report> items) {

		int i;

		for (i = 0; i < this.reportObservers.size(); i++) {

			this.reportObservers.get(i).entityUpdated(items);
		}
	}

	public void fireReportRefresh() {

		int i;
		logger.info("fire affiliation refresh");
		for (i = 0; i < this.reportObservers.size(); i++) {

			this.reportObservers.get(i).entityRefresh();
		}
	}
	 public void fireKnowledgeBaseRefresh() throws KnowledgeBaseException {
		 logger.info("fireKnowledgeBaseRefresh");
		    
		     fireReportRefresh();
		  
		   }

}