package dumpUI3;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Broker {
	   private List<Command> commands = new ArrayList<Command>(); 
	   private static final Logger logger = LoggerFactory.getLogger(Broker.class);
	   public void addCommand(Command command){
		   commands.add(command);		
	   }

	   public void executeCommands(){
	   
	      for (Command command : commands) {
	    	  command.execute();
	      }
	      commands.clear();
	   }

		
		
	}