package jakarta.tutorial.billpayment.event;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Common payment event that handles Debit and Credit payment types.
 */
public class PaymentEvent implements Serializable {

    private static final long serialVersionUID = -6407967360613478424L;

    public String paymentType;
    public BigDecimal value;
    public Date datetime;

    public PaymentEvent() {
    }

    @Override public String toString() {
        return this.paymentType + " = $" + this.value.toString() + " at " +
               this.datetime.toString();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
