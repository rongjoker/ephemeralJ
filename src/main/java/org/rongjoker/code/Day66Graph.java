package org.rongjoker.code;

import org.junit.Test;

import java.util.*;

/**
 * 倒计时66天，解决dfs和bfs
 * @date 10/21/2020
 *
 */
public class Day66Graph {

    @Test
    public void testD2Bfs(){
        initial();
        bfs(graph,"A");
        System.out.println("------- bfs end;dfs start ---------");
        dfs(graph,"A");
    }


    public Map<String, String[]> graph = new HashMap<>();


    public void initial(){
        graph.put("A",new String[]{"B","C"});
        graph.put("B",new String[]{"A","C","D"});
        graph.put("C",new String[]{"A","B","D","E"});
        graph.put("D",new String[]{"B","C","E","F"});
        graph.put("E",new String[]{"C","D"});
        graph.put("F",new String[]{"D"});

    }

    /**
     * 广度优先
     * @param g
     * @param init
     */
    public void bfs(Map<String, String[]> g,String init){

        List<String> queue = new ArrayList<>();

        Set<String > seen = new HashSet<>();

        queue.add(init);
        seen.add(init);

        while (queue.size()>0){
            String vertex = queue.get(0);
            queue.remove(0);
            String[] strings = g.get(vertex);
            if ((null!=strings)){
                for (String string : strings) {
                    if(!seen.contains(string)){
                        queue.add(string);
                        seen.add(string);
                    }

                }
            }
            System.out.println("v:"+vertex);

        }

    }


    /**
     * 深度优先
     * @param g
     * @param init
     */
    public void dfs(Map<String, String[]> g,String init){

        List<String> stock = new ArrayList<>();

        Set<String > seen = new HashSet<>();

        stock.add(init);
        seen.add(init);

        while (stock.size()>0){
            String vertex = stock.get(stock.size()-1);//弹出最后一个元素
            stock.remove(stock.size()-1);
            String[] strings = g.get(vertex);
            if ((null!=strings)){
                for (String string : strings) {
                    if(!seen.contains(string)){
                        stock.add(string);
                        seen.add(string);
                    }

                }
            }
            System.out.println("v:"+vertex);

        }

    }




    @Test
    public void testPriorityQueue(){

        PriorityQueue<Integer> strings = new PriorityQueue<>();
        strings.add(7);
        strings.add(3);
        strings.add(2);
        strings.add(9);

        System.out.println(strings.poll());



    }


}
