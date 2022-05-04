package Formatos;

import java.sql.Blob;

public class FormatoBlob {

    public Blob blobDocumento;
    public String idDocumento;

    public FormatoBlob(Blob blobDocumento, String idDocumento) {
        this.blobDocumento = blobDocumento;
        this.idDocumento = idDocumento;
    }

}//FormatoBlobs
