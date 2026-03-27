package com.lnw.acceeeeeppt.system;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.lnw.acceeeeeppt.model.PlayerModel;

public class SaveManager {
    private SaveManager() {
        /* This utility class should not be instantiated */
    }

    public static String toHexString(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static PlayerModel createNewPlayerModel(String saveName) {
        return new PlayerModel(saveName);
    }

    /**
     * @param playerModel
     * @return 0 on success, 1 on file exists, 2 on IOException
     */
    public static int saveToDisk(PlayerModel playerModel) {
        String fileName = playerModel.getSaveName();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(fileName.getBytes(StandardCharsets.UTF_8));
            fileName = toHexString(encodedHash) + ".dat";
        } catch (NoSuchAlgorithmException _) {
            fileName += ".dat";
        }
        Path path = Paths.get("saves", fileName);

        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Files.exists(path))
            return 1;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            oos.writeObject(playerModel);
        } catch (IOException e) {
            e.printStackTrace();
            return 2;
        }

        return 0;
    }
}
