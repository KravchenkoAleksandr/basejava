package com.webapp;

import com.webapp.model.Resume;
import com.webapp.storage.AbstractArrayStorage;
import com.webapp.storage.SortedArrayStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Interactive test for com.basejava.webapp.storage.ArrayStorage implementation
 * (just run, no need to understand)
 */
public class MainArray {
    static final AbstractArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume r;
        while (true) {
            System.out.print("Введите одну из команд - (list | size | save uuid | delete uuid | get uuid | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 3) {
                System.out.println("Неверная команда.");
                continue;
            }
            String param = null;
            if (params.length > 1) {
                param = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(ARRAY_STORAGE.size());
                    break;
                case "save":
                    r = new Resume(param);
                    ARRAY_STORAGE.save(r);
                    printAll();
                    break;
                case "update":
                    r = new Resume(param, params[2]);
                    ARRAY_STORAGE.update(r);
                    printAll();
                case "delete":
                    ARRAY_STORAGE.delete(param);
                    printAll();
                    break;
                case "get":
                    try {
                        System.out.println(ARRAY_STORAGE.get(param));
                        break;
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                case "clear":
                    ARRAY_STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    static void printAll() {
        List<Resume> all = ARRAY_STORAGE.doCopyAll();
        System.out.println("----------------------------");
        if (all.isEmpty()) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }
        System.out.println("----------------------------");
    }
}
