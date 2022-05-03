package Formatos;

import java.io.File;

public class FormatoDocumento {

    public String titulo,categoria;
    public File documento;

    public FormatoDocumento(String titulo, String categoria, File documento) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.documento = documento;
    }

}//FormatoDocumento
