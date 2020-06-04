// package com.group18.asdc.database;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;

// import java.sql.DriverManager;
// import java.sql.SQLException;

// import org.junit.Before;
// import org.junit.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import javax.sql.DataSource;

// public class DataSourceConnectionTest {

//     @InjectMocks
//     DataSourceConnection datasource = new DataSourceConnection();

//     @Before
//     public void init() {
//         MockitoAnnotations.initMocks(this);
//     }

//     @Test
//     public void testConnection() throws SQLException, ClassNotFoundException
//     {
//         // Class.forName(datasource.getDriverClassName());
//         // assertEquals("" , DriverManager.getConnection(datasource.getUrl(),datasource.getUsername(),datasource.getPassword()));
  
//         // assertEquals("DB Connection to DEV - H2", datasource.devDatabaseConnection());
//         // assertEquals("DB Connection to PROD", datasource.prodDatabaseConnection());
//         // assertEquals("DB Connection to TEST", datasource.testDatabaseConnection());
//         datasource.getConnection();
//     }
// }