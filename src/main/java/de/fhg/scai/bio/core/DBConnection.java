/*
 * Copyright (C) 2014 Vishal Siramshetty <srmshtty[at]gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.fhg.scai.bio.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * This class holds methods to validate database connection parameters by
 * establishing a connection with the database.
 *
 * @author Vishal Siramshetty <srmshtty[at]gmail.com>
 */
public class DBConnection {

    /**
     * MySQL JDBC driver class
     */
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    /**
     * Database connection
     */
    public static Connection conn = null;

    /**
     * Returns true if the Human Brain Pharmacome connection parameters are
     * valid.
     *
     * @return true or false
     */
    public static boolean testHBPConnection() {
        boolean isConnect = false;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection("jdbc:mysql://cartan-eth0:3306/HBP", "vishal", "Wq7JUubu35Sh69u");
            isConnect = true;
            return isConnect;
        } catch (ClassNotFoundException | SQLException ex) {
            return isConnect;
        }
    }

    /**
     * Returns true if the database connection parameters are valid.
     *
     * @param connectionParams
     * @return true or false
     */
    public static boolean testNewConnection(List<String> connectionParams) {
        boolean isConnect = false;

        String connURL = connectionParams.get(1);
        String userName = connectionParams.get(2);
        String pwd = connectionParams.get(3);

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(connURL, userName, pwd);
            isConnect = true;
            return isConnect;
        } catch (ClassNotFoundException | SQLException ex) {
            return isConnect;
        }
    }

}
