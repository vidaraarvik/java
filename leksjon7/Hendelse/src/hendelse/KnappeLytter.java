/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hendelse;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author speedy laptop
 */
public class KnappeLytter implements EventHandler<ActionEvent> {

    public KnappeLytter() {
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Hei fra knapp 1.");
    }
    
}
