package org.example.avl;

public class AVLTree {
    private AVLNode root;
    private final int LEFT = 1;
    private final int RIGHT = -1;
    private final int MAX_LEFT = 2;
    private final int MAX_RIGHT = -2;

    public AVLNode getRoot() {
        return root;
    }

    public void insert(int data){
        insert(root,data);
    }

    public void insert(AVLNode node,int data){
        if(node==null){
            node = new AVLNode(data);
            root = node;
            return;
        }
        AVLNode p = null;
        AVLNode tmp = node;
        int t = 0;
        while (tmp!=null){
            p = tmp;
            t = tmp.data-data;
            if(t>0){
                tmp = tmp.leftChild;
            }else if(t<0){
                tmp = tmp.rightChild;
            }else {
                //exception
                return;
            }
        }

        AVLNode newNode = new AVLNode(p, data);
        if(t < 0) {
            p.rightChild = newNode;
        } else if(t > 0) {
            p.leftChild = newNode;
        }
        rebuild(p);
    }

    private void rebuild(AVLNode p) {
        while (p!=null){
            //LL / LR
            if(calcBF(p)==MAX_LEFT){
                AVLNode node = p.leftChild;
                if(calcBF(node)==RIGHT){
                    RR(node);
                    LL(p);
                }else if(calcBF(node)==LEFT){
                    LL(p);
                }
            }else if(calcBF(p)==MAX_RIGHT){
                AVLNode node = p.rightChild;
                if(calcBF(node)==RIGHT){
                    RR(p);
                }else if(calcBF(node)==LEFT){
                    LL(node);
                    RR(p);
                }
                /*if(node.rightChild==newNode || node.rightChild.leftChild==newNode || node.rightChild.rightChild==newNode){
                    LL(node.rightChild);
                    RR(node);
                }else if(node.leftChild==newNode || node.leftChild.leftChild==newNode || node.leftChild.rightChild==newNode){
                    RR(node);
                }*/
            }
            p = p.parent;
        }
    }


    private void RR(AVLNode node) {
        AVLNode rightChild = node.rightChild;

        //处理rightChild的leftChild与node的关系
        node.rightChild = rightChild.leftChild;
        if(rightChild.leftChild!=null){
            rightChild.leftChild.parent = node;
        }
        //设置node的parent节点与rightChild的关系
        rightChild.parent = node.parent;
        if(node.parent==null){
            this.root = rightChild;
        }else if(node.parent.leftChild == node){
            node.parent.leftChild = rightChild;
        }else if(node.parent.rightChild == node){
            node.parent.rightChild = rightChild;
        }
        //设置node与rightChild的关系
        rightChild.leftChild = node;
        node.parent = rightChild;
    }

    private void LL(AVLNode node) {
        AVLNode leftChild = node.leftChild;
        node.leftChild = leftChild.rightChild;

        if(leftChild.rightChild!=null){
            leftChild.rightChild.parent = node;
        }
        leftChild.parent = node.parent;
        if(node.parent==null){
            this.root = leftChild;
        }else if(node.parent.leftChild == node){
            node.parent.leftChild = leftChild;
        }else if(node.parent.rightChild == node){
            node.parent.rightChild = leftChild;
        }
        leftChild.rightChild = node;
        node.parent = leftChild;
    }

    private int calcBF(AVLNode p) {
        int bf = getDepth(p.leftChild)-getDepth(p.rightChild);
        return bf;
    }

    private int getDepth(AVLNode child) {
        if(child==null){
            return 0;
        }
        return 1+Math.max(getDepth(child.leftChild),getDepth(child.rightChild));
    }

    /**
     * 删除指定val值的节点
     * @param val
     * @return
     */
    public boolean delete(int val) {
        AVLNode node = getNode(val);
        if(node == null) {
            return false;
        }
        boolean flag = false;
        AVLNode p = null;
        AVLNode parent = node.parent;
        AVLNode leftChild = node.leftChild;
        AVLNode rightChild = node.rightChild;
        if(leftChild == null && rightChild == null) {
            if(parent != null) {
                if(parent.leftChild == node) {
                    parent.leftChild = null;
                }
                else if(parent.rightChild == node) {
                    parent.rightChild = null;
                }
            }
            else {
                this.root = null;
            }

            p = parent;
            node = null;
            flag = true;
        }
        else if(leftChild == null && rightChild != null) {
            if(parent != null && parent.data > val) {
                parent.leftChild = rightChild;
            }
            else if(parent != null && parent.data < val) {
                parent.rightChild = rightChild;
            }
            else {
                this.root = rightChild;
            }
            p = parent;
            node = null;
            flag = true;
        }
        else if(leftChild != null && rightChild == null) {
            if(parent != null &&  parent.data > val) {
                parent.leftChild = leftChild;
            }
            else if(parent != null && parent.data < val) {
                parent.rightChild = leftChild;
            }
            else {
                this.root = leftChild;
            }

            p = parent;
            node = null;
            flag = true;
        }
        else if(leftChild != null && rightChild != null) {
            AVLNode successor = getSuccessor(node);
            int tempData = successor.data;
            if(delete(tempData)) {
                node.data = tempData;
            }
            p = successor;
            successor = null;
            flag = true;
        }

        if(flag) {
            this.rebuild(p);
        }
        return flag;
    }
    /**
     * 获得指定节点
     * @param key
     * @return
     */
    public AVLNode getNode(int key) {

        AVLNode node = root;
        int t;
        do {
            t = node.data - key;
            if(t > 0) {
                node = node.leftChild;
            }
            else if(t < 0) {
                node = node.rightChild;
            }
            else {
                return node;
            }
        } while(node != null);
        return null;
    }

    /***
     * 获得指定节点的后继
     * 找到node节点的后继节点
     * 1、先判断该节点有没有右子树，如果有，则从右节点的左子树中寻找后继节点，没有则进行下一步
     * 2、查找该节点的父节点，若该父节点的右节点等于该节点，则继续寻找父节点，
     *   直至父节点为Null或找到不等于该节点的右节点。
     * 理由，后继节点一定比该节点大，若存在右子树，则后继节点一定存在右子树中，这是第一步的理由
     *      若不存在右子树，则也可能存在该节点的某个祖父节点(即该节点的父节点，或更上层父节点)的右子树中，
     *      对其迭代查找，若有，则返回该节点，没有则返回null
     * @param node
     * @return
     */
    public AVLNode getSuccessor(AVLNode node) {
        if(node.rightChild != null) {
            AVLNode rightChild=  node.rightChild;
            while(rightChild.leftChild != null) {
                rightChild = rightChild.leftChild;
            }
            return rightChild;
        }
        AVLNode parent = node.parent;
        while(parent != null && (node == parent.rightChild)) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }
}
