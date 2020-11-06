import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BankersAlgo {

    public static Queue<Integer> safeSequence = new LinkedList<>();
    public static boolean[] visited = new boolean[100];

    public static boolean bankerAlgo(int[][]max, int[][]alloc, int[][] need, int[] resource,int[] avail, int numberOfResource, int numberOfProcess ){
        for(int i = 1; i <= numberOfProcess; i++){
            boolean flag = true;
            if(visited[i]) continue;

            for(int j = 1; j <= numberOfResource; j++){
                if(need[i][j] > avail[j]) flag = false;
            }
            if(flag){
                // we have available resources
                visited[i] = true;
                safeSequence.add(i);

                for(int k = 1; k <= numberOfResource; k++)
                    avail[k] += alloc[i][k];
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int i = 0;;){
            break;
        }

        int numberOfProcess;
        System.out.print("Enter number of process: ");
        numberOfProcess = sc.nextInt();

        int numberOfResource;
        System.out.print("Enter number of resource: ");
        numberOfResource = sc.nextInt();
        int[] resources = new int[numberOfResource+1];
        int[][] max = new int[numberOfProcess+1][numberOfResource+1];
        int[][] alloc = new int[numberOfProcess+1][numberOfResource+1];
        int[][] need = new int[numberOfProcess+1][numberOfResource+1];
        int[] avail = new int[numberOfResource+1];


        System.out.println();

        for(int i = 1; i<= numberOfProcess; i++){
            System.out.println("Process "+i);
            // max --------------------------
            for(int j = 1; j <= numberOfResource; j++){
                System.out.print("Maximum value of resource "+j+": ");
                max[i][j] = sc.nextInt();
            }
            // allocation -----------------------
            for(int j = 1; j <= numberOfResource; j++){
                System.out.print("Allocated from resource "+j+": ");
                alloc[i][j] = sc.nextInt();
            }


            for(int j = 1; j <= numberOfResource; j++){
                need[i][j] = max[i][j] - alloc[i][j];
            }

            System.out.println();
            System.out.println();

        }


        for(int i = 1; i <= numberOfResource; i++){
            System.out.print("Enter total value of resource "+i+":  ");
            resources[i] = sc.nextInt();
        }
        for(int i = 1; i <= numberOfResource; i++){
            int sum = 0;
            for(int j = 1; j <= numberOfProcess; j++){
                sum += alloc[j][i];
            }
            avail[i] = resources[i] - sum;
        }

        // ---------------------------- main algo --------------
        int totalSafeProcess = 0;

        while(totalSafeProcess < numberOfProcess){
            boolean b = bankerAlgo(max,alloc,need,resources,avail,numberOfResource,numberOfProcess);
            if(b) totalSafeProcess++;
            else{
                System.out.println("Donse not have any safe sequence");
                System.exit(0);
            }
        }

        System.out.println();
        System.out.println();

        System.out.print("The System is currently in safe state and < ");
        int n = safeSequence.size();
        for(int i = 1; i <= n; i++){
            System.out.print("P"+safeSequence.poll()+" ");
        }
        System.out.print("> is the safe sequence");


        System.exit(0);

    }
}
