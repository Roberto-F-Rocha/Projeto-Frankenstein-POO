package com.example.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoController {
    private static final String URL = "jdbc:mysql://localhost/poobanco?useUnicode=true&characterEncoding=UTF-8";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}

