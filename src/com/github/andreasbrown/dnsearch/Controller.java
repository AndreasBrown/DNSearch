package com.github.andreasbrown.dnsearch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sun.plugin.cache.OldCacheEntry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.github.andreasbrown.dnsearch.DNSearch.*;

public class Controller {

    @FXML
    private TextField field;
    @FXML
    private Accordion servers;
    @FXML
    private TitledPane server1;
    @FXML
    private TitledPane server2;
    @FXML
    private TitledPane server3;
    @FXML
    private TitledPane server4;
    @FXML
    private TitledPane server5;
    @FXML
    private TitledPane server6;
    @FXML
    private TitledPane server7;
    @FXML
    private TitledPane server8;
    @FXML
    private TitledPane server9;
    @FXML
    private Label domain;
    @FXML
    private Label domain1;
    @FXML
    private Label domain2;
    @FXML
    private Label domain3;
    @FXML
    private Label domain4;
    @FXML
    private Label domain5;
    @FXML
    private Label domain6;
    @FXML
    private Label domain7;
    @FXML
    private Label domain8;
    @FXML
    private Label ns;
    @FXML
    private Label ns1;
    @FXML
    private Label ns2;
    @FXML
    private Label ns3;
    @FXML
    private Label ns4;
    @FXML
    private Label ns5;
    @FXML
    private Label ns6;
    @FXML
    private Label ns7;
    @FXML
    private Label ns8;
    @FXML
    private Label Answer;
    @FXML
    private Label Types;
    @FXML
    private Label Types1;
    @FXML
    private Label Types2;
    @FXML
    private Label Types3;
    @FXML
    private Label Types4;
    @FXML
    private Label Types5;
    @FXML
    private Label Types6;
    @FXML
    private Label Types7;
    @FXML
    private Label Types8;
    @FXML
    private Label TTL;
    @FXML
    private Label TTL1;
    @FXML
    private Label TTL2;
    @FXML
    private Label TTL3;
    @FXML
    private Label TTL4;
    @FXML
    private Label TTL5;
    @FXML
    private Label TTL6;
    @FXML
    private Label TTL7;
    @FXML
    private Label TTL8;
    @FXML
    private Canvas canvas;
    @FXML
    private AnchorPane cANCPane;
    @FXML
    private VBox VBOXCan;
    @FXML
    public static Button StepBack;
    @FXML
    public static ProgressBar progressBar;

    public static long time = 0;
    public static List<TitledPane> ServerList = new ArrayList<>();
    public static List<Label> Domains = new ArrayList<>();
    public static List<Label> NameServers = new ArrayList<>();
    public static List<Label> types = new ArrayList<>();
    public static List<Label> TTLs = new ArrayList<>();
    private static int levelsOfTree=0;
    public static int oldCount = count;
    public static int oldBranch = branch;
    public static int flag= -1;
    public static int flag2= 0;
    private static int[] oldBranches = new int[5];


