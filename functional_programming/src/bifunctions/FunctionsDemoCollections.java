package bifunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class FunctionsDemoCollections {

	private static final double DISCOUNT_RATE = 0.05;

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "one");
		map.put(2, "two");
		map.put(3, "three");
		map.put(4, "four");

		System.out.println("========== Function type demo ==========");
		System.out.println("map.get(): " + map.get(5));
		map.computeIfAbsent(5, key -> Integer.toString(key));
		System.out.println("map.get(): " + map.get(5));

		map.computeIfAbsent(5, FunctionsDemoCollections::convertIntToString);
		System.out.println("map.get(): " + map.get(5));

		System.out.println("========== BiFunction type demo ==========");
		map.computeIfPresent(3, (key, value) -> key + " : " + value);
		System.out.println("map.get(): " + map.get(3));
		map.computeIfPresent(2, FunctionsDemoCollections::combineIntAndString);
		System.out.println("map.get(): " + map.get(2));

		System.out.println("========== Compose Functions type demo ==========");
		Map<Product, Double> productDiscountMap = new HashMap<Product, Double>();
		List<Product> products = new ArrayList<Product>(
				Arrays.asList(new Product(1, 199.99), new Product(2, 79.99), new Product(3, 49.99)));

		for (Product product : products) {
			Function<Product, Double> getPriceFunction = prod -> prod.getPrice();
			Function<Double, Double> getDiscountFunction = price -> price * DISCOUNT_RATE;
			Function<Product, Double> getPriceAndThenDiscountFunction = getPriceFunction.andThen(getDiscountFunction);
			Function<Product, Double> getPriceAndThenDicountFunction2 = getDiscountFunction.compose(getPriceFunction);

			productDiscountMap.computeIfAbsent(product, getPriceAndThenDiscountFunction);
		}

		System.out.println(productDiscountMap);

		System.out.println("========== Comparator.comparing() demo ==========");
		products.sort(Comparator.comparing(Product::getPrice));
		products.sort(Comparator.comparing(product -> product.getPrice()));
		System.out.println(products);
	}

	public static String convertIntToString(Integer integer) {
		return Integer.toString(integer);
	}

	private static String combineIntAndString(Integer integer, String string) {
		return integer + " : " + string;
	}

}

class Product {
	private int id;
	private double price;

	public Product(int id, double price) {
		this.id = id;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", price=" + price + "]";
	}
}