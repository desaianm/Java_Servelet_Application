package item_manager;

public class Item {
	private int iid;
	private String itemName;
	private int qty;
	private int uid;
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(int iid, String itemName, int iqty, int uid) {
		super();
		this.iid = iid;
		this.itemName = itemName;
		this.qty = iqty;
		this.uid = uid;
	}
	public Item(String itemName, int iqty, int uid, int iid) {
		super();
		this.itemName = itemName;
		this.qty = iqty;
		this.uid = uid;
		this.iid = iid;
		
	}
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int iqty) {
		this.qty = iqty;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	
}
