package SearchApp;


import org.hibernate.service.spi.InjectService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import utils.JSONReadFromFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import static utils.JSONReadFromFile.readFromFile;

public class SearchApp {

    @Autowired
    JSONReadFromFile jsonReadFromFile;

     public static String searchExactKeywords(String searchCriteria,String searchItem,String searchValue) {

         //search for users
         String usersJson="";
         String ticketsJson="";
         String organizationsJson="";
         StringBuilder sb=new StringBuilder();
        if(searchCriteria.equals("1")){

            if(searchValue.contains(searchValue)){

            }
           // searchMap.put(searchValue,searchItem);
            System.out.println("Welcome to the channel , you had selected the search criteria of users ");

        }
        //search for tickets
        if(searchCriteria.equals("2")){
            System.out.println("Welcome to the channel , you had selected the search criteria of tickets");
        }
        //search for organizations
        if(searchCriteria.equals("3")){
            System.out.println("Welcome to the channel , you had selected the search criteria of organizations");
        }
        return "";
    }

    public static void main(String[] args) throws IOException, ParseException {
        System.out. println("Select 1) Users or 2)Tickets or 3) Organizations ");
        Scanner scanner =new Scanner(System.in);
        String s = scanner. nextLine();
        System.out. println("Please Enter search Item ");
        String s1 = scanner. nextLine();
        System.out. println("Please Enter search Value ");
        String s2 = scanner. nextLine();
        JSONReadFromFile.readFromFile();

        //Search amd print
        String tempRes = JSONReadFromFile.searchMap.get(s2);
        String[] ids = tempRes.split("\\|\\|");
        for(String eachId : ids)
        {
            String _id= eachId.trim().substring(eachId.indexOf("::")+2);
            System.out.println(JSONReadFromFile.dataMap.get(_id));
            System.out.println("-----------------------------------------------------------");
        }

        searchExactKeywords(s,s1,s2);

        //1 id 77
    }




}
