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

import java.util.List;

/**
 * This class formats the query result into JSON format using a list of objects
 * and the headers.
 *
 * @author Vishal Siramshetty <srmshtty[at]gmail.com>
 */
public class JSONWriter {

    /**
     * List of headers
     */
    private List<String> headers;
    /**
     * List of objects
     */
    private List<String> objects;

    /**
     * Constructor
     *
     * @param headers
     * @param objects
     */
    public JSONWriter(List<String> headers, List<String> objects) {
        this.headers = headers;
        this.objects = objects;
    }

    /**
     * Returns the JSON string for the query result.
     *
     * @return
     */
    public String getJSONString() {
        StringBuilder jsonS = new StringBuilder();

        jsonS.append("{\n"
                + "  \"head\": {\n"
                + "    \"vars\": [ ");
        String header = headers.toString();
        jsonS.append(header.substring(1, header.length() - 1));
        jsonS.append(" ]\n"
                + "  } ,\n"
                + "  \"results\": {\n"
                + "    \"bindings\": [\n");
        int c = 0;
        for (String o : objects) {
            c++;
            jsonS.append("      {\n");
            String[] objs = o.split(";");
            for (int i = 0; i < objs.length; i++) {
                jsonS.append("        ");
                jsonS.append(headers.get(i));
                jsonS.append(": { \"type\": \"literal\" , \"value\": \"");
                jsonS.append(objs[i]);
                jsonS.append("\" }");
                if (i < objs.length - 1) {
                    jsonS.append(" ,");
                }
                jsonS.append("\n");
            }
            jsonS.append("      }");
            if (c < objects.size()) {
                jsonS.append(" ,\n");
            }
            jsonS.append("\n");
        }
        jsonS.append("    ]\n"
                + "  }\n"
                + "}");

        return jsonS.toString();
    }

}