    public void StartSearch(ActionEvent actionEvent) throws IOException {
        try {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            rec.clear();
            ServerList.add(server1);
            ServerList.add(server2);
            ServerList.add(server3);
            ServerList.add(server4);
            ServerList.add(server5);
            ServerList.add(server6);
            ServerList.add(server7);
            ServerList.add(server8);
            ServerList.add(server9);
            Domains.add(domain);
            Domains.add(domain1);
            Domains.add(domain2);
            Domains.add(domain3);
            Domains.add(domain4);
            Domains.add(domain5);
            Domains.add(domain6);
            Domains.add(domain7);
            Domains.add(domain8);
            NameServers.add(ns);
            NameServers.add(ns1);
            NameServers.add(ns2);
            NameServers.add(ns3);
            NameServers.add(ns4);
            NameServers.add(ns5);
            NameServers.add(ns6);
            NameServers.add(ns7);
            NameServers.add(ns8);
            types.add(Types);
            types.add(Types1);
            types.add(Types2);
            types.add(Types3);
            types.add(Types4);
            types.add(Types5);
            types.add(Types6);
            types.add(Types7);
            types.add(Types8);
            TTLs.add(TTL);
            TTLs.add(TTL1);
            TTLs.add(TTL2);
            TTLs.add(TTL3);
            TTLs.add(TTL4);
            TTLs.add(TTL5);
            TTLs.add(TTL6);
            TTLs.add(TTL7);
            TTLs.add(TTL8);
            //progressBar.setProgress(0);

            for (int i = 0; i < 9; i++) {
                ServerList.get(i).setFont(Font.font(servers.getHeight() / 40));
                Domains.get(i).setFont(Font.font(servers.getHeight() / 50));
                types.get(i).setFont(Font.font(servers.getHeight() / 60));
                NameServers.get(i).setFont(Font.font(servers.getHeight() / 60));
                NameServers.get(i).setPrefHeight(servers.getHeight() / 2);
                NameServers.get(i).setLayoutY(servers.getHeight() / 18);

                ServerList.get(i).setFont(Font.font(servers.getHeight() / 40));
                Domains.get(i).setFont(Font.font(servers.getHeight() / 45));
                Domains.get(i).setLayoutY(servers.getHeight() / 38);
                NameServers.get(i).setFont(Font.font(servers.getHeight() / 55));
                NameServers.get(i).setPrefHeight(servers.getHeight() / 2);
                NameServers.get(i).setLayoutY(servers.getHeight() / 12);
                types.get(i).setFont(Font.font(servers.getHeight() / 55));
                types.get(i).setLayoutY(servers.getHeight() / 25);

                ServerList.get(i).setOpacity(0);
            }

            Answer.setText("Current action");


            boolean eng = true;
            for (int i = 0; i < field.getCharacters().length(); i++) {
                if ((field.getCharacters().charAt(i) == '.') || (field.getCharacters().charAt(i) >= '0' && field.getCharacters().charAt(i) <= '9') || (field.getCharacters().charAt(i) >= 'A' && field.getCharacters().charAt(i) <= 'Z') || (field.getCharacters().charAt(i) >= 'a' && field.getCharacters().charAt(i) <= 'z')) {
                    eng = true;
                } else {
                    eng = false;
                    Answer.setText("Non-existent domain or host of domain is not exist");
                    alert.setTitle("Fail search");
                    alert.setHeaderText("Non-existent domain or host of domain is not exist");
                    alert.setContentText("Check the correctness of the entered URL. Maybe there is a space.");
                    alert.showAndWait();
                    break;
                }
            }
            if (eng) {
                String url = String.valueOf(field.getCharacters());
                time = System.currentTimeMillis();
                String result = DNSearch.main(url);
              //  progressBar.progressProperty().unbind();
             //   progressBar.progressProperty().bind(DNSearch.main(url).progressProperty());

                if (result == "Non-existent domain or host of domain is not exist") {
                    Answer.setText("Non-existent domain or host of domain is not exist");
                    alert.setTitle("Fail search");
                    alert.setHeaderText("Non-existent domain or host of domain is not exist");
                    alert.setContentText("Check the correctness of the entered URL. Maybe there is a space.");
                    alert.showAndWait();
                    return;
                }
                if (result == "No internet connection"){
                    Answer.setText("No internet connection");
                    alert.setTitle("Fail search");
                    alert.setHeaderText("No internet connection");
                    alert.setContentText("Check the internet connection");
                    alert.showAndWait();
                    return;
                }
                //System.out.println(DNSearch.count);
                for (int i = 0; i < DNSearch.count + 1; i++) {
                    ServerList.get(i).setOpacity(1);
                    if (i != DNSearch.count)
                        ServerList.get(i).setText("Server " + (i + 1));
                    int j = i;
                    servers.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
                        ServerList.get(j).setFont(Font.font(newValue.getHeight() / 40));
                        Domains.get(j).setFont(Font.font(servers.getHeight() / 45));
                        Domains.get(j).setLayoutY(servers.getHeight() / 38);
                        NameServers.get(j).setFont(Font.font(servers.getHeight() / 45));
                        NameServers.get(j).setPrefHeight(servers.getHeight() / 2);
                        NameServers.get(j).setLayoutY(servers.getHeight() / 12);
                        types.get(j).setFont(Font.font(servers.getHeight() / 55));
                        types.get(j).setLayoutY(servers.getHeight() / 25);
                    });
                }
                ServerList.get(DNSearch.count).setText("Answer");



                for (int i = 0; i < DNSearch.count; i++) {
                    Domains.get(i).setText(DNSearch.domains[i + 1]);
                    NameServers.get(i).setText("             Nameservers: \n" + Servers.get(i));
                    types.get(i).setText("Query type: " + DNSearch.QueryTypes[i + 1]);
                    TTLs.get(i).setText("TTL:" + DNSearch.ttl[i]);

                }
                DNSearch.QueryTypes = new String[10];
                Servers.clear();
                Domains.get(DNSearch.count).setText(url);
                String answer = "";
                for (int i = 0; i < DNSearch.finalAnswerTypeA.length; i++) {
                    answer += DNSearch.finalAnswerTypeA[i] + "\n";
                }
                NameServers.get(DNSearch.count).setText("Answer: \n" + answer);
                types.get(DNSearch.count).setText("Query type : A");
                TTLs.get(DNSearch.count).setText("TTL: " + DNSearch.ttl[DNSearch.count]);
            }
//            System.out.println("count: "+ count+ " branch: "+ branch);
            if (rec.size()!=2){
            if (count-branch>= branch)
                levelsOfTree =  count-branch+1;
            else levelsOfTree = branch+1;}
            else levelsOfTree = count + 1 - rec.get(0)-rec.get(1);
            System.out.println("LevelsOfTree: "+levelsOfTree);
            if (branch==count)
                branch=0;
            oldCount = count;
            oldBranch = branch;


