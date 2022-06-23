package com.example.genet_algoritm_gui;/*
 * Tour.java
 * Stores a candidate tour
 */



import java.util.ArrayList;
import java.util.Collections;

public class Tour{

    public int id;

    // Holds our tour of cities
    private ArrayList tour = new ArrayList<City>(); // массив городов
    // Cache
    private double fitness = 0;
    private int distance = 0; // суммарное расстоґяние которое когда то будет вічислятьсґя

    // Constructs a blank tour
    public Tour(){
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            tour.add(null);
        }
    }

    public Tour(ArrayList tour){
        this.tour = tour;
    }

    // Creates a random individual
    public void generateIndividual() {
        // Loop through all our destination cities and add them to our tour
        for (int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++) {
            setCity(cityIndex, TourManager.getCity(cityIndex));
        }
        // Randomly reorder the tour
        Collections.shuffle(tour);
    }

    // Gets a city from the tour
    public City getCity(int tourPosition) {
        return (City)tour.get(tourPosition);
    }

    // Sets a city in a certain position within a tour
    public void setCity(int tourPosition, City city) {
        tour.set(tourPosition, city);
        // If the tours been altered we need to reset the fitness and distance
        fitness = 0;
        distance = 0;
    }

    // Gets the tours fitness // целевая функция

    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double)getDistance(); // в качестве целевой функции 1/расстояние
        } // чем больше расстояние тем меньше фитнесс
        return fitness;
    }//контрлол колесико - увеличение екрана

    // Gets the total distance of the tour
    public int getDistance(){ // важен порядок городов, тур состоит из порядка городов, проходим по всему списку городов и проверяем растояние
        if (distance == 0) {
            int tourDistance = 0;
            // Loop through our tour's cities
            for (int cityIndex=0; cityIndex < tourSize(); cityIndex++) {
                // Get city we're travelling from
                City fromCity = getCity(cityIndex);
                // City we're travelling to
                City destinationCity;
                // Check we're not on our tour's last city, if we are set our
                // tour's final destination city to our starting city
                if(cityIndex+1 < tourSize()){
                    destinationCity = getCity(cityIndex+1);
                }
                else{
                    destinationCity = getCity(0);
                }
                // Get the distance between the two cities
                tourDistance += fromCity.distanceTo(destinationCity);
            }
            distance = tourDistance;
        }
        return distance;
    } // когда прощшли по всему списку получили сумму расстояний которое нужно проехать в данном конкретном случае
    // таких ппроходов делаем по циклу // количество пароходов - количество городов -1
    //интесует общая протяженность маршрутов

    // Get number of cities on our tour
    public int tourSize() {
        return tour.size();
    }

    // Check if the tour contains a city
    public boolean containsCity(City city){
        return tour.contains(city);
    } // заглушка для метода  візівает другой метод и возвращает его результат

    @Override
    public String toString() { // для визуализации чтоб показать порядок городов
        String geneString = "|";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i)+"|";
        }
        return geneString;
    }
    public int getMaxX () {
        int result = 0;
        for (int i = 0; i < tourSize(); i++) {
        if (getCity(i).x > result) {
        result=getCity(i).x;
            }
        }
        return result;
    }
    public int getMaxY () {
        int result = 0;
        for (int i = 0; i < tourSize(); i++) {
            if (getCity(i).y > result) {
                result=getCity(i).y;
            }
        }
        return result;
    }

}