package algorithm;

import util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by camork on 2019-03-05.
 */
class TreeNode {
    TreeNode(int value) {
        this.value = value;
    }

    TreeNode left;
    TreeNode right;
    int value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

public class BinarySearchTree {

    private final TreeNode rootNode;

    public BinarySearchTree(int value) {
        rootNode = new TreeNode(value);
    }

    /*
     *         6(root)
     *        ↙
     *       3→️→️→️10(nextLast)
     *       ↙
     *     1→→️4→9→→11
     *
     *  enqueue order: 6 3 10 1 4 9 11
     */
    public List<List<Integer>> printTreeByHierarchy() {
        TreeNode root = rootNode;

        Queue<TreeNode> queue = new LinkedList<>();

        TreeNode last = root;
        TreeNode nextLast = null;

        queue.offer(root);

        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> nodes = new ArrayList<>();
        while (!queue.isEmpty()) {
            root = queue.poll();

            nodes.add(root.value);

            if (root.left != null) {
                queue.offer(root.left);
                nextLast = root.left;
            }

            if (root.right != null) {
                queue.offer(root.right);
                nextLast = root.right;
            }

            if (last == root) {
                last = nextLast;

                lists.add(nodes);
                nodes = new ArrayList<>();
            }
        }

        System.out.println(lists);
        return lists;
    }

    public void printTreeByPreOrder(TreeNode root) {
        if (root != null) {
            System.out.println(root.value);

            printTreeByPreOrder(root.left);
            printTreeByPreOrder(root.right);
        }
    }

    public TreeNode getRoot() {
        return rootNode;
    }

    public TreeNode get(int value) {
        TreeNode root = rootNode;

        while (true) {
            if (root == null) {
                return null;
            }

            if (value == root.value) {
                return root;
            }
            else if (value < root.value) {
                root = root.left;
            }
            else {
                root = root.right;
            }
        }
    }

    public void insert(int... values) {
        for (int value : values) {
            insert(value);
        }
    }

    public TreeNode insert(int value) {
        TreeNode root = rootNode;

        TreeNode newNode = new TreeNode(value);
        while (true) {
            if (value == root.value) {
                return null;
            }
            else if (value < root.value) {
                if (root.left != null) {
                    root = root.left;
                }
                else {
                    return root.left = newNode;
                }
            }
            else {
                if (root.right != null) {
                    root = root.right;
                }
                else {
                    return root.right = newNode;
                }
            }
        }
    }

    public void remove(int value) {
        TreeNode currentRoot = rootNode;

        while (true) {
            if (currentRoot == null) {
                return;//没找到,返回或者抛异常
            }

            if (value < currentRoot.value) {
                currentRoot = currentRoot.left;
            }
            else if (value > currentRoot.value) {
                currentRoot = currentRoot.right;
            }
            else {
                TreeNode replaceNode = null;
                if (currentRoot.left == null && currentRoot.right == null) {//无双子节点
                    resetNodeParent(currentRoot, null);
                }
                else if (currentRoot.left != null && currentRoot.right != null) {//双子节点
                    replaceNode = getSuccessor(currentRoot);

                    replaceNode.left = currentRoot.left;
                    replaceNode.right = currentRoot.right;

                    resetNodeParent(replaceNode, null);       //重设后继节点的父节点
                    resetNodeParent(currentRoot, replaceNode);//重设正在移除节点的父节点
                }
                else if (currentRoot.left != null) {
                    resetNodeParent(currentRoot, currentRoot.left);  //
                }                                                                  //
                else {                                                             //单节点
                    resetNodeParent(currentRoot, currentRoot.right);//
                }                                                                  //

                return;
            }
        }
    }

    private TreeNode getSuccessor(TreeNode node) {
        TreeNode successor = null;

        if (node.right != null) {
            successor = node.right;

            while (successor.left != null) {
                successor = successor.left;
            }
        }

        return successor;
    }

    private void resetNodeParent(TreeNode node, TreeNode newNode) {
        Pair<TreeNode,Boolean> pair = getParent(node);

        if (pair == null) {
            return;
        }

        TreeNode parent = pair.getFirst();

        if (pair.getSecond()) {
            parent.left = newNode;
        }
        else {
            parent.right = newNode;
        }
    }

    private Pair<TreeNode,Boolean> getParent(TreeNode node) {
        TreeNode root = rootNode;
        TreeNode parent = null;
        boolean inLeft = false;

        while (true) {
            if (root == null) {
                return null;
            }

            if (node.value == root.value) {
                return Pair.pair(parent, inLeft);
            }
            else if (node.value < root.value) {
                inLeft = true;
                parent = root;
                root = root.left;
            }
            else {
                inLeft = false;
                parent = root;
                root = root.right;
            }
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree(41);
        tree.insert(20,65,11,29,50,91,28,32,72,99,27);
        tree.printTreeByHierarchy();

        tree.insert(26);
        tree.printTreeByHierarchy();

        TreeNode treeNode = tree.get(27);
        System.out.println(treeNode);

        //tree.remove(20);//含双子节点
        //tree.remove(72);//无子节点
        tree.remove(27);//单子节点
        tree.printTreeByHierarchy();
        tree.printTreeByPreOrder(tree.getRoot());
    }

}