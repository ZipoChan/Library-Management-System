package lab7;


public class _17213619_ChenZibo_7_FlatRateStrategy implements _17213619_ChenZibo_7_IPricingStrategy
{
	private double discountPerBook;
	public _17213619_ChenZibo_7_FlatRateStrategy(double dpb)
	{
		discountPerBook = dpb;
	}
	public double getSubTotal(double price)
	{
		_17213619_ChenZibo_7_MainUI.print("orign price:"+String.valueOf(price)+"get sub total "+String.valueOf(discountPerBook));
		_17213619_ChenZibo_7_MainUI.print("new price is "+String.valueOf(price-discountPerBook));
		return price-discountPerBook;
	}
	public void add(_17213619_ChenZibo_7_IPricingStrategy ips)
	{
		
	}
}
