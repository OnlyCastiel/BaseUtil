package com.mfq.map;

import com.mfq.home.DateUtil;

import java.util.Arrays;
import java.util.Date;

/**
 * AOE-网在工程计划和经营管理中有广泛的应用，针对实际的应用问题，通常需要解决以下两个问题：
 * （1）估算完成整项工程至少需要多少时间；
 * （2）判断哪些活动是影响工程进度的关键。
 *
 * 由于整个工程只有一个开始点和一个完成点，故在正常的情况（无环）下，网中只有一个入度为零的点，称作源点，也只有一个出度为零的点，称作汇点。
 * 在AOE-网中，一条路径各弧上的权值之和称为该路径的带权路径长度（后面简称路径长度）。
 * 要估算整项工程完成的最短时间，就是要找一条从源点到汇点的带权路径长度最长的路径，称为关键路径（Critical Path）。
 * 关键路径上的活动叫做关键活动，这些活动是影响工程进度的关键，它们的提前或拖延将使整个工程提前或拖延。
 *
 * 定义4个描述变量
 *（1）事件vi的最早发生时间ve(i)进入事件vi的每一活动都结束，vi才可发生，所以ve(i)是从源点到vi的最长路径长度。
 *求ve(i)的值，可根据拓扑顺序从源点开始向汇点递推。
 * 通常将工程的开始顶点事件v0的最早发生时间定义为0，即
 * ve(0)=0
 * ve(i)=Max{ve(k)+wk,i} <vk，vi>∈T，1≤i≤n−1
 * 其中，T是所有以vi为头的弧的集合，wk,i是弧<vk, vi>的权值，即对应活动<vk, vi>的持续时间。
 *
 * （2）事件vi的最迟发生时间vl(i)
 * 事件vi的发生不得延误vi的每一后继事件的最迟发生时间。
 * 为了不拖延工期，vi的最迟发生时间不得迟于其后继事件vk的最迟发生时间减去活动<vi, vk>的持续时间。
 * 求出ve(i)后，可根据逆拓扑顺序从汇点开始向源点递推，求出vl(i)。
 * vl(n−1)=ve(n−1)
 * vl(i)=Min{vl(k)−wi,k} <vi，vk>∈S，0≤i≤n−2
 * 其中，S是所有以vi为尾的弧的集合，wi,k是弧<vi, vk>的权值。
 *
 * （3）活动ai=<vj, vk>的最早开始时间e(i)
 * 只有事件vj发生了，活动ai才能开始。所以，活动ai的最早开始时间等于事件vj的最早发生时间ve(j)，
 * 即e(i)=ve(j)
 *
 *（4）活动ai=<vj, vk>的最晚开始时间l(i)活动ai的开始时间需保证不延误事件vk的最迟发生时间。
 * 所以活动ai的最晚开始时间l(i)等于事件vk的最迟发生时间vl(k)减去活动ai的持续时间wj,k，即：
 * l(i)=vl(k)−wj,k
 * 显然，对于关键活动而言，e(i)=l(i)。
 * 对于非关键活动，l(i)−e(i)的值是该工程的期限余量，在此范围内的适度延误不会影响整个工程的工期。
 *
 *关键路径求解的过程
 *（1）对图中顶点进行排序，在排序过程中按拓扑序列求出每个事件的最早发生时间ve(i)。
 *（2）按逆拓扑序列求出每个事件的最迟发生时间vl(i)。
 *（3）求出每个活动ai的最早开始时间e(i)。
 *（4）求出每个活动ai的最晚开始时间l(i)。
 *（5）找出e(i)=l(i)的活动ai，即为关键活动。
 * 由关键活动形成的由源点到汇点的每一条路径就是关键路径，关键路径有可能不止一条。
 *
 */
public class AOEMap {


    public static void criticalPath(int[][] arcs){
        int m = Integer.MAX_VALUE;
        int length = arcs.length;
        //（1）一维数组ve[i]：事件vi的最早发生时间。
        int[] ve = new int[length];
        for(int i =0;i<length;i++){
            ve[i] = 0;
        }
        //（2）一维数组vl[i]：事件vi的最迟发生时间。
        int[] vl = new int[length];
        for(int i =0;i<length;i++){
            vl[i] = m;
        }
        //（3）一维数组topo[i]：记录拓扑序列的顶点序号。
        int[] topo = AOVMap.TopoligicalSort(arcs);

        //计算化Ve[i],对应topo的元素顶点
        //ve(0)=0
        //ve(i)=Max{ve(k)+wk,i} <vk，vi>∈T，1≤i≤n−1
        ve[0] = 0;
        for(int i= 0;i<length-1;i++){
            for(int j=i+1;j<length;j++){
                if((arcs[i][j] < m) && (ve[i] + arcs[i][j] > ve[j])){
                    ve[j] = ve[i] + arcs[i][j];
                }
            }
        }
        //计算vl(n−1)=ve(n−1)
        //vl(i)=Min{vl(k)−wi,k} <vi，vk>∈S，0≤i≤n−2
        vl[length-1] = ve[length-1];
        for(int i=length-1;i>0;i--){
            for(int j=i-1;j>=0;j--){
                if((vl[i] >= arcs[j][i]) && (vl[i] - arcs[j][i] < vl[j])){
                    vl[j] = vl[i] - arcs[j][i];
                }
            }
        }
        //比较ve[i] 与 vl[i],相同的为关键路径
        System.out.println(Arrays.toString(ve));
        System.out.println(Arrays.toString(vl));
        for(int i=0;i<length;i++){
            if(ve[i] == vl[i]){
                System.out.println(i);
            }
        }
        //关键活动，即关键路线；同一组关键路径，在图中可能拥有多条关键活动；其中有些活动优先级并不是最高
        //判断方法，对关键路径每个顶点，V[i]  V[j]  如果 Ve[i] = Vl[j] - arce[i][j];即为关键活动（此值可能为小于）
        //Vl[j] - arce[i][j] - Ve[i] 为关键
    }


    public static void main(String[] args) {
        int m = Integer.MAX_VALUE;
        int[][] arcs = {
                {0,6,4,5,m,m,m,m,m},
                {m,m,m,m,1,m,m,m,m},
                {m,m,m,m,1,m,m,m,m},
                {m,m,m,m,m,2,m,m,m},
                {m,m,m,m,m,m,9,7,m},
                {m,m,m,m,m,m,m,4,m},
                {m,m,m,m,m,m,m,m,2},
                {m,m,m,m,m,m,m,m,4},
                {m,m,m,m,m,m,m,m,m},

        };
        criticalPath(arcs);

    }
}
