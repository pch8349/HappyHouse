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
    <b-col class="sm-3">
      <b-form-select
        v-model="sidoCode"
        :options="sidos"
        @change="gugunList"
      ></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select
        v-model="gugunCode"
        :options="guguns"
        @change="searchApt"
      ></b-form-select>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions /*, mapMutations*/ } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    memberStore: memberStore,
    houseStore: houseStore
  }  
*/
const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "houses"]),
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
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getHouseList",
      "clearSido",
      "clearGugun",
    ]), // houseStore.js에서 acsions에 clearsido, cleargugun 추가
    // ...mapMutations(houseStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST"]), // 이거 지우기
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      // method의 mapaction 호출
      //mutations는 직접호출하지 않음.
      console.log(this.sidoCode);
      this.clearGugun();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchApt() {
      if (this.gugunCode) this.getHouseList(this.gugunCode);
    },
  },
};
</script>

<style></style>
