//Programmer: Ben Rathbone
//CS 141
//Date: 5-30-23
//Assignment: Lab 5 - Mad Libs
//Purpose: A program that allows the user to play Mad Libs
//         The user can create a new mad lib by loading in a text file
//             and filling in the blanks, or the user can read a mad lib they have created.

import java.util.*;  //imports Scanner
import java.io.*;    //imports File and PrintStream

public class MadLibs
{
   public static void main(String[] args) throws FileNotFoundException
   {
      Scanner console = new Scanner(System.in); //creates Scanner object
      
      gameIntro();   //calls the gameIntro method to explain rules
      
      //user menu
      while (true)
      {
         menuText(); //calls the menuText method to explain commands
         
         //accepts user input
         char userInput = console.nextLine().charAt(0);   //saves input as char "userInput"
         userInput = Character.toUpperCase(userInput);  //converts char to uppercase
         
         if (userInput == 'C')   //create mad lib
         {
            create(console);
         }
         else if (userInput == 'V') //view mad lib
         {
            view(console);
         }
         else if (userInput == 'Q') //quit game
         {
            System.out.println("Have a nice day!");
            break;
         }
         else  //invalid input
         {
            System.out.println("Invalid input.  Please try again.");
         }
      }//end of user menu while loop
   }//end of main method
   
   //create a new mad lib
   //accepts console
   public static void create(Scanner console) throws FileNotFoundException
   {
      //calls inputFile method to prompt for input file, and creates scanner for that file
      File inputFile = inputFile(console);
      Scanner input = new Scanner(inputFile);
      
      //calls outputFile method to create output file, and creates scanner for that file
      File outputFile = outputFile(inputFile, console);
      PrintStream output = new PrintStream(outputFile);
      
      System.out.println();
      
      while (input.hasNextLine())
      {
         Scanner line = new Scanner(input.nextLine());   //scans line
         while (line.hasNext())  //scans each token on the line
         {          
            String token = line.next();      //scans token
            
            if (token.startsWith("<") && token.endsWith(">"))  //if token is a placeholder
            {
               //gets rid of <, >, & -
               token = token.replace("<","");
               token = token.replace(">","");
               token = token.replace("-"," ");
               
               //saves first letter of token as a char and converts it to uppercase
               char firstLetter = token.charAt(0);
               firstLetter = Character.toUpperCase(firstLetter);
               
               //prompts user for input
               System.out.print("Please type a");
                  if (firstLetter == 'A' || firstLetter == 'E' || //checks if first letter
                      firstLetter == 'I' || firstLetter == 'O' || //   is a vowel
                      firstLetter == 'U')
                  {
                     System.out.print("n");  //prints n to turn "a" into "an"
                  }  
               System.out.print(" " + token + ": ");  //prints placeholder to console
               
               output.print(console.nextLine() + " ");   //prints user input to file
            }
            else  //if token is not a placeholder
            {   
               output.print(token + " "); //prints token to the file as-is
               //System.out.print(token + " ");
            }
         }   
         output.println();       //prints new line to file
         //System.out.println();
      }
      System.out.println("Your mad lib has been created!");
   }//end of create method
   
   //view a previously created mad lib
   //accepts console
   public static void view(Scanner console) throws FileNotFoundException
   {
         Scanner input = new Scanner(inputFile(console)); //calls the inputFile method to
                                                          //prompt user for file name
         System.out.println();
         while (input.hasNextLine()) //prints the contents of the file
         {
            System.out.println(input.nextLine());
         }
   }//end of view method
   
   //prompts the user for input file name
   //if the file exists, returns the file
   //accepts console
   public static File inputFile(Scanner console)
   {
      while (true)
      {
         System.out.print("Input file name: ");
         File inputFile = new File(console.nextLine());
         
         if (inputFile.exists()) //if the file exists
         {
            return inputFile;
         }
         else  //if the file does not exist
         {
            System.out.println(inputFile + " does not exist.  Please try again.");
         }
      }
   }//end of inputFile method
   
   //prompts the user for output file name
   //if output file name is different from input file name, returns the output file
   //accepts input file name and console
   public static File outputFile(File inputFile, Scanner console)
   {
      while (true)
      {
         System.out.print("Output file name: ");          //prompts user for output file name
         File outputFile = new File(console.nextLine());  //creates new file object with that name
         
         //checks if output name is identical to input name
         if (outputFile.getName().equals(inputFile.getName())) //if they are identical
         {
           System.out.println("Output file name must be different than input file name!"); 
         }
         else  //if they are different
         {
            return outputFile;
         }
      }
   }//end of outputFile method 
   
   //explains the rules of mad libs
   public static void gameIntro()
   {
      System.out.println("Welcome to the game of Mad Libs!\n" +
                         "I will ask you to provide various words\n" +
                         "and phrases to fill in a story.\n" +
                         "The result will be written to an output file.");
   }//end of gameIntro method
   
   //displays menu text explaining the functions to the user
   public static void menuText()
   {
      System.out.println("\nPlease type a command:\n" +
                         "   \"c\" to create a new mad lib\n" +
                         "   \"v\" to view a mad lib\n" +
                         "   \"q\" to quit the game");
   }//end of menuText method
}//end of program