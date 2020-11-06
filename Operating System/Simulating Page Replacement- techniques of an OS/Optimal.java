import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Optimal {

    static int optimal(int pages[], int n, int capacity) {
        int frame[] = new int[capacity];

        HashSet<Integer> buffer = new HashSet<>(capacity);

        for(int i = 0; i < capacity; i++)frame[i] = -1;
        // To store the pages in FIFO manner


        // Start from initial page
        int page_faults = 0;
        for (int i = 0; i < n; i++) {
            if (page_faults < capacity) {
                //7 0 1 2 0 3 0 4 2 3 0 3 0 3 2 1 2 0 1 7 0 1
                if (!buffer.contains(pages[i])) {
                    frame[i] = pages[i];
                    page_faults++;
                    buffer.add(pages[i]);
                }

            } else {
                    if (!buffer.contains(pages[i])) {
                        //7 0 1 2 0 3 0 4 2 3 0 3 0 3 2 1 2 0 1 7 0 1
                        int indexes[] = new int[n];
                        for (int k = 0; k < capacity; k++) {
                            for (int l = i + 1; l < n; l++) {
                                if (frame[k] == pages[l]) {
                                    indexes[k] = l;
                                    break;
                                }
                            }

                        }
                        int indxOfMax = 0;
                        int frameIndx = 0;
                        for(int l = 0; l < capacity; l++){
                            if(indexes[l] == 0) indexes[l] = 5000;
                            if(indexes[l] > indxOfMax) {
                                indxOfMax = indexes[l];
                                frameIndx = l;
                            }
                            buffer.remove(frame[frameIndx]);
                        }
                        frame[frameIndx] = pages[i];
                        page_faults++;
                        buffer.add(pages[i]);
                    }
                }

            }

            return page_faults;
    }

    static int faluteRateOptimal(int totalFalute, int numberOfpref){
        return (int) Math.round((totalFalute*100.0)/numberOfpref);
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n_p,n_f,n_r;


        System.out.print("Number of pages: ");
        n_p = sc.nextInt();
        //  System.out.print("Number of pages = "+n_p);

        System.out.print("Number of pages reference: ");
        n_r = sc.nextInt();
        // System.out.print("Number of pages reference = "+n_r);

        int r_s[] = new int[n_r];

        System.out.print("Reference string: ");
        for(int i = 0; i < n_r ; i++) r_s[i] = sc.nextInt();
        // System.out.print("Reference string: ---");
        //for(int i = 0; i < n_r; i++) System.out.println(r_s[i]);

        System.out.print("Number of Memory page frame: ");
        n_f = sc.nextInt();
        // System.out.print("Number of Memory page frame = "+n_f);



        int m = optimal(r_s,n_r,n_f);
        System.out.println("Number of page fault using Optimal Page replacement Algorithm: "+m);
        System.out.println("Page Fault Rate:"+faluteRateOptimal(m,n_r)+"%");

    }

}

