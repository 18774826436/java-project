package com.lixiyu.frame;

import java.awt.Component;
import com.lixiyu.frame.login;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

import com.lixiyu.util.BackgroundPanel;
import com.lixiyu.util.DBHelper;
import com.lixiyu.util.DocumentSizeFilter;
import com.lixiyu.util.DocumentSizeListener;
import com.lixiyu.util.SwingUtil;
import com.lixiyu.model.User;



public class Register extends JFrame {
/**
*
*/
	static  String fullUserName= null;
	static  String  fullPassWord=  null;
	private static final long serialVersionUID = 2491294229716316338L;
	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private JTextField emailTextField;
	private JLabel tipLabel = new JLabel();// ��ʾ��ʾ��Ϣ
	
/**
* Launch the application.
*/
/**
* Create the frame.
*/
public Register() {
	setTitle("ע��");
	setSize(400,300);//���ñ���ͼƬ
	setResizable(false);
	setLocation(5000,5000);
	String path="image\\3.png";
	ImageIcon bg=new ImageIcon(path);
	JLabel la=new JLabel(bg);
	la.setBounds(0,0,this.getWidth(),this.getHeight());
	JPanel ip=(JPanel)this.getContentPane();
	ip.setOpaque(false);
	this.getLayeredPane().add(la,Integer.MIN_VALUE);
	((JPanel)this.getContentPane()).setOpaque(false);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	contentPane = new JPanel();
	setContentPane(contentPane);
	contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
	
	//document�����ú������йأ�һЩ���Ի�����ʾ
	JPanel usernamePanel = new JPanel();
	contentPane.add(usernamePanel);
	JLabel usernameLabel = new JLabel("\u7528 \u6237 \u540D\uFF1A");
	usernameLabel.setFont(new Font("΢���ź�", Font.PLAIN, 15));

	
	
	BackgroundPanel bgp; 
    bgp=new BackgroundPanel((new ImageIcon("image\\background.png")).getImage());  
    usernamePanel.add(bgp);
	usernamePanel.add(usernameLabel);
	usernameTextField = new JTextField();
	usernameTextField.setToolTipText("\u8BF7\u8F93\u51655~15\u4E2A\u7531\u5B57\u6BCD\u6570\u5B57\u4E0B\u5212\u7EBF\u7EC4\u6210\u7684\u5B57\u7B26\u4E32");
	AbstractDocument doc = (AbstractDocument) usernameTextField.getDocument();
	doc.setDocumentFilter(new DocumentSizeFilter(15));// �����ı����ڿ��������ַ�����Ϊ15
	doc.addDocumentListener(new DocumentSizeListener(tipLabel, 15));
	usernameTextField.setFont(new Font("΢���ź�", Font.PLAIN, 15));
	usernamePanel.add(usernameTextField);
	usernameTextField.setColumns(10);
	
	JPanel passwordPanel1 = new JPanel();
	contentPane.add(passwordPanel1);
	JLabel passwordLabel1 = new JLabel("\u8F93\u5165\u5BC6\u7801\uFF1A");
	BackgroundPanel bgp1; 
    bgp1=new BackgroundPanel((new ImageIcon("image\\background.png")).getImage());  
    passwordPanel1.add(bgp1);
	passwordLabel1.setFont(new Font("΢���ź�", Font.PLAIN, 15));
	passwordPanel1.add(passwordLabel1);
	passwordField1 = new JPasswordField();
	doc = (AbstractDocument) passwordField1.getDocument();
	doc.setDocumentFilter(new DocumentSizeFilter(20));// �����������ڿ��������ַ�����Ϊ20
	doc.addDocumentListener(new DocumentSizeListener(tipLabel, 20));
	passwordField1.setFont(new Font("΢���ź�", Font.PLAIN, 15));
	passwordField1.setColumns(10);
	passwordPanel1.add(passwordField1);
	
	JPanel passwordPanel2 = new JPanel();
	contentPane.add(passwordPanel2);
	JLabel passwordLabel2 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
	passwordLabel2.setFont(new Font("΢���ź�", Font.PLAIN, 15));
	BackgroundPanel bgp2; 
    bgp2=new BackgroundPanel((new ImageIcon("image\\background.png")).getImage());  
    passwordPanel2.add(bgp2);
	passwordPanel2.add(passwordLabel2);
	passwordField2 = new JPasswordField();
	doc = (AbstractDocument) passwordField2.getDocument();
	doc.setDocumentFilter(new DocumentSizeFilter(20));// �����������ڿ��������ַ�����Ϊ20
	doc.addDocumentListener(new DocumentSizeListener(tipLabel, 20));
	passwordField2.setFont(new Font("΢���ź�", Font.PLAIN, 15));
	passwordField2.setColumns(10);
	passwordPanel2.add(passwordField2);
	
	JPanel emailPanel = new JPanel();
	contentPane.add(emailPanel);
	JLabel emailLabel = new JLabel("\u7535\u5B50\u90AE\u7BB1\uFF1A");
	emailLabel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
	emailLabel.setOpaque(false);
	BackgroundPanel bgp3; 
    bgp3=new BackgroundPanel((new ImageIcon("image\\background.png")).getImage());  
    emailPanel.add(bgp3);
	emailPanel.add(emailLabel);
	emailTextField = new JTextField();
	doc = (AbstractDocument) emailTextField.getDocument();
	doc.setDocumentFilter(new DocumentSizeFilter(45));// �����ı����ڿ��������ַ�����Ϊ45
	doc.addDocumentListener(new DocumentSizeListener(tipLabel, 45));
	emailTextField.setFont(new Font("΢���ź�", Font.PLAIN, 15));
	emailPanel.add(emailTextField);
	emailTextField.setColumns(10);
	
	JPanel buttonPanel = new JPanel();
	contentPane.add(buttonPanel);
	JButton submitButton = new JButton("\u63D0\u4EA4");
	submitButton.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
		do_submitButton_actionPerformed(e);
}

private void do_submitButton_actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	User user = new User();
	String username = usernameTextField.getText();
	char[] password1 = passwordField1.getPassword();
	char[] password2 = passwordField2.getPassword();
	String email = emailTextField.getText();
	
