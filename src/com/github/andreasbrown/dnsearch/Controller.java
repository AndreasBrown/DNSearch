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



    public void StartSearch(ActionEvent actionEvent) throws IOException {
        try {

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
            progressBar.setProgress(0);

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
                    break;
                }
            }
            if (eng) {
                String url = String.valueOf(field.getCharacters());
                time = System.currentTimeMillis();
                String result = DNSearch.main(url);
                progressBar.progressProperty().unbind();
                progressBar.progressProperty().bind(DNSearch.main(url).progressProperty());

                if (result == "Non-existent domain or host of domain is not exist") {
                    Answer.setText("Non-existent domain or host of domain is not exist");
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

            if (count+1-branch>= branch)
                levelsOfTree =  count+1-branch+1;
            else levelsOfTree = branch+1;
            System.out.println("LevelsOfTree: "+levelsOfTree);
            if (branch==count)
                branch=0;
            oldCount = count;
            oldBranch = branch;


            System.out.println("Branch: "+branch);
            System.out.println("count: "+count);
            System.out.println("Rec: "+rec.size());
            System.out.println("oldCount: "+oldCount);

            GraphicsContext context = canvas.getGraphicsContext2D();
            drawing(context);
            flag= 3;

            Answer.setText("Succsess");

        } catch (NullPointerException e) {
            Answer.setText("Non-existent domain or host of domain is not exist");
        } catch (IndexOutOfBoundsException e) {
            Answer.setText("Non-existent domain or host of domain is not exist");
        }
    }
        private void drawing(GraphicsContext gc) {
        final double[] VBOXh = {VBOXCan.getHeight()};
        final double[] VBOXw = {VBOXCan.getWidth()};
        final double[] serverHeight = {(canvas.getHeight() / 7 )};
        final double[] serverWidth = {(canvas.getWidth() / 19 )};
        final double[] serverX = {(Math.round((canvas.getWidth()/2-serverWidth[0])))};
        final double[] serverY = {(Math.round((canvas.getHeight()/levelsOfTree-serverHeight[0]))/4)};

        canvas.setHeight(VBOXh[0]/1.1);
        canvas.setWidth(VBOXw[0]/1.2);

            gc.setFill(Color.rgb(244,244,244));
            gc.fillRect(0, 0, VBOXw[0], VBOXh[0]);
        gc.setFill(Color.BLACK);
       if (ServerList.get(0).getOpacity() != 0)
        gc.fillRect(serverX[0], serverY[0],serverWidth[0],serverHeight[0]);
            if (branch == 0){
                for (int i = 0; i < count-branch; i++) {
                    gc.beginPath();
                    gc.moveTo(serverX[0]+serverWidth[0]/2, serverY[0]+serverHeight[0]);
                    gc.lineTo(serverX[0]+serverWidth[0]/2, serverY[0] +2* serverHeight[0]);
                    gc.stroke();
                    gc.closePath();
                    serverY[0]+= Math.round(2*serverHeight[0]);
                    gc.fillRect(serverX[0], serverY[0],serverWidth[0],serverHeight[0]);

                }
                }
            else if(rec.size() == 1){
                for (int i = 0; i <= count-branch; i++){
                    gc.beginPath();
                    gc.moveTo(serverX[0], serverY[0]+serverHeight[0]);
                    gc.lineTo(serverX[0]-serverWidth[0], serverY[0] + 1.5*serverHeight[0]);
                    gc.stroke();
                    gc.closePath();
                    serverY[0]+= 1.5*serverHeight[0];
                    serverX[0]-= 2*serverWidth[0];
                    gc.fillRect(serverX[0], serverY[0],serverWidth[0],serverHeight[0]);
                }
                serverX[0] = Math.round((canvas.getWidth()/2-serverWidth[0]));
                serverY[0] = Math.round((canvas.getHeight()/levelsOfTree-serverHeight[0]))/4;

                for (int i = 0; i < branch; i++){
                    gc.beginPath();
                    gc.moveTo(serverX[0]+serverWidth[0], serverY[0]+serverHeight[0]);
                    gc.lineTo(serverX[0]+2*serverWidth[0], serverY[0] + 1.5*serverHeight[0]);
                    gc.stroke();
                    gc.closePath();
                    serverY[0]+= 1.5*serverHeight[0];
                    serverX[0]+= 2*serverWidth[0];
                    gc.fillRect(serverX[0], serverY[0],serverWidth[0],serverHeight[0]);

                }

                //System.out.println("serverX: "+serverX[0]+" serverY: "+serverY[0]);
            }
                 VBOXCan.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
                     VBOXh[0] = newValue.getHeight();
                     VBOXw[0] = newValue.getWidth();
                     canvas.setHeight(VBOXh[0]/1.2);
                     canvas.setWidth(VBOXw[0]/1.2);
                     serverX[0] = Math.round((canvas.getWidth()/2-serverWidth[0]));
                     serverY[0] = Math.round((canvas.getHeight()/levelsOfTree-serverHeight[0]))/4;
                     serverHeight[0] = Math.round(canvas.getHeight() / (2.5*levelsOfTree) );
                     serverWidth[0] = Math.round(canvas.getWidth() / 19 );
                     gc.setFill(Color.rgb(244,244,244));
                     gc.fillRect(0, 0, VBOXw[0], VBOXh[0]);
                     gc.setFill(Color.BLACK);
                     if (ServerList.get(0).getOpacity() != 0)
                     gc.fillRect(serverX[0], serverY[0],serverWidth[0],serverHeight[0]);
                     if (branch == 0){
                         for (int i = 0; i < count; i++) {
                             gc.beginPath();
                             gc.moveTo(serverX[0]+serverWidth[0]/2, serverY[0]+serverHeight[0]);
                             gc.lineTo(serverX[0]+serverWidth[0]/2, serverY[0] +2* serverHeight[0]);
                             gc.stroke();
                             gc.closePath();
                             serverY[0]+= Math.round(2*serverHeight[0]);
                             gc.fillRect(serverX[0], serverY[0],serverWidth[0],serverHeight[0]);
                         }
                     }
                     else if(rec.size() == 1){
                         for (int i = 0; i <= count-branch; i++){
                             gc.beginPath();
                             gc.moveTo(serverX[0], serverY[0]+serverHeight[0]);
                             gc.lineTo(serverX[0]-serverWidth[0], serverY[0] + 1.5*serverHeight[0]);
                             gc.stroke();
                             gc.closePath();
                             serverY[0]+= 1.5*serverHeight[0];
                             serverX[0]-= 2*serverWidth[0];
                             gc.fillRect(serverX[0], serverY[0],serverWidth[0],serverHeight[0]);
                         }
                         serverX[0] = Math.round((canvas.getWidth()/2-serverWidth[0]));
                         serverY[0] = Math.round((canvas.getHeight()/levelsOfTree-serverHeight[0]))/4;

                         for (int i = 0; i < branch; i++){
                             gc.beginPath();
                             gc.moveTo(serverX[0]+serverWidth[0], serverY[0]+serverHeight[0]);
                             gc.lineTo(serverX[0]+2*serverWidth[0], serverY[0] + 1.5*serverHeight[0]);
                             gc.stroke();
                             gc.closePath();
                             serverY[0]+= 1.5*serverHeight[0];
                             serverX[0]+= 2*serverWidth[0];
                             gc.fillRect(serverX[0], serverY[0],serverWidth[0],serverHeight[0]);

                         }

                         //System.out.println("serverX: "+serverX[0]+" serverY: "+serverY[0]);
                     }
                     else if (rec.size() >=2){
                         float[] koef = {-1, (float) 0.5, 2};
                         serverX[0] = Math.round((canvas.getWidth()/2-serverWidth[0]));
                         serverY[0] = Math.round((canvas.getHeight()/levelsOfTree-serverHeight[0]))/4;
                         rec.add(count-branch+1);
                         int f=0;
                         for (int i = rec.size()-1; i >= 0; i--){
                             for(int j = 0; j < rec.get(i); j++ ){
                                 gc.beginPath();
                                 gc.moveTo(serverX[0]+serverWidth[0]/2, serverY[0]+serverHeight[0]);
                                 gc.lineTo(serverX[0]+serverWidth[0]*koef[f], serverY[0] + 1.5*serverHeight[0]);
                                 gc.stroke();
                                 gc.closePath();
                                 serverY[0]+= 1.5*serverHeight[0];
                                 serverX[0]-= koef[f]*serverWidth[0];
                                 gc.fillRect(serverX[0], serverY[0],serverWidth[0],serverHeight[0]);


                             }
                             serverX[0] = Math.round((canvas.getWidth()/2-serverWidth[0]));
                             serverY[0] = Math.round((canvas.getHeight()/levelsOfTree-serverHeight[0]))/4;
                             f++;
                         }

                     }
                 });
    }

    public void StepBack(ActionEvent actionEvent){

        if (DNSearch.stop != 1) {
            if(rec.size() == 0 && count== oldCount)
                flag=0;
            if ((rec.size() == 0) || (count == oldCount) || (branch == 0)) {
                if (count >= 0 )
                    if (flag != 1)
                    count--;
                for (int i = 0; i < 9; i++)
                    ServerList.get(i).setOpacity(0);
                    if (flag ==0){
                        count--;}
                for (int i = 0; i < count+1; i++) {
                    ServerList.get(i).setOpacity(1);
                  }
                if (flag ==0){
                    count++;}
                GraphicsContext context = canvas.getGraphicsContext2D();
                drawing(context);
            }
            else{
            if (branch>0){
                flag = 1;
                branch--;
                count--;}
                if (branch == 0){
                    count++;
                    flag = 0;


                }

            System.out.println("count: "+count);
            for (int i = 0; i < 9; i++)
                ServerList.get(i).setOpacity(0);
                if (flag == 0 )
                    count--;
                for (int i = 0; i < count+1; i++) {
                ServerList.get(i).setOpacity(1);
            }
            if (flag == 0)
                count++;
            GraphicsContext context = canvas.getGraphicsContext2D();
            drawing(context);
            }
        }
    }
    public void StepForward(ActionEvent actionEvent) {
        if (DNSearch.stop != 1) {
            if (rec.size() == 0 || ((count < oldCount-3)&& count !=0)   ) {
                if (count < oldCount)
                    count++;
                System.out.println("here un: "+count);

                for (int i = 0; i < 9; i++)
                    ServerList.get(i).setOpacity(0);
                for (int i = 0; i < count + 1; i++) {
                    ServerList.get(i).setOpacity(1);
                    GraphicsContext context = canvas.getGraphicsContext2D();
                    drawing(context);
                }
            } else if (count == 0) {
                System.out.println("ere un: "+count);

                for (int i = 0; i < 9; i++)
                    ServerList.get(i).setOpacity(0);
                for (int i = 0; i < count + 1; i++) {
                    ServerList.get(i).setOpacity(1);
                }
                count++;
                GraphicsContext context = canvas.getGraphicsContext2D();
                drawing(context);
            }
            else if (count == oldCount-1 && branch == oldBranch) {
                System.out.println("here un: "+count);

                count = oldCount;
                branch = oldBranch;
                for (int i = 0; i < 9; i++ )
                    ServerList.get(i).setOpacity(0);
                for (int i = 0; i < count+1; i++){
                    ServerList.get(i).setOpacity(1);
                    GraphicsContext context = canvas.getGraphicsContext2D();
                    drawing(context);
                    flag2=0;

                }
            }
            else if(oldBranch != 0 && branch < oldBranch && count < oldCount-3){
                System.out.println("here count: "+count);

                if (flag != 0){
                    flag = 0;
                    //branch++;
                    count++;

                }
                count++;
               // branch++;
                for (int i = 0; i < 9; i++)
                    ServerList.get(i).setOpacity(0);
                for (int i = 0; i < count + 1; i++) {
                    ServerList.get(i).setOpacity(1);
                }

                GraphicsContext context = canvas.getGraphicsContext2D();
                drawing(context);

            }
            else if(count < oldCount || branch < oldBranch){

                for (int i = 0; i < 9; i++)
                    ServerList.get(i).setOpacity(0);
                for (int i = 0; i < count + 1; i++) {
                    ServerList.get(i).setOpacity(1);
                }
                System.out.println("hee un: "+count);

                if (flag2 < 1){
                    System.out.println("here co: "+count);
                    count++;
                    flag2++;
                }
                else {
                if (count < oldCount - 2 ){
                count++;

                    System.out.println("here coun: "+count);
                }
                branch++;
                if (count == oldCount - 1){   System.out.println("here con: "+count);
                }
                else {count++;}
                if (flag2 == 1) {
                    count--;
                    flag2++;
                }
                }

                GraphicsContext context = canvas.getGraphicsContext2D();
                drawing(context);

            }

        }

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
*           canvas+-
* progressbar
*           buttons+
*           перемотка+-
*           css+-
*           иконка+
*/