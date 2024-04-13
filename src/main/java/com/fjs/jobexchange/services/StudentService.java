package com.fjs.jobexchange.services;


import com.fjs.jobexchange.dtos.ApiException;
import com.fjs.jobexchange.models.Employer;
import com.fjs.jobexchange.models.Student;
import com.fjs.jobexchange.repositories.EmployerRepository;
import com.fjs.jobexchange.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student findById(Integer id){
        return studentRepository.findById(id)
                .orElseThrow(() -> new ApiException("Student không tồn tại!", HttpStatus.BAD_REQUEST));
    }

}
