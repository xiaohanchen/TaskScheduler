package common;

/**
 * @author xiaohan
 * @since 2020/2/27 11:49 PM
 */
public class Pair<L,R > {

    L left;

    R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public Pair<L, R> setLeft(L left) {
        this.left = left;
        return this;
    }

    public R getRight() {
        return right;
    }

    public Pair<L, R> setRight(R right) {
        this.right = right;
        return this;
    }
}
