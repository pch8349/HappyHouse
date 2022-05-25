<template>
  <b-tr
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-th>{{ house.일련번호 }}</b-th>
    <b-th>{{ house.아파트 }}</b-th>
    <b-th>{{ house.법정동 }}</b-th>
    <b-th>{{ house.층 }}</b-th>
    <b-th v-if="flag">{{ house.전용면적 }}m<sup>2</sup></b-th>
    <b-th v-else>{{ house.전용면적 | changeToPyeong }} 평</b-th>
    <b-th>{{ house.거래금액 }}만</b-th>
  </b-tr>
  <!-- <b-row
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="2" class="text-center align-self-center"> </b-col>
    <b-col cols="10" class="align-self-center">
      [{{ house.일련번호 }}] {{ house.아파트 }}
    </b-col>
  </b-row> -->
</template>

<script>
import { mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
    flag: Boolean,
  },
  filters: {
    changeToPyeong(house) {
      return (house / 3.3058).toFixed(2);
    },
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
