package lab7;

import java.util.ArrayList;

public class _17213619_ChenZibo_7_CopositeStrategy implements _17213619_ChenZibo_7_IPricingStrategy
{
	private ArrayList<_17213619_ChenZibo_7_IPricingStrategy> strategies=new ArrayList<_17213619_ChenZibo_7_IPricingStrategy>();
	
	public void add(_17213619_ChenZibo_7_IPricingStrategy ips)
	{
		strategies.add(ips);
	}
	
	public double getSubTotal(double price)
	{
		double miniprice=strategies.get(0).getSubTotal(price);
		for(_17213619_ChenZibo_7_IPricingStrategy temp:strategies)
		{
			if(temp.getSubTotal(price)<miniprice)
			miniprice=temp.getSubTotal(price);
		}
		return miniprice;
	}
}
