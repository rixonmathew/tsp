package com.rixon.tsp.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddCityDialog extends JDialog{
	
	private static final long serialVersionUID = 1L;

	public AddCityDialog(JFrame owner) {
		super(owner);
		setResizable(false);
		setLocationRelativeTo(owner);
		setLayout(new GridLayout(6, 2));
		setModal(true);
		add(new JLabel("Name:"));
		add(new JTextField());
		add(new JLabel("Start:"));
		add(new JCheckBox());
		add(new JLabel("End:"));
		add(new JCheckBox());
		add(new JLabel("X:"));
		add(new JTextField());
		add(new JLabel("Y:"));
		add(new JTextField());
		add(new JButton("OK"));
		add(new JButton("Cancel"));
		pack();
		setVisible(true);
	}
	
	

}
