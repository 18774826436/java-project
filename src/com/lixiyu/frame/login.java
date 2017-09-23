package com.lixiyu.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import org.apache.commons.lang.RandomStringUtils;
import com.lixiyu.util.BackgroundPanel;
import com.lixiyu.util.ColorfulCAPTCHALabel;
import com.lixiyu.util.DBHelper;

public class login extends JFrame{
	private static final long serialVersionUID = -4655235896173916415L;
	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JTextField validateTextField;
	private String randomText;
	
public static void main(String args[]){
	try {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	} catch (Throwable e) {
		e.printStackTrace();
	}
	EventQueue.invokeLater(new Runnable(){
	public void run(){
		try{
			login frame=new login();
			BackgroundPanel bgp; 
		    bgp=new BackgroundPanel((new ImageIcon("image\\background.png")).getImage());  
		    frame.setResizable(false);
		    frame.getContentPane().add(bgp);
			frame.setVisible(true);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	});
}

public login(){
	setTitle("��¼");
	setSize(350,190);//���ñ���ͼƬ
	setLocation(5000,5000);
	String path="image\\1.png";
	ImageIcon bg=new ImageIcon(path);
	JLabel la =new JLabel(bg);//���������Ǳ���ͼƬ��
	la.setBounds(0,0,this.getWidth(),this.getHeight());
	JPanel ip=(JPanel)this.getContentPane();
	ip.add(la);//�ؼ�Ҫ�ӵ�la��
	ip.setOpaque(false);
	ip.setLayout(null);
	this.getLayeredPane().add(la,Integer.MIN_VALUE);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	contentPane=new JPanel();
	setContentPane(contentPane);
	contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
	JLabel l=new JLabel();
	Icon icon=new ImageIcon("image\\background.png");     //�ڴ�ֱ�Ӵ�������
	l.setIcon(icon);
	l.setBounds(0, 0, icon.getIconWidth(),icon.getIconHeight());
    ip.add(l, new Integer(Integer.MIN_VALUE));
	
	BackgroundPanel bgp; 
    bgp=new BackgroundPanel((new ImageIcon("image\\background.png")).getImage());  

	JPanel usernamePanel=new JPanel();
	usernamePanel.add(bgp);
	contentPane.add(usernamePanel);

	JLabel usernameLable=new JLabel("\u7528\u6237\u540D\uFF1A");
	usernameLable.setFont(new Font("΢���ź�", Font.PLAIN, 15));
	usernamePanel.add(usernameLable);
	

	usernameTextField=new JTextField(Register.fullUserName);
	usernameTextField.setFont(new Font("΢���ź�", Font.PLAIN, 15));
	usernamePanel.add(usernameTextField);
	usernameTextField.setColumns(10);
	

	BackgroundPanel bgp2; 
    bgp2=new BackgroundPanel((new ImageIcon("image\\background.png")).getImage());  
	JPanel passwordPanel = new JPanel();
	passwordPanel.add(bgp2);
	contentPane.add(passwordPanel);
	JLabel passwordLabel = new JLabel("\u5BC6 \u7801\uFF1A");
	passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
	passwordLabel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
	passwordPanel.add(passwordLabel);
	
	passwordField = new JPasswordField(Register.fullPassWord);
	passwordField.setColumns(10);
	passwordField.setFont(new Font("΢���ź�", Font.PLAIN, 15));
	passwordPanel.add(passwordField);
	
	
	BackgroundPanel bgp3; 
    bgp3=new BackgroundPanel((new ImageIcon("image\\background.png")).getImage());  

	JPanel validatePanel = new JPanel();
	validatePanel.add(bgp3);
	contentPane.add(validatePanel);
	JLabel validateLabel = new JLabel("\u9A8C\u8BC1\u7801\uFF1A");
	validateLabel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
	validatePanel.add(validateLabel);
	validateTextField = new JTextField();
	validateTextField.setFont(new Font("΢���ź�", Font.PLAIN, 15));
	validatePanel.add(validateTextField);
	validateTextField.setColumns(3);
	
	randomText = RandomStringUtils.randomAlphanumeric(4);
	ColorfulCAPTCHALabel label = new ColorfulCAPTCHALabel(randomText);//�����֤��
	label.setFont(new Font("΢���ź�", Font.PLAIN, 7));
	validatePanel.add(label);
	JButton but = new JButton("\u5237\u65B0");
	but.setFont(new java.awt.Font("�¼����w",0,12));
	validatePanel.add(but);
	bgp3.setOpaque(false);

	but.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			label.setFont(new Font("΢���ź�", Font.PLAIN, 7));
		}
	});
	but.setFont(new Font("΢���ź�", Font.BOLD | Font.ITALIC, 7));
	
