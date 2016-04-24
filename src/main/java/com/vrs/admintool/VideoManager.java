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

public class VideoManager extends JFrame {
	private JLabel labelVideoId, labelTitle, labelDescription, labelReleaseDate,
			labelViewCount, labelAgeRating, labelUserRating, labelCategory;

	private JTextField textfieldVideoId, textfieldTitle, textfieldDescription,
			textfieldRelease, textfieldViewCount, textfieldAgeRating, textfieldUserRating,
			textfieldCategory;
	JButton buttonSave, buttonLookup;
	/**
	 * GUI for managing videos
	 * @param userId
	 */
	public VideoManager(String videoId) {
		setTitle("VRS Video Manager");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		try {
			buildMainPanel(getInfo(videoId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		buildBottomPanel();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private ResultSet getInfo(String videoId) throws Exception{
		return WebSqlExecutor.selectSql("SELECT * FROM MOVIE WHERE MOVIEID="+videoId);
	}

	private void buildBottomPanel() {
		JPanel bottomP = new JPanel();
		buttonSave = new JButton("Save");
		buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Unimplemented feature due to time constraints");
			}
		});

		/*buttonLookup = new JButton("Lookup");
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
		JPanel mainP = new JPanel(new GridLayout(8, 2));
		labelVideoId = new JLabel("Video ID");
		labelTitle = new JLabel("Title");
		labelDescription = new JLabel("Description");
		labelReleaseDate = new JLabel("Released");
		labelViewCount = new JLabel("ViewCount");
		labelAgeRating = new JLabel("AgeRating");
		labelUserRating = new JLabel("UserRating");
		labelCategory = new JLabel("Category");
		textfieldVideoId = new JTextField(rS.getString(1));
		textfieldVideoId.setEnabled(false);
		textfieldTitle = new JTextField(rS.getString(2));
		textfieldDescription = new JTextField(rS.getString(3));
		textfieldRelease = new JTextField(rS.getString(4));
		textfieldViewCount = new JTextField(rS.getString(5));
		textfieldAgeRating = new JTextField(rS.getString(6));
		textfieldUserRating = new JTextField(rS.getString(7));
		textfieldCategory = new JTextField(rS.getString(8));

		mainP.add(labelVideoId);
		mainP.add(textfieldVideoId);
		mainP.add(labelTitle);
		mainP.add(textfieldTitle);
		mainP.add(labelDescription);
		mainP.add(textfieldDescription);
		mainP.add(labelReleaseDate);
		mainP.add(textfieldRelease);
		mainP.add(labelViewCount);
		mainP.add(textfieldViewCount);
		mainP.add(labelAgeRating);
		mainP.add(textfieldAgeRating);
		mainP.add(labelUserRating);
		mainP.add(textfieldUserRating);
		mainP.add(labelCategory);
		mainP.add(textfieldCategory);
		add(mainP);
	}

}
