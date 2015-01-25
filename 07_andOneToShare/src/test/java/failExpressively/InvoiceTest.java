package failExpressively;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matcher;
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

        // When is it better to create a standalone matcher?
    }

    /**
     * Now, these don't need to be final. Why?
     * 
     */
    private Matcher<Invoice> hasDiscountedCost(Money lineItemsTotal, Money discount) {

        /**
         * First create one anonymously, then use two automated Eclipse refactorings:
         * -- anonymous class to (static) nested
         * -- move type to new file
         */
        return new InvoiceDiscountMatcher(lineItemsTotal, discount);
    }
}
