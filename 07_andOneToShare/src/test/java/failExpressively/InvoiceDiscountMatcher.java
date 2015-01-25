package failExpressively;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class InvoiceDiscountMatcher extends TypeSafeMatcher<Invoice> {

    private final Money discount;
    private final Money lineItemsTotal;
    private Money actualCost;

    public InvoiceDiscountMatcher(Money lineItemsTotal, Money discount) {
        this.discount = discount;
        this.lineItemsTotal = lineItemsTotal;
    }

    @Override
    public void describeTo(Description description) {
        Money expectedTotal = lineItemsTotal.reducedBy(discount);
        description.appendText(String.format(
            "total invoice cost of %s (a reduction of %s), but was %s (a reduction of %s)", expectedTotal,
            discount, actualCost, lineItemsTotal.reducedBy(actualCost)));
    }

    @Override
    protected boolean matchesSafely(Invoice item) {
        this.actualCost = item.totalCost();
        return false;
    }
}