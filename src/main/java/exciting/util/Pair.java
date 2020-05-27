package exciting.util;

import java.io.Serializable;

/**
 * Pair contains a key and a value.
 *
 * @author Tsz Shing Tsoi
 * @version 1.1
 * @param <K> a generic element
 * @param <V> a generic element
 */
public class Pair<K, V> implements Serializable, Cloneable {

    /**
     * Construct a Pair object.
     *
     * @param k a key
     * @param v a value
     * @precondition k != null && K implements Serializable && K is immutable
     * @precondition v != null && V implements Serializable && V is immutable
     */
    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    /**
     * Return the key.
     *
     * @return the key
     */
    public K k() {
        return k;
    }

    /**
     * Return the value.
     *
     * @return the value
     */
    public V v() {
        return v;
    }

    /**
     * Test whether two objects are equal.
     *
     * @param other another object
     * @return true if two objects are equal in key and value, and false
     * otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        } else {
            return k.equals(((Pair) other).k) && v.equals(((Pair) other).v);
        }
    }

    /**
     * Return the hashcode of the Pair object.
     *
     * @return the hashcode of the Pair object
     */
    @Override
    public int hashCode() {
        return 7 * k.hashCode() + 13 * v.hashCode();
    }

    /**
     * Convert the Pair object to a string representation.
     *
     * @return the string representation of the Pair object
     */
    @Override
    public String toString() {
        return getClass().getName()
                + "[key=" + k + ",value=" + v + "]";
    }

    /**
     * Clone the Pair object (shallow copy).
     *
     * @return a shallow clone of the Pair object
     * @throws CloneNotSupportedException if clone is not supported
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // instance variables
    private final K k;
    private final V v;

}
