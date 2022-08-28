/*
В Main class реализовать menu
1. Login ( введите логин и пароль чтобы зайти в юзер)
2. Создать user
3. Список всех user ов (выводит весь список с его банковской карточкой)
4. Exit
Хранить всех users в list
При успешном логине:
    1) Transfer money - создать метод который переводит деньги с юзера который залогинился к другому юзеру по его Id и сумма перевода.
    Если у вашего юзера не хватает баланса вывести сообщение- недостаточно баланса и вывести баланс
    2) edit user data (password, login, age) (Id, username неизменяемы)
    3) logout (возвращает на главное меню)
При create User
Через сканер ввести данные вручную через консоль  - user, bank
При создании объекта bank-    Вы вправе выбирать тип банка - только из вашего enum class
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static ArrayList<User> users = new ArrayList<>();
    public static void showUserList(){
        System.out.println("Список пользователей: ");
        for (int i=0; i< users.size(); i++){
            System.out.println(users.get(i));
        }
    }
    public static User getUserById(Integer id){
        for (int i=0; i< users.size(); i++){
            if (id.equals(users.get(i).getId())){
                return users.get(i);
            }
        }
        return null;
    }
    public static User getUserByLoginAndPass(String login, String password){
        for (int i=0; i< users.size(); i++){
            if (login.equals(users.get(i).getLogin()) && password.equals(users.get(i).getPassword())){
                return users.get(i);
            }
        }
        return null;
    }
    public static void transferMoney(String login, String password){
        User sender = getUserByLoginAndPass(login, password);
        showUserList();
        System.out.println("Меню перевода денег:");
        System.out.print("Выберите из списка id пользователя для перевода денег: ");
        Integer id = scan.nextInt();
        User recipient = getUserById(id);
        if (recipient==null){
            System.out.println("Ошибка! Пользователь не существует");
        }
        else {
            if (recipient.getId().equals(sender.getId())){
                System.out.println("Ошибка! Невозможный перевод");
            }
            else if (recipient.getId()!=null){
                System.out.print("Введите сумму перевода: ");
                Double balance = scan.nextDouble();
                if (balance<sender.getBankCard().getBalance()){
                    while (true){
                        System.out.println("Перевод средств пользователю "+recipient.getUsername()+" в размере "+balance);
                        System.out.println("1 - Подвтердить");
                        System.out.println("2 - Отмена");
                        System.out.print("Выберите действие: ");
                        Integer choice = scan.nextInt();
                        if (choice.equals(1)){
                            Double balance1 = sender.getBankCard().getBalance()-balance;
                            sender.getBankCard().setBalance(balance1);
                            Double balance2 = recipient.getBankCard().getBalance()+balance;
                            recipient.getBankCard().setBalance(balance2);
                            System.out.println("Перевод успешно завершен!");
                            break;
                        }
                        else if (choice.equals(2)){
                            System.out.println("Отмена перевода!");
                            break;
                        }
                        else {
                            System.out.println("Введите корректное значение!");
                        }
                    }
                }
                else {
                    System.out.println("Ошибка! Недостаточно средств, Ваш баланс "+sender.getBankCard().getBalance());
                }
            }
            else {
                System.out.println("Error!");
            }
        }
    }
    public static void editUser(User user){
        while (true){
            System.out.println("Меню редактирования пользователя:");
            System.out.println("1 - Редактировать данные");
            System.out.println("2 - Отмена");
            System.out.print("Выберите действие: ");
            Integer choice = scan.nextInt();
            if (choice.equals(2)){
                System.out.println("Выход с меню редактирования!");
                break;
            }
            else if (choice.equals(1)){
                System.out.print("Введите login: ");
                String login = scan.next();
                System.out.print("Введите password: ");
                String password = scan.next();
                System.out.print("Введите age: ");
                Integer age = scan.nextInt();
                if (!login.trim().isEmpty() && !password.trim().isEmpty() && age!=null){
                    if (age>0 && age<200){
                        user.setLogin(login);
                        user.setPassword(password);
                        user.setAge(age);
                        System.out.println("Данные успешно сохранены");
                        break;
                    }
                    else {
                        System.out.println("Ошибка! age не может быть 0 или меньше 0 или больше 200");
                        break;
                    }
                }
                else {
                    System.out.println("Ошибка! Введите корректное значение");
                    break;
                }
            }
            else {
                System.out.println("Ошибка! Введите корректное значение");
            }
        }
    }
    public static void createUser(){
        while (true){
            System.out.println("Меню создания пользователя:");
            System.out.println("1 - Создать пользователя");
            System.out.println("2 - Отмена");
            System.out.print("Выберите действие: ");
            Integer choice = scan.nextInt();
            if (choice.equals(2)){
                System.out.println("Выход с меню создания пользователя!");
                break;
            }
            else if (choice.equals(1)){
                System.out.print("Введите fullName: ");
                String fullName = scan.next();
                System.out.print("Введите login: ");
                String login = scan.next();
                System.out.print("Введите password: ");
                String password = scan.next();
                System.out.print("Введите username: ");
                String username = scan.next();
                System.out.print("Введите age: ");
                Integer age = scan.nextInt();
                System.out.print("Введите data: ");
                String data = scan.next();
                System.out.print("Введите cvc: ");
                String cvc = scan.next();
                System.out.print("Введите balance: ");
                Double balance = scan.nextDouble();
                BankType bankType = null;
                while (true){
                    System.out.println("Банк:");
                    System.out.println("1 - Kaspi");
                    System.out.println("2 - Halyk");
                    System.out.println("3 - Jusan");
                    System.out.println("4 - Alpha");
                    System.out.print("Выберите банк: ");
                    Integer choiceBank = scan.nextInt();
                    if (choiceBank.equals(1)){
                        bankType = BankType.Kaspi;
                        break;
                    }
                    else if (choiceBank.equals(2)){
                        bankType = BankType.Halyk;
                        break;
                    }
                    else if (choiceBank.equals(3)){
                        bankType = BankType.Jusan;
                        break;
                    }
                    else if (choiceBank.equals(4)){
                        bankType = BankType.Alpha;
                        break;
                    }
                    else {
                        System.out.println("Введите корректное значение банка");
                    }
                }
                BankCard bankCard = new BankCard();
                bankCard.setData(data);
                bankCard.setFullName(fullName);
                bankCard.setCvc(cvc);
                bankCard.setBalance(balance);
                bankCard.setBankType(bankType);
                User user = new User();
                user.setId(users.size()+1);
                user.setLogin(login);
                user.setPassword(password);
                user.setUsername(username);
                user.setAge(age);
                user.setBankCard(bankCard);
                users.add(user);
                System.out.println("Пользователь успешно создан!");
                break;
            }
            else {
                System.out.println("Ошибка! Введите корректное значение");
            }
        }
    }
    public static void main(String[] args) {
        while (true){
            System.out.println("Основное меню мобильного приложения:");
            System.out.println("1 - Личный кабинет");
            System.out.println("2 - Создать пользователя");
            System.out.println("3 - Показать список пользователей");
            System.out.println("4 - Выход");
            System.out.print("Выберите действие: ");
            Integer choice = scan.nextInt();
            if (choice.equals(4)){
                System.out.println("Выход с программы!");
                break;
            }
            else if (choice.equals(3)){
                showUserList();
            }
            else if (choice.equals(1)){
                System.out.print("Введите логин: ");
                String login = scan.next();
                System.out.print("Введите пароль: ");
                String password = scan.next();
                if (!login.trim().isEmpty() && !password.trim().isEmpty()){
                    User user = getUserByLoginAndPass(login, password);
                    if (user!=null){
                        while (true){
                            System.out.println("Меню пользователя "+user.getUsername());
                            System.out.println("1 - Перевод денег");
                            System.out.println("2 - Редактирование данных");
                            System.out.println("3 - Возврат в главное меню");
                            System.out.print("Выберите действие: ");
                            Integer choice1 = scan.nextInt();
                            if (choice1.equals(3)){
                                System.out.println("Выход с личного кабинета!");
                                break;
                            }
                            else if (choice1.equals(1)){
                                transferMoney(login, password);
                            }
                            else if (choice1.equals(2)){
                                editUser(user);
                            }
                            else {
                                System.out.println("Ошибка! Введите корректное значение");
                            }
                        }
                    }
                    else {
                        System.out.println("Ошибка! Неверный логин или пароль");

                    }
                }
            }
            else if (choice.equals(2)){
                createUser();
            }
            else {
                System.out.println("Ошибка! Введите корректное значение");
            }
        }
    }
}