package com.vladf.koursw.pc.memory;


import com.vladf.koursw.pc.System;
import com.vladf.koursw.process.Process;

import java.util.ArrayList;

public class MemScheduler {
private static ArrayList<MemoryBlock> memoryBlocks = new ArrayList<>();

public static int SearchMB(int size)
{
    memoryBlocks.sort(MemoryBlock.byEnd);

    for(int i=0;i<memoryBlocks.size()-1;i++) {
        if (memoryBlocks.get(i + 1).start - memoryBlocks.get(i).end >= size+1)
            return memoryBlocks.get(i).end + 1;
    }
    if(System.memory - memoryBlocks.get(memoryBlocks.size()-1).end>=size+1)
        return memoryBlocks.get(memoryBlocks.size()-1).end+1;
    else
        return System.memory;
}

public static boolean fillMB(com.vladf.koursw.process.Process process)
{
    int _start = SearchMB(process.getMemory());

    if (_start!=System.memory)
    {
        memoryBlocks.add(new MemoryBlock(_start, _start+process.getMemory(),process));
        return true;
    }
    else
    return false;
}

public static void releaseMB(Process process)
{
    memoryBlocks.removeIf(mb -> mb.process == process);
}

public static void add(MemoryBlock memoryBlock) {
    memoryBlocks.add(memoryBlock);
}

public static void print() {
    java.lang.System.out.println(memoryBlocks.toString());
}

    @Override
    public String toString() {
        return "MemScheduler{"+memoryBlocks+"}";
    }
}
