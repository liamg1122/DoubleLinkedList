
import java.util.ArrayList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BasicDoubleLinkedListStudentTest {
    BasicDoubleLinkedList<Horses> linkedHorses;
    HorseComparator comparatorHorse;

   @BeforeEach
   void setUp() throws Exception {
        linkedHorses = new BasicDoubleLinkedList<>();
        comparatorHorse = new HorseComparator();

        // Initialize with some horses
        linkedHorses.addToEnd(new Horses("Arabian", "Spirit", 5));
        linkedHorses.addToEnd(new Horses("Thoroughbred", "biscuit", 10));
    }

    @AfterEach
    void tearDown() throws Exception {
        linkedHorses = null;
    }

    @Test
    void testAddToFront() {
        linkedHorses.addToFront(new Horses("Mustang", "Rain", 7));
        assertEquals("Mustang Rain 7", linkedHorses.getFirst().toString());
    }

    @Test
    void testAddToEnd() {
        linkedHorses.addToEnd(new Horses("Clydesdale", "Bud", 8));
        assertEquals("Clydesdale Bud 8", linkedHorses.getLast().toString());
    }

    @Test
    void testGetFirst() {
        assertEquals("Arabian Spirit 5", linkedHorses.getFirst().toString());
    }

    @Test
    void testGetLast() {
        assertEquals("Thoroughbred biscuit 10", linkedHorses.getLast().toString());
    }

    @Test
    void testGetSize() {
        assertEquals(2, linkedHorses.getSize());
    }

    @Test
    void testRetrieveFirstElement() {
        assertEquals("Arabian Spirit 5", linkedHorses.retrieveFirstElement().toString());
        assertEquals(1, linkedHorses.getSize());
    }

    @Test
    void testRetrieveLastElement() {
        assertEquals("Thoroughbred biscuit 10", linkedHorses.retrieveLastElement().toString());
        assertEquals(1, linkedHorses.getSize());
    }

    @Test
    void testRemove() {
        linkedHorses.remove(new Horses("Arabian", "Spirit", 5), comparatorHorse);
        assertEquals(1, linkedHorses.getSize());
        assertEquals("Thoroughbred biscuit 10", linkedHorses.getFirst().toString());
    }

    @Test
    void testToArrayList() {
        ArrayList<Horses> list = linkedHorses.toArrayList();
        assertEquals("Arabian Spirit 5", list.get(0).toString());
        assertEquals("Thoroughbred biscuit 10", list.get(1).toString());
    }

    @Test
    void testIterator() {
        ListIterator<Horses> iterator = linkedHorses.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Arabian Spirit 5", iterator.next().toString());
        assertTrue(iterator.hasNext());
        assertEquals("Thoroughbred biscuit 10", iterator.next().toString());
        assertFalse(iterator.hasNext());
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




