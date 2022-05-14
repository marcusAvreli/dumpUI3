package dumpUI3.context;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import dumpUI3.Observer;
import dumpUI3.StateObserver;
import dumpUI3.model.KnowledgeBaseException;
import dumpUI3.model.KnowledgeBaseManager;
import dumpUI3.model.dao.FactoryDAO;

public class Context {

	private static final Logger logger = LoggerFactory.getLogger(Context.class);

	private Observer kbObserver = new Observer();
	private LinkedList<StateObserver> stateObservers = new LinkedList<StateObserver>();
	private KnowledgeBaseManager kbm;
	private FactoryDAO factoryDAO;
	private Context() {

	}

	public static Context getInstance() {
		return ContextSingleton.INSTANCE;
	}

	private static class ContextSingleton {
		private static final Context INSTANCE = new Context();
	}

	public Observer getKbObserver() {
		return kbObserver;
	}

	public void addStateObserver(StateObserver observer) {
		// Añadimos el observador.
		this.stateObservers.add(observer);

		// Le notificamos el estado actual de la base de conocimiento.
		// observer.stateObservers(isKnowledbaseLoaded());
	}

	public boolean removeStateObserver(StateObserver observer) {
		return this.stateObservers.remove(observer);
	}

	private void notifyStateObsever(boolean state) {

		for (int i = 0; i < stateObservers.size(); i++) {
			stateObservers.get(i).stateChanged(state);
		}
	}
	 public void loadProject() throws KnowledgeBaseException {
		    logger.info("load Project");

		    if (! isKnowledbaseLoaded()) {
		    	logger.info("loadProject");
		      try {

		        

		        this.kbm = new KnowledgeBaseManager();

		        
		        this.factoryDAO = new FactoryDAO(this.kbm);

		        this.notifyKnowledgeBaseObsever(isKnowledbaseLoaded());
		        this.kbObserver.fireKnowledgeBaseRefresh();

		      } catch (KnowledgeBaseException e) {
		        
		        this.kbm = null;

		        throw e;
		      }
		    }
		  }
	 public FactoryDAO getFactoryDAO() {
		return factoryDAO;
	}

	public void setFactoryDAO(FactoryDAO factoryDAO) {
		this.factoryDAO = factoryDAO;
	}

	public boolean isKnowledbaseLoaded() {
		  if(this.kbm != null) {
			  logger.info("knowledge base is not null");
		  }else {
			  logger.info("knowledge base is null");
		  }
	    return this.kbm != null;
	  }
	 private void notifyKnowledgeBaseObsever(boolean state) {
		  logger.info("notifyKnowledgeBaseObsever:");
	    for (int i = 0; i < stateObservers.size(); i++) {
	    	stateObservers.get(i).stateChanged(state);
	    }
	  }
}
