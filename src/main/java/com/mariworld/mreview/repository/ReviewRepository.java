package com.mariworld.mreview.repository;

import com.mariworld.mreview.entity.Member;
import com.mariworld.mreview.entity.Movie;
import com.mariworld.mreview.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);

    @Modifying
    @Query("delete from Review r where r.member =:member")
    void deleteByMember(@Param("member") Member member);
}
