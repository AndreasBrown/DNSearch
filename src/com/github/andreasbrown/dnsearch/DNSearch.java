package com.github.andreasbrown.dnsearch;

import javafx.application.Platform;
import javafx.concurrent.Task;
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
    public static long[] ttl = new long[64];
    public static int branch = 0;
    public static List<Integer> rec = new ArrayList<>();
    public static int pause = 0;
    public static int stop = 0;


    public static String Search(String host) throws TextParseException, UnknownHostException {
        try {


            int NotRecursion = 0;
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

                if (stop == 1)
                    return "STOP";
                count++;
                branch++;
                nameservers = "";
                System.out.println("_____________STEP:_" + count + "_______________________________");
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
                if (NS.getResult() == Lookup.SUCCESSFUL) {
                    QueryTypes[count] = "NS + A";
                    records = NS.run();
                    Buffer = new String[records.length];
                    for (int i = 0; i < records.length; i++) {
                        NSRecord ns = (NSRecord) records[i];
                        addr = Address.getByName(String.valueOf(ns.getTarget()));
                        ttl[count] = ns.getTTL();
                        System.out.println("STEP " + count + ": " + addr);
                        adr = String.valueOf(addr);
                        nameservers += adr + "\n";
                        String[] IpRes = adr.split("/");
                        Buffer[i] = IpRes[0];
                    }
                    Servers.add(nameservers);
                    System.out.println("add");
                } else {
                    String[] oldDomain = host.split("\\.");
                    String[] newDomain = Buffer[0].split("/");
                    System.out.println(newDomain[0]);
                    System.out.println(oldDomain[oldDomain.length - 1]);
                    if (newDomain[0].contains(oldDomain[oldDomain.length - 1])) {
                        count--;
                        branch--;
                        NotRecursion++;
                        Lookup canonicalName = new Lookup(name, Type.CNAME);
                        canonicalName.run();
                        if (canonicalName.getResult() == Lookup.SUCCESSFUL) {
                            NotRecursion++;
                            QueryTypes[count] = "CNAME + A";
                            records = new Lookup(name, Type.CNAME).run();
                            String string ="";
                            for (int i = records.length - 1; i >= 0; i--) {
                                CNAMERecord CN = (CNAMERecord) records[i];
                                addr = Address.getByName(String.valueOf(CN.getTarget()));
                                ttl[count] = CN.getTTL();
                                System.out.println(addr);
                                string = String.valueOf(CN.getTarget());
                            }
                            Servers.add(string);
                        }
                    }
                    else {

                        for (int i = 0; i < Buffer.length; i++) {
                            if (Buffer[i].equals(name)) {
                                NotRecursion++;
                                Lookup Fin = new Lookup(name, Type.A);
                                records = Fin.run();
                                ARecord ARec = (ARecord) records[0];
                                InetAddress answer = ARec.getAddress();
                                ttl[count] = ARec.getTTL();
                                System.out.println(answer);
                                adr = String.valueOf(answer);
                                nameservers += adr + "\n";
                            }
                        }
                        Servers.add(nameservers);
                        QueryTypes[count] = "A";
                        System.out.println("add");
                    }

                    if (NotRecursion == 0) {
                        if (Buffer[0].contains("dns.ripn.net") || Buffer[0].contains("nstld.com"))
                            return host;
                        System.out.println("_____________RECURSION_COMMING____________");
                        rec.add(branch-1);
                        branch = 0;
                        Servers.remove("");
                        count--;
                        Search(Buffer[0]);
                    }
                }}

            Lookup ReturnAndFin = new Lookup(OldName, Type.A);
            records = ReturnAndFin.run();
            finalAnswerTypeA = new String[records.length];
            for (int i = records.length - 1; i >= 0; i--) {
                ARecord ARec = (ARecord) records[i];
                ttl[count] = ARec.getTTL();
                InetAddress answer = ARec.getAddress();
                finalAnswerTypeA[i] = answer.toString();
            }
            Servers.remove("");
            System.out.println(name);
            System.out.println(name);




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
        try {
            count = 1;
            branch = 0;
            OldName = host;
            List<String> roots = new ArrayList<>();
            roots.add("a.root-servers.net/198.41.0.4");
            roots.add("b.root-servers.net/192.228.79.201");
            roots.add("c.root-servers.net/192.33.4.12");
            roots.add("d.root-servers.net/199.7.91.13");
            roots.add("e.root-servers.net/192.203.230.10");
            roots.add("f.root-servers.net/192.5.5.241");
            roots.add("g.root-servers.net/192.112.36.4");
            roots.add("h.root-servers.net/198.97.190.53");
            roots.add("i.root-servers.net/192.36.148.17");
            roots.add("j.root-servers.net/192.58.128.30");
            roots.add("k.root-servers.net/193.0.14.129");
            roots.add("l.root-servers.net/199.7.83.42");
            roots.add("m.root-servers.net/202.12.27.33");
            Random randRoot = new Random();

            InetAddress inetAddress = InetAddress.getByName("google.com");
            for (int i = 0; i < 3; i++) {
                if (inetAddress.isReachable(1000)) {
                    domains[count]="Root";
                    Servers.add(roots.get(randRoot.nextInt(12)));
                    QueryTypes[count] = "root";
                    ttl[count] = 0;
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
