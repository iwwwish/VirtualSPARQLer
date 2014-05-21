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
package de.fhg.scai.bio.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vishal Siramshetty <srmshtty[at]gmail.com>
 */
public class QueryResult {

    public static String FORMAT_TXT = ".txt";
    public static String FORMAT_XML = ".xml";
    public static String FORMAT_JSON = ".json";

    public static List<String> getHeads(String result) {
        List<String> heads = new ArrayList<>();

        String[] lines = result.split("\n");
        String header = lines[1];
        header = header.substring(1, header.length());
        String[] names = header.split("\\|");
        for (String name : names) {
            name = name.trim();
            StringBuilder builder = new StringBuilder();
            builder.append("\"").append(name).append("\"");
            heads.add(builder.toString());
        }

        return heads;
    }

    public static List<String> getObjects(String result) {
        List<String> objects = new ArrayList<>();
        String[] lines = result.split("\n");

        for (int i = 3; i < lines.length - 1; i++) {
            int p = lines[i].lastIndexOf("\"");
            String object = lines[i].substring(2, p);

            if (object.contains("|")) {
                String[] nLines = object.split("\\|");
                StringBuilder builder = new StringBuilder();
                for (String l : nLines) {
                    builder.append(l.trim()).append(";");
                }
                object = builder.toString().replaceAll("\"", "");
                objects.add(object.substring(0, object.length() - 1));
            } else {
                objects.add(object.trim());
            }
        }

        return objects;
    }

}
