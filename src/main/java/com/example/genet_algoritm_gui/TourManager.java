package com.example.genet_algoritm_gui;/*
 * TourManager.java
 * Holds the cities of a tour
 */



import java.util.ArrayList;

public class TourManager { // динамический массив список с городами только города с координатами

    // Holds our cities
    private static ArrayList destinationCities = new ArrayList<City>();

    // Adds a destination city
    public static void addCity(City city) {
        destinationCities.add(city);
    }
    public static void clearCity () {destinationCities.clear();
}
    // Get a city
    public static City getCity(int index){
        return (City)destinationCities.get(index);
    }

    // Get the number of destination cities
    public static int numberOfCities(){
        return destinationCities.size();
    } // в классе город не расстогяние а метод его расчетов
}