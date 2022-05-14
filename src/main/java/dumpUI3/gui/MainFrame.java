package dumpUI3.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dumpUI3.commands.task.ShowManagerTask;
import dumpUI3.components.StateObserverMenuItem;
import dumpUI3.components.adddialog.AddDialogManager;
import dumpUI3.components.manager.WordManager;

public class MainFrame extends JFrame {
	private StateObserverMenuItem globalReplaceWordGroupMenuItem;
	private JPanel mainPanel;
	private JMenu editMenu;
	private JMenu globalReplaceMenu;
	private JMenuBar mainMenuBar;
	private final WordManager wordManager = new WordManager();
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(MainFrame.class);

	public MainFrame() {

		logger.info("CheckPost_1");
		initComponents();
		logger.info("CheckPost_2");
		AddDialogManager.getInstance().init(this);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	public void clearMainPanel() {

		this.mainPanel.removeAll();
		this.mainPanel.validate();
		this.mainPanel.repaint();
	}

	@SuppressWarnings("unchecked")

	private void initComponents() {
		 
		 mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		// GenericDetailPanel<Report> panel = new ReportDetailPanel();
		editMenu = new JMenu();
		globalReplaceMenu = new JMenu();		
		mainMenuBar = new JMenuBar();
		globalReplaceWordGroupMenuItem = new StateObserverMenuItem();
		// mainPanel.add(panel);

		
	   

		
	    editMenu.setText("Report Manager");
	    globalReplaceMenu.setText("Report");
	    globalReplaceWordGroupMenuItem.setText("Define");
	    
	    editMenu.add(globalReplaceMenu);
	    globalReplaceMenu.add(globalReplaceWordGroupMenuItem);
		
		mainMenuBar.add(editMenu);
		
		globalReplaceWordGroupMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
			      globalReplaceWordGroupMenuItemActionPerformed(evt);
				
			}
		}) ;
		
		//add(mainMenuBar, BorderLayout.NORTH);
		setJMenuBar(mainMenuBar);
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout); layout.setHorizontalGroup(
		layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE) );
		layout.setVerticalGroup(
		layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(layout.createSequentialGroup()

		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)) );

		
		pack();
		
	}
	private void globalReplaceWordGroupMenuItemActionPerformed(ActionEvent evt) {
		 (new ShowManagerTask(this.mainPanel, this.wordManager)).execute();
	}

}
