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
 * This class formats the query result into either TSV or CSV format using a
 * list of objects and the header.
 *
 * @author Vishal Siramshetty <srmshtty[at]gmail.com>
 */
public class TSVWriter {

    /**
     * Header string
     */
    private String header;
    /**
     * List of objects
     */
    private List<String> objects;

    /**
     * Constructor
     *
     * @param header
     * @param objects
     */
    public TSVWriter(String header, List<String> objects) {
        this.header = header;
        this.objects = objects;
    }

    /**
     * Returns the TSV or CSV string for the query result.
     *
     * @return
     */
    public String getTSVString() {
        StringBuilder tsv = new StringBuilder();
        tsv.append(header).append("\n");
        for (String obj : objects) {
            tsv.append(obj).append("\n");
        }

        String tsvString = tsv.toString().substring(0, tsv.length() - 1);

        return tsvString;
    }

}
