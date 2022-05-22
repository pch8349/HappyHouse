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
      >
        <GmapMarker
          :key="index"
          v-for="(m, index) in markers"
          :position="m.position"
        />
      </GmapMap>
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
      markers: [
        {
          position: {
            lat: 37.500131499999995,
            lng: 127.03242579999998,
          },
        },
      ],
    };
  },
  computed: {
    ...mapState(houseStore, ["house", "sidoname", "gugunname"]),
  },

  methods: {
    async getLocation() {
      let vthis = this; // $ajax에서는 그냥 this 사용 불가
      await $.ajax({
        url: `https://maps.googleapis.com/maps/api/geocode/json?address=
        ${vthis.sidoname} ${vthis.gugunname} ${vthis.house.법정동} ${vthis.house.지번}
        &key=AIzaSyASKTABus6UEFjZ_wBGW4ZqOMWhA38QCSM&sensor=false`,
        type: "POST",
        async: false,
        success: function (myJSONResult) {
          if (myJSONResult.status == "OK") {
            vthis.center = {
              lat: myJSONResult.results[0].geometry.location.lat,
              lng: myJSONResult.results[0].geometry.location.lng,
            };
            vthis.markers[0].position = {
              lat: myJSONResult.results[0].geometry.location.lat,
              lng: myJSONResult.results[0].geometry.location.lng,
            };
          } else {
            console.log(myJSONResult);
          }
          console.log(vthis.house.법정동);
        },
      });
    },
  },
  beforeUpdate() {
    // 라이프사이클 시점의 문제!!
    this.getLocation();
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
