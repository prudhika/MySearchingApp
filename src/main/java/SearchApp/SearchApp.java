package SearchApp;



import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import utils.SearchDataProvider;
import java.io.IOException;
import java.util.Scanner;
public class SearchApp {

    @Autowired
    SearchDataProvider jsonReadFromFile;



//     public static String searchExactKeywords(String searchCriteria,String searchItem,String searchValue) {
//
//         //search for users
//         String usersJson="";
//         String ticketsJson="";
//         String organizationsJson="";
//         StringBuilder sb=new StringBuilder();
//        if(searchCriteria.equals("1")){
//
//            if(searchValue.contains(searchValue)){
//
//            }
//           // searchMap.put(searchValue,searchItem);
//            System.out.println("Welcome to the channel , you had selected the search criteria of users ");
//
//        }
//        //search for tickets
//        if(searchCriteria.equals("2")){
//            System.out.println("Welcome to the channel , you had selected the search criteria of tickets");
//        }
//        //search for organizations
//        if(searchCriteria.equals("3")){
//            System.out.println("Welcome to the channel , you had selected the search criteria of organizations");
//        }
//        return "";
//    }

    public static void main(String[] args) throws IOException, ParseException {


        System.out. println("Select 1) Users or 2)Tickets or 3) Organizations ");
        Scanner scanner =new Scanner(System.in);
        String input = scanner. nextLine();
        System.out. println("Please Enter search Item ");
        String field = scanner. nextLine();
        System.out. println("Please Enter search Value ");
        String value = scanner. nextLine();

        BasicSearchService basicSearchService = new BasicSearchService();
        basicSearchService.searchExactKeywords(input,field,value);

    }




}