	JPanel buttonPanel=new JPanel();
	contentPane.add(buttonPanel);
	JButton submitButton=new JButton("��¼");
	submitButton.addActionListener(new ActionListener() {
@Override
	public void actionPerformed(ActionEvent e) {
		do_submitButton_actionPerformed(e);
	}
	
	private void do_submitButton_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub��ȡ�ı����е���Ϣ
		String username = usernameTextField.getText();
		char[] password = passwordField.getPassword();
		String str = new String(password);
		String validate = validateTextField.getText();

		JFrame jf = new JFrame();
		if (username.isEmpty()) {// �ж��û����Ƿ�Ϊ��
				JOptionPane.showMessageDialog(jf, "�û�������Ϊ�գ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
			return;
			}
			if (new String(password).isEmpty()) {// �ж������Ƿ�Ϊ��
				JOptionPane.showMessageDialog(jf, "���벻��Ϊ�գ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
			return;
			}
			if (validate.isEmpty()) {// �ж���֤���Ƿ�Ϊ��
				JOptionPane.showMessageDialog(jf, "��֤�벻��Ϊ�գ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
			return;
			}
			//if
			
			if (!DBHelper.exists(username)) {// ����û����������������ʾ
					JOptionPane.showMessageDialog(jf, "�û��������ڣ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);					
					JFrame jf2 = new JFrame();
					Object[] options = {"ȷ��","ȡ��","����"};
					int response=JOptionPane.showOptionDialog(jf2, "����ע��", "ѡ��Ի������",JOptionPane.YES_OPTION,  
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(response==0)
					{
						dispose();// ����¼��������
						EventQueue.invokeLater(new Runnable() {
							@Override
								public void run() {
								try {
									Register register = new Register();// ����������
									register.setSize(400, 230);
									register.setVisible(false);
									register.setVisible(true);// ����������ɼ�
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
					else if(response==1)
					{ 
						JOptionPane.showMessageDialog(jf, "ȷ���˳���", "������Ϣ", JOptionPane.WARNING_MESSAGE);					
						dispose();// ����¼��������;
					} 
					else if(response==2) 
					{ 
						JOptionPane.showMessageDialog(jf, "������˰���������û��ʲô����", "������Ϣ", JOptionPane.WARNING_MESSAGE);					
					}  
				return;
				}
				if (!DBHelper.check(username, password)) {// �����������������ʾ
					JOptionPane.showMessageDialog(jf, "�������", "������Ϣ", JOptionPane.WARNING_MESSAGE);
				return;
				}
				if (!validate.equals(randomText)) {// ���У���벻ƥ���������ʾ
					JOptionPane.showMessageDialog(jf, "��֤�����", "������Ϣ", JOptionPane.WARNING_MESSAGE);
				return;
				}
				dispose();// ����¼��������
				TESTnewframe frame = new TESTnewframe();// ����������
				frame.setVisible(true);// ����������ɼ�
	}
});

	submitButton.setFont(new Font("΢���ź�", Font.PLAIN, 15));
	buttonPanel.add(submitButton);
	JButton cancelButton=new JButton("ע��");
	cancelButton.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
	do_cancelButton_actionPerformed(e);
}

	private void do_cancelButton_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dispose();// ����¼��������
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
			try {
				Register register = new Register();// ����������
				register.setSize(400, 230);
				register.setVisible(false);
				register.setVisible(true);// ����������ɼ�
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		});
	
	}
});
	
	cancelButton.setFont(new Font("΢���ź�",Font.PLAIN,15));
	buttonPanel.add(cancelButton);
	pack();// �Զ����������С
	setLocation(com.lixiyu.util.SwingUtil.centreContainer(getSize()));// �ô��������ʾ
	}
}
