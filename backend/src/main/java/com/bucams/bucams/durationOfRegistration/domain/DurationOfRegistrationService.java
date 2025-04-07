package com.bucams.bucams.durationOfRegistration.domain;

import com.bucams.bucams.department.domain.DepartmentRepository;
import com.bucams.bucams.durationOfRegistration.domain.dto.RequestDorDto;
import com.bucams.bucams.durationOfRegistration.domain.dto.ResponseDorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DurationOfRegistrationService {

    private final DurationOfRegistrationRepository durationOfRegistrationRepository;

    public ResponseDorDto showDorDto(){
        return durationOfRegistrationRepository.findDorDto();
    }

    public Long newDor(RequestDorDto requestDorDto) {

        DurationOfRegistration dor = requestDorDto.toDor();

        return durationOfRegistrationRepository.createDor(dor);
    }

    public Long deleteDor(Long dorId) {

        DurationOfRegistration durationOfRegistration = durationOfRegistrationRepository.findOne(dorId);

        durationOfRegistrationRepository.deleteDor(durationOfRegistration);

        return dorId;
    }
}
