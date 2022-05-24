<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-orange">
      <b-icon icon="cart4"></b-icon> Interest Service
    </h3>
    <b-row>
      <b-col>
        <interest-search-bar></interest-search-bar>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="6" align="left">
        <interest-list />
      </b-col>
      <b-col cols="6">
        <interest-detail />
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
import InterestSearchBar from "@/components/interest/InterestSearchBar.vue";
import InterestList from "@/components/interest/InterestList.vue";
import InterestDetail from "@/components/interest/InterestDetail.vue";

export default {
  name: "InterestView",
  components: {
    InterestSearchBar,
    InterestList,
    InterestDetail,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
</style>
