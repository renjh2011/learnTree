package org.example.binary;

import org.example.avl.AvlTree;
import org.junit.Test;

public class AVLTreeTest {
    @Test
    public void avlTreeTest(){
        AvlTree avlTree = new AvlTree();
//        int data[] = {1,2,3,4,5};
        int data[] = {6,2,7,0,4,3};
//        int data[] = {6,2,3};
        for (int i = 0; i < data.length; i++) {
            avlTree.insert(data[i]);
        }

        int bf = avlTree.calcNodeBalanceValue(avlTree.getRoot());
        System.out.println(bf);
    }
}
