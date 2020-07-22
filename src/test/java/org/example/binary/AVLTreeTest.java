package org.example.binary;

import org.example.avl.AVLTree;
import org.example.avl.AvlTreeBak;
import org.junit.Test;

public class AVLTreeTest {
    @Test
    public void avlTreeTest(){
        AVLTree avlTree = new AVLTree();
//        int data[] = {1,2,3,4,5};
        int data[] = {6,2,7,0,4,3};
//        int data[] = {6,2,3};
        for (int i = 0; i < data.length; i++) {
            avlTree.insert(data[i]);
        }

        System.out.println();
    }
}
