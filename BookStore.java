package fudandb;


import java.lang.*;
import java.sql.*;
import java.io.*;

public class BookStore {

	/**
	 * @param args
	 */
	public static void displayMenu()
	{
		 System.out.println("       Isa' s serious educational book store     ");
		 System.out.println("--------------------------------------------------");
    	 System.out.println("1. Sign up");
    	 System.out.println("2. Log in");
		 System.out.println("3. Log in as Administrator");
		 System.out.println("4. Log out");
		 System.out.println("--------------------------------------------------");
		 System.out.println("Administrator Section");
		 System.out.println("5. New book");
		 System.out.println("6. Arrival of more copies");
		 System.out.println("7. Manager Statistics");
		 System.out.println("8. User award");
		 System.out.println("--------------------------------------------------");
		 System.out.println("Customer Section");
		 System.out.println("9. Browse for a book(don't need to log in)");
		 System.out.println("10. Place an order");
		 System.out.println("11. Comment on a book");
		 System.out.println("12. Rate a comment");
		 System.out.println("13. Rate other users");
		 System.out.println("--------------------------------------------------");
		 System.out.println("Historical data");
		 System.out.println("14. Useful feedback(don't need to log in)");
		 System.out.println("15. 2-degree Separation(don't need to log in)");
		 System.out.println("16. help yourself(don't need to log in)");
		 System.out.println("--------------------------------------------------");
		 System.out.println("Hope to see you next time!");
		 System.out.println("0. exit:");
    	 System.out.println("please enter your choice:");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connector con=null;
		String choice;
        String sql=null;
        int c=0;
         try
		 {
			 	 con= new Connector();
	             BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			     String username="";
			     String manager="";
			     Manager man=new Manager();
	             while(true) {
					 displayMenu();
					 try {
						 while ((choice = in.readLine()) == null && choice.length() == 0) ;
						 c = Integer.parseInt(choice);
					 } catch (Exception e) {
						 continue;
					 }
					 if (c < 0 | c > 15)
						 continue;
					 /*if (c == 1) {
						 username=Customer.Register(con.stmt);
						 if (!username.equals(""))
							 System.out.println("Welcome," + username + "!");
						 else System.out.println("Try again!");
					 } else if (c == 2) {
						 username = Customer.LogIn(con.stmt);
						 if (!username.equals(""))
							 System.out.println("Welcome back," + username + "!");
						 else System.out.println("Try again!");
					 } else if (c == 3) {
						 if (manager.equals("")) {
							 manager = man.isManager(con.stmt,username,password);
						 }
						 if (!manager.equals(""))
							 System.out.println("Welcome,Administrator!");
						 else System.out.println("Try again!");
					 }
					 else if (c==4){
						 username=manager="";
					 }
					 else if (c == 5){
						 if (manager.equals(""))
							 System.out.println("Please log in!");
						 else
							 man.ManaNewBook(con.stmt);
					 }
					 else if(c==6) {
						 if (manager.equals(""))
							 System.out.println("Please log in!");
						 else
							man.ManaChangeInventory(con.stmt);
					 }
					 else if (c == 7) {
						 if (manager.equals(""))
							 System.out.println("Please log in!");
						 else
						 Statistics.searchStatistics(con.stmt);
					 } else if (c ==8){
						 if (manager.equals(""))
							 System.out.println("Please log in!");
						 else
						 Award.giveAward(con.stmt);
					 }
					 else if(c==9){
						 Browse.browse(con.stmt);
					 }
					 else if(c==10){
						 if(username.equals(""))
							 System.out.println("Please log in!");
						 else{
						 System.out.println(Order.getOrder(username, con.stmt));
						 }
					 }
					 else	 if (c==11){
						 if(username.equals("")){
							 System.out.println("Pleas log in!");
							 continue;
						 }
						 Comment.giveComment(username, con.stmt);
					 }
					 else if(c==12){
						 if(username.equals("")){
							 System.out.println("Pleas log in!");
							 continue;
						 }
						 Rating.giveRating(con.stmt, username);
					 }
					 else if(c==13){
						 if(username.equals("")){
						 System.out.println("Pleas log in!");
						 continue;
					 	}
						 Rating.trustRate(con.stmt, username);
					 }
					 else if(c==14){
						 Comment.getComment(con.stmt);
					 } else if(c==15){
						 Separation.degree(con.stmt);
					 }
	            	 else if (c==16)
	            	 {
	            		 System.out.println("please enter your query below:");
	            		 while ((sql = in.readLine()) == null && sql.length() == 0)
	            			 System.out.println(sql);
	            		 ResultSet rs=con.stmt.executeQuery(sql);
	            		 ResultSetMetaData rsmd = rs.getMetaData();
	            		 int numCols = rsmd.getColumnCount();
	            		 while (rs.next())
	            		 {
	            			 //System.out.print("cname:");
	            			 for (int i=1; i<=numCols;i++)
	            				 System.out.print(rs.getString(i)+"  ");
							 System.out.println("");
						 }
	            		 System.out.println(" ");
	            		 rs.close();
	            	 }
	            	 else
	            	 {
	            		 System.out.println("Remeber to pay us!");
	            		 con.stmt.close();

	            		 break;
	            	 }*/
	             }
		 }
         catch (Exception e)
         {
        	 e.printStackTrace();
        	 System.err.println ("Cannot connect to database server");
         }
         finally
         {
        	 if (con != null)
        	 {
        		 try
        		 {
        			 con.closeConnection();
        			 System.out.println ("Database connection terminated");
        		 }
        	 
        		 catch (Exception e) { /* ignore close errors */ }
        	 }	 
         }
	}
}
