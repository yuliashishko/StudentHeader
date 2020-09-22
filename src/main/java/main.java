import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class main {
    Scanner scanner;

    public static void main(String[] argc) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Добро пожаловать. Для работы с системой используйте клавиатуру.");
            System.out.println("Введите 1 для работы с группами");
            System.out.println("Введите 2 для работы со студентами");
            System.out.println("Введите 3 для сдачи задач");
            System.out.println("Введите 4 для завершения работы");
            int command = sc.nextInt();
            switch (command){
                case 1:
                    crudGroup();
                    break;
                case 2:
                    crudStudent();
                    break;
                case 3:
                    doTask();
                    break;
                case 4:
                    return;
            }

        }
    }

    private static void doTask() {
    }

    private static void crudStudent() {
    }

    private static void crudGroup() {
    }
}
