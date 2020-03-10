package lab7;

import java.awt.Window.Type;

public class _17213619_ChenZibo_7_BookSpecification 
{
	private double price;
	private String title;
	private int type;
	private String isbn;
	
	public _17213619_ChenZibo_7_BookSpecification(double p,String t,int ty,String i)
	{
		price=p;
		title=t;
		type=ty;
		isbn=i;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public String getIsbn()
	{
		return isbn;
	}
	
	public String gettypename()
	{
		return _17213619_ChenZibo_7_TypeFactory.getBookTypeName(type);
	}
	
	public String gettitle()
	{
		return title;
	}
	
	public int gettype()
	{
		return type;
	}
}
