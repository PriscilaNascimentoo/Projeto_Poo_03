 package database;



import model.Usuarios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import DB.Pergunta;

public class Boot_Table implements ServletContextListener {

    public static final String URL = "jdbc:sqlite:C:\\Users\\prisc\\quiz.db "; 
    public static final String CLASS_NAME = "org.sqlite.JDBC";

    public static String exceptionMessage = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String step = "Inicialização das tabelas";
        try {
            Class.forName(CLASS_NAME);
            Connection con = DriverManager.getConnection(URL);
            Statement stmt = con.createStatement();

            String SQL;

            step = "Tabelas para o login";
            SQL = "CREATE TABLE IF NOT EXISTS users("
                    + "name VARCHAR(200) NOT NULL,"
                    + "login VARCHAR(50) UNIQUE NOT NULL,"
                    + "password_hash LONG"
                    + ")";
            stmt.executeUpdate(SQL);
            
            step = "Criando tabela de questão";
            SQL = "CREATE TABLE IF NOT EXISTS questions ("
                    + "enunciated VARCHAR(500) UNIQUE NOT NULL,"
                    + "answer VARCHAR(100) NOT NULL"
                    + ")";
            stmt.executeUpdate(SQL);

            if (Usuarios.listaUsuarios().isEmpty()) {
                step = "Adicionando usuário como administrador";
                SQL = "INSERT INTO users VALUES('Administrador', 'admin', '" + ("admin".hashCode()) + "');";
                stmt.executeUpdate(SQL);
            }
            
            if (Pergunta.listaPerguntas().isEmpty()) {
                step = "Inserindo perguntas";
                SQL = "INSERT INTO questions VALUES('Ano de fundação do Google?: 2001, 2004, 2008, 1989, _', '1998');";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO questions VALUES('Qual foi a primeira linguagem de programação utilizada no mundo?: Pascal, Cobol, _', 'Fortran');";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO questions VALUES('Criador da IBM: Tim Berners-Lee, Mark Zuckerberg, Bill Gates, Xuxa, _', 'Charles Ranlett Flint');";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO questions VALUES('Qual é o aplicativo mais usado no mundo?: Snapchat, Instagram, Uber, Spotify, _', 'Whatsapp');";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO questions VALUES('Qual é a linguagem mais popular do mundo?:  C++, Phyton, Php, C#, _', 'Java');";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO questions VALUES('Navegador mais utilizado atualmente?: Microsoft Edge, Microsoft Internet Explorer, Mozilla Firefox, Apple Safari, _', 'Google Chrome');";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO questions VALUES('Dona da ferramenta de pesquisas Bing?: Apple, Facebook,  Google, Bing SA, _', 'Microsoft');";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO questions VALUES('Qual a área de TI que mais contrata?: Suporte, Segurança da Informação, Infraestrutura, Gestão de projetos, _', 'Desenvolvimento de software');";
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO questions VALUES('Ano de fundação da Microsoft?:  1985, 1987, 1990, 2001, _', '1975');"; 
                stmt.executeUpdate(SQL);
                SQL = "INSERT INTO questions VALUES('Versão atual do HTML?: X, Ultra, 2, PHP, _', '5');";
                stmt.executeUpdate(SQL);
            }

            stmt.close();
            con.close();
        } catch (Exception ex) {
            exceptionMessage = step + ": " + ex;
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
