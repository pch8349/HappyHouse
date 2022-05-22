<template>
  <b-container v-if="house" class="bv-example-row">
    <b-row>
      <b-col
        ><h3>{{ house.아파트 }}</h3></b-col
      >
    </b-row>
    <b-row class="mb-2 mt-1">
      <!-- <b-col
        ><b-img :src="require('@/assets/apt.png')" fluid-grow></b-img
      ></b-col> -->
      <GmapMap
        ref="mapRef"
        :center="center"
        :zoom="16"
        style="width: 100vw; height: 50vh"
      ></GmapMap>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >일련번호 : {{ house.일련번호 }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="primary"
          >아파트 이름 : {{ house.아파트 }}
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="info">법정동 : {{ house.법정동 }} </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="warning">층수 : {{ house.층 }}층</b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="danger"
          >거래금액 :
          {{
            (parseInt(house.거래금액.replace(",", "")) * 10000) | price
          }}원</b-alert
        >
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import $ from "jquery";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  data() {
    return {
      center: {
        lat: 37.500131499999995,
        lng: 127.03242579999998,
      },
    };
  },
  methods: {
    getLocation() {
      $.ajax({
        url: `https://maps.googleapis.com/maps/api/geocode/json?address=${$(
          "#sido option:selected",
        ).text()}+${$("#gugun option:selected").text()}+${$(
          "#dong option:selected",
        ).text()}&key=AIzaSyASKTABus6UEFjZ_wBGW4ZqOMWhA38QCSM&sensor=false`,
        type: "POST",
        async: false,
        success: function (myJSONResult) {
          if (myJSONResult.status == "OK") {
            this.center = {
              lat: myJSONResult.results[0].geometry.location.lat,
              lng: myJSONResult.results[0].geometry.location.lng,
            };
          } else {
            console.log(myJSONResult);
          }
        },
      });
    },
  },
  computed: {
    ...mapState(houseStore, ["house", "sidoname", "gugunname"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style></style>
