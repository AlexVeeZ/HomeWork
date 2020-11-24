package org.example;

import java.sql.*;

public class MainApp {

    private static Connection connection;
    private static Statement stmt;
    private static ResultSet rs;
    public static PreparedStatement ps;


    public static void connect() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
    }

    public static void main(String[] args) {

        try {
            connect();
            System.out.println("Первичный вид базы:");
            getTableStudents();
            System.out.println();
            System.out.println("Добавили человека в базу:");
            addNewPerson("Алексей",40);
            getTableStudents();
            System.out.println();
            System.out.println("Удалили строки, больше 7ой id из базы, изменили параметр score у Аркадия:");
            deleteFieldValue("id>7");
            changeFieldValue("score",100,"name == 'Аркадий'");
            getTableStudents();
            System.out.println();
            System.out.println("Выводим инфо о человеке под 4-м id:");
            getPerson("id == 4");


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            disconnect();
        }

    }
    public static void getTableStudents() throws SQLException {
        rs = stmt.executeQuery("SELECT * FROM students");
        while (rs.next()){
            System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
        }
        rs.close();

    }

    public static void getPerson(String where) throws SQLException {
        rs = stmt.executeQuery("SELECT * FROM students WHERE "+where);
        System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
    }
    ////ниже метод не работает ,не смог установить причину:
    public static void getPersonPS(String where) throws SQLException {
        rs = stmt.executeQuery("SELECT * FROM students WHERE ?");
        ps.setString(1,where);
        ps.addBatch();
        ps.executeBatch();

    }


    public static void addNewPerson(String name, int score) throws SQLException {
            ps = connection.prepareStatement("INSERT INTO students (name, score) VALUES (?, ?);");
            ps.setString(1,name);
            ps.setInt(2,score);
            ps.addBatch();
            ps.executeBatch();

    }

    public static void changeFieldValue(String field, Object value, String where) throws SQLException {
        stmt.executeUpdate("UPDATE students SET "+field+" = "+value+" WHERE "+where);

    }
    //ниже метод не работает ,не смог установить причину:
    public static void changeFieldValuePS(String field, Object value, String where) throws SQLException {
        ps = connection.prepareStatement("UPDATE students SET ? = ? WHERE ?");
        ps.setString(1,field);
        ps.setObject(2,value);
        ps.setString(3,where);
        ps.addBatch();
        ps.executeBatch();

    }
    public static void deleteFieldValue(String where) throws SQLException {
        stmt.executeUpdate("DELETE FROM students WHERE "+where);

    }
    //ниже метод не работает ,не смог установить причину:
    public static void deleteFieldValuePS(String where) throws SQLException {
        ps = connection.prepareStatement("DELETE FROM students WHERE ?");
        ps.setString(1,where);
        ps.addBatch();
        ps.executeBatch();

    }

    public static void deleteTable() throws SQLException {
        stmt.executeUpdate("DELETE FROM students");
    }


    private static void createTable() throws SQLException {
        stmt.executeUpdate("CREATE TABLE students (\n" +
                "                id    INTEGER PRIMARY KEY,\n" +
                "                name  TEXT,\n" +
                "                score INTEGER\n" +
                "        );");



    }



    private static void disconnect() {

        try {
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}
