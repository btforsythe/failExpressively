package failExpressively;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

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

        final Money expectedTotal = lineItemsTotal.reducedBy(twentyDollars);

        assertTrue(underTest.totalCost().equals(expectedTotal));
    }
}
