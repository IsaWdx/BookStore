package fudandb;


import java.lang.*;
import java.sql.*;
import java.io.*;

/**
 * Created by lenovo on 2015/5/14.
 */
public class Rating {
    public Rating(){
    }
    public static boolean giveRating(Statement stmt,String username){
        String rating="",ISBN="",ratedname="";
        int rate=7;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Input the isbn and username of the comment that you want to rate respectively:");
            System.out.println("Isbn:");
            while ((ISBN = in.readLine()) == null && ISBN.length() == 0);
            System.out.println("Username:");
            while ((ratedname = in.readLine()) == null && ratedname.length() == 0||ratedname.equals(username));
            System.out.println("Input the rating:(0:useless,1:useful,2:very useful)");
            while ((rating = in.readLine()) == null && rating.length() == 0 ||(rate =Integer.parseInt(rating))>2||(rate =Integer.parseInt(rating))<0);
        } catch (Exception e){System.out.println("Cannot get comment information or rating is out of range");}
        InsertRate(stmt,username,ratedname,rating,ISBN);
        return true;
    }
    public static boolean InsertRate(Statement stmt,String username,String ratedname,String rating,String isbn){
        String sqlinsert="Insert into rate values ('"+username+"',"+rating+",'"+isbn+"','"+ratedname+"')";
        System.out.println(sqlinsert);
        try {
            stmt.executeUpdate(sqlinsert);
        } catch (Exception e){System.out.println("cannot insert rate");}
        return true;
    }
    public static boolean trustRate(Statement stmt,String username) {
        String rating = "", ratedname = "";
        int rate = 7;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Input the trusted level and username of whom you want to rate respectively:");
            System.out.println("trusted level(-1:not trusted,1:trusted):");
            while ((rating = in.readLine()) == null && rating.length() == 0 || (Integer.parseInt(rating) != -1 && Integer.parseInt(rating) != 1))
                ;
            System.out.println("Username:");
            while ((ratedname = in.readLine()) == null && ratedname.length() == 0 || ratedname.equals(username)) ;
        } catch (Exception e) {
            System.out.println("Cannot get information or rating is out of range");
        }
        InserttrustRate(stmt, username, ratedname, rating);
        return true;
    }
    public static boolean InserttrustRate(Statement stmt, String username, String ratedname, String rating){
        String sqlinsert="insert into trust values('"+username+"','"+ratedname+"',"+rating+")";
        System.out.println(sqlinsert);
        try {
            stmt.executeUpdate(sqlinsert);
        } catch (Exception e){System.out.println("cannot insert rate");}
        return true;
    }
}

