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

import de.fhg.scai.bio.interfaces.Prefix;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vishal Siramshetty <srmshtty[at]gmail.com>
 */
public class Mapping {

    private static final Logger logger = Logger.getLogger("com.lia.core");
    private List<Prefix> prefixes;
    private File mappingFile;

    public Mapping(File mappingFile) {
        this.mappingFile = mappingFile;
    }

    public List<Prefix> getPrefixes() {
        List<Prefix> prefixes = new ArrayList<>();
        String prefix = "@prefix";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(this.mappingFile));
            String line;
            while ((line = reader.readLine()) != null && line.startsWith(prefix)) {
                int index = line.indexOf(':');
                String abb = line.substring(prefix.length(), index);
                String url = line.substring(index + 1, line.length() - 2);
                Prefix pref = new Prefix(abb, url);
                prefixes.add(pref);
            }
            reader.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Mapping file not found.");
            return null;
        }
        return prefixes;
    }

}
