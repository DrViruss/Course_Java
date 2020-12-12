package com.vladf.koursw.fx.windows;

import com.vladf.koursw.fx.Main;
import com.vladf.koursw.fx.TLauncher;
import com.vladf.koursw.pc.Configuration;
import com.vladf.koursw.pc.cpu.CPU;
import com.vladf.koursw.pc.memory.MemScheduler;
import com.vladf.koursw.process.Process;
import com.vladf.koursw.process.Queue;
import com.vladf.koursw.utils.ticker.Ticker;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class Controller {
    @FXML
    private void initialize()
    {
updateCoreTabs();

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


        statusColumn.setVisible(false);
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


    public void updateGUI(Queue q, ArrayList<Process> a, int tick, CPU cpu)
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

        for(int i=Configuration.coreCount-1; i>-1 ;i--)
        {
            ArrayList<Process> arr = new ArrayList<>();
            arr.add(cpu.getCores()[i].getCurrentProcess());
            TableView t = (TableView) coreTabPane.getTabs().get(i).getContent();
            t.setItems(FXCollections.observableArrayList(arr));
        }

        udateLabel(q,tick);

    }

    public void udateLabel(Queue q, int tick)
    {
        Platform.runLater(() -> {
            tickLabel.setText(String.valueOf(tick));
    queueLabel.setText(String.valueOf(q.getPID()));
    rejectedLabel.setText(String.valueOf(q.getRejectedQueue().size()));
        });
    }

    public void udateLabel()
    {
        Platform.runLater(() -> {
            tickLabel.setText(String.valueOf(0));
            queueLabel.setText(String.valueOf(0));
            rejectedLabel.setText(String.valueOf(0));
        });
    }
    public void updateCoreTabs()
    {
        for(int i=0; i< Configuration.coreCount;i++)
        {
            TableView<Process> p= new TableView<>();
            p.getColumns().setAll(genQTable());
            p.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            p.setTableMenuButtonVisible(true);
            p.getColumns().setAll(genQTable());
            coreTabPane.getTabs().add(new Tab("Core#"+i));
            coreTabPane.getTabs().get(i).setContent(p);
        }
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
        coreTabPane.getTabs().clear();
        queueTable.setItems(null);
        rejectedTable.setItems(null);
        doneTable.setItems(null);

        udateLabel();

        resetBTN.setDisable(true);
        configBTN.setDisable(false);
        pauseBTN.setDisable(true);
        runBTN.setDisable(false);
    }

    @FXML
    protected void configBTN_Click() throws IOException {
        coreTabPane.getTabs().clear();
        Parent loader = FXMLLoader.load(getClass().getResource("config.fxml"));
        Stage newWindow = new Stage();
        newWindow.setTitle("VTaskViewer");
        newWindow.setScene(new Scene(loader, 300, 300));
        newWindow.show();
        newWindow.setResizable(false);
        newWindow.setOnCloseRequest(Event::consume);
    }


    /*Labels*/
    @FXML
    Label tickLabel;
    @FXML
    Label queueLabel;
    @FXML
    Label rejectedLabel;

}
