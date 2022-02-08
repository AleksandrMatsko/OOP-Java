


public class Pair<T_1,T_2> {
    private T_1 key;
    private T_2 value;

    public Pair(T_1 element_1, T_2 element_2) {
        this.key = element_1;
        this.value = element_2;
    }

    public T_1 getKey() {
        return this.key;
    }

    public T_2 getValue() {
        return this.value;
    }

    public void setPair(T_1 element_1, T_2 element_2) {
        this.key = element_1;
        this.value = element_2;
    }

    public void setKey(T_1 element_1) {
        this.key = element_1;
    }

    public void setValue(T_2 element_2) {
        this.value = element_2;
    }
}
