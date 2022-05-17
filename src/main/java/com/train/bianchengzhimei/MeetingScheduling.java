package com.train.bianchengzhimei;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 在校园招聘的季节里，为了能让学生们更好地了解微软亚洲研究院各研究组的情况，
 * HR部门计划为每一个研究组举办一次见面会，让各个研究组的员工能跟学生相互了解和交流（如图1-14所示）。
 * 已知有n位学生，他们分别对m个研究组中的若干个感兴趣。为了满足所有学生的要求，
 * HR希望每个学生都能参加自己感兴趣的所有见面会。如果每个见面会的时间为t，那么，如何安排才能够使得所有见面会的总时间最短？
 *
 *
 * 思路：对于此问题，通过记录 meeting[m][n] 表示，对所有m研究小组,学生n是否感兴趣
 * 最终结果，要求在不存在学生同时对x个研究组感兴趣的情况下，同时安排尽可能多的研究组；进行排期；
 *
 *
 * 贪心算法：
 * 1、依次选择课程，每次同时安排与改课程不冲突的所有课程同时进行，安排课程时需要确保改课程不与改时段已安排课程冲突
 * 冲突判断方法： flag = A*B*C 矩阵乘法
 * 2、程序出口，安排完毕所有课程
 * 3、程序返回，课程安排顺序
 *
 * 推导：每一个课程肯定是需要举行的，那么对于它举行的过程中，最优解就是，安排与其不冲突的所有课程降低后续课程的安排难度
 *
 *
 * 图的深度优先算法：回溯
 * 1、将每个课程作为一个节点
 * 2、对于每一个学生，将其感兴趣的的课程，两两连接起来；（结果大概率是个非连同图）
 * 3、通过深度优先算法，遍历图，对于一个特定节点，判断其所有相邻节点的颜色，选择一个未使用过的颜色进行标记
 * 注意：可能是一个非连通图
 *
 * 两个思路的比较：基本类似，
 * 思路1是优先安排不互斥的课程一起进行，
 * 思路2 则是将互斥课程逐一安排；
 */
public class MeetingScheduling {



    private void scheduling(int[][] meeting){
        //新建一个数组，标记会议是否已经被安排
        int[] meetSch = new int[meeting.length];


        for(int i=0;i<meetSch.length;i++){//遍历安排每一个课程
            if(meetSch[i] == 1){//判断是否已安排
                continue;
            }
            //存储可以一起安排的课程
            List<Integer> oneTimeSch = new ArrayList<>();
            oneTimeSch.add(i);
            int[] sum = meeting[i];
            for(int j=i+1;j<meetSch.length;j++){
                if(meetSch[j] == 1){//判断是否已安排
                    continue;
                }
                int[] temp = sum;
                boolean falg = true;//标记是否冲突
                for(int k=0;k<temp.length;k++){
                    temp[k] = temp[k] + meeting[j][k];
                    if(temp[k] > 1){
                        falg = false;//存在冲突
                        break;
                    }
                }
                if(falg){//无冲突
                    sum = temp;//记录累计值
                    oneTimeSch.add(j);//记录同时举行的会议
                    meetSch[j] = 1;//标记已安排
                }
            }
            System.out.println(Arrays.toString(oneTimeSch.toArray()));
            oneTimeSch.clear();
        }
    }


    /**
     * 根据课程学生的记录，转换为图的邻接矩阵表达
     *
     */
    public int[][] converMap(int[][] meeting){
        int[][] meetMap = new int[meeting.length][meeting.length];
        for(int i=0;i<meeting[0].length;i++){
            List<Integer> sameTimeMeeting = new ArrayList<>();
            for(int j=0;j<meeting.length;j++){
                if(meeting[j][i] == 1){
                    sameTimeMeeting.add(j);
                }
            }
            //遍历完成之后
            if(sameTimeMeeting.size() >0){
                //图进行两两相连
                for(int m=0;m<sameTimeMeeting.size();m++){
                    for(int n=m+1;n<sameTimeMeeting.size();n++){
                        meetMap[sameTimeMeeting.get(m)][sameTimeMeeting.get(n)] = 1;
                        meetMap[sameTimeMeeting.get(n)][sameTimeMeeting.get(m)] = 1;
                    }
                }
            }
        }
        return meetMap;
    }

    /**
     * 根据图的邻接矩阵来进行最小着色
     * @param meetMap
     */
    public int[] schedulingByMap(int[][] meetMap){
        //初始化排序，index0表示是否已排，index1表示颜色；
        int[][] meetSch = new int[meetMap.length][2];
        //深度优先搜索递归
        deepSerchAndScheduling(meetMap,meetSch,0);

        int[] meetSchResult = new int[meetMap.length];
        for(int i =0;i< meetMap.length;i++){
            meetSchResult[i] = meetSch[i][1];
        }
        return meetSchResult;
    }

    /**
     *
     * @param meetMap 图的邻接矩阵
     * @param meetSch 会议的安排 index0表示是否已排，index1表示颜色；
     * @param index 当前遍历的图
     */
    public void deepSerchAndScheduling(int[][] meetMap,int[][] meetSch,int index){
        //出口，当前节点已在别的分支上色
        if(meetSch[index][0] == 1){
            return;
        }
        //存放已被使用的颜色
        List<Integer> colorList = new ArrayList<>();
        //记录所有未遍历的子节点
        List<Integer> indexNext = new ArrayList<>();
        //查找所有相邻节点（子节点）的颜色，找到一个未被使用的颜色
        for(int i =0 ;i<meetMap.length;i++){
            //存在路径（互斥关系），并且已排
            if(meetMap[index][i] == 1 && meetSch[i][0] == 1){
                //记录颜色
                colorList.add(meetSch[index][1]);
            }
            if(index !=i && meetSch[i][0] == 0){//可能非连通图
                //记录未被遍历
                indexNext.add(i);
            }
        }
        //如何从一个数组中，找到最小的未被使用的数字 0为最小
        for(int i=0;i<meetMap.length;i++){
            if(!colorList.contains(i)){
                meetSch[index][0] = 1; //标记已排
                meetSch[index][1] = i; //标记颜色
                break;
            }
        }
        //深度递归,每一个未着色的子节点
        for(Integer newIndex : indexNext){
            deepSerchAndScheduling(meetMap,meetSch,newIndex);
        }
        //出口,当前节点所有相邻节点都已上色完毕
        return;
    }


    /**
     * 着色方案优化：当最小着色数远小于图的顶点数时，该算法复杂度远低于深度优先搜索法
     * 此思路类似于，贪心算法；
     * @param args
     */




    public static void main(String[] args) {
        int[] A = {1,1,0,1}; //标记研究组对每个学生是否感兴趣
        int[] B = {0,0,1,0};
        int[] C = {0,1,1,0};
        int[][] meeting = {A,B,C};


        MeetingScheduling meetingScheduling = new MeetingScheduling();
        int[][] meetMap = meetingScheduling.converMap(meeting);
        for(int i =0;i< meetMap.length;i++ ){
            System.out.println(Arrays.toString(meetMap[i]));
        }
        int[] resultSch = meetingScheduling.schedulingByMap(meetMap);
        System.out.println(Arrays.toString(resultSch));

        meetingScheduling.scheduling(meeting);


    }




}
