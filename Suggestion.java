package fudandb;
import java.lang.*;
import java.sql.*;
import java.io.*;
/**
 * Created by lenovo on 2015/5/16.
 */
public class Suggestion {
    public Suggestion(){
    }
    {};
    public static boolean GiveSuggestion(String username,Statement stmt,String oid){
        System.out.println("Other users also buy:");
        String sql="select orderbooks.isbn,book.title\n" +
                "from orderbooks,book,\n" +
                "\t(select distinct(orderbooks.oid )as o_oid,orders.username as u_name\n" +
                "\tfrom orderbooks,orders,\n" +
                "\t\t(select orderbooks.isbn as o_isbn\n" +
                "\t\tfrom orders,orderbooks\n" +
                "\t\twhere orders.oid=orderbooks.oid and orders.oid='"+oid+"' )as t\n" +
                "\twhere orderbooks.isbn=t.o_isbn and orders.oid=orderbooks.oid)as e\n" +
                "where orderbooks.isbn not in \n" +
                "\t(select orderbooks.isbn as o_isbn\n" +
                "\tfrom orders,orderbooks\n" +
                "\twhere orders.oid=orderbooks.oid and orders.username='"+username+"' ) and book.isbn=orderbooks.isbn\n" +
                "group by orderbooks.isbn\n" +
                "order by sum(orderbooks.copy)desc";
        ResultSet rs=null;
        try{
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString(1)+","+rs.getString(2));
            }
        }catch (Exception e){System.out.println("cannot execute query");}

                return true;
    }



}
