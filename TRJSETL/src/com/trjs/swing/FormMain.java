package com.trjs.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trjs.expd.MyTaskNew;
/**
 * 
 * 项目名称：FormPro 类名称：FormView 类描述： 创建人：Dell 创建时间：2014-4-23 上午11:31:44 修改人：Dell
 * 修改时间：2014-4-23 上午11:31:44 修改备注：
 * 
 * @version
 * 
 */
public class FormMain extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(Class.class);
	private MyJSpinner begin,end;
	private MyJSpinnerM beginM,endM;
	private JComboBox box;
	private JButton btn_cal,btn_stop;
	/**
	 * 初始化组件
	 * 
	 * @方法名: initComponent
	 * @参数
	 */
	private void initComponent() {
		begin = new MyJSpinner();
		end = new MyJSpinner();
		beginM = new MyJSpinnerM();
		endM = new MyJSpinnerM();
		box = new JComboBox(new String[]{"微观数据处理"});
		btn_cal = new JButton("开始");
		btn_cal.setPreferredSize(new java.awt.Dimension(80, 35));
		btn_cal.addActionListener(this);
		btn_stop = new JButton("停止");
		btn_stop.setPreferredSize(new java.awt.Dimension(80, 35));
		btn_stop.addActionListener(this);
	}

	public FormMain() {
		setLayout(new BorderLayout());
		initComponent();
		add(getNorthPanel(),BorderLayout.NORTH);
		add(getCenterPanel(),BorderLayout.CENTER);
		add(getDownPanel(),BorderLayout.SOUTH);
	}
	private JPanel getCenterPanel(){
		JPanel p = new JPanel();
		JPanel p1 = new JPanel();
		FormData fdata=new FormData();
		p.setPreferredSize(new java.awt.Dimension(380,40));
//		p.setBackground(new Color(180,180,180));
		p1.setPreferredSize(new java.awt.Dimension(342,40));
		//fdata.left=new FormAttachment(0.1f,1);
		fdata.left=new FormAttachment(0.4f,1);
		p1.add(btn_cal,fdata);
		p1.add(btn_stop,fdata);
		p1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p.add(p1);
		return p;
	}
	
	
	
	private JPanel getNorthPanel(){
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
//		JPanel p3 = new JPanel();
		
		JPanel p = new JPanel(new GridBagLayout());
//		p.setBackground(new Color(180, 180, 180));
		p.setBorder(new TitledBorder("选择条件"));
		p.setPreferredSize(new java.awt.Dimension(340,150));
		GridBagConstraints c = getGridConstraints();
		p.add(new JLabel(" 起始日期：    "), c);
		c.gridx = 1;
		p.add(begin, c);
		c.gridx = 2;
		p.add(beginM, c); 
		
		c.gridy = 1;
		c.gridx = 0;
		p.add(new JLabel(" 终止日期：    "), c);
		c.gridx = 1;
		p.add(end, c);
		c.gridx = 2;
		p.add(endM, c);
		
		c.gridy = 2;
		c.gridx = 0;
		p.add(new JLabel(" 选择项目：    "), c);
		c.gridx = 1;
		c.gridwidth = 2;
		p.add(box, c);
		c.gridwidth = 1;
		
		
		p1.setPreferredSize(new java.awt.Dimension(380,170));
		p2.setPreferredSize(new java.awt.Dimension(380,5));
		
		p1.add(p2,0);
		p1.add(p,1);
		
		return p1;
	}
	
	
	private JPanel getDownPanel(){
		JPanel p=new JPanel(new FormLayout());
//		p.setBackground(new Color(180,180,180));
		FormData fdata=new FormData();
		fdata.left=new FormAttachment(0.05f,1);
		fdata.top=new FormAttachment(0.2f,1);
		JTextPane textPane=new JTextPane();
		//生成一条横线
		textPane.setPreferredSize(new java.awt.Dimension(335,1));
		textPane.setBackground(Color.BLACK);
		p.add(textPane,fdata);
		
		fdata=new FormData();
		fdata.left=new FormAttachment(0.32f,1);
		fdata.top=new FormAttachment(0.3f,1);
		p.add(new JLabel("青岛天润金穗数码科技有限公司"),fdata);
		fdata=new FormData();
		fdata.left=new FormAttachment(0.34f,1);
		fdata.top=new FormAttachment(0.6f,1);
		p.add(new JLabel("Copyright  2008-2025年"),fdata);
		p.setPreferredSize(new java.awt.Dimension(380,60));
		return p;
	}

	private GridBagConstraints getGridConstraints() {
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0;
		c.weighty = 0;
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);
		c.ipadx = 0;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridy = 0;
		c.gridx = 0;
		return c;
	}

