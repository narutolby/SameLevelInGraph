package com.baidu;

import java.util.*;


/**
 * @author huyang01
 * 
 * @since 2013-01-10
 * 
 * 算法核心思想：
 * a的数据流必经过b的深刻含义为，a的数据流无论从哪条路走都必须经过b，这就要求a的相邻节点也具有该特性；
 * 同理将有向图反向，b的数据流也必经过a
 */
public class SameLevel {
    //n为节点个数，e为有向边个数
    private static int n,e;
    //有向图正向临接表
    private static Map<Character,LinkedList<Character>> adjacencyMap = new HashMap<Character,LinkedList<Character>>();
    //有向图反向临接表
    private static Map<Character,LinkedList<Character>> adjacencyReverseMap = new HashMap<Character,LinkedList<Character>>();
    //填充正向与反向临接表
    private static void fullMap(Map<Character,LinkedList<Character>> map,Character a,Character b){
        if(!map.containsKey(a)){
            map.put(a,new LinkedList<Character>());
        }
        map.get(a).add(b);
        if(!map.containsKey(b)){
            map.put(b,new LinkedList<Character>());
        }
    }
   /*
    * 根据输入的map创建图
    */
    public static boolean createGraph(Set<Edge<Character>> set){
        if(set == null || set.size() == 0) return false;
        for(Edge<Character> edge : set){
            Character key = edge.getStart(),value = edge.getEnd();
            fullMap(adjacencyMap,key,value);
            fullMap(adjacencyReverseMap,value,key);
        }
        return true;
    }
     /*
      * 判断a是否从任意路径可达b
      */
    public static boolean DFSTraverseAtoB(char a ,char b){
    	if(a == b)  return true;
        boolean ret = false;
        LinkedList<Character> adjacentList =  adjacencyMap.get(a);
        for(char c : adjacentList){
            if(!DFSTraverseAtoB(c,b)){
                return false;
            }
            ret = true;
        }
        return ret;
    }
    /*
     * 判断两个节点是否是同一层次
     */
    public static boolean isSameLevel(char a ,char b, Set<Edge<Character>> set){
        if(set == null || set.size() == 0) return false;
        boolean  ret = false,ret1 = false,created = createGraph(set);
        if(!created || !adjacencyMap.containsKey(a) || !adjacencyMap.containsKey(b)){
            throw new RuntimeException("Error!");
        }
        ret = DFSTraverseAtoB(a ,b);
        adjacencyMap = adjacencyReverseMap;
        //有向图反向再判断一次
        ret1 = DFSTraverseAtoB(b,a);
        return ret && ret1;
    }
     /*
      *
      */
    public static void main(String[]args){
        Set<Edge<Character>> set = new HashSet<Edge<Character>>();
        set.add(new Edge<Character>('a','b'));
        set.add(new Edge<Character>('b','c'));
        set.add(new Edge<Character>('b','d'));
        set.add(new Edge<Character>('d','e'));
        set.add(new Edge<Character>('c','d'));
        System.out.println(isSameLevel('a','d',set));

    }

    /*
     * 定义有向图的边对象
     * @start 为边的起点
     * @end 为边的终点
     */
    static class Edge<T>{

        private T start;
        private T end;

        public T getStart() {
            return start;
        }

        public void setStart(T start) {
            this.start = start;
        }

        public T getEnd() {
            return end;
        }

        public void setEnd(T end) {
            this.end = end;
        }

        public Edge(T start,T end){
             this.start = start;
             this.end = end;
        }

    }
}
