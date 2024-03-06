import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SortedDoubleLinkedListStudentTest {

	private SortedDoubleLinkedList<Horses> horseSort;
	private HorseComparator sortedHorseComp;
	Horses a = new Horses("Mustang", "FastCar", 20);
	Horses b = new Horses("Thoroughbred", "Ghost", 12);
	Horses c = new Horses("Appaloosa", "Spot", 10);
	Horses d = new Horses("Arabian", "Sandy", 2);
	
	@BeforeEach
	void setUp() throws Exception {
		
	sortedHorseComp = new HorseComparator();
	horseSort = new SortedDoubleLinkedList<Horses>(sortedHorseComp);
	
	}

	@AfterEach
	void tearDown() throws Exception {
		horseSort = null;
	}

	@Test
	void testAddHorse() {
		horseSort.add(a);
		horseSort.add(b);
		horseSort.add(c);
		horseSort.add(d);
		assertEquals("Appaloosa Spot 10", horseSort.getFirst().toString());
		assertEquals("Thoroughbred Ghost 12", horseSort.getLast().toString());
	}
	
	@Test
	void testIterator() {		
		horseSort.add(a);
		horseSort.add(b);
		horseSort.add(c);
		horseSort.add(d);
		
		ListIterator<Horses> iterator = horseSort.iterator();

		
		assertEquals(true, iterator.hasNext());
		assertEquals(c, iterator.next());
	}
	
	 @Test
	    void testRemove() {
	        horseSort.add(a);
	        horseSort.add(b);
	        horseSort.add(c);
	        horseSort.add(d);
	        
	        assertEquals(4, horseSort.getSize());

	        horseSort.remove(c, sortedHorseComp);
	        assertEquals(3, horseSort.getSize());
	        
	        ListIterator<Horses> iterator = horseSort.iterator();
	        assertEquals(d, iterator.next());
	        assertEquals(a, iterator.next());
	        assertFalse(iterator.next().equals(c)); 
	    }
	 
	    @Test
	    void testToArrayList() {
	        horseSort.add(a);
	        horseSort.add(b);
	        horseSort.add(c);
	        horseSort.add(d);

	        ArrayList<Horses> list = horseSort.toArrayList();
	        assertEquals(4, list.size());
	        assertEquals(c, list.get(0)); 
	        assertEquals(b, list.get(3)); 
	    }
	 
	private class Horses{
		String breed;
		String name;
		int age;
		
		public Horses(String breed, String name, int age){
			this.breed = breed;
			this.name = name;
			this.age = age;
		}
		
		public String getBreed(){
			return breed;
		}
		public String getName(){
			return name;
		}
		public int getAge(){
			return age;
		}
		
		public String toString() {
			return (getBreed()+" "+getName()+" "+getAge());
		}
	}
	
	private class HorseComparator implements Comparator<Horses>{

		@Override
		public int compare(Horses o1, Horses o2) {
			
			return o1.toString().compareTo(o2.toString());
		}

	}}
