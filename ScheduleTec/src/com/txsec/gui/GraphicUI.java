package com.txsec.gui;

import java.awt.AWTException;

import java.awt.Color;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.txsec.engine.Engine;
import com.txsec.main.Settings;
import com.txsec.task.impl.ClockTask;
import com.txsec.util.CalendarAgent;
import com.txsec.util.FileUtil;

import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;



public class GraphicUI extends JFrame implements ActionListener,MouseListener {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private JMenuItem jMenuItemExit;
    private JMenuItem jMenuAbout;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private Date date;
    private Engine engine;
	private TaskDialog taskDialog;
	private EditPane editPane;
	private CalendarAgent calendarAgent;
	private Calendar calendar;
	private JMenuItem jMenuItemUpdate;
	private JTextField textField;
	private JTable table;
	private boolean clicked;
	private DefaultTableModel dtm;
	private String textDay;
	private JButton btnViewTask;
	
    


	public GraphicUI(Engine engine){
		calendar = GregorianCalendar.getInstance();
		this.engine = engine;
		calendarAgent = new CalendarAgent();
		initComponents();
	}
	

	private void initComponents() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e2) {
			e2.printStackTrace();
		}
		date = Calendar.getInstance().getTime();
		jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuItemUpdate = new javax.swing.JMenuItem();
        jMenuAbout = new JMenuItem();
        jLabel4 = new javax.swing.JLabel();

        /**
         * All JFrame attributes.
         */
        setLocationRelativeTo(null);
        setTitle(Settings.TITLE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
			setIconImage(ImageIO.read(GraphicUI.class.getResourceAsStream("/schedule.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        
      //Check the SystemTray is supported
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        
			TrayIcon trayIcon = null;
			
			try {
				trayIcon = new TrayIcon(ImageIO.read(GraphicUI.class.getResourceAsStream("/schedule.png")));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        
		final SystemTray tray = SystemTray.getSystemTray();
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
			
		});
		MenuItem open = new MenuItem("Open");
		open.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
			}
			
		});
	    final PopupMenu popup = new PopupMenu();
	    popup.add(open);
	    popup.add(exitItem);
	    trayIcon.setPopupMenu(popup);
	    trayIcon.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!isVisible()){
					setVisible(true);
					setState(Frame.NORMAL);
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
        
	    
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
        
        addWindowStateListener(new WindowStateListener() {

            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == ICONIFIED) {
					setVisible(false);
                }
            }
        });

        jButton1.setText("Add Task");
        jButton1.addActionListener(this);
        jButton2.setText("Remove Task");
        
        JScrollPane scrollPane = new JScrollPane();
        
        
        table = new JTable(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
	        public boolean isCellEditable(int row, int column) {      
	        	if(column == 0)
	        		return true;
	        		else 
	            return false;     
	        }
	        
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex == 0){
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }
	        
	        
        };
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        scrollPane.setViewportView(table);
        table.setSurrendersFocusOnKeystroke(true);
        dtm = new DefaultTableModel(0, 0);
        String header[] = new String[] { "#", "Task Title", "Start Date",
                "End Date"};
        dtm.setColumnIdentifiers(header);
        table.setModel(dtm);
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(178);
        table.getColumnModel().getColumn(1).setMinWidth(16);
        table.getColumnModel().getColumn(2).setPreferredWidth(65);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(3).setPreferredWidth(65);
        table.setRowHeight(29);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setCellSelectionEnabled(false);
        
        JButton btnEditTask = new JButton();
        btnEditTask.setText("Edit Task");
        
        btnViewTask = new JButton();
        btnViewTask.setText("View Task");
        btnViewTask.addActionListener(this);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(scrollPane, 0, 0, Short.MAX_VALUE)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(btnViewTask, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
        					.addGap(10)
        					.addComponent(jButton1)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(btnEditTask, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jButton2)))
        			.addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap(35, Short.MAX_VALUE)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnViewTask)
        				.addComponent(jButton1)
        				.addComponent(btnEditTask)
        				.addComponent(jButton2))
        			.addContainerGap())
        );
        jPanel1.setLayout(jPanel1Layout);

        calendarAgent.getjTable().setDragEnabled(false);
        calendarAgent.getjTable().setShowGrid(false);
        calendarAgent.getjTable().addMouseListener(this);
        calendarAgent.getjTable().setEnabled(false);
        calendarAgent.setTable(calendar);
        calendarAgent.getjTable().setRowHeight(25);
        textDay = calendarAgent.getjTable().
				getModel().getValueAt(calendarAgent.getjTable().getSelectedRow(), calendarAgent.getjTable().getSelectedColumn()).toString();
        table.getColumnModel().getColumn(1).setHeaderValue("Day "+textDay+" Description Task's");	
		
       
        loadFiles();
        
        
		repaint();
        engine.pushTask(new ClockTask(jLabel1));
        JScrollPane scrollPane_2 = new JScrollPane();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2Layout.setHorizontalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
        				.addComponent(jLabel1))
        			.addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel1)
        			.addGap(18)
        			.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(126, Short.MAX_VALUE))
        );
        scrollPane_2.setViewportView(calendarAgent.getjTable());
        jPanel2.setLayout(jPanel2Layout);

        jMenu1.setText("File");

       
        jMenuItemUpdate.setText("Update");
        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(this);
        jMenuAbout.setText("About");
        jMenuAbout.addActionListener(this);
        jMenu1.add(jMenuAbout);
        jMenu1.add(jMenuItemExit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);
        jPanel1.setBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(192, 192, 192)));
        
        JPanel panel = new JPanel();
        panel.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(192, 192, 192)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
        			.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(panel, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
        		.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
        		.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
        );
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        tabbedPane.setBorder(null);
        JPanel pane = new JPanel();
        pane.setBorder(null);
        JPanel pane2 = new JPanel();
        JPanel pane3 = new JPanel();
        
        tabbedPane.addTab("FriendList",pane);
        tabbedPane.addTab("Request",pane2);
        tabbedPane.addTab("Options",pane3);
        
        textField = new JTextField();
        textField.setColumns(10);
        
        JButton btnNewButton = new JButton("Send Request");
        GroupLayout gl_pane2 = new GroupLayout(pane2);
        gl_pane2.setHorizontalGroup(
        	gl_pane2.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_pane2.createSequentialGroup()
        			.addComponent(textField, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnNewButton))
        );
        gl_pane2.setVerticalGroup(
        	gl_pane2.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_pane2.createSequentialGroup()
        			.addContainerGap(258, Short.MAX_VALUE)
        			.addGroup(gl_pane2.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnNewButton)))
        );
        pane2.setLayout(gl_pane2);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
        );
        panel.setLayout(gl_panel);
        getContentPane().setLayout(layout);

        pack();
    }   

	private void loadFiles() {
        String splitInfo[] = FileUtil.readAllFiles();
        for(int i = 0; i < splitInfo.length-1;)
        	dtm.addRow(new Object[]{null,splitInfo[i++],splitInfo[i++],splitInfo[i++]});
}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jMenuItemExit){
			dispose();
		} if(e.getSource() == jMenuAbout){
			javax.swing.JOptionPane.showMessageDialog(this, "Made by TxSec \nVersion: "+Settings.VERSION,"About", JOptionPane.INFORMATION_MESSAGE);
			
		} else if(e.getSource() == jButton1){
		
			taskDialog = new TaskDialog(this);
			taskDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
			taskDialog.requestFocus();
			try {
				taskDialog.setIconImage(ImageIO.read(GraphicUI.class.getResourceAsStream("/schedule.png")));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			taskDialog.setTitle(Settings.TITLE+"- Add Task");
			taskDialog.setAlwaysOnTop(true);
			taskDialog.setLocationRelativeTo(this); //relativeTo is the name of parent frame
			taskDialog.setVisible(true);
			taskDialog.setResizable(false);
		} else if(e.getSource() == btnViewTask){
			editPane = new EditPane();
			editPane.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			editPane.requestFocus();
			try {
				editPane.setIconImage(ImageIO.read(GraphicUI.class.getResourceAsStream("/schedule.png")));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			editPane.setTitle(Settings.TITLE+"- Add Task");
			editPane.setAlwaysOnTop(true);
			editPane.setLocationRelativeTo(this); //relativeTo is the name of parent frame
			editPane.setVisible(true);
			editPane.setResizable(false);
			editPane.pack();
		}
		
	}


	@Override
	public void mouseClicked(MouseEvent mouse) {
		textDay = ""+calendarAgent.getjTable().
				getModel().getValueAt(calendarAgent.getjTable().getSelectedRow(), calendarAgent.getjTable().getSelectedColumn());
		if(textDay.equalsIgnoreCase("null")){
			textDay = "0";
		}
		if(!textDay.equalsIgnoreCase("0")){
		table.getColumnModel().getColumn(1).setHeaderValue("Day "+textDay+" Description Task's");
		repaint();
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		textDay = ""+calendarAgent.getjTable().
				getModel().getValueAt(calendarAgent.getjTable().getSelectedRow(), calendarAgent.getjTable().getSelectedColumn());
		if(textDay.equalsIgnoreCase("null")){
			textDay = "0";
		}
		if(!textDay.equalsIgnoreCase("0")){
		table.getColumnModel().getColumn(1).setHeaderValue("Day "+textDay+" Description Task's");
		repaint();
		}
		
	}
	
	public String getTextDay() {
		return textDay;
	}


	public void setTextDay(String textDay) {
		this.textDay = textDay;
	}


	public DefaultTableModel getDtm() {
		return dtm;
	}


	public void setDtm(DefaultTableModel dtm) {
		this.dtm = dtm;
	}
}
