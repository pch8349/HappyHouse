<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form @submit="onSubmit" @reset="onReset">
          <b-form-group
            id="id-group"
            label="아이디:"
            label-for="id"
            description="아이디를 입력하세요."
          >
            <b-form-input
              ref="id"
              id="id"
              v-model="member.id"
              type="text"
              required
              placeholder="아이디 입력..."
            ></b-form-input>
          </b-form-group>

          <b-form-group
            id="password-group"
            label="비밀번호:"
            label-for="password"
            description="비밀번호를 입력하세요."
          >
            <b-form-input
              ref="password"
              id="password"
              v-model="member.password"
              type="password"
              required
              placeholder="비밀번호 입력..."
            ></b-form-input>
          </b-form-group>

          <b-form-group
            id="re_password-group"
            label="비밀번호 재확인:"
            label-for="re_password"
            description="비밀번호 재확인을 입력하세요."
          >
            <b-form-input
              ref="re_password"
              id="re_password"
              v-model="member.re_password"
              type="password"
              required
              placeholder="비밀번호 재확인 입력..."
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

          <b-button type="submit" variant="primary" class="m-1"
            >회원가입</b-button
          >
          <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { signUp, idCheck } from "@/api/user";

export default {
  name: "UserRegister",
  data() {
    return {
      member: {
        id: "",
        password: "",
        re_password: "",
        name: "",
        email: "",
      },
    };
  },
  methods: {
    async onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      let check = false;
      !this.member.id &&
        ((msg = "아이디를 입력해주세요"), (err = false), this.$refs.id.focus());
      err &&
        !this.member.password &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.password.focus());
      err &&
        !this.member.re_password &&
        ((msg = "비밀번호 확인을 입력해주세요"),
        (err = false),
        this.$refs.re_password.focus());
      err &&
        !this.member.name &&
        ((msg = "이름 입력해주세요"), (err = false), this.$refs.name.focus());
      err &&
        !this.member.email &&
        ((msg = "이메일을 입력해주세요"),
        (err = false),
        this.$refs.email.focus());

      await idCheck(this.member.id, (response) => {
        if (!response.data && response.data != "success") check = true;
        else {
          check = false;
          console.log(this.$refs.id);
          msg = "중복된 아이디입니다";
          this.$refs.id.focus();
          err = false;
        }
      });
      if (this.member.password != this.member.re_password) {
        msg = "비밀번호 재입력을 확인해주세요";
        err = false;
        this.$refs.re_password.focus();
      }
      console.log(check);
      if (err && check) this.registUser();
      else alert(msg);
    },
    onReset(event) {
      event.preventDefault();
      this.member.id = "";
      this.member.password = "";
      this.member.re_password = "";
      this.member.name = "";
      this.member.email = "";
      this.$router.push({ name: "home" });
    },
    registUser() {
      signUp(
        {
          id: this.member.id,
          password: this.member.password,
          name: this.member.name,
          email: this.member.email,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveList() {
      this.$router.push({ name: "signin" });
    },
  },
};
</script>

<style></style>
