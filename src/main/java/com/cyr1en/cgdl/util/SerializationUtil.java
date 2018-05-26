package com.cyr1en.cgdl.util;

import com.sun.org.apache.bcel.internal.generic.ObjectType;

import java.io.*;

public class SerializationUtil {
    private static void serialize(ObjectType obj, String filePath) {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outputStream.writeObject(obj);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static ObjectType loadSerialized(String filePath) throws ClassNotFoundException, IOException {
        FileInputStream fileIn = new FileInputStream(filePath);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        return (ObjectType) in.readObject();
    }
}
