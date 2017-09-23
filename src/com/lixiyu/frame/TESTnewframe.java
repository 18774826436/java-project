
package com.lixiyu.frame;

import java.io.*;//引入相關物件
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


public class TESTnewframe extends javax.swing.JFrame //建立一窗口
{
	
    //创建背景面板。  
    BackgroundPanel bgp,bgp2;  
   
  private JDesktopPane jDesktopPane1;//画布1
  private JDesktopPane jDesktopPane2;//画布2
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

  
  public TESTnewframe()//建立窗口
  {
    super();
    initGUI();//呼叫GUI函数
     
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
  }//建立窗口结束
  
   
  public int[] getdate()//取得系统日期函数
  {
    int[] date_array = new int[3];
    Calendar ca = new GregorianCalendar();  
    date_array[0] = ca.get(Calendar.YEAR);//年
    date_array[1] = ca.get(Calendar.MONTH)+1;//月
    date_array[2] = ca.get(Calendar.DAY_OF_MONTH);//日
    return date_array;//回传自定日期
  }//取得系统日期函数結束

  public void new_btn()//重新产生生日期按钮函数
  {
    jTextArea1.setText("");//清空记事
    int year,month;
    year = Integer.parseInt(jLabel5.getText().substring(0,4));//设定已选择的年
    month = Integer.parseInt(jLabel5.getText().substring(7,9));//设定已选择的月
    date_btn_create(year,month);//呼叫产生日期按钮函数
  }//重新产生生日期按钮函数结束
  
  private JButton createBtn() {
		JButton btn = new JButton(" ");
		btn.setUI(new BasicButtonUI());// 恢复基本视觉效果
		//btn.setPreferredSize(new Dimension(80, 27));// 设置按钮大小
		btn.setContentAreaFilled(false);// 设置按钮透明
		btn.setFont(new Font("微软雅黑", Font.PLAIN, 24));// 按钮文本样式
		btn.setMargin(new Insets(0, 0, 0, 0));// 按钮内容与边框距离
		//btn.setMargin(null);// 按钮内容与边框距离
		btn.setBorderPainted(false);// 隐藏边框
		btn.addMouseListener(new MyMouseListener());
		return btn;
	}

