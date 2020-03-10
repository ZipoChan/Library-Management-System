package lab7;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.management.remote.SubjectDelegationPermission;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class _17213619_ChenZibo_7_ShoppingCarUI extends JFrame
{
	
	private JTable ShopTable;
	private DefaultTableModel tableModel;
	private JLabel totJL=new JLabel("ToTal Price:");
	private JTextField totJT=new JTextField("0");
	private double totalprice=0;
	
	public _17213619_ChenZibo_7_ShoppingCarUI(String clientname)
	{
		JPanel jp=new JPanel();
		
		Object[][] tableDate= {};
		String[] name={"isbn","title","price","copies"};
		tableModel = new DefaultTableModel(tableDate,name);
		ShopTable = new JTable(tableModel);
		ShopTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		JScrollPane scrollPane = new JScrollPane(ShopTable);
		getContentPane().add(scrollPane,BorderLayout.CENTER);
		
		setTitle("ShoppingCar for"+clientname);
		setBounds(100,100,600,600);
		setVisible(true);
		
		JPanel subjp=new JPanel();
		subjp.setLayout(new GridLayout(1,2,5,5));
		subjp.add(totJL);
		subjp.add(totJT);
		totJT.setEditable(false);
		getContentPane().add(subjp,BorderLayout.SOUTH);
	}
	
	public void AddBook(String isbn,String title,int copies,double price)
	{
		totalprice+=copies*price;
		int index=hasSame(isbn, title, price);
		if(index!=-1)
		{
			int origncopies=Integer.parseInt( ShopTable.getValueAt(index, 3).toString()); 
			origncopies+=copies;
			ShopTable.setValueAt(String.valueOf( origncopies), index, 3);
		}
		else
		{
			String[] rawValue= {isbn,title,String.valueOf(price),String.valueOf(copies)};
			tableModel.addRow(rawValue);
		}
		totJT.setText(String.valueOf(totalprice));
		totJT.repaint();
	}
	
	public int hasSame(String isbn,String title,double price)
	{
		int index=-1;
		for(int i=0;i<ShopTable.getRowCount();i++)
		{
			if(isbn.equals(ShopTable.getValueAt(i, 0)))
				if(title.equals(ShopTable.getValueAt(i, 1)))
					if(price==Double.parseDouble(ShopTable.getValueAt(i, 2).toString()))
						index=i;			
		}
		return index;
	}
	
	public void settile(String clname)
	{
		setTitle("ShoppingCar for"+clname);
	}
	
	
}
