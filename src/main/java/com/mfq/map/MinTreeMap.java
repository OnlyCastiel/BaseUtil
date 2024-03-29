package com.mfq.map;

/**
 * 最小生成树
 * 在一个连通网的所有生成树中，各边的代价之和最小的那棵生成树称为该连通网的最小代价生成树（Minimum CostSpanning Tree），简称为最小生成树。
 * 实际应用场景：假设要在n个城市之间建立通信联络网，则连通n个城市只需要n−1条线路。这时，自然会考虑这样一个问题，如何在最节省经费的前提下建立这个通信网。
 *
 * 1．普里姆算法
 * （1）普里姆算法的构造过程假设N=(V, E)是连通网，TE是N上最小生成树中边的集合。
 * ① U={u0}(u0∈V)，TE={}。
 * ② 在所有u∈U，v∈V−U的边(u, v)∈E中找一条权值最小的边(u0, v0)并入集合TE，同时v0并入U。
 * ③ 重复②，直至U=V为止。
 * 此时TE中必有n−1条边，则T=(V, TE)为N的最小生成树。
 *
 * 可以看出，普里姆算法逐步增加U中的顶点，可称为“加点法”。因此适用于求稠密网的最小生成树。
 *
 *
 * 2．克鲁斯卡尔算法
 * （1）克鲁斯卡尔算法的构造过程假设连通网N=(V, E)，将N中的边按权值从小到大的顺序排列。
 * ① 初始状态为只有n个顶点而无边的非连通图T=(V, {})，图中每个顶点自成一个连通分量。
 * ② 在E中选择权值最小的边，若该边依附的顶点落在T中不同的连通分量上（即不形成回路），则将此边加入到T中，否则舍去此边而选择下一条权值最小的边。
 * ③ 重复②，直至T中所有顶点都在同一连通分量上为止。
 *
 *由此，克鲁斯卡尔算法的时间复杂度为O(elog2e)，与网中的边数有关，与普里姆算法相比，克鲁斯卡尔算法更适合于求稀疏网的最小生成树。
 *
 */
public class MinTreeMap {
}
