import lombok.SneakyThrows;

import java.util.Scanner;

public class main {
    Scanner scanner;

    @SneakyThrows
    public static void main(String[] argc) {
        IDatabase database =
                new SQLiteDataBase("jdbc:sqlite:C:\\Users\\Professional\\PycharmProjects\\peopleTester\\server\\db\\tester.sqlite");
        //ICRUDEditor editor = new CUICrudEditor(database);
        //editor.run();
    }
}
