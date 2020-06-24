package model;

import database.Boot_Table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;



public class Usuarios {

    private String name;
    private String login;

    public Usuarios(String name, String login) {
        this.name = name;
        this.login = login;
    }

    public static ArrayList<Usuarios> listaUsuarios() throws Exception {
        ArrayList<Usuarios> list = new ArrayList<>();

        Class.forName(Boot_Table.CLASS_NAME);

        Connection con = DriverManager.getConnection(Boot_Table.URL);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");

        while (rs.next()) {
            list.add(new Usuarios(rs.getString("name"), rs.getString("login")));
        }

        rs.close();
        stmt.close();
        con.close();

        return list;
    }

    public static void addUsuario(String name, String login, String password) throws Exception {
        Class.forName(Boot_Table.CLASS_NAME);

        Connection con = DriverManager.getConnection(Boot_Table.URL);
        String SQL = "INSERT INTO users(name, login, password_hash) VALUES(?,?,?)";

        PreparedStatement stmt = con.prepareStatement(SQL);

        stmt.setString(1, name);
        stmt.setString(2, login);
        stmt.setLong(3, password.hashCode());

        stmt.execute();

        stmt.close();
        con.close();

    }

    public static Usuarios buscaUsuario(String login, String password) throws Exception {
        Usuarios user = null;

        Class.forName(Boot_Table.CLASS_NAME);
        Connection con = DriverManager.getConnection(Boot_Table.URL);

        String SQL = "SELECT * FROM users WHERE login=? AND password_hash=?";
        PreparedStatement stmt = con.prepareStatement(SQL);

        stmt.setString(1, login);
        stmt.setLong(2, password.hashCode());

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            user = new Usuarios(rs.getString("name"), rs.getString("login"));
        }

        rs.close();
        stmt.close();
        con.close();

        return user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
