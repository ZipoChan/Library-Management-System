package lab7;

public class _17213619_ChenZibo_7_Controller 
{
	public static void addBook(_17213619_ChenZibo_7_BookSpecification bk)
	{
		_17213619_ChenZibo_7_BookCatalog.addBook(bk);
	}
	
	public static void deleteBook(String isbn)
	{
		_17213619_ChenZibo_7_BookCatalog.deleteBook(isbn);
	}
	
	public static void addSimpleStrategy(int booktype,_17213619_ChenZibo_7_IPricingStrategy ips,String strname)
	{
		_17213619_ChenZibo_7_PricingStrategyFactory.addStrategy(booktype, ips, strname);
	}
	
	
	public static void deleteStrategy(int booktype)
	{
		_17213619_ChenZibo_7_PricingStrategyFactory.deleteStrategy(booktype);
	}
	
	
}
