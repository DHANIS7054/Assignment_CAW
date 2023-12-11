import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JsonArrayList {
    public ArrayList<ArrayList<String>> gettingJsonArrayList(String Filepath) throws IOException, ParseException {
        //READING DATA FROM GIVEN JSON FILE AND PUTTING IT'S VALUE IN THE ARRAYLIST

        //Reading JSON file and converting it into array
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(Filepath);
        JSONArray array = new JSONArray();
        array = (JSONArray) jsonParser.parse(reader);

        //getting values only from the array and adding it into arraylist
        ArrayList<ArrayList<String>> expectedArray = new ArrayList<>();
        for(int i=0; i<array.size(); i++){
            JSONObject jsonObject = (JSONObject) array.get(i);
            ArrayList<String> user = new ArrayList<>();
            String name = (String) jsonObject.get("name");
            long age = (long) jsonObject.get("age");
            String gender = (String) jsonObject.get("gender");
            user.add(name);
            user.add(String.valueOf(age));
            user.add(gender);
            expectedArray.add(user);
        }

        //returning arraylist
        return expectedArray;
    }
}