import java.util.Objects;

public class Pair<TKey, TVal> {
    private TKey key;
    private TVal value;

    public Pair(TKey key, TVal val) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(key, pair.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    /*public void setKey(tKey element_1) {
        this.key = element_1;
    }*/

    public void setValue(TVal val) {
        this.value = val;
    }

}
