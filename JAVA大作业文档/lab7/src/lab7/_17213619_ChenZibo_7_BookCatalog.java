package lab7;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import lab7._17213619_ChenZibo_7_BookSpecification;

public class _17213619_ChenZibo_7_BookCatalog 
{
	private static LinkedList<_17213619_ChenZibo_7_BookSpecification> books=new LinkedList<_17213619_ChenZibo_7_BookSpecification>();
	
	public static _17213619_ChenZibo_7_BookSpecification getBookSpecification(String isbn)
	{
		_17213619_ChenZibo_7_BookSpecification book=null;
		for(_17213619_ChenZibo_7_BookSpecification temp:books)
		{
			if(isbn==temp.getIsbn())
			{
				book=temp;
				break;
			}
		}
		return book;
	}
	
	public static void addBook(_17213619_ChenZibo_7_BookSpecification bk)
	{
		books.add(bk);
		_17213619_ChenZibo_7_BookCatalogTable.addTable(bk.getPrice(), bk.gettitle(), bk.gettype(), bk.getIsbn());
	}
	
	public static void deleteBook(String isbn)
	{
		
		Iterator<_17213619_ChenZibo_7_BookSpecification> it = books.iterator();
		while(it.hasNext())
		{
		    _17213619_ChenZibo_7_BookSpecification x = it.next();
		    if(x.getIsbn().equals(isbn))
		    {
		    		_17213619_ChenZibo_7_MainUI.print("remove "+isbn+" "+x.gettitle());
		        it.remove();
		        _17213619_ChenZibo_7_BookCatalogTable.deleteTable(isbn);
		        break;
		    }
		}
	}
	
	public static void initTable(DefaultTableModel dtm)
	{
		_17213619_ChenZibo_7_MainUI.print("init start");
		/*Iterator<BookSpecification> it = books.iterator();
		while(it.hasNext())
		{
		    BookSpecification temp = it.next();
		    if(temp!=null)
		    {
		    		MainUI.print("init "+temp.getIsbn());
		    	    String isbn=temp.getIsbn();
				String title=temp.gettitle();
				String typename=temp.gettypename();
				String StrtegyType=PricingStrategyFactory.getPricingStrategyName(temp.gettype());
				String priceStr=String.valueOf(temp.getPrice());
				String [] rowdata= {isbn,title,typename,StrtegyType,priceStr};
				dtm.addRow(rowdata);
		    }
		}*/
		for(_17213619_ChenZibo_7_BookSpecification temp:books)
		{
			_17213619_ChenZibo_7_MainUI.print("init "+temp.getIsbn());
	    	    String isbn=temp.getIsbn();
			String title=temp.gettitle();
			String typename=temp.gettypename();
			String StrtegyType=_17213619_ChenZibo_7_PricingStrategyFactory.getPricingStrategyName(temp.gettype());
			String priceStr=String.valueOf(temp.getPrice());
			String [] rowdata= {isbn,title,typename,StrtegyType,priceStr};
			dtm.addRow(rowdata);
		}
	}
	
	public static void updateTable(DefaultTableModel dtm)
	{
		int cnt=dtm.getRowCount();
		for(int i=0;i<cnt;i++)
		{
			dtm.removeRow(0);
		}
		initTable(dtm);
	}
	
	public static boolean hasbooktype(int type)
	{
		/*Iterator<BookSpecification> it = books.iterator();
		while(it.hasNext())
		{
		    BookSpecification temp = it.next();
		    if(temp!=null)
		    {
		    	    if(temp.gettype()==type)
		    	    {
		    	    		return true;
		    	    }
		    }
		}
		return false;*/
		for(_17213619_ChenZibo_7_BookSpecification temp:books)
		{
			if(temp.gettype()==type)
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasIsbn(String isbn)
	{
		_17213619_ChenZibo_7_MainUI.print("check ISBN with "+isbn);
		for(_17213619_ChenZibo_7_BookSpecification temp:books)
		{
			_17213619_ChenZibo_7_MainUI.print(temp.getIsbn());
			if(isbn.equals(temp.getIsbn()))
			{
				_17213619_ChenZibo_7_MainUI.print("equals");
				return true;
			}
		}
		return false;
	}
}
