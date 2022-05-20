<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="id-group"
          label="작성자:"
          label-for="id"
          description="작성자를 입력하세요."
        >
          <b-form-input
            id="id"
            disabled
            v-model="noarticle.id"
            type="text"
            required
            placeholder="작성자 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="title-group"
          label="제목:"
          label-for="title"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="title"
            v-model="noarticle.title"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="noarticle.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { writeNoArticle, getNoArticle, modifyNoArticle } from "@/api/notice";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "NoticeInputItem",
  data() {
    return {
      noarticle: {
        idx: 0,
        id: "",
        title: "",
        content: "",
      },
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      getNoArticle(
        this.$route.params.idx,
        ({ data }) => {
          // this.noarticle.noarticleno = data.noarticle.noarticleno;
          // this.noarticle.id = data.noarticle.id;
          // this.noarticle.title = data.noarticle.title;
          // this.noarticle.content = data.noarticle.content;
          this.noarticle = data;
        },
        (error) => {
          console.log(error);
        },
      );
      this.isid = true;
    } else {
      this.noarticle.id = this.userInfo.id;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.noarticle.id &&
        ((msg = "작성자 입력해주세요"), (err = false), this.$refs.id.focus());
      err &&
        !this.noarticle.title &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.noarticle.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register"
          ? this.registNoArticle()
          : this.modifyNoArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.noarticle.idx = 0;
      this.noarticle.title = "";
      this.noarticle.content = "";
      this.$router.push({ name: "noticeList" });
    },
    registNoArticle() {
      writeNoArticle(
        {
          id: this.noarticle.id,
          title: this.noarticle.title,
          content: this.noarticle.content,
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
    modifyNoArticle() {
      modifyNoArticle(
        {
          idx: this.noarticle.idx,
          id: this.noarticle.id,
          title: this.noarticle.title,
          content: this.noarticle.content,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "noticeList" });
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveList() {
      this.$router.push({ name: "noticeList" });
    },
  },
};
</script>

<style></style>
