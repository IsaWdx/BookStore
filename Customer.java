package fudandb;


import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.lang.*;
import java.sql.*;
import java.io.*;

public class Customer {
		public Customer()
		{}
		//some problem exists with encrypting**********
	    public static boolean NoDuplicate(Statement stmt,String username){
			ResultSet rs=null;
			try {
						String sql="select * from customer where username='"+username+"'";
						rs=stmt.executeQuery(sql);
						int cnt=0;
						while(rs.next()){
							cnt++;
						}
						if(cnt==0) {
							return true;
						}
			}catch (Exception e){//System.out.println("cannot get personal information");
			}
			return false;
		}
		public static String Register(Statement stmt,String username,String realname, String address,String phone,String password)
		{
			String output="";
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			ResultSet rs=null;

			Course course=new Course();
			Coder coder = new Coder();
			String secretpass="";
			try{
            secretpass = coder.passwordDigest(password);}
			catch (Exception e){
				//System.out.println("cannot encrypt!");
			}
			//String sqlreg="insert into customer (username, realname,password,address,phone) VALUES ('"+username+"','"+realname+"','"+password+"','"+address+"','"+phone+"')";
			String sqlreg="insert into customer (username, realname,password,address,phone) VALUES ('"+username+"','"+realname+"','"+secretpass+"','"+address+"','"+phone+"')";
   		 	//System.out.println("executing "+sqlreg);
   		 	try{
	   		 	stmt.executeUpdate(sqlreg);
   		 	}
   		 	catch(Exception e)
   		 	{
				username="";//System.out.println("cannot execute the update");
   		 	}
   		 	finally
   		 	{
   		 		try{
	   		 		if (rs!=null && !rs.isClosed())
	   		 			rs.close();
   		 		}
   		 		catch(Exception e)
   		 		{
   		 			//System.out.println("cannot close resultset");
					username="";
   		 		}
   		 	}
		    return output;
		}

		public static String LogIn(Statement stmt,String username,String password)
		{
			Coder coder = new Coder();
			String secretpass="";
			try{
				secretpass = coder.passwordDigest(password);}
			catch (Exception e){System.out.println("cannot encrypt");}
			//find out the real code
			ResultSet rs=null;
			String sqlchoose="select password from customer where username='"+username+"'";
			String output="";
			//System.out.println(username);
			try{
				rs=stmt.executeQuery(sqlchoose);
				while (rs.next())
				{
					output=rs.getString("password");
				}
				rs.close();
				}catch(Exception e) {
				//System.out.println("cannot execute the query");
			}
			System.out.println("executing "+sqlchoose);
			try{
				stmt.executeQuery(sqlchoose);
			}catch(Exception e)	{
				//System.out.println("cannot execute the query");
			}
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
}

