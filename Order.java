package fudandb;


import java.lang.*;
import java.sql.*;
import java.io.*;
/**
 * Created by lenovo on 2015/5/11.
 */
public class Order {
    public Order()
    {};
    public static String getOrder(String username,Statement stmt) {
        String number="0", output = "",sql="",oid="",prices="0";
        double price=0;
        if (username.equals("")) {
            System.out.println("haven't logged in");
            output = "not successful";
            return output;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(number);
        while(num<1||num>99999999){
            try {
                System.out.println("how many kinds of books do you want?");
                while ((number = in.readLine()) == null && number.length() == 0) ;
                num = Integer.parseInt(number);
            } catch (Exception e) {
                System.out.println("cannot get book numbers!");
            }
            // int oid=newOid(stmt),

        };
        Paytime paytime=new Paytime();
        String pay=paytime.getPaytime();
        oid+=pay+username;
        sql+="Insert into orders  (oid,time,paymentstatus,username,total)VALUES('"+oid+"','"+pay+"',"+0+",'"+username+"',"+prices+")";
        //System.out.println(sql);
        try{
            stmt.executeUpdate(sql);
        }catch(Exception e){System.out.println("can't give order!");return output;}//if not successful should delete from orderbooks
        System.out.println("executing "+sql);
        while (num > 0) {
            num--;
            System.out.println("Input book ISBN");
            String ISBN = "", copy = "";
            int copies = 0;
            try {
                while ((ISBN = in.readLine()) == null && ISBN.length() == 0) ;
            } catch (Exception e) {
                num++;
                System.out.println("cannot get book ISBN!");
            }
            System.out.println("Input numbers!");
            try {
                String sql2="select inventory from book where isbn='"+ISBN+"'";
                //System.out.println(sql2);
                ResultSet rs=null;
                int origincopy=0;
                rs=stmt.executeQuery(sql2);
                while(rs.next())
                    origincopy=rs.getInt(1);
                System.out.println(origincopy);
                while ((copy = in.readLine()) == null && copy.length() == 0||(copies = Integer.parseInt(copy))<0||(origincopy-copies)<0) ;
            } catch (Exception e) {
                num++;
                System.out.println("cannot get book copy!");
                continue;
            }
            ResultSet result=null;
            String sql3="select book.price from book where book.isbn='"+ISBN+"'";
            System.out.println(sql3);
            try{
                result=stmt.executeQuery(sql3);
            }catch(Exception e){System.out.println("can't fetch money!");}
            try{
                 double money=0;
                while(result.next()){
                money=result.getDouble(1);}
                price+=money*copies;
            }catch (Exception e){System.out.println("Cannot calculate the total price!");}
            String sql2="Insert into orderbooks (oid,isbn,copy)VALUES('"+oid+"','"+ISBN+"',"+copies+")";
            try{
                stmt.executeUpdate(sql2);
            }catch(Exception e){System.out.println("can't order this book!");num++;continue;}
            try{
                System.out.print(Manager.ChangeInventory(stmt, ISBN, -copies));
            }catch(Exception e){System.out.println("can't order this book!");num++;continue;}
            System.out.println("executing " + sql2);
        }
        prices=String.valueOf(price);
        String sql4=" UPDATE orders SET total= "+price+"  where oid= '"+oid+"'";
        try{
            stmt.executeUpdate(sql4);
        }catch(Exception e){System.out.println("can't update price!");}
        Suggestion sug=new Suggestion();
        return pay+username;
    }
    public static int newOid(Statement stmt){//this function doesn't work
        String sql="select sum(oid) as o from orders";
        ResultSet rs=null;
        int oldoid=0;
        int oid=0;

        System.out.println(" executing"+sql+"\n");
        try{
            rs=stmt.executeQuery(sql);
        }
        catch(Exception e)
        {
            System.out.println("cannot execute the query or the first order");
        }
        finally
        {
            try{
                if (rs!=null && !rs.isClosed())
                    rs.close();
            }
            catch(Exception e)
            {
                System.out.println("cannot close resultset");
            }
        }
        try{
            while(rs.next()) {
                oldoid= rs.getInt(1);
            }
        }catch (Exception e){System.out.println("cannot get int");}
        oid=oldoid+1;
        return oid;
    }


}
