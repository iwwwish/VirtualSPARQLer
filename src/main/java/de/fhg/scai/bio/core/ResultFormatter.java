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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class holds the methods to format a query query result into different
 * formats that can be saved by the user.
 *
 * @author Vishal Siramshetty <srmshtty[at]gmail.com>
 */
public class ResultFormatter {

    /**
     * Formats the result as XML string.
     *
     * @param result
     * @return XML String
     */
    public static String formatResultAsXML(String result) {
        List<String> results = new ArrayList<>();
        // split result by new line
        List<String> lines = Arrays.asList(result.split("\n"));
        // remove first line and last line
        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).contains("|")) {
                results.add(lines.get(i));
            }
        }
        // get heads
        List<String> heads = getHeads(results);
        // get objects
        List<String> objects = getObjects(results);
        // format as XML String
        XMLWriter writer = new XMLWriter(heads, objects);

        return writer.getXMLString();
    }

    /**
     * Formats the result as JSON string.
     *
     * @param result
     * @return JSON String
     */
    public static String formatResultAsJSON(String result) {
        List<String> results = new ArrayList<>();
        // split result by new line
        List<String> lines = Arrays.asList(result.split("\n"));
        // remove first line and last line
        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).contains("|")) {
                results.add(lines.get(i));
            }
        }
        // get heads
        List<String> heads = getHeads(results);
        // get objects
        List<String> objects = getObjects(results);
        // format as JSON String
        JSONWriter writer = new JSONWriter(heads, objects);

        return writer.getJSONString();
    }

    /**
     * Formats the result as TSV string.
     *
     * @param result
     * @return TSV String
     */
    public static String formatResultAsTSV(String result) {
        List<String> results = new ArrayList<>();
        // split result by new line
        List<String> lines = Arrays.asList(result.split("\n"));
        // remove first line and last line
        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).contains("|")) {
                results.add(lines.get(i));
            }
        }
        // get heads
        String header = getHeader(results, "\t");
        // get objects
        List<String> objects = getObjects(results, "\t");
        // format as XML String
        TSVWriter writer = new TSVWriter(header, objects);

        return writer.getTSVString();
    }

    /**
     * Formats the result as CSV string.
     *
     * @param result
     * @return CSV String
     */
    public static String formatResultAsCSV(String result) {
        List<String> results = new ArrayList<>();
        // split result by new line
        List<String> lines = Arrays.asList(result.split("\n"));
        // remove first line and last line
        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).contains("|")) {
                results.add(lines.get(i));
            }
        }
        // get heads
        String header = getHeader(results, ",");
        // get objects
        List<String> objects = getObjects(results, ",");
        // format as XML String
        TSVWriter writer = new TSVWriter(header, objects);

        return writer.getTSVString();
    }

    /**
     * Returns a list of headers from the query result.
     *
     * @param result
     * @return List<String>
     */
    private static List<String> getHeads(List<String> result) {
        List<String> heads = new ArrayList<>();

        String header = result.get(0);
        List<String> names = Arrays.asList(header.split("\\|"));
        for (String n : names) {
            if (!n.isEmpty()) {
                heads.add(n.trim());
            }
        }

        return heads;
    }

    /**
     * Returns a single header string that contains the headers from query
     * result separated by a user specified separator.
     *
     * @param result
     * @param separator
     * @return
     */
    private static String getHeader(List<String> result, String separator) {

        String header = result.get(0);
        List<String> names = Arrays.asList(header.split("\\|"));
        StringBuilder head = new StringBuilder();
        for (String n : names) {
            if (!n.isEmpty()) {
                head.append(n.trim()).append(separator);
            }
        }
        header = head.toString().substring(0, head.length() - 1);

        return header;
    }

    /**
     * Returns a list of objects from the query result.
     *
     * @param result
     * @return List<String>
     */
    private static List<String> getObjects(List<String> result) {
        List<String> obs = new ArrayList<>();
        result = result.subList(1, result.size());

        for (String obj : result) {
            List<String> objs = Arrays.asList(obj.split("\\|"));
            StringBuilder object = new StringBuilder();
            for (String o : objs) {
                if (!o.isEmpty()) {
                    object.append(o.trim()).append(";");
                }
            }
            String ob = object.toString().substring(0, object.length() - 1);
            if (ob.contains("\"")) {
                ob = ob.replaceAll("\"", "");
            }
            obs.add(ob);
        }

        return obs;
    }

    /**
     * Returns a list of objects from the query result, each separated by the
     * user specified separator.
     *
     * @param result
     * @return List<String>
     */
    private static List<String> getObjects(List<String> result, String separator) {
        List<String> obs = new ArrayList<>();
        result = result.subList(1, result.size());

        for (String obj : result) {
            List<String> objs = Arrays.asList(obj.split("\\|"));
            StringBuilder object = new StringBuilder();
            for (String o : objs) {
                if (!o.isEmpty()) {
                    object.append(o.trim()).append(separator);
                }
            }
            String ob = object.toString().substring(0, object.length() - 1);
            if (ob.contains("\"")) {
                ob = ob.replaceAll("\"", "");
            }
            obs.add(ob);
        }

        return obs;
    }
}
