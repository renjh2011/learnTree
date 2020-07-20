package org.example.avl;

public class AVLNode {

    public AVLNode parent;
    public AVLNode leftChild, rightChild;
    public int data;

    public AVLNode(AVLNode parent, AVLNode leftChild, AVLNode rightChild, int data) {
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.data = data;
    }

    public AVLNode(int data) {
        this(null, null, null, data);
    }

    public AVLNode(AVLNode parent, int data) {
        this.parent = parent;
        this.data = data;
    }

    public AVLNode getParent() {
        return parent;
    }

    public void setParent(AVLNode parent) {
        this.parent = parent;
    }

    public AVLNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(AVLNode leftChild) {
        this.leftChild = leftChild;
    }

    public AVLNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(AVLNode rightChild) {
        this.rightChild = rightChild;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}