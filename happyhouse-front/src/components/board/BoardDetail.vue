<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show variant="dark"><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-secondary" @click="listArticle"
          >목록</b-button
        >
      </b-col>
      <b-col v-if="check" class="text-right">
        <b-button
          variant="outline-dark"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.articleno}.
          ${article.subject} [${article.hit}]</h3><div><h6>${article.id}</div><div>${article.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
//import http.get 하는 대신 src/api/board.js의 api 설정을 가져옴
import { getArticle, deleteArticle } from "@/api/board";
import jwtDecode from "jwt-decode";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
      id: "",
      check: false,
      check2: false,
    };
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  beforeUpdate() {
    this.id = jwtDecode(sessionStorage.getItem("access-token")).id;
    this.check = this.id == "admin";
    console.log(this.check);
    console.log(this.article);
    this.check2 = this.article.id == this.id;
    this.check = this.check | this.check2;
    console.log(this.check2);
  },
  created() {
    //http.get 해서 가져오기랑 같음
    getArticle(
      //src/api/getArticle에서 articleno, success, fail 3개 인자를 받으니 3개 설정
      //then. 과 같음
      this.$route.params.articleno,
      (response) => {
        this.article = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      },
    );
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        deleteArticle(this.article.articleno, () => {
          this.$router.push({ name: "boardList" });
        });
      }
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
