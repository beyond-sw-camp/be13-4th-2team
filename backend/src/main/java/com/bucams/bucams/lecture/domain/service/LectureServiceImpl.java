package com.bucams.bucams.lecture.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.bucams.bucams.registration.RegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bucams.bucams.lecture.domain.dto.LectureCreateRequestDto;
import com.bucams.bucams.lecture.domain.dto.RegisterLectureResponseDto;
import com.bucams.bucams.lecture.domain.entity.Lecture;
import com.bucams.bucams.lecture.domain.dto.LectureUpdateRequestDto;
import com.bucams.bucams.lecture.domain.dto.LectureResponseDto;
import com.bucams.bucams.lecture.domain.entity.SearchOption;
import com.bucams.bucams.lecture.domain.repository.LectureRepository;
import com.bucams.bucams.member.MemberRepository;
import com.bucams.bucams.member.domain.Member;
import com.bucams.bucams.member.domain.Role;
import com.bucams.bucams.registration.domain.Registration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

	private final LectureRepository lectureRepository;
	private final MemberRepository memberRepository;
	private final RegistrationRepository registrationRepository;

	@Override
	@Transactional
	public LectureResponseDto createLecture(LectureCreateRequestDto lectureRequestDto) {


		Long memberId = lectureRequestDto.getProfessorId();
		// 1. member가 존재하는지 검증
		Member member = memberRepository.findById(memberId).orElseThrow(
			() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다.")
		);

		// 2. member가 교수님인지 검증
		if(member.getRole() != Role.PROFESSOR){
			throw new IllegalArgumentException("해당 사용자는 교수님이 아닙니다.");
		}

		// DTO -> entity 로 변환
		Lecture lecture = Lecture.builder()
			.name(lectureRequestDto.getLectureName())
			.schedule(String.join( "," , lectureRequestDto.getSchedule()))
			.limitCount(lectureRequestDto.getLimitCount())
			.credit(lectureRequestDto.getCredit())
			.type(lectureRequestDto.getLectureType())
			.member(member)
			.build();

		// repository 에 entity 저장
		lectureRepository.save(lecture);

		// entity -> responseDto 로 변환 후 반환
		return new LectureResponseDto(lecture);

	}

	@Override
	@Transactional
	public LectureResponseDto updateLecture(LectureUpdateRequestDto lectureRequestDto) {



		// 1. user가 존재하는지 검증
		Long memberId = lectureRequestDto.getProfessorId();
		Member member = memberRepository.findById(memberId).orElseThrow(
			() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다.")
		);

		// 2. member가 교수님인지 검증
		if(member.getRole() != Role.PROFESSOR){
			throw new IllegalArgumentException("해당 사용자는 교수님이 아닙니다.");
		}


		Long lectureId = lectureRequestDto.getLectureId();
		// 3. lecture가 존재하는지 검증
		Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(
			() -> new IllegalArgumentException("해당 강의가 존재하지 않습니다.")
		);


		// 4. lecture가 user가 개설한 강의가 먼지 검증
		lectureRepository.findByIdAndMemberId(lectureId, memberId).orElseThrow(
			() -> new IllegalArgumentException("해당 강의는 사용자가 개설한 강의가 아닙니다.")
		);


		// 5. lecture 수정
		lecture.update(lectureRequestDto);

		return new LectureResponseDto(lecture);
	}



	@Override
	@Transactional
	public void deleteLecture(Long memberId, Long lectureId){

		// 1. lecture 존재하는지 검증
		Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(
			() -> new IllegalArgumentException("해당 강의가 존재하지 않습니다.")
		);

		// 2. member 존재하는지 검증
		Member member = memberRepository.findById(memberId).orElseThrow(
			() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다.")
		);

		// 3. member가 교수님인지 검증
		if(member.getRole() != Role.PROFESSOR){
			throw new IllegalArgumentException("해당 사용자는 교수님이 아닙니다.");
		}


		// 4. 해당 lecture 가 member 가 개설한 강의인지 검증
		lectureRepository.findByIdAndMemberId(lectureId, memberId).orElseThrow(
			() -> new IllegalArgumentException("해당 강의는 사용자가 개설한 강의가 아닙니다.")
		);


		// 5. lecture 삭제
		lectureRepository.deleteById(lectureId);
	}

	// 교수님이 본인이 개설한 모든 강의 조회
	@Override
	public List<LectureResponseDto> getMyAllLectures(Long memberId) {

		// 1. member 존재하는지 검증
		Member member = memberRepository.findById(memberId).orElseThrow(
			() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다.")
		);


		// 2. 해당 member가  교수가 맞는지 검증
		if(member.getRole() != Role.PROFESSOR){
			throw new IllegalArgumentException("해당 사용자는 교수님이 아닙니다.");
		}

		// 3. member가 개설한 강의 조회
		List<Lecture> allLecture = lectureRepository.findAllByMemberId(memberId);

		if(allLecture.isEmpty()){
			throw new IllegalArgumentException("개설한 강의가 없습니다.");
		};

		return allLecture.stream()
			.map(LectureResponseDto::new)
			.collect(Collectors.toList());
	}

	// 강의 단건 조회
	@Override
	public LectureResponseDto getLecture(Long memberId, Long lectureId) {

		// 1. 로그인한 유저만 조회 가능
		memberRepository.findById(memberId).orElseThrow(
			() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다.")
		);

		// 2. lecture가 존재하는지 검증
		Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(
			() -> new IllegalArgumentException("해당 강의가 존재하지 않습니다.")
		);


		// 3. lecture -> responseDto 변환
		return new LectureResponseDto(lecture);
	}


	// 강의 전체 조회, 검색
	@Override
	public List<LectureResponseDto> getAllLectures(String keyword, SearchOption searchOption) {

		// 검색어가 있는데 검색 옵션을 선택하지 않은 경우 검색이 안됨
		if (keyword != null && searchOption == null) {
			throw new IllegalArgumentException("검색 옵션을 선택해 주십시오.");
		}

		List<LectureResponseDto> searchAllLectures = lectureRepository.searchAllLectures(keyword, searchOption);


		if(searchAllLectures.isEmpty()){
			throw new IllegalArgumentException("강의가 없습니다.");
		}

		return searchAllLectures;
	}

	@Override
	@Transactional
	public RegisterLectureResponseDto registerLecture(Long studentId, Long lectureId) {

		// 1. 학생이 존재하는지 검증
		Member student = memberRepository.findById(studentId).orElseThrow(
			() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다.")
		);

		// 2. 학생인지 검증
		if(student.getRole() != Role.STUDENT){
			throw new IllegalArgumentException("해당 사용자는 학생이 아닙니다.");
		}

		// 3. 강의가 존재하는지 검증
		Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(
			() -> new IllegalArgumentException("해당 강의가 존재하지 않습니다.")
		);

		/// 4.  해당 학생 시간표의 시간과 겹치지 않는 것 검증
		// 1. 일단 이 학생이 이 강의를 신청했는지 확인
		// 2. 학생의 시간표에서 겹치지 않는지 확인

		List<Registration> registrationList = registrationRepository.findByMemberId(studentId);
		String[] wantSchedule = lecture.getSchedule().split(","); // 신청하려는 강의 시간표

		for (Registration registration : registrationList) {
			String[] existSchedule = registration.getLecture().getSchedule().split(",");

			for (String exist : existSchedule) {
				for (String want : wantSchedule) {
					if (isScheduleConflict(exist.trim(), want.trim())) {
						throw new IllegalArgumentException("이미 해당 시간에 수강 중인 강의가 있어 수강 신청이 불가능합니다.");
					}
				}
			}
		}

		// 5. 강의의 인원이 full 이 아닌지 검증
		if( lecture.getCurrCount() >= lecture.getLimitCount() ){
			throw new IllegalArgumentException("해당 강의는 수강신청이 마감되었습니다.");
		}


		// 6. 수강 신청 시 21학점 초과하면 신청 불가
		if(student.getCurrentCredits() + lecture.getCredit() > 21){
			throw new IllegalArgumentException("수강신청 학점이 21학점을 초과했습니다.");
		}


		// 7. 수강신청 고고
		// 7-1 ) 해당 강의 현재 수강인원 증가
		lecture.addCurrCount();

		// 7-2 ) 학생의 현재 학점 증가
		student.addCurrentCredits(lecture.getCredit());

		// 7-3 ) 학생의 수강신청 내역에 해당 강의 추가
		registrationRepository.save(Registration.builder()
												.lecture(lecture)
												.member(student)
												.registeredAt(LocalDateTime.now())
												.build());



		return new RegisterLectureResponseDto(lecture, student);
	}


	/**
	 * 시간표 문자열 두 개가 겹치는지 검사
	 * 예: "수 2-5, 목 7-9" 와 "수 4-6, 금 11-1" → true
	 */
	private boolean isScheduleConflict(String s1, String s2) {

		String day1 = s1.substring(0, 1); // 요일
		String[] times1 = s1.substring(2).split("-"); // 시간대1

		int start1 = Integer.parseInt(times1[0]); // 시작 시간
		int end1 = Integer.parseInt(times1[1]); // 끝 시간

		String day2 = s2.substring(0, 1);
		String[] times2 = s2.substring(2).split("-");
		int start2 = Integer.parseInt(times2[0]);
		int end2 = Integer.parseInt(times2[1]);

		if (!day1.equals(day2)) return false; // 요일 다르면 겹치지 않음

		return start1 <= end2 && start2 <= end1; // 시간 겹침 여부
	}


}

