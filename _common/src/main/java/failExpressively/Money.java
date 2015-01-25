package failExpressively;

/**
 * TODO we need a better name
 * 
 * Also, equals/hashcode aren't implemented. Tests could never pass.
 */
public class Money {

    /**
     * If this were real, it would be something more complex than an int.
     */
    private final int value;

    public Money(int value) {
        this.value = value;

    }

    public int value() {
        return value;
    }

    /**
     * TODO We need a better verb.
     */
    public Money reducedBy(Money amount) {
        return new Money(value - amount.value);
    }

    /**
     * TODO We need a better verb.
     */
    public Money increasedBy(Money amount) {
        return new Money(value + amount.value);
    }

    @Override
    public String toString() {
        return "$" + value;
    }

}
