/* eslint-disable prettier/prettier */
import { sidoList, gugunList, interestList, shopList, hospitalList } from "@/api/interest.js";
//api로 주고받은 값을 뿌리기 위한 store

const interestStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시/도를 선택하세요" }],
    guguns: [{ value: null, text: "구/군을 선택하세요" }],
    interests: [],
    interest: null,
    sidoname: "",
    gugunname: "",
    shophospitals: [],
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
    SET_INTEREST_LIST: (state, interests) => {
      //   console.log(interests);
      state.interests = interests;
    },
    SET_DETAIL_INTEREST: (state, interest) => {
      state.interest = interest;
    },
    SET_SIDO_NAME: (state, sidoname) => {
      state.sidoname = sidoname;
    },
    SET_GUGUN_NAME: (state, gugunname) => {
      state.gugunname = gugunname;
    },
    SET_SHOPHOSPITAL_LIST: (state, shophospitals) => {
      state.shophospitals = shophospitals;
    },
    SET_INTEREST_ONE: (state, interest) => {
      state.interests.push(interest);
    },
    DELELTE_INTEREST_ONE: (state, idx) => {
      for (let i = 0; i < state.interests.length; i++) {
        if (idx == state.interests[i].idx) {
          state.interests.splice(i, 1);
          break;
        } 
      }
    },
  },

  actions: {
    setShopList: ({ commit }, { sidoname, gugunname }) => {
      shopList(
        { sidoName: sidoname, gugunName: gugunname },
        ({ data }) => {
          const dat = [];
          for (let i = 0; i < data.length; i++) {
            dat.push({
              info: `분류: ${data[i].shopCat} <br> 상호명: ${data[i].shopName} `,
              lat: data[i].lng,
              lng: data[i].lat,
              //위도 경도가 db에서 바뀌어있음
            });
          };
          commit("SET_SHOPHOSPITAL_LIST", dat);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    setHospitalList: ({ commit }, { sidoname, gugunname }) => {
      hospitalList(
        { sidoName: sidoname, gugunName: gugunname },
        ({ data }) => {
          const dat = [];
          for (let i = 0; i < data.length; i++) {
            dat.push({
              info: `병원명: ${data[i].hospitalName} `,
              lat: data[i].lat,
              lng: data[i].lng,
            });
          };
          console.log(dat);
          commit("SET_SHOPHOSPITAL_LIST", dat);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    deleteInterestOne: ({ commit }, idx) => {
      commit("DELELTE_INTEREST_ONE", idx);
    },
    setInterestOne: ({ commit }, interest) => {
      commit("SET_INTEREST_ONE", interest);
    },
    setShopHospitalList: ({ commit }, shopHospitals) => {
      commit("SET_SHOPHOSPITAL_LIST", shopHospitals);
    },
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
    getInterestList: ({ commit }, { id }) => {
      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.
      //   const SERVICE_KEY =
      //     "9Xo0vlglWcOBGUDxH8PPbuKnlBwbWU6aO7%2Bk3FV4baF9GXok1yxIEF%2BIwr2%2B%2F%2F4oVLT8bekKU%2Bk9ztkJO0wsBw%3D%3D";
      interestList(
        id,
        (response) => {
          //   console.log(response.data.response.body.items.item);
          commit("SET_INTEREST_LIST", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailInterest: ({ commit }, interest) => {
      // interest에 하나 등록
      commit("SET_DETAIL_INTEREST", interest);
    },
  },
};

export default interestStore;
