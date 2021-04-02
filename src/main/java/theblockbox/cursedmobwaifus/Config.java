package theblockbox.cursedmobwaifus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class Config {
    public static Config instance = new Config();
    public int percentChanceOfWaifu = 10;

    public static final File CONFIG_FILE = new File("config", CMW.MODID + ".json");

    public static void init() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            if (CONFIG_FILE.exists() && CONFIG_FILE.isFile()) {
                Reader reader = new FileReader(CONFIG_FILE);
                Config.instance = gson.fromJson(reader, Config.class);
                reader.close();
                return;
            }
            Writer writer = new FileWriter(CONFIG_FILE);
            gson.toJson(Config.instance, writer);
            writer.close();
        } catch (Exception e) {
            System.err.println(CMW.MODID + ": Could not load config.");
        }
    }
}
