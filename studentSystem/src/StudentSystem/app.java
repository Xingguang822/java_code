package StudentSystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class app {
    public static void main(String[] args) {
        ArrayList<user> list = new ArrayList<>();
        while (true) {
            System.out.println("Welcome to student system.");
            System.out.println("Please write your choice: 1.Login 2.Register 3.Forget password");
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            switch (choose) {
                case "1" -> login(list);
                case "2" -> register(list);
                case "3" -> forgetPassword(list);
                case "4" -> {
                    System.out.println("No this coisce.");
                    System.exit(0);
                }
                default -> System.out.println("No this coisce.");
            }
        }
    }


    public static void login(ArrayList<user> list) {
        Scanner sc=new Scanner(System.in);


        for (int i=0;i<3;++i) {
            System.out.println("Please write your username");
            String username=sc.next();


            if(!contains(list,username)){
                System.out.println("User"+username+"haven't register.");
                return;
            }

            System.out.println("Please write your password");
            String password=sc.next();


            while (true) {
                String rightCode=getCode();
                System.out.println("The true code is "+rightCode);
                System.out.println("Please write code on your screen");
                String code=sc.next();
                if(rightCode.equals(code)){
                    System.out.println("The code is right");
                    break;
                }else{
                    System.out.println("The code is wrong");
                    continue;
                }
            }


            user userInfo=new user(username,password,null,null);
            boolean result=checkUserInfo(list,userInfo);

            if(result){
                System.out.println("Login success,you can use student system now.");
                studentSystem ss=new studentSystem();
                ss.startStudentSystem();
                break;
            }else{
                System.out.println("Login failed.");
                if(i==2){
                    System.out.println("This account is locked,please contact to fzk.");
                    return;
                }else{
                    System.out.println("You have "+(2-i)+" chance to login.");
                }
            }
        }
    }


    public static void register(ArrayList<user> list) {
        Scanner sc = new Scanner(System.in);

        String username;
        while (true) {
            System.out.println("Please write your username");
            username = sc.next();

            boolean flag1 = checkUsername(username);
            if (!flag1) {
                System.out.println("Username don't require our requirement");
                continue;
            }
            boolean flag2 = contains(list, username);
            if (flag2) {
                System.out.println("Username already exit");
            } else {
                System.out.println("You can use this username");
                break;
            }
        }

        String password;
        while (true) {
            System.out.println("Please write your password.");
            password = sc.next();
            System.out.println("Please write your password again.");
            String againPassword = sc.next();

            if (!password.equals(againPassword)) {
                System.out.println("The two password aren't same.");
                continue;
            } else {
                break;
            }
        }

        String personID;
        while (true) {
            System.out.println("Please write your personID.");
            personID = sc.next();
            boolean flag = checkPersonID(personID);
            if (flag) {
                System.out.println("This personID require the requirement.");
                break;
            } else {
                System.out.println("This personID not require the requirement.");
            }
        }

        String phoneNumber;
        while (true) {
            System.out.println("Please write your phoneNumber.");
            phoneNumber = sc.next();
            boolean flag = checkphoneNumber(phoneNumber);
            if (flag) {
                System.out.println("This phoneNumber require the requirement.");
                break;
            } else {
                System.out.println("This phoneNumber not require the requirement.");
            }
        }

        user u = new user(username, password, personID, phoneNumber);

        list.add(u);
        System.out.println("Register success");

        printList(list);
    }


    private static void forgetPassword(ArrayList<user> list) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please write your username");
        String username=sc.next();

        if(!contains(list,username)){
            System.out.println("User "+username+" haven't register.");
            return;
        }

        System.out.println("Please write your personID");
        String personID=sc.next();

        System.out.println("Please write your phoneNumber");
        String phoneNumber=sc.next();

        int index=findindex(list,username);
        user u=list.get(index);
        if(!(u.getPersonID().equals(personID)&&u.getPhoneNumber().equals(phoneNumber))){
            System.out.println("PersonID or phoneNumber write wrong.");
            return;
        }

        String password;
        while (true) {
            System.out.println("Please write your password.");
            password = sc.next();
            System.out.println("Please write your password again.");
            String againPassword = sc.next();

            if (!password.equals(againPassword)) {
                System.out.println("The two password aren't same.");
                continue;
            } else {
                break;
            }
        }

        u.setPassword(password);
        System.out.println("Password change success.");
    }


    private static int findindex(ArrayList<user> list, String username) {
        for(int i=0;i<list.size();++i){
            if(list.get(i).getUsername().equals(username)){
                return i;
            }
        }
        return -1;
    }


    private static boolean checkUserInfo(ArrayList<user> list,user userInfo) {
        for(user u:list){
            if(u.getUsername().equals(userInfo.getUsername())&&u.getPassword().equals(userInfo.getPassword())){
                return true;
            }
        }

        return false;
    }


    private static void printList(ArrayList<user> list) {
        for (user u : list) {
            System.out.println(u.getUsername() + "," + u.getPassword() + "," + u.getPersonID() + "," + u.getPhoneNumber());
        }
    }


    private static boolean checkphoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 11 || phoneNumber.startsWith("0")) {
            return false;
        }
        for (int i = 0; i < phoneNumber.length(); ++i) {
            char c = phoneNumber.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }


    private static boolean checkPersonID(String personID) {
        if (personID.length() != 18 || personID.startsWith("0")) {
            return false;
        }

        for (int i = 0; i < personID.length() - 1; ++i) {
            char c = personID.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }

        char endChar = personID.charAt(personID.length() - 1);
        if (endChar == 'x' || endChar == 'X' || (endChar >= '0' && endChar <= '9')) {
            return true;
        } else {
            return false;
        }
    }


    private static boolean contains(ArrayList<user> list, String username) {
        for (user u : list) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }


    private static boolean checkUsername(String username) {
        int len = username.length();

        //长度的判断
        if (len < 3 || len > 15) {
            return false;
        }

        //保证用户名为数字和字母的组合
        for (int i = 0; i < username.length(); ++i) {
            char c = username.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) {
                return false;
            }
        }

        //防止用户名全为数字
        int count = 0;
        for (int i = 0; i < username.length(); ++i) {
            char c = username.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                ++count;
                break;
            }
        }
        return count > 0;
    }


    private static String getCode() {

        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            list.add((char) ('a' + i));
            list.add((char) ('A' + i));
        }


        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 4; ++i) {
            int index = r.nextInt(list.size());
            char c = list.get(index);
            sb.append(c);
        }


        sb.append(r.nextInt(10));

        char[] arr = sb.toString().toCharArray();
        int randomIndex = r.nextInt(arr.length);
        char temp = arr[randomIndex];
        arr[randomIndex] = arr[arr.length - 1];
        arr[arr.length - 1] = temp;

        return new String(arr);
    }
}
