package guru.springframework.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/*
 * Created by itamar at 2022-02-02
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        // TODO implement code to save file
        log.debug("Received file: " + file.getOriginalFilename());
    }
}
