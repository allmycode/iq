package sl;

import java.util.*;

public class BST<E> implements Iterable<E> {
    BST<E> parent;
    BST<E> left;
    BST<E> right;

    E key;

    public BST(E key) {
        this.key = key;
    }

    public BST(E key, BST<E> parent) {
        this(key);
        this.parent = parent;
    }

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
    
    public static void main(String[] args) {
        BST<Integer> r = node(700, node(350, node(300), node(400)), node(800));
        r.printInOrder();
        System.out.println("---- iterator ----");
        for (Integer i : r) {
            print(i);
        }

        System.out.println("----");
        BST<Integer> r2 = node(1000, 
                               node(900, node(800, node(700), null), node(950)), 
                               node(1100, 
                                    node(1090, node(1080, null, node(1081, null, node(1082))), null), 
                                    node(1200, null, node(1300))));
        r2.printInOrder();

        System.out.println("---- iterator ----");
        for (Integer i : r2) {
            print(i);
        }

    }

    private static void print(Object o) {
        System.out.println(o);
    }

    public void printInOrder() {
        BST<E> prev = null;
        BST<E> cur = this;
        while (cur != null) {
            BST<E> pprev = cur;
            if (prev == cur.parent && cur.left != null) {
                prev = cur;   
                cur = cur.left;
                continue;
            }

            if (prev == cur.parent || prev == cur.left) {
                print(cur.key);
            }

            cur = ((prev == cur.parent && cur.left == null) || prev == cur.left) && cur.right != null ?
                cur.right : cur.parent;

            prev = pprev;
        }
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            BST<E> next;
            BST<E> prev = null;
            BST<E> cur = BST.this;

            private BST<E> move() {
                while (cur != null) {
                    BST<E> pprev = cur;
                    if (prev == cur.parent && cur.left != null) {
                        prev = cur;   
                        cur = cur.left;
                        continue;
                    }
                    
                    if (prev == cur.parent || prev == cur.left) {
                        next = cur;
                    }
                    
                    cur = ((prev == cur.parent && cur.left == null) || prev == cur.left) && cur.right != null ?
                        cur.right : cur.parent;
                    
                    prev = pprev;
                    
                    if (next != null) return next;
                }
                return null;
            }

            public boolean hasNext() {
                return next != null ? true : move() != null;
            }

            public E next() {
                if (next == null)
                    move();
                
                if (next != null) {
                    E k = next.key;
                    next = null;
                    return k;
                }

                throw new RuntimeException();
            }

            public void remove() {
            }
        };
    }

    public String toString() {
        return "N(" + key + ")";
    } 

 }
