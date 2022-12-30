package com.example.mybookstor.config.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.example.mybookstor.entities.Book;
import com.example.mybookstor.entities.UserEntity;
import com.example.mybookstor.execptions.UserExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
@RequiredArgsConstructor

public class CloudinaryUtil {
    private final CloudinaryConfig config;



    private Map setImageParameterForUser(UserEntity user) {
        return ObjectUtils.asMap
                (

                        "use_filename", false,
                        "public_id", config.getUserFolderName()+ "/" + user.getUserId(),
                        "unique_filename", false,
                        "overwrite", true

                );
    }

    private Map setImageParameterForBook(Book book) {
        return ObjectUtils.asMap
                (

                        "use_filename", false,
                        "public_id", config.getBookFolderName() + "/" + book.getIsbn(),
                        "unique_filename", false,
                        "overwrite", true

                );
    }


    private boolean imageFileCheck(MultipartFile imagePathDirectory) {
        if (Objects.requireNonNull(imagePathDirectory.getContentType()).contains("image") &&
                imagePathDirectory.getSize() <= 1_000_000)
            return true;

        throw new UserExistException("An image type file expected or file size is too large");

    }


    private String convertFileToString(MultipartFile multipartFile) throws IOException {
        imageFileCheck(multipartFile);

        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        FileOutputStream fos = new FileOutputStream(convFile);

        fos.write(multipartFile.getBytes());

        fos.close();

        return convFile.getPath();
    }


    private String uploadAndTransformImage(UserEntity user, String filePath) {
        String imageUrl = new String();
        Cloudinary cloudinary = new Cloudinary(config.getCloudinaryUrl());

        Map params = setImageParameterForUser(user);
        try {

            imageUrl = cloudinary.uploader().upload(filePath, params).get(config.getSecreteKey()).toString();

            cloudinary.url().transformation(new Transformation()
                            .crop("pad")
                            .width(300)
                            .height(400)
                            .background("auto:predominant"))
                    .imageTag(user.getUserId().toString());
        } catch (Exception e) {
            e.getMessage();
        }


        return imageUrl;
    }


    public String defaultImageUpload(UserEntity user) {


        return uploadAndTransformImage(user,config.getAvatarImagePath());
    }


    public String createOrUpdateImage(MultipartFile file, UserEntity user) throws IOException {

        String filePath = convertFileToString(file);

        if (filePath.equals(""))
            return defaultImageUpload(user);

        return uploadAndTransformImage(user, filePath);
    }


    public String deleteImage(UserEntity user) throws IOException {
        Map params1 = setImageParameterForUser(user);
        Cloudinary cloudinary = new Cloudinary(config.getCloudinaryUrl());

        cloudinary.uploader().destroy(user.getUserId().toString(), params1);

        return "Image Successfully deleted";
    }
}
