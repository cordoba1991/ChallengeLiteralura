import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BookParser {
    public static void parseBooks(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonArray results = jsonObject.getAsJsonArray("results");

        for (int i = 0; i < results.size(); i++) {
            JsonObject book = results.get(i).getAsJsonObject();
            String title = book.get("title").getAsString();
            JsonArray authorsArray = book.getAsJsonArray("authors");
            StringBuilder authors = new StringBuilder();
            for (int j = 0; j < authorsArray.size(); j++) {
                JsonObject author = authorsArray.get(j).getAsJsonObject();
                if (j > 0) {
                    authors.append(", ");
                }
                authors.append(author.get("name").getAsString());
            }
            System.out.println("Title: " + title);
            System.out.println("Authors: " + authors.toString());
        }
    }
}
