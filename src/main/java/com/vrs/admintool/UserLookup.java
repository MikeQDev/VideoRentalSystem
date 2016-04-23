package com.vrs.admintool;

import java.awt.BorderLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.vrs.database.WebSqlExecutor;

public class UserLookup extends JFrame {

	public UserLookup() {
		setTitle("User Lookup");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JComboBox<String> comboBox = new JComboBox<String>();
		String[] searchBy = { "User ID", "Email", "First Name", "Last Name" };
		for (String s : searchBy)
			comboBox.addItem(s);
		JTextField query = new JTextField(10);
		JButton buttonSearch = new JButton("Search");
		buttonSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResultSet results = null;
				String q = query.getText();
				try {
					switch ((String) comboBox.getSelectedItem()) {
					case "User ID":
						results = WebSqlExecutor
								.selectSql("SELECT * FROM VRS_USER_T WHERE USERID="
										+ q);
						break;
					case "Email":
						results = WebSqlExecutor
								.selectSql("SELECT * FROM VRS_USER_T WHERE EMAIL LIKE '%"
										+ q + "%'");
						break;
					case "First Name":
						results = WebSqlExecutor
								.selectSql("SELECT * FROM VRS_USER_T WHERE FIRSTNAME LIKE '%"
										+ q + "%'");
						break;
					case "Last Name":
						results = WebSqlExecutor
								.selectSql("SELECT * FROM VRS_USER_T WHERE LASTNAME LIKE '%"
										+ q + "%'");
						break;
					}
					List resultList = new List();
					resultList.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							new UserManager(
									e.getSource().toString().split(",")[4]
											.split("=")[1]);
						}
					});
					while (results.next()) {
						String uId = results.getString(1);
						String eM = results.getString(2);
						String fN = results.getString(3);
						String lN = results.getString(4);
						String ph = results.getString(5);
						String str = results.getString(7);
						String ci = results.getString(8);
						String sta = results.getString(9);
						String zip = results.getString(10);
						String ccn = results.getString(11);
						String ccnSec = results.getString(12);
						String pType = results.getString(13);
						resultList.add(uId + "," + eM + "," + fN + "," + lN
								+ "," + ph + "," + str + "," + ci + "," + sta
								+ "," + zip + "," + ccn + "," + ccnSec + ","
								+ pType);
					}
					JFrame resultsFrame = new JFrame("Search results");
					resultsFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					resultsFrame.add(resultList);
					resultsFrame.pack();
					resultsFrame.setVisible(true);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Error: " + ex.getMessage());
					ex.printStackTrace();
					return;
				}
			}
		});

		add(comboBox, BorderLayout.WEST);
		add(query, BorderLayout.EAST);
		add(buttonSearch, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new UserLookup();
	}
}
