package dumpUI3.events;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dumpUI3.event.Event;
import dumpUI3.model.KnowledgeBaseException;



public class EventsReceiver {
	private static final Logger logger = LoggerFactory.getLogger(EventsReceiver.class);

	private ArrayList<Event> events;

	private EventsReceiver() {

		this.events = new ArrayList<Event>();
	}

	public static EventsReceiver getInstance() {
		return EventsReceiverSingleton.INSTANCE;
	}

	private static class EventsReceiverSingleton {
		private static final EventsReceiver INSTANCE = new EventsReceiver();
	}

	public void addEvent(Event event) {

		this.events.add(event);
	}

	public void fireEvents() {
		logger.info("fire_events");
		int i;

		for (i = 0; i < this.events.size(); i++) {

			try {
				this.events.get(i).fireEvent();
			} catch (KnowledgeBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.events.clear();
	}

}