package com.baidu;

import java.util.*;


/**
 * @author narutolby
 *
 * @since 2013-01-10
 *
 * 算法核心思想：
 * a的数据流必经过b的深刻含义为，a的数据流无论从哪条路走都必须经过b，这就要求a的相邻节点也具有该特性；
 * 同理将有向图反向，b的数据流也必经过a
 */
public class SameLevel {
    //n为节点个数，e为有向边个数
    private int n,e;
    //缓存连通图两点是否任意路径可达
    private Set<Edge<Character>> cacheSet = new HashSet<Edge<Character>>();
    //有向图正向临接表
    private Map<Character,LinkedList<Character>> adjacencyMap = new HashMap<Character,LinkedList<Character>>();
    //有向图反向临接表
    private Map<Character,LinkedList<Character>> adjacencyReverseMap = new HashMap<Character,LinkedList<Character>>();
    /*
     * 构造方法，用来有向联通图图
     */
    public SameLevel(Set<Edge<Character>>set){
       boolean created = createGraph(set);
        if(!created){
            throw new RuntimeException("Error!");
        }
    }
    //填充正向与反向临接表
    private void fullMap(Map<Character,LinkedList<Character>> map,Character a,Character b){
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
    private boolean createGraph(Set<Edge<Character>> set){
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
    private boolean DFSTraverseAtoB(char a ,char b ,boolean isCached){
    	if(a == b || cacheSet.contains(new Edge<Character>(a,b)))  return true;
        boolean ret = false;
        LinkedList<Character> adjacentList =  adjacencyMap.get(a);
        for(char c : adjacentList){
            if(!DFSTraverseAtoB(c,b,isCached)){
                return false;
            }
            ret = true;
           // if(isCached) cacheSet.add(new Edge<Character>(c,b));
        }
       if(ret && isCached) cacheSet.add(new Edge<Character>(a,b));
        return ret;
    }
    /*
     * 判断两个节点是否是同一层次
     */
    public boolean isSameLevel(char a ,char b){
        if(!adjacencyMap.containsKey(a) || !adjacencyMap.containsKey(b)) return false;
        boolean  ret = false,ret1 = false;
        ret = DFSTraverseAtoB(a ,b,true);
        swapMap();
        //有向图反向再判断一次
        ret1 = DFSTraverseAtoB(b,a ,false);
        swapMap();
        return ret && ret1;
    }
    /*
     * adjacencyMap与 adjacencyReverseMap交换
     */
   private void swapMap(){
        Map<Character,LinkedList<Character>> tmp = adjacencyReverseMap;
        adjacencyReverseMap = adjacencyMap;
        adjacencyMap = tmp;
    }
    public static void main(String[]args){
        Set<Edge<Character>> set = new HashSet<Edge<Character>>();
        set.add(new Edge<Character>('0','1'));
        set.add(new Edge<Character>('1','2'));
        set.add(new Edge<Character>('1','3'));
        set.add(new Edge<Character>('2','3'));
        set.add(new Edge<Character>('3','4'));
        SameLevel sl = new SameLevel(set);
        System.out.println(sl.isSameLevel('0', '1'));
        System.out.println(sl.isSameLevel('1', '0'));
        System.out.println(sl.isSameLevel('1', '3'));
        System.out.println(sl.isSameLevel('1', '4'));
        System.out.println(sl.isSameLevel('2', '4'));
        System.out.println(sl.isSameLevel('2', '3'));

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            if (end != null ? !end.equals(edge.end) : edge.end != null) return false;
            if (start != null ? !start.equals(edge.start) : edge.start != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = start != null ? start.hashCode() : 0;
            result = 31 * result + (end != null ? end.hashCode() : 0);
            return result;
        }
    }
}
