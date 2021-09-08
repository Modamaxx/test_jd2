package service;

import storage.DBStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CalculationsService {
    private static final CalculationsService instance = new CalculationsService();
    private final DBStorage dbStorage;

    public CalculationsService() {
        dbStorage = DBStorage.getInstance();
    }

    private final String PATH = "D:\\Java\\courses\\DZ\\Home\\HomeWork6\\src\\main\\resources";

    public int[] Department() throws IOException {
        int [] arr=new int[5];
        Path path = Paths.get(PATH + "/Departments.txt");
        Scanner scanner = new Scanner(path);
        for (int i=0;i<5;i++){
         arr[i]=dbStorage.getIdName(scanner.next(),'D');
         scanner.next();
        }
      return  arr;
    }
    public int[] Position() throws IOException {
        int [] arr=new int[10];
        Path path = Paths.get(PATH + "/Position.txt");
        Scanner scanner = new Scanner(path);
        for (int i=0;i<10;i++){
            arr[i]=dbStorage.getIdName(scanner.next(),'C');
        }
        return  arr;
    }

    public static CalculationsService getInstance() {
        return instance;
    }
}
