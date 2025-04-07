package com.bucams.bucams.lecture.domain.dto;

import com.bucams.bucams.lecture.domain.entity.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureCreateRequestDto {

	// 교수님이랑 강의 정보 넘기기
	private Long professorId;
	private String lectureName; // 강의 제목
	private String[] schedule; // 강의 시간
	private int limitCount;
	private int credit;
	private Type lectureType;
}
