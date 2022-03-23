package Classes;

import javafx.fxml.FXML;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SeccionDocumentos {

    @FXML void action() {
        try {
            Desktop.getDesktop().open(new File ("C:\\Users\\chris\\IdeaProjects\\BACE\\src\\Images\\doc.pdf"));
        }//try
        catch (IOException ex) {
            ex.printStackTrace();
        }//catch
    }//action

}//seccionDocumentos
