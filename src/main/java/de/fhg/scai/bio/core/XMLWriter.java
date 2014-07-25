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
 * This class formats the query result into XML format using a list of objects
 * and the headers.
 *
 * @author Vishal Siramshetty <srmshtty[at]gmail.com>
 */
public class XMLWriter {

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
    public XMLWriter(List<String> headers, List<String> objects) {
        this.headers = headers;
        this.objects = objects;
    }

    /**
     * Returns the XML string for the query result.
     *
     * @return
     */
    public String getXMLString() {
        StringBuilder xmlS = new StringBuilder();
        xmlS.append("<?xml version=\"1.0\"?>\n"
                + "<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\">\n"
                + "  <head>\n");
        for (String h : headers) {
            xmlS.append("    <variable name=").append(h).append("/>\n");

        }
        xmlS.append("  </head>\n"
                + "  <results>\n");

        for (String o : objects) {
            String[] objs = o.split(";");
            xmlS.append("    <result>\n");
            for (int i = 0; i < objs.length; i++) {
                xmlS.append("      <binding name=").append(headers.get(i)).append(">\n");
                xmlS.append("        <literal>").append(objs[i]).append("</literal>\n");
                xmlS.append("      </binding>\n");
            }
            xmlS.append("    </result>\n");
        }
        xmlS.append("  </results>\n"
                + "</sparql>");

        return xmlS.toString();
    }

}
