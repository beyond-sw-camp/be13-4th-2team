<template>
  <div class="lecture-container">
    <h2>내가 등록한 강의 목록</h2>

    <div v-if="lectures.length > 0" class="lecture-list">
      <div v-for="lecture in lectures" :key="lecture.lectureId" class="lecture-card">
        <h3>{{ lecture.name }}</h3>
        <p><strong>시간:</strong> {{ lecture.schedule }}</p>
        <p><strong>학점:</strong> {{ lecture.credit }} | <strong>정원:</strong> {{ lecture.limitCount }}명</p>
        <div class="button-group">
          <button class="edit-btn" @click="editLecture(lecture)">수정</button>
          <button class="delete-btn" @click="deleteLecture(lecture.lectureId)">삭제</button>
        </div>
      </div>
    </div>

    <p v-else class="no-lecture-msg">등록된 강의가 없습니다.</p>

    <!-- 수정 폼 -->
    <div v-if="editingLecture" class="edit-form">
      <h3>강의 수정</h3>
      <form @submit.prevent="submitEdit">
        <label>강의명</label>
        <input v-model="editingLecture.name" placeholder="강의명" />

        <label>학점</label>
        <input v-model.number="editingLecture.credit" type="number" placeholder="학점" />

        <label>정원</label>
        <input v-model.number="editingLecture.limitCount" type="number" placeholder="정원" />

        <label>강의 시간</label>
        <input v-model="editingLecture.schedule" placeholder="예: 수 1-3, 목 2-4" />

        <label>강의 유형</label>
        <select v-model="editingLecture.lectureType">
          <option value="MAJOR">전공</option>
          <option value="GENERAL">교양</option>
        </select>

        <button type="submit" class="save-btn">저장</button>
      </form>
    </div>
  </div>
</template>

<script>
import api from '@/api/axios';

export default {
  data() {
    return {
      lectures: [],
      editingLecture: null,
    };
  },
  mounted() {
    this.fetchMyLectures();
  },
  methods: {
    async fetchMyLectures() {
      try {
        const memberId = localStorage.getItem('memberId');
        if (!memberId) {
          alert('memberId가 설정되어 있지 않습니다.');
          return;
        }

        const res = await api.get('/lectures/myLectures', {
          params: { memberId },
        });
        this.lectures = res.data;
      } catch (err) {
        console.error('강의 목록 조회 실패:', err);
        alert('강의 목록을 불러오지 못했습니다.');
      }
    },

    editLecture(lecture) {
      this.editingLecture = { ...lecture };
    },

    async submitEdit() {
      try {
        const memberId = localStorage.getItem('memberId');
        const payload = {
          professorId: Number(memberId),
          lectureId: this.editingLecture.lectureId,
          lectureName: this.editingLecture.name,
          credit: this.editingLecture.credit,
          limitCount: this.editingLecture.limitCount,
          schedule: this.editingLecture.schedule.split(','),
          lectureType: this.editingLecture.lectureType,
        };
        console.log('수정할 강의 정보:', payload);
        await api.post('/lectures/update', payload);
        alert('강의가 수정되었습니다.');
        this.fetchMyLectures();
        this.editingLecture = null;
      } catch (err) {
        console.error('강의 수정 실패:', err);
        alert(err.response?.data?.message || '수정 실패');
      }
    },

    async deleteLecture(lectureId) {

      const confirmDelete = confirm("정말로 이 강의를 삭제하시겠습니까?");
      if (!confirmDelete) return;


      try {
        const memberId = localStorage.getItem('memberId');
        await api.delete(`/lectures/${lectureId}`, {
          params: { memberId },
        });
        this.lectures = this.lectures.filter(l => l.lectureId !== lectureId);
        alert('강의가 삭제되었습니다.');
      } catch (err) {
        console.error('강의 삭제 실패:', err);
        alert(err.response?.data?.message || '삭제 실패');
      }
    },
  },
};
</script>

<style scoped>
.lecture-container {
  max-width: 850px;
  margin: 2rem auto;
  padding: 2rem;
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.06);
  font-family: 'Segoe UI', sans-serif;
  color: #222;
}

.lecture-container h2 {
  margin-bottom: 1.5rem;
  color: #1e88e5;
  font-size: 1.8rem;
}

.lecture-list {
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
}

.lecture-card {
  background-color: #f0f2f5;
  border-left: 5px solid #1e88e5;
  border-radius: 8px;
  padding: 1rem 1.5rem;
  transition: background-color 0.2s;
}

.lecture-card:hover {
  background-color: #e3eaf4;
}

.lecture-card h3 {
  margin: 0 0 0.5rem;
  font-size: 1.2rem;
  color: #1e88e5;
}

.lecture-card p {
  margin: 0.2rem 0;
  font-size: 0.95rem;
}

.button-group {
  margin-top: 0.8rem;
}

.edit-btn,
.delete-btn,
.save-btn {
  padding: 0.45rem 1rem;
  margin-right: 0.5rem;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-weight: 500;
  font-size: 0.95rem;
}

.edit-btn {
  background-color: #1e88e5;
  color: white;
}

.edit-btn:hover {
  background-color: #1565c0;
}

.delete-btn {
  background-color: #e53935;
  color: white;
}

.delete-btn:hover {
  background-color: #c62828;
}

.save-btn {
  background-color: #43a047;
  color: white;
  margin-top: 1rem;
}

.save-btn:hover {
  background-color: #2e7d32;
}

.edit-form {
  margin-top: 2.5rem;
  background-color: #fafafa;
  border: 1px solid #ddd;
  padding: 1.5rem;
  border-radius: 8px;
}

.edit-form h3 {
  color: #1e88e5;
  margin-bottom: 1rem;
}

.edit-form label {
  display: block;
  margin: 0.6rem 0 0.3rem;
  font-weight: 600;
  font-size: 0.95rem;
}

.edit-form input,
.edit-form select {
  width: 100%;
  padding: 0.5rem;
  margin-bottom: 0.8rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 0.95rem;
  box-sizing: border-box;
}

.no-lecture-msg {
  color: #555;
  font-style: italic;
  margin-top: 1rem;
  text-align: center;
}
</style>
