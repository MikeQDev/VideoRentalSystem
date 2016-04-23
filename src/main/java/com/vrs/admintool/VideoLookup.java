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

public class VideoLookup extends JFrame {

	public VideoLookup() {
		setTitle("Video Lookup");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JComboBox<String> comboBox = new JComboBox<String>();
		String[] searchBy = { "Video ID","Title","Description","Category" };
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
					case "Video ID":
						results = WebSqlExecutor
								.selectSql("SELECT * FROM MOVIE WHERE MOVIEID="
										+ q);
						break;
					case "Title":
						results = WebSqlExecutor
								.selectSql("SELECT * FROM MOVIE WHERE TITLE LIKE '%"
										+ q + "%'");
						break;
					case "Description":
						results = WebSqlExecutor
								.selectSql("SELECT * FROM MOVIE WHERE DESCRIPTION LIKE '%"
										+ q + "%'");
						break;
					case "Category":
						results = WebSqlExecutor
								.selectSql("SELECT * FROM MOVIE WHERE CATEGORY LIKE '%"
										+ q + "%'");
						break;
					}
					List resultList = new List();
					resultList.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							new VideoManager(
									e.getSource().toString().split(",")[4]
											.split("=")[1]);
						}
					});
					while (results.next()) {
						String movieId = results.getString(1);
						String title = results.getString(2);
						String description = results.getString(3);
						String releaseDate = results.getString(4);
						String viewCount = results.getString(5);
						String ageRating = results.getString(6);
						String userRating = results.getString(7);
						String category = results.getString(8);
						resultList.add(movieId + "," + title + "," + description + "," + releaseDate
								+ "," + viewCount + "," + ageRating + "," + userRating+","+category);
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
		new VideoLookup();
	}
}
