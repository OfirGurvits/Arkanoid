// id: 325655058 author: Ofir Gurvits

/**
 * The type Counter.
 */
public class Counter {
    /**
     * The Value.
     */
    private int value = 0;

    /**
     * Instantiates a new Counter.
     *
     * @param value the value
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Increase the count by the given number.
     * @param number the number
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Decrease the count by the given number.
     * @param number the number
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * Get value int.
     * @return the int
     */
    public int getValue() {
        return this.value;
    }
}