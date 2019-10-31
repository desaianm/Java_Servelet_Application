package item_manager;

import java.sql.*;   
import java.util.ArrayList;
import item_manager.Home;

public class DB_Access implements DB_Vars {
	private static Connection con;
	private static Statement st;
	private PreparedStatement pst;
	
	public DB_Access() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, uname, upass);
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int checkUserLogin(String uname, String upass) {
		int uid = -1; // -1 means the login was not successfull
		
		String sql = " select uid from t_users "+
						"where username = '"+uname+"' "+
						"and loginpass = '"+upass+"'";
		
		try {
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				uid = rs.getInt(1);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return uid;
	}
	
	public String getUserName(int uid) {
		String sql = " select loginname from t_users where uid = " + uid;
		String name = "";
		
		try {
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return name;
	}
	
	public ArrayList<Item> getAllUserItems(int uid) {
		ArrayList<Item> all = new ArrayList<Item>();
		
		String sql = "select iid, itemname, qty, uid from t_items " +
					"where uid = " + uid;

		ResultSet rs;
		try {
			rs = st.executeQuery(sql);
			while(rs.next()) {
				all.add(new Item(rs.getInt(1), 
								rs.getString(2), 
								rs.getInt(3), 
								rs.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return all;
	}
	
	public boolean insertItem(Item i) {
		boolean success = true;
		
		String sql = "insert into t_items(itemname, qty, uid) values(?,?,?)";
		
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, i.getItemName());
			pst.setInt(2, i.getQty());
			pst.setInt(3, i.getUid());
			if(pst.executeUpdate() == 0) success = false;
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}
		
		
		return success;
	}
	
	public boolean ModifyItem(Item i) {
		boolean success = true;
		
		st =null;
		try {
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			String sql = " select iid,uid,itemname,qty from t_items where iid ="+i.getIid();


			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
			rs.updateString(3, i.getItemName());
			rs.updateInt(4, i.getQty());
			rs.updateRow();
			}
			
			
		} catch (SQLException  e1) {
			success = false;
			e1.printStackTrace();
		}
		
		
		
		return success;
	}
	
	
	public static Item ViewItems(int iid) {
		
		ResultSet rs;
		con = null;
		
		String sql = " select iid, itemname, qty, uid from t_items " +
					" where iid  = "+iid;
		
		 Item i=new Item();  
         
	        try{    
	        	rs = st.executeQuery(sql);
	    		
	            if(rs.next()){  
	                i.setIid(rs.getInt(1));  
	                i.setItemName(rs.getString(2));  
	                i.setQty(rs.getInt(3));  
	                
	            }  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	        return i;  
		
	}
	
	public boolean insertUser(User u) {
		boolean success = true;
		
		String sql = " insert into t_users(username,LoginPass,Loginname) values(?,?,?)";
		
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, u.getUsername());
			pst.setString(2, u.getPassword());
			pst.setString(3, u.getLoginname());
			
			if(pst.executeUpdate() == 0) {
				success = false;
			}
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}
		
		
		return success;
	}
	
	public boolean ModifyAccount(User u) {
		boolean success = true;
		
		st =null;
		try {
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			String sql = " select * from t_users where uid ="+u.getUid();
			
			
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
			rs.updateString(2,u.getLoginname());
			rs.updateString(3,u.getUsername());
			rs.updateString(4,u.getPassword());
			rs.updateRow();
			}
			
			
		} catch (SQLException  e1) {
			success = false;
			e1.printStackTrace();
		}
		
		
		
		return success;
	}
	public boolean DeleteItem(int iid) {
		boolean success = true;
		Item i = new Item();
		st =null;
		try {
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			i.setIid(iid);
			String sql = " delete  from t_items where iid ="+i.getIid();
			
			

			int res = st.executeUpdate(sql);
			
			
			
		} catch (SQLException  e1) {
			success = false;
			e1.printStackTrace();
		}
		
		
		
		return success;
	}

	
	

	
	
	
	
}

	
	
	











