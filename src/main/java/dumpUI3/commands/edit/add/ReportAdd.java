package dumpUI3.commands.edit.add;

import javax.sql.rowset.spi.XmlReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dumpUI3.Command;
import dumpUI3.context.Context;
import dumpUI3.events.EventsReceiver;


public class ReportAdd  implements Command{
	private static final Logger logger = LoggerFactory.getLogger(ReportAdd.class);
	private XmlReader xmlReader;
	public XmlReader getXmlReader() {
		return xmlReader;
	}
	public void setXmlReader(XmlReader xmlReader) {
		this.xmlReader = xmlReader;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		logger.info("read xml");
		//boolean successful = Context.getInstance().getXmlReadDAO().xmlReadDAO();
		EventsReceiver.getInstance().fireEvents();
	
	}

}
