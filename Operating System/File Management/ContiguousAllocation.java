package Session13;

import java.util.*;

public class ContiguousAllocation {

    public void contiguious()
    {
        //Arrays.fill(Memory.mmemory, -1); // initilize
        System.out.println("::::::::::::::::Contiguous::::::::::::::::");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of blocks :");
        Memory.totalMemorySize = sc.nextInt();
        Memory.remainMemory = Memory.totalMemorySize;
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
                 contiguiousAllocation(fileName,fileSize);
            }
            else
            {   // not enough memory
                System.out.println("File "+ fileName +" cannot be created (not enough free blocks)");
            }
            System.out.println();

        }
        System.out.println();
        System.out.println("Search a file");

        while (true)
        {

            System.out.print("Enter Filename:");
            String fileName = sc.nextLine();
            if(fileName.equalsIgnoreCase("exit")) break;

            Memory.searchFile(fileName);
        }
    }

    public void contiguiousAllocation(String newFileName, int newFileSize)
    {
        Vector<Integer> occupiedMemoryBlock = new Vector<>();
        for(int i = 1; i <= newFileSize; i++)
        {
            occupiedMemoryBlock.add(Memory.lastOccupiedBlock+1);
            Memory.lastOccupiedBlock += 1;// last index
        }
        Memory.remainMemory -= newFileSize;
        Memory mm = new Memory(newFileName, newFileSize, occupiedMemoryBlock);

        Memory.memory.add(mm);

        System.out.println("File "+newFileName+" created.");
    }

}
