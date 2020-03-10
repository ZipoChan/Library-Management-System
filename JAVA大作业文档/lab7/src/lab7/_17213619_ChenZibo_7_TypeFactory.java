package lab7;

import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JComboBox;

public class _17213619_ChenZibo_7_TypeFactory 
{
	private static HashMap<Integer, String> booktypename=new HashMap<Integer, String>();
	
	public static String getBookTypeName(int booktype)
	{
		return booktypename.get(booktype);
	}
	
	public static int getBookTypeInt(String name)
	{
		int bktype=0;
		for (HashMap.Entry<Integer, String> entry : booktypename.entrySet())
		{
			if(entry.getValue().equals(name))
				return entry.getKey();
		}
		return bktype;
	}
	
	public static void setBookTypeName(int booktype,String name)
	{
		_17213619_ChenZibo_7_MainUI.print("has");
		if(!booktypename.containsKey(booktype))
		{
			_17213619_ChenZibo_7_MainUI.print("hasn't");
			booktypename.put(booktype, name);
			_17213619_ChenZibo_7_StrategiesUI.addType(name);
			_17213619_ChenZibo_7_AddBookUI.addType(name);
		}
	}
	
	public static void initJB(JComboBox jb)
	{
		for (HashMap.Entry<Integer, String> entry : booktypename.entrySet())
		{
			jb.addItem(entry.getValue());
		}
	}
	
	public static void InitType()
	{
		setBookTypeName(0,"Science");
		setBookTypeName(1,"Social");
		setBookTypeName(2,"Math");
		setBookTypeName(3,"PE");
		setBookTypeName(4,"ComputerScience");
		setBookTypeName(5,"English");
	}
	
	public static boolean hastypeID(int id)
	{
		for (HashMap.Entry<Integer, String> entry : booktypename.entrySet())
		{
			_17213619_ChenZibo_7_MainUI.print("id :"+String.valueOf(entry.getKey()));
			if(entry.getKey().equals(id))
			{
				_17213619_ChenZibo_7_MainUI.print("Has "+String.valueOf(entry.getKey()));
				return true;
			}
		}
		return false;
	}
	
}
