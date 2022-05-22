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
      <b-form-select v-model="yyyy" :options="yList"></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select v-model="mm" :options="mList"></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select
        v-model="sidoCode"
        :options="sidos"
        @change="gugunList($event)"
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
import { mapState, mapActions /*, mapMutations */ } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    userStore: userStore,
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
      yyyy: null,
      mm: null,
      yList: [{ text: "연도를 선택하세요", value: null }],
      mList: [{ text: "월을 선택하세요", value: null }],
    };
  },
  computed: {
    ...mapState(houseStore, [
      "sidos",
      "guguns",
      "houses",
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

    const nowYear = new Date().getFullYear();
    for (let i = 0; i < 30; i++) {
      let date = nowYear - i;
      this.yList.push({ value: date, text: date + "년" });
    }
    for (let i = 1; i < 13; i++) {
      if (i < 10) this.mList.push({ value: "0" + String(i), text: i + "월" });
      else this.mList.push({ value: i, text: i + "월" });
    }
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getHouseList",
      "clearSido",
      "clearGugun",
      "setSidoName",
      "setGugunName",
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
    searchApt() {
      for (let i = 0; i < this.sidos.length; i++) {
        // 법정동 코드(시, 구)
        if (this.sidos[i].value == this.sidoCode)
          this.setSidoName(this.sidos[i].text);
      }
      for (let i = 0; i < this.guguns.length; i++) {
        if (this.guguns[i].value == this.gugunCode)
          this.setGugunName(this.guguns[i].text);
      }
      console.log(this.sidoname + this.gugunname);
      if (this.gugunCode)
        this.getHouseList({
          gugunCode: this.gugunCode,
          ymd: String(this.yyyy) + String(this.mm),
        });
      console.log(String(this.yyyy) + String(this.mm));
    },
  },
};
</script>

<style></style>
