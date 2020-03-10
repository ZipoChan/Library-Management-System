package lab7;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.AttributeSet.CharacterAttribute;

public class _17213619_ChenZibo_7_PricingStrategyFactory 
{
	public static _17213619_ChenZibo_7_PricingStrategyFactory instance= new _17213619_ChenZibo_7_PricingStrategyFactory();
	private HashMap<Integer, String> strategiename=new HashMap<Integer, String>();
	private HashMap<Integer,_17213619_ChenZibo_7_IPricingStrategy> strategies=new HashMap<Integer, _17213619_ChenZibo_7_IPricingStrategy>();
	
	private _17213619_ChenZibo_7_PricingStrategyFactory()
	{
		
	}
	
	public static void addStrategy(int booktype,_17213619_ChenZibo_7_IPricingStrategy ips,String strname)
	{	
			instance.strategies.put(booktype, ips);
			instance.strategiename.put(booktype, strname);
	}
	
	public static void deleteStrategy(int booktype)
	{
		instance.strategies.remove(booktype);
		instance.strategiename.remove(booktype);
	}
	
	public static void updateStrategy(int booktype,_17213619_ChenZibo_7_IPricingStrategy ips,String strname)
	{
		instance.strategies.replace(booktype, ips);
		instance.strategiename.replace(booktype, strname);
	}
	
	public static _17213619_ChenZibo_7_IPricingStrategy getPricingStrategy(int booktype)
	{
		return instance.strategies.get(booktype);
	}
	
	public static String getPricingStrategyName(int booktype)
	{
		if(instance.strategies.containsKey(booktype))
		return instance.strategiename.get(booktype);
		return "Default";
	}
	
	public static _17213619_ChenZibo_7_IPricingStrategy createSimpleStrategy(int strategytype,double num)
	{
		_17213619_ChenZibo_7_IPricingStrategy ips=null;
		if(strategytype==0)
		{
			ips=new _17213619_ChenZibo_7_NoDiscountStrategy();
		}
		else if(strategytype==1)
		{
			ips=new _17213619_ChenZibo_7_FlatRateStrategy(num);
		}
		else
		{
			ips=new _17213619_ChenZibo_7_PercentageStrategy((int)num);
		}
		return ips;
	}
	
	public static _17213619_ChenZibo_7_IPricingStrategy createCompositeStrategy(ArrayList<_17213619_ChenZibo_7_IPricingStrategy> strategyAray)
	{
		_17213619_ChenZibo_7_CopositeStrategy cs=new _17213619_ChenZibo_7_CopositeStrategy();
		for(_17213619_ChenZibo_7_IPricingStrategy temp:strategyAray)
		{
			cs.add(temp);
		}
		return cs;
	}
	
	
	public static boolean hasbooktype(int booktype)
	{
		if(instance.strategies.containsKey(booktype))
			return true;
		return false;
	}
	
	public static int getTypeTnt(String name)
	{
		int bktype=0;
		for (HashMap.Entry<Integer, String> entry : instance.strategiename.entrySet())
		{
			if(entry.getValue().equals(name))
				return entry.getKey();
		}
		return bktype;
	}
	
	public static double getTotal(double price,int type)
	{
		double p=price;
		if(instance.strategies.containsKey(type))
		{
		_17213619_ChenZibo_7_IPricingStrategy ips=getPricingStrategy(type);
		return ips.getSubTotal(p);
		}
		return p;
	}
	
	public static void updateJB(JComboBox jbx)
	{
		jbx.removeAllItems();
		jbx.addItem("--StrName--");
		for (HashMap.Entry<Integer, String> entry : instance.strategiename.entrySet())
		{
				jbx.addItem(entry.getValue());
		}
	}
	
}
