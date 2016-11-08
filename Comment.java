package fudandb;


import java.lang.*;
import java.sql.*;
import java.io.*;


/**
 * Created by lenovo on 2015/5/14.
 */
public class Comment {
    public Comment(){}
    // star:1 to 10 word optional
    public static boolean InsertComment(String isbn,String username,String score,String text,Statement stmt){
        Paytime t=new Paytime();
        ResultSet rs=null;
        String date=t.getDay();
        String sqlinsert="";
        if(text.equals(""))
            sqlinsert = "insert into feedback(score,isbn,username,date) values(" + score + ",'" + isbn + "','" + username + "','" + date + "')";
        else
            sqlinsert = "insert into feedback values('" + text + "'," + score + ",'" + isbn + "','" + username + "','" + date + "')";
        System.out.println(sqlinsert);
        try{
            stmt.executeUpdate(sqlinsert);
        }catch (Exception e){
            System.out.println("cannot insert your comment!Perhaps you have already left one.");
        }
        return true;
    }
    public static boolean SelectComment(String isbn,int num,Statement stmt){
        ResultSet rs=null;
        String sqlselect="select f.text,f.score,f.username,f.date,f.isbn,avg(r.RATESCORE)\n" +
                "from feedback f,rate r\n" +
                "where r.isbn=f.isbn and r.ratedname=f.username and f.isbn='"+isbn+"'\n" +
                "group by f.isbn,f.username\n" +
                "order by avg(r.RATESCORE) desc";
            //System.out.println(sqlselect);
        try{
            rs=stmt.executeQuery(sqlselect);
        }catch (Exception e){
            //System.out.println("cannot find the comment!");
        }
      //  System.out.println("f.text\tf.score\tf.username\tf.date\tf.isbn\tavg(r.RATESCORE)");
        try{
            for(int i=0;i<num&&rs.next();i++) {
                for (int j = 0; j < 6; j++) {
                    if (j == 0 || j == 2 || j == 3 || j == 4)
                        System.out.print(rs.getString(j + 1) + "\t");
                    else if (j == 1)
                        System.out.print(rs.getInt(j + 1) + "\t");
                    else
                        System.out.print(rs.getDouble(j + 1) + "\t");
                }
                System.out.print("\n");
            }
        }catch (Exception e){
            //System.out.println("cannot output the result");
        }
        return true;
    }
}
