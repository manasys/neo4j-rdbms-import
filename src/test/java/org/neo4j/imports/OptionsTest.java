/**
 * Copyright (c) 2015 Michael Hunger
 *
 * This file is part of Relational to Neo4j Importer.
 *
 *  Relational to Neo4j Importer is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Relational to Neo4j Importer is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Relational to Neo4j Importer.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.imports;

import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.*;
import static org.neo4j.imports.Options.Rename.*;

public class OptionsTest {
    @Test
    public void testRenameCamelCase() throws Exception {
        assertEquals("Foo",camelcase.transform("foo"));
        assertEquals("FooBar",camelcase.transform("foo_bar"));
        assertEquals("FooBar",camelcase.transform("foo bar"));
        assertEquals("FooBarFoo",camelcase.transform("foo bar__foo"));
    }
    @Test
    public void testRenameLowerCamelCase() throws Exception {
        assertEquals("foo",lowerCamelCase.transform("foo"));
        assertEquals("fooBar",lowerCamelCase.transform("foo_bar"));
        assertEquals("fooBar",lowerCamelCase.transform("foo bar"));
        assertEquals("fooBarFoo", lowerCamelCase.transform("foo bar__foo"));
    }
    @Test
    public void testRenameUpCase() throws Exception {
        assertEquals("FOO",upcase.transform("foo"));
        assertEquals("FOO_BAR",upcase.transform("foo_bar"));
        assertEquals("FOO_BAR",upcase.transform("fooBar"));
        assertEquals("FOO_BAR",upcase.transform("foo bar"));
        assertEquals("FOO_BAR_FOO", upcase.transform("foo bar__foo"));
        assertEquals("FOO_BAR_FOO", upcase.transform("fooBar Foo"));
    }

    @Test
    @Ignore
    public void testSkipPattern() throws Exception {
        Options o = new Options();
        assertTrue(o.setSkipList("table").shouldSkip("table", "field"));
        assertTrue(o.setSkipList("table%").shouldSkip("table25","field"));
        assertTrue(o.setSkipList("table.field").shouldSkip("table","field"));
        assertTrue(o.setSkipList("table,table2").shouldSkip("table","field"));
        assertTrue(o.setSkipList("table,table2").shouldSkip("table2","field"));
        assertTrue(o.setSkipList("table,table2").shouldSkip("table","field"));
        assertTrue(o.setSkipList("table,table2").shouldSkip("table",null));
        assertFalse(o.setSkipList("table.field").shouldSkip("table","field2"));
        assertFalse(o.setSkipList("table.field").shouldSkip("table","field2"));

    }
}
