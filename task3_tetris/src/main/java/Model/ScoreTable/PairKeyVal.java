package Model.ScoreTable;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PairKeyVal<?, ?> pair = (PairKeyVal<?, ?>) o;
        return Objects.equals(key, pair.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    public void setValue(TVal val) {
        this.value = val;
    }

}
