package sl;

import org.junit.*;
import java.util.*;
import static org.hamcrest.Matchers.*;
import static java.util.Arrays.asList;

public class BSTTest {
   
    private static BST<Integer> node(int key) {
        return new BST<>(key);
    }

    private static BST<Integer> node(int key, BST<Integer> left, BST<Integer> right) {
        BST<Integer> n = new BST<>(key);
        if (left != null) 
            left.parent = n;
        if (right != null) 
            right.parent = n;
        n.left = left;
        n.right = right;
        return n;
    }

    private <E> List<E> fromInOrder(BST<E> bst) {
        List<E> result = new ArrayList<>();
        for (E e: bst)
            result.add(e);
        return result;
    }

    @Test
    public void oneNode() {
        Assert.assertThat(fromInOrder(node(700)), is(asList(700)));
    }

    @Test
    public void twoNode1() {
        Assert.assertThat(fromInOrder(node(700, node(600), null)), is(asList(600, 700)));
    }

    @Test
    public void twoNode2() {
        Assert.assertThat(fromInOrder(node(700, null, node(800))), is(asList(700, 800)));
    }
}
