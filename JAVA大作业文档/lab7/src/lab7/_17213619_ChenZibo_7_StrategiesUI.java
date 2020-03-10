package lab7;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class _17213619_ChenZibo_7_StrategiesUI extends JPanel
{
	private static JButton switchButton=new JButton("Switch");
	private static JButton DeleteButton=new JButton("Delete");
	private static JButton AddSimpleButton=new JButton("AddSimpleStrategy");
	private static JButton updateButton=new JButton("Update");
	private static JButton CreateCopositeStrategy = new JButton("CreateCopos");
	private static JButton AddCopositeStrategy = new JButton("AddCopositeStrategy");
	private static JTextField StrategyNameJT=new JTextField();  
	private static JTextField StrategyDiscountJT=new JTextField();
	private static JComboBox StrategyTypeJB=new JComboBox();
	private static JComboBox StrategyNameJB=new JComboBox();
	private static JComboBox BookTypeJB=new JComboBox();
	
	private static JTable StrategyTable; 
	private static DefaultTableModel tableModel;
	
	public _17213619_ChenZibo_7_StrategiesUI()
	{
		Object[][] tableDate= {};
		String[] name={"策略号","策略名称","策略类型","适用类型","折扣百分比/每本优惠额度"};
		
		StrategyDiscountJT.setDocument(new PlainDocument()
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
		
		tableModel = new DefaultTableModel(tableDate,name);
		 JTable StrategyTable = new JTable(tableModel);
		 StrategyTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		StrategyTypeJB.addItem("--StrType--"); 
		StrategyTypeJB.addItem("NoDiscount");
		StrategyTypeJB.addItem("Percentage");
		StrategyTypeJB.addItem("FlatRate");
		BookTypeJB.addItem("--BookType--");
		StrategyNameJB.addItem("--StrName--");
		
		JScrollPane scrollPane = new JScrollPane(StrategyTable);
		add(scrollPane);
		
		DeleteButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
            	int selectedRow = StrategyTable.getSelectedRow();//获得选中行的索引
                if(selectedRow!=-1)  //存在选中行
                {
              
                    String typename=StrategyTable.getValueAt(selectedRow, 3).toString();
                    String StrategyName=StrategyTable.getValueAt(selectedRow, 2).toString();
            		    int booktype=_17213619_ChenZibo_7_TypeFactory.getBookTypeInt(typename);
                	
            		    	_17213619_ChenZibo_7_MainUI.print("hasnot "+String.valueOf(booktype));
                		tableModel.removeRow(selectedRow);  //删除行
                		StrategyNameJB.removeItem(StrategyName);
                		_17213619_ChenZibo_7_Controller.deleteStrategy(booktype);
                		_17213619_ChenZibo_7_BookCatalogTable.updateTable();
                		_17213619_ChenZibo_7_PricingStrategyFactory.updateJB(StrategyNameJB);
                }
                }});
		
		AddSimpleButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e)
            {
                 	addSimpleStrategy();
            }
        });
		
		switchButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
            			_17213619_ChenZibo_7_MainUI.switchtoB();
            }
        });
		
		AddCopositeStrategy.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
             	int selectedRow = StrategyTable.getSelectedRow();//获得选中行的索引
                if(selectedRow!=-1)  //存在选中行
                {
              
	                	_17213619_ChenZibo_7_MainUI.print(String.valueOf(selectedRow));
	        			String subStrategyName=StrategyNameJB.getSelectedItem().toString();
	        			int subbooktype=_17213619_ChenZibo_7_PricingStrategyFactory.getTypeTnt(subStrategyName);
	        			_17213619_ChenZibo_7_MainUI.print(String.valueOf(subbooktype));
	        			_17213619_ChenZibo_7_IPricingStrategy subips=_17213619_ChenZibo_7_PricingStrategyFactory.getPricingStrategy(subbooktype);
	        			
	        			String booktypename=StrategyTable.getValueAt(selectedRow, 3).toString();
	        			int bktype=_17213619_ChenZibo_7_TypeFactory.getBookTypeInt(booktypename);
	        			
	        			_17213619_ChenZibo_7_IPricingStrategy cips=_17213619_ChenZibo_7_PricingStrategyFactory.getPricingStrategy(bktype);
	        			cips.add(subips);
	        			String orignstr=StrategyTable.getValueAt(selectedRow, 4).toString();
	        			StrategyTable.setValueAt(orignstr+String.valueOf(subbooktype), selectedRow, 4);
                }

            }
        });
		
		
		CreateCopositeStrategy.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
            		createCopositeStrategy();
            }
        });
		
		updateButton.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e)
            {
            		int selectedRow = StrategyTable.getSelectedRow();//获得选中行的索引
                if(selectedRow!=-1)  //存在选中行
                {
                		String booktypestr=BookTypeJB.getSelectedItem().toString();
                		String strtypeStr=StrategyTypeJB.getSelectedItem().toString();
                		String DiscountStr=StrategyDiscountJT.getText();
                		String StrNameStr=StrategyNameJT.getText();
            		
            		
	            		if(booktypestr.equals("--BookType--")||booktypestr.equals(""))
	            			return;
	            		if(strtypeStr.equals("--StrType--")||strtypeStr.equals(""))
	            			return;
	            		if(DiscountStr.equals(""))
	            			return;
	            		if(StrNameStr.equals(""))
	            			return;
	            		
	            		int num=Integer.parseInt(StrategyDiscountJT.getText());
	            		
	            		if(StrategyTypeJB.getSelectedItem().toString().equals("Percentage")&&(num>=100))
	            			return;
                	
                	
                		String typename=StrategyTable.getValueAt(selectedRow, 3).toString();
                    String StrategyName=StrategyTable.getValueAt(selectedRow, 2).toString();
            		    int booktype=_17213619_ChenZibo_7_TypeFactory.getBookTypeInt(typename);
	                	int orignbooktype=_17213619_ChenZibo_7_TypeFactory.getBookTypeInt(typename);
	            		
	            		_17213619_ChenZibo_7_MainUI.print("select: "+String.valueOf(selectedRow));
	            		
	            		_17213619_ChenZibo_7_MainUI.print("OriginBook"+String.valueOf(orignbooktype));
	            		int newbooktype=_17213619_ChenZibo_7_TypeFactory.getBookTypeInt(BookTypeJB.getSelectedItem().toString());
	            		
	            		if(newbooktype!=orignbooktype&&_17213619_ChenZibo_7_PricingStrategyFactory.hasbooktype(newbooktype))
	            		{
	            			return;
	            		}
	            		
	            			            		
	            		_17213619_ChenZibo_7_MainUI.print("discount is"+String.valueOf(num));
	            		
	            		String strname=StrategyNameJT.getText();
	            		_17213619_ChenZibo_7_IPricingStrategy ips;
	            		if(StrategyTypeJB.getSelectedItem().toString().equals("NoDiscount"))
	            		{
	            			_17213619_ChenZibo_7_MainUI.print("add nodiscount ips");
	            			ips=new _17213619_ChenZibo_7_NoDiscountStrategy();
	            		}
	            		else if(StrategyTypeJB.getSelectedItem().toString().equals("Percentage"))
	            		{
	            			_17213619_ChenZibo_7_MainUI.print("add Percentage ips "+String.valueOf(num));
	            			ips=new _17213619_ChenZibo_7_PercentageStrategy(num);
	            		}
	            		else 
	            		{
	            			_17213619_ChenZibo_7_MainUI.print("add FlatRate ips "+String.valueOf(num));
	            			ips=new _17213619_ChenZibo_7_FlatRateStrategy(num);
	            		}
	            		
	            		StrategyNameJB.removeItem(StrategyName);
	            		
	            		_17213619_ChenZibo_7_Controller.deleteStrategy(orignbooktype);
	            		StrategyTable.setValueAt(String.valueOf(selectedRow), selectedRow, 0);
	            		StrategyTable.setValueAt(StrategyNameJT.getText(), selectedRow, 1);
	            		StrategyTable.setValueAt(StrategyTypeJB.getSelectedItem().toString(), selectedRow, 2);
	            		StrategyTable.setValueAt(BookTypeJB.getSelectedItem().toString(), selectedRow, 3);
	            		StrategyTable.setValueAt(StrategyDiscountJT.getText(), selectedRow, 4);
	            		
	            		StrategyNameJB.addItem(StrategyNameJT.getText());
	            		_17213619_ChenZibo_7_Controller.addSimpleStrategy(newbooktype, ips,strname);
	            		_17213619_ChenZibo_7_BookCatalogTable.updateTable();
	            		
	            		_17213619_ChenZibo_7_PricingStrategyFactory.updateJB(StrategyNameJB);
                }
            }
        });
		
		
		
		
		JPanel subjp=new JPanel();
		subjp.setLayout(new GridLayout(3,4,2,2));
		subjp.add(StrategyNameJT);
		subjp.add(StrategyDiscountJT);
		subjp.add(BookTypeJB);
		subjp.add(StrategyTypeJB);
		subjp.add(StrategyNameJB);
		subjp.add(AddSimpleButton);
		subjp.add(CreateCopositeStrategy);
		subjp.add(AddCopositeStrategy);
		subjp.add(updateButton);
		subjp.add(DeleteButton);
		subjp.add(switchButton);
		add(subjp);
	}
	
	public void update(int selectedRow)
	{
		String booktypestr=BookTypeJB.getSelectedItem().toString();
		String strtypeStr=StrategyTypeJB.getSelectedItem().toString();
		String DiscountStr=StrategyDiscountJT.getText();
		String StrNameStr=StrategyNameJT.getText();
		
		
		if(booktypestr.equals("--BookType--")||booktypestr.equals(""))
			return;
		if(strtypeStr.equals("--StrType--")||strtypeStr.equals(""))
			return;
		if(DiscountStr.equals(""))
			return;
		if(StrNameStr.equals(""))
			return;
		
		int num=Integer.parseInt(StrategyDiscountJT.getText());
		
		if(StrategyTypeJB.getSelectedItem().toString().equals("Percentage")&&(num>=100))
			return;
		
		 String typename=StrategyTable.getValueAt(selectedRow, 3).toString();
         String StrategyName=StrategyTable.getValueAt(selectedRow, 2).toString();
 		  int orignbooktype=_17213619_ChenZibo_7_TypeFactory.getBookTypeInt(typename);
		
		_17213619_ChenZibo_7_MainUI.print("select: "+String.valueOf(selectedRow));
		
		_17213619_ChenZibo_7_MainUI.print("OriginBook"+String.valueOf(orignbooktype));
		int newbooktype=_17213619_ChenZibo_7_TypeFactory.getBookTypeInt(BookTypeJB.getSelectedItem().toString());
		
		if(newbooktype!=orignbooktype&&_17213619_ChenZibo_7_PricingStrategyFactory.hasbooktype(newbooktype))
		{
			return;
		}
		
		
		_17213619_ChenZibo_7_MainUI.print("discount is"+String.valueOf(num));
		
		String strname=StrategyNameJT.getText();
		_17213619_ChenZibo_7_IPricingStrategy ips;
		if(StrategyTypeJB.getSelectedItem().toString().equals("NoDiscount"))
		{
			_17213619_ChenZibo_7_MainUI.print("add nodiscount ips");
			ips=new _17213619_ChenZibo_7_NoDiscountStrategy();
		}
		else if(StrategyTypeJB.getSelectedItem().toString().equals("Percentage"))
		{
			_17213619_ChenZibo_7_MainUI.print("add Percentage ips "+String.valueOf(num));
			ips=new _17213619_ChenZibo_7_PercentageStrategy(num);
		}
		else if(StrategyTypeJB.getSelectedItem().toString().equals("FlatRate"))
		{
			_17213619_ChenZibo_7_MainUI.print("add FlatRate ips "+String.valueOf(num));
			ips=new _17213619_ChenZibo_7_FlatRateStrategy(num);
		}
		else {
			return;
		}
		
		
		
		_17213619_ChenZibo_7_Controller.deleteStrategy(orignbooktype);
		
		tableModel.setValueAt(String.valueOf(selectedRow), selectedRow, 0);
		tableModel.setValueAt(StrategyNameJT.getText(), selectedRow, 1);
		tableModel.setValueAt(StrategyTypeJB.getSelectedItem().toString(), selectedRow, 2);
		tableModel.setValueAt(BookTypeJB.getSelectedItem().toString(), selectedRow, 3);
		tableModel.setValueAt(StrategyDiscountJT.getText(), selectedRow, 4);
		
		StrategyNameJB.addItem(StrategyNameJT.getText());
		_17213619_ChenZibo_7_Controller.addSimpleStrategy(newbooktype, ips,strname);
		_17213619_ChenZibo_7_BookCatalogTable.updateTable();
	}
	
	public void addSimpleStrategy()
	{
		String booktypestr=BookTypeJB.getSelectedItem().toString();
		String strtypeStr=StrategyTypeJB.getSelectedItem().toString();
		String DiscountStr=StrategyDiscountJT.getText();
		String StrNameStr=StrategyNameJT.getText();
		
		if(booktypestr.equals("--BookType--")||booktypestr.equals(""))
			return;
		if(strtypeStr.equals("--StrType--")||strtypeStr.equals(""))
			return;
		if(DiscountStr.equals(""))
			return;
		if(StrNameStr.equals(""))
			return;
		
		
		int booktype=_17213619_ChenZibo_7_TypeFactory.getBookTypeInt(booktypestr);
		
		if(_17213619_ChenZibo_7_PricingStrategyFactory.hasbooktype(booktype))
		{
			return;
		}
		
		int num=Integer.parseInt(StrategyDiscountJT.getText());
		
		if(StrategyTypeJB.getSelectedItem().toString().equals("Percentage")&&(num>=100))
			return;
		
		_17213619_ChenZibo_7_MainUI.print("simple booktype is"+String.valueOf(booktype));
		
		_17213619_ChenZibo_7_MainUI.print("1"+StrategyDiscountJT.getText());
		
		
		_17213619_ChenZibo_7_MainUI.print("1"+StrategyDiscountJT.getText());
		
		
		_17213619_ChenZibo_7_MainUI.print("discount is"+String.valueOf(num));
		
		String strname=StrategyNameJT.getText();
		_17213619_ChenZibo_7_IPricingStrategy ips;
		if(StrategyTypeJB.getSelectedItem().toString().equals("NoDiscount"))
		{
			_17213619_ChenZibo_7_MainUI.print("add nodiscount ips");
			ips=new _17213619_ChenZibo_7_NoDiscountStrategy();
		}
		else if(StrategyTypeJB.getSelectedItem().toString().equals("Percentage"))
		{
			_17213619_ChenZibo_7_MainUI.print("add Percentage ips "+String.valueOf(num));
			ips=new _17213619_ChenZibo_7_PercentageStrategy(num);
		}
		else if(StrategyTypeJB.getSelectedItem().toString().equals("FlatRate"))
		{
			_17213619_ChenZibo_7_MainUI.print("add FlatRate ips "+String.valueOf(num));
			ips=new _17213619_ChenZibo_7_FlatRateStrategy(num);
		}
		else {
			return;
		}
		String []rowValues = {"1",StrategyNameJT.getText(),StrategyTypeJB.getSelectedItem().toString(),BookTypeJB.getSelectedItem().toString(),StrategyDiscountJT.getText()};
        tableModel.addRow(rowValues); 
		StrategyNameJB.addItem(StrategyNameJT.getText());
		_17213619_ChenZibo_7_Controller.addSimpleStrategy(booktype, ips,strname);
	}
	
	
	public void addCopositeStrategy()
	{
		_17213619_ChenZibo_7_MainUI.print("add coposit");
		int selectedRow = StrategyTable.getSelectedRow();
		_17213619_ChenZibo_7_MainUI.print(String.valueOf(selectedRow));
		if(selectedRow!=-1)
		{
		
			_17213619_ChenZibo_7_MainUI.print(String.valueOf(selectedRow));
			String subStrategyName=StrategyNameJB.getSelectedItem().toString();
			int subbooktype=_17213619_ChenZibo_7_PricingStrategyFactory.getTypeTnt(subStrategyName);
			_17213619_ChenZibo_7_MainUI.print(String.valueOf(subbooktype));
			_17213619_ChenZibo_7_IPricingStrategy subips=_17213619_ChenZibo_7_PricingStrategyFactory.getPricingStrategy(subbooktype);
			
			String booktypename=StrategyTable.getValueAt(selectedRow, 3).toString();
			int bktype=_17213619_ChenZibo_7_TypeFactory.getBookTypeInt(booktypename);
			
			_17213619_ChenZibo_7_IPricingStrategy cips=_17213619_ChenZibo_7_PricingStrategyFactory.getPricingStrategy(bktype);
			cips.add(subips);
		}
	}
	
	public void createCopositeStrategy()
	{
		String booktypestr=BookTypeJB.getSelectedItem().toString();
		String StrNamestr=StrategyNameJB.getSelectedItem().toString();
		if(booktypestr.equals("--BookType--"))
			return;
		if(StrNamestr.equals("--StrName--"))
			return;
		
		int booktype=_17213619_ChenZibo_7_TypeFactory.getBookTypeInt(booktypestr);
		
		if(_17213619_ChenZibo_7_PricingStrategyFactory.hasbooktype(booktype))
		{
			return;
		}
		
		
		_17213619_ChenZibo_7_MainUI.print("create coposit");
		_17213619_ChenZibo_7_MainUI.print("create COposite");
		String []rowValues = {"1",StrategyNameJT.getText(),"CopositeStrategy",BookTypeJB.getSelectedItem().toString(),""};
         tableModel.addRow(rowValues); 
         _17213619_ChenZibo_7_MainUI.print("booktype:"+String.valueOf(booktype));
         String strname=StrategyNameJT.getText();
         _17213619_ChenZibo_7_IPricingStrategy ips=new _17213619_ChenZibo_7_CopositeStrategy();
         _17213619_ChenZibo_7_Controller.addSimpleStrategy(booktype, ips,strname);
		
	}
	
	
	public static void addType(String typename)
	{
		_17213619_ChenZibo_7_MainUI.print("addadadadad");
		BookTypeJB.addItem(typename);
		BookTypeJB.repaint();
	}
	
	public static void InitStrategy()
	{
		_17213619_ChenZibo_7_IPricingStrategy ips;
		ips=new _17213619_ChenZibo_7_PercentageStrategy(20);
		StrategyNameJB.addItem("Science Discount");
		_17213619_ChenZibo_7_Controller.addSimpleStrategy(0, ips,"Science Discount");
		String []rowValues = {"0","Science Discount","Percentage","Science","20"};
        tableModel.addRow(rowValues); 
        
        _17213619_ChenZibo_7_IPricingStrategy ips1;
		ips1=new _17213619_ChenZibo_7_PercentageStrategy(33);
		StrategyNameJB.addItem("Social Discount");
		_17213619_ChenZibo_7_Controller.addSimpleStrategy(1, ips1,"Social Discount");
		String[] rowValues1 = {"1","Social Discount","Percentage","Social","33"};
        tableModel.addRow(rowValues1); 
        
        _17213619_ChenZibo_7_IPricingStrategy ips2;
		ips2=new _17213619_ChenZibo_7_FlatRateStrategy(10);
		StrategyNameJB.addItem("Math Discount");
		_17213619_ChenZibo_7_Controller.addSimpleStrategy(2, ips2,"Math Discount");
		String[] rowValues2 = {"2","Math Discount","FlatRate","Math","10"};
        tableModel.addRow(rowValues2); 
        
        
	}
	
	
	/*public static void updateStrategyNameJB()
	{
		StrategyNameJB.removeAll();
		int cnt=tableModel.getRowCount();
		System.out.println(cnt);
		for(int row=0;row<cnt;row++)
		{
			if(!StrategyTable.getValueAt(row, 2).toString().equals("CopositeStrategy"))
			{
				StrategyNameJB.addItem(StrategyTable.getValueAt(row, 1).toString());
			}
		}
	}*/
	
	
	
	
}
