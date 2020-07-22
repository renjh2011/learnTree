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
        rebuild(p,newNode);
    }

    private void rebuild(AVLNode p, AVLNode newNode) {
        while (p!=null){
            //LL / LR
            if(calcBF(p)==MAX_LEFT){
                AVLNode node = p.leftChild;
                if(node.rightChild==newNode || node.rightChild.leftChild==newNode || node.rightChild.rightChild==newNode){
                    LL(node);
                    RR(p);
                }else if(node.leftChild==newNode || node.leftChild.leftChild==newNode || node.leftChild.rightChild==newNode){
                    LL(node);
                }
            }else if(calcBF(p)==MAX_RIGHT){
                AVLNode node = p.rightChild;
                if(node.rightChild==newNode || node.rightChild.leftChild==newNode || node.rightChild.rightChild==newNode){
                    LL(node.rightChild);
                    RR(node);
                }else if(node.leftChild==newNode || node.leftChild.leftChild==newNode || node.leftChild.rightChild==newNode){
                    RR(node);
                }
            }
            p = p.parent;
        }
    }

    /**
     * RR型 左旋
     * @param node
     */
    private void LL(AVLNode node) {
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

    /**
     * LL型，右旋
     * @param node
     */
    private void RR(AVLNode node) {
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
}
