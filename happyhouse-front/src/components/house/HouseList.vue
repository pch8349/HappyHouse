<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <b-col class="text-right" @click="changearea">
      <img src="@/assets/changeButton.png" style="width: 40px" />
    </b-col>
    <br />
    <b-table-simple hover responsive align="center">
      <b-thead>
        <b-tr>
          <b-th>일련번호</b-th>
          <b-th>아파트명</b-th>
          <b-th>법정동</b-th>
          <b-th>층수</b-th>
          <b-th>전용면적</b-th>
          <b-th>거래금액</b-th>
        </b-tr>
      </b-thead>
      <b-tbody>
        <house-list-item
          v-for="(house, index) in houses"
          :key="index"
          :house="house"
          :flag="flag"
        />
      </b-tbody>
    </b-table-simple>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {
      flag: false,
    };
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
  methods: {
    changearea() {
      this.flag = !this.flag;
    },
  },
};
</script>

<style></style>
