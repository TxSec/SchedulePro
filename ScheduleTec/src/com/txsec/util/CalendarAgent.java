package com.txsec.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class CalendarAgent {
	
	
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
	private Calendar calendar;
    
    
	public CalendarAgent(){
	calendar = GregorianCalendar.getInstance();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTable1 = new javax.swing.JTable(){

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
        public boolean isCellEditable(int row, int column) {                
            return false;               
    }
        
      
        
    };
    
    jScrollPane1.setViewportView(jTable1);
	}
	


	public javax.swing.JScrollPane getjScrollPane() {
		return jScrollPane1;
	}


	public void setjScrollPane(javax.swing.JScrollPane jScrollPane1) {
		this.jScrollPane1 = jScrollPane1;
	}


	public javax.swing.JTable getjTable() {
		return jTable1;
	}


	public void setjTable(javax.swing.JTable jTable1) {
		this.jTable1 = jTable1;
	}

	public void setTable(Calendar calendar) {
		int days  = calendar.getActualMaximum(Calendar.DATE);
		int dayofMoth = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int week = 0;
		int incrementDay = 0;
		String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(calendar.getTime());
		switch(dayOfWeek){
		case "Monday":
			incrementDay = 1;
			break;
		case "Tuesday":
			incrementDay = 2;
			break;
		case "Wednesday":
			incrementDay = 3;
			break;
		case "Thursday":
			incrementDay = 4;
			break;
		case "Friday":
			incrementDay = 5;
			break;
		case "Saturday":
			incrementDay = 6;
			break;
		case "Sunday":
			incrementDay = 0;
			break;
		}
		Object object[][] = new Object[6][7];
		for(int i = 1; i <= days;i++){
			if(incrementDay == 7){
				incrementDay = 0;
				week++;
			}
			object[week][incrementDay] = i;
			incrementDay++;
		}
	    jTable1.setModel(new javax.swing.table.DefaultTableModel(object,
                new String [] {
                    "Sun", "Mon", "Tue", "Wed","Thu","Fri","Sat"
                }
            ));		
	    for(int i = 0; i < 7;i++){
	   jTable1.getColumnModel().getColumn(i).setCellRenderer(r);
	    }
	    String text ="";
	    	for(int j = 0; j < jTable1.getRowCount();j++){
	    		  for(int i = 0 ; i < jTable1.getColumnCount();i++){
	    			  text = ""+ jTable1.getValueAt(j, i);
	    			  if(text.equalsIgnoreCase("null"))
	    				  text = "0";
	    	if(Integer.parseInt(text) == dayofMoth){
	    		  jTable1.changeSelection(j, i, false, false);
	    		  }
	    		  }
	    }

	    jTable1.setEnabled(true);
	    jTable1.getTableHeader().setResizingAllowed(false);
	   	jTable1.getTableHeader().setReorderingAllowed(false);
	    jTable1.setColumnSelectionAllowed(false);
	    MouseMotionListener[] listeners = jTable1.getMouseMotionListeners();
	    for (MouseMotionListener l : listeners)
	    {
	        jTable1.removeMouseMotionListener(l);
	    }
	   	jTable1.setCellSelectionEnabled(true);
	   	jTable1.setSelectionBackground(new Color(204,229,255));
	   	jTable1.setSelectionForeground(new Color(0,0,0));
	}
	
    DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object
            value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
            setHorizontalAlignment(JLabel.CENTER);
            setFont(getFont().deriveFont(10f));
            return this;
        }
    };
	
	
	
}
