package databases.part02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Data Access Object for the Artist table in the Chinook database.
 */
public class ArtistDAO {
	
	
    /**
     * The connection string used to connect to the database. You MUST use this
     * string when connecting to the database using JDBC. In the unit tests, this
     * field will be set to a different value.
     */
    private final String connectionString;

    /**
     * Creates a new ArtistDAO that uses the specified connection string to connect
     * to the database. For example: "jdbc:sqlite:data/Chinook_Sqlite.sqlite"
     *
     * @param connectionString, see https://www.baeldung.com/java-jdbc-url-format
     */
    
    
    
    
    
    
    public ArtistDAO(String connectionString) {
        this.connectionString = connectionString;
    }

    /*private Connection makeConnection() {
    	Connection connection = null;
    	return connection;
    }*/
    
    
    /**
     * Returns a list of all artists in the database. The list is ordered by artist
     * name. If there are no artists in the database, an empty list is returned.
     *
     * @return a list of all artists in the database.
     */
    public List<Artist> getArtists() {
        List<Artist> artists = new LinkedList<>();
        
        
        String connectionString = "jdbc:sqlite:data/Chinook_Sqlite.sqlite";

      

            /*
             * These variables are declared outside of the try block so they can be closed
             * in the finally block. This ensures that the resources are closed even if an
             * exception is thrown and the try block is exited early.
             */
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                // Create a connection to the database
               connection = DriverManager.getConnection(connectionString);

                // Create a prepared statement to execute a query
                preparedStatement = connection.prepareStatement("SELECT Name, ArtistId FROM Artist ORDER BY Name");
                
                //ORDER BY TITLE COLLATE NOCASE ASC

                // Execute the query and get the result set
                resultSet = preparedStatement.executeQuery();

                /*
                 * Iterate over the result set and print the results. The result set contains
                 * the rows returned by the query. Each time next() is called, the result set
                 * moves to the next row.
                 *
                 * If next() returns false, then there are no more rows in the result set, and
                 * the loop terminates.
                 */
                while (resultSet.next()) {

                    /*
                     * getLong() and getString() are used to retrieve the values from the current
                     * row in the result set. The argument passed to these methods is the name of
                     * the column in the result set. There are also methods for other types of
                     * data, such as getInt() and getDouble().
                     */
                    String name = resultSet.getString("Name");
                    long id = resultSet.getLong("ArtistId");
                    Artist artist = new Artist(id, name);
                    
                    artists.add(artist);
                    
                    
                   
                }
            } catch (SQLException e) {
                /*
                 * Operations that access the database can throw SQLExceptions. SQLException is
                 * a checked exception, so it must be caught or thrown. Here we don't really
                 * handle the exception, we just print the stack trace and exit.
                 */
                e.printStackTrace();

            } finally {
                /*
                 * Close the result set, prepared statement, and connection in the finally block
                 * to ensure they are closed even if an exception is thrown.
                 *
                 * This is a bit verbose, and you could either implement a utility method to
                 * close the resources, or use a try-with-resources block.
                 */
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (Exception e) {
                    /*
                     * Even operations that close the resources can throw exceptions, so we still
                     * need to catch them.
                     */
                    e.printStackTrace();
                }
            }
        
    
        
        

        /*
         * hint: see the class from part 1 for an example of how to connect to the
         * database and retrieve data from it. This time create new Artist objects
         * instead of printing the results to the console.
         *
         * Note that you must use the `connectionString` field in this class to connect
         * to the database. You can't "hard code" the connection string, as that would
         * make tests run against your actual database, which may have unexpected data.
         */

        return artists;
    }

    /**
     * Returns the artist with the specified id, or null if no artist exists with
     * that id.
     *
     * @param id the id of the artist to retrieve.
     * @return the artist with the specified id, or null if no artist exists with
     *         that id.
     */
    public Artist getArtistById(long id) {
    	
    	Artist artist = null;

    	
        String connectionString = "jdbc:sqlite:data/Chinook_Sqlite.sqlite";

        

        /*
         * These variables are declared outside of the try block so they can be closed
         * in the finally block. This ensures that the resources are closed even if an
         * exception is thrown and the try block is exited early.
         */
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Create a connection to the database
           connection = DriverManager.getConnection(connectionString);

            // Create a prepared statement to execute a query
            preparedStatement = connection.prepareStatement("SELECT Name FROM Artist WHERE ArtistId = ?");
            
            preparedStatement.setLong(1, id);
            
            //ORDER BY TITLE COLLATE NOCASE ASC

            // Execute the query and get the result set
            resultSet = preparedStatement.executeQuery();

            /*
             * Iterate over the result set and print the results. The result set contains
             * the rows returned by the query. Each time next() is called, the result set
             * moves to the next row.
             *
             * If next() returns false, then there are no more rows in the result set, and
             * the loop terminates.
             */
            while (resultSet.next()) {

                /*
                 * getLong() and getString() are used to retrieve the values from the current
                 * row in the result set. The argument passed to these methods is the name of
                 * the column in the result set. There are also methods for other types of
                 * data, such as getInt() and getDouble().
                 */
                String name = resultSet.getString("Name");
                
                artist = new Artist(id, name);
   
            }
        } catch (SQLException e) {
            /*
             * Operations that access the database can throw SQLExceptions. SQLException is
             * a checked exception, so it must be caught or thrown. Here we don't really
             * handle the exception, we just print the stack trace and exit.
             */
            e.printStackTrace();

        } finally {
            /*
             * Close the result set, prepared statement, and connection in the finally block
             * to ensure they are closed even if an exception is thrown.
             *
             * This is a bit verbose, and you could either implement a utility method to
             * close the resources, or use a try-with-resources block.
             */
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                /*
                 * Even operations that close the resources can throw exceptions, so we still
                 * need to catch them.
                 */
                e.printStackTrace();
            }
        }
    

    
    

    /*
     * hint: see the class from part 1 for an example of how to connect to the
     * database and retrieve data from it. This time create new Artist objects
     * instead of printing the results to the console.
     *
     * Note that you must use the `connectionString` field in this class to connect
     * to the database. You can't "hard code" the connection string, as that would
     * make tests run against your actual database, which may have unexpected data.
     */

    return artist;


        
//    	preparedStatement = connection.prepareStatement("SELECT Name FROM Artist WHERE ArtistId = ?");
    	
//    	preparedStatement.setLong(1, id)
    	
    	
    	
        /*
         * hint: use similar code to the getArtists() method above, but add a WHERE
         * clause to the SQL query to only retrieve the artist with the specified id.
         *
         * The id can be added to the SQL query using PreparedStatement's setLong()
         * method. For example: preparedStatement.setLong(1, id);
         *
         * You could also just call the getArtists() method above and iterate through
         * the results until you find the artist with the specified id. This is less
         * efficient, but it gets the job done and is easy to implement.
         *
         * Note that you must use the `connectionString` field in this class to connect
         * to the database. You can't "hard code" the connection string, as that would
         * make tests run against your actual database, which may have unexpected data.
         */
        
    }
}
