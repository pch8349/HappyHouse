<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show variant="dark"><h3>글작성</h3></b-alert>
      </b-col>
    </b-row>
    <notice-input-item type="register" />
  </b-container>
</template>

<script>
import NoticeInputItem from "@/components/notice/item/NoticeInputItem.vue";

export default {
  name: "noticeWrite",
  components: {
    NoticeInputItem,
  },
};
</script>

<style></style>
