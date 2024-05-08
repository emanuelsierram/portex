package com.portaexperiencia.infraestructura.storage;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;

@Component
public class AzureStorageService{

    private BlobServiceClient blobServiceClient;

    @Value("${azure.storage.connection-string}")
    private String cadenaConexion;


    public void subirArchivo(String containerNombre, String blobNombre, File imagen) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(cadenaConexion).buildClient();
        BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerNombre).getBlobClient(blobNombre);
        blobClient.uploadFromFile(imagen.getAbsolutePath(), true);
    }


    public String leerArchivo(String containerNombre, String blobNombre) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(cadenaConexion).buildClient();
        BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerNombre).getBlobClient(blobNombre);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        blobClient.download(outputStream);
        return outputStream.toString();
    }
}
