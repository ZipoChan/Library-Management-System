package lab7;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.*;

public class _17213619_ChenZibo_7_MainUI extends JFrame
{
	private static JTextArea jta=new JTextArea("",30,45);
	private JButton loginButton=new JButton("Login");
	private JButton registerButton=new JButton("Register");
	
	private static JPanel cards=new JPanel(new CardLayout());
	private static CardLayout cl;
	private static boolean initialflag=true;
	private static int clientcnt=0;
	
	public _17213619_ChenZibo_7_MainUI()
	{
		JMenuBar jMenuBar=new JMenuBar();
		jMenuBar.add(createFileMenu());
		jMenuBar.setVisible(true);
		setJMenuBar(jMenuBar);
		
		//JPanel Loginjp=createLoginJP();
		JPanel p1=new _17213619_ChenZibo_7_StrategiesUI();
        JPanel p2=new _17213619_ChenZibo_7_AddBookUI();
        JPanel jp=new JPanel();
        
        cards.add(p1,"card1");
        cards.add(p2,"card2"); 
        cl=(CardLayout)(cards.getLayout());
        cl.show(cards,"card1");
        
        jp.setLayout(new GridLayout(1,2,5,5));
        
        //jp.add(Loginjp);
        jp.add(cards);
        add(jp);
        setBounds(100,100,740,600);
        setTitle("MainUI");
        setVisible(true);
        
	}
	
	/*public JPanel createLoginJP()
	{
		JPanel jp=new JPanel();
		jta.setLineWrap(true);
		JScrollPane jsp=new JScrollPane(jta);
		jsp.setBounds(600,400,600,400);
		jp.add(jsp);
		jp.add(loginButton);
		jp.add(registerButton);
		loginButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
            			BuyUI buyUI=new BuyUI(String.valueOf(clientcnt++));
            }
        });
		return jp;
	}*/
	
	 private JMenu createFileMenu()
	    {
	        JMenu menu=new JMenu("Menu(M)");
	        menu.setMnemonic(KeyEvent.VK_M);
	        JMenuItem item=new JMenuItem("SetBookType(S)",KeyEvent.VK_S);
	        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
	        menu.add(item);
	        JMenuItem item2=new JMenuItem("Initial(I)",KeyEvent.VK_I);
	        item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
	        menu.add(item2);
	        JMenuItem item3=new JMenuItem("BuyBook(B)",KeyEvent.VK_B);
	        item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
	        menu.add(item3);
	        
	        item.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	                JFrame cframe= createTpyeFrame();
	            }
	        });
	        
	        item2.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	            		if(initialflag)
	            		{
	            		_17213619_ChenZibo_7_TypeFactory.InitType();
	            		_17213619_ChenZibo_7_StrategiesUI.InitStrategy();
	            		_17213619_ChenZibo_7_AddBookUI.InitBook();
	            		initialflag=false;
	            		}
	            }
	        });
	        
	        item3.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	            	_17213619_ChenZibo_7_BuyUI buyUI=new _17213619_ChenZibo_7_BuyUI(String.valueOf(clientcnt++));
	            }
	        });
	        
	        return menu;
	    }
	 
	 private JFrame createTpyeFrame()
	 {
			 JFrame frame=new JFrame("Set Book Type");
			 frame.setTitle("表格");
			 frame.setBounds(100,100,500,400);
			 
	        String[] columnNames = {"BookType","TypeName"}; 
	        String [][]tableVales={}; 
	        DefaultTableModel tableModel = new DefaultTableModel(tableVales,columnNames);
	        JTable table = new JTable(tableModel);
	        JScrollPane scrollPane = new JScrollPane(table); 
	        
	        frame.getContentPane().add(scrollPane,BorderLayout.CENTER);
	        frame.setVisible(true);
	        
	        scrollPane.setViewportView(table);
	        final JPanel panel = new JPanel();
	        
	        panel.add(new JLabel("TypeID: "));
	        JTextField aTextField = new JTextField("",10);
	        panel.add(aTextField);
	        panel.add(new JLabel("TypeName: "));
	        JTextField bTextField = new JTextField("",10);
	        panel.add(bTextField);
	        
	        aTextField.setDocument(new PlainDocument()
	        {
	            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
	            {
	                boolean flag = true;
	                for (int i = 0; i < str.length(); i++)
	                    if ((str.charAt(i) < '0' || str.charAt(i) > '9'))
	                        flag = false;
	                if (flag)
	                    super.insertString(offs, str, a);
	            }
	        });
	        
	        final JButton addButton = new JButton("添加");   //添加按钮
	        addButton.addActionListener(new ActionListener(){//添加事件
	            public void actionPerformed(ActionEvent e){
	            		if(!aTextField.getText().equals(""))
	            		if(!bTextField.getText().equals(""))
	            		{
		                int booktype=Integer.parseInt(aTextField.getText());
		                print("value is"+String.valueOf(booktype));
		                
		                String name=bTextField.getText();
		                print(name);
		                
		                if(!_17213619_ChenZibo_7_TypeFactory.hastypeID(booktype))
		                	{
		                		String []rowValues = {aTextField.getText(),bTextField.getText()};
		    	                tableModel.addRow(rowValues); 
		    	                _17213619_ChenZibo_7_TypeFactory.setBookTypeName(booktype, name);
		                	}
	                
	            		}
	            }
	        });
	        
	        frame.getContentPane().add(panel,BorderLayout.SOUTH);
	        
	        panel.add(addButton);  
	        panel.setVisible(true);;
	        
	        
		 return frame;
	 }
	 
	 public static void print(String msg)
	 {
		 jta.append(msg+"\n");
	 }
	 
	 public static void switchtoS()
	 {
		 cl.show(cards,"card1");
	 }
	 
	 public static void switchtoB()
	 {
		 cl.show(cards,"card2");
	 }
	
}
