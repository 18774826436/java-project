
package com.lixiyu.frame;

import java.io.*;//�������P���
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicButtonUI;

import com.lixiyu.util.BackgroundPanel;
import com.lixiyu.util.MyMouseListener;


public class TESTnewframe extends javax.swing.JFrame //����һ����
{
	
    //����������塣  
    BackgroundPanel bgp,bgp2;  
   
  private JDesktopPane jDesktopPane1;//����1
  private JDesktopPane jDesktopPane2;//����2
  private JButton jButton2;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel4;
  private JLabel jLabel3;
  private JLabel jLabel5;
  private JLabel jLabel6;
  private JLabel jLabel7;
  private JLabel jLabel8;
  private JLabel jLabel9;
  private JLabel jLabel10;
  private JLabel jLabel11;
  private JComboBox jComboBox1;
  private JTextField jTextField1;
  private JButton jButton3;
  private JTextArea jTextArea1; 
  private static int x_size;
  private static int y_size;
  private JCheckBox[]jCheckBox = new JCheckBox[10] ;

  
  public TESTnewframe()//��������
  {
    super();
    initGUI();//����GUI����
     
    setResizable(false);

    getContentPane().setLayout(null);  
      
    bgp=new BackgroundPanel((new ImageIcon("1.jpg")).getImage());  
    bgp.setBounds(0,0,700,472);  
    jDesktopPane1.add(bgp);  
    
    bgp2=new BackgroundPanel((new ImageIcon("2.jpg")).getImage());  
    bgp2.setBounds(0,0,396,308);  
    jDesktopPane2.add(bgp2);
            
    this.setSize(700,472);  
    this.setLocation(629,472);  
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    this.setVisible(true);  
  }//�������ڽ���
  
   
  public int[] getdate()//ȡ��ϵͳ���ں���
  {
    int[] date_array = new int[3];
    Calendar ca = new GregorianCalendar();  
    date_array[0] = ca.get(Calendar.YEAR);//��
    date_array[1] = ca.get(Calendar.MONTH)+1;//��
    date_array[2] = ca.get(Calendar.DAY_OF_MONTH);//��
    return date_array;//�ش��Զ�����
  }//ȡ��ϵͳ���ں����Y��

  public void new_btn()//���²��������ڰ�ť����
  {
    jTextArea1.setText("");//��ռ���
    int year,month;
    year = Integer.parseInt(jLabel5.getText().substring(0,4));//�趨��ѡ�����
    month = Integer.parseInt(jLabel5.getText().substring(7,9));//�趨��ѡ�����
    date_btn_create(year,month);//���в������ڰ�ť����
  }//���²��������ڰ�ť��������
  
  private JButton createBtn() {
		JButton btn = new JButton(" ");
		btn.setUI(new BasicButtonUI());// �ָ������Ӿ�Ч��
		//btn.setPreferredSize(new Dimension(80, 27));// ���ð�ť��С
		btn.setContentAreaFilled(false);// ���ð�ť͸��
		btn.setFont(new Font("΢���ź�", Font.PLAIN, 24));// ��ť�ı���ʽ
		btn.setMargin(new Insets(0, 0, 0, 0));// ��ť������߿����
		//btn.setMargin(null);// ��ť������߿����
		btn.setBorderPainted(false);// ���ر߿�
		btn.addMouseListener(new MyMouseListener());
		return btn;
	}

