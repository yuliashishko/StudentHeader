import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class main {
    Scanner scanner;

    public static void main(String[] argc) {
        IDatabase database = new PlaintextDatabase("database.txt");
        ICRUDEditor editor = new CUICrudEditor(database);
        editor.run();
    }
}
