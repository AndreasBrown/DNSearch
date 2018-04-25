import org.xbill.DNS.*;

import java.net.*;
import java.io.*;
import java.util.*;

public class DNSearch {

    private static String name = "";
    private static String BufferNS = "";
    private static Resolver res;
    private static String OldName = name;
    private static String[] Buffer;
    private static String[] finalAnswerTypeA;
    private static String[] Aliases;


    private static String Search(String host) throws TextParseException, UnknownHostException {
        try {
            String[] DomainLevel = host.split("\\.");
            int count = 0;
            Record[] records;
            res = new SimpleResolver("8.8.8.8");
            Thread.sleep(0);
            for (int level = DomainLevel.length - 1; level >= 0; level--) {
                count++;
                System.out.println("_____________STEP:_"+count+"_______________________________");
                if (level == DomainLevel.length - 1) {
                    name = DomainLevel[level] + ".";
                } else {
                    name = DomainLevel[level] + "." + name;
                }
                System.out.println(name);
                Lookup NS = new Lookup(name, Type.NS);
                NS.setResolver(res);
                NS.run();
                if (NS.getResult() == Lookup.SUCCESSFUL){
                    records = NS.run();
                    Buffer = new String[records.length];
                    for (int i = 0; i < records.length; i++) {
                        NSRecord ns = (NSRecord) records[i];
                        InetAddress addr = Address.getByName(String.valueOf(ns.getTarget()));
                        BufferNS = String.valueOf(ns.getTarget());
                        System.out.println("STEP " + count + ": " + addr);
                        String adr = String.valueOf(addr);
                        String [] IpRes = adr.split("/");
                        Buffer[i] = IpRes[0];
                }
            }
                else {
                    Lookup canonicalName = new Lookup(name, Type.CNAME);
                    canonicalName.run();
                    if (canonicalName.getResult() == Lookup.SUCCESSFUL){
                        records = new Lookup(name, Type.CNAME).run();
                        Aliases = new String[records.length];
                        for (int i = records.length-1; i >=0 ; i--) {
                            CNAMERecord CN = (CNAMERecord) records[i];
                            InetAddress addr = Address.getByName(String.valueOf(CN.getTarget()));
                            BufferNS = String.valueOf(CN.getTarget());
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
                            }
                        }
                        if (NotRecursion == 0){
                            if (Buffer[0].contains("dns.ripn.net")||Buffer[0].contains("nstld.com"))
                                return host;
                            System.out.println("_____________RECURSION_COMMING____________");
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
                System.out.println("UncheckedIOException");
        } catch (TextParseException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        } catch (StackOverflowError e) {
                System.out.println("Non-existent domain or host of domain is not exist");
                return host;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return host;
    }
    public static void main(String[] args) throws IOException{
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter URL: ");
            OldName = in.next();
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
                    System.out.println("No internet connection");
                }
            }
        }
        catch (NullPointerException e){
            System.out.println("Non-existent domain or host of domain is not exist");
        }
    }
}
