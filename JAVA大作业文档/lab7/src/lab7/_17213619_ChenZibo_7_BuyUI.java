package lab7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

public class _17213619_ChenZibo_7_BuyUI extends JFrame
{
	private String clientname;
	private _17213619_ChenZibo_7_ShoppingCarUI subframe=new _17213619_ChenZibo_7_ShoppingCarUI(clientname);
	private DefaultTableModel tableModel;
	private JTable BookTable=_17213619_ChenZibo_7_BookCatalogTable.createTable(tableModel);
	
	private JLabel jLabel=new JLabel("Copies");
	private JTextField copieJT=new JTextField();
	private JButton buyBT=new JButton("BUY");
	
	public _17213619_ChenZibo_7_BuyUI(String clname)
	{
		copieJT.setDocument(new PlainDocument()
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
		
		clientname=clname;
		setTitle("BuyUI for"+clientname);
		subframe.settile(clname);
		JPanel jp=new JPanel();
		JScrollPane scrollPane = new JScrollPane(BookTable);
		scrollPane.setViewportView(BookTable);
		jp.add(scrollPane);
		
		JPanel subjp=new JPanel();
		subjp.setLayout(new GridLayout(3,4,5,5));
		subjp.add(jLabel);
		subjp.add(copieJT);
		subjp.add(buyBT);
		add(jp);
		getContentPane().add(subjp,BorderLayout.SOUTH);
		
		
		buyBT.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
            			BuyBook();
            }
        });
		
		setVisible(true);
		setBounds(100,100,700,500);
	}
	
	public void BuyBook()
	{
		int selectedRow = BookTable.getSelectedRow();//获得选中行的索引
        if(selectedRow!=-1)  //存在选中行
        {
      
        		int copies=Integer.parseInt(copieJT.getText());
			if(ValidInput(copies))
			{
				double price=Double.parseDouble(BookTable.getValueAt(selectedRow, 4).toString());
				int type= _17213619_ChenZibo_7_TypeFactory.getBookTypeInt(BookTable.getValueAt(selectedRow, 2).toString());
				String isbn=BookTable.getValueAt(selectedRow, 0).toString();
				String title=BookTable.getValueAt(selectedRow, 1).toString();
				_17213619_ChenZibo_7_MainUI.print("compute price");
				price=_17213619_ChenZibo_7_PricingStrategyFactory.getTotal(price, type);
				subframe.AddBook(isbn, title, copies, price);
			}
        	}
	}
	
	public boolean ValidInput(int copies)
	{
		if(copies>0&&copies<10000)
		{
			return true;
		}
		
		return false;
	}
	
	
}
