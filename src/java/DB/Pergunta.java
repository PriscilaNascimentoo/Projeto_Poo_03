package DB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Pergunta {



    private String enunciated;
    private String answer;

    public Pergunta(String enunciated, String answer) {
        this.enunciated = enunciated;
        this.answer = answer;
    }

    public static ArrayList<Pergunta> listaPerguntas() throws Exception {
        ArrayList<Pergunta> list;
        list = new ArrayList<>();

        Class.forName(Boot_Table.CLASS_NAME);

        Connection con = DriverManager.getConnection(Boot_Table.URL);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * from questions");

        while (rs.next()) {
            list.add(new Pergunta(rs.getString("enunciated"), rs.getString("answer")));
        }

        rs.close();
        stmt.close();
        con.close();

        return list;
    }

    public static int geraNumeroRandom() {
        return (int) (Math.random() * ((150 - 0) + 1)) + 0;
    }

    public String getEnunciated() {
        return enunciated;
    }

    public void setEnunciated(String enunciated) {
        this.enunciated = enunciated;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}