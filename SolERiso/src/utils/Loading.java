/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author johnn
 */
public class Loading {
    private static Stage loadingStage;
    
    public static void show() throws Exception {
        loadingStage = new Stage();
        loadingStage.initStyle(StageStyle.UNDECORATED);
        
        LoginFXPreloader preloader = new LoginFXPreloader();
        preloader.start(loadingStage);
    }
    
    public static void close() {
        loadingStage.close();
    }
}
