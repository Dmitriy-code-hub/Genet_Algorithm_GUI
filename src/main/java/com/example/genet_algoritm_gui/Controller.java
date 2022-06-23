package com.example.genet_algoritm_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Controller {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField tfidField;

    @FXML
    private TextField tf_numberplaces;

    @FXML
    private TextField tf_initialisepopulation;

    @FXML
    private TextField tf_numberiterations;

    @FXML
    private TextField tf_mutationRate;


    @FXML
    protected void onHelloButtonClick()
    {

        int counterPlaces = Integer.parseInt(tf_numberplaces.getText());
        TourManager.clearCity ();
        for (int i = 0; i < counterPlaces; i++) {
            City city = new City();
            TourManager.addCity(city);
        }

        GA.mutationRate = Double.parseDouble(tf_mutationRate.getText());



        int populationSize = Integer.parseInt(tf_initialisepopulation.getText());
        Population pop = new Population(populationSize, true);
       // System.out.println("Initial distance: " + pop.getFittest().getDistance()); //віводят на єкран состогяние обєкта начальное расстояние 0

        // Evolve population for 100 generations
        pop = GA.evolvePopulation(pop); //передают старую популяцию получают новую популяцию




        int SizeIteraziy = Integer.parseInt(tf_numberiterations.getText());
        for (int i = 0; i < SizeIteraziy; i++) {
            pop = GA.evolvePopulation(pop);
        }



        Tour BestTour = pop.getFittest(); // берем лучший результат
        welcomeText.setText ("Coordinates of optimal route: " + BestTour.toString());
        btn_ris(BestTour); // візиваем функцию разделяем вичислительную и рисовательную часть
            }


           public void btn_ris(Tour BestTour)
           {
                    WindowRis.the_best = BestTour;
                    try {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("painter_view.fxml"));
                    Stage stage = new Stage( );
                    Scene scene = new Scene(fxmlLoader.load(), 500, 500);
                    stage.setTitle("Optimal route");
                    stage.setScene(scene);
                    stage.show();
                }
                catch (Exception ex)
                {

                }

            }
}