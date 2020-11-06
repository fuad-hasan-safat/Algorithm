import java.util.*;

public class PageReplacement {

    static int fifo(int pages[], int n, int capacity) {
        HashSet<Integer> frame = new HashSet<>(capacity);
        // To store the pages in FIFO manner
        Queue<Integer> indexes = new LinkedList<>();

        // Start from initial page
        int page_faults = 0;
        for (int i = 0; i < n; i++) {
            if (frame.size() < capacity) {

                if (!frame.contains(pages[i])) {
                    frame.add(pages[i]);


                    page_faults++;


                   indexes.add(pages[i]);
                }
            } else {

                if (!frame.contains(pages[i])) {
                    //Pop the first page from the queue
                    int val = indexes.peek();

                    indexes.poll();

                    frame.remove(val);

                    frame.add(pages[i]);

                    // push the current page into
                    // the queue
                    indexes.add(pages[i]);

                    // Increment page faults
                    page_faults++;
                }
            }
        }

        return page_faults;
    }

    // -------------------------------------- optimal ------------------------

    static int optimal(int pages[], int n, int capacity) {
        int frame[] = new int[capacity];

        for(int i = 0; i < capacity; i++)frame[i] = -1;
        // To store the pages in FIFO manner


        // Start from initial page
        int page_faults = 0;
        for (int i = 0; i < n; i++) {
            if (page_faults < capacity) {
                //7 0 1 2 0 3 0 4 2 3 0 3 0 3 2 1 2 0 1 7 0 1
                boolean t = true;
                for (int j = 0; j < capacity; j++) {
                    if (frame[j] == pages[i]) {
                        t = false;
                    }
                }
                if (t) {
                    frame[i] = pages[i];
                    page_faults++;
                }

            } else {
                boolean t = true;
                for (int j = 0; j < capacity; j++) {
                    if (frame[j] == pages[i]) {
                        t = false;
                    }
                }
                if (t) {
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
                    }
                    frame[frameIndx] = pages[i];
                    page_faults++;
                }
            }

        }

        return page_faults;
    }



    // -------------------------------------- optimal ------------------------

    static int lru(int pages[], int n, int capacity) {
        int frame[] = new int[capacity];

        for(int i = 0; i < capacity; i++)frame[i] = -1;
        // To store the pages in FIFO manner


        // Start from initial page
        int page_faults = 0;
        for (int i = 0; i < n; i++) {
            if (page_faults < capacity) {
                //7 0 1 2 0 3 0 4 2 3 0 3 0 3 2 1 2 0 1 7 0 1
                boolean t = true;
                for (int j = 0; j < capacity; j++) {
                    if (frame[j] == pages[i]) {
                        t = false;
                    }
                }
                if (t) {
                    frame[i] = pages[i];
                    page_faults++;
                }

            } else {
                boolean t = true;
                for (int j = 0; j < capacity; j++) {
                    if (frame[j] == pages[i]) {
                        t = false;
                    }
                }
                if (t) {
                    //7 0 1 2 0 3 0 4 2 3 0 3 0 3 2 1 2 0 1 7 0 1
                    int indexes[] = new int[n];
                    for (int k = 0; k < capacity; k++) {
                        for (int l = i - 1; l > -1; l--) {
                            if (frame[k] == pages[l]) {
                                indexes[k] = l;
                                break;
                            }
                        }

                    }
                    int indxOfMax = 92000;
                    int frameIndx = 0;
                    for(int l = 0; l < capacity; l++){
                        if(indexes[l] == 0) indexes[l] = -120;
                        if(indexes[l] < indxOfMax) {
                            indxOfMax = indexes[l];
                            frameIndx = l;
                        }
                    }
                    frame[frameIndx] = pages[i];
                    page_faults++;
                }
            }

        }

        return page_faults;
    }






    static int faluteRate(int totalFalute, int numberOfpref){
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

        int n = fifo(r_s,n_r,n_f);
        System.out.println();
        System.out.println("Number of page fault using FIFO Page replacement Algorithm: "+n);
        System.out.println("Page Fault Rate:"+faluteRate(n,n_r)+"%");

        System.out.println();
        int m = optimal(r_s,n_r,n_f);
        System.out.println("Number of page fault using Optimal Page replacement Algorithm: "+m);
        System.out.println("Page Fault Rate:"+faluteRate(m,n_r)+"%");

        System.out.println();

        int o = lru(r_s,n_r,n_f);
        System.out.println("Number of page fault using Least Recently Used Page replacement Algorithm: "+o);
        System.out.println("Page Fault Rate:"+faluteRate(o,n_r)+"%");
    }
}
// 7 0 1 2 0 3 0 4 2 3 0 3 0 3 2 1 2 0 1 7 0 1