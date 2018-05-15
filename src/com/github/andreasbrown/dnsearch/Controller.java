package com.github.andreasbrown.dnsearch;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.plugin.cache.OldCacheEntry;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

import static com.github.andreasbrown.dnsearch.DNSearch.*;

public class Controller implements Initializable {

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
    private ProgressBar progressBar;
    @FXML
    private Label percent;

    Task search;

    public static long time = 0;
    public static List<TitledPane> ServerList = new ArrayList<>();
    public static List<Label> Domains = new ArrayList<>();
    public static List<Label> NameServers = new ArrayList<>();
    public static List<Label> types = new ArrayList<>();
    public static List<Label> TTLs = new ArrayList<>();
    private static int levelsOfTree = 0;
    public static int oldCount = count;
    public static int oldBranch = branch;
    public static int flag = -1;
    public static int flag2 = 0;
    private static int[] oldBranches = new int[5];
    private static List<Integer> oldRec = new ArrayList<>();


    public void StartSearch(ActionEvent actionEvent) throws IOException {
        try {
            progressBar.setProgress(0.1);
            percent.setText("10%");


            Alert alert = new Alert(Alert.AlertType.ERROR);
            flag2 = 0;
            rec.clear();
            oldRec.clear();
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

            Answer.setText("Статус поиска");

            boolean eng = true;
            for (int i = 0; i < field.getCharacters().length(); i++) {
                if ((field.getCharacters().charAt(i) == '.') || (field.getCharacters().charAt(i) >= '0' && field.getCharacters().charAt(i) <= '9') || (field.getCharacters().charAt(i) >= 'A' && field.getCharacters().charAt(i) <= 'Z') || (field.getCharacters().charAt(i) >= 'a' && field.getCharacters().charAt(i) <= 'z')) {
                    eng = true;
                } else {
                    eng = false;
                    Answer.setText("Домен не существует или у домена нет хоста");
                    alert.setTitle("Поиск потерпел фиаско!");
                    alert.setHeaderText("Домен не существует или у домена нет хоста");
                    alert.setContentText("Проверьте правильность введенного URL. Недопустимо наличие пробелов, кириллицы и любых специальных символов кроме точки.");
                    alert.showAndWait();
                    break;
                }
            }



            if (eng) {
                progressBar.setProgress(0.4);
                percent.setText("40%");
                Thread.sleep(50);
                String url = String.valueOf(field.getCharacters());
                time = System.currentTimeMillis();
                String result = DNSearch.main(url);
                Thread.sleep(50);
                progressBar.setProgress(1.0);
                percent.setText("100%");



                if (result == "Non-existent domain or host of domain is not exist") {
                    Answer.setText("Домен не существует или у домена нет хоста");
                    alert.setTitle("Поиск потерпел фиаско!");
                    alert.setHeaderText("Домен не существует или у домена нет хоста");
                    alert.setContentText("Проверьте правильность введенного URL. Недопустимо наличие пробелов, кириллицы и любых специальных символов кроме точки.");
                    alert.showAndWait();
                    return;
                }
                if (result == "No internet connection") {
                    Answer.setText("Подключение к сети отсутствует");
                    alert.setTitle("Поиск потерпел фиаско!");
                    alert.setHeaderText("Подключение к сети отсутствует");
                    alert.setContentText("Проверьте подключение к интернету");
                    alert.showAndWait();
                    return;
                }

                for (int i = 0; i < DNSearch.count + 1; i++) {
                    ServerList.get(i).setOpacity(1);
                    if (i != DNSearch.count)
                        ServerList.get(i).setText("Сервер " + (i + 1));
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
                ServerList.get(DNSearch.count).setText("Ответ");


                for (int i = 0; i < DNSearch.count; i++) {
                    Domains.get(i).setText(DNSearch.domains[i + 1]);
                    NameServers.get(i).setText("             Серверы имён: \n" + Servers.get(i));
                    types.get(i).setText("Тип запроса: " + DNSearch.QueryTypes[i + 1] + "      Тип сервера: Authority");
                    TTLs.get(i).setText("TTL:" + DNSearch.ttl[i]);

                }
                DNSearch.QueryTypes = new String[10];
                Servers.clear();
                Domains.get(DNSearch.count).setText(url);
                String answer = "";
                for (int i = 0; i < DNSearch.finalAnswerTypeA.length; i++) {
                    answer += DNSearch.finalAnswerTypeA[i] + "\n";
                }
                NameServers.get(DNSearch.count).setText("Ответ: \n" + answer);
                types.get(DNSearch.count).setText("Тип запроса : A      Тип сервера: Authority");
                TTLs.get(DNSearch.count).setText("TTL: " + DNSearch.ttl[DNSearch.count]);
            }
            if (rec.size() != 2) {
                if (count - branch >= branch)
                    levelsOfTree = count - branch + 1;
                else levelsOfTree = branch + 1;
            } else levelsOfTree = count + 1 - rec.get(0) - rec.get(1);
            if (branch == count)
                branch = 0;
            oldCount = count;
            oldBranch = branch;
            if (rec.size() == 2)
                oldRec.add(rec.get(1));

            flag = 0;

            GraphicsContext context = canvas.getGraphicsContext2D();
            drawing(context);
            drawing(context);
            Answer.setText("Успешно");

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Answer.setText("Домен не существует или у домена нет хоста");
            alert.setTitle("Поиск потерпел фиаско!");
            alert.setHeaderText("Домен не существует или у домена нет хоста");
            alert.setContentText("Проверьте правильность введенного URL. Недопустимо наличие пробелов, кириллицы и любых специальных символов кроме точки.");
            alert.showAndWait();
        } catch (IndexOutOfBoundsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Answer.setText("Домен не существует или у домена нет хоста");
            alert.setTitle("Поиск потерпел фиаско!");
            alert.setHeaderText("Домен не существует или у домена нет хоста");
            alert.setContentText("Проверьте правильность введенного URL. Недопустимо наличие пробелов, кириллицы и любых специальных символов кроме точки.");
            alert.showAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void drawing(GraphicsContext gc) {
        final double[] VBOXh = {VBOXCan.getHeight()};
        final double[] VBOXw = {VBOXCan.getWidth()};
        final double[] serverHeight = {Math.round(canvas.getHeight() / (2.5 * levelsOfTree))};
        final double[] serverWidth = {(canvas.getWidth() / 19)};
        final double[] serverX = {(Math.round((canvas.getWidth() / 2 - serverWidth[0])))};
        final double[] serverY = {(Math.round((canvas.getHeight() / levelsOfTree - serverHeight[0])) / 4)};

        canvas.setHeight(VBOXh[0] / 1.2);
        canvas.setWidth(VBOXw[0] / 1.2);
        gc.setFont(new Font("Sans-Serif", servers.getHeight() / 55));
            gc.setTextAlign(TextAlignment.CENTER);
        gc.setFill(Color.rgb(244, 244, 244));
        gc.fillRect(0, 0, VBOXw[0], VBOXh[0]);
        double y = serverY[0];
        for (int i = 0; i < levelsOfTree; i++){

            gc.beginPath();
            gc.moveTo(0, y + serverHeight[0]/2);
            gc.lineTo(serverWidth[0]*2.5,y + serverHeight[0]/2);
            gc.stroke();
            gc.closePath();
            gc.setFill(Color.BLACK);
            gc.setTextAlign(TextAlignment.CENTER);
            if (i == 0){
                gc.fillText("Корневой уровень",0+serverWidth[0]*1.25,y+10,serverWidth[0]*2.5);
            }
            else if(i == 1){
                gc.fillText("Верхний уровень",0+serverWidth[0]*1.25,y+10,serverWidth[0]*2.5);
            }
            else                gc.fillText(i +" уровень",0+serverWidth[0]*1.25,y+10,serverWidth[0]*2.5);

            y+= 2*serverHeight[0];

        }
        if (rec.size() == 0) {
            for (int i = 0; i < count; i++) {
                if (i == 0) {

                    gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                    gc.setFill(Color.BLACK);
                     gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                } else {

                    gc.beginPath();
                    gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
                    gc.lineTo(serverX[0] + serverWidth[0] / 2, serverY[0] + 2 * serverHeight[0]);
                    gc.stroke();
                    gc.closePath();
                    serverY[0] += Math.round(2 * serverHeight[0]);
                    gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                    gc.setFill(Color.BLACK);
                    if (Domains.get(i).getText().length() > 10) {
                        String domain = Domains.get(i).getText();
                        domain = domain.replaceFirst("\\.", ".\n");
                                    gc.fillText((i + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                    } else {
                                     gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0] );
                    }
                }
            }
        } else if (rec.size() == 1) {

            for (int i = 0; i < count; i++) {
                if (i >= count - branch) {
                    continue;
                } else {
                    if (i == 0) {
                        gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                        gc.setFill(Color.BLACK);
                        gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
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
                            gc.fillText((i + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0] );
                        } else {
                            gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                        }

                    }
                }
            }
            double oldX = serverX[0];
            double oldY = serverY[0];

            serverX[0] = (Math.round((canvas.getWidth() / 2 - serverWidth[0])));
            serverY[0] = (Math.round((canvas.getHeight() / levelsOfTree - serverHeight[0])) / 4);
            for (int i = count - branch; i < branch + (count - branch); i++) {
                gc.beginPath();
                gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
                gc.lineTo(serverX[0] + serverWidth[0] * 1.5, serverY[0] + 2 * serverHeight[0]);
                gc.stroke();
                gc.closePath();
                serverX[0] += serverWidth[0];
                serverY[0] += Math.round(2 * serverHeight[0]);
                gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                gc.setFill(Color.BLACK);
                if (count == oldCount) {
                    if (Domains.get(i).getText().length() > 10) {
                        String domain = Domains.get(i).getText();
                        domain = domain.replaceFirst("\\.", ".\n");
                        gc.fillText((i + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0] );
                    } else {
                        gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                    }
                } else {
                    if (Domains.get(i + 1).getText().length() > 10) {
                        String domain = Domains.get(i + 1).getText();
                        domain = domain.replaceFirst("\\.", ".\n");
                        gc.fillText((i + 2) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0] );
                    } else {
                        gc.fillText((i + 2) + ": " + Domains.get(i + 1).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                    }
                }

            }
            serverX[0] = oldX;
            serverY[0] = oldY;

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
                    gc.fillText((count + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                } else {
                    gc.fillText((count + 1) + ": " + Domains.get(count).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                }

            } else {
                if (Domains.get(count - branch).getText().length() > 10) {
                    String domain = Domains.get(count - branch).getText();
                    domain = domain.replaceFirst("\\.", ".\n");
                    gc.fillText((count - branch + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                } else {
                    gc.fillText((count - branch + 1) + ": " + Domains.get(count - branch).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                }
            }
        } else if (rec.size() == 2) {
            for (int i = 0; i < count; i++) {
                if (i >= count - rec.get(0) - rec.get(1)) {
                    continue;
                } else {
                    if (i == 0) {
                        gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                        gc.setFill(Color.BLACK);
                        gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                    } else {
                        gc.beginPath();
                        gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
                        gc.lineTo(serverX[0] - serverWidth[0], serverY[0] + 2 * serverHeight[0]);
                        gc.stroke();
                        gc.closePath();
                        serverX[0] -= 1.5 * serverWidth[0];
                        serverY[0] += Math.round(2 * serverHeight[0]);
                        gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                        gc.setFill(Color.BLACK);
                        if (count == oldCount) {
                            if (Domains.get(i).getText().length() > 8) {
                                String domain = Domains.get(i).getText();
                                domain = domain.replaceFirst("\\.", ".\n");
                                gc.fillText((i + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                            } else {
                                gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                            }
                        } else {
                            if (Domains.get(i).getText().length() > 8) {
                                String domain = Domains.get(i).getText();
                                domain = domain.replaceFirst("\\.", ".\n");
                                gc.fillText((i + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                            } else {
                                gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                            }
                        }
                    }
                }
            }
            double oldX = serverX[0];
            double oldY = serverY[0];
            serverX[0] = (Math.round((canvas.getWidth() / 2 - serverWidth[0])));
            serverY[0] = (Math.round((canvas.getHeight() / levelsOfTree - serverHeight[0])) / 4);
            for (int i = count - rec.get(0) - rec.get(1); i < count - rec.get(1); i++) {
                gc.beginPath();
                gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
                gc.lineTo(serverX[0] + serverWidth[0] / 2, serverY[0] + 2 * serverHeight[0]);
                gc.stroke();
                gc.closePath();
                serverY[0] += Math.round(2 * serverHeight[0]);
                gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                gc.setFill(Color.BLACK);

                if (count == oldCount) {
                    if (Domains.get(i).getText().length() > 8) {
                        String domain = Domains.get(i).getText();
                        domain = domain.replaceFirst("\\.", ".\n");
                        gc.fillText((i + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                    } else {
                        gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                    }
                } else {
                    if (Domains.get(i + 1).getText().length() > 8) {
                        String domain = Domains.get(i + 1).getText();
                        domain = domain.replaceFirst("\\.", ".\n");
                        gc.fillText((i + 2) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                    } else {
                        gc.fillText((i + 2) + ": " + Domains.get(i + 1).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                    }
                }
            }
            serverX[0] = (Math.round((canvas.getWidth() / 2 - serverWidth[0])));
            serverY[0] = (Math.round((canvas.getHeight() / levelsOfTree - serverHeight[0])) / 4);
            int j = count + rec.get(0) - rec.get(1);
            for (int i = count - rec.get(1); i < count; i++) {
                gc.beginPath();
                gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
                gc.lineTo(serverX[0] + serverWidth[0] * 2, serverY[0] + 2 * serverHeight[0]);
                gc.stroke();
                gc.closePath();
                serverX[0] += 1.5 * serverWidth[0];
                serverY[0] += Math.round(2 * serverHeight[0]);
                gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                gc.setFill(Color.BLACK);
                if (count == oldCount) {


                    if (Domains.get(i).getText().length() > 8) {
                        String domain = Domains.get(i).getText();
                        domain = domain.replaceFirst("\\.", ".\n");
                        gc.fillText((i + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                    } else {
                        gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0] );
                    }
                } else {
                    if (Domains.get(i + 1).getText().length() > 8) {
                        String domain = Domains.get(i + 1).getText();
                        domain = domain.replaceFirst("\\.", ".\n");
                        gc.fillText((i + 2) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                    } else {
                        gc.fillText((i + 2) + ": " + Domains.get(i + 1).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0] );
                    }
                }


            }
            int ServNumber;
            if (count != oldCount) {
                ServNumber = count - rec.get(0) - rec.get(1);
            } else {
                ServNumber = count;
            }
            serverX[0] = oldX;
            serverY[0] = oldY;
            gc.beginPath();
            gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
            gc.lineTo(serverX[0] - serverWidth[0] / 2, serverY[0] + 2 * serverHeight[0]);
            gc.stroke();
            gc.closePath();
            serverX[0] -= serverWidth[0];
            serverY[0] += Math.round(2 * serverHeight[0]);
            gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
            gc.setFill(Color.BLACK);

            if (Domains.get(ServNumber).getText().length() > 8) {
                String domain = Domains.get(ServNumber).getText();
                domain = domain.replaceFirst("\\.", ".\n");
                gc.fillText((ServNumber + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0] );
            } else {
                gc.fillText((ServNumber + 1) + ": " + Domains.get(ServNumber).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
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
            if (rec.size() == 0) {
                for (int i = 0; i < count; i++) {
                    if (i == 0) {

                        gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                        gc.setFill(Color.BLACK);
                        gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                    } else {

                        gc.beginPath();
                        gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
                        gc.lineTo(serverX[0] + serverWidth[0] / 2, serverY[0] + 2 * serverHeight[0]);
                        gc.stroke();
                        gc.closePath();
                        serverY[0] += Math.round(2 * serverHeight[0]);
                        gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                        gc.setFill(Color.BLACK);
                        if (Domains.get(i).getText().length() > 10) {
                            String domain = Domains.get(i).getText();
                            domain = domain.replaceFirst("\\.", ".\n");
                            gc.fillText((i + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                        } else {
                            gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                        }
                    }
                }
            } else if (rec.size() == 1) {

                for (int i = 0; i < count; i++) {
                    if (i >= count - branch) {
                        continue;
                    } else {
                        if (i == 0) {
                            gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                            gc.setFill(Color.BLACK);
                            gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
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
                                gc.fillText((i + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                            } else {
                                gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                            }

                        }
                    }
                }
                double oldX = serverX[0];
                double oldY = serverY[0];

                serverX[0] = (Math.round((canvas.getWidth() / 2 - serverWidth[0])));
                serverY[0] = (Math.round((canvas.getHeight() / levelsOfTree - serverHeight[0])) / 4);
                for (int i = count - branch; i < branch + (count - branch); i++) {
                    gc.beginPath();
                    gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
                    gc.lineTo(serverX[0] + serverWidth[0] * 1.5, serverY[0] + 2 * serverHeight[0]);
                    gc.stroke();
                    gc.closePath();
                    serverX[0] += serverWidth[0];
                    serverY[0] += Math.round(2 * serverHeight[0]);
                    gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                    gc.setFill(Color.BLACK);
                    if (count == oldCount) {
                        if (Domains.get(i).getText().length() > 10) {
                            String domain = Domains.get(i).getText();
                            domain = domain.replaceFirst("\\.", ".\n");
                            gc.fillText((i + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                        } else {
                            gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                        }
                    } else {
                        if (Domains.get(i + 1).getText().length() > 10) {
                            String domain = Domains.get(i + 1).getText();
                            domain = domain.replaceFirst("\\.", ".\n");
                            gc.fillText((i + 2) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                        } else {
                            gc.fillText((i + 2) + ": " + Domains.get(i + 1).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                        }
                    }

                }
                serverX[0] = oldX;
                serverY[0] = oldY;

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
                        gc.fillText((count + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                    } else {
                        gc.fillText((count + 1) + ": " + Domains.get(count).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                    }

                } else {
                    if (Domains.get(count - branch).getText().length() > 10) {
                        String domain = Domains.get(count - branch).getText();
                        domain = domain.replaceFirst("\\.", ".\n");
                        gc.fillText((count - branch + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                    } else {
                        gc.fillText((count - branch + 1) + ": " + Domains.get(count - branch).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                    }
                }
            } else if (rec.size() == 2) {
                for (int i = 0; i < count; i++) {
                    if (i >= count - rec.get(0) - rec.get(1)) {
                        continue;
                    } else {
                        if (i == 0) {
                            gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                            gc.setFill(Color.BLACK);
                            gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0] /2);
                        } else {
                            gc.beginPath();
                            gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
                            gc.lineTo(serverX[0] - serverWidth[0], serverY[0] + 2 * serverHeight[0]);
                            gc.stroke();
                            gc.closePath();
                            serverX[0] -= 1.5 * serverWidth[0];
                            serverY[0] += Math.round(2 * serverHeight[0]);
                            gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                            gc.setFill(Color.BLACK);
                            if (count == oldCount) {
                                if (Domains.get(i).getText().length() > 8) {
                                    String domain = Domains.get(i).getText();
                                    domain = domain.replaceFirst("\\.", ".\n");
                                    gc.fillText((i + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                                } else {
                                    gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                                }
                            } else {
                                if (Domains.get(i).getText().length() > 8) {
                                    String domain = Domains.get(i).getText();
                                    domain = domain.replaceFirst("\\.", ".\n");
                                    gc.fillText((i + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                                } else {
                                    gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                                }
                            }
                        }
                    }
                }
                double oldX = serverX[0];
                double oldY = serverY[0];
                serverX[0] = (Math.round((canvas.getWidth() / 2 - serverWidth[0])));
                serverY[0] = (Math.round((canvas.getHeight() / levelsOfTree - serverHeight[0])) / 4);
                for (int i = count - rec.get(0) - rec.get(1); i < count - rec.get(1); i++) {
                    gc.beginPath();
                    gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
                    gc.lineTo(serverX[0] + serverWidth[0] / 2, serverY[0] + 2 * serverHeight[0]);
                    gc.stroke();
                    gc.closePath();
                    serverY[0] += Math.round(2 * serverHeight[0]);
                    gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                    gc.setFill(Color.BLACK);

                    if (count == oldCount) {
                        if (Domains.get(i).getText().length() > 8) {
                            String domain = Domains.get(i).getText();
                            domain = domain.replaceFirst("\\.", ".\n");
                            gc.fillText((i + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0] );
                        } else {
                            gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                        }
                    } else {
                        if (Domains.get(i + 1).getText().length() > 8) {
                            String domain = Domains.get(i + 1).getText();
                            domain = domain.replaceFirst("\\.", ".\n");
                            gc.fillText((i + 2) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0] );
                        } else {
                            gc.fillText((i + 2) + ": " + Domains.get(i + 1).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0] );
                        }
                    }
                }
                serverX[0] = (Math.round((canvas.getWidth() / 2 - serverWidth[0])));
                serverY[0] = (Math.round((canvas.getHeight() / levelsOfTree - serverHeight[0])) / 4);
                int j = count + rec.get(0) - rec.get(1);
                for (int i = count - rec.get(1); i < count; i++) {
                    gc.beginPath();
                    gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
                    gc.lineTo(serverX[0] + serverWidth[0] * 2, serverY[0] + 2 * serverHeight[0]);
                    gc.stroke();
                    gc.closePath();
                    serverX[0] += 1.5 * serverWidth[0];
                    serverY[0] += Math.round(2 * serverHeight[0]);
                    gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                    gc.setFill(Color.BLACK);
                    if (count == oldCount) {


                        if (Domains.get(i).getText().length() > 8) {
                            String domain = Domains.get(i).getText();
                            domain = domain.replaceFirst("\\.", ".\n");
                            gc.fillText((i + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                        } else {
                            gc.fillText((i + 1) + ": " + Domains.get(i).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0] );
                        }
                    } else {
                        if (Domains.get(i + 1).getText().length() > 8) {
                            String domain = Domains.get(i + 1).getText();
                            domain = domain.replaceFirst("\\.", ".\n");
                            gc.fillText((i + 2) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0] );
                        } else {
                            gc.fillText((i + 2) + ": " + Domains.get(i + 1).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0]);
                        }
                    }


                }
                int ServNumber;
                if (count != oldCount) {
                    ServNumber = count - rec.get(0) - rec.get(1);
                } else {
                    ServNumber = count;
                }
                serverX[0] = oldX;
                serverY[0] = oldY;
                gc.beginPath();
                gc.moveTo(serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0]);
                gc.lineTo(serverX[0] - serverWidth[0] / 2, serverY[0] + 2 * serverHeight[0]);
                gc.stroke();
                gc.closePath();
                serverX[0] -= serverWidth[0];
                serverY[0] += Math.round(2 * serverHeight[0]);
                gc.strokeRect(serverX[0], serverY[0], serverWidth[0], serverHeight[0]);
                gc.setFill(Color.BLACK);

                if (Domains.get(ServNumber).getText().length() > 8) {
                    String domain = Domains.get(ServNumber).getText();
                    domain = domain.replaceFirst("\\.", ".\n");
                    gc.fillText((ServNumber + 1) + ": " + domain, serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 3, serverWidth[0]);
                } else {
                    gc.fillText((ServNumber + 1) + ": " + Domains.get(ServNumber).getText(), serverX[0] + serverWidth[0] / 2, serverY[0] + serverHeight[0] / 2, serverWidth[0] );
                }


            }


        });
    }

    public void StepBack(ActionEvent actionEvent) {
        if (rec.size() == 0) {
            if (count >= 1) {
                GraphicsContext context = canvas.getGraphicsContext2D();
                drawing(context);
                count--;
                for (int i = 0; i < 9; i++)
                    ServerList.get(i).setOpacity(0);
                for (int i = 0; i < count + 1; i++) {
                    ServerList.get(i).setOpacity(1);

                }
            }

        } else if (rec.size() == 1) {
            if (count >= 1) {

                if (count == oldCount) {
                    count--;
                    GraphicsContext context = canvas.getGraphicsContext2D();
                    drawing(context);
                    for (int i = 0; i < 9; i++)
                        ServerList.get(i).setOpacity(0);
                    for (int i = 0; i < count + 1; i++) {
                        ServerList.get(i).setOpacity(1);
                    }
                } else if (branch != 0) {
                    branch--;
                    count--;
                    GraphicsContext context = canvas.getGraphicsContext2D();
                    drawing(context);
                    for (int i = 0; i < 9; i++)
                        ServerList.get(i).setOpacity(0);
                    for (int i = 0; i < count + 1; i++) {
                        ServerList.get(i).setOpacity(1);
                    }

                } else {
                    if (count >= 1) {
                        for (int i = 0; i < rec.size(); i++) {
                            oldBranches[i] = rec.get(i);
                        }
                        oldRec.add(rec.get(0));
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
        } else if (rec.size() == 2) {
            if (count >= 1) {
                if (count == oldCount) {
                    count--;
                    GraphicsContext context = canvas.getGraphicsContext2D();
                    drawing(context);
                    for (int i = 0; i < 9; i++)
                        ServerList.get(i).setOpacity(0);
                    for (int i = 0; i < count + 1; i++) {
                        ServerList.get(i).setOpacity(1);
                    }
                } else if (rec.get(1) > 0) {
                    int buffercount = count - rec.get(0) - rec.get(1);
                    rec.set(1, rec.get(1) - 1);
                    count = buffercount + rec.get(0) + rec.get(1);
                    GraphicsContext context = canvas.getGraphicsContext2D();
                    drawing(context);

                    for (int i = 0; i < 9; i++)
                        ServerList.get(i).setOpacity(0);
                    for (int i = 0; i < count + 1; i++) {
                        ServerList.get(i).setOpacity(1);
                    }
                } else {
                    oldRec.add(rec.get(1));
                    rec.remove(1);
                }
            }
        }
    }

    public void StepForward(ActionEvent actionEvent) {
        if (DNSearch.stop == 0) {
            if (oldRec.size() > 2) {
                for (int i = 2; i < oldRec.size(); i++)
                    oldRec.remove(i);
            }
            if (oldRec.size() != 2) {
                if (oldRec.size() == 1) {
                    rec.add(oldRec.get(0));
                    oldRec.clear();
                }

                if (rec.size() == 0) {
                    if (count < oldCount) {
                        count += 2;
                        GraphicsContext context = canvas.getGraphicsContext2D();
                        if (count != oldCount + 1)
                            drawing(context);
                        count--;
                        for (int i = 0; i < 9; i++)
                            ServerList.get(i).setOpacity(0);
                        for (int i = 0; i < count + 1; i++) {
                            ServerList.get(i).setOpacity(1);
                        }
                    }
                } else if (rec.size() == 1) {

                    if (count < oldCount - oldBranch - 1) {
                        count++;
                    } else if (branch < oldBranch) {
                        branch++;
                        count++;
                    } else if (count < oldCount) {
                        count++;
                    }

                    for (int i = 0; i < 9; i++)
                        ServerList.get(i).setOpacity(0);
                    for (int i = 0; i < count + 1; i++) {
                        ServerList.get(i).setOpacity(1);
                        GraphicsContext context = canvas.getGraphicsContext2D();
                        drawing(context);


                    }
                }
            } else {
                if (flag2 == 0) {
                    rec.add(0);
                    rec.add(0);
                    oldRec.set(1, 2);
                    flag2++;
                }
                if (count < oldCount - oldRec.get(0) - oldRec.get(1) - 1) {
                    count++;
                } else if (rec.get(0) < oldRec.get(0)) {
                    rec.set(0, rec.get(0) + 1);
                    count++;

                } else if (rec.get(1) < oldRec.get(1)) {
                    rec.set(1, rec.get(1) + 1);
                    count++;

                } else if (count < oldCount) {
                    count++;
                }

                for (int i = 0; i < 9; i++)
                    ServerList.get(i).setOpacity(0);
                for (int i = 0; i < count + 1; i++) {
                    ServerList.get(i).setOpacity(1);

                    GraphicsContext context = canvas.getGraphicsContext2D();
                    drawing(context);

                }
            }
        }
    }

    public void Play(ActionEvent actionEvent) throws IOException {
        ActionEvent action = new ActionEvent();
        StartSearch(action);
    }

    public void Pause(ActionEvent actionEvent) {
        DNSearch.stop = 0;
    }

    public void Stop(ActionEvent actionEvent) {
        DNSearch.stop = 1;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void Help(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLDocumentController(stage);

    }

    protected void FXMLDocumentController(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Help.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMinWidth(640);
        stage.setMinHeight(320);
        stage.setMaxWidth(640);
        stage.setMinHeight(320);
        stage.show();
    }
    @FXML
    private Button btnhelp;

    public void cancel(ActionEvent actionEvent){
        Stage stage = (Stage) btnhelp.getScene().getWindow();
        stage.close();
    }


}


