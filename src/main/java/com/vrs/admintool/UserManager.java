package com.vrs.admintool;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.vrs.database.WebSqlExecutor;

public class UserManager extends JFrame {
	private JLabel labelUserId, labelEmail, labelFirstName, labelLastName,
			labelPhone, labelStreet, labelCity, labelState, labelZip,
			labelCCNum, labelSecNum, labelPlanType;

	private JTextField textfieldUserId, textfieldEmail, textfieldFirstName,
			textfieldLastName, textfieldPhone, textfieldStreet, textfieldCity,
			textfieldState, textfieldZip, textfieldCCNum, textfieldSecNum,
			textfieldPlanType;
	JButton buttonSave, buttonLookup;

	/**
	 * GUI for managing users
	 * @param userId
	 */
	public UserManager(String userId) {
		setTitle("VRS User Manager");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		try {
			buildMainPanel(getInfo(userId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		buildBottomPanel();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private ResultSet getInfo(String userId) throws Exception{
		return WebSqlExecutor.selectSql("SELECT * FROM VRS_USER_T WHERE USERID="+userId);
	}

	private void buildBottomPanel() {
		JPanel bottomP = new JPanel();
		buttonSave = new JButton("Save");
		buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Unimplemented feature due to time constraints.");
			}
		});

	/*	buttonLookup = new JButton("Lookup");
		buttonLookup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame("User lookup");

			}
		});*/
		bottomP.add(buttonSave);
		//bottomP.add(buttonLookup);

		add(bottomP, BorderLayout.SOUTH);
	}

	private void buildMainPanel(ResultSet rS) throws SQLException {
		rS.next();
		JPanel mainP = new JPanel(new GridLayout(12, 2));
		labelUserId = new JLabel("User ID");
		labelEmail = new JLabel("Email");
		labelFirstName = new JLabel("First name");
		labelLastName = new JLabel("Last name");
		labelPhone = new JLabel("Phone");
		labelStreet = new JLabel("Street");
		labelCity = new JLabel("City");
		labelState = new JLabel("State");
		labelZip = new JLabel("ZIP");
		labelCCNum = new JLabel("Credit card#");
		labelSecNum = new JLabel("Sec#");
		labelPlanType = new JLabel("Plan Type");
		textfieldUserId = new JTextField(rS.getString(1));
		textfieldUserId.setEnabled(false);
		textfieldEmail = new JTextField(rS.getString(2));
		textfieldFirstName = new JTextField(rS.getString(3));
		textfieldLastName = new JTextField(rS.getString(4));
		textfieldPhone = new JTextField(rS.getString(5));
		textfieldStreet = new JTextField(rS.getString(7));
		textfieldCity = new JTextField(rS.getString(8));
		textfieldState = new JTextField(rS.getString(9));
		textfieldZip = new JTextField(rS.getString(10));
		textfieldCCNum = new JTextField(rS.getString(11));
		textfieldSecNum = new JTextField(rS.getString(12));
		textfieldPlanType = new JTextField(rS.getString(13));

		mainP.add(labelUserId);
		mainP.add(textfieldUserId);
		mainP.add(labelEmail);
		mainP.add(textfieldEmail);
		mainP.add(labelFirstName);
		mainP.add(textfieldFirstName);
		mainP.add(labelLastName);
		mainP.add(textfieldLastName);
		mainP.add(labelPhone);
		mainP.add(textfieldPhone);
		mainP.add(labelStreet);
		mainP.add(textfieldStreet);
		mainP.add(labelCity);
		mainP.add(textfieldCity);
		mainP.add(labelState);
		mainP.add(textfieldState);
		mainP.add(labelZip);
		mainP.add(textfieldZip);
		mainP.add(labelCCNum);
		mainP.add(textfieldCCNum);
		mainP.add(labelSecNum);
		mainP.add(textfieldSecNum);
		mainP.add(labelPlanType);
		mainP.add(textfieldPlanType);
		add(mainP);
	}

}
