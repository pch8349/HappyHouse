<template>
  <div>
    <b-row class="m-2">
      <b-col cols="10" class="align-self-center">
        {{ idx + 1 }}. {{ interest.sidoName }} {{ interest.gugunName }}
      </b-col>
    </b-row>
    <b-row class="m-2">
      <b-col class="sm-1">
        <div>
          <b-button
            variant="outline-info"
            size="sm"
            @click="selectHospital"
            class="mr-2"
            >병원 보기</b-button
          >
        </div>
      </b-col>
      <b-col class="sm-1">
        <div>
          <b-button
            variant="outline-info"
            size="sm"
            @click="selectShop"
            class="mr-2"
            >상가 보기</b-button
          >
        </div>
      </b-col>
      <b-col class="sm-1" />
      <b-col class="sm-1">
        <div>
          <b-button
            variant="outline-info"
            size="sm"
            @click="deleteInterest"
            class="mr-2"
            >삭제</b-button
          >
        </div>
      </b-col>
    </b-row>
    <!-- <b-row
    @click="selectInterest"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  ></b-row> -->
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import { interestDelete } from "@/api/interest";

const interestStore = "interestStore";

export default {
  name: "InterestListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    interest: Object,
    idx: Number,
  },
  computed: {
    ...mapState(interestStore, [
      "sidos",
      "guguns",
      "interests",
      "sidoname",
      "gugunname",
    ]),
  },
  methods: {
    ...mapActions(interestStore, [
      "deleteInterestOne",
      "detailInterest",
      "setShopList",
      "setHospitalList",
    ]),
    selectHospital() {
      this.detailInterest(this.interest);
      this.setHospitalList({
        sidoname: this.interest.sidoName,
        gugunname: this.interest.gugunName,
      });
    },
    selectShop() {
      this.detailInterest(this.interest);
      console.log("하이");
      console.log(this.interest.sidoName);
      this.setShopList({
        sidoname: this.interest.sidoName,
        gugunname: this.interest.gugunName,
      });
    },
    deleteInterest() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        interestDelete(this.interest.idx);
        this.deleteInterestOne(this.interest.idx);
      }
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
