package StudentSystem;

import java.util.Scanner;
import java.util.ArrayList;

public class studentSystem {

    private static final String ADD_STUDENT="1";
    private static final String DELETE_STUDENT="2";
    private static final String UPDATE_STUDENT="3";
    private static final String QUERY_STUDENT="4";
    private static final String EXIT="5";

    public static void startStudentSystem() {
        ArrayList<student> list = new ArrayList<>();
        loop:
        while (true) {
            System.out.println("--------------------Welecome to heima's student system--------------------");
            System.out.println("1:Add student");
            System.out.println("2:Delete student");
            System.out.println("3:update student");
            System.out.println("4:Query student");
            System.out.println("5:exit");
            System.out.println("Please write your choice:");
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            switch (choose) {
                case ADD_STUDENT -> addStudent(list);
                case DELETE_STUDENT -> deleteStudent(list);
                case UPDATE_STUDENT -> updateStudent(list);
                case QUERY_STUDENT -> queryStudent(list);
                case EXIT -> {
                    System.out.println("exit");
                    break loop;
                }
                default -> System.out.println("No this choice.");
            }
        }

    }

    //add student
    public static void addStudent(ArrayList<student> list) {
        Scanner sc = new Scanner(System.in);

        String id = null;
        while (true) {
            System.out.println("Please write student's id");
            id = sc.next();
            if (contains(list, id)) {
                System.out.println("There already have a same student id!");
            } else {
                break;
            }
        }

        System.out.println("Please write student's name");
        String name = sc.next();

        System.out.println("Please write student's age");
        int age = sc.nextInt();

        System.out.println("Please write student's address");
        String address = sc.next();

        student s = new student(id, name, age, address);
        list.add(s);

        System.out.println("Add success");
    }


    //delete student
    public static void deleteStudent(ArrayList<student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please write student's id");
        String id = sc.next();
        int index = getIndex(list, id);

        if (index >= 0) {
            list.remove(index);
            System.out.println("Already delete" + id);
        } else {
            System.out.println("There haven't this student.");
        }

    }


    //update student
    public static void updateStudent(ArrayList<student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please write student's id");
        String id = sc.next();
        int index = getIndex(list, id);

        if (index == -1) {
            System.out.println("There haven't this student.");
            return;
        }

        student stu = list.get(index);

        System.out.println("Please write student's name");
        String name = sc.next();
        stu.setName(name);
        System.out.println("Please write student's age");
        int age = sc.nextInt();
        stu.setAge(age);
        System.out.println("Please write student's address");
        String address = sc.next();
        stu.setAddress(address);
    }


    //query student
    public static void queryStudent(ArrayList<student> list) {
        if (list.isEmpty()) {
            System.out.println("There aren't have student");
        }
        System.out.println("id\t\tname\tage\t\taddress");
        for (student stu : list) {
            System.out.println(stu.getId() + "\t\t" + stu.getName() + "\t\t" + stu.getAge() + "\t\t" + stu.getAddress());
        }
    }


    public static boolean contains(ArrayList<student> list, String id) {
        for (student s : list) {
            if (s.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }


    public static int getIndex(ArrayList<student> list, String id) {
        for (int i = 0; i < list.size(); ++i) {
            student stu = list.get(i);
            String sid = stu.getId();
            if (sid.equals(id)) return i;
        }
        return -1;
    }
}
