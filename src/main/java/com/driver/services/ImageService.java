package com.driver.services;

import com.driver.models.*;
import com.driver.models.Image;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        //get the blog to add this image
        Blog blog = blogRepository2.findById(blogId).get();
        image.setBlog(blog);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        String dimensions = image.getDimensions();
        int a = dimensions.charAt(0)-'0';
        int b = dimensions.charAt(2)-'0';
        int A = screenDimensions.charAt(0)-'0';
        int B = screenDimensions.charAt(2)-'0';
        return (A/a)*(B/b);
    }
}
