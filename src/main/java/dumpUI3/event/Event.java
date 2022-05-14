package dumpUI3.event;

import dumpUI3.model.KnowledgeBaseException;

public interface Event {
	public void fireEvent() throws KnowledgeBaseException;
}