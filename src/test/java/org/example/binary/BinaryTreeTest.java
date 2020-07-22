package org.example.binary;

import org.junit.Test;

public class BinaryTreeTest {
    @Test
    public void testAdd() {
        BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
        Integer[] datas = {4, 7, 2, 8, 5, 6,3, 9};
        for (int i = 0; i < datas.length; i++) {
            binaryTree.insert(datas[i]);
        }

        binaryTree.midOrder();

        binaryTree.delete(6);
        System.out.println("--------------");
        binaryTree.midOrder();
    }

    @Test
    public void testSwap() {
        Node<Integer> nodeA = new Node<Integer>(2);
        Node<Integer> nodeb = new Node<Integer>(5);
        Node<Integer> nodec = new Node<Integer>(7);
        nodeA.setLNode(nodeb);
        nodeb.setRNode(nodec);
//        Node<Integer> tmp = nodeA;
//        nodeA = nodeb;
//        nodeb = tmp;
//        System.out.println(nodeA.getData());
//        System.out.println(nodeb.getData());

    }
}
