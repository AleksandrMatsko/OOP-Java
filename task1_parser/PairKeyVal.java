public class PairKeyVal<TKey, TVal> {
    private TKey key;
    private TVal value;

    public PairKeyVal(TKey key, TVal val) {
        this.key = key;
        this.value = val;
    }

    public TKey getKey() {
        return this.key;
    }

    public TVal getValue() {
        return this.value;
    }

    public void setPair(TKey key, TVal val) {
        this.key = key;
        this.value = val;
    }

    /*public void setKey(tKey element_1) {
        this.key = element_1;
    }

    public void setValue(tVal element_2) {
        this.value = element_2;
    }*/

}
