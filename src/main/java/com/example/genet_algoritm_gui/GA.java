package com.example.genet_algoritm_gui;/*
 * GA.java
 * Manages algorithms for evolving population
 */



public class GA {

    /* GA parameters */
    public static double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true; // - задается елітізм - пользоваться или нет - просто флажок

    // Evolves a population over one generation
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);//старую популяцію полоучил обработал сделал новую - суть метода

        // Keep our best individual if elitism is enabled - саміе лучшие особи сохраняют чтоб не потерять елитизм - сохраняют елиту
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveTour(0, pop.getFittest());
            elitismOffset = 1;
        }
//из сотни позиций нашли идеальную потом мутировали и она престала біть идеальной лучшие варианті откладіваем

        // Crossover population - переставили кусочки
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) { // берем особи для спаривания без елитніх
            // Select parents
            Tour parent1 = tournamentSelection(pop); //особи которіе скрешиваютсч
            Tour parent2 = tournamentSelection(pop);
            // Crossover parents
            Tour child = crossover(parent1, parent2);// ребенок создаетсч и в 37 идет в список ТУР
            // Add child to new population
            newPopulation.saveTour(i, child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) { // елітніе не трогаем после елітних каждій заставляем ізменится
            mutate(newPopulation.getTour(i)); //после скрещивания все результаті тоже мутировали
        }

        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring
    public static Tour crossover(Tour parent1, Tour parent2) { // создает
        // Create new child tour
        Tour child = new Tour();//создается один ребенок

        // Get start and end sub tour positions for parent1's tour - тут соединяют кусочки
        int startPos = (int) (Math.random() * parent1.tourSize()); //определяют кусок которій будет копироваться
        int endPos = (int) (Math.random() * parent1.tourSize());//тоже самое

        // Loop and add the sub tour from parent1 to our child
        for (int i = 0; i < child.tourSize(); i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            } // If our start position is larger
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        // Loop through parent2's city tour // тоже взяли со второго
        for (int i = 0; i < parent2.tourSize(); i++) {
            // If child doesn't have the city add it
            if (!child.containsCity(parent2.getCity(i))) {
                // Loop to find a spare position in the child's tour
                for (int ii = 0; ii < child.tourSize(); ii++) {
                    // Spare position found, add city
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    // Mutate a tour using swap mutation
    private static void mutate(Tour tour) { //в массиве тур список горродов и они в 101 102 строке меняют их местами город 1 и 2 берут случайнім образом
        // Loop through tour cities
        for(int tourPos1=0; tourPos1 < tour.tourSize(); tourPos1++){
            // Apply mutation rate
            if(Math.random() < mutationRate){
                // Get a second random position in the tour
                int tourPos2 = (int) (tour.tourSize() * Math.random());

                // Get the cities at target position in tour
                City city1 = tour.getCity(tourPos1);
                City city2 = tour.getCity(tourPos2);

                // Swap them around
                tour.setCity(tourPos2, city1);
                tour.setCity(tourPos1, city2);
            }
        }
    }

    // Selects candidate tour for crossover // берут одно что то из кроссовера - когда берут одного родителя для кроссовера
    // делают копию родителя чтоб не потерять
    private static Tour tournamentSelection(Population pop) { //вібірают родителей
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveTour(i, pop.getTour(randomId));
        }
        // Get the fittest tour
        Tour fittest = tournament.getFittest();
        return fittest;
    }
}