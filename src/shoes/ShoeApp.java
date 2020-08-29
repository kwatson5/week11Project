package shoes;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import shoes.dao.ShoeDao;
import shoes.entity.Shoe;

public class ShoeApp {
	
	private Scanner scanner = new Scanner(System.in);
	private ShoeDao shoeDao = new ShoeDao();

	public static void main(String[] args) {
		new ShoeApp().run();
	}

	/**
	 * 
	 */
	private void run() {
		while(true) {
			printInstructions();
			
			System.out.println("Enter a selection: ");
			String choice = scanner.nextLine();
			
			if(choice.isBlank()) {
				break;
			}
			
			try {
			switch(choice) {
			case "1":
				createShoe();
				break;
				
			case "2":
				modifyShoe();
				break;
				
			case "3":
				listShoes();
				break;
				
			case "4":
				deleteShoe();
				break;	
				
			default:
				System.out.println("Invalid selection, try again");
			}
		}
			catch(SQLException e) {
				System.out.println("There is an error: " + e.getMessage());
				break;
			}
		}	
	}
	
	private void deleteShoe() throws SQLException {
		listShoes();
		System.out.println("Enter the shoe ID to delete:");
		long id = Long.parseLong(scanner.nextLine());
		
		shoeDao.deleteShoe(id);
		listShoes();
	}

	private void modifyShoe() throws SQLException {
		listShoes();
		System.out.println("Enter the shoe ID to modify:");
		long id = Long.parseLong(scanner.nextLine());
		
		System.out.println("Enter new shoe name");
		String name = scanner.nextLine();
		
		shoeDao.modifyShoe(id, name);
		listShoes();
	}

	private void listShoes() throws SQLException {
		List<Shoe> shoes = shoeDao.getShoes();	
		for (Shoe shoe : shoes) {
			System.out.println(shoe.getShoeId() + ": " + shoe.getShoeName());
		}
	}

	/**
	 * @throws SQLException 
	 * 
	 */
	private void createShoe() throws SQLException {
		System.out.println("Enter a shoe name:");
		String shoeName = scanner.nextLine();
		shoeDao.createShoe(shoeName);
	}

	/**
	 * @throws SQLException
	 */
	private void printInstructions() {
		System.out.println("Choose from the following options:");
		System.out.println("1. Create a Shoe");
		System.out.println("2. Modify a Shoe");
		System.out.println("3. List of Shoes");
		System.out.println("4. Delete a Shoe");
	}

}
