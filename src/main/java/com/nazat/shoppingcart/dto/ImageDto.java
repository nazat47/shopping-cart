package com.nazat.shoppingcart.dto;

import jakarta.persistence.Lob;
import lombok.Data;

import java.sql.Blob;

@Data
public class ImageDto {
    private Long id;
    private String fileName;
    private String downloadUrl;
}
