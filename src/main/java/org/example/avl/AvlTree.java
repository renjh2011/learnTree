package org.example.avl;

public class AvlTree {
    private AVLNode root;
    private final int LEFT = 1;
    private final int RIGHT = -1;
    private final int MAX_LEFT = 2;
    private final int MAX_RIGHT = -2;

    public AVLNode getRoot() {
        return root;
    }

    public void insert(int data) {
        insert(root, data);
    }

    private void insert(AVLNode node, int data) {
        if (node == null) {
            node = new AVLNode(data);
            root = node;
            return;
        }

        int t;
        AVLNode p;
        AVLNode temp = node;
        do {
            p=temp;
            t = temp.data-data;
            if(t<0){
                temp=temp.rightChild;
            }else if(t>0){
                temp=temp.leftChild;
            }else {
                //相同的不需要处理
            }
        } while (temp!=null);
        if(t < 0) {
            p.rightChild = new AVLNode(p, data);
        }
        else if(t > 0) {
            p.leftChild = new AVLNode(p, data);
        }
        rebuild(p);
    }

    /**
     * 平衡二叉树的方法
     * @param node
     */
    public void rebuild(AVLNode node) {
        while(node != null) {
            if(calcNodeBalanceValue(node) == MAX_LEFT) {
                fixAfterInsertion(node, LEFT);
            }
            else if(calcNodeBalanceValue(node) == MAX_RIGHT) {
                fixAfterInsertion(node, RIGHT);
            }
            node = node.parent;
        }
    }

    /**
     * 调整树的结构
     * @param node
     * @param type
     */
    public void fixAfterInsertion(AVLNode node, int type) {
        if(type == LEFT) {
            AVLNode leftChild = node.leftChild;
            if(leftChild.leftChild != null) {  //右旋
                rightRotation(node);
            }
            else if(leftChild.rightChild != null) {   //左右旋
//                leftRotation(leftChild);
                rightRotation(node);
            }
        }
        else if(type == RIGHT) {
            AVLNode rightChild = node.rightChild;
            if(rightChild.rightChild != null) {   //左旋
                leftRotation(node);
            }
            else if(rightChild.leftChild != null) {   //右左旋
                rightRotation(rightChild);
//                leftRotation(node);
            }
        }
    }

    /**
     * 左旋
     * @param node
     * @return
     */
    public void leftRotation(AVLNode node) {
        /*if(node != null) {
            AVLNode pointNode = node.rightChild;
            node.rightChild = pointNode.leftChild;
            if(pointNode.leftChild!=null){
                pointNode.leftChild.parent = node;
            }
            pointNode.parent = node.parent;
            if(node.parent==null){
                this.root = pointNode;
            }else if(node.parent.rightChild==node){
                node.parent.rightChild=pointNode;
            }
            else if(node.parent.leftChild==node){
                node.parent.leftChild=pointNode;
            }
            pointNode.leftChild = node;
            node.parent = pointNode;
        }*/
        if(node != null) {
            AVLNode rightChild = node.rightChild;
            node.rightChild = rightChild.leftChild;
            if(rightChild.leftChild != null) {
                rightChild.leftChild.parent = node;
            }
            rightChild.parent = node.parent;
            if(node.parent == null) {
                this.root = rightChild;
            }
            else if(node.parent.rightChild == node) {
                node.parent.rightChild = rightChild;
            }
            else if(node.parent.leftChild == node) {
                node.parent.leftChild = rightChild;
            }
            rightChild.leftChild = node;
            node.parent = rightChild;
        }
    }
    /**
     * 右旋
     * @param node
     * @return
     */
    public void rightRotation(AVLNode node) {
        if(node != null) {
            AVLNode p = node.parent;
            if(p.parent==null){
                this.root = node;
                node.parent=null;
                node.leftChild = p;
                p.parent = node;
            }else {
                AVLNode pp = p.parent;
                node.leftChild = p;
                p.parent = node;
                pp.rightChild = node;
                node.parent = pp;
            }

            /*AVLNode leftChild = node.leftChild;
            node.leftChild = leftChild.rightChild;
            // 如果leftChild的右节点存在，则需将该右节点的父节点指给node节点
            if(leftChild.rightChild != null) {
                leftChild.rightChild.parent = node;
            }
            leftChild.parent = node.parent;
            if(node.parent == null) {
                this.root = leftChild;
            }
            else if(node.parent.rightChild == node) {  // 即node节点在它原父节点的右子树中
                node.parent.rightChild = leftChild;
            }
            else if(node.parent.leftChild == node) {
                node.parent.leftChild = leftChild;
            }

            leftChild.rightChild = node;
            node.parent = leftChild;
            return leftChild;*/
        }
    }
    /**
     * 计算node节点的BF值
     * @param node
     * @return
     */
    public int calcNodeBalanceValue(AVLNode node) {
        if(node != null) {
            return getHeightByNode(node);
        }
        return 0;
    }

    private int getHeightByNode(AVLNode node) {
        if(node == null) {
            return 0;
        }
        return getChildDepth(node.leftChild) - getChildDepth(node.rightChild);
    }

    private int getChildDepth(AVLNode node) {
        if(node == null) {
            return 0;
        }
        return 1 + Math.max(getChildDepth(node.leftChild), getChildDepth(node.rightChild));
    }
}
