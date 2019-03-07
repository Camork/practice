package algorithm;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
        TreeNode currentRoot = rootNode;

        Queue<TreeNode> queue = new LinkedList<>();

        TreeNode last = currentRoot;
        TreeNode nextLast = null;

        queue.offer(currentRoot);

        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> nodes = new ArrayList<>();
        while (!queue.isEmpty()) {
            currentRoot = queue.poll();

            nodes.add(currentRoot.value);

            if (currentRoot.left != null) {
                queue.offer(currentRoot.left);
                nextLast = currentRoot.left;
            }

            if (currentRoot.right != null) {
                queue.offer(currentRoot.right);
                nextLast = currentRoot.right;
            }

            if (last == currentRoot) {//因为last是当前层的最后一个节点，如果等式成立，说明这层已经遍历完
                last = nextLast;

                lists.add(nodes);
                nodes = new ArrayList<>();
            }
        }

        System.out.println(lists);
        return lists;
    }

    public void getTreeByInOrder(List<TreeNode> list, TreeNode root) {
        if (root != null) {
            getTreeByInOrder(list, root.left);
            list.add(root);
            getTreeByInOrder(list, root.right);
        }
    }

    public TreeNode getRoot() {
        return rootNode;
    }

    public TreeNode get(int value) {
        TreeNode currentRoot = rootNode;

        while (true) {
            if (currentRoot == null) {
                return null;
            }

            if (value == currentRoot.value) {
                return currentRoot;
            }
            else if (value < currentRoot.value) {
                currentRoot = currentRoot.left;
            }
            else {
                currentRoot = currentRoot.right;
            }
        }
    }

    public void insert(int... values) {
        for (int value : values) {
            insert(value);
        }
    }

    public TreeNode insert(int value) {
        TreeNode currentRoot = rootNode;

        TreeNode newNode = new TreeNode(value);
        while (true) {
            if (value == currentRoot.value) {
                return null;
            }
            else if (value < currentRoot.value) {
                if (currentRoot.left != null) {
                    currentRoot = currentRoot.left;
                }
                else {
                    return currentRoot.left = newNode;
                }
            }
            else {
                if (currentRoot.right != null) {
                    currentRoot = currentRoot.right;
                }
                else {
                    return currentRoot.right = newNode;
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
                TreeNode replacement;//代替者
                if (currentRoot.left == null && currentRoot.right == null) {//无双子节点
                    resetParentNode(currentRoot, null);
                }
                else if (currentRoot.left != null && currentRoot.right != null) {//双子节点
                    replacement = getSuccessor(currentRoot);

                    replacement.left = currentRoot.left;
                    replacement.right = currentRoot.right;

                    resetParentNode(replacement, null);       //重设后继节点的父节点
                    resetParentNode(currentRoot, replacement);//重设正在移除节点的父节点
                }
                else if (currentRoot.left != null) {
                    resetParentNode(currentRoot, currentRoot.left);  //
                }                                                                  //
                else {                                                             //单节点
                    resetParentNode(currentRoot, currentRoot.right);//
                }                                                                  //

                return;
            }
        }
    }

    @Nullable
    private TreeNode getSuccessor(@NotNull TreeNode node) {
        List<TreeNode> list = new ArrayList<>();

        getTreeByInOrder(list, getRoot());

        int i = list.indexOf(node);

        if (i > 0 && ++i < list.size()) {
            return list.get(i);
        }

        return null;
    }

    private void resetParentNode(TreeNode node, TreeNode newNode) {
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

    /**
     * @return 一对返回值：父节点和当前节点是否为左节点
     */
    @Nullable
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

        tree.remove(20);//含双子节点
        //tree.remove(72);//无子节点
        //tree.remove(27);//单子节点
        tree.printTreeByHierarchy();
    }

}