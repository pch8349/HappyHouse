<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>내정보</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
          <template #header>My Page</template>

          <template #lead> 내 정보 확인페이지입니다. </template>

          <hr class="my-4" />

          <b-container class="mt-4">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.id }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.name }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">가입일</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.joindate
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />

          <b-button
            variant="primary"
            href="#"
            class="mr-1"
            @click="onClickModifyUser"
            >정보수정</b-button
          >
          <b-button variant="danger" href="#" @click.prevent="onClickDeleteUser"
            >회원탈퇴</b-button
          >
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import { deleteUser } from "@/api/user";

const userStore = "userStore";

export default {
  name: "UserMyPage",
  components: {},
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    async onClickDeleteUser() {
      let flag = false;
      if (confirm("정말로 탈퇴하겠습니까?")) {
        await deleteUser(this.userInfo.id, () => {
          flag = true;
        });
      }
      if (flag) {
        this.SET_IS_LOGIN(false);
        this.SET_USER_INFO(null);
        sessionStorage.removeItem("access-token");
        if (this.$route.path != "/") this.$router.push({ name: "home" });
      }
      // console.log("userStore : ", ms);
    },
    onClickModifyUser() {
      this.$router.push({ name: "userModify" });
      // console.log("userStore : ", ms);
    },
  },
};
</script>

<style></style>
