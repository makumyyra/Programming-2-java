package databases.part03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import databases.part02.Artist;

/**
 * Data Access Object for the Album table in the Chinook database.
 */
public class AlbumDAO {

    /**
     * The connection string used to connect to the database. You MUST use this
     * string when connecting to the database using JDBC. In the unit tests, this
     * field will be set to a different value.
     */
    private final String connectionString;

    /**
     * Creates a new AlbumDAO that uses the specified connection string to connect
     * to the database. For example: "jdbc:sqlite:data/Chinook_Sqlite.sqlite"
     *
     * @param jdbcConnection see https://www.baeldung.com/java-jdbc-url-format
     */
    public AlbumDAO(String jdbcConnection) {
        this.connectionString = jdbcConnection;
    }

    /**
     * Returns a list of all albums that have the specified artist as the artist.
     * If there are no albums for the specified artist, the list is empty.
     *
     * @param artist the artist whose albums to retrieve.
     * @return a list of all albums that have the specified artist as the artist,
     *         sorted by AlbumId in ascending order.
     */
    public List<Album> getAlbumsByArtist(Artist artist) {
    	
    	List<Album> albums = new LinkedList<>();
    	
    	long artistId = artist.getId();
    	
    	
    	//String connectionString = "jdbc:sqlite:data/Chinook_Sqlite.sqlite";

    	 Connection connection = null;
         PreparedStatement preparedStatement = null;
         ResultSet resultSet = null;

         try {
             // Create a connection to the database
            connection = DriverManager.getConnection(connectionString);

             // Create a prepared statement to execute a query
             preparedStatement = connection.prepareStatement("SELECT AlbumId, Title, ArtistId FROM Album WHERE ArtistId = ?");             
             preparedStatement.setLong(1, artistId);
            
             // Execute the query and get the result set
             resultSet = preparedStatement.executeQuery();

            
             while (resultSet.next()) {

                 String title = resultSet.getString("Title");
                 long albumId = resultSet.getLong("AlbumId");
                 Album album = new Album(albumId, title, artistId);
                 albums.add(album);
    
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

     return albums;
  
}

    			
   	
       


    /**
     * Adds the specified album to the database. Returns true if the album was
     * added successfully, false otherwise.
     *
     * @param album the album to add to the database.
     * @return true if the album was added successfully, false otherwise.
     */
    public boolean addAlbum(Album album) {
    	
    	boolean added = false;
    	
    	//long id = album.getId();
        String title = album.getTitle();
        long artistId = album.getArtistId();
        
        Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
        
        try {
            // Create a connection to the database
           connection = DriverManager.getConnection(connectionString);
           preparedStatement = connection.prepareStatement("INSERT INTO Album (Title, ArtistId) VALUES (?, ?);");
           
           //preparedStatement.setLong(1, id);
           preparedStatement.setString(1, title);
           preparedStatement.setLong(2, artistId);
    
   
           int count = preparedStatement.executeUpdate();
           
           if(count > 0) {
        	   added = true;
           }
           
        } catch (SQLException e) {
            
            e.printStackTrace();

        } finally {
            
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
                
                e.printStackTrace();
            }
        }

    return added;
 
}
    	
        /*
         * hint 1: use PreparedStatement's setString() and setLong() methods to
         * add the album's title and artist id to the SQL query. Leave the AlbumId
         * blank, as it will be automatically generated by the database.
         *
         * hint 2: executeUpdate() returns the number of rows affected by the query.
         * If the number of rows affected is greater than 0, the album was added.
         *
         * Remember to use the `connectionString` instead of hard coding it ;)
         */


    /**
     * Updates the specified album in the database. Returns true if the album was
     * updated successfully, false otherwise.
     *
     * @param album the album to update in the database.
     * @return true if the album was updated successfully, false otherwise.
     */
    public boolean updateAlbum(Album album) {
        
    	boolean updated = false;
    	
    	long id = album.getId();
        String title = album.getTitle();
        long artistId = album.getArtistId();
        
        Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
        
        try {
            // Create a connection to the database
           connection = DriverManager.getConnection(connectionString);
           preparedStatement = connection.prepareStatement("UPDATE Album SET Title = ?, ArtistId = ? WHERE AlbumId = ?;");
           
           preparedStatement.setString(1, title);
           preparedStatement.setLong(2, artistId);
           preparedStatement.setLong(3, id);
   
           int count = preparedStatement.executeUpdate();
           
           if(count > 0) {
        	   updated = true;
           }
           
        } catch (SQLException e) {
            
            e.printStackTrace();

        } finally {
            
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
                
                e.printStackTrace();
            }
        }

    return updated;
 
    }
    	
    	
    	
    	/*
         * hint 1: use PreparedStatement's setString() and setLong() methods to
         * add the album's title and artist id to the SQL query. Do not change the
         * AlbumId, but use it to identify the album to update.
         *
         * Remember to use the `connectionString` instead of hard coding it, otherwise
         * your tests will be deleting albums from your actual database!
         */


    /**
     * Deletes the specified album from the database. Returns true if the album was
     * deleted successfully, false otherwise.
     *
     * @param album the album to delete from the database.
     * @return true if the album was deleted successfully, false otherwise.
     */
    public boolean deleteAlbum(Album album) {
    	
    	boolean deleted = false;
    	
    	
    	long id = album.getId();
      //  String title = album.getTitle();
      //  long artistId = album.getArtistId();
        
        Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
        
        try {
            // Create a connection to the database
           connection = DriverManager.getConnection(connectionString);
           preparedStatement = connection.prepareStatement("DELETE FROM Album WHERE AlbumId = ?;");
           //, Title = ?, ArtistId = ?
           //DELETE FROM table_name WHERE condition;
           
           preparedStatement.setLong(1, id);
           //preparedStatement.setString(2, title);
           //preparedStatement.setLong(3, artistId);
           
   
           int count = preparedStatement.executeUpdate();
           
           if(count > 0) {
        	   deleted = true;
           }
           
        } catch (SQLException e) {
            
            e.printStackTrace();

        } finally {
            
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
                
                e.printStackTrace();
            }
        }
    	
        /*
         * See hints for the methods above.
         *
         * Remember to use the `connectionString` instead of hard coding it, otherwise
         * your tetss will be deleting albums from your actual database!
         */
        return deleted;
    }
}
