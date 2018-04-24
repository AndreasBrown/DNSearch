import org.xbill.DNS.*;
import java.net.*;
import java.io.*;
import java.nio.Buffer;
import java.util.*;
import java.util.stream.Collectors;

public class DNSearch {

    private static String name = "";
    private static String BufferNS = "";
    private static Resolver res;
    private static String OldName = name;
    private static String[] Buffer;
    private static String[] fin;

    public static String Search(String host) throws TextParseException, UnknownHostException {
        try {
            String[] DomainLevel = host.split("\\.");
            int count = 0;
            Record[] records;
            res = new SimpleResolver("8.8.8.8");
          //  Thread.sleep(5000);
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
                        for (int i = records.length-1; i >=0 ; i--) {
                            CNAMERecord CN = (CNAMERecord) records[i];
                            InetAddress addr = Address.getByName(String.valueOf(CN.getTarget()));
                            BufferNS = String.valueOf(CN.getTarget());
                            System.out.println(addr);
                        }

                    }
                    else {
                        int flex = 0;
                        for (int i = 0; i < Buffer.length; i++){
                       //     System.out.println("Not CNAME Buffer: " + Buffer[i]);
                        //    System.out.println("Not CNAME name: " + name);
                         //   System.out.println(Buffer.length);
                        if (Buffer[i].equals(name)){
                            flex++;
                            Lookup Fin = new Lookup(name, Type.A);
                            records = Fin.run();
                            ARecord ARec = (ARecord) records[0];
                            InetAddress answer = ARec.getAddress();
                            System.out.println(answer);
                            }
                        }
                        if (flex == 0){
                            System.out.println("_____________RECURSION_COMMING____________");
                            Search(Buffer[0]);
                        }
                    }
                }
            }
            Lookup ReturnAndFin = new Lookup(OldName, Type.A);
            records = ReturnAndFin.run();
            fin = new String[records.length];
            for (int i = records.length-1; i >= 0 ; i--) {
                ARecord ARec = (ARecord) records[i];
                InetAddress answer = ARec.getAddress();
                fin[i] = answer.toString();
            }

        } catch (UncheckedIOException io) {

        } catch (TextParseException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Blyat");
        }
        return host;
    }
    public static void main(String[] args) throws IOException{
        OldName = "www.google.com";
        Search(OldName);
        System.out.println("_________________ANSWER_________________");
        for (int i = 0; i < fin.length; i++)
        System.out.println(fin[i]);
        System.out.println("__________________TEST_________________");
        InetAddress inetAddress = InetAddress.getByName(OldName);
        System.out.println(inetAddress);
        //Добавить список адресов типа а в конце, если их несколько
        // www.google.com. иногда странные адреса
        // www.microsoft.com. тоже 50 на 50
    }
}

/*
lookup.setResolver(Resolver resolver)
* www.pisem.net - разобрать
* server *root*
* type=ns
* net.
*type=a
*server *net.*
* type= ns
* pisem.net.
* (рекурсия)
* ns3.itmm.ru.
* ns делать до самого конца, а только в самом конце.
* */