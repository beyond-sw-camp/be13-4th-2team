<template>
    <header class="p-3 text-bg-dark">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
          <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
        </a>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="#" class="nav-link px-2 text-secondary">수강 신청</a></li>
          <li><a href="#" class="nav-link px-2 text-white">내 정보</a></li>
          <li>
            <RouterLink :to="{ name: 'departments' }" class="nav-link px-2 text-white">
              학과 전체 조회
            </RouterLink>
          </li>
          <li>
            <RouterLink :to="{ name: 'lecture-create' }" class="nav-link px-2 text-white">
            강의 생성
            </RouterLink>
          </li>
          <li>
            <RouterLink :to="{ name: 'lecture-manage' }" class="nav-link px-2 text-white">
            강의 관리
            </RouterLink>
          </li>
          <li>
            <RouterLink :to="{ name: 'lecture-list' }" class="nav-link px-2 text-white">
            강의 리스트
            </RouterLink>
          </li>
        </ul>

        <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
          <input type="search" class="form-control form-control-dark text-bg-dark" placeholder="Search..." aria-label="Search">
        </form>

        <div class="text-end">
          <template v-if="authStore.isLoggedIn">
            <button @click="handleLogout" class="btn btn-outline-light me-2">Logout</button>
          </template>
          <template v-else>
            <RouterLink :to="{ name: 'login' }" class="btn btn-outline-light me-2">Login</RouterLink>
          </template>
          <button type="button" class="btn btn-warning">Sign-up</button>
        </div>
      </div>
    </div>
    </header>
</template>

<script setup>
  import { useAuthStore } from '../../stores/auth'
  const authStore = useAuthStore();

  const handleLogout = () => {
  authStore.logout()
  }
</script>

<style lang="scss" scoped>

</style>