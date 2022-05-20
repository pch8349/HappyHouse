<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>비밀번호 찾기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form @submit="onSubmit">
          <b-form-group
            id="id-group"
            label="아이디:"
            label-for="text"
            description="아이디를 입력하세요."
          >
            <b-form-input
              ref="id"
              id="id"
              v-model="member.id"
              type="id"
              required
              placeholder="아이디 입력..."
            ></b-form-input>
          </b-form-group>

          <b-form-group
            id="name-group"
            label="이름:"
            label-for="name"
            description="이름를 입력하세요."
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
            description="이메일을 입력하세요."
          >
            <b-form-input
              ref="email"
              id="email"
              v-model="member.email"
              type="text"
              required
              placeholder="이메일 입력..."
            ></b-form-input>
          </b-form-group>

          <b-button type="submit" variant="primary" class="m-1">확인</b-button>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { detailUser } from "@/api/user";

export default {
  name: "UserPasswordModify",
  data() {
    return {
      member: {
        id: "",
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
      err &&
        !this.member.id &&
        ((msg = "아이디를 입력해주세요"), (err = false), this.$refs.id.focus());
      err &&
        !this.member.name &&
        ((msg = "이름을 입력해주세요"), (err = false), this.$refs.name.focus());
      err &&
        !this.member.email &&
        ((msg = "이메일을 입력해주세요"),
        (err = false),
        this.$refs.email.focus());

      let mid = this.member.id;
      let mname = this.member.name;
      let memail = this.member.email;

      await detailUser(this.member.id, ({ data }) => {
        if (
          !data ||
          data.id != mid ||
          data.name != mname ||
          data.email != memail
        ) {
          msg = "입력한 정보를 확인해 주세요!";
          err = false;
        } else {
          alert("비밀번호 : " + data.password);
        }
      });
      if (err) {
        this.$router.push({ name: "signIn" });
      } else alert(msg);
    },
  },
};
</script>

<style></style>
