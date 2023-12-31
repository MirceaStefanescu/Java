package maps;

import java.util.HashMap;
import java.util.Map;

import onlineshop.enteties.Product;
import onlineshop.enteties.User;
import onlineshop.enteties.impl.DefaultProduct;
import onlineshop.enteties.impl.DefaultUser;
import onlineshop.enteties.impl.UserForHashTables;

public class MapDemo {

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(2, "two");
		map.put(1, "one");
		map.put(3, "three");
		map.put(4, null);

		System.out.println("Second map element: " + map.get(1));

		System.out.println("\nIterating map keys");
		for (Integer key : map.keySet()) {
			System.out.println(key);
		}

		System.out.println("\nIterating map entries");
		for (Map.Entry<Integer, String> entry : map.entrySet())
			System.out.println("Key: " + entry.getValue() + " - " + "Value: " + entry.getValue());

		// ====== getOrDefault demo
		System.out.println("\ngetOrDefault method demo with key 4: " + map.getOrDefault(4, "default"));
		System.out.println("\ngetOrDefault method demo with key 5: " + map.getOrDefault(5, "default"));

		// ====== putIfAbsent demo
		map.putIfAbsent(4, "four");
		System.out.println("After putIfAbsent method was called for key 4: " + map.get(4));

		// ======= Hash tables demo
		Map<User, Product> userProductMap = new HashMap<User, Product>();
		User user = new DefaultUser(1, "John", "Smith", "password", "john.smith@email.com");
		Product product = new DefaultProduct(1, "product name 1", "product category 1", 99.99);

		userProductMap.put(user, product);

		User userCopy = new DefaultUser(1, "John", "Smith", "password", "john.smith@email.com");

		System.out.println("Get product by user from map: " + userProductMap.get(userCopy));

		System.out.println("user hashCode(): " + user.hashCode());
		System.out.println("userCopy hashCode(): " + userCopy.hashCode());

		User userForHashTable = new UserForHashTables(1, "John", "Smith", "password", "john.smith@email.com");
		userProductMap.put(userForHashTable, product);

		User copyOfUserForHashTable = new UserForHashTables(1, "John", "Smith", "password", "john.smith@email.com");

		System.out.println("Get product by user with hashCode() " + "and equals() overridden from map: "
				+ userProductMap.get(copyOfUserForHashTable));

		copyOfUserForHashTable.setEmail("john.smith@newdomain.com");
		System.out.println("Get product form map when user is changed: " + userProductMap.get(copyOfUserForHashTable));
	}

}
