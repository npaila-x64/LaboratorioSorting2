package org.app.Sort;

import org.app.Registro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSort {
    private Registro[] registros;
    private int nElems;

    public QuickSort(List<Registro> registros) {
        this.registros = registros.toArray(new Registro[0]);
        nElems = registros.size();
    }

    public void display(){
        for (int i = 0; i < nElems; i++) {
            System.out.println(registros[i]);
        }
    }

    public void quickSort() {
        recQuickSort(0, nElems-1);
    }

    public void recQuickSort(int left, int right) {
        int size = right-left+1;
        if(size < 10)                   // insertion sort if small
            insertionSort(left, right);
        else                            // quicksort if large
        {
            double median = medianOf3(left, right);
            int partition = partitionIt(left, right, median);
            recQuickSort(left, partition-1);
            recQuickSort(partition+1, right);
        }
    }  // end recQuickSort()

    public double medianOf3(int left, int right) {
        int center = (left+right)/2;
        // order left & center
        if( registros[left].getPm101h() > registros[center].getPm101h() )
            swap(left, center);
        // order left & right
        if( registros[left].getPm101h() > registros[right].getPm101h() )
            swap(left, right);
        // order center & right
        if( registros[center].getPm101h() > registros[right].getPm101h() )
            swap(center, right);

        swap(center, right-1);           // put pivot on right
        return registros[right-1].getPm101h();        // return median value
    }  // end medianOf3()

    public void swap(int dex1, int dex2) {
        Registro temp = registros[dex1];        // A into temp
        registros[dex1] = registros[dex2];   // B into A
        registros[dex2] = temp;             // temp into B
    }  // end swap(

    public int partitionIt(int left, int right, double pivot) {
        int leftPtr = left;             // right of first elem
        int rightPtr = right - 1;       // left of pivot
        while(true)
        {
            while( registros[++leftPtr].getPm101h() < pivot )  // find bigger
                ;                                  // (nop)
            while( registros[--rightPtr].getPm101h() > pivot ) // find smaller
                ;                                  // (nop)
            if(leftPtr >= rightPtr)      // if pointers cross,
                break;                    //    partition done
            else                         // not crossed, so
                swap(leftPtr, rightPtr);  // swap elements
        }  // end while(true)
        swap(leftPtr, right-1);         // restore pivot
        return leftPtr;                 // return pivot location
    }  // end partitionIt()

    public void insertionSort(int left, int right) {
        int in, out;
        //  sorted on left of out
        for(out=left+1; out<=right; out++)
        {
            Registro temp = registros[out];    // remove marked item
            in = out;                     // start shifts at out
            // until one is smaller,
            while(in>left && registros[in-1].getPm101h() >= temp.getPm101h())
            {
                registros[in] = registros[in-1]; // shift item to right
                --in;                      // go left one position
            }
            registros[in] = temp;          // insert marked item
        }
    }

    public List<Registro> toArrayList(){
        return new ArrayList<>(Arrays.asList(registros));
    }
}
