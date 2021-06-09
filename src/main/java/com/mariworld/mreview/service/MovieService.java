package com.mariworld.mreview.service;

import com.mariworld.mreview.dto.MovieDTO;
import com.mariworld.mreview.dto.MovieImageDTO;
import com.mariworld.mreview.dto.PageRequestDTO;
import com.mariworld.mreview.dto.PageResultDTO;
import com.mariworld.mreview.entity.Movie;
import com.mariworld.mreview.entity.MovieImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {

    Long register(MovieDTO movieDTO);
    PageResultDTO<MovieDTO,Object[]> getList(PageRequestDTO pageRequestDTO);
    MovieDTO getMovie(Long mno);

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

    default MovieDTO entitiesToDTO(Movie movie, List<MovieImage> movieImages,  Long reviewCnt ,Double avg){
        MovieDTO movieDTO = MovieDTO.builder().mno(movie.getMno()).title(movie.getTitle())
                .avg(avg).reviewCnt(reviewCnt.intValue()).modDate(movie.getModDate())
                .regDate(movie.getRegDate())

                .imageDTOList(movieImages.stream().map(img -> {

                    MovieImageDTO dto = MovieImageDTO.builder()
                            .imgName(img.getImgName())
                            .uuid(img.getUuid())
                            .path(img.getPath()).build();
                    return dto;

                }).collect(Collectors.toList()))
                .build();
        return movieDTO;
    }
}
