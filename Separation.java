package fudandb;
import java.lang.*;
import java.sql.*;
import java.io.*;

/**
 * Created by lenovo on 2015/5/16.
 */
public class Separation {
    public Separation(){}
    public static int degree(Statement stmt){
        String a1="",a2="";
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Input name1:");
            while ((a1 = in.readLine()) == null && a1.length() == 0) ;
            System.out.println("Input name2:");
            while ((a2 = in.readLine()) == null && a1.length() == 0) ;
        } catch (Exception e) {
            System.out.println("cannot get book numbers!");
        }
        ResultSet rs=null;
        String sql="select distinct(a1.author),a2.author\n" +
                "from writes a1,writes a2\n" +
                "where a1.isbn=a2.isbn and a1.author!=a2.author";
        try{
            rs=stmt.executeQuery(sql);
            while(rs.next()) {
                if (a1.equals(rs.getString(1))&&a2.equals(rs.getString(2))){
                    System.out.println("1-degree");
                    return 1;
                }
            }
        }catch (Exception e){System.out.println("cannot get 1-degree information");}
        String sql2="select distinct (t1.c2),t2.c2\n" +
                "from (select distinct(a1.author) as c1,a2.author as c2\n" +
                "      from writes a1,writes a2\n" +
                "       where a1.isbn=a2.isbn  and a1.author!=a2.author) as t1,\n" +
                "       (select distinct(a1.author) as c1,a2.author as c2\n" +
                "      from writes a1,writes a2\n" +
                "       where a1.isbn=a2.isbn  and a1.author!=a2.author) as t2\n" +
                "where t1.c1=t2.c1 and t1.c2!=t2.c2 and (t1.c2,t2.c2)not in \n" +
                "(select distinct(a1.author) as c1,a2.author as c2\n" +
                "      from writes a1,writes a2\n" +
                "       where a1.isbn=a2.isbn and a1.author!=a2.author )";
        try{
            rs=stmt.executeQuery(sql2);
            while(rs.next()) {
                if (a1.equals(rs.getString(1))&&a2.equals(rs.getString(2))){
                    System.out.println("2-degree");
                    return 2;
                }
            }
        }catch (Exception e){System.out.println("cannot get 2-degree information");}
        System.out.println("far away");
        return 0;
    }
}
