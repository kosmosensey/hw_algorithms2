package org.example.service;


import org.example.exception.*;

import java.util.Arrays;

public class StringListImpl implements StringList {

    private final String[] arrayLists;
    private int size;

    public StringListImpl() {
        arrayLists = new String[5];
    }

    public StringListImpl(int initSize) {
        arrayLists = new String[initSize];
    }

    @Override
    public String add(String item) {
        checkSize();
        checkItem(item);
        arrayLists[size++] = item;
        return item;

    }

    @Override
    public String add(int index, String item) {
        checkSize();
        checkItem(item);
        checkIndex(index);

        if (index == size) {
            arrayLists[size++] = item;
            return item;
        }

        System.arraycopy(arrayLists, index, arrayLists, index + 1, size - index);
        arrayLists[index] = item;
        size++;

        return item;
    }

    @Override
    public String set(int index, String item) {
        checkIndex(index);
        checkItem(item);
        arrayLists[index] = item;
        return item;
    }


    // Удаление элемента.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public String remove(String item) {
        checkItem(item);
        int index = indexOf(item);
        return remove(item);
    }

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public String remove(int index) {
        checkIndex(index);
        String item = arrayLists[index];
        if (index != size) {
            System.arraycopy(arrayLists, index + 1, arrayLists, index, size - index);
        }
        size--;
        return item;
    }

    // Проверка на существование элемента.
    // Вернуть true/false;
    @Override
    public boolean contains(String item){
        return indexOf(item) != -1;
    }

    // Поиск элемента.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int indexOf(String item){
        for (int i = 0; i < size; i++) {
            if (arrayLists[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    // Поиск элемента с конца.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int lastIndexOf(String item){
        for (int i = size - 1; i >= 0; i--) {
            if (arrayLists[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    // Получить элемент по индексу.
    // Вернуть элемент или исключение,
    // если выходит за рамки фактического
    // количества элементов.
    @Override
    public String get(int index){
        checkIndex(index);
        return arrayLists[index];
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение,
    // если передан null.
    @Override
    public boolean equals(StringList otherList){
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    // Вернуть фактическое количество элементов.
    @Override
    public int size(){
        return size;
    }

    // Вернуть true,
    // если элементов в списке нет,
    // иначе false.
    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    // Удалить все элементы из списка.
    @Override
    public void clear(){
        size = 0;
    }

    // Создать новый массив
    // из строк в списке
    // и вернуть его.
    @Override
    public String[] toArray(){
        return Arrays.copyOf(arrayLists, size);
    }

    private void checkItem(String item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void checkSize() {
        if (size == arrayLists.length) {
            throw new StorageIsFullException();
        }
    }

    private void checkIndex(int index) {
        if (index != 0) {
            throw new InvalidIndexException();
        }
    }
}
