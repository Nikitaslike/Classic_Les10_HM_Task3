package org.example;
import java.util.ArrayList;
import java.util.Scanner;
//Створіть систему управління завданнями,
// де буде класи для завдань, користувачів і операцій з завданнями.
// Реалізуйте класи "Завдання" та "Користувач".
// Клас "Завдання" повинен мати поля для назви, опису,
// статусу (виконане або невиконане) та власника.
// Клас "Користувач" повинен мати поля для імені та списку завдань.
// Напишіть методи для створення, редагування та видалення завдань,
// а також для виведення списку завдань користувача.
class Task {
    private String name;
    private String description;
    private boolean completed;
    private User owner;
    public Task(String name, String description, boolean completed, User owner) {
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.owner = owner;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    public void printInfo() {
        System.out.println("Назва: " + name);
        System.out.println("Опис: " + description);
        System.out.println("Статус: " + (completed ? "Виконане" : "Невиконане"));
        System.out.println("Виконавець: " + owner.getName());
    }
}
class User {
    private String name;
    private ArrayList<Task> tasks;
    public User(String name) {
        this.name = name;
        this.tasks = new ArrayList<Task>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public void addTask(Task task) {
        tasks.add(task);
    }
    public void printInfo() {
        System.out.println("Ім'я: " + name);
        System.out.println("Кількість завдань: " + tasks.size());
    }
    public void printTasks() {
        System.out.println("Список завдань:");
        int counter = 1;
        for (Task task : tasks) {
            System.out.println("Номер завдання: " + counter);
            task.printInfo();
            System.out.println();
            counter++;
        }
    }
}
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static User user;
    public static void main(String[] args) {
        System.out.print("Введіть ім'я користувача: ");
        String userName = scanner.next();
        user = new User(userName);
        System.out.println("Вітаю в системі управління завданнями!");
        System.out.println("Для того щоб створити нове завдання натисніть 1. Для того щоб редагувати існуюче завдання- натисніть 2.");
        System.out.println("Для того щоб Видалити існуюче завдання натисніть 3. Для того щоб вивести список завдань- натисніть 4.");
        System.out.println("Для того щоб вийти з програми- натисніть 5.");
        int choice = 0;
        do {
            System.out.print("Введіть номер операції: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createTask();
                    break;
                case 2:
                    editTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    printTasks();
                    break;
                case 5:
                    System.out.println("Дякую за використання програми!");
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
                    break;
            }
        } while (choice != 5);
    }

    public static void createTask() {
        System.out.print("Введіть назву завдання: ");
        String name = scanner.next();
        System.out.print("Введіть опис завдання: ");
        String description = scanner.next();
        System.out.print("Введіть статус завдання (true - виконане, false - невиконане): ");
        boolean completed = scanner.nextBoolean();
        Task task = new Task(name, description, completed, user);
        user.addTask(task);
        System.out.println("Завдання успішно створено!");
    }
    public static void editTask() {
        System.out.print("Введіть номер завдання, яке потрібно редагувати: ");
        int index = scanner.nextInt();
        if (index >= 0 && index < user.getTasks().size()) {
            Task task = user.getTasks().get(index);
            System.out.println("Ви обрали наступне завдання: ");
            task.printInfo();
            System.out.print("Введіть нову назву завдання або - для залишити стару: ");
            String newName = scanner.next();
            if (!newName.equals("-")) {
                task.setName(newName);
            }
            System.out.print("Введіть новий опис завдання або - для залишити старий: ");
            String newDescription = scanner.next();
            if (!newDescription.equals("-")) {
                task.setDescription(newDescription);
            }
            System.out.print("Введіть новий статус завдання (true - виконане, false - невиконане) або - для залишити старий: ");
            String newStatus = scanner.next();
            if (!newStatus.equals("-")) {
                task.setCompleted(Boolean.parseBoolean(newStatus));
            }
            System.out.println("Завдання успішно відредаговано!");
        } else {
            System.out.println("Невірний номер завдання. Спробуйте ще раз.");
        }
    }
    public static void deleteTask() {
        System.out.print("Введіть номер завдання, яке потрібно видалити: ");
        int index = scanner.nextInt();
        if (index >= 0 && index < user.getTasks().size()) {
            Task task = user.getTasks().remove(index);
            System.out.println("Ви видалили наступне завдання: ");
            task.printInfo();
            System.out.println("Завдання успішно видалено!");
        } else {
            System.out.println("Невірний номер завдання. Спробуйте ще раз.");
        }
    }
    public static void printTasks() {
        if (user.getTasks().isEmpty()) {
            System.out.println("У вас немає жодного завдання.");
        } else {
            user.printTasks();
        }
    }
}