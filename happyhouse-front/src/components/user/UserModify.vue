<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원정보 수정</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form @submit="onSubmit">
          <b-form-group
            id="id-group"
            label="아이디:"
            label-for="id"
            description="아이디를 입력하세요."
          >
            <b-form-input
              ref="id"
              id="id"
              disabled
              v-model="member.id"
              type="text"
              required
              placeholder="아이디 입력..."
            ></b-form-input>
          </b-form-group>

          <b-form-group
            id="name-group"
            label="이름:"
            label-for="name"
            description="이름을 입력하세요."
          >
            <b-form-input
              ref="name"
              id="name"
              v-model="member.name"
              type="text"
              required
              placeholder="이름 입력..."
            ></b-form-input>
          </b-form-group>

          <b-form-group
            id="email-group"
            label="이메일:"
            label-for="email"
            description="이메일 입력하세요."
          >
            <b-form-input
              ref="email"
              id="email"
              v-model="member.email"
              type="email"
              required
              placeholder="이메일 입력..."
            ></b-form-input>
          </b-form-group>

          <b-button type="submit" variant="primary" class="m-1">수정</b-button>
          <b-button
            type="button"
            variant="danger"
            class="m-1"
            @click="moveUserPasswordModify"
            >비밀번호 변경</b-button
          >
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { updateUser } from "@/api/user";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "UserRegister",
  data() {
    return {
      member: {
        name: "",
        email: "",
      },
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    this.member.id = this.userInfo.id;
    this.member.name = this.userInfo.name;
    this.member.email = this.userInfo.email;
  },
  methods: {
    async onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      err &&
        !this.member.name &&
        ((msg = "이름 입력해주세요"), (err = false), this.$refs.name.focus());
      err &&
        !this.member.email &&
        ((msg = "이메일을 입력해주세요"),
        (err = false),
        this.$refs.email.focus());

      if (err) this.modifyUser();
      else alert(msg);
    },
    async modifyUser() {
      let flag = false;
      await updateUser(
        {
          id: this.userInfo.id,
          password: this.userInfo.password,
          name: this.member.name,
          email: this.member.email,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
            flag = true;
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        },
      );
      if (flag) {
        this.userInfo.name = this.member.name;
        this.userInfo.email = this.member.email;
      }
    },
    moveList() {
      this.$router.push({ name: "mypage" });
    },
    moveUserPasswordModify() {
      this.$router.push({ name: "userPasswordModify" });
    },
  },
};
</script>

<style></style>
