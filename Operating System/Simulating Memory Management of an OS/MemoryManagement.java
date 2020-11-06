package Session9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MemoryManagement {

    public static void printMatrix(int[][] matrix, int[] memoryHoles, int[] memoryRequests){
        boolean flag = false;
        int extFragmentation = 0;
        boolean isExtFragmentation = false;
        for(int i = 0; i < memoryHoles.length; i++){
            for(int j = 0; j < memoryRequests.length; j++){
                if(matrix[j][i] == -1){
                    flag = true;
                    if(i == 0) System.out.print("CAN'T BE\t\t");
                    else if(i == 1) System.out.print("ALLOCATED\t\t");
                    else if(i == 2) System.out.print("Ext.Frag\t\t");
                    else if(i == 3){
                        extFragmentation = 0;
                        for(int k = 0; k < memoryHoles.length; k++)
                            extFragmentation += matrix[j-1][k];
                        if(extFragmentation >= memoryRequests[j]) {
                            isExtFragmentation = true;
                            System.out.print(extFragmentation);
                        }
                        System.out.print("\t\t\t\t");
                    } else{
                        System.out.print("\t\t\t\t");
                    }
                }else if(matrix[j][i] == 0 && flag)
                    System.out.print(" ");
                else{
                    System.out.print(matrix[j][i]+"\t\t\t\t");
                }
            }
            System.out.println();
        }
        if(!isExtFragmentation) System.out.println("NO EXTERNAL FRAGMENTATION");
        else System.out.println("EXTERNAL FRAGMENTATION: "+extFragmentation);
    }


    // implementing First fit -----------------------------------------
    public static void firstFit(int[] memoryHoles, int[] memoryRequests){
        System.out.println("First Fit");
        int[][] matrix = new int[memoryRequests.length][memoryHoles.length];
        int[] memoryBlock = new int[memoryHoles.length];


        System.arraycopy(memoryHoles, 0, memoryBlock, 0, memoryHoles.length);

        System.out.println("Step by step memory allocation situation in First Fit");
        for(int i = 0; i < memoryRequests.length; i++) {
            boolean isServed = false;
            for (int j = 0; j < memoryHoles.length; j++) {
                if (memoryBlock[j] >= memoryRequests[i]) {
                    memoryBlock[j] -= memoryRequests[i];
                    isServed = true;
                    break;
                }
            }
            if (isServed) {
                System.arraycopy(memoryBlock, 0, matrix[i], 0, memoryHoles.length);
            } else {
                for(int j = 0; j < memoryHoles.length; j++){
                    matrix[i][j] = -1;
                }
                break;
            }
        }

        // printing matrix and calculate external fragmentation
        for (int memoryRequest : memoryRequests) System.out.print(memoryRequest + "\t\t\t\t");
        System.out.println();
        printMatrix(matrix,memoryHoles,memoryRequests);

    }

    // first fit end -----------------------------------------------------------



    // implementing best fit ---------------------------------------------------
    public static void bestFit(int[] memoryHoles, int[] memoryRequests){
        System.out.println("Best Fit");
        int[][] matrix = new int[memoryRequests.length][memoryHoles.length];
        int[] memoryBlock = new int[memoryHoles.length];
        int difference;

        System.arraycopy(memoryHoles, 0, memoryBlock, 0, memoryHoles.length);

        System.out.println("Step by step memory allocation situation in Best Fit");
        for(int i = 0; i < memoryRequests.length; i++){
            boolean isServed = false;
            int minIndex = 0;
            difference = 9999;
            for(int j = 0; j < memoryHoles.length; j++){
                if((memoryBlock[j] >= memoryRequests[i]) && ((memoryBlock[j] - memoryRequests[i]) < difference)){
                    minIndex = j;
                    isServed = true;
                    difference = (memoryBlock[j] - memoryRequests[i]);
                }
            }
            if(isServed) {
                memoryBlock[minIndex] -= memoryRequests[i];
                System.arraycopy(memoryBlock, 0, matrix[i], 0, memoryHoles.length);
           }else{
                    for(int j = 0; j < memoryHoles.length; j++){
                        matrix[i][j] = -1;
                    }
                    break;
           }
        }

//50 200 70 115 15
        // 100 10 35 15 23 6 25 55 190 40
        // printing matrix and calculate external fragmentation
        for (int memoryRequest : memoryRequests) System.out.print(memoryRequest + "\t\t\t\t");
        System.out.println();
        printMatrix(matrix,memoryHoles,memoryRequests);
    }

    // best fit end

    // implementing worst fit ---------------------------------------------------
    public static void worstFit(int[] memoryHoles, int[] memoryRequests){
        System.out.println("Worst Fit");
        int[][] matrix = new int[memoryRequests.length][memoryHoles.length];
        int[] memoryBlock = new int[memoryHoles.length];
        int difference;

        System.arraycopy(memoryHoles, 0, memoryBlock, 0, memoryHoles.length);

        System.out.println("Step by step memory allocation situation in Worst Fit");
        for(int i = 0; i < memoryRequests.length; i++){
            boolean isServed = false;
            int maxIndex = 0;
            difference = -999;
            for(int j = 0; j < memoryHoles.length; j++){
                if((memoryBlock[j] >= memoryRequests[i]) && ((memoryBlock[j] - memoryRequests[i]) > difference)){
                    maxIndex = j;
                    isServed = true;
                    difference = (memoryBlock[j] - memoryRequests[i]);
                }
            }
            if(isServed){
                memoryBlock[maxIndex] -= memoryRequests[i];
                System.arraycopy(memoryBlock, 0, matrix[i], 0, memoryHoles.length);
            }else{
                for(int j = 0; j < memoryHoles.length; j++){
                    matrix[i][j] = -1;
                }
                break;
            }
        }


        // printing matrix and calculate external fragmentation

        for (int memoryRequest : memoryRequests) System.out.print(memoryRequest + "\t\t\t\t");
        System.out.println();
        printMatrix(matrix,memoryHoles,memoryRequests);
    }

    // worst fit end


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Memory Holes : ");
        String[] mh = br.readLine().split(" ");

        System.out.print("Memory Requests : ");
        String[] mr = br.readLine().split(" ");


        int[] m_h = new int[mh.length];
        for(int i=0;i<mh.length;i++)
        {
            m_h[i]=Integer.parseInt(mh[i]);
        }

        int[] m_r = new int[mr.length];
        for(int i=0;i<mr.length;i++)
        {
            m_r[i]=Integer.parseInt(mr[i]);
        }


        firstFit(m_h,m_r);
        bestFit(m_h,m_r);
        worstFit(m_h,m_r);

    }

}


// 50 200 70 115 15   memory holes

// 100 10 35 15 23 6 25 55 88 40   memory request

// 100 10 35 15 23 6 25 55 190 40