package com.mariworld.mreview.service;

import com.mariworld.mreview.dto.MovieDTO;
import com.mariworld.mreview.dto.MovieImageDTO;
import com.mariworld.mreview.entity.Movie;
import com.mariworld.mreview.entity.MovieImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MovieService {

    Long register(MovieDTO movieDTO);

    default Map<String, Object> dtoToEntity(MovieDTO movieDTO){
        Map<String,Object> entityMap = new HashMap<>();
        Movie movie = Movie.builder()
                .title(movieDTO.getTitle())
                .mno(movieDTO.getMno())
                .build();
        entityMap.put("movie",movie);
        List<MovieImageDTO> movieImageList = movieDTO.getImageDTOList();


        if(movieImageList!=null && movieImageList.size()>0){

            List<MovieImage> movieImages = new ArrayList<>();
            movieImageList.stream().forEach(movieImage ->
            {MovieImage image = MovieImage.builder()
                    .imgName(movieImage.getImgName())
                    .path(movieImage.getPath())
                    .uuid(movieImage.getUuid())
                    .movie(movie)
                    .build();
                movieImages.add(image);
            });
            entityMap.put("imgList" , movieImages);
        }

        return entityMap;

    };
}
