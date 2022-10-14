import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;
    List<String> stringList = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("");
        } else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            stringList.add(String.format(parameters[1]));
            return (String.format(parameters[1]) + " added!");
        } else if (url.getPath().contains("/search")) {
            List<String> newList = new ArrayList<String>();
            String[] userSearch = url.getQuery().split("=");
            for (int i = 0; i < stringList.size(); i++) {
                if (String.format(stringList.get(i)).contains(String.format(userSearch[1]))) {
                   newList.add(String.format(stringList.get(i)));
                }
            }
            return (Arrays.toString(newList.toArray()));
        }
        return "";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