  public void date_btn_create(int year,int month)//���������ڰ��o
  {
    int y=0,x=0,x_add=0,y_add=0,week_add=0,date_acc=0,week_of_day=0;
    String syear,smonth,sday,filename;
    syear = String.valueOf(year);
    smonth = String.valueOf(month);
    if (smonth.length() == 1)//��С��10��(һλ��)����ǰ���0
      smonth = "0"+smonth;
    
    jDesktopPane1.remove(jDesktopPane2);//�Ƴ�����2(���ڰ�ť������Ҳ���ǰ����ڰ�ť�Ƴ�)
    jDesktopPane2 = new JDesktopPane();//����һ���µ�����
    jDesktopPane1.add(jDesktopPane2);
    jDesktopPane2.setBounds(0, 80, 396, 258);//�趨��С��λ��          ����������λ��
    
    
      
    switch(month)//�趨�·�����
    {
      case 1://����31��
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        date_acc = 31;
        break;
        
      case 4://С��30��
      case 6:
      case 9:
      case 11:
        date_acc = 30;
        break;
        
      case 2:
        if (leap_year(year))
          date_acc = 29;
        else
          date_acc = 28;
    }
    
    week_of_day = dow(year,month,1);//�������ں���(ȡ�õ��µ�һ��Ϊ���ڼ�)
    
    switch(week_of_day)//�趨����1��λ��(����)
    {
      case 0:
        week_add = 0;
        break;
      case 1:
        week_add = 53;
        break;
      case 2:
        week_add = 106;
        break;
      case 3:
        week_add = 159;
        break;
      case 4:
        week_add = 212;
        break;
      case 5:
        week_add = 265;
        break;
      case 6:
        week_add = 318;
        break;
    }
    JButton btn[] = new JButton[date_acc];//�������ڰ�ť����
    for (int i=0;i<date_acc;i++)//�������ڰ�ťѭ��
    {
      btn[i] = createBtn();//������Ӧ���ڰ�ť         //���������ڰ�ť
      jDesktopPane2.add(btn[i]);//�ӵ�����2��
      btn[i].setText(String.valueOf(i+1));//�趨��ťΪ��������
      if ((i-week_of_day>0 && (i+week_of_day)%7==0) || ((i+week_of_day)%7==0 && i != 0))
      {//�趨���µ�һ�����ڰ�ťλ��
        x=0;//X������
        x_add=0;//��һ����ť����(X��)��ֵ
        y++;//Y������
        y_add+=16;//��������(Y��)��ֵ
        week_add=0;//���µ�һ�հ�ť�����ֵ
      }//�����趨��ť��С����ֵ(XΪ��ʼ10+�ڼ�����ť*�M����25+���+���µ�һ�����ڼ���ֵ)
      btn[i].setBounds(16+x*39+x_add+week_add, y*31+y_add, 39, 31);//(YΪ�ڼ�����ť*�߶�20+���м�ֵ����ť��Ϊ25��Ϊ20
      btn[i].setFont(new java.awt.Font("Arial",1,19));//�趨�����С����ʽ
      btn[i].setBorder(BorderFactory.createTitledBorder(""));//�趨��ť���ֲ��Զ�������С
     
        
      sday = String.valueOf(i+1);
      filename = syear+smonth+sday;//�����ĵ�����(��+��+��.txt)
      File file=new File(filename+".txt");//�����ĵ�
      if (file.exists())//���±����ɫ
        btn[i].setForeground(new java.awt.Color(0,0,255));
      
      btn[i].addActionListener(new ActionListener() {//��ť��������
        public void actionPerformed(ActionEvent evt) {
          btnActionPerformed(evt);
        }
      });
      x++;
      x_add+=14;//��һ����ťX����
    }
  }
  
  public boolean leap_year(int year)//�ж�����
  {
    boolean leap_year;//4�ı�������Ϊ100�ı�������Ϊ4�ı�����������
    if (year%4 == 0)
    {
      if (year%100 == 0)
      {
        if (year%400 == 0)
          leap_year = true;
        else
          leap_year = false;
      }
      else
        leap_year = true;
    }
    else
      leap_year = false;
    return leap_year;
  }//�ж����꺯������
  
  public int dow(int y,int m,int d)//�ж����ڼ�
  {
    int[] ww={6, 2, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};//������������ֵ��������ô�������
    int w;
    w=ww[m-1]+y+(y/4)-(y/100)+(y/400);//�����趨
    if( ((y%4)==0) && (m<3) )//��ʽ
    {
      w--;
      if((y%100)==0) w++;
      if((y%400)==0) w--;
    }
    return (w+d)%7;//�ش����ڼ�(0Ϊ�����գ�1Ϊ����һ�Դ�����)
  }
  
