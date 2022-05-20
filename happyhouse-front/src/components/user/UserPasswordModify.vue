<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>비밀번호 변경</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form @submit="onSubmit">
          <b-form-group
            id="password-group"
            label="비밀번호:"
            label-for="password"
            description="기존 비밀번호 입력하세요."
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
            id="newpassword-group"
            label="새 비밀번호:"
            label-for="newpassword"
            description="새 비밀번호를 입력하세요."
          >
            <b-form-input
              ref="newpassword"
              id="newpassword"
              v-model="member.newpassword"
              type="password"
              required
              placeholder="비밀번호 입력..."
            ></b-form-input>
          </b-form-group>

          <b-form-group
            id="repassword-group"
            label="비밀번호 재확인:"
            label-for="repassword"
            description="비밀번호 재확인을 입력하세요."
          >
            <b-form-input
              ref="repassword"
              id="repassword"
              v-model="member.repassword"
              type="password"
              required
              placeholder="비밀번호 재확인 입력..."
            ></b-form-input>
          </b-form-group>

          <b-button type="submit" variant="primary" class="m-1"
            >비밀번호 변경</b-button
          >
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { updateUser, detailUser } from "@/api/user";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "UserPasswordModify",
  data() {
    return {
      member: {
        password: "",
        newpassword: "",
        repassword: "",
      },
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    async onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      let pass = this.member.password;
      let flag = false;
      err &&
        !this.member.password &&
        ((msg = "기존 비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.password.focus());
      err &&
        !this.member.newpassword &&
        ((msg = "새 비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.newpassword.focus());
      err &&
        !this.member.repassword &&
        ((msg = "비밀번호 재확인을 입력해주세요"),
        (err = false),
        this.$refs.repassword.focus());

      await detailUser(this.userInfo.id, ({ data }) => {
        if (data.password == pass) {
          flag = true;
        } else {
          msg = "기존 비밀번호를 확인해 주세요!";
          err = false;
        }
      });
      //   if (this.member.password != this.userInfo.password) {
      //     console.log(this.member.password);
      //     console.log(this.userInfo);
      //     msg = "기존 비밀번호를 확인해 주세요!";
      //     err = false;
      //     this.$refs.password.focus();
      //   }
      if (!flag) this.$refs.password.focus();
      else if (this.member.newpassword != this.member.repassword) {
        msg = "비밀번호 재입력을 확인해 주세요!";
        err = false;
        this.$refs.repassword.focus();
      } else msg = "비밀번호 변경이 완료되었습니다";
      if (err) this.modifyUser();
      else alert(msg);
    },
    async modifyUser() {
      let flag = false;
      await updateUser(
        {
          id: this.userInfo.id,
          password: this.member.newpassword,
          name: this.userInfo.name,
          email: this.userInfo.email,
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
        this.userInfo.password = this.member.newpassword;
      }
    },
    moveList() {
      this.$router.push({ name: "mypage" });
    },
  },
};
</script>

<style></style>
