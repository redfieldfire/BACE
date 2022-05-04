package Formatos;

import java.sql.Blob;

public class FormatoBlob {

    public Blob blobDocumento;
    public String idDocumento;

    public byte[] data;

    public FormatoBlob(Blob blobDocumento, String idDocumento, byte[] data) {
        this.blobDocumento = blobDocumento;
        this.idDocumento = idDocumento;
        this.data = data;
    }

}//FormatoBlobs