  private void initGUI()//������
  {
    try
    {
      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//�趨��򴰿���Ҫ������Ϊ��׼(������С���ŵ���󣬹ر�)
      {
        this.setTitle("����������");//���ڱ���
        jDesktopPane1 = new JDesktopPane();//����һ����
        getContentPane().add(jDesktopPane1, BorderLayout.CENTER);
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(739, 471));//�趨�����С
        {//��������2��ʼ(�����ڰ�ť������)
          jDesktopPane2 = new JDesktopPane();
          jDesktopPane1.add(jDesktopPane2);
          jDesktopPane2.setBounds(0, 47, 396, 308);
          //jDesktopPane2.setBackground(new java.awt.Color(148,205,176));
        }//��������2����
        {//���������ձ�ǩ��ʼ
          jLabel1 = new JLabel();
          jDesktopPane1.add(jLabel1);
          jLabel1.setText("Sun");
          jLabel1.setBounds(22, 45, 33, 33);
          jLabel1.setFont(new java.awt.Font("Arial",0,17));
          //jLabel1.setForeground(new java.awt.Color(255,0,0));
        }//���������ձ�ǩ�Y��
        {//�������¿�ʼ
          jTextArea1 = new JTextArea(100, 50);
          jDesktopPane1.add(jTextArea1);
          jTextArea1.setText("");//Ԥ��������
          jTextArea1.setBounds(396, 47, 275, 70);//�趨��С
          jTextArea1.setFont(new java.awt.Font("΢���ź�",0,17));//�趨������ʽ��С
          jTextArea1.setBackground(new java.awt.Color(240,240,242));
          jTextArea1.setLineWrap(true);//���ֹ����Զ�����
          jTextArea1.setWrapStyleWord(true);//���ֹ����Զ�����
          
        }//�������¿����
        {//�������水ť��ʼ
          jButton2 = new JButton();
          jDesktopPane1.add(jButton2);
          jButton2.setText("����");
          jButton2.setBounds(629, 130, 43, 28);
          jButton2.setFont(new java.awt.Font("΢���ź�",0,17));
          jButton2.setBorder(BorderFactory.createTitledBorder(""));
          jButton2.addActionListener(new ActionListener() {//�趨���水ť��������
            public void actionPerformed(ActionEvent evt) {
              jButton2ActionPerformed(evt);
            }
          });
        }//�������水ť����
        {//�����û���
            jLabel9 = new JLabel();
            jDesktopPane1.add(jLabel9);
            jLabel9.setText("�û�����");
            jLabel9.setOpaque(false);//͸����
            jLabel9.setBounds(20, 5, 70, 33);
            jLabel9.setFont(new java.awt.Font("΢���ź�",0,17));
        }//�����û�������
        {//�����û������գ�
            jLabel10 = new JLabel();
            jDesktopPane1.add(jLabel10);
            jLabel10.setText("����д");
            jLabel10.setOpaque(false);//͸����
            jLabel10.setBounds(100, 5, 200, 33);
            jLabel10.setFont(new java.awt.Font("΢���ź�",0,17));
        }//�����û������գ�����
        {//��������һ~��������ǩ��ʼ
          jLabel2 = new JLabel();
          jDesktopPane1.add(jLabel2);
          jLabel2.setText("Mon    Tue     Wed     Thu      Fri      Sat");
          jLabel2.setBounds(77, 45, 297, 33);
          jLabel2.setFont(new java.awt.Font("Arial",0,17));
        }//��������һ~��������ǩ����
        {//������ѯ��������ʼ
          jTextField1 = new JTextField();
          jDesktopPane1.add(jTextField1);
          jTextField1.setText("");
          //jTextField1.setOpaque(false);//͸����
          jTextField1.setBounds(22, 363, 66, 33);
          jTextField1.setBackground(new java.awt.Color(240,240,236));
          jTextField1.setFont(new java.awt.Font("Arial",0,17));
        }//������ѯ�����������
        {//��������һ~��������ǩ��ʼ
            jLabel11 = new JLabel();
            jDesktopPane1.add(jLabel11);
            jLabel11.setText("������ճ�");
            jLabel11.setBounds(395, 11, 110, 33);
            jLabel11.setFont(new java.awt.Font("΢���ź�",0,17));
        }//��������һ~��������ǩ����
        {//����״̬��ǩ��ʼ
          jLabel6 = new JLabel();
          jDesktopPane1.add(jLabel6);
          jLabel6.setText("");
          jLabel6.setBounds(561, 11, 110, 33);
          jLabel6.setFont(new java.awt.Font("΢���ź�",0,17));
          //jLabel6.setForeground(new java.awt.Color(0,0,255));
        }//����״̬��ǩ����
        {//�����������ڰ�ť�ݴ��ǩ��ʼ
          jLabel7 = new JLabel();
          jDesktopPane1.add(jLabel7);
          jLabel7.setText("");
          jLabel7.setBounds(0, 0, 0, 0);//�趨��СΪ0
        }//�����������ڰ�ť�ݴ��ǩ����
        {//����Ŀǰѡ�����ڱ�ǩ��ʼ
          jLabel8 = new JLabel();
          jDesktopPane1.add(jLabel8);
          jLabel8.setText("Ŀǰ��ѡ������");
          jLabel8.setBounds(396, 134, 275, 22);
          jLabel8.setFont(new java.awt.Font("΢���ź�",0,17));
        }//����Ŀǰѡ�����ڱ�ǩ����
        {//�����·�����ѡ����ʼ
          ComboBoxModel jComboBox1Model = new DefaultComboBoxModel
          (new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" });//�����O��1~12
          jComboBox1 = new JComboBox();
          jDesktopPane1.add(jComboBox1);
          jComboBox1.setModel(jComboBox1Model);
          jComboBox1.setOpaque(false);//͸����
          jComboBox1.setBounds(132, 363, 62, 33);
          jComboBox1.setFont(new java.awt.Font("Arial",0,17));
        }//�����·�����ѡ������
        {//����"��"��ǩ��ʼ
          jLabel3 = new JLabel();
          jDesktopPane1.add(jLabel3);
          jLabel3.setText("��");
          jLabel3.setBounds(210, 363, 22, 33);
          jLabel3.setFont(new java.awt.Font("΢���ź�",0,17));
        }//����"��"��ǩ����
        {//����"��"��ǩ��ʼ
          jLabel4 = new JLabel();
          jDesktopPane1.add(jLabel4);
          jLabel4.setText("��");
          jLabel4.setFont(new java.awt.Font("΢���ź�", 0, 17));
          jLabel4.setBounds(99, 363, 22, 33);
        }//����"��"��ǩ����
        {//������ѯ��ť��ʼ
          jButton3 = new JButton();
          jDesktopPane1.add(jButton3);
          jButton3.setText("��ѯ");
          jButton3.setBounds(240, 363, 55, 33);
          jButton3.setBorder(BorderFactory.createTitledBorder(""));
          jButton3.setFont(new java.awt.Font("΢���ź�",0,17));
          jButton3.addActionListener(new ActionListener() {//��ԃ���o�O ����
            public void actionPerformed(ActionEvent evt) {
              jButton3ActionPerformed(evt);
            }
          });
         }//������ѯ��ť����
         {//�¼���¼
        	 int j = 22; 
             for(int i = 0; i<10 ; i++,j=j+22){
           	jCheckBox[i] = new JCheckBox(" ");
           	jDesktopPane1.add(jCheckBox[i]);
               jCheckBox[i].setBounds(396, 136+j, 275, 22);
               jCheckBox[i].setBorder(BorderFactory.createTitledBorder(""));
               jCheckBox[i].setFont(new java.awt.Font("΢���ź�",0,17));
               jCheckBox[i].setOpaque(false);//͸����
               jCheckBox[i].addItemListener(new CustomItemListener());
               jCheckBox[i].addActionListener(new ActionListener() {//������ڼ�������
                 public void actionPerformed(ActionEvent evt) {
               	  btnActionPerformed(evt);
                 }
               });
             }
         }
        {//����Ŀǰ����λ�ñ�ǩ��ʼ
          int[] now = new int[3];
          now = getdate();//Ԥ��Ϊ���굱��
          String year5,smonth;
          year5 = String.valueOf(now[0]);
          smonth = String.valueOf(now[1]);
          if (smonth.length() == 1)
            smonth = "0"+smonth;
          
          jLabel5 = new JLabel();
          jDesktopPane1.add(jLabel5);
          jLabel5.setText(year5+" �� "+smonth+" ��");
          jLabel5.setBounds(396, 11, 189, 33);
          jLabel5.setForeground(new java.awt.Color(255,255,255));//�趨����Ϊ��ɫ
        }//����Ŀǰ����λ�ñ�ǩ�Y��
        {//Ԥ�赱�굱�����ڰ�ť������ʼ
          int[] now = new int[3];
          now = getdate();
          date_btn_create(now[0],now[1]);//�������ڰ�ť
        }//Ԥ�赱�굱�����ڰ�ť��������
      }
      pack();
      this.setSize(267, 496);
    } catch (Exception e) {//�쳣����
      e.printStackTrace();
    }
  }
  class CustomItemListener implements ItemListener {
      public void itemStateChanged(ItemEvent e) {
    	  if (jCheckBox[0].isSelected()) {
    		  jCheckBox[0].setText("");
    		  reset();
    	  }
    	 
      }    
   }
  public void reset(){
	  String year,month,day,filename;
	    year = jLabel5.getText().substring(0,4);
	    month = jLabel5.getText().substring(7,9);
	    day = jLabel7.getText();
	    filename = year+month+day;
	    File file=new File(filename+".txt");//�h�����ռ��µ���
	    file.delete();
	    new_btn();//���²�����ť
	    jLabel6.setText("���������");//�趨���ѶϢ
	    jLabel7.setText("");
	    jLabel8.setText("Ŀǰ��ѡ������");
	    jDesktopPane2.add(bgp2);
  }
  private void jButton2ActionPerformed(ActionEvent evt)//���水ť���³���ʱ�俪ʼ
  {
    String year,month,day,filename,insert_str;
    year = jLabel5.getText().substring(0,4);
    month = jLabel5.getText().substring(7,9);
    day = jLabel7.getText();
    filename = year+month+day;
    insert_str = jTextArea1.getText();//������
    if (insert_str.length() != 0 && day.length() != 0)//�����¿������������ѡ�������򴢴���µ���
    {
      try
      {
        FileWriter fw=new FileWriter(filename+".txt");//�����ĵ�д��
        BufferedWriter bfw=new BufferedWriter(fw);//���û�����д��
        bfw.write(insert_str); //��Textarea����д�뻺������
        bfw.flush();//����������д���ĵ�
        fw.close();//�ر��ĵ�
        jLabel6.setText("�������Ѽ�¼");//�趨���ѶϢ
        jLabel7.setText("");
        jLabel8.setText("Ŀǰ��ѡ������");
        new_btn();
        jDesktopPane2.add(bgp2);
      }catch(IOException e)
      {
        e.printStackTrace();
      }
    }
    else//���޼����ݻ���ѡ������
    {
    	JOptionPane.showMessageDialog(null,"���ճ�", "����Ϊ��",JOptionPane.WARNING_MESSAGE);

    }
    
  }//���水ť���´���ʱ�����
  