            System.out.println("Branch: "+branch);
            System.out.println("count: "+count);
            System.out.println("Rec: "+rec.size());
            System.out.println("root: "+Domains.get(0).getText());

            GraphicsContext context = canvas.getGraphicsContext2D();
            drawing(context);
            flag= 0;

            Answer.setText("Succsess");

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Answer.setText("Non-existent domain or host of domain is not exist");
            alert.setTitle("Fail search");
            alert.setHeaderText("Non-existent domain or host of domain is not exist");
            alert.setContentText("Check the correctness of the entered URL. Maybe there is a space.");
            alert.showAndWait();
        }
        catch (IndexOutOfBoundsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Answer.setText("Non-existent domain or host of domain is not exist");
            alert.setTitle("Fail search");
            alert.setHeaderText("Non-existent domain or host of domain is not exist");
            alert.setContentText("Check the correctness of the entered URL. Maybe there is a space.");
            alert.showAndWait();
        }
    }
        private void drawing(GraphicsContext gc) {
        final double[] VBOXh = {VBOXCan.getHeight()};
        final double[] VBOXw = {VBOXCan.getWidth()};
        final double[] serverHeight = {Math.round(canvas.getHeight() / (2.5 * levelsOfTree))};
        final double[] serverWidth = {(canvas.getWidth() / 19 )};
        final double[] serverX = {(Math.round((canvas.getWidth()/2-serverWidth[0])))};
        final double[] serverY = {(Math.round((canvas.getHeight()/levelsOfTree-serverHeight[0]))/4)};

        canvas.setHeight(VBOXh[0]/1.2);
        canvas.setWidth(VBOXw[0]/1.2);
                 gc.setFont(new Font("Sans-Serif", servers.getHeight() / 55));
                 gc.setFill(Color.rgb(244,244,244));
                 gc.fillRect(0, 0, VBOXw[0], VBOXh[0]);

            if (rec.size() == 0){
                for (int i = 0; i < count; i++) {
                    if (i == 0){
                        gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                        gc.setFill(Color.BLACK);
                        gc.fillText(Domains.get(i).getText(), serverX[0] + serverWidth[0] / 4, serverY[0] + serverHeight[0] / 2, serverWidth[0] / 4 * 3);
                    }
                    else {
                        gc.beginPath();
                        gc.moveTo(serverX[0]+serverWidth[0]/2, serverY[0]+serverHeight[0]);
                        gc.lineTo(serverX[0]+serverWidth[0]/2, serverY[0] +2* serverHeight[0]);
                        gc.stroke();
                        gc.closePath();
                        serverY[0]+= Math.round(2*serverHeight[0]);
                        gc.strokeRect(serverX[0], serverY[0],serverWidth[0],serverHeight[0]);
                        gc.setFill(Color.BLACK);
                        if (Domains.get(i).getText().length() > 10){
                            String domain = Domains.get(i).getText();
                            domain =  domain.replaceFirst("\\.",".\n");
                            gc.fillText(domain,serverX[0]+serverWidth[0]/10,serverY[0]+serverHeight[0]/3,serverWidth[0]/10*9  );
                        }
                        else {
                        gc.fillText(Domains.get(i).getText(),serverX[0]+serverWidth[0]/4,serverY[0]+serverHeight[0]/2,serverWidth[0]/4*3);
                        }
                    }
                }
            }
            else if (rec.size() == 1) {
                for (int i = 0; i < count; i++) {
                    if (i >= count - branch) {
                        continue;
                    }
                    else {
                        if (i == 0) {
                            gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                            gc.setFill(Color.BLACK);
                            gc.fillText(Domains.get(i).getText(), serverX[0] + serverWidth[0] / 4, serverY[0] + serverHeight[0] / 2, serverWidth[0] / 4 * 3);
                        } else {
                            gc.beginPath();
                            gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
                            gc.lineTo(serverX[0] - serverWidth[0] / 2, serverY[0] + 2 * serverHeight[0]);
                            gc.stroke();
                            gc.closePath();
                            serverX[0] -= serverWidth[0];
                            serverY[0] += Math.round(2 * serverHeight[0]);
                            gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                            gc.setFill(Color.BLACK);

                            if (Domains.get(i).getText().length() > 10) {
                                String domain = Domains.get(i).getText();
                                domain = domain.replaceFirst("\\.", ".\n");
                                gc.fillText(domain, serverX[0] + serverWidth[0] / 10, serverY[0] + serverHeight[0] / 3, serverWidth[0] / 10 * 9);
                            } else {
                                gc.fillText(Domains.get(i).getText(), serverX[0] + serverWidth[0] / 4, serverY[0] + serverHeight[0] / 2, serverWidth[0] / 4 * 3);
                            }

                        }
                    }
                }
                double oldX = serverX[0];
                double oldY = serverY[0];

                serverX[0] = (Math.round((canvas.getWidth()/2-serverWidth[0])));
                serverY[0] = (Math.round((canvas.getHeight()/levelsOfTree-serverHeight[0]))/4);
                for (int i = count-branch; i < branch+(count-branch); i++){
                    gc.beginPath();
                    gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
                    gc.lineTo(serverX[0] + serverWidth[0]*1.5, serverY[0] + 2 * serverHeight[0]);
                    gc.stroke();
                    gc.closePath();
                    serverX[0] += serverWidth[0];
                    serverY[0] += Math.round(2 * serverHeight[0]);
                    gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                    gc.setFill(Color.BLACK);
                if(count == oldCount) {
                    if (Domains.get(i).getText().length() > 10) {
                        String domain = Domains.get(i).getText();
                        domain = domain.replaceFirst("\\.", ".\n");
                        gc.fillText(domain, serverX[0] + serverWidth[0] / 10, serverY[0] + serverHeight[0] / 3, serverWidth[0] / 10 * 9);
                    } else {
                        gc.fillText(Domains.get(i).getText(), serverX[0] + serverWidth[0] / 4, serverY[0] + serverHeight[0] / 2, serverWidth[0] / 4 * 3);
                    }
                }
                else {
                    if (Domains.get(i+1).getText().length() > 10) {
                        String domain = Domains.get(i+1).getText();
                        domain = domain.replaceFirst("\\.", ".\n");
                        gc.fillText(domain, serverX[0] + serverWidth[0] / 10, serverY[0] + serverHeight[0] / 3, serverWidth[0] / 10 * 9);
                    } else {
                        gc.fillText(Domains.get(i+1).getText(), serverX[0] + serverWidth[0] / 4, serverY[0] + serverHeight[0] / 2, serverWidth[0] / 4 * 3);
                    }
                }

                }
                serverX[0]= oldX;
                serverY[0]= oldY;

                gc.beginPath();
                gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
                gc.lineTo(serverX[0] - serverWidth[0] / 2, serverY[0] + 2 * serverHeight[0]);
                gc.stroke();
                gc.closePath();
                serverX[0] -= serverWidth[0];
                serverY[0] += Math.round(2 * serverHeight[0]);
                gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                gc.setFill(Color.BLACK);
                if (count == oldCount) {
                    if (Domains.get(count).getText().length() > 10) {
                        String domain = Domains.get(count).getText();
                        domain = domain.replaceFirst("\\.", ".\n");
                        gc.fillText(domain, serverX[0] + serverWidth[0] / 10, serverY[0] + serverHeight[0] / 3, serverWidth[0] / 10 * 9);
                    } else {
                        gc.fillText(Domains.get(count).getText(), serverX[0] + serverWidth[0] / 4, serverY[0] + serverHeight[0] / 2, serverWidth[0] / 4 * 3);
                    }

                }
                 else {
                   if (Domains.get(count-branch).getText().length() > 10) {
                        String domain = Domains.get(count-branch).getText();
                        domain = domain.replaceFirst("\\.", ".\n");
                        gc.fillText(domain, serverX[0] + serverWidth[0] / 10, serverY[0] + serverHeight[0] / 3, serverWidth[0] / 10 * 9);
                    } else {
                        gc.fillText(Domains.get(count-branch).getText(), serverX[0] + serverWidth[0] / 4, serverY[0] + serverHeight[0] / 2, serverWidth[0] / 4 * 3);
                    }
                }
            }
            else if (rec.size() == 2){
                for (int i = 0; i < count; i++) {
                    if (i >= count - rec.get(0)-rec.get(1)) {
                        continue;
                    } else {
                        if (i == 0) {
                            gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                            gc.setFill(Color.BLACK);
                            gc.fillText(Domains.get(i).getText(), serverX[0] + serverWidth[0] / 4, serverY[0] + serverHeight[0] / 2, serverWidth[0] / 4 * 3);
                        } else {
                            gc.beginPath();
                            gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
                            gc.lineTo(serverX[0] - serverWidth[0], serverY[0] + 2 * serverHeight[0]);
                            gc.stroke();
                            gc.closePath();
                            serverX[0] -= 1.5*serverWidth[0];
                            serverY[0] += Math.round(2 * serverHeight[0]);
                            gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                            gc.setFill(Color.BLACK);

                            if (Domains.get(i).getText().length() > 10) {
                                String domain = Domains.get(i).getText();
                                domain = domain.replaceFirst("\\.", ".\n");
                                gc.fillText(domain, serverX[0] + serverWidth[0] / 10, serverY[0] + serverHeight[0] / 3, serverWidth[0] / 10 * 9);
                            } else {
                                gc.fillText(Domains.get(i).getText(), serverX[0] + serverWidth[0] / 4, serverY[0] + serverHeight[0] / 2, serverWidth[0] / 4 * 3);
                            }
                        }
                    }

                }
                double oldX = serverX[0];
                double oldY = serverY[0];
                serverX[0] = (Math.round((canvas.getWidth()/2-serverWidth[0])));
                serverY[0] = (Math.round((canvas.getHeight()/levelsOfTree-serverHeight[0]))/4);
                for (int i = count-rec.get(0)-rec.get(1); i < count-rec.get(1); i ++){
                    gc.beginPath();
                    gc.moveTo(serverX[0]+serverWidth[0]/2, serverY[0]+serverHeight[0]);
                    gc.lineTo(serverX[0]+serverWidth[0]/2, serverY[0] +2* serverHeight[0]);
                    gc.stroke();
                    gc.closePath();
                    serverY[0]+= Math.round(2*serverHeight[0]);
                    gc.strokeRect(serverX[0], serverY[0],serverWidth[0],serverHeight[0]);
                    gc.setFill(Color.BLACK);
                    if (Domains.get(i).getText().length() > 10){
                        String domain = Domains.get(i).getText();
                        domain =  domain.replaceFirst("\\.",".\n");
                        gc.fillText(domain,serverX[0]+serverWidth[0]/10,serverY[0]+serverHeight[0]/3,serverWidth[0]/10*9  );
                    }
                    else {
                        gc.fillText(Domains.get(i).getText(),serverX[0]+serverWidth[0]/4,serverY[0]+serverHeight[0]/2,serverWidth[0]/4*3);
                    }
                }
                serverX[0] = (Math.round((canvas.getWidth()/2-serverWidth[0])));
                serverY[0] = (Math.round((canvas.getHeight()/levelsOfTree-serverHeight[0]))/4);
                int j = count+rec.get(0)-rec.get(1);
                System.out.println("count+rec.get(0)-rec.get(1): "+j);
                System.out.println("count: "+count);

                for (int i = count-rec.get(0); i < count; i ++) {
                    gc.beginPath();
                    gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
                    gc.lineTo(serverX[0] + serverWidth[0]*2, serverY[0] + 2 * serverHeight[0]);
                    gc.stroke();
                    gc.closePath();
                    serverX[0] += 1.5*serverWidth[0];
                    serverY[0] += Math.round(2 * serverHeight[0]);
                    gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                    gc.setFill(Color.BLACK);

                    if (Domains.get(i).getText().length() > 10) {
                        String domain = Domains.get(i).getText();
                        domain = domain.replaceFirst("\\.", ".\n");
                        gc.fillText(domain, serverX[0] + serverWidth[0] / 10, serverY[0] + serverHeight[0] / 3, serverWidth[0] / 10 * 9);
                    } else {
                        gc.fillText(Domains.get(i).getText(), serverX[0] + serverWidth[0] / 4, serverY[0] + serverHeight[0] / 2, serverWidth[0] / 4 * 3);
                    }



                }
                serverX[0]= oldX;
                serverY[0]= oldY;
                gc.beginPath();
                gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
                gc.lineTo(serverX[0] - serverWidth[0] / 2, serverY[0] + 2 * serverHeight[0]);
                gc.stroke();
                gc.closePath();
                serverX[0] -= serverWidth[0];
                serverY[0] += Math.round(2 * serverHeight[0]);
                gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                gc.setFill(Color.BLACK);

                if (Domains.get(count).getText().length() > 10) {
                    String domain = Domains.get(count).getText();
                    domain = domain.replaceFirst("\\.", ".\n");
                    gc.fillText(domain, serverX[0] + serverWidth[0] / 10, serverY[0] + serverHeight[0] / 3, serverWidth[0] / 10 * 9);
                } else {
                    gc.fillText(Domains.get(count).getText(), serverX[0] + serverWidth[0] / 4, serverY[0] + serverHeight[0] / 2, serverWidth[0] / 4 * 3);
                }
            }

            //onresize
            VBOXCan.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
                     VBOXh[0] = newValue.getHeight();
                     VBOXw[0] = newValue.getWidth();
                     canvas.setHeight(VBOXh[0] / 1.2);
                     canvas.setWidth(VBOXw[0] / 1.2);
                     serverX[0] = Math.round((canvas.getWidth() / 2 - serverWidth[0]));
                     serverY[0] = Math.round((canvas.getHeight() / levelsOfTree - serverHeight[0])) / 4;
                     serverHeight[0] = Math.round(canvas.getHeight() / (2.5 * levelsOfTree));
                     serverWidth[0] = Math.round(canvas.getWidth() / 19);
                     gc.setFont(new Font("Sans-Serif", newValue.getHeight() / 55));
                     gc.setFill(Color.rgb(244, 244, 244));
                     gc.fillRect(0, 0, VBOXw[0], VBOXh[0]);


                     //draw
                    if (rec.size() == 0){
                        for (int i = 0; i < count; i++) {
                            if (i == 0){
                                gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                                gc.setFill(Color.BLACK);
                                gc.fillText(Domains.get(i).getText(), serverX[0] + serverWidth[0] / 4, serverY[0] + serverHeight[0] / 2, serverWidth[0] / 4 * 3);
                        }
                            else {
                                gc.beginPath();
                                gc.moveTo(serverX[0]+serverWidth[0]/2, serverY[0]+serverHeight[0]);
                                gc.lineTo(serverX[0]+serverWidth[0]/2, serverY[0] +2* serverHeight[0]);
                                gc.stroke();
                                gc.closePath();
                                serverY[0]+= Math.round(2*serverHeight[0]);
                                gc.strokeRect(serverX[0], serverY[0],serverWidth[0],serverHeight[0]);
                                gc.setFill(Color.BLACK);
                                if (Domains.get(i).getText().length() > 10){
                                    String domain = Domains.get(i).getText();
                                    domain =  domain.replaceFirst("\\.",".\n");
                                    gc.fillText(domain,serverX[0]+serverWidth[0]/10,serverY[0]+serverHeight[0]/3,serverWidth[0]/10*9  );
                                }
                                else {
                                    gc.fillText(Domains.get(i).getText(),serverX[0]+serverWidth[0]/4,serverY[0]+serverHeight[0]/2,serverWidth[0]/4*3);
                                }
                            }
                        }
                    }



                 });
    }
    public void StepBack(ActionEvent actionEvent) {
        if (rec.size() == 0) {
            GraphicsContext context = canvas.getGraphicsContext2D();
            drawing(context);
            count--;
            for (int i = 0; i < 9; i++)
                ServerList.get(i).setOpacity(0);
            for (int i = 0; i < count + 1; i++) {
                ServerList.get(i).setOpacity(1);

            }

        } else if (rec.size() == 1) {
            if (count == oldCount) {
                count--;
                GraphicsContext context = canvas.getGraphicsContext2D();
                drawing(context);
                for (int i = 0; i < 9; i++)
                    ServerList.get(i).setOpacity(0);
                for (int i = 0; i < count + 1; i++) {
                    ServerList.get(i).setOpacity(1);
                }
            }
            else if (branch != 0){
                branch--;
                count--;
                GraphicsContext context = canvas.getGraphicsContext2D();
                drawing(context);
                for (int i = 0; i < 9; i++)
                    ServerList.get(i).setOpacity(0);
                for (int i = 0; i < count + 1; i++) {
                    ServerList.get(i).setOpacity(1);
                }

            }
            else {
                if (count >= 0){
                    for(int i = 0; i < rec.size();i++){
                        oldBranches[i] = rec.get(i);
                    }
                    rec.clear();

                    GraphicsContext context = canvas.getGraphicsContext2D();
                    drawing(context);
                count--;
                for (int i = 0; i < 9; i++)
                    ServerList.get(i).setOpacity(0);
                for (int i = 0; i < count + 1; i++) {
                    ServerList.get(i).setOpacity(1);
                }
                }
            }
        }
        else if(rec.size() == 2){
            if (count == oldCount){
                count--;
                GraphicsContext context = canvas.getGraphicsContext2D();
                drawing(context);
                for (int i = 0; i < 9; i++)
                    ServerList.get(i).setOpacity(0);
                for (int i = 0; i < count + 1; i++) {
                    ServerList.get(i).setOpacity(1);
                }
            }
            else if(rec.get(1)>-1){
            rec.set(1,rec.get(1)-1);//ВОТ ЗДЕСЬ ХЗ
            if (flag == 0) {
                count--;
                flag=1;
            }
            else if(flag==1)
                flag=0;

            GraphicsContext context = canvas.getGraphicsContext2D();
            drawing(context);

            for (int i = 0; i < 9; i++)
                ServerList.get(i).setOpacity(0);
            for (int i = 0; i < count + 1; i++) {
                ServerList.get(i).setOpacity(1);
                }
            }
            else {
                rec.remove(1);
            }
        }
    }

    public void StepForward(ActionEvent actionEvent) {
    }
    public void Play(ActionEvent actionEvent){
        DNSearch.stop = 0;
        count = oldCount;
        branch = oldBranch;
        for (int i = 0; i < 9; i++ )
            ServerList.get(i).setOpacity(0);
        for (int i = 0; i < count+1; i++){
            ServerList.get(i).setOpacity(1);
            GraphicsContext context = canvas.getGraphicsContext2D();
            drawing(context);


        }
    }

    public void Pause(ActionEvent actionEvent){
        DNSearch.stop = 0;
    }
    public void Stop(ActionEvent actionEvent){
        DNSearch.stop = 1;
    }
}
/*
*
*
*
*/