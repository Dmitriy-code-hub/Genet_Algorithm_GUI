package com.example.genet_algoritm_gui;



public class Population { // определяет место где будут хранится особи одного поколения, а особь -  в данном случае конкретній тур

    // Holds population of tours
    Tour[] tours;

    // Construct a population
    public Population(int populationSize, boolean initialise) { //метод для работі с массивом инизиализация
        tours = new Tour[populationSize];
        // If we need to initialise a population of tours do so
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < populationSize(); i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveTour(i, newTour);
            }
        }
    }

    // Saves a tour
    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    } //потом изменение елементов сеттер

    // Gets a tour from population
    public Tour getTour(int index) {
        return tours[index];
    } // пролучение елементов из массива

    // Gets the best tour in the population
    public Tour getFittest() { //проходит по всей популяции целиком
        Tour fittest = tours[0];
        // Loop through individuals to find fittest
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        return fittest; // возвращает лучший тур - именно тур с минимальнім расстоянием суммарное растояние
    }

    // Gets population size
    public int populationSize() {
        return tours.length;
    }
}