<template>
  <div class="lecture-form">
    <h2>강의 등록</h2>
    <form @submit.prevent="submitLecture">
      <div class="form-group">
        <label for="lectureName">강의명</label>
        <input id="lectureName" v-model="lectureName" placeholder="예: 자바스크립트 기초" />
      </div>

      <div class="form-group">
        <label for="credit">학점</label>
        <input id="credit" v-model.number="credit" type="number" placeholder="예: 3" />
      </div>

      <div class="form-group">
        <label for="limitCount">수강 정원</label>
        <input id="limitCount" v-model.number="limitCount" type="number" placeholder="예: 40" />
      </div>

      <div class="form-group">
        <label for="scheduleInput">강의 시간</label>
        <input id="scheduleInput" v-model="scheduleInput" placeholder="예: 수 1-3, 목 4-6" />
      </div>

      <div class="form-group">
        <label for="lectureType">강의 유형</label>
        <select id="lectureType" v-model="lectureType">
          <option value="MAJOR">전공</option>
          <option value="GENERAL">교양</option>
        </select>
      </div>

      <button type="submit">강의 등록</button>
    </form>
  </div>
</template>

<script>
import api from '@/api/axios';

export default {
  data() {
    return {
      lectureName: '',
      credit: 0,
      limitCount: 0,
      scheduleInput: '',
      lectureType: 'MAJOR',
    };
  },
  methods: {
    async submitLecture() {
      const memberId = localStorage.getItem('memberId');
      const payload = {
        professorId: Number(memberId),
        lectureName: this.lectureName,
        credit: this.credit,
        limitCount: this.limitCount,
        schedule: this.scheduleInput.split(',').map(str => str.trim()),
        lectureType: this.lectureType,
      };

      try {
        const res = await api.post('/lectures', payload);
        alert('강의 등록 성공');
        this.$emit('lecture-added', res.data);
        this.$router.push({ name: 'lecture-manage' });
      } catch (err) {
        alert(err.response?.data?.message || '등록 실패');
      }
    },
  },
};
</script>

<style scoped>
.lecture-form {
  max-width: 500px;
  margin: 2rem auto;
  background: #fafafa;
  padding: 1.5rem;
  border-radius: 8px;
  box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 1rem;
}

label {
  display: block;
  font-weight: bold;
  margin-bottom: 0.3rem;
}

input,
select {
  width: 100%;
  padding: 0.5rem;
  font-size: 1rem;
  box-sizing: border-box;
}

button {
  margin-top: 1rem;
  padding: 0.6rem 1.2rem;
  font-size: 1rem;
  background-color: #3f51b5;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #2c3e9f;
}
</style>
