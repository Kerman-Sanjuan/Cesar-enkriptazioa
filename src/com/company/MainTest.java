package com.company;

import junit.framework.TestCase;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest extends TestCase {
    HashMap<Integer, Character> ordezkatzeHiztegia = null;

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {
    }

    public void testZifratu() {
        assertEquals("BP MZTVB MBWWZWBJ ZGVB",Main.kodetu("ZXCVBNMASDFGHJKLQWERTYUIOP","EZ GAUDE GERRAREN ALDE"));
    }

    public void testDeszifratu() {

        assertEquals("EZ GAUDE GERRAREN ALDE", Main.dekodetu(Main.kodetu("ZXCVBNMASDFGHJKLQWERTYUIOP","EZ GAUDE GERRAREN ALDE"),"ZXCVBNMASDFGHJKLQWERTYUIOP"));
    }
}