package com.txsec.gui;

import javax.swing.JDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.txsec.util.CalendarAgent;
import com.txsec.util.FileUtil;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class TaskDialog extends JDialog implements ActionListener,MouseListener{
	
	private CalendarAgent calendar;
	private Calendar calendarTime;
	private JButton btnNewButton;
	private GraphicUI graphicUI;
	private String newTime;
	
	public TaskDialog(GraphicUI graphicUI) {
		this.graphicUI = graphicUI;
		calendar = new CalendarAgent();
		calendar.getjTable().setForeground(new Color(0,0,0));
		calendarTime = GregorianCalendar.getInstance();
		textField_1 = new JTextField();
		textField_1.setText(calendarTime.getTime().toLocaleString());
		textField_1.setEditable(false);
		textField_1.setColumns(11);
		calendar.setTable(calendarTime);
		
		JPanel panel = new JPanel();
		btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(this);
		calendar.getjTable().addMouseListener(this);
		JScrollPane scrollPane = new JScrollPane();
		
		
		textField_2 = new JTextField();
		textField_2.setText("DD/MM/YY HH/MM/SS");
		textField_2.setEditable(false);
		textField_2.setColumns(11);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		
		JLabel lblEndDate = new JLabel("End Date:");
		
		JLabel lblNewLabel_1 = new JLabel("Add a new task...");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(89)
							.addComponent(lblNewLabel_1)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblStartDate))
									.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblEndDate, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndDate)
						.addComponent(lblStartDate))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
		);
		scrollPane.setViewportView(calendar.getjTable());
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(0, 2, 0, 0, (Color) Color.LIGHT_GRAY));
		
		JPanel panel_2 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(panel_2, 0, 0, Short.MAX_VALUE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
		);
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addContainerGap(517, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblDescription = new JLabel("Description:");
		
		JLabel lblNewLabel = new JLabel("Write here:");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
						.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
						.addComponent(lblDescription, Alignment.LEADING)
						.addComponent(lblNewLabel, Alignment.LEADING))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(14)
					.addComponent(lblDescription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		txtrWriteHere = new JTextArea();
		txtrWriteHere.setToolTipText("");
		scrollPane_1.setViewportView(txtrWriteHere);
		panel_1.setLayout(gl_panel_1);
		getContentPane().setLayout(groupLayout);
		this.pack();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea txtrWriteHere;


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNewButton){
			calendarTime = GregorianCalendar.getInstance();
			graphicUI.getDtm().addRow(new Object[]{null,textField.getText(),calendarTime.getTime().toLocaleString(),newTime});
			String data = textField.getText()+"*"+textField_1.getText()+"*"+newTime;
			//+txtrWriteHere.getText() REST OF THE INFO
			FileUtil.writeFile("Day"+graphicUI.getTextDay()+".txt",	data.getBytes());
			setVisible(false);
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		calendarTime = GregorianCalendar.getInstance();
		String editTime = calendarTime.getTime().toLocaleString();
		newTime = "";
		String splitTime[] = editTime.split("-");
		splitTime[0] = calendar.getjTable().getModel().getValueAt(calendar.getjTable().getSelectedRow(), 
				calendar.getjTable().getSelectedColumn()).toString();
		for(int i = 0; i < splitTime.length;i++){
			if(i == splitTime.length-1)
				newTime +=splitTime[i];
			else
			newTime +=splitTime[i]+"-";
		}
		textField_2.setText(newTime);
		repaint();
	}


	@Override
	public void mouseEntered(MouseEvent e) {
	}


	@Override
	public void mouseExited(MouseEvent e) {
	}


	@Override
	public void mousePressed(MouseEvent e) {
	}


	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
