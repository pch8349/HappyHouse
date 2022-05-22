/* eslint-disable prettier/prettier */
import { sidoList, gugunList, houseList } from "@/api/house.js";
//api로 주고받은 값을 뿌리기 위한 store

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시/도를 선택하세요" }],
    guguns: [{ value: null, text: "구/군을 선택하세요" }],
    houses: [],
    house: null,
    sidoname: "",
    gugunname: "",
  }, // computed로. 데이터 캐싱, 데이터 변경시 용이..
  //state 데이터 참조는 ...mapStates 사용

  getters: {}, //getter 사용 x, state로 접근하기!

  mutations: {
    // 세팅파트. state에 데이터 작성해서, mutations에서 데이터 뿌리기.
    //외부에선 직접 호출하지 않고 actions에서 호출함
    //동기 비동기가 맞지 않으면, null에러가 나거나 할 수 있음. 시점이 맞지 않아서?
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "시/도를 선택하세요" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "구/군을 선택하세요" }];
    },
    SET_HOUSE_LIST: (state, houses) => {
      //   console.log(houses);
      state.houses = houses;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    SET_SIDO_NAME: (state, sidoname) => {
      state.sidoname = sidoname;
    },
    SET_GUGUN_NAME: (state, gugunname) => {
      state.gugunname = gugunname;
    },
  },

  actions: {
    setSidoName: ({ commit }, sidoname) => {
      commit( "SET_SIDO_NAME", sidoname);
    },
    setGugunName: ({ commit }, gugunname) => {
      commit( "SET_GUGUN_NAME", gugunname);
    },
    clearSido: ({ commit }) => {
      commit("CLEAR_SIDO_LIST");
    },
    clearGugun: ({ commit }) => {
      commit("CLEAR_GUGUN_LIST");
    },
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseList: ({ commit }, { gugunCode, ymd }) => {
      console.log(ymd);
      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.
      const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      //   const SERVICE_KEY =
      //     "9Xo0vlglWcOBGUDxH8PPbuKnlBwbWU6aO7%2Bk3FV4baF9GXok1yxIEF%2BIwr2%2B%2F%2F4oVLT8bekKU%2Bk9ztkJO0wsBw%3D%3D";
      const params = {
        LAWD_CD: gugunCode,
        DEAL_YMD: ymd,
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      houseList(
        params,
        (response) => {
          //   console.log(response.data.response.body.items.item);
          commit("SET_HOUSE_LIST", response.data.response.body.items.item);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
  },
};

export default houseStore;
