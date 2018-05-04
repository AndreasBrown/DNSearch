package com.github.andreasbrown.dnsearch;

import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import org.xbill.DNS.*;

import java.net.*;
import java.io.*;
import java.util.*;

public class DNSearch {

    public static String name = "";
    private static String OldName = name;
    private static String[] Buffer;
    private static String adr = "";
    private static String nameservers = "";
    public static String[] finalAnswerTypeA;
    public static String[] domains = new String[10];
    public static int count = 0;
    public static List<String> Servers = new ArrayList<>();
    public static String[] QueryTypes = new String[10];


    public static String Search(String host) throws TextParseException, UnknownHostException {
        try {
            Resolver res;
            String[] DomainLevel = host.split("\\.");
            Record[] records;
            res = new SimpleResolver("8.8.8.8");
            Thread.sleep(0);
            long currentTime = System.currentTimeMillis();
            if ((Controller.time - currentTime) > 10000) {
                System.out.println("fuck");
                return "loooop";
            }
            for (int level = DomainLevel.length - 1; level >= 0; level--) {
                count++;
                nameservers="";
                System.out.println("_____________STEP:_"+count+"_______________________________");
                if (level == DomainLevel.length - 1) {
                    name = DomainLevel[level] + ".";
                } else {
                    name = DomainLevel[level] + "." + name;
                }
                System.out.println(name);
                domains[count] = name;
                InetAddress addr;
                Lookup NS = new Lookup(name, Type.NS);
                NS.setResolver(res);
                NS.run();
                if (NS.getResult() == Lookup.SUCCESSFUL){
                    QueryTypes[count] = "NS + A";
                    records = NS.run();
                    Buffer = new String[records.length];
                    for (int i = 0; i < records.length; i++) {
                        NSRecord ns = (NSRecord) records[i];
                        addr = Address.getByName(String.valueOf(ns.getTarget()));
                        System.out.println("STEP " + count + ": " + addr);
                        adr = String.valueOf(addr);
                        nameservers += adr+"\n";

                        String [] IpRes = adr.split("/");
                        Buffer[i] = IpRes[0];
                }
                    Servers.add(nameservers);
                    System.out.println("add");

            }
                else {
                    Lookup canonicalName = new Lookup(name, Type.CNAME);
                    canonicalName.run();
                    if (canonicalName.getResult() == Lookup.SUCCESSFUL){
                        QueryTypes[count] = "CNAME + A";
                        records = new Lookup(name, Type.CNAME).run();
                        for (int i = records.length-1; i >=0 ; i--) {
                            CNAMERecord CN = (CNAMERecord) records[i];
                            addr = Address.getByName(String.valueOf(CN.getTarget()));
                            System.out.println(addr);
                        }
                    }
                    else {
                        int NotRecursion = 0;
                        for (int i = 0; i < Buffer.length; i++){
                        if (Buffer[i].equals(name)){

                            NotRecursion++;
                            Lookup Fin = new Lookup(name, Type.A);
                            records = Fin.run();
                            ARecord ARec = (ARecord) records[0];
                            InetAddress answer = ARec.getAddress();
                            System.out.println(answer);
                            adr = String.valueOf(answer);
                            nameservers += adr+"\n";
                            }

                        }
                        Servers.add(nameservers);
                        QueryTypes[count] = "A";

                        System.out.println("add");
                        if (NotRecursion == 0){
                            if (Buffer[0].contains("dns.ripn.net")||Buffer[0].contains("nstld.com"))
                                return host;
                            System.out.println("_____________RECURSION_COMMING____________");
                            Servers.remove("");
                            count--;
                            Search(Buffer[0]);
                        }
                    }
                }
            }

            Lookup ReturnAndFin = new Lookup(OldName, Type.A);
            records = ReturnAndFin.run();
            finalAnswerTypeA = new String[records.length];
            for (int i = records.length-1; i >= 0 ; i--) {
                ARecord ARec = (ARecord) records[i];
                InetAddress answer = ARec.getAddress();
                finalAnswerTypeA[i] = answer.toString();

            }
        } catch (UncheckedIOException io) {
                return "UncheckedIOException";
        } catch (TextParseException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            return "NullPointerException";
        } catch (StackOverflowError e) {
                return "Non-existent domain or host of domain is not exist";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return host;
    }
    public static String main(String host) throws IOException{
        try { count = 0;
            OldName = host;
            InetAddress inetAddress = InetAddress.getByName("google.com");
            for (int i = 0; i < 3; i++) {
                if (inetAddress.isReachable(1000)) {
                    Search(OldName);
                    System.out.println("_________________ANSWER_________________");
                    for (i = 0; i < finalAnswerTypeA.length; i++)
                        System.out.println(finalAnswerTypeA[i]);
                    System.out.println("__________________TEST_________________");
                    inetAddress = InetAddress.getByName(OldName);
                    System.out.println(inetAddress);
                    break;
                }
                else {
                    return "No internet connection";
                }
            }
        }
        catch (UnknownHostException e){
            return "No internet connection";
        }
        catch (NullPointerException e){
            return "Non-existent domain or host of domain is not exist";
        }
        return finalAnswerTypeA[0];
    }
}