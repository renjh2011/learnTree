package org.example.binary;

import lombok.Getter;
import lombok.Setter;

import javax.swing.tree.TreeNode;
import java.util.Objects;
import java.util.Stack;

@Getter
@Setter
class Node<T extends Comparable<T>> {
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
     *
     * @param data
     */
    public void insert(T data) {
        if (root == null) {
            root = new Node<T>();
            root.setData(data);
        } else {
            Node<T> parent;
            Node<T> curr = root;
            while (true) {
                parent = curr;
                if (curr.getData().compareTo(data) < 0) {
                    curr = curr.getLNode();
                    if (curr == null) {
                        parent.setLNode(new Node<T>(data));
                        return;
                    }
                } else {
                    curr = curr.getRNode();
                    if (curr == null) {
                        parent.setRNode(new Node<T>(data));
                        return;
                    }
                }
            }
        }
    }

    /**
     * 递归实现插入
     *
     * @param data
     */
    public void addNode(T data) {
        if (root == null) {
            root = new Node<T>();
            root.setData(data);
        } else {
            addNode(root, data);
        }
    }

    public void addNode(Node<T> currNode, T data) {
        if (currNode.getData().compareTo(data) < 0) {
            if (currNode.getLNode() == null) {
                currNode.setLNode(new Node<T>(data));
            } else {
                addNode(currNode.getLNode(), data);
            }
        } else {
            if (currNode.getRNode() == null) {
                currNode.setRNode(new Node<T>(data));
            } else {
                addNode(currNode.getRNode(), data);
            }
        }
    }

    public void preOrder() {
        if (root == null) {
            return;
        }
        preOrder(root);
    }

    public void preOrder(Node<T> node) {
        if (node != null) {
            System.out.println(node.getData());
            preOrder(node.getLNode());
            preOrder(node.getRNode());
        }
    }

    public void circlePreOrder(Node<T> node) {
        Node<T> cNode = node;
        Stack<Node<T>> stack = new Stack<>();
        stack.push(node);
        while (!stack.empty()){
            Node<T> nodeOut = stack.pop();
            if(nodeOut!=null){
                System.out.println(nodeOut.getData());
                if(nodeOut.getRNode()!=null)
                stack.push(nodeOut.getRNode());
                if(nodeOut.getLNode()!=null)
                stack.push(nodeOut.getLNode());
            }
        }
    }

    public void midOrder() {
        if (root == null) {
            return;
        }
        midOrder(root);
    }

    public void midOrder(Node<T> node) {
        if (node != null) {
            midOrder(node.getLNode());
            System.out.println(node.getData());
            midOrder(node.getRNode());
        }
    }

    public Node<T> midSearchLeftNode(Node<T> node) {
        if (node != null) {
            midOrder(node.getLNode());
            if (node.getRNode() == null && node.getLNode() == null) {
                return node;
            }
            midOrder(node.getRNode());
        }
        //未查找到
        return null;
    }

    public void postOrder() {
        if (root == null) {
            return;
        }
        postOrder(root);
    }

    public void postOrder(Node<T> node) {
        if (node != null) {
            postOrder(node.getLNode());
            postOrder(node.getRNode());
            System.out.println(node.getData());
        }
    }

    public void delete(T data) {
        if (root == null) {
            return;
        }
        delete(null, root, data);
    }

    public void delete(Node<T> pNode, Node<T> cNode, T data) {
        if (cNode.getData().compareTo(data) < 0) {
            delete(cNode, cNode.getLNode(), data);
        } else if (cNode.getData().compareTo(data) > 0) {
            delete(cNode, cNode.getRNode(), data);
        } else {
            if(cNode.getLNode()==null){
                if (pNode != null) {
                    if (pNode.getLNode() == cNode)
                        pNode.setLNode(cNode.getLNode());
                    else
                        pNode.setRNode(cNode.getRNode());
                }
            }else if(cNode.getRNode()==null){
                if (pNode != null) {
                    if (pNode.getLNode() == cNode)
                        pNode.setLNode(cNode.getRNode());
                    else
                        pNode.setRNode(cNode.getLNode());
                }
            }else {
                //取前驱结点 ，先转左，然后一直到最右
                Node<T> replaceNode = cNode.getRNode();
                Node<T> replaceParentNode = cNode;

                while (replaceNode.getLNode() != null) {
                    replaceParentNode = replaceNode;
                    replaceNode = replaceNode.getLNode();
                }
                //获取到前驱结点极其双亲结点

                //如果前驱结点的双亲结点是 要删除的结点
                //交换
                T tmp = cNode.getData();
                cNode.setData(replaceNode.getData());
                replaceNode.setData(tmp);
                tmp = null;

                if (replaceParentNode == cNode)
                    cNode.setRNode(null);
                else
                    replaceParentNode.setLNode(null);

            }
        }
    }
}
