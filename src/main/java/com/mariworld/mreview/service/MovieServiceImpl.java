package com.mariworld.mreview.service;

import com.mariworld.mreview.dto.MovieDTO;
import com.mariworld.mreview.dto.PageRequestDTO;
import com.mariworld.mreview.dto.PageResultDTO;
import com.mariworld.mreview.entity.Movie;
import com.mariworld.mreview.entity.MovieImage;
import com.mariworld.mreview.repository.MovieImageRepository;
import com.mariworld.mreview.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final MovieImageRepository imageRepository;


    @Override @Transactional
    public Long register(MovieDTO movieDTO) {
        Map<String,Object> entityMap = dtoToEntity(movieDTO);

        Movie movie= (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

        movieRepository.save(movie);
        movieImageList.stream().forEach(movieImage ->
                imageRepository.save(movieImage));

        return movie.getMno();
    }

    @Override
    public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO) {
        PageRequest pageRequest = requestDTO.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getListPage(pageRequest);

        Function<Object[],MovieDTO> fn = (arr ->
            entitiesToDTO((Movie) arr[0], Arrays.asList((MovieImage) arr[1]), (Long) arr[2], (Double) arr[3]));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public MovieDTO getMovie(Long mno) {

        List<Object[]> result = movieRepository.getMovieWithAll(mno);

        Movie movie = (Movie) result.get(0)[0];
        List<MovieImage> imageList = new ArrayList<>();
        result.forEach(arr->{
            MovieImage image = (MovieImage)arr[1];
            imageList.add(image);
        });
        Long reviewCnt = (Long) result.get(0)[2];
        Double avg = (Double) result.get(0)[3];

        return entitiesToDTO(movie,imageList,reviewCnt,avg);
    }
}