  private void jButton3ActionPerformed(ActionEvent evt)//��ѯ��ť���´���ʱ�俪ʼ
  {
    String syear,smonth;
    try
    {
      jTextArea1.setText("");
      jLabel6.setText("��ѯ����");
      syear = jTextField1.getText();
      smonth = String.valueOf(jComboBox1.getSelectedIndex() + 1);
      if (smonth.length() == 1)
            smonth = "0"+smonth;
      if (syear == "" || Integer.parseInt(syear)<1582)//��δ������ݾʹ����쳣
      {
        int[] now = new int[3];
        now = getdate();
        syear = String.valueOf(now[0]);//��ѡ�����С��1582����Ԥ��Ϊ����
        jLabel6.setText("��ѡ1582����");
      }
      jLabel5.setText(syear+" �� "+smonth+" ��");
      date_btn_create(Integer.parseInt(syear),Integer.parseInt(smonth));
      jLabel7.setText("");
      jLabel8.setText("Ŀǰ��ѡ������");
    }catch(NumberFormatException e)//���⴦���趨Ϊ���꼰ѡ����·�
    {
      int[] now = new int[3];
      now = getdate();
      syear = String.valueOf(now[0]);
      smonth = String.valueOf(jComboBox1.getSelectedIndex() + 1);
      if (smonth.length() == 1)
            smonth = "0"+smonth;
      jLabel5.setText(syear+" �� "+smonth+" ��");
      jLabel6.setText("��ѡ1582����");
      date_btn_create(Integer.parseInt(syear),Integer.parseInt(smonth));
      jLabel7.setText("");
      jLabel8.setText("Ŀǰ��ѡ������");
    }
    jDesktopPane2.add(bgp2);
  }//��ѯ��ť���´����¼�����
  
