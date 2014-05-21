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
package de.fhg.scai.bio.utils;

import de.fhg.scai.bio.interfaces.QueryResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JRootPane;
import javax.swing.JTextArea;

/**
 *
 * @author Vishal Siramshetty <srmshtty[at]gmail.com>
 */
public class D2RCommands {

    public static String D2RQ_Query = "/home/vishal/d2rq-0.8.1/d2r-query";
    public static String D2RQ_Server = "/home/vishal/d2rq-0.8.1/d2r-server";
    private static String queryResult;
    private static double startTime;
    private static double executionTime;
    private static TimeManager timeManager = new TimeManager();

    public static String getQueryResult() {
        return queryResult;
    }

    public static double getExecutionTime() {
        executionTime = Math.floor(executionTime * 100) / 100;
        return executionTime;
    }

    public static void executeQuery(String mappingFilePath, String queryString) {
        if (!D2RQ_Query.isEmpty()) {

            ProcessBuilder pb
                    = new ProcessBuilder("/bin/bash", D2RQ_Query, mappingFilePath, queryString);
            startTime = System.currentTimeMillis();
            try {
                Process p = pb.start();

                BufferedReader output = getOutput(p);
                BufferedReader error = getError(p);
                StringBuilder builder = new StringBuilder();
                String line = "";

                while ((line = output.readLine()) != null) {
                    builder.append(line).append("\n");
                    //System.out.println(line);
                }

                while ((line = error.readLine()) != null) {
                    builder.append(line).append("\n");
                    //System.out.println(line);
                }

                queryResult = builder.toString();
                executionTime = timeManager.getTimeElapsed(startTime, "seconds");

            } catch (IOException ex) {
                Logger.getLogger(D2RCommands.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public static void saveQueryResult(JTextArea parent, JRootPane rootpane) {
        File fileToSave = Utility.UI.saveFileAs(rootpane);
        String ext = Utility.getExtension(fileToSave);

        FileWriter fw;
        try {
            fw = new FileWriter(fileToSave);
            if (ext.equals(QueryResult.FORMAT_TXT)) {
                fw.write(parent.getText());
            }
            if (ext.equals(QueryResult.FORMAT_JSON)) {
                String res = parent.getText();
                JSONWriter writer = new JSONWriter(QueryResult.getHeads(res), QueryResult.getObjects(res));
                fw.write(writer.getJSONString());
            }
            if (ext.equals(QueryResult.FORMAT_XML)) {
                String res = parent.getText();
                XMLWriter writer = new XMLWriter(QueryResult.getHeads(res), QueryResult.getObjects(res));
                fw.write(writer.getXMLString());
            }

            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(D2RCommands.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static BufferedReader getOutput(Process p) {
        return new BufferedReader(new InputStreamReader(p.getInputStream()));
    }

    private static BufferedReader getError(Process p) {
        return new BufferedReader(new InputStreamReader(p.getErrorStream()));
    }

}
