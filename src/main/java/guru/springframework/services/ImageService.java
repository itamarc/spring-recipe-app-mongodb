package guru.springframework.services;

import org.springframework.web.multipart.MultipartFile;

/*
 * Created by itamar at 2022-02-02
 */
public interface ImageService {
    void saveImageFile(Long recipeId, MultipartFile file);
}
