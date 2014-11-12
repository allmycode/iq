public class Heap {

    int[] a;
    boolean max;

    public Heap(int[] a, boolean max) {
        this.a = a;
        this.max = max;
        buildHeap();
    }

    public static void main(String[] args) {
        int[] a = new int[args.length-1];
        for (int i = 0; i < args.length-1; i++)
            a[i] = Integer.parseInt(args[i+1]);
        new Heap(a, args[0].equals("max")).print();
    }

    public void print() {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "\t");
        }
        
        System.out.println(" -> " + checkHeap(1));
    }

    private void heapify(int i) {
        int left = (i+1)*2;
        int right = left + 1;
        int largest = i;

        if (cp(largest, left))
            largest = left;
        
        if (cp(largest, right))
            largest = right;
        
        if (largest != i) 
            swap(largest, i);

        if (i > 0) 
            heapify((i+1)/2-1);
    }

    public boolean checkHeap(int i) {
        if ((i+1) > a.length/2) 
            return true;
        
        int left = (i+1)*2;
        int right = left + 1;
        
        return cp(i, left) || cp(i, right) ?
            false :
            checkHeap(left) && checkHeap(right);
    }

    public void buildHeap() {
        for (int i = a.length/2; i > 0; i--) {
            heapify(i);
        }
    }

    private boolean cp(int p, int c) {
        return c < a.length && (max ? a[p] < a[c] : a[p] > a[c]);
    }

    private void swap(int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