//	private static ServerSocket socket;
	
	public void Test(){
		try {
//			socket = new ServerSocket(12346);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "该程序已启动..");
			System.exit(0);
		}
		JFrame window = new JFrame("青岛天润金穗数码科技有限公司");
		window.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		window.getContentPane().add(new FormMain());
		window.setSize(380,310);
		//获得屏幕尺寸
		Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(screenSize.width/2-450/2,screenSize.height/2-400/2);
		
		
		window.setResizable(false);
		window.setVisible(true);
		
		 
		java.net.URL imgURL = getClass().getResource("logo.png");       //logo.png图片放到当前目录下。方便导出jar包时能正确取到图片
		ImageIcon imgIcon = new ImageIcon(imgURL);
		Image icon = imgIcon.getImage();
		window.setIconImage(icon);
	}
	
	public void testPanel(){
		
		JFrame frame = new JFrame("青岛天润金穗数码科技有限公司");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		frame.setSize(380,310);
		//获得屏幕尺寸
		Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width/2-450/2,screenSize.height/2-400/2);
		
		frame.setBackground(new Color(180,180,180));
		frame.getContentPane().setBackground(frame.getBackground());
		
		frame.getContentPane().setVisible(true);
//		frame.remove(frame.getContentPane());
//		frame.pack();
		
		frame.setResizable(true);
		frame.setVisible(true);
		
	}
	
	public void testFrame(){
		JFrame f = new JFrame("青岛天润金穗数码科技有限公司");
		f.setSize(380, 310);
		// 获得屏幕尺寸
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation(screenSize.width / 2 - 450 / 2,
				screenSize.height / 2 - 400 / 2);
		f.setUndecorated(true);
		f.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
//		f.setBackground(Color.red);
		f.setVisible(true);
// JFrame mainframe = new JFrame("五子棋");
//        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JPanel cp = (JPanel) mainframe.getContentPane();
//        cp.setLayout(new BorderLayout());
//        cp.setBackground(Color.blue);
//        ImageIcon background = new ImageIcon(getClass().getResource("logo.png"));
//        JLabel label=new JLabel(background);
//        cp.add("Center", label);
//        mainframe.pack();
//        mainframe.setVisible(true);
	}
	
	
		  

	
	//添加按钮监听事件方法
	public void actionPerformed(ActionEvent e) {
//		MyTask task = new MyTask();
		MyTaskNew task = new MyTaskNew();
		if(e.getSource()==btn_cal){
			log.info("   开始日期:"+com.trjs.util.DateUtil.date2Str(begin.getDate(),"yyyy")+com.trjs.util.DateUtil.date2Str(beginM.getDate(),"MM")+"01");
			log.info("   结束日期:"+com.trjs.util.DateUtil.date2Str(end.getDate(),"yyyy")+com.trjs.util.DateUtil.date2Str(endM.getDate(),"MM")+"01");
	        task.dingshirw(com.trjs.util.DateUtil.date2Str(begin.getDate(),"yyyy")+com.trjs.util.DateUtil.date2Str(beginM.getDate(),"MM")+"01",com.trjs.util.DateUtil.date2Str(end.getDate(),"yyyy")+com.trjs.util.DateUtil.date2Str(endM.getDate(),"MM")+"01");
	        log.info("   点击开始，执行任务");
		}else if(e.getSource()==btn_stop){
			log.info("   点击停止，退出任务");
			System.exit(0);
		}
	}
}
