package com.mfq.map;


import java.math.BigInteger;
import java.util.Arrays;

/**
 * 迪杰斯特拉算法解决有向图，某个顶点到其余各顶点的最短距离
 *
 * 原理：如果把所有顶点按照抵达路径长度排序，那么长度较长的路径，其上一定包含了路径短的顶点；故而应该从短的开始计算排序，再从已计算的长度中，探索下一个最短路径
 *
 * 假设用带权的邻接矩阵arcs来表示带权有向网G，G.arcs[i][j]表示弧<vi, vj>上的权值。若<vi, vj>不存在，则置G.arcs[i][j]为∞，源点为v0。
 *
 * 算法的实现要引入以下辅助的数据结构。
 * ① 一维数组S[i]：记录从源点v0到终点vi是否已被确定最短路径长度，true表示确定，false表示尚未确定。
 * ② 一维数组Path[i]：记录从源点v0到终点vi的当前最短路径上vi的直接前驱顶点序号。其初值为：如果从v0到vi有弧，则Path [i]为v0；否则为−1。
 * ③ 一维数组D[i]：记录从源点v0到终点vi的当前最短路径长度。其初值为：如果从v0到vi有弧，则D[i]为弧上的权值；否则为∞。
 *
 * 显然，长度最短的一条最短路径必为(v0, vk)，满足以下条件：
 *          D[k]=Min{D[i]|vi∈V−S}
 * 求得顶点vk的最短路径后，将其加入到第一组顶点集S中。
 *
 * 每当加入一个新的顶点到顶点集S，对第二组剩余的各个顶点而言，多了一个“中转”顶点，
 * 从而多了一个“中转”路径，所以要对第二组剩余的各个顶点的最短路径长度进行更新。
 *
 * 原来v0到vi的最短路径长度为D[i]，加进vk之后，以vk作为中间顶点的“中转”路径长度为：
 * D[k]+G.arcs[k][i]，若D[k]+G.arcs[k][i]<D[i]，则用D[k]+G.arcs[k][i]取代D[i]。
 * 更新后，再选择数组D中值最小的顶点加入到第一组顶点集S中，如此进行下去，直到图中所有顶点都加入到第一组顶点集S中为止。
 */
public class DjMap {

    //以V-index 为源点，计算抵达各顶点的距离
    public static void ShortestPath_DIJ(int[][] arcs,int index){
        int length = arcs.length;

        //初始化,标记已计算最短记录的顶点，S[index] = true,自己到自己最近
        boolean[] S = new boolean[length];
        S[index] = true;
        //初始化Path[],为抵达各节点最短路径的前驱节点坐标，Path[index] = index 初始化为-1
        int[] Path = new int[length];
        Path[index] = index;
        //数组D[i]：记录从源点v0到终点vi的当前最短路径长度。其初值为：如果从v0到vi有弧，则D[i]为弧上的权值；否则为∞
        int[] D = new int[length];
        for(int i=0;i<length;i++){
            //初始化完成之后，从顶点index到所有能够一次抵达的顶点，会被初始化赋值，此时下一段最短路径一定在此之中
            D[i] = arcs[index][i];
            if(D[i] < Integer.MAX_VALUE){//维护前驱顶点
                Path[i] = index;
            }
        }


        //循环n-1次，对后续位置进行计算
        for(int i=1;i<length;i++){
            //每一次循环取当前D[i]中的最小值，即为当前最短长度
            int maxValue = Integer.MAX_VALUE;
            int k = 0;
            for(int v=0;v<length;v++){
                if(!S[v] && D[v] < maxValue){
                    maxValue = D[v];
                    k = v;
                }
            }
            S[k] = true;

            //此时target即为，剩余未完成排序的顶点中，路径长度最小的顶点
            //维护当前各个顶点的“临时最短路径”
            //每一次需要更新的D[i]范围为，基于上轮次最短顶点的拓展,进行更新对应的D[i]
            for(int v=0;v<length;v++){
                //未计算完成的顶点，D[i]的值才参与比较,表达式成立代表有路径
                if(!S[v] && Long.valueOf(D[k]) + arcs[k][v] < D[v]){//避免溢出
                    Path[v] = k;
                    D[v] = D[k]+ arcs[k][v];
                }
            }
        }
        System.out.println(Arrays.toString(D));
    }


    public static void main(String[] args) {

        int max = Integer.MAX_VALUE;
        //arcs[i][j],代表顶点i到顶点j的权值，如果没有路径，则为max;
        int[][] arcs = {
                {0,max,10,max,30,100},
                {max,0,5,max,max,max},
                {max,max,0,50,max,max},
                {max,max,max,0,max,10},
                {max,max,max,20,0,60},
                {max,max,max,max,max,0}
        };
        ShortestPath_DIJ(arcs,0);
    }

}
