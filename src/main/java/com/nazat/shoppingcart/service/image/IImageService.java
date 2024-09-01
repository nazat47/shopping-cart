package com.nazat.shoppingcart.service.image;

import com.nazat.shoppingcart.dto.ImageDto;
import com.nazat.shoppingcart.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);

    void deleteImageById(Long id);

    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);

    void updateImage(MultipartFile file, Long imageId);
}
