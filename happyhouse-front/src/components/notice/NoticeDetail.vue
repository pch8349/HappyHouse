<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listNoArticle"
          >목록</b-button
        >
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyNoArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteNoArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${noarticle.idx}.
          ${noarticle.title}</h3><div><h6>${noarticle.id}</div><div>${noarticle.date}</h6></div>`"
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
//import http.get 하는 대신 src/api/notice.js의 api 설정을 가져옴
import { getNoArticle, deleteNoArticle } from "@/api/notice";

export default {
  name: "NoticeDetail",
  data() {
    return {
      noarticle: {},
    };
  },
  computed: {
    message() {
      if (this.noarticle.content)
        return this.noarticle.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    //http.get 해서 가져오기랑 같음
    getNoArticle(
      //src/api/getnoArticle에서 noarticleno, success, fail 3개 인자를 받으니 3개 설정
      //then. 과 같음
      this.$route.params.idx,
      (response) => {
        this.noarticle = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      },
    );
  },
  methods: {
    listNoArticle() {
      this.$router.push({ name: "noticeList" });
    },
    moveModifyNoArticle() {
      this.$router.replace({
        name: "noticeModify",
        params: { idx: this.noarticle.idx },
      });
      //   this.$router.push({ path: `/notice/modify/${this.noarticle.noarticleno}` });
    },
    deleteNoArticle() {
      if (confirm("정말로 삭제?")) {
        deleteNoArticle(this.noarticle.idx, () => {
          this.$router.push({ name: "noticeList" });
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
