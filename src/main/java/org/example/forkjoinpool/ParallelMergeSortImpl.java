package org.example.forkjoinpool;

import java.util.concurrent.ForkJoinPool;

public class ParallelMergeSortImpl implements MergeSort {
    @Override
    public void sort(int[] arr) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.invoke(new MergeSortAction(arr));
    }
}
