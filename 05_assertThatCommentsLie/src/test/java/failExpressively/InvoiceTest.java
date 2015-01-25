package failExpressively;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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

        Money expectedTotal = lineItemsTotal.reducedBy(twentyDollars);

        // if you do this, you're asking the code to lie
        assertThat("the total should be $32", underTest.totalCost(), is(expectedTotal));
    }
}
