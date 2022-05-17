package com.mfq.map;

import java.util.Arrays;

/**
 * 弗洛伊德算法解决有向图各个顶点之间的最短距离
 *
 * 注：一种方式是将迪杰斯特拉算法执行n次，但弗洛伊德算法会更加直观，两种方式时间复杂度相同，都为O（n*n）
 *
 * 弗洛伊德算法仍然使用带权的邻接矩阵arcs来表示有向网G，求从顶点vi到vj的最短路径。
 * 算法的实现要引入以下辅助的数据结构。
 * （1）二维数组Path[i][j]：最短路径上顶点vj的前一顶点的序号。
 * （2）二维数组D[i][j]：记录顶点vi和vj之间的最短路径长度
 *
 *  将vi到vj的最短路径长度初始化，即D[i][j]=G.arcs[i][j]，然后进行n次比较和更新。
 *
 *  ① 在vi和vj间加入顶点v0，比较（vi, vj）和（vi, v0, vj）的路径长度，取其中较短者作为vi到vj的中间顶点序号不大于0的最短路径。
 *
 * ② 在vi和vj间加入顶点v1，得到（vi,…, v1）和（v1,…, vj），其中（vi,…, v1）是vi到v1的且中间顶点的序号不大于0的最短路径，
 * （v1,…, vj）是v1到vj的且中间顶点的序号不大于0的最短路径，这两条路径已在上一步中求出。比较（vi,…, v1,…, vj）
 * 与上一步求出的vi到vj的中间顶点序号不大于0的最短路径，取其中较短者作为vi到vj的中间顶点序号不大于1的最短路径。
 *
 * ③ 依次类推，在vi和vj间加入顶点vk，若（vi,…, vk）和（vk,…, vj）分别是从vi到vk和从vk到vj的中间顶点的序号不大于k−1的最短路径，
 * 则将（vi,…, vk,…, vj）和已经得到的从vi到vj且中间顶点序号不大于k−1的最短路径相比较，其长度较短者便是从vi到vj的中间顶点的序号不大于k的最短路径。
 * 这样，经过n次比较后，最后求得的必是从vi到vj的最短路径。按此方法，可以同时求得各对顶点间的最短路径。
 *
 * 根据上述求解过程，图中的所有顶点对vi和vj间的最短路径长度对应一个n阶方阵D。在上述n+1步中，D的值不断变化，对应一个n阶方阵序列。
 *
 * n阶方阵序列可定义为：
 * D(−1) , D(0) , D(1) , …, D(k), …, D(n−1)
 * D(−1)[i][j]=G.arcs[i][j]
 *
 * 依次计算 D(−1) , D(0) , D(1) , …, D(k), …, D(n−1)
 * D(-1):代表不通过任何节点中转，直接连接的距离
 * D(0):代表通过V0节点进行中转；最短距离
 * D(1):代表通过V0/V1节点进行中转；最短距离
 * 以此类推：不难推断
 * D(1)[i][j] = Min{D(0)[i][j],D(0)[i][1] + D(0)[i][j]};
 * 而其中的因子，都在D(0)轮次中完成了计算；
 *
 * 可以推断：
 * D(k)[i][j]=Min{D(k−1)[i][j]，D(k−1)[i][k]+D(k−1)[k][j]} 0≤k≤n−1
 *
 * 对于每两个点之间的路径D(x)而言，其中转的最短路径已经在上一轮D(x-1)中计算得到，只需和D[i][j]进行比较取最小值即可
 *
 * 最终执行n轮后， D(n-1)即为判断了所有通路的情况下，最小值
 *
 * 记录前驱的作用
 * Path[i][j] 为从顶点i到顶点j最短路径上，j的前驱节点为K
 * 根据此前驱，可以得到此最短路径经过K，因为是在D(k)阶段，获取到的最短路径，则赋值；
 * 通过K，再得到Path[i][k] ,不断逆向，得到最短路径的全路径；
 *
 */
public class FloydMap {

    public static void shortestPath_Floyd(int[][] arcs){
        int length = arcs.length;
        int[][] D = new int[length][length];
        int[][] Path = new int[length][length];

        //初始化D(-1)
        for(int i=0;i<length;i++){
            for(int j=0;j<length;j++){
                D[i][j] = arcs[i][j];
                if(arcs[i][j] < Integer.MAX_VALUE){
                    Path[i][j] = i;
                }else{//数组初始化默认为0，0为一个有效节点，需要改为-1
                    Path[i][j] = -1;
                }
            }
        }

        //轮次D(k)
        for(int k=0;k<length;k++){
            for(int i=0;i<length;i++){
                for(int j=0;j<length;j++){
                    if(Long.valueOf(D[i][k]) + D[k][j] < D[i][j]){
                        D[i][j] = D[i][k] + D[k][j];
                        Path[i][j] = Path[k][j];
                    }
                }
            }
        }
        for(int i=0;i<length;i++){
            System.out.println(Arrays.toString(D[i]));
        }
    }



    public static void main(String[] args) {
        int max = Integer.MAX_VALUE;
        //arcs[i][j],代表顶点i到顶点j的权值，如果没有路径，则为max;
        int[][] arcs = {
                {0,1,max,4},
                {max,0,9,2},
                {3,5,0,8},
                {max,max,6,0}
        };
        shortestPath_Floyd(arcs);
    }
}
