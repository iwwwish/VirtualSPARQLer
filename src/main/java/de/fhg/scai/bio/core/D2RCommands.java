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

import de.fhg.scai.bio.vocab.QueryResult;
import de.fhg.scai.bio.utils.TimeManager;
import de.fhg.scai.bio.utils.Utility;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JRootPane;
import javax.swing.JTextArea;

/**
 * This class holds the methods to execute D2RQ commands using the D2RQ query
 * engine and mapping generator.
 *
 * @author Vishal Siramshetty <srmshtty[at]gmail.com>
 */
public class D2RCommands {

    private static final String D2RQ_Query = "d2rq-0.8.1/d2r-query";
    private static final String D2RQ_Mapping = "d2rq-0.8.1/generate-mapping";
    private static String queryResult;
    private static double startTime;
    private static double executionTime;
    private static final TimeManager timeManager = new TimeManager();
    private static final Logger VSLog = Logger.getLogger("de.fhg.scai.bio");

    /**
     * Returns the query result.
     *
     * @return query result
     */
    public static String getQueryResult() {
        return queryResult;
    }

    /**
     * Returns the query execution time.
     *
     * @return time in seconds
     */
    public static double getExecutionTime() {
        executionTime = Math.floor(executionTime * 100) / 100;
        return executionTime;
    }

    /**
     * Executes the query using a mapping file.
     *
     * @param mappingFilePath
     * @param queryString
     */
    public static void executeQuery(String mappingFilePath, String queryString) {
        if (!D2RQ_Query.isEmpty()) {
            VSLog.log(Level.INFO, "Query execution started...");
            File log = new File(System.getProperty("user.home") + "/vsparqler_log");
            ProcessBuilder pb
                    = new ProcessBuilder("/bin/bash", D2RQ_Query, mappingFilePath, queryString);
            startTime = System.currentTimeMillis();
            pb.redirectErrorStream(true);
            try {
                Process p = pb.start();
                assert pb.redirectInput() == Redirect.PIPE;
                BufferedReader output = getOutput(p);
                BufferedReader error = getError(p);
                StringBuilder builder = new StringBuilder();
                String line = "";

                while ((line = output.readLine()) != null) {
                    builder.append(line).append("\n");
                }

                while ((line = error.readLine()) != null) {
                    builder.append(line).append("\n");
                }

                queryResult = builder.toString();
                executionTime = timeManager.getTimeElapsed(startTime, "seconds");
                VSLog.log(Level.INFO, "Successful!");

            } catch (IOException ex) {
                VSLog.log(Level.SEVERE, ex.getLocalizedMessage());
            }

        }
    }

    /**
     * Generates a new mapping file (e.g. mapping.ttl) in user's home directory.
     *
     * @param connectionParams
     */
    public static void generateMappingFile(List<String> connectionParams) {
        if (!D2RQ_Mapping.isEmpty()) {
            VSLog.log(Level.INFO, "Mapping file generation started...");
            String dbName = connectionParams.get(0);
            String url = connectionParams.get(1);
            String user = connectionParams.get(2);
            String pwd = connectionParams.get(3);
            String out = System.getProperty("user.home") + "/" + dbName + "-mapping.ttl";
            File log = new File(System.getProperty("user.home") + "/vsparqler_log");

            ProcessBuilder pb
                    = new ProcessBuilder("/bin/bash", D2RQ_Mapping, "-o", out, "-u", user, "-p", pwd, url);
            pb.redirectErrorStream(true);
            pb.redirectOutput(Redirect.appendTo(log));

            try {
                Process p = pb.start();
                assert pb.redirectInput() == Redirect.PIPE;
                assert pb.redirectOutput().file() == log;
                VSLog.log(Level.INFO, "Successful!");
            } catch (IOException ex) {
                VSLog.log(Level.SEVERE, ex.getLocalizedMessage());
            }

        }
    }

    /**
     * Saves the query result in a chosen format to a destination chosen by the
     * user.
     *
     * @param parent
     * @param rootpane
     */
    public static void saveQueryResult(JTextArea parent, JRootPane rootpane) {
        VSLog.log(Level.INFO, "Attempt to save the query result.");
        File fileToSave = Utility.UI.saveFileAs(rootpane);
        if (fileToSave == null) {
            VSLog.log(Level.INFO, "Result not saved!");
        } else {
            String ext = Utility.getExtension(fileToSave);
            if (ext == null || ext.isEmpty()) {
                ext = ".txt";
            }
            FileWriter fw;
            try {
                fw = new FileWriter(fileToSave);
                // text format
                if (ext.equals(QueryResult.FORMAT_TXT)) {
                    fw.write(parent.getText());
                    Utility.UI.showInfoMessage(rootpane, "File saved as " + fileToSave.getAbsolutePath());
                } // csv format
                else if (ext.equals(QueryResult.FORMAT_CSV)) {
                    String res = parent.getText();
                    fw.write(ResultFormatter.formatResultAsCSV(res));
                    Utility.UI.showInfoMessage(rootpane, "File saved as " + fileToSave.getAbsolutePath());
                } // tsv format
                else if (ext.equals(QueryResult.FORMAT_TSV)) {
                    String res = parent.getText();
                    fw.write(ResultFormatter.formatResultAsTSV(res));
                    Utility.UI.showInfoMessage(rootpane, "File saved as " + fileToSave.getAbsolutePath());
                } // json format
                else if (ext.equals(QueryResult.FORMAT_JSON)) {
                    String res = parent.getText();
                    fw.write(ResultFormatter.formatResultAsJSON(res));
                    Utility.UI.showInfoMessage(rootpane, "File saved as " + fileToSave.getAbsolutePath());
                } // xml format
                else if (ext.equals(QueryResult.FORMAT_XML)) {
                    String res = parent.getText();
                    fw.write(ResultFormatter.formatResultAsXML(res));
                    Utility.UI.showInfoMessage(rootpane, "File saved as " + fileToSave.getAbsolutePath());
                } else {
                    Utility.UI.showInfoMessage(rootpane, "File extension inappropriate. File formats allowed are .txt .csv .tsv .json and .xml.");
                }
                fw.close();
                VSLog.log(Level.INFO, "Result saved!");
            } catch (IOException ex) {
                VSLog.log(Level.SEVERE, ex.getLocalizedMessage());
            }
        }
    }

    /**
     * Returns the InputStreamReader for output during a process.
     *
     * @param p
     * @return
     */
    private static BufferedReader getOutput(Process p) {
        return new BufferedReader(new InputStreamReader(p.getInputStream()));
    }

    /**
     * Returns the InputStreamReader for error during a process.
     *
     * @param p
     * @return
     */
    private static BufferedReader getError(Process p) {
        return new BufferedReader(new InputStreamReader(p.getErrorStream()));
    }

}
