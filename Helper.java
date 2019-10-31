package item_manager;

public class Helper {
	public static boolean isValueValid(String val) {
		boolean isValid = true;
		
		if(val.trim().equalsIgnoreCase("")) isValid = false;
		if(val.trim().length() < 3) isValid = false;
		
		return isValid;
	}
	public static boolean isItemNameValid(String val) {
		boolean isValid = true;
		if(val.trim().equalsIgnoreCase("")) isValid = false;
		return isValid;
	}
	
	public static boolean isItemQtyValid(String val) {
		boolean isValid = true;
		try {
			Integer.parseInt(val);
		}
		catch(Exception e) {
			isValid = false;
		}
		return isValid;
	}
	
	public static int validateNamePass(String uname, String upass) {		
		DB_Access db = new DB_Access();
		int uid = db.checkUserLogin(uname, upass);
		
		return uid;
	}




}







