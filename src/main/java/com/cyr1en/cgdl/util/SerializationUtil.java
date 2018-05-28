package com.cyr1en.cgdl.util;

import java.io.*;

public class SerializationUtil {
    public static void serialize(Serializable serializable, String filePath) {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outputStream.writeObject(serializable);
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

    public static Object deserialize(String filePath) throws ClassNotFoundException, IOException {
        File file = new File(filePath);
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        return in.readObject();
    }
}
