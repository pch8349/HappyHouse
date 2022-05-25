<template>
  <b-container
    v-if="interests && interests.length != 0"
    class="bv-example-row mt-3"
  >
    <b-table-simple hover responsive align="center">
      <tbody>
        <interest-list-item
          v-for="(interest, index) in interests"
          :key="index"
          :interest="interest"
          :idx="index"
        />
      </tbody>
    </b-table-simple>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>관심지역 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import InterestListItem from "@/components/interest/InterestListItem.vue";
import { mapState, mapActions } from "vuex";
import jwtDecode from "jwt-decode";

const interestStore = "interestStore";

export default {
  name: "InterestList",
  components: {
    InterestListItem,
  },
  data() {
    return {};
  },
  methods: {
    ...mapActions(interestStore, ["getInterestList"]),
  },
  created() {
    this.getInterestList({
      id: jwtDecode(sessionStorage.getItem("access-token")).id,
    });
  },
  computed: {
    ...mapState(interestStore, ["interests"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
};
</script>

<style></style>
