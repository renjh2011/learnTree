package org.example.binary;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Node<T extends Comparable<T>>{
    private T data;
    private Node<T> lNode;
    private Node<T> rNode;

    public Node(T data) {
        this.data = data;
    }

    public Node() {
    }
}
public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    public Node<T> getRoot() {
        return root;
    }

    /**
     * 循环实现插入
     * @param data
     */
    public void insert(T data){
        if(root==null){
            root = new Node<T>();
            root.setData(data);
        }else {
            Node<T> parent;
            Node<T> curr = root;
            while (true){
                parent = curr;
                if(curr.getData().compareTo(data)<0){
                    curr = curr.getLNode();
                    if(curr==null){
                        parent.setLNode(new Node<T>(data));
                        return;
                    }
                }else {
                    curr = curr.getRNode();
                    if(curr==null){
                        parent.setRNode(new Node<T>(data));
                        return;
                    }
                }
            }
        }
    }

    /**
     * 递归实现插入
     * @param data
     */
    public void addNode(T data){
        if(root==null){
            root = new Node<T>();
            root.setData(data);
        }else {
            addNode(root,data);
        }
    }

    public void addNode(Node<T> currNode,T data){
        if(currNode.getData().compareTo(data)<0){
            if(currNode.getLNode()==null){
                currNode.setLNode(new Node<T>(data));
            }else {
                addNode(currNode.getLNode(),data);
            }
        }else {
            if(currNode.getRNode()==null){
                currNode.setRNode(new Node<T>(data));
            }else {
                addNode(currNode.getRNode(),data);
            }
        }
    }

    public void preOrder(){
        if(root==null){
            return;
        }
        preOrder(root);
    }

    public void preOrder(Node<T> node){
        if(node!=null){
            System.out.println(node.getData());
            preOrder(node.getLNode());
            preOrder(node.getRNode());
        }
    }

    public void midOrder(){
        if(root==null){
            return;
        }
        midOrder(root);
    }
    public void midOrder(Node<T> node){
        if(node!=null){
            midOrder(node.getLNode());
            System.out.println(node.getData());
            midOrder(node.getRNode());
        }
    }

    public void postOrder(){
        if(root==null){
            return;
        }
        postOrder(root);
    }
    public void postOrder(Node<T> node){
        if(node!=null){
            postOrder(node.getLNode());
            postOrder(node.getRNode());
            System.out.println(node.getData());
        }
    }
}
