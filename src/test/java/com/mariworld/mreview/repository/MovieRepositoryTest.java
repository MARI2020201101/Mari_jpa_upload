package com.mariworld.mreview.repository;

import com.mariworld.mreview.entity.Movie;
import com.mariworld.mreview.entity.MovieImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieImageRepository imageRepository;

    @Test
    @Commit
    @Transactional
    public void insertTest(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Movie movie = Movie.builder()
                    .title("Movie...."+i)
                    .build();
            movieRepository.save(movie);

            int rannum=(int) (Math.random()*5)+1;
            for(int j=0 ; j<rannum ; j++){
                MovieImage image = MovieImage.builder()
                        .imgName("test"+j+".jpg")
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .build();
                imageRepository.save(image);
            }
        });
    }

    @Test
    public void getListTest(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("mno").descending());
        Page<Object[]> result = movieRepository.getListPage(pageable);
        List<Object[]> movies = result.getContent();
        for(Object[] movie : movies){
            //Arrays.stream(movie).forEach(System.out::println);
            System.out.println(Arrays.toString(movie));
        }
    }
}
