<template>
  <b-row class="mt-4 mb-4 text-center">
    <!-- <b-col class="sm-3">
      <b-form-input
        v-model.trim="dongCode"
        placeholder="동코드 입력...(예 : 11110)"
        @keypress.enter="sendKeyword"
      ></b-form-input>
    </b-col>
    <b-col class="sm-3" align="left">
      <b-button variant="outline-primary" @click="sendKeyword">검색</b-button>
    </b-col> -->
    <b-col class="sm-1"></b-col>
    <b-col class="sm-5">
      <b-form-select
        v-model="sidoCode"
        :options="sidos"
        @change="gugunList($event)"
      ></b-form-select>
    </b-col>
    <b-col class="sm-5">
      <b-form-select v-model="gugunCode" :options="guguns"></b-form-select>
    </b-col>
    <b-col class="sm-1">
      <div>
        <b-button
          variant="outline-info"
          size="sm"
          @click="registInterest"
          class="mr-2"
          >등록</b-button
        >
      </div>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions /*, mapMutations */ } from "vuex";
import jwtDecode from "jwt-decode";
import { interestRegist } from "@/api/interest";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    userStore: userStore,
    houseStore: houseStore
  }  
*/
const interestStore = "interestStore";

export default {
  name: "InterestSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
    };
  },
  computed: {
    ...mapState(interestStore, [
      "sidos",
      "guguns",
      "interests",
      "interest",
      "sidoname",
      "gugunname",
    ]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    //mounted 되기 전에 필요한 데이터가 있으면 method의 map 데이터를 호출
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.clearSido();
    this.getSido();
  },
  methods: {
    ...mapActions(interestStore, [
      "getSido",
      "getGugun",
      "getInterestList",
      "clearSido",
      "clearGugun",
      "setSidoName",
      "setGugunName",
      "setInterestOne",
    ]), // houseStore.js에서 acsions에 clearsido, cleargugun 추가
    // ...mapMutations(houseStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST"]), // 이거 지우기
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      // method의 mapaction 호출
      //mutations는 직접호출하지 않음.
      // console.log(event.target.value);
      this.clearGugun();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    async registInterest() {
      for (let i = 0; i < this.sidos.length; i++) {
        // 법정동 코드(시, 구)
        if (this.sidos[i].value == this.sidoCode)
          await this.setSidoName(this.sidos[i].text);
      }
      for (let i = 0; i < this.guguns.length; i++) {
        if (this.guguns[i].value == this.gugunCode)
          await this.setGugunName(this.guguns[i].text);
      }
      console.log(this.sidoname, this.gugunname);
      await interestRegist({
        sidoName: this.sidoname,
        gugunName: this.gugunname,
        id: jwtDecode(sessionStorage.getItem("access-token")).id,
      });
      console.log(this.sidoname + this.gugunname);
      await this.setInterestOne({
        sidoName: this.sidoname,
        gugunName: this.gugunname,
        id: jwtDecode(sessionStorage.getItem("access-token")).id,
      });
    },
  },
};
</script>

<style></style>
