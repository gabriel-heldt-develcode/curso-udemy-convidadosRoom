package com.example.convidados.constantes;

public class ConstantesDatabase {

    private ConstantesDatabase(){}

    /**
     * Tabela disponível no banco de dados com suas colunas
     */
    public static class Convidados {
        public static final String TABELA_NOME = "Convidados";

        public static class COLUNAS {
            public static final String ID = "id";
            public static final String NOME = "nome";
            public static final String PRESENCA = "presenca";
        }
    }
}
