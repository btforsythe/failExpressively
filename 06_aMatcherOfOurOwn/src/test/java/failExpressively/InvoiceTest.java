package failExpressively;

import static org.junit.Assert.assertThat;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

/**
 * expected the total order cost to be $42.00 (a reduction of $20.00), but it
 * was $52.00 (a reduction of $10.00)
 */
public class InvoiceTest {

    private Invoice underTest;

    private final Money lineItemsTotal = new Money(62);

    private final Money twentyDollars = new Money(20);

    @Before
    public void setup() {
        underTest = new Invoice(lineItemsTotal);
    }

    @Test
    public void shouldApplyDiscount() {

        underTest.applyDiscount(twentyDollars);

        assertThat(underTest, hasDiscountedCost(lineItemsTotal, twentyDollars));
    }

    /**
     * Yes, these need to be final. Why?
     */
    private Matcher<Invoice> hasDiscountedCost(final Money lineItemsTotal, final Money discount) {

        final Money expectedTotal = lineItemsTotal.reducedBy(twentyDollars);
        /**
         * We could definitely benefit by creating some code for cleaner matchers
         */
        return new TypeSafeMatcher<Invoice>() {

            private Money actualCost;

            @Override
            public void describeTo(Description description) {
                description.appendText(String.format(
                    "total invoice cost of %s (a reduction of %s), but was %s (a reduction of %s)", expectedTotal,
                    discount, actualCost, lineItemsTotal.reducedBy(actualCost)));
            }

            @Override
            protected boolean matchesSafely(Invoice item) {
                this.actualCost = item.totalCost();
                return false;
            }
        };
    }
}
