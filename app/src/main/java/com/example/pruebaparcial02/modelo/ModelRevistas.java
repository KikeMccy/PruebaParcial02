package com.example.pruebaparcial02.modelo;

import java.io.Serializable;

public class ModelRevistas implements Serializable {

    private String journal_id;
    private String portada;
    private  String name;
    //private JournalThumbnail [] objects;
    private String description;

    public ModelRevistas(String journal_id, String portada, String name, String description) {
        this.journal_id = journal_id;
        this.portada = portada;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getJournal_id() {
        return journal_id;
    }

    public void setJournal_id(String journal_id) {
        this.journal_id = journal_id;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }


    /*public class JournalThumbnail{
        private String name;

        public JournalThumbnail(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }*/
}
