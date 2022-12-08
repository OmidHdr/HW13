package org.example;

import org.example.exception.InvalidException;
import org.example.exception.NullpointerExeption;
import org.example.panels.Run;
import org.example.validation.Validation;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Main {

    public final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws NullpointerExeption, DataFormatException, SQLException, InvalidException, Invalid {
        Run.basePanel();
        Run.start();
    }
}