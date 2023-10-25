package jakarta.tutorial.dukesbookstore.web.managedbeans;

import java.util.Calendar;
import java.util.Date;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIOutput;
import jakarta.faces.component.UISelectBoolean;
import jakarta.faces.model.SelectItem;
import jakarta.inject.Named;
import jakarta.tutorial.dukesbookstore.ejb.BookRequestBean;
import jakarta.tutorial.dukesbookstore.exception.OrderException;

/**
 * <p>Backing bean for the <code>/bookcashier.xhtml</code> and
 * <code>bookreceipt.xhtml</code> pages.</p>
 */
@Named @RequestScoped public class CashierBean extends AbstractBean {

    private static final long serialVersionUID = -9221440716172304017L;
    private static final SelectItem[] newsletterItems = {new SelectItem("Duke's Quarterly"),
            new SelectItem("Innovator's Almanac"), new SelectItem(
            "Duke's Diet and Exercise Journal"), new SelectItem("Random Ramblings")};
    @EJB BookRequestBean bookRequestBean;
    UIOutput specialOfferText = null;
    UISelectBoolean specialOffer = null;
    UIOutput thankYou = null;
    private String name = null;
    private String creditCardNumber = null;
    private Date shipDate;
    private String shippingOption = "2";
    private String stringProperty = "This is a String property";
    private String[] newsletters;

    public CashierBean() {
        this.newsletters = new String[0];
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String[] getNewsletters() {
        return this.newsletters;
    }

    public void setNewsletters(String[] newsletters) {
        this.newsletters = newsletters;
    }

    public SelectItem[] getNewsletterItems() {
        return newsletterItems;
    }

    public Date getShipDate() {
        return this.shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public String getShippingOption() {
        return this.shippingOption;
    }

    public void setShippingOption(String shippingOption) {
        this.shippingOption = shippingOption;
    }

    public UIOutput getSpecialOfferText() {
        return this.specialOfferText;
    }

    public void setSpecialOfferText(UIOutput specialOfferText) {
        this.specialOfferText = specialOfferText;
    }

    public UISelectBoolean getSpecialOffer() {
        return this.specialOffer;
    }

    public void setSpecialOffer(UISelectBoolean specialOffer) {
        this.specialOffer = specialOffer;
    }

    public UIOutput getThankYou() {
        return this.thankYou;
    }

    public void setThankYou(UIOutput thankYou) {
        this.thankYou = thankYou;
    }

    public String getStringProperty() {
        return (this.stringProperty);
    }

    public void setStringProperty(String stringProperty) {
        this.stringProperty = stringProperty;
    }

    public String submit() {
        // Calculate and save the ship date
        int days = Integer.valueOf(shippingOption).intValue();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, days);
        setShipDate(cal.getTime());

        if ((cart.getTotal() > 100.00) && !specialOffer.isRendered()) {
            specialOfferText.setRendered(true);
            specialOffer.setRendered(true);

            return null;
        } else if (specialOffer.isRendered() && !thankYou.isRendered()) {
            thankYou.setRendered(true);

            return null;
        } else {
            try {
                bookRequestBean.updateInventory(cart);
            } catch (OrderException ex) {
                return "bookordererror";
            }

            cart.clear();

            return ("bookreceipt");
        }
    }
}