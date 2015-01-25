package failExpressively;

public class Invoice {

    /**
     * Used to make test fail.
     */
    private static final Money FAILURE_OFFSET = new Money(10);

    private final Money lineItemsTotal;

    private Money discounts = new Money(0);

    public Invoice(Money lineItemsTotal) {
        this.lineItemsTotal = lineItemsTotal;
    }

    public void applyDiscount(Money discount) {
        this.discounts = discounts.increasedBy(discount);
    }

    public Money totalCost() {
        return lineItemsTotal.reducedBy(discounts).increasedBy(FAILURE_OFFSET);
    }

}
