package clasess.classes2;

import java.math.BigDecimal;

public abstract class Product {

	private int id;
	private String name;
	private int minOrderQuantity;
	private boolean isDeliveryAvailable;
	private BigDecimal price;

	public abstract boolean isAvailableInStock();

	public int getRemainigAmountInStock() {
		return 100;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinOrderQuantity() {
		return minOrderQuantity;
	}

	public void setMinOrderQuantity(int minOrderQuantity) {
		this.minOrderQuantity = minOrderQuantity;
	}

	public boolean isDeliveryAvailable() {
		return isDeliveryAvailable;
	}

	public void setDeliveryAvailable(boolean isDeliveryAvailable) {
		this.isDeliveryAvailable = isDeliveryAvailable;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
