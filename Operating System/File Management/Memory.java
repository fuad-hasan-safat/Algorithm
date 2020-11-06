package Session13;

import java.util.Vector;

public class Memory {
    public static int totalMemorySize;
    public static int remainMemory;
    public static int lastOccupiedBlock = -1;
    public static Integer[] mmemory = new Integer[1000];

    public String fileName;
    public int fileSize;
    public Vector<Integer> occupiedMemoryBlock = new Vector<>();

    public Memory(String fileName, int fileSize, Vector<Integer> occupiedMemoryBlock) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.occupiedMemoryBlock = occupiedMemoryBlock;
    }

    public static Vector<Memory> memory = new Vector<>();



    // is file exist
    public static boolean isFileExist(String newFileName)
    {
       // System.out.println("Enter is checking ");
        //System.out.println("New file name = "+newFileName);
        boolean flag = false;
        for(int i = 0;  i < memory.size(); i++)
        {
            //System.out.println("name of index "+ i +" is "+memory.get(i).fileName);
            if(memory.get(i).fileName.equals(newFileName)) {
                flag = true;
            }
        }
        //System.out.println("Flag = "+flag);
        return flag;
    }


    // is enough memory
    public static boolean isEnoughMemory(int newFileSize)
    {
        boolean flag = false;
        if(newFileSize < remainMemory) flag = true;
        return flag;
    }



    // search file
    public static void searchFile(String fileName)
    {
        boolean flag = false;
        for (var block: memory) {
            if(block.fileName.equalsIgnoreCase(fileName))
            {
                flag = true;
                System.out.print("File Found in the blocks :");

                for(int i = 0; i < block.occupiedMemoryBlock.size(); i++)
                {
                    System.out.print(block.occupiedMemoryBlock.get(i)+" ");
                }
                System.out.println();
            }
        }
        if(!flag)
        {
            System.out.println("File not Found.");
        }
    }

}
