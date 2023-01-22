package com.example.mybookstor.config.cloudinary;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter

@RequiredArgsConstructor
public class CloudinaryConfig {
    @Value("${CLOUDINARY_URL}")
    private   String cloudinaryUrl;

    @Value("${USER_FOLDER_NAME}")
    private   String UserFolderName;

    @Value("${DEFAULT_AVATAR}")
    private  String userAvatarImagePath;
    private  String bookAvartarImage;

    @Value("${SECRETE_KEY}")
    private  String secreteKey;
    @Value("${BOOK_FOLDER_NAME}")
    private  String BookFolderName;
}
