package com.vladf.koursw.fx.windows;

import com.vladf.koursw.fx.Main;
import com.vladf.koursw.fx.TLauncher;
import com.vladf.koursw.pc.memory.MemScheduler;
import com.vladf.koursw.process.Process;
import com.vladf.koursw.process.Queue;
import com.vladf.koursw.utils.ticker.Ticker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;


public class Controller {
    @FXML
    private void initialize()
    {
        queueTable.getColumns().setAll(genQTable());
        rejectedTable.getColumns().setAll(genQTable());
        doneTable.getColumns().setAll(genQTable());
    }


 /*Tables*/
    @FXML
    TableView<Process> queueTable;
    @FXML
    TableView<Process> rejectedTable;
    @FXML
    TableView<Process> doneTable;

    @FXML
    TabPane coreTabPane;

    ObservableList<Process> qTableList = FXCollections.observableArrayList();
    ObservableList<Process> rTableList = FXCollections.observableArrayList();
    ObservableList<Process> dTableList = FXCollections.observableArrayList();

    private ArrayList<TableColumn<Process, String>> genQTable() {

        TableColumn<Process, String> idColumn= new TableColumn<>("ID");
        TableColumn<Process, String> nameColumn= new TableColumn<>("Name");
        TableColumn<Process, String> priorColumn= new TableColumn<>("Priority");
        TableColumn<Process, String> statusColumn= new TableColumn<>("Status");
        TableColumn<Process, String> tickColumn = new TableColumn<>("TickWorks");
        TableColumn<Process, String> memColumn= new TableColumn<>("Memory");
        TableColumn<Process, String> timeInColumn= new TableColumn<>("TimeIn");
        TableColumn<Process, String> burstColumn= new TableColumn<>("BursTime");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priorColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        tickColumn.setCellValueFactory(new PropertyValueFactory<>("tickWorks"));
        memColumn.setCellValueFactory(new PropertyValueFactory<>("memory"));
        timeInColumn.setCellValueFactory(new PropertyValueFactory<>("timeIn"));
        burstColumn.setCellValueFactory(new PropertyValueFactory<>("bursTime"));

        priorColumn.setVisible(false);
        tickColumn.setVisible(false);
        memColumn.setVisible(false);
        burstColumn.setVisible(false);


        ArrayList<TableColumn<Process, String>> _tmp = new ArrayList<>();
        _tmp.add(idColumn);
        _tmp.add(nameColumn);
        _tmp.add(priorColumn);
        _tmp.add(statusColumn);
        _tmp.add(tickColumn);
        _tmp.add(memColumn);
        _tmp.add(timeInColumn);
        _tmp.add(burstColumn);
        return _tmp;
    }

    public void updateTable(Queue q, ArrayList<Process> a)
    {
        qTableList.setAll(q.getQueue());
        queueTable.setItems(qTableList);
        queueTable.refresh();


        rTableList.setAll(q.getRejectedQueue());
        dTableList.setAll(a);


        rejectedTable.setItems(rTableList);
        rejectedTable.refresh();
        doneTable.setItems(dTableList);
        doneTable.refresh();
    }


 /*Buttons*/

    @FXML
    Button runBTN;
    @FXML
    Button pauseBTN;
    @FXML
    Button stopBTN;
    @FXML
    Button resetBTN;
    @FXML
    Button configBTN;

    @FXML
    protected void runBTN_Click()
    {

        if(!Main.emuThread.isAlive())
            Main.emuThread.start();
        else
            Main.emuThread.resume();

        runBTN.setDisable(true);
        configBTN.setDisable(true);
        resetBTN.setDisable(true);
        pauseBTN.setDisable(false);
        stopBTN.setDisable(true);
    }

    @FXML
    protected void pauseBTN_Click()
    {
        if(Main.emuThread.isAlive())
            Main.emuThread.suspend();

        pauseBTN.setDisable(true);
        runBTN.setDisable(false);
        stopBTN.setDisable(false);
    }

    @FXML
    protected void stopBTN_Click()
    {
        if(Main.emuThread.isAlive())
            Main.emuThread.stop();

        stopBTN.setDisable(true);
        configBTN.setDisable(true);
        resetBTN.setDisable(false);
        runBTN.setDisable(true);
    }

    @FXML
    protected void resetBTN_Click()
    {
        MemScheduler.clearMem();
        Ticker.clearTime();
        Main.emuThread = new Thread(new TLauncher());
        queueTable.setItems(null);
        rejectedTable.setItems(null);
        doneTable.setItems(null);

        resetBTN.setDisable(true);
        configBTN.setDisable(false);
        pauseBTN.setDisable(true);
        runBTN.setDisable(false);
    }


    @FXML
    protected void configBTN_Click()
    {

    }



}
