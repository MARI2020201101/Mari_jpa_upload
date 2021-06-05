package com.mariworld.mreview.repository;

import com.mariworld.mreview.entity.Member;
import com.mariworld.mreview.entity.Movie;
import com.mariworld.mreview.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.print.attribute.IntegerSyntax;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertReviews(){
        IntStream.rangeClosed(1,200).forEach(i->{
            long mno =(long) (Math.random()*100)+1;
            long mid =(long) (Math.random()*100)+1;

            Movie movie = Movie.builder().mno(mno).build();
            Member member = Member.builder().mid(mid).build();

            Review review = Review.builder()
                    .grade((int)(Math.random()*5)+1)
                    .text("이 영화에 대한 느낌...."+i)
                    .member(member)
                    .movie(movie)
                    .build();

            reviewRepository.save(review);
        });
    }
}
