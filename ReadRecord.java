
 /* 
 * Completed by: yobbahim javier israel perez vite
 */

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadRecord {
    
    private ObjectInputStream input;
    
    /**
     *  ObjectInputStream using a FileInputStream
     */
    
    private void readObjectsFromFile(String name)
    {
        MusicRecord record ;
        
        try
        {
            input = new ObjectInputStream(new FileInputStream( name ) );
        }
        catch ( IOException ioException )
        {
            System.err.println( "Error opening file." );
        }
        
        /* 
         * Loop terminates when an EOFException is thrown while reading object input...
         */
        
        try
        {
            while ( true )
            {
                
                
                // TO BE COMPLETED BY THE STUDENTS
            	record = (MusicRecord) input.readObject();
            	System.out.printf( "%-10d%-23s%-18s%-10.2f\n", record.getYear(), record.getSongName(), 
            			record.getSingerName(), record.getPurchasePrice());
           
            }   // END OF WHILE
        }catch ( EOFException e) {
        	System.err.println("Error ....");
        }
                // ADD NECESSARY catch CLAUSES HERE
 catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

    }          
    
    
    public static void main(String [] args)
    {
        ReadRecord record = new ReadRecord();
        record.readObjectsFromFile("mySongs.ser");
    }
}