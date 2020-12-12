package com.vladf.koursw.fx.windows;

import com.vladf.koursw.fx.Main;
import com.vladf.koursw.pc.Configuration;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

public class ConfigController {

    @FXML
    TextField coreTB;
    @FXML
    TextField memTB;
    @FXML
    TextField tpsTB;
    @FXML
    TextField startP_TB;
    @FXML
    CheckBox loggerCB;


    @FXML
    private void initialize()
    {
        coreTB.setText(String.valueOf(Configuration.coreCount));
        memTB.setText(String.valueOf(Configuration.maxMemsize));
        tpsTB.setText(String.valueOf(Configuration.tickIncrement));
        startP_TB.setText(String.valueOf(Configuration.initPCount));
        loggerCB.setSelected(Configuration.logger);
        //TODO: LOAD Info From PATTERN__!!!

        coreTB.setTextFormatter(new TextFormatter<Number>(this::numberFilter));
        coreTB.setOnAction(event -> validateNumberField(coreTB, 1, 100, 4));
        coreTB.focusedProperty().addListener((observable, oldValue, newValue) -> validateNumberField(coreTB, 1, 100, 4));

        memTB.setTextFormatter(new TextFormatter<Number>(this::numberFilter));
        memTB.setOnAction(event -> validateNumberField(memTB, 1024, 16384, 2048));
        memTB.focusedProperty().addListener((observable, oldValue, newValue) -> validateNumberField(memTB, 10, 16384, 4096));

        tpsTB.setTextFormatter(new TextFormatter<Number>(this::numberFilter));
        tpsTB.setOnAction(event -> validateNumberField(tpsTB, 1, 100, 2));
        tpsTB.focusedProperty().addListener((observable, oldValue, newValue) -> validateNumberField(tpsTB, 1, 100, 2));

        startP_TB.setTextFormatter(new TextFormatter<Number>(this::numberFilter));
        startP_TB.setOnAction(event -> validateNumberField(startP_TB, 0, 100, 2));
        startP_TB.focusedProperty().addListener((observable, oldValue, newValue) -> validateNumberField(startP_TB, 0, 100, 2));
    }

    /*Thanks, theorangeind!*/
    private void validateNumberField(TextField field, int minVal, int maxVal, int defVal)
    {
        try
        {
            String oldText = field.getText();
            int old = Integer.parseInt(oldText);
            if(old > maxVal) field.setText(String.valueOf(maxVal));
            else if(old < minVal) field.setText(String.valueOf(minVal));
            else field.setText(oldText);
        }
        catch (Exception e) {field.setText(String.valueOf(defVal));}
    }
    private TextFormatter.Change numberFilter(TextFormatter.Change change)
    {
        if (change.isDeleted())
        {
            return change;
        }

        String text = change.getControlNewText();
        if (!text.matches("[0-9]+"))
        {
            return null;
        }

        return change;
    }


    @FXML
    protected void okBTN_onClick()
    {
        Configuration.coreCount = Integer.parseInt(coreTB.getText());
        Configuration.maxMemsize = Integer.parseInt(memTB.getText());
        Configuration.tickIncrement = Integer.parseInt(tpsTB.getText());
        Configuration.initPCount = Integer.parseInt(startP_TB.getText());
        Configuration.logger = loggerCB.isSelected();

        Main.controller.updateCoreTabs();
        Stage stage = (Stage) startP_TB.getScene().getWindow();
        stage.close();
    }

}
