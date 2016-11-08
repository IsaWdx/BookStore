package fudandb;

import java.lang.*;
import java.sql.*;
import java.io.*;
/**
 * Created by IsaWdx on 2015/5/16.
 */
public class Statistics {
    public Statistics(){}
    //a popular books,b.popular authors,c,popular publishers  num
    public  static boolean StatisticsQuery(String choice,Statement stmt,int num){
        ResultSet rs=null;
        String sql="";
        if(choice.equals("a"))sql="select orderbooks.isbn\n" +
                "from orders,orderbooks\n" +
                "where orders.oid=orderbooks.oid and orders.time>(now()-600000000)\n" +
                "group by orderbooks.isbn\n" +
                "order by sum(orderbooks.copy)desc";
        else if(choice.equals("b"))sql="select writes.author\n" +
                "from orders,orderbooks,writes \n" +
                "where orders.oid=orderbooks.oid and writes.isbn=orderbooks.isbn and orders.time>(now()-600000000)\n" +
                "group by writes.author\n" +
                "order by sum(orderbooks.copy)desc\n";
        else if (choice.equals("c"))sql="select book.publisher\n" +
                "from orders,orderbooks,book\n" +
                "where orders.oid=orderbooks.oid and book.isbn=orderbooks.isbn and orders.time>(now()-600000000)\n" +
                "group by book.publisher\n" +
                "order by sum(orderbooks.copy)desc;";
                try{
                    rs=stmt.executeQuery(sql);
                for(int i=0;i<num&&(rs.next());i++)
                    System.out.println(rs.getString(1));
                }catch (Exception e){
                    System.out.println("Cannot get statistics");
                }
        return true;
    }
}
