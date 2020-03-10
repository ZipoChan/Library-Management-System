package lab7;

public class _17213619_ChenZibo_7_PercentageStrategy implements _17213619_ChenZibo_7_IPricingStrategy
{
	private int discountPercentage;
	
	public _17213619_ChenZibo_7_PercentageStrategy(int pc) 
	{
		discountPercentage=pc;
	}
	
	public double getSubTotal(double price)
	{
		return price*(100-discountPercentage)/100;
	}
	
	public void add(_17213619_ChenZibo_7_IPricingStrategy ips)
	{
		
	}
}
