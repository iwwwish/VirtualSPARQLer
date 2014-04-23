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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
    private List<Property> properties;
    private List<Resource> resources;
    private File mappingFile;

    public Mapping(File mappingFile) {
        this.mappingFile = mappingFile;
    }

    public List<Prefix> getPrefixes() {
        List<Prefix> prefs = new ArrayList<>();
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
                prefs.add(pref);
            }
            reader.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Mapping file not found.");
            return null;
        }
        return prefs;
    }

    public List<Property> getProperties() {
        properties = new ArrayList<>();
        try {

            BufferedReader reader;

            reader = new BufferedReader(new FileReader(this.mappingFile));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("d2rq:property ")) {
                    String prop = line.trim();
                    int index = prop.indexOf(" ");
                    prop = prop.substring(index, prop.length() - 1);
                    Property p = new Property(prop, false);
                    if (!duplicateProperty(p)) {
                        properties.add(p);
                    }

                }
            }
            reader.close();

        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Mapping file not found.");
            return null;
        }

        sortProperties();
        return properties;
    }

    public List<Resource> getResources() {
        resources = new ArrayList<>();
        try {
            BufferedReader reader;

            reader = new BufferedReader(new FileReader(this.mappingFile));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("map:")) {
                    int index = line.indexOf(" ");
                    String name = line.substring(4, index);

                    int type = line.lastIndexOf(" ");
                    String res_type = line.substring(type + 1, line.length() - 1);

                    if (res_type.equals(Resource.TYPE_ClassMap) || res_type.equals(Resource.TYPE_PropertyBridge)) {
                        Resource res = new Resource(name, res_type);
                        if (!duplicateResource(res)) {
                            resources.add(res);
                        }
                    }

                }
            }
            reader.close();

        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Mapping file not found.");
            return null;
        }
        return resources;
    }

    private void sortProperties() {
        List<String> props = new ArrayList();

        for (Property pr : properties) {
            props.add(pr.toString());
        }

        Collections.sort(props);
        properties.clear();

        for (String p : props) {
            properties.add(new Property(p, false));
        }
    }

    private boolean duplicateProperty(Property prop) {

        String p = prop.toString();
        for (Property pr : properties) {
            if (pr.toString().equals(p)) {
                return true;
            }
        }
        return false;
    }

    private boolean duplicateResource(Resource res) {

        String rs = res.toString();
        for (Resource r : resources) {
            if (r.toString().equals(rs)) {
                return true;
            }
        }
        return false;
    }
}
