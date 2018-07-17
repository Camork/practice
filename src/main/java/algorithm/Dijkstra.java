package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * @author chaisson
 * @since 2015-5-30 上午11:51:59
 *
 */
public class Dijkstra {

    List<Node> openList = new ArrayList<Node>();//未访问过

    List<Node> closeList = new ArrayList<Node>();//已访问过

    Node A = new Node("A");
    Node B = new Node("B");
    Node C = new Node("C");
    Node D = new Node("D");
    Node E = new Node("E");


    //初始化数据节点之间的关系
    private void init(){

        A.linkedNode.add(E);
        A.linkedNode.add(D);
        A.setValue(E,5);
        A.setValue(D,3);

        B.linkedNode.add(A);
        B.linkedNode.add(C);
        B.setValue(A, 10);
        B.setValue(C, 18);

        C.linkedNode.add(A);
        C.setValue(A, 5);

        D.linkedNode.add(C);
        D.setValue(C, 2);

        E.linkedNode.add(C);
        E.linkedNode.add(D);
        E.setValue(C, 2);
        E.setValue(D, 2);

        openList.add(A);
        openList.add(B);
        openList.add(C);
        openList.add(D);
        openList.add(E);

    }

    //计算从start到end，走过的路径
    public void calculate(Node start,Node end){
        if(closeList.size() == openList.size()){
            System.out.println(start.getName()+"->"+end.getName()+" min.length.length:"+start.getValue(end));
            return;
        }
        Node childNode = getMinValueNode(start);//找到目前除已经分析过的节点之外的距离start节点最近的节点
        start.getAllPassNodes(childNode).add(childNode);//记录扩展到当前最近节点所有经过的节点
        if(childNode == end){
            System.out.println(start.getName()+"->"+end.getName()+" min.length:"+start.getValue(end));
            return;
        }
        //System.out.println("当前距离"+start.getName()+"最近节点为："+childNode.getName());
        for(Node ccNode : childNode.linkedNode){
            if(closeList.contains(ccNode)){
                continue;
            }
            /**
             * start节点到距离其最近的一个节点的其中一个子节点的距离（假设有1个或多个子节点）
             * 即start节点到子子节点的距离
             * 重新计算一遍A（假设start就是A，下同）到所有点的距离,与原来的距离相比较
             */
            int ccnodeValue = start.getValue(childNode)+childNode.getValue(ccNode);//超过最大值之后，会变成负数
            if(Math.abs(ccnodeValue) < start.getValue(ccNode)){
                start.setValue(ccNode,ccnodeValue);
                System.out.println(start.getName()+"->"+ccNode.getName()+"的目前最短距离是："+ccnodeValue);//这个最短距离只是暂时的，只要分析没有结束，最短距离可能进一步缩小
                start.getAllPassNodes(ccNode).clear();//临时最短距离缩小，所经过路径也清除重新添加
                start.getAllPassNodes(ccNode).addAll(start.getAllPassNodes(childNode));
                start.getAllPassNodes(ccNode).add(ccNode);
            }
        }
        closeList.add(childNode);
        calculate(start,end);//重复计算A到所有点的最短距离之后，再取距离A最短的节点，对其进行子节点分析【往外面节点扩展分析】
    }

    //取跟入参节点距离最近的节点，如果有多个相同距离的节点，则随便取其中一个
    private Node getMinValueNode(Node node){
        Node retNode = null;
        int minValue = Integer.MAX_VALUE;
        for(Node n : node.getValueMap().keySet()){
            if(closeList.contains(n)){
                continue;
            }
            if(node.getValue(n) < minValue){
                minValue = node.getValue(n);
                retNode = n;
            }
        }
        return retNode;
    }

    public static void main(String[] args) {
        Dijkstra d = new Dijkstra();
        d.init();
        d.closeList.add(d.A);
        d.calculate(d.A, d.E);
        //打印路径
        for(Node node : d.A.getAllPassNodes(d.E)){
            System.out.print(node.getName()+"->");
        }
    }
}


class Node {

    private String name;

    //记录本Node所有相连的Node
    public List<Node> linkedNode = new ArrayList<Node>();

    //记录本Node与其它Node的最短距离
    private Map<Node,Integer> valueMap = new HashMap<Node,Integer>();

    //记录从本Node到其它Node之间最短距离时所有经过的节点，并保持前后顺序，其实与valueMap对应
    private Map<Node,LinkedHashSet<Node>> orderSetMap = new HashMap<Node,LinkedHashSet<Node>>();

    public Node(String name){
        this.name = name;
    }

    public void setValue(Node node,Integer value){
        valueMap.put(node, value);
    }

    //如果没有本节点到参数节点的取值，则默认最大值
    public Integer getValue(Node node){
        return valueMap.get(node) == null? Integer.MAX_VALUE : valueMap.get(node);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Node, Integer> getValueMap() {
        return valueMap;
    }

    //取本节点到参数节点经过的所有节点集合
    public LinkedHashSet<Node> getAllPassNodes(Node node) {
        if(orderSetMap.get(node) == null){
            LinkedHashSet<Node> set = new LinkedHashSet<Node>();
            set.add(this);
            orderSetMap.put(node, set);
        }
        return orderSetMap.get(node);
    }

}