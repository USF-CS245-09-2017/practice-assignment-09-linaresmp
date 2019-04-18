import java.util.Arrays;

public class BinaryHeap<T extends Comparable<T>>{
    private static final int DEFAULT_CAPACITY = 10;
    protected int[] array;
    protected int size;
    
    //BinaryHeap constructor
    @SuppressWarnings("unchecked")
	public BinaryHeap () {
        array = new int[DEFAULT_CAPACITY];  
        size = 0;
    }
    
    
    // Adds a value to the heap.
    public void add(int value) {
        // resizes array
        if (size >= array.length - 1) {
            array = this.resize();
        }        
        // place element into bottom of heap
        size++;
        int index = size;
        array[index] = value;
        
        bubbleUp();
    }
    
    
    // return true if size is 0;
    public boolean isEmpty() {
        return size == 0;
    }

    
    //returns the min element in the heap
    public int peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        
        return array[1];
    }

    
    //removes and returns the minimum element
    public int remove() {
    	int result = array[1];
    	// get rid of the last element 
    	array[1] = array[size];
    	array[size] = 0;
    	size--;
    	
    	bubbleDown();
    	
    	return result;
    }
    
    
    /**
     * Returns a String representation of BinaryHeap with values stored with 
     * heap structure and order properties.
     */
    public String toString() {
        return Arrays.toString(array);
    }

    
    /**
     * Performs the "bubble down" operation to place the element that is at the 
     * root of the heap in its correct place so that the heap maintains the 
     * min-heap order property.
     */
    protected void bubbleDown() {
        int index = 1;
        
        // bubble down
        while (hasLeftChild(index)) {
            // which of my children is smaller?
            int smallerChild = leftIndex(index);
            
            // bubble with the smaller child, if I have a smaller child
            if (hasRightChild(index)
                && array[leftIndex(index)] > (array[rightIndex(index)])) {
                smallerChild = rightIndex(index);
            } 
            
            if (array[index] > (array[smallerChild])) {
                swap(index, smallerChild);
            } else {
                // otherwise, get outta here!
                break;
            }
            
            // make sure to update loop counter/index of where last el is put
            index = smallerChild;
        }        
    }
    
    
    /**
     *places a newly inserted element in its correct place so 
     * that the heap maintains the min-heap order property.
     */
    protected void bubbleUp() {
        int index = this.size;
        
        while (hasParent(index)
                && (parent(index) > (array[index]))) {
            // parent/child are out of order; swap them
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }        
    }
    
    
    protected boolean hasParent(int i) {
        return i > 1;
    }
    
    
    protected int leftIndex(int i) {
        return i * 2;
    }
    
    
    protected int rightIndex(int i) {
        return i * 2 + 1;
    }
    
    
    protected boolean hasLeftChild(int i) {
        return leftIndex(i) <= size;
    }
    
    
    protected boolean hasRightChild(int i) {
        return rightIndex(i) <= size;
    }
    
    
    protected int parent(int i) {
        return array[parentIndex(i)];
    }
    
    
    protected int parentIndex(int i) {
        return i / 2;
    }
    
    
    protected int[] resize() {
        return Arrays.copyOf(array, array.length * 2);
    }
    
    
    protected void swap(int index1, int index2) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;        
    }
}