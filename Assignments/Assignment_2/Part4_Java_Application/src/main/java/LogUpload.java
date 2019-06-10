import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Address{
   String ip;
   String timeStamp;
   String timeStampWithoutZone;
   String statusCode;
   String singleStatusCode;
   String command;
   String webAddress;

   Address(String ip, String timeStamp,String timeStampWithoutZone, String statusCode, String singleStatusCode, String command, String webAddress){
       this.ip = ip;
       this.timeStamp = timeStamp;
       this.timeStampWithoutZone = timeStampWithoutZone;
       this.statusCode = statusCode;
       this.singleStatusCode = singleStatusCode;
       this.command = command;
       this.webAddress = webAddress;
   }

    @Override
    public String toString() {
        return "Address{" +
                "ip='" + ip + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", singleStatusCode='" + singleStatusCode + '\'' +
                ", command='" + command + '\'' +
                ", webAddress='" + webAddress + '\'' +
                '}';
    }
}

public class LogUpload {
    final static String FILE_NAME = "C:\\Users\\ankit\\Documents\\4th_Sem\\EnggBigData\\Assignments\\Assignment_2\\access.log";
    final static Charset ENCODING = StandardCharsets.UTF_8;
    private static ArrayList<String> lines = new ArrayList<>();
    private static List<Address> allLogs = new ArrayList<>();

    public static void main(String[] args){
        try {
            lines = readLargerTextFile(FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String s:lines)
        allLogs.add(UploadLog(s));

        System.out.println("Total logs: "+allLogs.size());

        MongoDBUpload();


    }

    public static void MongoDBUpload(){
        // Creating a Mongo client
        MongoClient mongo = new MongoClient( "localhost" , 27017 );

        System.out.println("Connected to the database successfully");
//      In Java, we display databases using snippet below:
        //mongo.getDatabaseNames().forEach(System.out::println);

        // Now, let’s display all existing collections for current database:
        DB database = mongo.getDB("accessLogDB");
        //database.getCollectionNames().forEach(System.out::println);

     /*   Let’s start by creating a Collection (table equivalent for MongoDB) for our database.
        Once we have connected to our database, we can make a Collection as:*/

        database.createCollection("allLogs", null);

        // save into collection

        DBCollection collection = database.getCollection("allLogs");

        for(Address add: allLogs) {
            BasicDBObject document = new BasicDBObject();
            document.put("ip", add.ip);
            document.put("timestamp", add.timeStamp);
            document.put("timeStampWithoutZone", add.timeStampWithoutZone);
            //document.put("statuscode", add.statusCode);
            document.put("statuscode", add.singleStatusCode);
            document.put("command", add.command);
            document.put("webaddress", add.webAddress);
            collection.insert(document);
        }

        System.out.println("All the documents successfully saved in MongoDB");
    }

    public static ArrayList<String> readLargerTextFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner scanner =  new Scanner(path, ENCODING.name())){
            while (scanner.hasNextLine()){
                //process each line in some way
                lines.add(scanner.nextLine());
            }
        }
        return lines;
    }

    public static Address UploadLog(String log){

        String[] logs = log.split(" - - ");
        String ipAddress = logs[0];

        logs = logs[1].split("]", 2);
        String timeStamp = logs[0].substring(1);
        timeStamp = timeStamp.replaceFirst(":"," ");

        System.out.println(timeStamp);
        String timeStampWithoutZone = timeStamp.split(" ")[0];

        logs = logs[1].split("\"");
        String statusCode = logs[2].substring(1);
        String singleStatusCode = statusCode.substring(0,statusCode.indexOf(' '));

        logs = logs[1].split(" ",2);
        String command = logs[0];
        String address="";
        try{
            address = logs[1];

        } catch(ArrayIndexOutOfBoundsException e){
            address = "No Web Address present";
        }
        return new Address(ipAddress, timeStamp, timeStampWithoutZone, statusCode, singleStatusCode, command, address);
    }
}
