<template>
  <b-tr>
    <b-td>{{ idx }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'noticeDetail', params: { idx: idx } }">{{
        title
      }}</router-link>
    </b-th>
    <b-td>{{ id }}</b-td>
    <b-td>{{ date }}</b-td>
  </b-tr>
</template>

<script>
export default {
  name: "NoticeListItem",
  props: {
    idx: Number,
    id: String,
    title: String,
    date: String,
  },
};
</script>

<style></style>
