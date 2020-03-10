package lab7;

import javax.swing.JPanel;

import javax.swing.JTable;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.*;

public class _17213619_ChenZibo_7_BookCatalogTable
{
	
	private static LinkedList<_17213619_ChenZibo_7_TablePair> tableList=new LinkedList<_17213619_ChenZibo_7_TablePair>();

	public _17213619_ChenZibo_7_BookCatalogTable()
	{
	}
	
	public static JTable createTable(DefaultTableModel tableModel)
	{
		
		Object[][] tableDate= {};
		String[] name={"isbn","title","type","strategy","price"};
		tableModel = new DefaultTableModel(tableDate,name);
		JTable table=new JTable(tableModel);
		_17213619_ChenZibo_7_BookCatalog.initTable(tableModel);
	    table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);	   
	    _17213619_ChenZibo_7_TablePair tablePair=new _17213619_ChenZibo_7_TablePair(table, tableModel);
	    tableList.add(tablePair);
		return table;
	}
	
	public static void addTable(double price,String title,int type,String isbn)
	{
		for(_17213619_ChenZibo_7_TablePair temp:tableList)
		{
			String typename=_17213619_ChenZibo_7_TypeFactory.getBookTypeName(type);
			String sname=_17213619_ChenZibo_7_PricingStrategyFactory.getPricingStrategyName(type);
			String priceStr=String.valueOf(price);
			if(temp!=null)
			{
				String [] rawvalues= {isbn,title,typename,sname,priceStr};
				temp.tmodel.addRow(rawvalues);
			}
		}
	}
	
	public static void deleteTable(String isbn)
	{
		for(_17213619_ChenZibo_7_TablePair temp:tableList)
		{
			for(int i=0;i<temp.table.getRowCount();i++)
			{
				if(temp.table.getValueAt(i,0).equals(isbn))
				{
					temp.tmodel.removeRow(i);
				}
			}
		}
	}
	
	public static void updateTable()
	{
		for(_17213619_ChenZibo_7_TablePair temp:tableList)
		{
			_17213619_ChenZibo_7_BookCatalog.updateTable(temp.tmodel);
		}
	}
	
	
	
}

