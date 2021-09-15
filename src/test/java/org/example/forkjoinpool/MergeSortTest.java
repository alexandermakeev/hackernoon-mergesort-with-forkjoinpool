package org.example.forkjoinpool;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MergeSortTest {

    private final List<MergeSort> mergeSortImpls =
            Arrays.asList(new MergeSortImpl(), new ParallelMergeSortImpl());

    @Test
    void sort() {
        for (MergeSort mergeSort : mergeSortImpls) {
            int[] arr = IntStream
                    .range(0, 100_000_000)
                    .map(i -> ThreadLocalRandom.current().nextInt())
                    .toArray();
            ZonedDateTime now = ZonedDateTime.now();
            mergeSort.sort(arr);
            System.out.printf("%s exec time: %dms\n",
                    mergeSort.getClass().getSimpleName(),
                    ChronoUnit.MILLIS.between(now, ZonedDateTime.now()));
            assertTrue(isSorted(arr));
        }
    }

    private boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1])
                return false;
        }
        return true;
    }

}