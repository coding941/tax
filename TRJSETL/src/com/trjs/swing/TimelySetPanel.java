package com.trjs.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class TimelySetPanel extends JPanel {

	private JButton ok_btn;

	private JCheckBox set_box;

	public TimelySetPanel() {
		setLayout(new BorderLayout());
		add(getTop(), BorderLayout.NORTH);

	}

	private JPanel getTop() {
		JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top.setBorder(new TitledBorder("设置计划"));
		ok_btn = new JButton("保存");
		ok_btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (set_box.isSelected()) {

				} else {

				}
			}
		});
		set_box = new JCheckBox("是否设置计划任务");
		set_box.setBackground(Color.white);
		top.setBackground(Color.white);
		top.add(set_box);
		top.add(new JLabel("          "));
		top.add(ok_btn);
		return top;
	}

}
