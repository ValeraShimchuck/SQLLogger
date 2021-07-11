package com.sqltest;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends JavaPlugin {
    public String url;
    public String user;
    public String password;
    @Override
    public void onEnable() {
        File config = new File(getDataFolder(),"config.yml");
        if(!config.exists()){
            getLogger().info("creating cfg file & stop plugin");
            getLogger().severe("Please check new config file & rerun server");
            getConfig().options().copyDefaults(true);
            Bukkit.getPluginManager().disablePlugin(this);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            getLogger().info("SQL connection successful!");
            try {
                url = this.getConfig().getString("url");
                user = this.getConfig().getString("user");
                password = this.getConfig().getString("password");
                Connection connection = DriverManager.getConnection(url,user,password);
                Statement s = connection.createStatement();
                s.executeUpdate("CREATE TABLE IF NOT EXISTS player_logdata (time BIGINT, name TEXT, action TEXT, info TEXT);");
                s.close();
                connection.close();
                getLogger().info("SQL connection to store DB successful");
                Bukkit.getPluginManager().registerEvents(new Handler(this),this);

            } catch (SQLException throwables) {
                getLogger().info("SQL don`t connected to DB");
                getLogger().info(String.valueOf(throwables));
                Bukkit.getPluginManager().disablePlugin(this);
            }
        } catch (ClassNotFoundException e) {
            getLogger().info("SQL not found!");
            Bukkit.getPluginManager().disablePlugin(this);
        } catch (InstantiationException e) {
            getLogger().info("InstantiationExcept");
            Bukkit.getPluginManager().disablePlugin(this);
        } catch (IllegalAccessException e) {
            getLogger().info("IllegalAccessException");
            Bukkit.getPluginManager().disablePlugin(this);
        }



    }
    public void saveData(Long time, String playerName, String action, String info){
        try {
            Connection c = DriverManager.getConnection(url,user,password);
            Statement s = c.createStatement();
            s.executeUpdate(String.format("INSERT INTO player_logdata (time, name, action,info) VALUES(%d, '%s','%s','%s');",time,playerName,action,info));

            s.close();
            c.close();
        } catch (SQLException throwables) {
            getLogger().severe("SQL error, can`t connect to DB!");
        }
    }
}
