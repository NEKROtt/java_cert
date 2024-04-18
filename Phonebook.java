import java.util.*;

public class Phonebook {
    private static Map<String, Set<String>> phoneBook = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nТелефонная книга:\n");
            System.out.println("1. Просмотреть записи");
            System.out.println("2. Добавить");
            System.out.println("3. Удалить");
            System.out.println("4. Редактировать");
            System.out.println("5. Выйти");
            System.out.print("\nВыберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            clearConsole();

            switch (choice) {
                case 1:
                    displayItems();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    removeItem();
                    break;
                case 4:
                    editItem();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("\nТакого действия нет! Введите корректно пожалуйста)\n");
            }
        }
        System.out.println("\nРабота приложения завершена.\n");
    }

    private static void displayItems() {
        if (phoneBook.isEmpty()) {
            System.out.println("\nТелефонная книга пуста.\n");
        } else {
            List<Map.Entry<String, Set<String>>> sortedEntries = new ArrayList<>(phoneBook.entrySet());
            sortedEntries.sort((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()));
            System.out.println("Записи в телефонной книге (отсортированы по убыванию числа телефонов):\n");
            for (Map.Entry<String, Set<String>> entry : sortedEntries) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
        tapEnter();
    }

    private static void addItem() {
        System.out.print("ДОБАВЛЕНИЕ\n\n");
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите номер телефона: ");
        String phoneNumber = scanner.nextLine();
        phoneBook.computeIfAbsent(name, k -> new HashSet<>()).add(phoneNumber);
        System.out.println("\nЗапись добавлена: " + name + ": " + phoneNumber);
        tapEnter();
    }
    private static void removeItem() {
        System.out.print("УДАЛЕНИЕ\n\n");
        System.out.print("Введите имя для удаления: ");
        String name = scanner.nextLine();
        if (phoneBook.containsKey(name)) {
            Set<String> phoneNumbers = phoneBook.remove(name);
            System.out.println("Запись удалена: " + name + ": " + phoneNumbers);
        } else {
            System.out.println("Запись с таким именем не найдена.");
        }
        tapEnter();
    }

    private static void editItem() {
        System.out.print("РЕДАКТИРОВАНИЕ\n\n");
        System.out.print("Введите имя для редактирования: ");
        String name = scanner.nextLine();
        if (phoneBook.containsKey(name)) {
            System.out.print("Введите новый номер телефона: ");
            String newPhoneNumber = scanner.nextLine();
            phoneBook.get(name).add(newPhoneNumber);
            System.out.println("Запись отредактирована: " + name + ": " + phoneBook.get(name));
        } else {
            System.out.println("Запись с таким именем не найдена!");
        }
        tapEnter();
    }

    private static void tapEnter() {
        System.out.print("\nНажмите ENTER для продолжения ");
        scanner.nextLine();
        clearConsole();
    }
   
    public static void clearConsole() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }
}