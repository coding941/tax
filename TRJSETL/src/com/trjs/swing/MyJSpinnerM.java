package com.trjs.swing;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import org.apache.commons.lang.time.DateUtils;




@SuppressWarnings("serial")
public class MyJSpinnerM extends JComponent {
	private JSpinner timeEditor;

	public MyJSpinnerM() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		timeEditor = new JSpinner(new SpinnerDateModel(DateUtils.truncate(
				new Date(), Calendar.MONTH), null, null, Calendar.MONTH));
		JSpinner.DateEditor spinnerEditor = new JSpinner.DateEditor(timeEditor,
				"MM月");
		timeEditor.setEditor(spinnerEditor);
		timeEditor.setPreferredSize(new Dimension(70, 22));
		add(timeEditor,gbc);
	}

	public Date getDate() {
		Date date = DateUtil.to_Date("1990-01-01", "yyyy-MM-dd");
		Date time = (Date) timeEditor.getValue();
		Calendar ca=Calendar.getInstance();
		ca.setTime(time);
		int month =ca.get(Calendar.MONTH);
		ca.setTime(date);
		ca.set(Calendar.MONTH, month);
		date = ca.getTime();
		return date;
	}
	public void setDate(Date date){
		timeEditor.setValue(date);
	}
	@Override
	public void setEnabled(boolean flag) {
		timeEditor.setEnabled(flag);
	}
}
