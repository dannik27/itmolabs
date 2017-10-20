package ru.ifmo.ctddev.nikiforovykh.arrayset;

import java.util.*;

public class ArraySet<E> extends AbstractSet<E> implements NavigableSet<E>{

    ArrayList<E> data;
    ArrayList<E> dataReversed;
    Comparator<? super E> comparator;

    public ArraySet(Collection<E> collection, Comparator<? super E> comparator){
        this.comparator = comparator;

        ArrayList<E> inputData = new ArrayList<>(collection);
        data = new ArrayList<>(inputData.size());

        inputData.sort(comparator);

        if(inputData.size() != 0){
            data.add(inputData.get(0));
        }
        for(int i = 1; i< inputData.size();i++){
            if (comparator.compare(inputData.get(i), inputData.get(i - 1)) != 0) {
                data.add(inputData.get(i));
            }
        }
        data.trimToSize();


        dataReversed = new ArrayList<>(data);
        dataReversed.sort(comparator.reversed());


    }

    public ArraySet(Collection<E> collection){
        this(collection, (o1, o2) -> ((Comparable<E>)o1).compareTo(o2));
    }

    public ArraySet(){
        data = new ArrayList<>();
        dataReversed = new ArrayList<>();
        comparator = (Comparator<E>) (o1, o2) -> 0;
    }

    private int indexOf(E item){

        return Collections.binarySearch(data, item, comparator);

    }


    // ----------------------------------------

    @Override
    public E lower(E item) {
        int index = indexOf(item);
        if((index != 0)&&(index != -1)){

            if(index < 0){
                index = -(index + 1);
            }

            return data.get(index - 1);

        }else{
            return null;
        }


    }

    @Override
    public E ceiling(E item) {
        int index = indexOf(item);
        if(index > 0){

            return data.get(index);
        }else{
            index = -(index + 1);
            return data.get(index + 1);
        }


    }

    @Override
    public E floor(E item) {
        int index = indexOf(item);
        if(index > 0){
            return data.get(index - 1);
        }

        return null;
    }

    @Override
    public E higher(E item) {
        int index = indexOf(item);
        if(index < data.size()){
            if((index + 1 < data.size())&&(comparator.compare(data.get(index), data.get(index + 1)) == 0)){
                return null;
            }
            return data.get(index + 1);
        }

        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public NavigableSet<E> descendingSet() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return null;
    }

    @Override
    public Comparator<? super E> comparator() {
        return null;
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return null;
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return null;
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return null;
    }

    @Override
    public E first() {
        return null;
    }

    @Override
    public E last() {
        return null;
    }
}
