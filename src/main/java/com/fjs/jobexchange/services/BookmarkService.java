package com.fjs.jobexchange.services;

import com.fjs.jobexchange.dtos.ApiException;
import com.fjs.jobexchange.models.*;
import com.fjs.jobexchange.repositories.BookmarkRepository;
import com.fjs.jobexchange.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final StudentService studentService;
    private final JobService jobService;
    private UserRepository userRepository;

    @Transactional
    public Bookmark createBookmark(Integer id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student=studentService.findById(user.getUserId());
        Job job=jobService.get(id);
        BookmarkId bookmarkId = new BookmarkId();
        bookmarkId.setStudentId(student.getId());
        bookmarkId.setJobId(job.getId());
        Bookmark bookmark=Bookmark.builder()
                .id(bookmarkId)
                .studentId(student)
                .job(job).build();
        try {
            bookmark = bookmarkRepository.save(bookmark);
            return bookmark;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException("Có lỗi xảy ra khi tạo CV. Vui lòng thử lại!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public void deleteBookmark(Integer id) {
        Bookmark bookmark = findByStudentIdAndJobId(id);
        bookmarkRepository.delete(bookmark);
    }


    public Bookmark findByStudentIdAndJobId(Integer id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student=studentService.findById(user.getUserId());
        Job job=jobService.get(id);
        return bookmarkRepository.findByStudentIdAndJobId(student,job.getId())
                .orElseThrow(() -> new ApiException("Bạn chưa bookmark bài viết này!.", HttpStatus.NOT_FOUND));
    }
}