  public void date_btn_create(int year,int month)//产生生日期按鈕
  {
    int y=0,x=0,x_add=0,y_add=0,week_add=0,date_acc=0,week_of_day=0;
    String syear,smonth,sday,filename;
    syear = String.valueOf(year);
    smonth = String.valueOf(month);
    if (smonth.length() == 1)//若小于10月(一位数)就在前面加0
      smonth = "0"+smonth;
    
    jDesktopPane1.remove(jDesktopPane2);//移除桌面2(日期按钮附著，也就是把日期按钮移除)
    jDesktopPane2 = new JDesktopPane();//产生一个新的桌面
    jDesktopPane1.add(jDesktopPane2);
    jDesktopPane2.setBounds(0, 80, 396, 258);//设定大小及位置          这是日历的位置
    
    
      
    switch(month)//设定月份天数
    {
      case 1://大月31天
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        date_acc = 31;
        break;
        
      case 4://小月30天
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
    
    week_of_day = dow(year,month,1);//呼叫星期函数(取得当月第一天为星期几)
    
    switch(week_of_day)//设定当月1日位置(像素)
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
    JButton btn[] = new JButton[date_acc];//建立日期按钮阵列
    for (int i=0;i<date_acc;i++)//建立日期按钮循环
    {
      btn[i] = createBtn();//建立对应日期按钮         //这里是日期按钮
      jDesktopPane2.add(btn[i]);//加到桌面2上
      btn[i].setText(String.valueOf(i+1));//设定按钮为文字日期
      if ((i-week_of_day>0 && (i+week_of_day)%7==0) || ((i+week_of_day)%7==0 && i != 0))
      {//设定当月第一天日期按钮位置
        x=0;//X轴坐标
        x_add=0;//下一个按钮坐标(X轴)加值
        y++;//Y轴坐标
        y_add+=16;//还行坐标(Y轴)加值
        week_add=0;//当月第一日按钮坐标加值
      }//下面设定按钮大小及加值(X为起始10+第几個按钮*橫向宽度25+间隔+当月第一天星期几加值)
      btn[i].setBounds(16+x*39+x_add+week_add, y*31+y_add, 39, 31);//(Y为第几个按钮*高度20+换行加值）按钮宽为25高为20
      btn[i].setFont(new java.awt.Font("Arial",1,19));//设定字体大小及样式
      btn[i].setBorder(BorderFactory.createTitledBorder(""));//设定按钮文字不自动调整大小
     
        
      sday = String.valueOf(i+1);
      filename = syear+smonth+sday;//记事文档名称(年+月+日.txt)
      File file=new File(filename+".txt");//建立文档
      if (file.exists())//有事变成蓝色
        btn[i].setForeground(new java.awt.Color(0,0,255));
      
      btn[i].addActionListener(new ActionListener() {//按钮监听函数
        public void actionPerformed(ActionEvent evt) {
          btnActionPerformed(evt);
        }
      });
      x++;
      x_add+=14;//下一个按钮X坐标
    }
  }
  
  public boolean leap_year(int year)//判断闰年
  {
    boolean leap_year;//4的倍数，若为100的倍数则必須为4的倍数才是闰年
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
  }//判断闰年函数结束
  
  public int dow(int y,int m,int d)//判断星期几
  {
    int[] ww={6, 2, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};//天文星体运行值？？？这么高深的吗
    int w;
    w=ww[m-1]+y+(y/4)-(y/100)+(y/400);//闰年设定
    if( ((y%4)==0) && (m<3) )//公式
    {
      w--;
      if((y%100)==0) w++;
      if((y%400)==0) w--;
    }
    return (w+d)%7;//回传星期几(0为星期日，1为星期一以此类推)
  }
  
  private void initGUI()//主界面
  {
    try
    {
      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设定外框窗口主要功能列为标准(缩到最小，放到最大，关闭)
      {
        this.setTitle("万年行事历");//窗口标题
        jDesktopPane1 = new JDesktopPane();//建立一桌面
        getContentPane().add(jDesktopPane1, BorderLayout.CENTER);
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(739, 471));//设定桌面大小
        {//建立桌面2开始(放日期按钮的桌面)
          jDesktopPane2 = new JDesktopPane();
          jDesktopPane1.add(jDesktopPane2);
          jDesktopPane2.setBounds(0, 47, 396, 308);
          //jDesktopPane2.setBackground(new java.awt.Color(148,205,176));
        }//建立桌面2结束
        {//建立星期日标签开始
          jLabel1 = new JLabel();
          jDesktopPane1.add(jLabel1);
          jLabel1.setText("Sun");
          jLabel1.setBounds(22, 45, 33, 33);
          jLabel1.setFont(new java.awt.Font("Arial",0,17));
          //jLabel1.setForeground(new java.awt.Color(255,0,0));
        }//建立星期日标签結束
        {//建立记事框开始
          jTextArea1 = new JTextArea(100, 50);
          jDesktopPane1.add(jTextArea1);
          jTextArea1.setText("");//预设內容清空
          jTextArea1.setBounds(396, 47, 275, 70);//设定大小
          jTextArea1.setFont(new java.awt.Font("微软雅黑",0,17));//设定字体样式大小
          jTextArea1.setBackground(new java.awt.Color(240,240,242));
          jTextArea1.setLineWrap(true);//文字过场自动换行
          jTextArea1.setWrapStyleWord(true);//文字过场自动换行
          
        }//建立记事框结束
        {//建立储存按钮开始
          jButton2 = new JButton();
          jDesktopPane1.add(jButton2);
          jButton2.setText("储存");
          jButton2.setBounds(629, 130, 43, 28);
          jButton2.setFont(new java.awt.Font("微软雅黑",0,17));
          jButton2.setBorder(BorderFactory.createTitledBorder(""));
          jButton2.addActionListener(new ActionListener() {//设定储存按钮监听函数
            public void actionPerformed(ActionEvent evt) {
              jButton2ActionPerformed(evt);
            }
          });
        }//建立储存按钮结束
        {//建立用户名
            jLabel9 = new JLabel();
            jDesktopPane1.add(jLabel9);
            jLabel9.setText("用户名：");
            jLabel9.setOpaque(false);//透明化
            jLabel9.setBounds(20, 5, 70, 33);
            jLabel9.setFont(new java.awt.Font("微软雅黑",0,17));
        }//建立用户名结束
        {//建立用户名（空）
            jLabel10 = new JLabel();
            jDesktopPane1.add(jLabel10);
            jLabel10.setText("待填写");
            jLabel10.setOpaque(false);//透明化
            jLabel10.setBounds(100, 5, 200, 33);
            jLabel10.setFont(new java.awt.Font("微软雅黑",0,17));
        }//建立用户名（空）结束
        {//建立星期一~星期六标签开始
          jLabel2 = new JLabel();
          jDesktopPane1.add(jLabel2);
          jLabel2.setText("Mon    Tue     Wed     Thu      Fri      Sat");
          jLabel2.setBounds(77, 45, 297, 33);
          jLabel2.setFont(new java.awt.Font("Arial",0,17));
        }//建立星期一~星期六标签结束
        {//建立查询年份输入框开始
          jTextField1 = new JTextField();
          jDesktopPane1.add(jTextField1);
          jTextField1.setText("");
          //jTextField1.setOpaque(false);//透明化
          jTextField1.setBounds(22, 363, 66, 33);
          jTextField1.setBackground(new java.awt.Color(240,240,236));
          jTextField1.setFont(new java.awt.Font("Arial",0,17));
        }//建立查询年份输入框结束
        {//建立星期一~星期六标签开始
            jLabel11 = new JLabel();
            jDesktopPane1.add(jLabel11);
            jLabel11.setText("请添加日程");
            jLabel11.setBounds(395, 11, 110, 33);
            jLabel11.setFont(new java.awt.Font("微软雅黑",0,17));
        }//建立星期一~星期六标签结束
        {//建立状态标签开始
          jLabel6 = new JLabel();
          jDesktopPane1.add(jLabel6);
          jLabel6.setText("");
          jLabel6.setBounds(561, 11, 110, 33);
          jLabel6.setFont(new java.awt.Font("微软雅黑",0,17));
          //jLabel6.setForeground(new java.awt.Color(0,0,255));
        }//建立状态标签结束
        {//建立隐藏日期按钮暂存标签开始
          jLabel7 = new JLabel();
          jDesktopPane1.add(jLabel7);
          jLabel7.setText("");
          jLabel7.setBounds(0, 0, 0, 0);//设定大小为0
        }//建立隐藏日期按钮暂存标签结束
        {//建立目前选择日期标签开始
          jLabel8 = new JLabel();
          jDesktopPane1.add(jLabel8);
          jLabel8.setText("目前无选择日期");
          jLabel8.setBounds(396, 134, 275, 22);
          jLabel8.setFont(new java.awt.Font("微软雅黑",0,17));
        }//建立目前选择日期标签结束
        {//建立月份下拉选单开始
          ComboBoxModel jComboBox1Model = new DefaultComboBoxModel
          (new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" });//內容設定1~12
          jComboBox1 = new JComboBox();
          jDesktopPane1.add(jComboBox1);
          jComboBox1.setModel(jComboBox1Model);
          jComboBox1.setOpaque(false);//透明化
          jComboBox1.setBounds(132, 363, 62, 33);
          jComboBox1.setFont(new java.awt.Font("Arial",0,17));
        }//建立月份下拉选单结束
        {//建立"月"标签开始
          jLabel3 = new JLabel();
          jDesktopPane1.add(jLabel3);
          jLabel3.setText("月");
          jLabel3.setBounds(210, 363, 22, 33);
          jLabel3.setFont(new java.awt.Font("微软雅黑",0,17));
        }//建立"月"标签结束
        {//建立"年"标签开始
          jLabel4 = new JLabel();
          jDesktopPane1.add(jLabel4);
          jLabel4.setText("年");
          jLabel4.setFont(new java.awt.Font("微软雅黑", 0, 17));
          jLabel4.setBounds(99, 363, 22, 33);
        }//建立"年"标签结束
        {//建立查询按钮开始
          jButton3 = new JButton();
          jDesktopPane1.add(jButton3);
          jButton3.setText("查询");
          jButton3.setBounds(240, 363, 55, 33);
          jButton3.setBorder(BorderFactory.createTitledBorder(""));
          jButton3.setFont(new java.awt.Font("微软雅黑",0,17));
          jButton3.addActionListener(new ActionListener() {//查詢按鈕監聽函數
            public void actionPerformed(ActionEvent evt) {
              jButton3ActionPerformed(evt);
            }
          });
         }//建立查询按钮结束
         {//事件记录
        	 int j = 22; 
             for(int i = 0; i<10 ; i++,j=j+22){
           	jCheckBox[i] = new JCheckBox(" ");
           	jDesktopPane1.add(jCheckBox[i]);
               jCheckBox[i].setBounds(396, 136+j, 275, 22);
               jCheckBox[i].setBorder(BorderFactory.createTitledBorder(""));
               jCheckBox[i].setFont(new java.awt.Font("微软雅黑",0,17));
               jCheckBox[i].setOpaque(false);//透明化
               jCheckBox[i].addItemListener(new CustomItemListener());
               jCheckBox[i].addActionListener(new ActionListener() {//点击日期监听函數
                 public void actionPerformed(ActionEvent evt) {
               	  btnActionPerformed(evt);
                 }
               });
             }
         }
        {//建立目前年月位置标签开始
          int[] now = new int[3];
          now = getdate();//预设为当年当月
          String year5,smonth;
          year5 = String.valueOf(now[0]);
          smonth = String.valueOf(now[1]);
          if (smonth.length() == 1)
            smonth = "0"+smonth;
          
          jLabel5 = new JLabel();
          jDesktopPane1.add(jLabel5);
          jLabel5.setText(year5+" 年 "+smonth+" 月");
          jLabel5.setBounds(396, 11, 189, 33);
          jLabel5.setForeground(new java.awt.Color(255,255,255));//设定字体为白色
        }//建立目前年月位置标签結束
        {//预设当年当月日期按钮产生开始
          int[] now = new int[3];
          now = getdate();
          date_btn_create(now[0],now[1]);//产生日期按钮
        }//预设当年当月日期按钮产生结束
      }
      pack();
      this.setSize(267, 496);
    } catch (Exception e) {//异常处理
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
	    File file=new File(filename+".txt");//刪除当日记事档案
	    file.delete();
	    new_btn();//重新产生按钮
	    jLabel6.setText("行事已清除");//设定相关讯息
	    jLabel7.setText("");
	    jLabel8.setText("目前无选择日期");
	    jDesktopPane2.add(bgp2);
  }
  private void jButton2ActionPerformed(ActionEvent evt)//储存按钮按下出发时间开始
  {
    String year,month,day,filename,insert_str;
    year = jLabel5.getText().substring(0,4);
    month = jLabel5.getText().substring(7,9);
    day = jLabel7.getText();
    filename = year+month+day;
    insert_str = jTextArea1.getText();//记事內容
    if (insert_str.length() != 0 && day.length() != 0)//若记事框內有文字且有选择日期则储存记事档案
    {
      try
      {
        FileWriter fw=new FileWriter(filename+".txt");//启用文档写入
        BufferedWriter bfw=new BufferedWriter(fw);//启用缓冲区写入
        bfw.write(insert_str); //将Textarea内容写入缓冲区域
        bfw.flush();//将缓冲区域写入文档
        fw.close();//关闭文档
        jLabel6.setText("行事历已记录");//设定相关讯息
        jLabel7.setText("");
        jLabel8.setText("目前无选择日期");
        new_btn();
        jDesktopPane2.add(bgp2);
      }catch(IOException e)
      {
        e.printStackTrace();
      }
    }
    else//若无记事內容或无选择日期
    {
    	JOptionPane.showMessageDialog(null,"无日程", "内容为空",JOptionPane.WARNING_MESSAGE);

    }
    
  }//储存按钮按下触发时间结束
  
  private void jButton3ActionPerformed(ActionEvent evt)//查询按钮按下触发时间开始
  {
    String syear,smonth;
    try
    {
      jTextArea1.setText("");
      jLabel6.setText("查询日期");
      syear = jTextField1.getText();
      smonth = String.valueOf(jComboBox1.getSelectedIndex() + 1);
      if (smonth.length() == 1)
            smonth = "0"+smonth;
      if (syear == "" || Integer.parseInt(syear)<1582)//若未输入年份就触发异常
      {
        int[] now = new int[3];
        now = getdate();
        syear = String.valueOf(now[0]);//若选择年份小于1582年则预设为当年
        jLabel6.setText("请选1582以上");
      }
      jLabel5.setText(syear+" 年 "+smonth+" 月");
      date_btn_create(Integer.parseInt(syear),Integer.parseInt(smonth));
      jLabel7.setText("");
      jLabel8.setText("目前无选择日期");
    }catch(NumberFormatException e)//例外处理设定为当年及选择的月份
    {
      int[] now = new int[3];
      now = getdate();
      syear = String.valueOf(now[0]);
      smonth = String.valueOf(jComboBox1.getSelectedIndex() + 1);
      if (smonth.length() == 1)
            smonth = "0"+smonth;
      jLabel5.setText(syear+" 年 "+smonth+" 月");
      jLabel6.setText("请选1582以上");
      date_btn_create(Integer.parseInt(syear),Integer.parseInt(smonth));
      jLabel7.setText("");
      jLabel8.setText("目前无选择日期");
    }
    jDesktopPane2.add(bgp2);
  }//查询按钮按下触发事件结束
  
  private void btnActionPerformed(ActionEvent evt)//日期按钮按下触发时间开始
  {
    jTextArea1.setText("");
    String year,month,btn_date,filename,read_str;
    year = jLabel5.getText().substring(0,4);//取得年
    month = jLabel5.getText().substring(7,9);//取得月
    btn_date = evt.getActionCommand();//取得按下按钮文字
    filename = year+month+btn_date;
    jLabel7.setText(btn_date);
    try
    {
      FileReader fr = new FileReader(filename+".txt");//读取选择日期记事档案
      BufferedReader bfr = new BufferedReader(fr);//将档案读取到缓冲区
    
      while((read_str = bfr.readLine())!=null) // 每次读取一行，直到档案结束
      {
          jCheckBox[0].setText("\n");
          jCheckBox[0].setText(read_str);
      }
      jLabel6.setText("当天记事");
      jLabel8.setText(year+"年"+month+"月"+btn_date+"日");
      fr.close();
    }catch(FileNotFoundException e)//如果没有指定的记事档案就印出当日无行事日历
    {
      jLabel6.setText("当日无行事历");
      jLabel8.setText(year+" 年 "+month+" 月 "+btn_date+" 日");
    }catch(IOException e)
    {
      e.printStackTrace();
    }
    
  }//日期按钮按下触发事件结束
 
}



