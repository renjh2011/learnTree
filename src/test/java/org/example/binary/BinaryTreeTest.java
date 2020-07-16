package org.example.binary;

import org.junit.Test;

public class BinaryTreeTest {
    @Test
    public void testAdd(){
        BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
        Integer[] datas = {4,5,2,8,6,3,9};
        for(int i=0;i<datas.length;i++){
            binaryTree.insert(datas[i]);
        }

        binaryTree.postOrder();
    }
}
