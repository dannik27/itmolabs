package ru.ifmo.ctddev.nikiforovykh.arrayset;

import java.util.ArrayList;

public class Starter {


    public static void main(String... args){


        ArrayList<Integer> list = new ArrayList<>();

        list.add(-1914524706);
        list.add(163634405);
        list.add(-560145825);
        list.add(1931725586);
        list.add(2004520789);
        list.add(-116065026);



        ArraySet<Integer> as = new ArraySet<>(list, (o1, o2) -> 0);
        //ArraySet<Integer> as = new ArraySet<>(list);
        System.out.println(as.data);
        //System.out.println(as.dataReversed);

        System.out.println(as.ceiling(163634405));
    }
}
