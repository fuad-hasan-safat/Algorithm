package Session13;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class NonContiguousAllocation {


    public void nonContiguious()
    {
        System.out.println("::::::::::::::::Non Contiguous::::::::::::::::");

        Arrays.fill(Memory.mmemory, -1); // initilize


        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of blocks :");
        Memory.totalMemorySize = sc.nextInt();
        Memory.remainMemory = Memory.totalMemorySize;

        for(int m = 0; m < Memory.totalMemorySize; m++)
        {
            if(m%3 == 0) Memory.mmemory[m] = 1;
        }


        System.out.println("\n\n");
        String garb = sc.nextLine();

        while(true)
        {
            System.out.print("Enter Filename:");
            String fileName = sc.nextLine();

            // System.out.println("is exist = "+Memory.isFileExist(fileName));
            if(Memory.isFileExist(fileName)) {
                System.out.println("Already exist");
                System.out.println();
                continue;  // aready have this file in memory
            }
            if(fileName.equalsIgnoreCase("exit")) break;

            System.out.print("Enter File size:");
            int fileSize = sc.nextInt();
            String garb1 = sc.nextLine();



            if(Memory.isEnoughMemory(fileSize))
            {
                // enough memory
                // ContiguiousAllocation ga = new ContiguiousAllocation();
                nonContiguiousAllocation(fileName,fileSize);
            }
            else
            {   // not enough memory
                System.out.println("File "+ fileName +" cannot be created (not enough free blocks)");
            }
            System.out.println();

        }
        System.out.println("Search a file");

        while (true)
        {

            System.out.print("Enter Filename:");
            String fileName = sc.nextLine();
            if(fileName.equalsIgnoreCase("exit")) break;

            Memory.searchFile(fileName);

        }


    }



    public void nonContiguiousAllocation(String newFileName, int newFileSize)
    {
        Vector<Integer> occupiedMemoryBlock = new Vector<>();
        int siz = newFileSize;
        for(int i = 0; i < Memory.totalMemorySize   && siz > 0; i++)
        {
            if(Memory.mmemory[i] == -1){
                occupiedMemoryBlock.add(i);
                siz -= 1;
                Memory.mmemory[i] = 1;
            }

        }
        Memory.remainMemory -= newFileSize;
        Memory mm = new Memory(newFileName, newFileSize, occupiedMemoryBlock);

        Memory.memory.add(mm);

        System.out.println("File "+newFileName+" created.");
    }

}