	user.setEmail(email);
	String password = new String(password1);
	user.setPassword(password);
	user.setUsername(username);
	fullUserName=usernameTextField.getText();
	fullPassWord=password;
		
	JFrame jf = new JFrame();
	if (username.isEmpty()) {// �ж��û����Ƿ�Ϊ��
			JOptionPane.showMessageDialog(jf, "�û�������Ϊ�գ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
		return;
		}
		if (new String(password1).isEmpty()) {// �ж������Ƿ�Ϊ��
			JOptionPane.showMessageDialog(jf, "���벻��Ϊ�գ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
		return;
		}
		if (new String(password2).isEmpty()) {// �ж�ȷ�������Ƿ�Ϊ��
			JOptionPane.showMessageDialog(jf, "ȷ�����벻��Ϊ�գ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
		return;
		}
		if (email.isEmpty()) {// �жϵ��������Ƿ�Ϊ��
			JOptionPane.showMessageDialog(jf, "�������䲻��Ϊ�գ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
		return;
		}
		
		// У���û����Ƿ�Ϸ�
		if (!Pattern.matches("\\w{5,15}", username)) {
			JOptionPane.showMessageDialog(jf, "������Ϸ����û�����", "������Ϣ", JOptionPane.WARNING_MESSAGE);
		return;
		}
		// У����������������Ƿ���ͬ
		if (!Arrays.equals(password1, password2)) {
			JOptionPane.showMessageDialog(jf, "������������벻ͬ��", "������Ϣ", JOptionPane.WARNING_MESSAGE);
		return;
		}
		// У����������Ƿ�Ϸ�
		if (!Pattern.matches("\\w+@\\w+\\.\\w+", email)) {
			JOptionPane.showMessageDialog(jf, "������Ϸ��ĵ������䣡", "������Ϣ", JOptionPane.WARNING_MESSAGE);
		return;
		}
		// У���û����Ƿ����
		if (DBHelper.exists(username)) {
			JOptionPane.showMessageDialog(jf, "�û����Ѿ�����", "������Ϣ", JOptionPane.WARNING_MESSAGE);
		return;
		}
		DBHelper.save(user);
		dispose();// ����¼��������
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
			try {
				login login = new login();// ����������
				login.setVisible(true);// ����������ɼ�
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		});
}
});
	buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
	tipLabel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
	buttonPanel.add(tipLabel);
	Component glue = Box.createGlue();
	buttonPanel.add(glue);
	submitButton.setFont(new Font("΢���ź�", Font.PLAIN, 15));
	buttonPanel.add(submitButton);
	JButton cancelButton = new JButton("\u53D6\u6D88");
	cancelButton.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
		do_cancelButton_actionPerformed(e);
}

private void do_cancelButton_actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub	
	JFrame jf = new JFrame();
	Object[] options = {"ȷ��","ȡ��","����"};
	int response=JOptionPane.showOptionDialog(jf, "��ȷ��Ҫ�˳�", "��ʾ",JOptionPane.YES_OPTION,  
	JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	if(response==0)
	{ 
		dispose();// ����¼��������;
	}
	else if(response==1)
	{ 
	} 
	else if(response==2) 
	{ 
		JOptionPane.showMessageDialog(null, "��������help���ǣ���û��ʲô����" , " ����", JOptionPane.ERROR_MESSAGE);
	}  
}
});
	cancelButton.setFont(new Font("΢���ź�", Font.PLAIN, 15));
	buttonPanel.add(cancelButton);
	pack();// �Զ����������С
	setLocation(SwingUtil.centreContainer(getSize()));// �ô��������ʾ
	}
}
