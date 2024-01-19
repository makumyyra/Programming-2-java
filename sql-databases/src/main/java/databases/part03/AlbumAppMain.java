package databases.part03;

import java.util.List;

import databases.part02.Artist;

/**
 * A simple application that prints all artists in the Chinook database.
 *
 * This version uses the DAO approach, and the result is cleaner, easier to test
 * and reusable.
 */
public class AlbumAppMain {

    /**
     * The connection string used to connect to the database. If you are using a
     * MySQL database, you will need to change this string to include your username
     * and password. For example:
     *
     * jdbc:mysql://localhost:3306/Chinook?user=CHANGE&password=CHANGE
     *
     * If you change the connection string here, you don't need to change it back
     * when you submit your code. The tests will use a different connection string
     * specified in the test class.
     */
    private static final String JDBC_URL = "jdbc:sqlite:data/Chinook_Sqlite.sqlite";

    public static void main(String[] args) {
    	Artist a = new Artist (1, "AC/DC");
        AlbumDAO albumDAO = new AlbumDAO(JDBC_URL);
        List<Album> albums = albumDAO.getAlbumsByArtist(a);
        		

        for (Album album : albums) {
            System.out.println(album);
            		
            		
        }
        
        boolean added = albumDAO.addAlbum(new Album("Biisi", 12));
        System.out.println(added);
    }
}
