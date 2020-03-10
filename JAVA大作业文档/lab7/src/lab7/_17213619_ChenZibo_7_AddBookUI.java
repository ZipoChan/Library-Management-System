package lab7;

import java.awt.GridLayout;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class _17213619_ChenZibo_7_AddBookUI extends JPanel
{
	private DefaultTableModel tableModel;
	private JTable BookTable=_17213619_ChenZibo_7_BookCatalogTable.createTable(tableModel);
	
	static private JTextField isbnJT=new JTextField();
	static private JTextField nameJT=new JTextField();
	static private JComboBox typeJCB=new JComboBox();
	static private JTextField priceJT=new JTextField();
	static private JButton AddBT = new JButton("ADD");
	static private JButton DelBT = new JButton("DELETE");
	static private JButton SwitchBT= new JButton("SWITCH");
	
	public _17213619_ChenZibo_7_AddBookUI()
	{
		JScrollPane scrollPane = new JScrollPane(BookTable);
		scrollPane.setViewportView(BookTable);
		add(scrollPane);
		
		JPanel subjp=new JPanel();
		subjp.setLayout(new GridLayout(3,4,5,5));
		subjp.add(new JLabel("isbn"));
		subjp.add(isbnJT);
		subjp.add(new JLabel("name"));
		subjp.add(nameJT);
		subjp.add(new JLabel("type"));
		subjp.add(typeJCB);
		subjp.add(new JLabel("price"));
		subjp.add(priceJT);
		subjp.add(AddBT);
		subjp.add(DelBT);
		subjp.add(SwitchBT);
		add(subjp);
		
		priceJT.setDocument(new PlainDocument()
        {
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
            {
                boolean flag = true;
                for (int i = 0; i < str.length(); i++)
                    if ((str.charAt(i) < '0' || str.charAt(i) > '9')&&str.charAt(i)!='.')
                        flag = false;
                if (flag)
                    super.insertString(offs, str, a);
            }
        });
		
		SwitchBT.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
            			_17213619_ChenZibo_7_MainUI.switchtoS();
            }
        });
		AddBT.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
            			AddBook();
            }
        });
		DelBT.addActionListener(new ActionListener(){//添加事件
            public void actionPerformed(ActionEvent e){
            			DelBook();
            }
        });
	}
	
	public static void addType(String typename)
	{
		_17213619_ChenZibo_7_MainUI.print("addadadadad");
		typeJCB.addItem(typename);
		typeJCB.repaint();
	}
	
	public void AddBook()
	{
		String isbn=isbnJT.getText();
		String name=nameJT.getText();
		int type=_17213619_ChenZibo_7_TypeFactory.getBookTypeInt(typeJCB.getSelectedItem().toString());
		double price=Double.parseDouble(priceJT.getText());
		if(validInput(isbn, price))
		{
			_17213619_ChenZibo_7_BookSpecification bk=new _17213619_ChenZibo_7_BookSpecification(price, name, type, isbn);
			_17213619_ChenZibo_7_MainUI.print("add book "+isbn);
			_17213619_ChenZibo_7_Controller.addBook(bk);
		}
	}
	
	public void DelBook()
	{
		int selectedRow = BookTable.getSelectedRow();//获得选中行的索引
        if(selectedRow!=-1)  //存在选中行
        {
      
            String isbn=BookTable.getValueAt(selectedRow, 0).toString();
        	
    		    	_17213619_ChenZibo_7_MainUI.print("delete "+isbn);
        		_17213619_ChenZibo_7_Controller.deleteBook(isbn);
        }
	}
	
	private boolean validInput(String isbn,double price)
	{
		if(price<=0||price>1000)
		{
			return false;
		}
		if(_17213619_ChenZibo_7_BookCatalog.hasIsbn(isbn))
		{
			return false;
		}
		return true;
	}
	
	public static void InitBook()
	{
		_17213619_ChenZibo_7_BookSpecification bk=new _17213619_ChenZibo_7_BookSpecification(70.0, "Nature", 0, "SCI-1");
		_17213619_ChenZibo_7_Controller.addBook(bk);
		_17213619_ChenZibo_7_BookSpecification bk1=new _17213619_ChenZibo_7_BookSpecification(66.6, "socialism", 1, "SCO-1");
		_17213619_ChenZibo_7_Controller.addBook(bk1);
		_17213619_ChenZibo_7_BookSpecification bk2=new _17213619_ChenZibo_7_BookSpecification(180.6, "FastCompute", 2, "Math-1");
		_17213619_ChenZibo_7_Controller.addBook(bk2);
	}
	
}
