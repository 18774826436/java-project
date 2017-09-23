
package com.lixiyu.util;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.lixiyu.frame.TESTnewframe;


public class MyMouseListener implements MouseListener {

	private TESTnewframe frame;
	
	/**
	 * Launch the application.
	 */
	public MyMouseListener() {
		// TODO Auto-generated constructor stub
	}

	public MyMouseListener(TESTnewframe f) {
		this.frame = f;
	}



	/**
	 * Create the frame.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String btnName =((JButton)e.getSource()).getText();
		if ("File".equals(btnName.trim())) {
			//������¼�
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getComponent();
		Border etchedBorder = new EtchedBorder(EtchedBorder.RAISED,Color.YELLOW,Color.YELLOW);// ���ñ߿�͹��
	

		btn.setBorder(etchedBorder);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getComponent();
		Border etchedBorder = new EtchedBorder(EtchedBorder.LOWERED,Color.YELLOW,Color.YELLOW);// ���ñ߿���
		btn.setBorderPainted(false);// ���ر߿�

		btn.setBorder(etchedBorder);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getComponent();
		btn.setForeground(new Color(0x33, 0x66, 0xcc));// ����������ɫ
		btn.setBorderPainted(true);// ��ʾ�߿�
		Border etchedBorder = new EtchedBorder(EtchedBorder.LOWERED,Color.YELLOW,Color.YELLOW);// ���ñ߿���
		btn.setBorder(etchedBorder);
		btn.setRolloverEnabled(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getComponent();
		btn.setForeground(Color.black);// ����������ɫ
		btn.setBorderPainted(false);// ���ر߿�

	}

}