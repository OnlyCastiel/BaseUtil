package com.mfq.map;


import java.util.Arrays;
import java.util.Stack;

/**
 * 一个无环的有向图称作有向无环图（Directed Acycline Graph），简称DAG图。
 * 有向无环图是描述一项工程或系统的进行过程的有效工具。通常把计划、施工过程、生产流程、程序流程等都当成一个工程。
 * 除了很小的工程外，一般的工程都可分为若干个称做活动（Activity）的子工程，
 * 而这些子工程之间，通常受着一定条件的约束，如其中某些子工程的开始必须在另一些子工程完成之后。
 *
 * 拓扑排序的过程
 * （1）在有向图中选一个无前驱的顶点且输出它。
 * （2）从图中删除该顶点和所有以它为尾的弧。
 * （3）重复（1）和（2），直至不存在无前驱的顶点。
 * （4）若此时输出的顶点数小于有向图中的顶点数，则说明有向图中存在环，否则输出的顶点序列即为一个拓扑序列。
 *
 * ① 求出各顶点的入度存入数组indegree[i]中，并将入度为0的顶点入栈。
 * ② 只要栈不空，则重复以下操作：
 *  ·将栈顶顶点vi出栈并保存在拓扑序列数组topo中；
 *  ·对顶点vi的每个邻接点vk的入度减1，如果vk的入度变为0，则将vk入栈。
 * ③ 如果输出顶点个数少于AOV-网的顶点个数，则网中存在有向环，无法进行拓扑排序，否则拓扑排序成功。
 *
 * 核心要点：环无法进行破开，找不到遍历的起点；
 */
public class AOVMap {

    public static int[] TopoligicalSort(int[][] arcs){
        int length = arcs.length;
        //数组，计算并存储每个元素的入度
        int[] indegree = new int[length];
        //维护一个栈，记录入度为0的元素
        Stack<Integer> S= new Stack<>();
        //数组记录拓扑序列
        int[] topo = new int[length];

        //入度初始化,弧头为自己
        for(int i= 0;i<length;i++){
            int count = 0;
            for(int j=0;j<length;j++){
                if(i!=j && arcs[j][i] == 1){
                    count++;
                }
            }
            indegree[i] =count;
            //入度为零的顶点入栈
            if(count == 0){
                S.push(i);
            }
        }
        for(int i=0;!S.empty() && i<length ;i++){
            Integer pop = S.pop();
            topo[i] = pop;
            //减去对应的入度
            for(int j=0;j<length;j++){
                if(arcs[pop][j] == 1 && indegree[j] > 0){
                    indegree[j] --;
                    if(indegree[j] == 0){//入度为0则入栈
                        S.push(j);
                    }
                }
            }
        }
        //遍历输出topo
        //System.out.println(Arrays.toString(topo));
        return topo;
    }



    public static void main(String[] args) {
        int m = Integer.MAX_VALUE;
        //arcs[i][j],代表顶点i到顶点j的权值，如果没有路径，则为max;
        int[][] arcs = {
                {0,1,1,1,m,m,m,m,m,m,m,1},
                {m,0,1,m,m,m,m,m,m,m,m,m},
                {m,m,0,m,1,m,1,1,m,m,m,m},
                {m,m,m,0,1,m,m,m,m,m,m,m},
                {m,m,m,m,0,m,1,m,m,m,m,m},
                {m,m,m,m,m,0,m,1,m,m,m,m},
                {m,m,m,m,m,m,0,m,m,m,m,m},
                {m,m,m,m,m,m,m,0,m,m,m,m},
                {m,m,m,m,m,m,m,m,0,1,1,1},
                {m,m,m,m,m,m,m,m,m,0,m,1},
                {m,m,m,m,m,1,m,m,m,m,0,m},
                {m,m,m,m,m,m,m,m,m,m,m,0},
        };
        TopoligicalSort(arcs);
    }



}
