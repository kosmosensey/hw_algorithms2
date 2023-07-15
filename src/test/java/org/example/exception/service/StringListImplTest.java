package org.example.exception.service;

import org.example.service.StringList;
import org.example.service.StringListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.exception.*;

import static org.junit.jupiter.api.Assertions.*;

public class StringListImplTest {
    private StringList stringListOne;
    private StringList stringListTwo;

    @BeforeEach
    void setUp() {
        stringListOne = new StringListImpl(2);
        stringListTwo = new StringListImpl(2);
    }

    @Test
    void add() {
        assertThrows(NullItemException.class, () -> stringListOne.add(null));
        assertThrows(NullItemException.class, () -> stringListOne.add(1, null));
        assertThrows(InvalidIndexException.class, () -> stringListOne.add(3, "item"));

        stringListOne.add("one_item");
        stringListOne.add("two_item");

        assertThrows(StorageIsFullException.class, () -> stringListOne.add("three_item"));
        assertThrows(StorageIsFullException.class, () -> stringListOne.add(2, "three_item"));
    }

    @Test
    void set() {
        stringListOne.add("one_item");
        assertThrows(NullItemException.class, () -> stringListOne.set(0, null));
        assertThrows(InvalidIndexException.class, () -> stringListOne.set(3, "item"));
    }

    @Test
    void remove() {
        assertThrows(NullItemException.class, () -> stringListOne.remove(null));
        assertThrows(InvalidIndexException.class, () -> stringListOne.remove(3));
    }

    @Test
    void contains() {
        stringListOne.add("one_item");
        assertTrue(stringListOne.contains("one_item"));
    }

    @Test
    void indexOf() {
        stringListOne.add(0, "one");
        int actual = stringListOne.indexOf("one");
        int expected = stringListOne.indexOf("one");
        assertEquals(actual, expected);
    }

    @Test
    void lastIndexOf() {
        stringListOne.add(0, "one");
        int actual = stringListOne.lastIndexOf("one");
        int expected = stringListOne.lastIndexOf("one");
        assertEquals(actual, expected);
    }

    @Test
    void get() {
        assertThrows(InvalidIndexException.class, () -> stringListOne.get(5));
    }

    @Test
    void equals() {
        stringListOne.add(0, "one");
        stringListTwo.add(0, "one");

        String[] actual = stringListOne.toArray();
        String[] expected = stringListTwo.toArray();

        assertArrayEquals(actual, expected);
    }

    @Test
    void size() {
        stringListOne.add("one_item");

        int actual = stringListOne.size();
        int expected = 1;
        assertEquals(actual, expected);
    }

    @Test
    void isEmpty() {
        assertTrue(stringListOne.isEmpty());
    }

    @Test
    void clear() {
        stringListOne.add(0, "one");
        stringListOne.add("two");
        stringListOne.clear();
        for (int i = 0; i < stringListOne.size(); i++) {
            assertNull(stringListOne.get(i));
        }
    }

    @Test
    void toArray() {
        stringListOne.add("one");
        stringListOne.add("two");
        String[] actual = stringListOne.toArray();
        String[] expected = {"one", "two"};
        assertArrayEquals(actual, expected);
    }
}