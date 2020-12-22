import java.util.Scanner;

public class main {
    Scanner scanner;

    public static void main(String[] argc) {
        IDatabase database = new PlaintextDatabase("C:/Users/Professional/IdeaProjects/ProjectForJava/src/main/resources/database.txt");
        ICRUDEditor editor = new CUICrudEditor(database);
        editor.run();
    }
}
