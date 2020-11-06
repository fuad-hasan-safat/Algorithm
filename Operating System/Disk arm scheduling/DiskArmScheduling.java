import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DiskArmScheduling {

    static  void  FCFS(int CylinderRequests[], int CurrentHeadPos, int numberOfRequest){
        Queue<Integer> Cylinder_Serving_Order = new LinkedList<>();

        int totalCylinderMovement = 0;

        Cylinder_Serving_Order.add(CurrentHeadPos);

        for(int i = 0; i < numberOfRequest; i++){
            Cylinder_Serving_Order.add(CylinderRequests[i]);

            totalCylinderMovement += Math.abs(CurrentHeadPos - CylinderRequests[i]);
            CurrentHeadPos = CylinderRequests[i];
        }

        // -------------------- output ----------------

        System.out.print("Cylinder Serving Order: ");
        int l = Cylinder_Serving_Order.size();
        for(int i = 0; i < l; i++){
            int val = Cylinder_Serving_Order.poll();
            System.out.print(val);

            if(i < (numberOfRequest )){
                System.out.print(" -> ");
            }
        }
        System.out.println();

        System.out.println("Total Cylinder movement:" + totalCylinderMovement);

        System.out.println();
    }


//--------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------



    static void C_SCAN(int CylinderRequests[], int initHeadPos, int numberOfRequest, int numOfHeads) {
        Queue<Integer> Cylinder_Serving_Order = new LinkedList<>();

        int totalCylinderMovement = 0;
        int currentHeadPos = initHeadPos;

        Cylinder_Serving_Order.add(initHeadPos);
        int orderedRequest[] = new int[numberOfRequest + 2];
        orderedRequest[0] = 0;
        orderedRequest[numberOfRequest + 1] = numOfHeads - 1;

        for (int i = 1; i < numberOfRequest; i++) {
            orderedRequest[i] = CylinderRequests[i];
        }

        Arrays.sort(orderedRequest);

        // implementing CSCAn
        for (int i = 1; i < numberOfRequest + 2; i++) {
            if (orderedRequest[i] > initHeadPos) {
                Cylinder_Serving_Order.add(orderedRequest[i]);
                totalCylinderMovement += Math.abs(currentHeadPos - orderedRequest[i]);
                currentHeadPos = orderedRequest[i];
            }
        }

        for (int i = 1; orderedRequest[i] < initHeadPos; i++) {
            if (orderedRequest[i] < initHeadPos)
                Cylinder_Serving_Order.add(orderedRequest[i]);
            totalCylinderMovement += Math.abs(currentHeadPos - orderedRequest[i]);
            currentHeadPos = orderedRequest[i];
        }


        // -------------------- output ----------------

        System.out.print("Cylinder Serving Order: ");
        int l = Cylinder_Serving_Order.size();
        for (int i = 0; i < l; i++) {
            int val = Cylinder_Serving_Order.poll();
            System.out.print(val);

            if (i < (l - 1)) {
                System.out.print(" -> ");
            }
        }
        System.out.println();

        System.out.println("Total Cylinder movement:" + totalCylinderMovement);

        System.out.println();
    }








    static void SSTF(int CylinderRequests[], int initHeadPos, int numberOfRequest, int numOfHeads) {
        Queue<Integer> Cylinder_Serving_Order = new LinkedList<>();

        int totalCylinderMovement = 0;
        int currentHeadPos = initHeadPos;

        Cylinder_Serving_Order.add(initHeadPos);

        int orderedRequest[] = new int[numberOfRequest + 1];

        orderedRequest[0] = currentHeadPos;

        for (int i = 1; i <= numberOfRequest; i++) {
            orderedRequest[i] = CylinderRequests[i-1];
        }

        Arrays.sort(orderedRequest);

        // implementing SSTF

        int leftIndex = 0, rightIndex = 0;

        for (int i = 0; i < numberOfRequest; i++) {
            if (initHeadPos == orderedRequest[i]) {
                if (i == 0) {
                    rightIndex = 1;
                    leftIndex = -1;
                } else if (i == numberOfRequest - 1) {
                    rightIndex = -1;
                    leftIndex = numberOfRequest - 2;
                } else {
                    rightIndex = i + 1;
                    leftIndex = i - 1;
                }

                currentHeadPos = orderedRequest[i];
            }
        }

        if (leftIndex == -1) {
            for (int i = rightIndex; i <= numberOfRequest; i++) Cylinder_Serving_Order.add(orderedRequest[i]);
        } else if (rightIndex == -1) {
            for (int i = leftIndex; i >= 0; i--) Cylinder_Serving_Order.add(orderedRequest[i]);
        } else{
            for (int i = 0; i < numberOfRequest + 1; i++) {


                if(leftIndex == -1 || rightIndex == -1){
                    if(leftIndex == -1) while(rightIndex<numberOfRequest+1){
                        totalCylinderMovement += Math.abs((currentHeadPos - orderedRequest[rightIndex]));
                        Cylinder_Serving_Order.add(orderedRequest[rightIndex++]);
                    }
                    else while(leftIndex>=0) {
                        totalCylinderMovement += Math.abs((currentHeadPos - orderedRequest[leftIndex]));
                        Cylinder_Serving_Order.add(orderedRequest[leftIndex--]);
                    }
                }
                else {
                    int rightDiffer = Math.abs((currentHeadPos - orderedRequest[rightIndex]));
                    int leftDiffer = Math.abs((currentHeadPos - orderedRequest[leftIndex]));

                    if (rightDiffer <= leftDiffer) {
                        totalCylinderMovement += rightDiffer;
                        Cylinder_Serving_Order.add(orderedRequest[rightIndex]);
                        currentHeadPos = orderedRequest[rightIndex];
                        rightIndex++;
                    } else if (rightDiffer > leftDiffer) {
                        totalCylinderMovement += leftDiffer;
                        Cylinder_Serving_Order.add(orderedRequest[leftIndex]);
                        currentHeadPos = orderedRequest[leftIndex];
                        leftIndex--;
                    }
                }
            }
        }




        // -------------------- output ----------------

        System.out.print("Cylinder Serving Order: ");
        int l = Cylinder_Serving_Order.size();
        for(int i = 0; i < l; i++){
            int val = Cylinder_Serving_Order.poll();
            System.out.print(val);

            if(i < (l -1 )){
                System.out.print(" -> ");
            }
        }
        System.out.println();

        System.out.println("Total Cylinder movement:" + totalCylinderMovement);

        System.out.println();


    }


//---------------------------------------------------------------------------------------------
   // ----------------------------------------------------------------------------
    //---------------------------------------------------------------------------


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n_h;
       // String s = new String();

        int c_h_p;

        System.out.print("Number of heads: ");
        n_h = sc.nextInt();


        System.out.print("Cylinder requests: ");
        String[] s = br.readLine().split(" ");
        int l = s.length;
        int[] c_r = new int[l];
        for(int i=0;i<l;i++)
        {
            c_r[i]=Integer.parseInt(s[i]);
        }

        System.out.print("Current head position: ");
        c_h_p = sc.nextInt();

        System.out.println();

        FCFS(c_r,c_h_p,l);
        C_SCAN(c_r,c_h_p,l,n_h);

        SSTF(c_r,c_h_p,l,n_h);

    }
}
/// 98 183 37 122 14 124 65 67
