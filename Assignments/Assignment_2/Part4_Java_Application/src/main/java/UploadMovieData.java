import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class UploadMovieData {
    final static String FILE_PATH = "C:\\temp\\ml-1m\\";
    final static Charset ENCODING = StandardCharsets.UTF_8;
    private static  MongoClient mongo;
    private static MongoDatabase database;


    public static void main(String[] args){

        mongo = new MongoClient( "localhost" , 27017 );
        System.out.println("Connected to the database successfully");
        database = mongo.getDatabase("movieRatingDB");

        String movies = "movies.dat";
        String rating = "ratings.dat";
        String users = "users.dat";
        ArrayList<String> movieList = new ArrayList<>();
        ArrayList<String> ratingList = new ArrayList<>();
        ArrayList<String> userList = new ArrayList<>();


        try {
            movieList = readLargerTextFile(FILE_PATH+movies);
            ratingList = readLargerTextFile(FILE_PATH+rating);
            userList = readLargerTextFile(FILE_PATH+users);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String s:movieList)uploadMovies(s);
        System.out.println("Movies uploaded in database successfully");

        for(String s:ratingList)uploadRatings(s);
        System.out.println("Ratings uploaded in database successfully");

        for(String s:userList)uploadUsers(s);
        System.out.println("Users uploaded in database successfully");

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

    public static void uploadMovies(String log){
        MongoCollection<Document> collection = database.getCollection("movies");

        String [] logs = log.split("::");

        String movieId = logs[0];
        String movieInfo[] = logs[1].split("[()]");
        String movieName = movieInfo[0];
        String releaseYear = movieInfo[1];

        String genre[] = logs[2].split("[|]");

        Document doc = new Document("movieId", movieId)
                .append("movieName", movieName)
                .append("releaseYear", releaseYear)
                .append("genre", Arrays.asList(genre));

        collection.insertOne(doc);

    }
    public static void uploadRatings(String log){
        MongoCollection<Document> collection = database.getCollection("rating");
        String [] logs = log.split("::");

        String userId = logs[0];
        String movieId = logs[1];
        String rating = logs[2];
        String timestamp = logs[3];

        Document doc = new Document("userId", userId)
                .append("movieId", movieId)
                .append("rating", rating)
                .append("timestamp", timestamp);

        collection.insertOne(doc);
    }
    public static void uploadUsers(String log){
        MongoCollection<Document> collection = database.getCollection("users");
        String [] logs = log.split("::");
        String userId = logs[0];
        String gender = logs[1];
        String age = logs[2];
        String occupation = logs[3];
        String zip = logs[4];

        Document doc = new Document("userId", userId)
                .append("gender", gender)
                .append("age", age)
                .append("occupation", occupation)
                .append("zip",zip);

        collection.insertOne(doc);
    }
}

