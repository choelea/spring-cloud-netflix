package com.joe.bookmark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joe.bookmark.entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Bookmark getByUserIdAndId(String userId, Long id);

    List<Bookmark> findByUserId(String userId);
}
