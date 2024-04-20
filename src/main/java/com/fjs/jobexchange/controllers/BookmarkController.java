package com.fjs.jobexchange.controllers;

import com.fjs.jobexchange.models.Bookmark;
import com.fjs.jobexchange.services.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @PreAuthorize("hasRole('student')")
    @PostMapping("/create/{id}")
    public ResponseEntity<?> create(@PathVariable Integer id) {
        Bookmark bookmark = bookmarkService.createBookmark(id);
        return new ResponseEntity<>(bookmark, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('student')")
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        bookmarkService.deleteBookmark(id);
        return new ResponseEntity<>("Đã hũy bookmark", HttpStatus.OK);
    }
    @PreAuthorize("hasRole('student')")
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findByStudentIdAndJobId(@PathVariable Integer id) {
       Bookmark bookmark=  bookmarkService.findByStudentIdAndJobId(id);
        return new ResponseEntity<>(bookmark, HttpStatus.OK);
    }
}
