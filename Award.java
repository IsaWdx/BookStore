package fudandb;
import java.lang.*;
import java.sql.*;
import java.io.*;
/**
 * Created by lenovo on 2015/5/16.
 */
public class Award {
    public Award(){
    }
    public static boolean giveAward(Statement stmt) {
        int input = 0;
        String choice = "", inputs = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("What do you want to know?(a.trusted users,b.useful users)");
            while ((choice = in.readLine()) == null && choice.length() == 0) ;
            System.out.println("How many of it?(1-10000)");
            while (((inputs = in.readLine()) == null && inputs.length() == 0) || (input = Integer.parseInt(inputs)) > 10000 || (input = Integer.parseInt(inputs)) < 1);
        } catch (Exception e) {
            System.out.println("cannot get information");
        }
        StatisticsQuery(choice, stmt, input);
        return true;
    }
    public static boolean StatisticsQuery(String choice,Statement stmt,int num){
        ResultSet rs=null;
        String sql="";
        if(choice.equals("a"))sql="select customer.username\n" +
                "from customer,trust\n" +
                "where customer.username=trust.trusted\n" +
                "group by customer.username\n" +
                "order by sum(trust.level)desc;";
        else if(choice.equals("b"))sql="select feedback.username\n" +
                "from feedback,rate\n" +
                "where feedback.username=rate.ratedname\n" +
                "group by feedback.username\n" +
                "order by sum(rate.ratescore)desc";

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
