package shoes.entity;

public class Shoe {
	private long shoeId;
	private String shoeName;
	
	public Shoe(long shoeId, String shoeName) {
		this.shoeId = shoeId;
		this.shoeName = shoeName;
	}

	public String getShoeName() {
		return shoeName;
	}

	public long getShoeId() {
		return shoeId;
	}
}
