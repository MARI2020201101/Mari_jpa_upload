package com.mariworld.mreview.service;

import com.mariworld.mreview.dto.MovieDTO;
import com.mariworld.mreview.entity.Movie;
import com.mariworld.mreview.entity.MovieImage;
import com.mariworld.mreview.repository.MovieImageRepository;
import com.mariworld.mreview.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final MovieImageRepository imageRepository;


    @Override @Transactional
    public Long register(MovieDTO movieDTO) {
        Map<String,Object> entityMap = new HashMap<>();

        Movie movie= (Movie) dtoToEntity(movieDTO).get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) dtoToEntity(movieDTO).get("imgList");

        movieRepository.save(movie);
        movieImageList.stream().forEach(movieImage ->
                imageRepository.save(movieImage));

        return movie.getMno();
    }
}
