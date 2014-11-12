public class Heap {

    int[] a;
    int l;

    public Heap(int[] a) {
        this.a = a;
        this.l = a.length-1;
        buildMaxHeap();
    }

    public static void main(String[] args) {
        new Heap(new int[]{0,3,5, 6, 2, 2,2 ,2,6,9,2,8,7}).print();
    }

    public void print() {
        for (int i = 1; i < l; i++) {q
            System.out.print(a[i] + "\t");
        }
        
        System.out.println(" -> " + checkHeap(1));
    }

    private void maxHeapify(int i) {
        int left = i*2;
        int right = left + 1;
        int largest = i;

        if (left < l && a[largest] < a[left])
            largest = left;
        
        if (right < l && a[largest] < a[right]) 
            largest = right;
        
        if (largest != i) 
            swap(largest, i);

        if (i > 1) 
            maxHeapify(i/2);
    }

    public boolean checkHeap(int i) {
        if (i > l/2) 
            return true;
        
        int left = i*2;
        int right = left + 1;
        
        return (left < l && a[i] < a[left]) || (right < l && a[i] < a[right]) ?
            false :
            checkHeap(left) && checkHeap(right);
    }

    public void buildMaxHeap() {
        for (int i = l/2; i > 0; i--) {
            maxHeapify(i);
        }
    }

    private void swap(int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
