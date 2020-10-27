package com.interview.designpatterns;
/*
 * Reference: https://www.youtube.com/watch?v=_tS0CzMN9P8&list=PLmCsXDGbJHdhULIoYzIIIeFFJSo1NlMbN&index=4
 * Reference: https://www.baeldung.com/java-connection-pooling
 * Reference: https://www.javamadesoeasy.com/2015/12/connection-pooling-in-java-with-example.html  (Good)
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

public class ConnectionPoolExample {
    interface ConnectionPool {
        Connection getConnection();
        boolean releaseConnection(Connection connection);
        String getUrl();
        String getUser();
        String getPassword();
    }
    
    class BasicConnectionPool implements ConnectionPool {
   
      private String url;
      private String user;
      private String password;
      private List<Connection> connectionPool;
      private List<Connection> usedConnections = new ArrayList<>();
      private static final int INITIAL_POOL_SIZE = 10;
      
      public static BasicConnectionPool create(String url, String user, String password) throws SQLException {
   
          List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
          for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
              pool.add(createConnection(url, user, password));
          }
          return new BasicConnectionPool(url, user, password, pool);
      }
      
      // standard constructors
      
      @Override
      public Connection getConnection() {
          Connection connection = connectionPool
            .remove(connectionPool.size() - 1);
          usedConnections.add(connection);
          return connection;
      }
      
      @Override
      public boolean releaseConnection(Connection connection) {
          connectionPool.add(connection);
          return usedConnections.remove(connection);
      }
      
      private static Connection createConnection(
        String url, String user, String password) 
        throws SQLException {
          return DriverManager.getConnection(url, user, password);
      }
      
      public int getSize() {
          return connectionPool.size() + usedConnections.size();
      }
   
      // standard getters
  }
    
    public void runStatePatternExample() {
        
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ConnectionPoolExample cpe = new ConnectionPoolExample();
        cpe.runStatePatternExample();
        
    }
    
}