  private void btnActionPerformed(ActionEvent evt)//���ڰ�ť���´���ʱ�俪ʼ
  {
    jTextArea1.setText("");
    String year,month,btn_date,filename,read_str;
    year = jLabel5.getText().substring(0,4);//ȡ����
    month = jLabel5.getText().substring(7,9);//ȡ����
    btn_date = evt.getActionCommand();//ȡ�ð��°�ť����
    filename = year+month+btn_date;
    jLabel7.setText(btn_date);
    try
    {
      FileReader fr = new FileReader(filename+".txt");//��ȡѡ�����ڼ��µ���
      BufferedReader bfr = new BufferedReader(fr);//��������ȡ��������
    
      while((read_str = bfr.readLine())!=null) // ÿ�ζ�ȡһ�У�ֱ����������
      {
          jCheckBox[0].setText("\n");
          jCheckBox[0].setText(read_str);
      }
      jLabel6.setText("�������");
      jLabel8.setText(year+"��"+month+"��"+btn_date+"��");
      fr.close();
    }catch(FileNotFoundException e)//���û��ָ���ļ��µ�����ӡ����������������
    {
      jLabel6.setText("������������");
      jLabel8.setText(year+" �� "+month+" �� "+btn_date+" ��");
    }catch(IOException e)
    {
      e.printStackTrace();
    }
    
  }//���ڰ�ť���´����¼�����
 
}



