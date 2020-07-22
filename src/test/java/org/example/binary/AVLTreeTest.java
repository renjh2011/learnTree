package org.example.binary;

import org.example.avl.AVLTree;
import org.example.avl.AvlTreeBak;
import org.junit.Test;

public class AVLTreeTest {
    @Test
    public void testRR(){
        AvlTree avlTree = new AvlTree();
        int data[] = {1,2,3,4,5};
        for (int i = 0; i < data.length; i++) {
            avlTree.insert(data[i]);
        }

        System.out.println();
    }

    @Test
    public void testLL(){
        AvlTree avlTree = new AvlTree();
        int data[] = {5,4,3,2,1};
//        int data[] = {6,2,7,0,4,3};
//        int data[] = {6,2,3};
        for (int i = 0; i < data.length; i++) {
            avlTree.insert(data[i]);
        }

        int bf = avlTree.calcNodeBalanceValue(avlTree.getRoot());
        System.out.println(bf);
    }

    @Test
    public void testLR(){
        AvlTree avlTree = new AvlTree();
        int data[] = {6,2,7,0,4,3};
        for (int i = 0; i < data.length; i++) {
            avlTree.insert(data[i]);
        }

        int bf = avlTree.calcNodeBalanceValue(avlTree.getRoot());
        System.out.println(bf);
    }

    @Test
    public void testLR1(){
        AvlTree avlTree = new AvlTree();
        int data[] = {6,2,3};
        for (int i = 0; i < data.length; i++) {
            avlTree.insert(data[i]);
        }

        int bf = avlTree.calcNodeBalanceValue(avlTree.getRoot());
        System.out.println(bf);
    }
}
