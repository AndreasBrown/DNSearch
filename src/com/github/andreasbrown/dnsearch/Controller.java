package com.github.andreasbrown.dnsearch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.github.andreasbrown.dnsearch.DNSearch.Servers;

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



    public static long time = 0;


    public static List<TitledPane> ServerList = new ArrayList<>();
    public static List<Label> Domains = new ArrayList<>();
    public static List<Label> NameServers = new ArrayList<>();
    public static List<Label> types = new ArrayList<>();


    public void StartSearch(ActionEvent actionEvent) throws IOException {
      try {
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

          for (int i = 0; i < 9; i++){
              ServerList.get(i).setFont(Font.font(servers.getHeight()/40));
              Domains.get(i).setFont(Font.font(servers.getHeight()/50));
              types.get(i).setFont(Font.font(servers.getHeight()/60));
              NameServers.get(i).setFont(Font.font(servers.getHeight()/60));
              NameServers.get(i).setPrefHeight(servers.getHeight()/2);
              NameServers.get(i).setLayoutY(servers.getHeight()/18);


              ServerList.get(i).setFont(Font.font(servers.getHeight()/40));
              Domains.get(i).setFont(Font.font(servers.getHeight()/45));
              Domains.get(i).setLayoutY(servers.getHeight()/38);
              NameServers.get(i).setFont(Font.font(servers.getHeight()/55));
              NameServers.get(i).setPrefHeight(servers.getHeight()/2);
              NameServers.get(i).setLayoutY(servers.getHeight()/12);
              types.get(i).setFont(Font.font(servers.getHeight()/55));
              types.get(i).setLayoutY(servers.getHeight()/25);

              ServerList.get(i).setOpacity(0);
          }




          Answer.setText("Current action");

          boolean eng = true;
          for (int i = 0; i < field.getCharacters().length(); i++) {
              if ((field.getCharacters().charAt(i) == '.') || (field.getCharacters().charAt(i) >= '0' && field.getCharacters().charAt(i) <= '9') ||(field.getCharacters().charAt(i) >= 'A' && field.getCharacters().charAt(i) <= 'Z') ||(field.getCharacters().charAt(i) >= 'a' && field.getCharacters().charAt(i) <= 'z') ) {
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
             String result =  DNSearch.main(url);
             if (result == "Non-existent domain or host of domain is not exist"){
                 Answer.setText("Non-existent domain or host of domain is not exist");
                return;
             }
              System.out.println(DNSearch.count);

                  for (int i = 0; i < DNSearch.count+1; i++){
                        ServerList.get(i).setOpacity(1);
                        if (i != DNSearch.count)
                            ServerList.get(i).setText("Server "+(i+1));




                      int j = i;
                      servers.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
                          ServerList.get(j).setFont(Font.font(newValue.getHeight()/40));
                          Domains.get(j).setFont(Font.font(servers.getHeight()/45));
                          Domains.get(j).setLayoutY(servers.getHeight()/38);
                          NameServers.get(j).setFont(Font.font(servers.getHeight()/45));
                          NameServers.get(j).setPrefHeight(servers.getHeight()/2);
                          NameServers.get(j).setLayoutY(servers.getHeight()/12);
                          types.get(j).setFont(Font.font(servers.getHeight()/55));
                          types.get(j).setLayoutY(servers.getHeight()/25);



                      });
                      }
                    ServerList.get(DNSearch.count).setText("Answer");


                for (int i = 0 ; i < DNSearch.count; i++){
                    Domains.get(i).setText(DNSearch.domains[i+1]);
                    NameServers.get(i).setText("             Nameservers: \n"+Servers.get(i));
                    types.get(i).setText("Query type: " +DNSearch.QueryTypes[i+1]);
                }
                DNSearch.QueryTypes = new String[10];
              Servers.clear();

              Domains.get(DNSearch.count).setText(url);
              String answer ="";
              for (int i = 0; i < DNSearch.finalAnswerTypeA.length; i++){
                  answer+= DNSearch.finalAnswerTypeA[i]+ "\n";
              }
              NameServers.get(DNSearch.count).setText("Answer: \n"+answer);
              types.get(DNSearch.count).setText("Query type : A");

          }
      }
    catch(NullPointerException e){
        Answer.setText("Non-existent domain or host of domain is not exist");

    }
    catch (IndexOutOfBoundsException e){
        Answer.setText("Non-existent domain or host of domain is not exist");
        }
    }
}