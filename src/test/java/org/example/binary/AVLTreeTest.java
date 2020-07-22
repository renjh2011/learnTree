package org.example.binary;

import org.example.avl.AVLTree;
import org.example.avl.AvlTreeBak;
import org.junit.Test;

public class AVLTreeTest {
    @Test
    public void testRR(){
        AVLTree avlTree = new AVLTree();
        int data[] = {1,2,3,4,5};
        for (int i = 0; i < data.length; i++) {
            avlTree.insert(data[i]);
        }

        System.out.println();
    }

    @Test
    public void testLL(){
        AVLTree avlTree = new AVLTree();
        int data[] = {5,4,3};
        for (int i = 0; i < data.length; i++) {
            avlTree.insert(data[i]);
        }

        System.out.println();
    }

    @Test
    public void testLR(){
        AVLTree avlTree = new AVLTree();
        int data[] = {6,4,5};
        for (int i = 0; i < data.length; i++) {
            avlTree.insert(data[i]);
        }

        System.out.println();
    }

    @Test
    public void testRL(){
        AVLTree avlTree = new AVLTree();
        int data[] = {4,6,5};
        for (int i = 0; i < data.length; i++) {
            avlTree.insert(data[i]);
        }

        System.out.println();
    }

    @Test
    public void testLR1(){
    }
}
