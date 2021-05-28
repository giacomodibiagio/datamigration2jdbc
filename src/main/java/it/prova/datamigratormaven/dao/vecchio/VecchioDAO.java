package it.prova.datamigratormaven.dao.vecchio;

import it.prova.datamigratormaven.dao.AbstractMySQLDAO;
import it.prova.datamigratormaven.model.Vecchio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VecchioDAO extends AbstractMySQLDAO {

    public void setConnection(Connection connection) {
        this.connection = connection;

    }


    public List<Vecchio> oldDataInterrogation() throws Exception {
        // prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");


        String query = "SELECT d.id, d.codice_fiscale, a.nome, a.cognome,a.`data`, count(s.id) as numero_sinistri \n" +
                "from dati_fiscali d\n" +
                "inner join anagrafica a ON  a.id_datifiscali=d.id\n" +
                "left join sinistri s ON s.id_anagrafica=a.id\n" +
                "group by d.id;";

        List<Vecchio> lista=new ArrayList<>();

        try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery(query)) {

            while (rs.next()) {
                Vecchio vecchio= new Vecchio();
                vecchio.setId(rs.getLong("id"));
                vecchio.setNome(rs.getString("nome"));
                vecchio.setCognome(rs.getString("cognome"));
                vecchio.setCodiceFiscale(rs.getString("codice_fiscale"));
                vecchio.setData(rs.getDate("data"));
                vecchio.setNumeroSinistri(rs.getInt("numero_sinistri"));

                lista.add(vecchio);

            }


        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return lista;

    }
}
