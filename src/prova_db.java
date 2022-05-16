import java.sql.*;
public class prova_db {
    public static void main (String args[]) {
        try {
// Carichiamo un driver
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
// Creiamo la stringa di connessione
            String url = "jdbc:mysql://localhost:3306/rubrica";
// Creiamo un oggetto Statement per poter interrogare il db
            try (// Otteniamo una connessione con username e password
                 Connection con = DriverManager.getConnection (url, "root", ""); // Creiamo un oggetto Statement per poter interrogare il db
                 Statement cmd = con.createStatement ()) {
                // Eseguiamo una query e immagazziniamone i risultati
                // in un oggetto ResultSet
                String qry = "SELECT `anagrafica_clienti`.`ragione_sociale`, `anagrafica_clienti`.`localita`, `anagrafica_clienti`.`partita_iva`, `vendite`.`descrizione`, " +
                        "`vendite`.`prezzo_di_vendita`, `vendite`.`giacenza`" +
                        " FROM `anagrafica_clienti` INNER JOIN `vendite` ON `vendite`.`id_anagrafica` = `anagrafica_clienti`.`id_anagrafica`;";
// Stampiamone i risultati riga per riga
                try (ResultSet res = cmd.executeQuery(qry)) {
                    // Stampiamone i risultati riga per riga
                    while (res.next()) {
                        System.out.println("Ragione Sociale: " + res.getString("ragione_sociale") + " ");
                        System.out.println("Localita': " + res.getString("localita") + " ");
                        System.out.println("Partita Iva: " + res.getString("partita_iva") + " ");
                        System.out.println("Descrizione Articolo: " + res.getString("descrizione") + " ");
                        System.out.println("Prezzo Di Vendita: " + res.getString("prezzo_di_vendita") + " ");
                        System.out.println("Rimanenze: " + res.getString("giacenza"));
                        System.out.println("*********************************");
                    }   }
            }
        } catch (SQLException | ClassNotFoundException e) {
        }
    }
}