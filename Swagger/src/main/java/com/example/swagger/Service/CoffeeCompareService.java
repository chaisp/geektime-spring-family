package com.example.swagger.Service;

import com.example.swagger.model.Coffee;

import java.util.*;

public class CoffeeCompareService {


    public static void main(String[] args){
        compareByHashMap();
        compareByTreeMap();
    }
    public static void compareByTreeMap(){
        //只能对key进行排序
        Map<Coffee,String> coffeeMap = new TreeMap<Coffee,String>(
                new Comparator<Coffee>() {
                    public int compare(Coffee obj1, Coffee obj2) {
                        // 降序排序
                        return obj1.compareTo(obj2);
                    }});

        for (int i=0;i<10;i++) {
            Coffee coffee = new Coffee();
            coffee.name="coffe"+i;
            coffeeMap.put(coffee,String.valueOf(i));

        }
        for (Map.Entry<Coffee, String> entry: coffeeMap.entrySet()){
            System.out.println(entry.getKey().name);
        }
    }
    public static void compareByHashMap(){
        Map<String,Coffee> coffeeMap = new HashMap<String, Coffee>();
        for (int i=0;i<10;i++) {
            Coffee coffee = new Coffee();
            coffee.name="coffe"+i;
            coffeeMap.put(String.valueOf(i),coffee);

        }

            List<Map.Entry<String,Coffee>> list = new ArrayList<Map.Entry<String,Coffee>>(coffeeMap.entrySet());
            Collections.sort(list,new Comparator<Map.Entry<String,Coffee>>() {
                //升序排序
                public int compare(Map.Entry<String, Coffee> o1,
                                   Map.Entry<String, Coffee> o2) {
                    return o2.getValue().name.compareTo(o1.getValue().name);
                }

            });

            for(Map.Entry<String,Coffee> mapping:list) {
                System.out.println(mapping.getKey() + ":" + mapping.getValue().name);
            }
    }
}
