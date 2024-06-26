package HW_PACKAGE;
import java.util.Random;

public class MetalRecycleBin<T> implements IBag<T> {
	
	private static int default_capacity = getRandomNumber();
	private T[] metalBin;
	private int numberOfItems;
	private boolean initialized = false;
	private static int MAX_CAPACITY = 10000;

	public MetalRecycleBin() {
		this(default_capacity);
	}

	public MetalRecycleBin(int capacity) {
		if (capacity<= MAX_CAPACITY) {
			@SuppressWarnings("unchecked")
			T[] tempBin = (T[])new Object[capacity];
			metalBin = tempBin;
			numberOfItems = 0;
			initialized = true;
		}
		else {
			throw new IllegalStateException("Attempt to create a bag with over max allowed capacity."); 
		}
		
	}

	private void checkInitialization() {
		if(!initialized) {
			throw new SecurityException("Array bag object is not initialized properly.");
			
		}
	}
	

	@Override
	public boolean add(T newItem) {
		checkInitialization();
		boolean result = true;
		if (isFull()) {
			result = false;
					
		}
		else {
			metalBin[numberOfItems] = newItem;
			numberOfItems ++;
			
		}
		return result;
		
	}	

	@Override
	public boolean isEmpty() {
		
		return numberOfItems == 0;
	}

	@Override
	public boolean isFull() {
		
		return numberOfItems >= metalBin.length;
	}

	@Override
	public T removeByIndex(int index) {
		T result = null;
		if (!isEmpty()&&index>=0) {
			result = metalBin[index];
			metalBin[index] = metalBin[numberOfItems-1];
			metalBin[numberOfItems-1] = null;
			numberOfItems --;
		}
		return result;
	}

	@Override
	public T remove() {
		checkInitialization();
		T result = null;
		if (!isEmpty()) {
			result = metalBin[numberOfItems-1];
			metalBin[numberOfItems-1] = null;
			numberOfItems --;
		}
		return result;
		
		
	}

	@Override
	public T remove(T item) {
		checkInitialization();
		int index = getIndexOf(item);
		T result = removeByIndex(index);
		return result;
	}

	@Override
	public int getItemCount() {
		
		return numberOfItems;
	}

	@Override
	public int getIndexOf(T item) {
		int where = -1;
		boolean found = false;
		int index = 0;
		while (!found&&(index<numberOfItems)) {
			if(item.equals(metalBin[index])) {
				found = true;
				where = index;			
			}
			index ++;
		}
		
		return where;
	}

	@Override
	public boolean contains(T item) {
		checkInitialization();
		return getIndexOf(item)>-1;
	}

	@Override
	public void displayItems() {
		System.out.println("Contents: ");
		for(int i = 0; i<=numberOfItems-1; i++) {
			System.out.println(metalBin[i]);
		}		
	}

	@Override
	public void dump() {
		while(!isEmpty()) {
			remove();
		}
		
	}

	@Override
	public boolean transferTo(IBag<T> targetBag, T item) {
		targetBag.add(item);
		;
		return true;
	}
	
	private static int getRandomNumber() {
        Random rand = new Random();
        int[] numbers = {5, 10, 15};
        int index = rand.nextInt(numbers.length);
        return numbers[index];
	}

	
	public T getItem(int index) {
		
		return metalBin[index];
	}

	@Override
	public int getFrequencyOf(T item) {
		checkInitialization();
		int counter = 0;
		for(int i = 0; i<numberOfItems;i++) {
			if (item.equals(metalBin[i])){
				counter++;			
			}
		}
		return counter;
	}

}
