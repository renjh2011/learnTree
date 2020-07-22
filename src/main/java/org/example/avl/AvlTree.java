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
        AVLNode newNode = new AVLNode(p, data);
        if(t < 0) {
            p.rightChild = newNode;
        }
        else if(t > 0) {
            p.leftChild = newNode;
        }
        rebuild(p,newNode);
    }

    /**
     * 平衡二叉树的方法
     * @param node
     */
    public void rebuild(AVLNode node,AVLNode newNode) {
        while(node != null) {
            if(calcNodeBalanceValue(node) == MAX_LEFT) {
                fixAfterInsertion(node, newNode,LEFT);
            }
            else if(calcNodeBalanceValue(node) == MAX_RIGHT) {
                fixAfterInsertion(node, newNode,RIGHT);
            }
            node = node.parent;
        }
    }

    /**
     * 调整树的结构
     * @param node
     * @param type
     */
    public void fixAfterInsertion(AVLNode node, AVLNode newNode, int type) {
        if(type == LEFT) {
            AVLNode leftChild = node.leftChild;
            if(leftChild.leftChild==newNode || leftChild.leftChild.leftChild==newNode || leftChild.leftChild.rightChild==newNode ){
                rightRotation(node);
            }else if(leftChild.rightChild==newNode || leftChild.rightChild.leftChild==newNode || leftChild.rightChild.rightChild==newNode){
                leftRotation(leftChild);
                rightRotation(node);
            }
            /*if(leftChild.rightChild != null) {   //左右旋
                leftRotation(leftChild);
                rightRotation(node);
            } else if(leftChild.leftChild != null) {  //右旋
                rightRotation(node);
            }*/
        }
        else if(type == RIGHT) {
            AVLNode rightChild = node.rightChild;
            if(rightChild.rightChild==newNode || rightChild.rightChild.leftChild==newNode || rightChild.rightChild.rightChild==newNode ){
                leftRotation(node);
            }else if(rightChild.leftChild==newNode || rightChild.leftChild.leftChild==newNode || rightChild.leftChild.rightChild==newNode){
                rightRotation(rightChild);
                leftRotation(node);
            }
            /*if(rightChild.leftChild != null) {   //右左旋
                rightRotation(rightChild);
                leftRotation(node);
            }else if(rightChild.rightChild != null) {   //左旋
                leftRotation(node);
            }*/
        }
    }

    /**
     * 左旋
     * @param node
     * @return
     */
    public void leftRotation(AVLNode node) {
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
            AVLNode leftChild = node.leftChild;
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
