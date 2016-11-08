package fudandb;

import javax.xml.transform.Result;
import java.lang.*;
import java.sql.*;
import java.io.*;
/**
 * Created by lenovo on 2015/5/13.
 */
public class Manager {
    public Manager()
    {};
    public static String IsManager(Statement stmt,String username,String password){
        ResultSet rs=null;
        String secretpass=Coder.passwordDigest(password);
        String sqlchoose="select password from admin where adminname='"+username+"'";
        String output="";
        try{
            rs=stmt.executeQuery(sqlchoose);
            while (rs.next())
            {
                output=rs.getString("password");
            }
            rs.close();
        }catch(Exception e) {//System.out.println("cannot execute the query")
        ;}
        //System.out.println("executing "+sqlchoose);
        try{
            stmt.executeQuery(sqlchoose);
        }catch(Exception e)	{//System.out.println("cannot execute the query")
        ;	}
        finally {
            try{
                if (rs!=null && !rs.isClosed())
                    rs.close();
            }
            catch(Exception e)
            {
                //System.out.println("cannot close resultset");
            }
        }
        if(!output.equals(secretpass))
        {
            //System.out.println("Failed because of a wrong username or password!");
            output="";
        }
        else output=username;
        return output;
    }
//ISBN copy title year prices publisher subject format

    public static String NewBook(Statement stmt,String isbn,String title,String publishyear,String price,String publisher,String subject,String inventory,String format){
        String output="success new book";
        String sql="insert into book values('"+isbn+"','"+title+"',"+publishyear+","+price+",'"+publisher+"','"+subject+"',"+inventory+","+format+")";
        System.out.println("executing " + sql);
        try{
            stmt.executeUpdate(sql);
        }
        catch (Exception e){System.out.println("can't update new book");}
        return output;
    }
    public static boolean ChangeAuthor(String author,String isbn, Statement stmt){
        String sql2="select max(aid)+1 from  writes";
        System.out.println(sql2);
        ResultSet rs=null;
        String aid="";
        try{
            rs=stmt.executeQuery(sql2);
            while(rs.next())
                aid=rs.getString(1);
        } catch (Exception e){System.out.println("can't find max aid!");}//isbn,aid,author
        String sql="insert into writes values('"+isbn+"','"+aid+"','"+author+"')";
        System.out.println(sql);
        try{
            stmt.executeUpdate(sql);
        } catch (Exception e){System.out.println("can't insert author name!");}//isbn,aid,author
        return true;
    }
    public static boolean ChangeInventory(Statement stmt,String ISBN,int copies) {//cannot be used directly
        ResultSet rs = null;
        int origincopy = 0;
        String sql = "select inventory from book where isbn='" + ISBN + "'";

        System.out.println(sql);
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next())
                origincopy = rs.getInt(1);
        } catch (Exception e) {
            //System.out.println("can't find the book record!");
        }
        int newcopy = copies + origincopy;
        String newcopies = String.valueOf(newcopy);
        sql = "UPDATE book	SET inventory = " + newcopies + " where isbn='" + ISBN + "'";
        try {
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            //System.out.println("cannot update number");
             }
            return true;
        }
}
