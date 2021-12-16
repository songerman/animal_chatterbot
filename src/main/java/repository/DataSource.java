package repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import config.Info;

import java.beans.PropertyVetoException;

class DataSource {

    private static final String BASE_URL = "jdbc:postgresql://localhost:5432/animals_chatterbot";
    private static final String USERNAME = "postgres";
    private static ComboPooledDataSource cpds;

    static ComboPooledDataSource getComboPooledDataSource() {
        if (cpds == null) {
            setComboPooledDataSource();
        }
        return cpds;
    }

    private static void setComboPooledDataSource() {
        try {
            cpds = new ComboPooledDataSource();
            cpds.setDriverClass("org.postgresql.Driver");
            cpds.setJdbcUrl(BASE_URL);
            cpds.setUser(USERNAME);
            cpds.setPassword(Info.getPassword());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }
}
