package fudandb;
import java.lang.*;
import java.sql.*;
import java.io.*;
/**
 * Created by lenovo on 2015/5/15.
 */
public class Browse {
    public Browse(){
    }
    public static boolean browse(Statement stmt){
        String condition="",sortway="";
        ResultSet rs=null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Input the condition like this:");
            System.out.println("(condition A and Condition B) or (conidion C and condition D):");
            System.out.println("valid conditions includes:authors, publisher, title, subject:");
            while ((condition = in.readLine()) == null && condition.length() == 0) ;
            System.out.println("How do you want to sort the result?");
            System.out.print("a: by year, or b: by the average numerical score of the feedbacks, or c: by the average numerical score of the trusted user feedbacks.\n");
            while (((sortway = in.readLine()) == null && sortway.length() == 0 )||!sortway.equals("a")&&!sortway.equals("b")&&!sortway.equals("c")) ;//need check
        }catch (Exception e){System.out.println("cannot get feedback information");}
        rs=browseQuery(stmt,condition,sortway);
        try{
            System.out.println("(book.isbn)\ttitle\tpublish year\tprice\tpublisher\tsubject\tinventory\tformat");
            while(rs.next()){
            for(int i=0;i<8;i++)
                if(i==0||i==1||i==4||i==5)
                    System.out.print(rs.getString(i+1)+"\t");
                else if(i==2||i==6)
                    System.out.print(rs.getInt(i+1)+"\t");
                else
                    System.out.print(rs.getDouble(i+1)+"\t");
            System.out.print("\n");
        }
        }catch (Exception e){System.out.println("cannot output resultset");}
        return true;
    }
    public static ResultSet browseQuery(Statement stmt,String condition,String sortway){
        String sqlselect="";
        ResultSet rs=null;
        if(sortway.equals("b"))sqlselect="select distinct (book.isbn),title,publishyear,price,publisher,subject,inventory,format "+
        "from book,writes,feedback where book.isbn=writes.isbn and  feedback.isbn=book.isbn and("+condition+")group by isbn order by avg(score) desc";
        else if(sortway.equals("a"))sqlselect="select distinct (book.isbn),title,publishyear,price,publisher,subject,inventory,format from book,writes where book.isbn=writes.isbn and ("
                +condition+")order by publishyear desc";
        else if(sortway.equals("c"))sqlselect="select distinct (book.isbn),title,publishyear,price,publisher,subject,inventory,format\n" +
                "from book,writes,feedback, \n" +
                "(select customer.username\n" +
                "from customer, trust\n" +
                "where customer.username=trust.trusted\n" +
                "group by customer.username\n" +
                "having sum(level)>0\n" +
                ")as trustedusers\n" +
                "where trustedusers.username=feedback.username and book.isbn=feedback.isbn and writes.isbn=book.isbn and (\n"+condition+
                ")group by isbn order by avg(score) desc";
        System.out.println(sqlselect);
        try {
            rs=stmt.executeQuery(sqlselect);
        } catch (Exception e){System.out.println("cannot select books");}
        return rs;
    }
}
