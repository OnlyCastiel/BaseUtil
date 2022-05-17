package com.train.bianchengzhimei;


import org.bytedeco.javacpp.lept;

/**
 * 微软亚洲研究院所在的希格玛大厦一共有6部电梯。在高峰时间，每层都有人上下，电梯在每层都停。
 * 实习生小飞常常会被每层都停的电梯弄得很不耐烦，于是他提出了这样一个办法：
 * 由于楼层并不太高，那么在繁忙的上下班时间，每次电梯从一层往上走时，我们只允许电梯停在其中的某一层。
 * 所有的乘客都从一楼上电梯，到达某层楼后，电梯停下来，所有乘客再从这里爬楼梯到自己的目的层。
 * 在一楼的时候，每个乘客选择自己的目的层，电梯则自动计算出应停的楼层。
 *
 * 问：电梯停在哪一层楼，能够保证这次乘坐电梯的所有乘客爬楼梯的层数之和最少。
 *
 *
 * 核心思路：
 * 方案一：如果选择N楼停，什么样的情况下，在下一楼停的优势更大；
 * 假设对于当前停靠楼层N,有N1人需要下楼，N2人刚好在这个楼层下，N3人需要上楼
 * 对于电梯不去当前楼层，选择上一楼，那么总体爬的楼层变化为  N1+N2-N3;
 * 不难发现，当N1+N2-N3 < 0 时，电梯应该上一楼；
 * 此时我们可以选择遍历整个数组，维护 N1,N2,N3 的值;然后遍历数组，一直到电梯再上一层楼会导致 N1+N2-N3 >=0 时，目标楼层就找到了
 *
 *
 * 方案二：不难发现对于电梯停靠楼层x,与乘客需要爬的总楼层y之间的关系；呈现一个先递减再递增的抛物线
 * 在抛物线的最小值，对应的x为电梯停靠楼层的最优解；此时我们会发现，所有乘客数量的中间数，刚好在这个楼层；
 * 存在某种情况，连续某几个楼层，所有乘客爬的总楼层y为同一值；比如 220022  在第2层到第5层之间停靠，所有乘客爬的楼层总数相同
 */
public class ElevatorPlan {



    /**
     * 计算最佳楼层
     * @param nums 每个楼层下的人数
     * @return
     */
    private int getFloor(int[] nums){
        //从第一楼开始遍历
        int finalFloor = 0;
        //初始化N1、N2、N3
        int N1 = 0;
        int N2 = nums[0];
        int N3 = 0;
        for(int i = 1;i<nums.length;i++){//从第二层楼开始计算
            N3 += nums[i];
        }


        //判断电梯是否可以在下一层停
        for(int i = 0;i<nums.length;i++){
            if(N1+N2 < N3){
                finalFloor ++;
                N1 += N2; //本楼人需要爬一层
                N2 = nums[i+1]; //下一楼的人
                N3 -= nums[i+1]; //原本楼上的人，需要减去本楼
            }else{
                break;
            }
        }
        //楼层对应数组+1
        return finalFloor+1;
    }

    /**
     * 进阶思考，假设上楼需要耗费能量upEnergy，下楼需要耗费能量downEnergy
     * 如何确定停靠楼层使得所有乘客耗费的总能量最小
     *
     * 核心思路:在原有基础上，楼层数量判断，变成了楼层数量*能量
     * 维护 N1,N2,N3 的值; 不再是总楼层数，而是能量值
     * @param args
     */
    private int getFloorByEnergy(int[] nums,int upEnergy,int downEnergy){
        //从第一楼开始遍历
        int finalFloor = 0;
        //初始化N1、N2、N3
        int N1 = 0;
        int N2 = nums[0];
        int N3 = 0;
        for(int i = 1;i<nums.length;i++){//从第二层楼开始计算
            N3 += nums[i] * downEnergy;
        }


        //判断电梯是否可以在下一层停
        for(int i = 0;i<nums.length;i++){
            if(N1+N2 * upEnergy < N3){
                finalFloor ++;
                N1 += N2 * upEnergy; //本楼人需要爬一层
                N2 = nums[i+1]; //下一楼的人
                N3 -= nums[i+1] * downEnergy; //原本楼上的人，需要减去本楼
            }else{
                break;
            }
        }
        //楼层对应数组+1
        return finalFloor+1;
    }

    /**
     * 进阶思考：如果电梯可以最多K个楼层，而不仅仅是一个楼层，如何求解
     *
     * 核心思路：不难发现，对于停靠1个楼层问题，
     * 最后选择的楼层为整个数组之和totalNum 中位数存在的楼层，该楼层；
     *
     * 对于可以停靠多个楼层的问题，则可以选择找  totalNum/K 的位置
     * 对于该思路需要进行推导：例如：{20,0,0,0,0,1} 这种情况，当K为2时，都会选择第一层停靠
     * 所以需要增加逻辑，当有人的楼层 j <= k 时，直接输出这些楼层
     *
     * 如何推导这种思路为最优解：遗留问题
     *
     *
     * 动态规划思路：
     *
     *
     *
     * @param args
     */
    private int getMultiPleFloor(int[] nums,int k){
        //从第一楼开始遍历
        int finalFloor = 0;
        //初始化N1、N2、N3
        int N1 = 0;
        int N2 = nums[0];
        int N3 = 0;
        for(int i = 1;i<nums.length;i++){//从第二层楼开始计算
            N3 += nums[i];
        }


        //判断电梯是否可以在下一层停
        for(int i = 0;i<nums.length;i++){
            if(N1+N2 < N3){
                finalFloor ++;
                N1 += N2; //本楼人需要爬一层
                N2 = nums[i+1]; //下一楼的人
                N3 -= nums[i+1]; //原本楼上的人，需要减去本楼
            }else{
                break;
            }
        }
        //楼层对应数组+1
        return finalFloor+1;
    }

    public static void main(String[] args) {
        ElevatorPlan plan = new ElevatorPlan();
        int[] nums = {2,2,20};
        System.out.println(plan.getFloor(nums));
        System.out.println(plan.getFloorByEnergy(nums,5,1));
    }

}
