package lesson_5;

import java.sql.SQLOutput;
//создал класс Worker
public class Worker {
    //указал все аргументы, присущие нашим сотрудникам
    protected String name;
    protected int age;
    protected char gender;
    protected String post;
    protected double salary;
    protected String email;
    protected String telephone;//указал строковое значение, для отображения телефона с дефисами


    //создал основной конструктор
    public Worker(String name, int age, char gender, String post, double salary, String email, String telephone) {

        this.name = name;
        this.age = age;
        this.gender = gender;
        this.post = post;
        this.salary = salary;
        this.email = email;
        this.telephone = telephone;

    }
    //в данной версии конструктора нет никакой необходимости,
    //можно при создании сотрудника в мэйне задать значение "ОТСУТСТВУЕТ"
    public Worker(String name, int age, char gender, String post, double salary, String telephone) {

        this(name, age, gender, post, salary, "ОТСУТСТВУЕТ", telephone);
    }
    //данный метод выводит анкету в консоль
    @Override
    public String toString() {
        System.out.println("\n\tАНКЕТА СОТРУДНИКА");
        return "ФИО: " + name
                +"\nВозраст: " + age
                +"\nПол: " + gender
                +"\nДолжность: " + post
                +"\nЗарплата: " + salary + " РУБ."
                +"\nЭл.почта: " + email
                +"\nТелефон: " + telephone;

    }
    //далее идут геттеры и сеттеры
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public String getPost() {
        return post;
    }
    public void setPost(String post) {
        this.post = post;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getEmail() { return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }





}
