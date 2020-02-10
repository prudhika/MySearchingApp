package SearchApp;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import utils.AppConstants;
import java.io.IOException;
import java.util.Scanner;

public class SearchApp {

    static final Logger logger = Logger.getLogger(SearchApp.class);
    private static String input, field, value ;
    static boolean repeatSearch = Boolean.TRUE;
    static boolean isFirstSearch = Boolean.TRUE;

    public static void main(String[] args) {

        //Configure logger
        BasicConfigurator.configure();
        org.apache.log4j.Logger.getRootLogger().setLevel(Level.INFO);

        Scanner scanner =new Scanner(System.in);

        BasicSearchService basicSearchService = new BasicSearchService();
        try {
            while(repeatSearch)
            {
                scanUserInput(scanner);
                basicSearchService.searchExactKeywords(input,field,value,isFirstSearch); // perform search
                /* Select the input */
                System.out.println("\n Do you want to do a new search ? 1) YES or Any other key: NO ");
                isFirstSearch = Boolean.FALSE;

                if(!scanner.nextLine().equalsIgnoreCase(AppConstants.ONE))
                {
                    repeatSearch = Boolean.FALSE;
                    System.out.println("See you again!");
                }
            }
        } catch (IOException e) {
            logger.error(AppConstants.USER_ERROR_MESSAGE + e.getStackTrace());
        } catch (ParseException e) {
            logger.error(AppConstants.USER_ERROR_MESSAGE + e.getStackTrace());
        }
    }

    static void scanUserInput(Scanner scanner)
    {
        /* Select the input */
        System.out.println("Select 1) Users or 2)Tickets or 3) Organizations ");
         input = scanner. nextLine();

        /* Select the search field */
        System.out.println("Please Enter search Item "); // Can be replaced with Logger later with some customizations.
         field = scanner. nextLine();

        /* Enter the search value */
        System.out.println("Please Enter search Value ");
         value = scanner. nextLine();
    }
}
