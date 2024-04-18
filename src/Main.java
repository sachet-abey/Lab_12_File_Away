import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
public class Main {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser(); // Create a JFileChooser object to choose a file
        File selectedFile; // Declare a variable for the selected file
        String rec = ""; // Initialize a variable for the line
        try { // If file found and successful IO operation
            File workingDirectory = new File(System.getProperty("user.dir")); // Get the current working directory and save it in a File object
            chooser.setCurrentDirectory(workingDirectory); // Sets the current directory
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { // If file choose dialog returns approve (yes)
                selectedFile = chooser.getSelectedFile(); // Get selected file and store it in a variable
                Path file = selectedFile.toPath(); // Initialize a Path object of the selected file
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE)); // Create an input stream for the specified file
                BufferedReader reader = new BufferedReader(new InputStreamReader(in)); // Create a buffered reader to read characters from the input stream
                String fileName = String.valueOf(file.getFileName()); // Initialize file name by using Path object's .getFileName() method
                int lines = 0; // Initialize number of lines to 0
                int words = 0; // Initialize number of words to 0
                int characters = 0; // Initialize numbers of characters to 0
                while (reader.ready()) { // While the reader is ready
                    rec = reader.readLine(); // Read the line
                    lines++; // Add 1 to lines
                    words += rec.split(" ").length; // Find number of words by splitting by space and add that to words variable
                    characters += rec.length(); // Increase the number of characters by the string's length
                }
                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("Summary Report:"); // Print summary report
                System.out.println("File name: "+fileName); // Print file name
                System.out.println("Number of lines: "+lines); // Print number of lines
                System.out.println("Number of words: "+words); // Print number of words
                System.out.println("Number of characters: "+characters); // Print number of characters
            } else  // User closed the chooser without selecting a file
            {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file."); // Tell user that no file was selected and try again
            }
        }
        catch (FileNotFoundException e) // If file not found
        {
            System.out.println("File not found!!!"); // Print that file was not found
            e.printStackTrace(); // Print the throwable and other details to track the exception
        }
        catch (IOException e) // IO operation failed so catch an exception
        {
            e.printStackTrace(); // Print the throwable and other details to track the exception
        }
    }
}
