import java.util.*;

public class User implements IUser {
    static HashSet <Integer> IDBase = new HashSet<>();

    private final int ID;                                         //id пользователя
    private Store store;                                    //передается пользователью через конструктор для доступа к методам store.
    private String userName;
    private String userEmail;

    User(Store store) {
        Random r = new Random();
        int ID = r.nextInt(Integer.MAX_VALUE);
        while (IDBase.contains(ID)) {
            ID = r.nextInt(Integer.MAX_VALUE);
        }
        this.ID = ID;
        this.store = store;
    }

    @Override
    public void initName() {
        if (userName == null) {                                             //todo: не реализована валидация
            System.out.print("\nВведите ваше имя: ");
            userName = new Scanner(System.in).nextLine();
        }
    }

    @Override
    public void initEMail() {
        if (userEmail == null) {                                            //todo: не реализована валидация
            System.out.print("\nУкажите вашу электронную почту: ");
            userEmail = new Scanner(System.in).nextLine();
        }
    }

    public int getID() {
        return ID;
    }

    public Store getStore() {
        return store;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
}